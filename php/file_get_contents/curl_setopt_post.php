<?php
$url = 'http://snchmsobj.sncl.kr/test/login_success.php';
$form_data = array('id' => 'test_id',
				   'pw' => 'test_pw');

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $form_data);

print curl_exec($ch);
