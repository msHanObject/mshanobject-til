<?php
$response_data = array('현재 시각' => time());
header('Content-Type: application/json');
print json_encode($response_data);
