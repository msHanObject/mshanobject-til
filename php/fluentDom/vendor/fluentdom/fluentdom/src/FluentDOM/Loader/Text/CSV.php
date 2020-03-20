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

namespace FluentDOM\Loader\Text {

  use FluentDOM\DOM\Document;
  use FluentDOM\DOM\DocumentFragment;
  use FluentDOM\DOM\Element;
  use FluentDOM\Loadable;
  use FluentDOM\Loader\Options;
  use FluentDOM\Loader\Result;
  use FluentDOM\Loader\Supports;
  use FluentDOM\Utility\QualifiedName;

  /**
   * Load a CSV file
   */
  class CSV implements Loadable
  {
      use Supports;

      const XMLNS = 'urn:carica-json-dom.2013';
      const DEFAULT_QNAME = '_';

      private $_delimiter = ',';
      private $_enclosure = '"';
      private $_escape = '\\';

      /**
       * @return string[]
       */
      public function getSupported(): array
      {
          return ['text/csv'];
      }

      /**
       * @see Loadable::load
       * @param mixed $source
       * @param string $contentType
       * @param array|\Traversable|Options $options
       * @return Document|Result|NULL
       * @throws \InvalidArgumentException
       * @throws \FluentDOM\Exceptions\InvalidSource
       */
      public function load($source, string $contentType, $options = [])
      {
          $options = $this->getOptions($options);
          $hasHeaderLine = isset($options['HEADER']) ? (bool)$options['HEADER'] : !isset($options['FIELDS']);
          $this->configure($options);
          if ($this->supports($contentType) && ($lines = $this->getLines($source, $options))) {
              $document = new Document('1.0', 'UTF-8');
              $document->appendChild($list = $document->createElementNS(self::XMLNS, 'json:json'));
              $list->setAttributeNS(self::XMLNS, 'json:type', 'array');
              $this->appendLines($list, $lines, $hasHeaderLine, $options['FIELDS'] ?? null);
              return $document;
          }
          return null;
      }

      /**
       * @see Loadable::loadFragment
       *
       * @param string $source
       * @param string $contentType
       * @param array|\Traversable|Options $options
       * @return DocumentFragment|NULL
       * @throws \InvalidArgumentException
       * @throws \FluentDOM\Exceptions\InvalidSource
       */
      public function loadFragment($source, string $contentType, $options = [])
      {
          $options = $this->getOptions($options);
          $options[Options::ALLOW_FILE] = false;
          $hasHeaderLine = isset($options['FIELDS']) ? false : (isset($options['HEADER']) && $options['HEADER']);
          $this->configure($options);
          if ($this->supports($contentType) && ($lines = $this->getLines($source, $options))) {
              $document = new Document('1.0', 'UTF-8');
              $fragment = $document->createDocumentFragment();
              $this->appendLines($fragment, $lines, $hasHeaderLine, $options['FIELDS'] ?? null);
              return $fragment;
          }
          return null;
      }

      /**
       * Append the provided lines to the parent.
       *
       * @param \DOMNode $parent
       * @param array|\Traversable $lines
       * @param bool $hasHeaderLine
       * @param array $columns
       */
      private function appendLines(\DOMNode $parent, $lines, bool $hasHeaderLine, array $columns = null)
      {
          $document = $parent instanceof \DOMDocument ? $parent : $parent->ownerDocument;
          $headers = null;
          /** @var array $record */
          foreach ($lines as $record) {
              if ($headers === null) {
                  $headers = $this->getHeaders(
              $record,
              $hasHeaderLine,
              $columns
          );
                  if ($hasHeaderLine) {
                      continue;
                  }
              }
              /** @var Element $node */
              $node = $parent->appendChild($document->createElement(self::DEFAULT_QNAME));
              foreach ($record as $index => $field) {
                  if (isset($headers[$index])) {
                      $this->appendField($node, (string)$headers[$index], (string)$field);
                  }
              }
          }
      }

      /**
       * @param Element $parent
       * @param string $name
       * @param string $value
       */
      private function appendField(Element $parent, string $name, string $value)
      {
          $qname = QualifiedName::normalizeString($name, self::DEFAULT_QNAME);
          $child = $parent->appendElement($qname, $value);
          if ($qname !== $name) {
              $child->setAttributeNS(self::XMLNS, 'json:name', $name);
          }
      }

      /**
       * @param array $record
       * @param bool $hasHeaderLine
       * @param array|NULL $columns
       * @return array
       */
      private function getHeaders(array $record, bool $hasHeaderLine, $columns = null): array
      {
          if (\is_array($columns)) {
              $headers = [];
              foreach ($record as $index => $field) {
                  $key = $hasHeaderLine ? $field : $index;
                  $headers[$index] = $columns[$key] ?? false;
              }
              return $headers;
          }
          if ($hasHeaderLine) {
              return $record;
          }
          return \array_keys($record);
      }

      /**
       * @param mixed $source
       * @param Options $options
       * @return NULL|\Traversable
       * @throws \FluentDOM\Exceptions\InvalidSource
       */
      private function getLines($source, Options $options)
      {
          $result = null;
          if (\is_string($source)) {
              $options->isAllowed($sourceType = $options->getSourceType($source));
              if ($sourceType === Options::IS_FILE) {
                  $result = new \SplFileObject($source);
              } else {
                  $result = new \SplFileObject('data://text/csv;base64,'.\base64_encode($source));
              }
              $result->setFlags(\SplFileObject::READ_CSV);
              $result->setCsvControl(
            $this->_delimiter,
            $this->_enclosure,
            $this->_escape
        );
          } elseif (\is_array($source)) {
              $result = new \ArrayIterator($source);
          } elseif ($source instanceof \Traversable) {
              $result = $source;
          }
          return empty($result) ? null : $result;
      }

      /**
       * @param array|\Traversable|Options $options
       */
      private function configure($options)
      {
          $this->_delimiter = $options['DELIMITER'] ?? $this->_delimiter;
          $this->_enclosure = $options['ENCLOSURE'] ?? $this->_enclosure;
          $this->_escape = $options['ESCAPE'] ?? $this->_escape;
      }

      /**
       * @param array|\Traversable|Options $options
       * @return Options
       * @throws \InvalidArgumentException
       */
      public function getOptions($options): Options
      {
          $result = new Options(
          $options,
          [
          Options::CB_IDENTIFY_STRING_SOURCE => function ($source) {
              return (\is_string($source) && (false !== \strpos($source, "\n")));
          }
        ]
      );
          return $result;
      }
  }

}
