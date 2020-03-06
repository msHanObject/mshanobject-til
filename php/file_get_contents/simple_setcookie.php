<?php
$value = isset($_COOKIE['c']) ? $_COOKIE['c'] : 0;
$value++;
setcookie('c1', '0');
setcookie('c2', $value);
print "쿠키 개수 : " . count($_COOKIE) . "<br/>";
foreach ($_COOKIE as $k => $v) {
	print "$k: $v\n";
}
