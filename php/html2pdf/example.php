<?php

$data = [
  'url' => urlencode('http://shinbom.sncl.kr/ablee_report/'),
  'apiKey' => 'df24f5bcb48039e125996a019320e7939d25f56e065affbb7ac8aafeb6420b9c',
];

$dataString = json_encode($data);

$ch = curl_init('https://api.html2pdf.app/v1/generate');
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
curl_setopt($ch, CURLOPT_POSTFIELDS, $dataString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    'Content-Type: application/json',
]);

$response = curl_exec($ch);
$err = curl_error($ch);

curl_close($ch);

if ($err) {
    echo 'Error #:' . $err;
} else {
    //  header('Content-Type: application/pdf');
    //  header('Content-Disposition: inline; filename="your-file-name.pdf"');
    //  header('Content-Transfer-Encoding: binary');
    //  header('Accept-Ranges: bytes');
    //  echo $response;

    $fp = fopen('output.pdf', "w");
    fwrite($fp, $response);
    fclose($fp);
}
