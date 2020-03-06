<?php
$ch = curl_init('snchmsobj.sncl.kr');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($ch);
echo $result;
$result = curl_exec($ch);
print $result;
