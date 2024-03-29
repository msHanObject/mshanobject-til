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

namespace FluentDOM {

  /**
   * FluentDOM\XmlSerializable describes an interface for objects that can be serialized to
   * and XML fragment (without document element and declaration).
   */
  interface XmlSerializable extends Appendable
  {

    /**
     * Return the object as an XML fragment.
     *
     * @return string
     */
      public function getXml(): string;
  }
}
