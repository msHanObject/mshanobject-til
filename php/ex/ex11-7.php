<?php
include 'ex11-2.php';
$c = curl_init($url);
curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
curl_setopt($c, CURLOPT_HTTPHEADRER, array('Content-Type: application/json'));
print curl_exec($c);
