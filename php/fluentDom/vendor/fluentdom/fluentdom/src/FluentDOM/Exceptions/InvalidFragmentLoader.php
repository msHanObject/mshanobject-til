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

  class InvalidFragmentLoader extends \InvalidArgumentException implements Exception
  {

    /**
     * @param string $className
     */
      public function __construct(string $className)
      {
          parent::__construct(sprintf('Loader "%s" can not load fragments.', $className));
      }
  }
}
