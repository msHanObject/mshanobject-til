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

namespace FluentDOM\Loader {

  trait Supports
  {

    /**
     * @see Loadable::supports
     * @param string $contentType
     * @return bool
     */
      public function supports(string $contentType): bool
      {
          return in_array(strtolower($contentType), $this->getSupported(), true);
      }

      /**
       * @return string[]
       */
      public function getSupported(): array
      {
          return [];
      }

      /**
       * Allow the loaders to validate the first part of the provided string.
       *
       * @param string $haystack
       * @param string $needle
       * @param bool $ignoreWhitespace
       * @return bool
       */
      private function startsWith(string $haystack, string $needle, bool $ignoreWhitespace = true): bool
      {
          return $ignoreWhitespace
        ? (bool)\preg_match('(^\s*'.\preg_quote($needle, '(').')', $haystack)
        : 0 === \strpos($haystack, $needle);
      }
  }
}
