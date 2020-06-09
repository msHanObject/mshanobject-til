<?php

require __DIR__ . '/vendor/autoload.php';

use Knp\Snappy\Pdf;

$ch = curl_init();

//$url = 'http://modernpug.github.io/php-the-right-way/';//success
//$url = 'https://blog.snclab.kr/29';//fail
//$url = 'https://brunch.co.kr/@snclab/45/';//fail
//$url = 'https://github.com/';
//$url = 'https://blog.naver.com/parisprince6/221986754613';
//$url = 'https://blog.naver.com/happycgi/221312864965/';
//$url = 'https://section.blog.naver.com/BlogHome.nhn?directoryNo=0&currentPage=1&groupId=0';
$url = 'https://brunch.co.kr/@hitchwill/1769';
curl_setopt($ch, CURLOPT_URL, $url);

//return the transfer as a string
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

// $output contains the output string
$html_str = curl_exec($ch);

// close curl resource to free up system resources
curl_close($ch);

$snappy = new Pdf('/usr/local/bin/wkhtmltopdf');
$snappy->generateFromHtml($html_str, 'snappy_test.pdf');
