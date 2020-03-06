<?php
if (ini_get('allow_url_fopen')) {
	die('allow_url_fopen is enabled. file_get_contents should work well');
} else {
	die('allow_url_fopen is disabled. file_get_contents would not work');
}
$url = 'http://numbersapi.com/09/28';
$contents_from_url = file_get_contents($url);
echo 'Did you know that ' . $contents_from_url;
