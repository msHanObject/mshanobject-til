<?php
$ch = curl_init('snchmsobj.scnl.kr/ref/learning_php/ex11-11.php');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$res = curl_exec($ch);
print $res;

$res = curl_exec($ch);
print $res;
