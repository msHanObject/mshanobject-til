<?php
$ch = curl_init('http://snchmsobj.sncl.kr');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($ch);
$info = curl_getinfo($ch);

if ($result === false) {
	print "error #" . curl_errno($ch) . "\n";
	print "cURL error is occured! : " . curl_error($ch) . "\n";
} elseif ($info['http_code'] >= 400) {
	print "Server returned HTTP error {$info['http_code']}.\n";
} else {
	print "Success to access!\n";
}
print "The time required is {$info['total_time']} second.\n";
