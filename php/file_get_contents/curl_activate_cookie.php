<?php
$ch  = curl_init('snchmsobj.sncl.kr/ref/learning_php/ex11-12.php');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_COOKIEJAR, true);

$res = curl_exec($ch);
print $res;

$res = curl_exec($ch);
print $res;
