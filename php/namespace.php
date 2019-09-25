<?php
namespace my\name;

class MyClass {}
function myfuction() {}
const MYCONST = 1;

$a = new MyClass;
$c = new \my\name\MyClass;

$a = strlen('hi');

$d = namespace\MYCONST;

$d = __NAMESPACE__.'\MYCONST';
echo constant($d);
?>
