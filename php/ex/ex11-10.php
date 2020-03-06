<?php
$url = 'snchmsobj.sncl.kr';
$form_data = array('name' => 'black pepper', 'smell' => 'good');
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTPPHEADER, array('Content-Type: application/json'));
curl_setopt($ch, CURLOPT_POSTFILEDS, json_encode($form_data));

print curl_exec($ch);
