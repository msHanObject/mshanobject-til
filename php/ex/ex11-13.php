<?php
$ch = curl_init('snchmsobj.sncl.kr');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_COOKIEJAR, true);
$new_cookie = isset($_COOKIE['nc']) ? $_COOKIE['nc'] : 0;
setcookie('nc', $new_cookie);
$res = curl_exec($ch);
print $res;
$res = curl_exec($ch);
print $res;
