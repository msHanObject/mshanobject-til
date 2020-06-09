<?php
require_once 'vendor/autoload.php';
// reference the Dompdf namespace
use Dompdf\Dompdf;

$ch = curl_init();
//$url = 'https://www.naver.com/';
//$url = 'http://snclab.kr/';
$url = 'https://github.com/dompdf/dompdf/wiki';
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$html = curl_exec($ch);
// instantiate and use the dompdf class
$dompdf = new Dompdf();
//$dompdf->loadHtml('<h1>DOMPDF 데모</h1><br><p>Hello 월드!</p>');
$dompdf->loadHtml($html);

// (Optional) Setup the paper size and orientation
$dompdf->setPaper('A4', 'portrait');

// Render the HTML as PDF
$dompdf->render();

// Output the generated PDF to Browser
//$dompdf->stream('result.pdf', Array('Attachment'=>0));
//$file_name = $url . '.pdf';
file_put_contents('githubwiki.pdf', $dompdf->output());
