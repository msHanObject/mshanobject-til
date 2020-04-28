<?php

namespace PhpCss\Ast\Selector\Simple {

  use PhpCss\Ast;

  class PseudoClass extends Ast\Selector\Simple
  {
      public $name = '';
      public $parameter = null;

      public function __construct($name, $parameter = null)
      {
          $this->name = $name;
          $this->parameter = $parameter;
      }

      public function accept(Ast\Visitor $visitor)
      {
          if ($this->parameter instanceof Ast) {
              if ($visitor->visitEnter($this)) {
                  $this->parameter->accept($visitor);
                  $visitor->visitLeave($this);
              }
          } else {
              $visitor->visit($this);
          }
      }
  }
}
