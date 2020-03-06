<?php
$url = 'http://snchmsobj.sncl.kr';
$form_data = array('name' => 'black pepper', 'smell' => 'good');
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFERE, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFILEDS, $form_data);

print curl_exec($ch);
