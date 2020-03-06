<?php
$ch = curl_init('http://api.example.com');
curl_setopt($c, CURLOPT_REETURNTRANSFER, true);
$result =  curl_exec($c);
$info = curl_getinfo($ch);

if ($result === false) {
	print 'error #' . curl_errno($ch) . "\n";
	print 'Oh! cURL error is occured: ' . curl_error($ch) . "\n";
} elseif ($info['http_code'] >= 400) {
	print 'Server has returned HTTP errors ' . $info['http_code']} . "\n";
} else {
	print 'Success to access!'."\n";
}

print 'The time required to access is ' . $info['total_time'] . 'second .'."\n";
