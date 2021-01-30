<?php
require __DIR__ . '/vendor/autoload.php';

use Knp\Snappy\Pdf;

// Set header
$header = <<<HTML
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<style type="text/css">p { color: #FF0000; }</style>
</head>

<body>
<p>Header 한글 테스트</p>
</body>

</html>
HTML;

// Set footer
$footer = <<<HTML
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<style type="text/css">p { color: #0000FF; }</style>
</head>

<body>
<p style="padding: 10px;">Footer 한글 test</p>
</body>

</html>
HTML;

$url = '테스트 URL';
$filename = 'output' . date('YmdHis') . '.pdf';

// Initialization
$pdf = new Pdf('/usr/local/bin/wkhtmltopdf');

// Set options
// Without html extension you might face following error: Exit with code 1, due to unknown error.
$footer_path = tempnam('/tmp', 'footer') . '.html';
file_put_contents($footer_path, $footer);
$options = ['header-html' => $header, 'footer-html' => $footer_path];
$pdf->setOptions($options);

// Download the pdf from the browser
/*
 * Half Success test
*/
header('Content-Type: application/pdf');
header('Content-Disposition: attachment; filename="' . $filename .'"');
echo $pdf->getOutput($url);

// Get html string via curl
/*
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);    //return the transfer as a string
$html_str = curl_exec($ch);
curl_close($ch);    // close curl resource to free up system resources
*/

// Display the pdf in the browser
/*
 * Failed test
header('Content-Type: application/pdf');
echo $pdf->getOutput($html_str);
*/

// Generate local pdf file
/*
 * Failed test
$pdf->generateFromHtml($html_str, $filename);
*/

// Merge multiple urls into one pdf
/*
 * It works but not quite well
header('Content-Type: application/pdf');
header('Content-Disposition: attachment; filename="file.pdf"');
echo $pdf->getOutput(array('http://www.github.com','http://www.knplabs.com','http://www.php.net'));
*/
