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

namespace FluentDOM\XMLWriter {

  use FluentDOM\Utility\Namespaces;

  class NamespaceDefinition
  {

    /**
     * @var int
     */
      private $_indent;
      /**
       * @var Namespaces
       */
      private $_namespaces;

      public function __construct($inherit = null)
      {
          $this->_indent = 0;
          $this->_namespaces = new Namespaces($inherit);
      }

      public function getDepth(): int
      {
          return $this->_indent;
      }

      public function increaseDepth(): int
      {
          return ++$this->_indent;
      }

      public function decreaseDepth(): int
      {
          if ($this->_indent > 0) {
              return --$this->_indent;
          }
          throw new \LogicException('Did not resolve namespace levels properly.');
      }

      public function registerNamespace($prefix, $namespaceURI)
      {
          $this->_namespaces[$prefix] = $namespaceURI;
      }

      public function resolveNamespace($prefix)
      {
          try {
              return $this->_namespaces->resolveNamespace($prefix);
          } catch (\LogicException $e) {
              return '';
          }
      }
  }
}
