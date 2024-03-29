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
   * FluentDOMCss is used for the FluentDOM:css property, providing an array like interface
   * to the css properties in the style attribute of the selected nodes(s)
   *
   * It acts like the FluentDOMStyle::css() method. If you read css properties it uses the first
   * selected node. Write actions are applied to all matches element nodes.
   *
   * @package FluentDOM
   */
  class Css implements \ArrayAccess, \Countable, \IteratorAggregate
  {

    /**
     * Pattern to decode the style property string
     */
      const STYLE_PATTERN = /** @lang TEXT */
      '((?:^|;)\s*(?P<name>[-\w]+)\s*:\s*(?P<value>[^;]+))';

      /**
       * owner object
       * @var Query
       */
      private $_query;


      /**
       * Store the FluentDOM instance for later use and decode the style string into an array
       *
       * @param Query $query
       */
      public function __construct(Query $query)
      {
          $this->_query = $query;
      }

      /**
       * Get the style properties from the first node in the Query object
       *
       * @return Css\Properties|NULL
       */
      private function getStyleProperties()
      {
          if (isset($this->_query[0])) {
              $node = $this->_query[0];
              if ($node instanceof \DOMElement) {
                  return new Css\Properties($node->getAttribute('style'));
              }
          }
          return null;
      }

      /**
       * Allow to use isset() and array syntax to check if a css property is set on
       * the first matched node.
       *
       * @see \ArrayAccess::offsetExists()
       * @param string $name
       * @return bool
       */
      public function offsetExists($name): bool
      {
          if ($properties = $this->getStyleProperties()) {
              return isset($properties[$name]);
          }
          return false;
      }

      /**
       * Allow to use array syntax to read a css property value from first matched node.
       *
       * @see ArrayAccess::offsetGet()
       * @param string $name
       * @return bool|mixed $value
       */
      public function offsetGet($name)
      {
          if ($properties = $this->getStyleProperties()) {
              return $properties[$name];
          }
          return false;
      }

      /**
       * Allow to use array syntax to change a css property value on all matched nodes.
       *
       * @see ArrayAccess::offsetSet()
       * @param string $name
       * @param string $value
       */
      public function offsetSet($name, $value)
      {
          $this->_query->css($name, $value);
      }

      /**
       * Allow to use unset and array syntax to remove a css property value on
       * all matched nodes.
       *
       * @see ArrayAccess::offsetUnset()
       * @param string $name
       */
      public function offsetUnset($name)
      {
          foreach ($this->_query as $node) {
              if ($node instanceof \DOMElement &&
          $node->hasAttribute('style')) {
                  $properties = new Css\Properties($node->getAttribute('style'));
                  unset($properties[$name]);
                  if (\count($properties) > 0) {
                      $node->setAttribute('style', (string)$properties);
                  } else {
                      $node->removeAttribute('style');
                  }
              }
          }
      }

      /**
       * Get an iterator for the properties
       *
       * @see IteratorAggregate::getIterator()
       * @return \Iterator
       */
      public function getIterator(): \Iterator
      {
          if ($properties = $this->getStyleProperties()) {
              return $properties->getIterator();
          }
          return new \EmptyIterator();
      }

      /**
       * Get the property count of the first selected node
       *
       * @see Countable::count()
       * @return int
       */
      public function count(): int
      {
          if ($properties = $this->getStyleProperties()) {
              return \count($properties);
          }
          return 0;
      }
  }
}
