<?php
/**
 * FluentDOM
 *
 * @link https://thomas.weinert.info/FluentDOM/
 * @copyright Copyright 2009-2018 FluentDOM Contributors
 * @license http://www.opensource.org/licenses/mit-license.php The MIT License
 *
 */

namespace FluentDOM\Creator {

  use FluentDOM\Appendable;
  use FluentDOM\DOM\Element;

  /**
   * Internal class for the FluentDOM\Creator, please do not use directly
   */
  class Nodes implements Appendable, \OuterIterator
  {

    /**
     * @var array|\Traversable
     */
      private $_iterable;

      /**
       * @var callable|NULL
       */
      private $_map;

      /**
       * @var NULL|\Iterator
       */
      private $_iterator;

      /**
       * @param array|\Traversable $iterable
       * @param callable $map
       */
      public function __construct($iterable, callable $map = null)
      {
          if (\is_array($iterable) || $iterable instanceof \Traversable) {
              $this->_iterable = $iterable;
          }
          $this->_map = $map;
      }

      /**
       * @return \Iterator
       */
      public function getInnerIterator(): \Iterator
      {
          if (null === $this->_iterator) {
              if ($this->_iterable instanceof \Iterator) {
                  $this->_iterator = $this->_iterable;
              } elseif (\is_array($this->_iterable)) {
                  $this->_iterator = new \ArrayIterator($this->_iterable);
              } else {
                  $this->_iterator = (null !== $this->_iterable)
            ? new \IteratorIterator($this->_iterable)
            : new \EmptyIterator();
              }
          }
          return $this->_iterator;
      }

      public function rewind()
      {
          $this->getInnerIterator()->rewind();
      }

      public function next()
      {
          $this->getInnerIterator()->next();
      }

      /**
       * @return string|int|float
       */
      public function key()
      {
          return $this->getInnerIterator()->key();
      }

      /**
       * @return mixed
       */
      public function current()
      {
          if (null !== $this->_map) {
              $map = $this->_map;
              return $map(
            $this->getInnerIterator()->current(),
            $this->getInnerIterator()->key()
        );
          }
          return $this->getInnerIterator()->current();
      }

      /**
       * @return bool
       */
      public function valid(): bool
      {
          return $this->getInnerIterator()->valid();
      }

      /**
       * @param Element $parent
       * @return Element
       */
      public function appendTo(Element $parent): Element
      {
          foreach ($this as $item) {
              $parent->append($item);
          }
          return $parent;
      }
  }
}
