<?php

use Mpdf\Mpdf;

require_once __DIR__ . '/vendor/autoload.php';

// create curl resource
$ch = curl_init();

// set url
//$url = 'https://www.naver.com/';
$url = 'http://snclab.kr';
//$url = 'http://modernpug.github.io/php-the-right-way/';
curl_setopt($ch, CURLOPT_URL, $url);

//return the transfer as a string
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

// $output contains the output string
$html_str = curl_exec($ch);

// close curl resource to free up system resources
curl_close($ch);


$mpdf = new Mpdf(['mode' => '+aCJK']);
$mpdf-> WriteHTML($html_str);
$mpdf->Output('filename.pdf', \Mpdf\Output\Destination::FILE);
