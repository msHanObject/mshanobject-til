<?php
$url =  'http://snchmsobj.sncl.kr';
$form_data = array('name' => 'black pepper', 'smell' => 'good');

$options = array('method' => 'POST', 'header' => 'Content-Type: application/x-www-form-urlencoded', 'content' => http_build_query($form_data));
$context = stream_context_create(array('http' => $options));
print file_get_contents($url, false, $context);
