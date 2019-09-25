<?php
var_dump(round(3.4));
var_dump(round(3.5));
var_dump(round(3.6));
var_dump(round(3.6, 0));
var_dump(round(1.95583, 2));
var_dump(round(1241757, -3));
var_dump(round(5.045, 2));
var_dump(round(5.055, 2));
echo 'Rounding modes with 9.5'.PHP_EOL;
var_dump(round(9.5, 0, PHP_ROUND_HALF_UP));
var_dump(round(9.5, 0, PHP_ROUND_HALF_DOWN));
var_dump(round(9.5, 0, PHP_ROUND_HALF_EVEN));
var_dump(round(9.5, 0, PHP_ROUND_HALF_ODD));
echo 'Using PHP_ROUND_HALF_ODD with 1 decimal digit precision'.PHP_EOL;
var_dump(round(1.55, 1, PHP_ROUND_HALF_ODD));
var_dump(round(1.45, 1, PHP_ROUND_HALF_ODD));
var_dump(round(1.55, 2, PHP_ROUND_HALF_ODD));
?>
