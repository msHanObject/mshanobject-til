<?php
require_once 'vendor/autoload.php';
// reference the Dompdf namespace
use Dompdf\Dompdf;

$ch = curl_init();
$url = 'https://www.naver.com/';
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$html = curl_exec($ch);

// instantiate and use the dompdf class
$dompdf = new Dompdf();

// Set dompdf options
$options = $dompdf->getOptions();
$options->setIsRemoteEnabled(true);
//$options->setIsHtml5ParserEnabled(true);
$dompdf->setOptions($options);

$dompdf->loadHtml($html);

// (Optional) Setup the paper size and orientation
$dompdf->setPaper('A4', 'portrait');

// Render the HTML as PDF
$dompdf->render();

// Output the generated PDF to Browser
//$dompdf->stream('result.pdf', Array('Attachment'=>0));

file_put_contents('output.pdf', $dompdf->output());
