<?php
define('NDB_API_KEY', 'VrnJwAjxdDIg1acMZLKjTTmtp61vhPsci6tINcgo');
$params = array('api_key' => NDB_API_KEY,
				'q' => 'black pepper');
$url = "http://api.nal.usda.gov/ndb/search?" . http_build_query($params);
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
print curl_exec($ch);
