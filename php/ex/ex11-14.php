<?php
$ch = curl_init('snchmsobj.sncl.kr/ref/leanring_php/ex11-13.php');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// define file name with the path of file for writing  of cookie information sent by server
// If the file does not exist, it is saved in the same path as the current file with the name 'saved cookies'
curl_setopt($ch, CURLOPT_COOKIEJAR, __DIR__ . '/saved.cookies');
// cURL keeps cookies on a per-handle basis.
// COOKIEJAR represents where to write the cookie value and COOKIEFILE represents the setting to return the cookie to the server
curl_setopt($ch, CURLOPT_COOKIEFILE, __DIR__ . 'saved.cookies');
// verify server uniqueness
print_r(curl_version());
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, true);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 2);
$res = curl_exec($ch);
print $res;
