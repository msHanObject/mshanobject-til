<?php
/**
 * FluentDOM
 *
 * @link https://thomas.weinert.info/FluentDOM/
 * @copyright Copyright 2009-2019 FluentDOM Contributors
 * @license http://www.opensource.org/licenses/mit-license.php The MIT License
 *
 */
declare(strict_types=1);

namespace FluentDOM\Transformer {

  use FluentDOM\DOM\Document;
  use FluentDOM\DOM\Element;
  use FluentDOM\DOM\Xpath;
  use FluentDOM\Utility\StringCastable;

  /**
   * Allows to transform JSONx to JsonDOM
   *
   * @package FluentDOM\Transformer
   */
  class JSONx implements StringCastable
  {
      const XMLNS_JSONX = 'http://www.ibm.com/xmlns/prod/2009/jsonx';
      const XMLNS_JSONDOM = 'urn:carica-json-dom.2013';

      /**
       * @var \DOMDocument
       */
      private $_document;

      /**
       * Import a DOM document and use the JsonDOM rules to convert it into JSONx.
       *
       * @param \DOMDocument $document
       */
      public function __construct(\DOMDocument $document)
      {
          $this->_document = $document;
      }

      /**
       * Create a JSONX document and return it as xml string
       *
       * @return string
       */
      public function __toString(): string
      {
          try {
              return $this->getDocument()->saveXML();
          } catch (\Throwable $e) {
              return '';
          }
      }

      /**
       * Create and return a JSONx document.
       *
       * @return Document
       */
      public function getDocument(): Document
      {
          $document = new Document();
          $document->registerNamespace('json', self::XMLNS_JSONX);
          $this->addNode($document, $this->_document->documentElement);
          return $document;
      }

      /**
       * @param Document|Element $parent
       * @param \DOMElement $node
       * @param bool $addNameAttribute
       */
      public function addNode($parent, \DOMElement $node, bool $addNameAttribute = false)
      {
          switch ($this->getType($node)) {
      case 'object':
        $result = $parent->appendElement('json:object');
        $this->appendChildNodes($result, $node, true);
        break;
      case 'array':
        $result = $parent->appendElement('json:array');
        $this->appendChildNodes($result, $node);
        break;
      case 'number':
        $result = $parent->appendElement('json:number', $node->nodeValue);
        break;
      case 'boolean':
        $result = $parent->appendElement('json:boolean', $node->nodeValue);
        break;
      case 'null':
        $result = $parent->appendElement('json:null');
        break;
      default:
        $result = $parent->appendElement('json:string', $node->nodeValue);
        break;
      }
          if ($addNameAttribute) {
              $name = $node->localName;
              if ($node->hasAttributeNS(self::XMLNS_JSONDOM, 'name')) {
                  $name = $node->getAttributeNS(self::XMLNS_JSONDOM, 'name');
              }
              $result['name'] = $name;
          }
      }

      /**
       * @param Element $target
       * @param \DOMElement $source
       * @param bool $addNameAttribute
       */
      private function appendChildNodes(Element $target, \DOMElement $source, bool $addNameAttribute = false)
      {
          $xpath = new Xpath($source->ownerDocument);
          /** @var \DOMElement $child */
          foreach ($xpath('*', $source) as $child) {
              $this->addNode($target, $child, $addNameAttribute);
          }
      }

      /**
       * @param \DOMElement $node
       * @return string
       */
      private function getType(\DOMElement $node): string
      {
          if ($node->hasAttributeNS(self::XMLNS_JSONDOM, 'type')) {
              return $node->getAttributeNS(self::XMLNS_JSONDOM, 'type');
          }
          $xpath = new Xpath($node->ownerDocument);
          return $xpath('count(*) > 0', $node) ? 'object' : 'string';
      }
  }
}
