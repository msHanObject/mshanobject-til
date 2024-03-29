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

namespace FluentDOM\Exceptions {

  use FluentDOM\Exception;

  class InvalidSerializer extends \UnexpectedValueException implements Exception
  {
      public function __construct(string $contentType, string $class)
      {
          parent::__construct(
          \sprintf(
            'Invalid serializer for content type %s, instances of %s are not castable to string.',
            $contentType,
            $class
        )
      );
      }
  }
}
