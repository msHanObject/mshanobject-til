<?php
$ch = curl_init('snchmsobj.sncl.kr');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_COOKIEJAR, __DIR__ . './saved_cookies');
curl_setopt($ch, CURLOPT_COOKIEFILE, __DIR__ . './saved_cookies');

print_r (curl_version());
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, true);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 2);

$res = curl_exec($ch);
print $res;
