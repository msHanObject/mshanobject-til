<?php

$data = [
  'url' => urlencode('타겟 URL'),
  'apiKey' => '발급받은 API 키',
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
