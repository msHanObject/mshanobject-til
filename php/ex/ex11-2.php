<?php
define('NDB_API_KEY', 'VrnJwAjxdDIglacMZLKjTTmtp61vhPsci6tINcgo');
$params = array('api_key' => NDB_API_KEY, 'q' => 'black pepper', 'format' => 'json');
$url = 'http://api.nal.usda.gov/ndb/search?' . http_build_query($params);
