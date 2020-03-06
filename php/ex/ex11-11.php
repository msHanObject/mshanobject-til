<?php
$value = isset($_COOKIE['c']) ? $_COOKIE['c'] : 0;
$valeu++;
setcookie('b', '12');
setcookie('c', $value);
print 'count of cookies: ' . count($_COOKIE) . '<br/>';
foreach ($_COOKIE as $k => $v) {
	print $k . ' : ' . $v . "\n";
}
