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

namespace FluentDOM\DOM {

  use DOMDocumentType;

  /**
   * Extend DOMImplementation to return FluentDOM\DOM classes
   */
  class Implementation extends \DOMImplementation
  {
      public function createDocument($namespaceURI = null, $qualifiedName = null, DOMDocumentType $doctype = null)
      {
          $document = new Document();
          if ($doctype) {
              $document->appendChild($doctype);
          }
          if ($qualifiedName) {
              $document->appendChild($document->createElementNS($namespaceURI, $qualifiedName));
              $prefix = strstr($qualifiedName, ':', true);
              if ($prefix !== '' || !empty($namespaceURI)) {
                  $document->registerNamespace($prefix, $namespaceURI);
              }
          }
          return $document;
      }
  }
}
