<?php
$formats = array('application/json', 'text/html', 'text/plain');
$default_format = 'application/json';
//$_SERVER['HTTP_ACCEPT'] = 'text/html';
if (isset($_SERVER['HTTP_ACCEPT'])) {
	if (in_array($_SERVER['HTTP_ACCEPT'], $formats)) {
		$format = $_SERVER['HTTP_ACCEPT'];
	} else {
		http_response_code(406);
		exit();
	}
} else {
	$format = $default_format;
}

$response_data = array('now' => time());
header('Content-Type: ' . $format);
if ($format == 'application/json') {
	print json_encode($response_data);
	echo 'json';
} elseif ($format == 'text/html') {?>
<!doctype html>
	<html>
		<head><title>time</time></head>
		<body><time><?= data('c', $response_data['now']) ?></time></body>
	</html>
<?php
	echo 'html';
} elseif ($format == 'text/plain') {
	print $response_data['now'];
	echo  'text/plain';
} else {
	echo 'Somebody help me!';
}
