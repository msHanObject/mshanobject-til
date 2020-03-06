<?php
$url = 'https://jsonplaceholder.typicode.com/users';
$form_data = array('id' => 11,
				   'name' => 'json_tester');
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($form_data));

print curl_exec($ch);
