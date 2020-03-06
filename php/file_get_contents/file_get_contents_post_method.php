<?php
$url = 'http://snchmsobj.sncl.kr/test/login_success.php';
$form_data = array('id' => 'tester',
				   'pw' => '1234'
				  );
$options_1 = array('method' => 'POST',
				   'header' => 'Content-Type: application/x-www-form-urlencoded',
				   'content' => http_build_query($form_data)
				  );
$options_2 = array('method' => 'POST',
				   'header' => 'Content-Type: application/json',
				   'content' => json_encode($form_data)
				  );
$context = stream_context_create(array('http' => $options_1));
print file_get_contents($url, false, $context);
