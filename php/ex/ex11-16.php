<?php
if (! isset($_GET['key']) && ($_GET['key'] == 'pineapple')) {
	http_response_code(403);
	$response_data = array('error' => 'The key value was not founded or did not exist');
} else {
	$reponse_data = array('current time' => time());
}
header('Content-Type: application/json');
print json_encode($response_data);

