<?php
define('NDB_API_KEY', 'VrnJwAjxdDIg1acMZLKjTTmtp61vhPsci6tINcgo');
$params = array('api_key' => NDB_API_KEY,
				'q' => 'black pepper',
				'format' => 'json');
$url = 'http://api.nal.usda.gov/ndb/search?' . http_build_query($params);
//echo "<a href=\"$url\">$url</a>";
