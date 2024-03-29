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

namespace FluentDOM\Query {

  use FluentDOM\Query;

  /**
   * FluentDOM\Query\Attributes is used for the FluentDOM\Query:attr property, providing an array like interface
   * to the attributes of the selected nodes(s)
   *
   * It acts like the FluentDOM\Query::attr() method. If you read attributes it uses the first
   * selected node. Write actions are applied to all matches element nodes.
   */
  class Attributes implements \ArrayAccess, \Countable, \IteratorAggregate
  {

    /**
     * owner object
     * @var Query
     */
      private $_query;

      /**
       * Store the FluentDOM instance for later use
       *
       * @param Query $query
       */
      public function __construct(Query $query)
      {
          $this->_query = $query;
      }

      /**
       * Convert the attributes of the first node into an array
       *
       * @return array
       */
      public function toArray(): array
      {
          $result = [];
          if ($node = $this->getFirstElement()) {
              foreach ($node->attributes as $attribute) {
                  $result[$attribute->name] = $attribute->value;
              }
          }
          return $result;
      }

      /**
       * Check if the first selected node has the specified attribute
       *
       * @see ArrayAccess::offsetExists()
       * @param string $name
       * @return bool
       */
      public function offsetExists($name): bool
      {
          if ($node = $this->getFirstElement()) {
              return $node->hasAttribute($name);
          }
          return false;
      }

      /**
       * Read the specified attribute from the first node
       *
       * @see ArrayAccess::offsetGet()
       * @see FluentDOM::attr()
       * @example properties/attr-get.php Usage: Get attribute property
       * @param string $name
       * @return string
       */
      public function offsetGet($name): string
      {
          return $this->_query->attr($name);
      }

      /**
       * Set the attribute on all selected element nodes
       *
       * @see ArrayAccess::offsetSet()
       * @see FluentDOM::attr()
       * @example properties/attr-set.php Usage: Set attribute property
       * @param string $name
       * @param string $value
       */
      public function offsetSet($name, $value)
      {
          $this->_query->attr($name, $value);
      }

      /**
       * Remove the attribute(s) on all selected element nodes
       *
       * @see ArrayAccess::offsetUnset()
       * @see FluentDOM::removeAttr()
       * @example properties/attr-unset.php Usage: Remove attribute properties
       * @param string|array $name
       */
      public function offsetUnset($name)
      {
          $this->_query->removeAttr($name);
      }

      /**
       * Get an iterator for the attributes of the first node
       *
       * @see IteratorAggregate::getIterator()
       * @return \Iterator
       */
      public function getIterator(): \Iterator
      {
          return new \ArrayIterator($this->toArray());
      }

      /**
       * Get the attribute count of the first selected node
       *
       * @see Countable::count()
       * @return int
       */
      public function count(): int
      {
          if ($node = $this->getFirstElement()) {
              return $node->attributes->length;
          }
          return 0;
      }

      /**
       * @return \DOMElement|NULL
       */
      private function getFirstElement()
      {
          if (isset($this->_query[0]) && $this->_query[0] instanceof \DOMElement) {
              return $this->_query[0];
          }
          return null;
      }
  }
}
