<?php
$is_https = (isset($_SERVER['HTTPS']) && ($_SERVER['HTTPS'] == 'on'));
if (! $is_https) {
	$new_url = 'https://' . $_SERVER['HTTP_HOST'] .$_SERVER['REQUEST_URI'];
	header('Location: ' . $new_url);
	exit();
} else {
	print 'Your have come through the HTTPS protocool';
}
