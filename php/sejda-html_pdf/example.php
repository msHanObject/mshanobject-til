<?php
$url = "https://api.sejda.com/v2/html-pdf";
$apiKey = 'api_FFBDCE6733FC40348F593FB17FDFAC8D';
$target_url = 'http://shinbom.sncl.kr/ablee_report/';
$content = json_encode(array('url' => $target_url));

$curl = curl_init($url);
curl_setopt($curl, CURLOPT_HEADER, false);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

curl_setopt($curl, CURLOPT_HTTPHEADER, array(
  "Content-type: application/json",
  "Authorization: Token: " . $apiKey));

curl_setopt($curl, CURLOPT_POST, true);
curl_setopt($curl, CURLOPT_POSTFIELDS, $content);

$response = curl_exec($curl);

$status = curl_getinfo($curl, CURLINFO_HTTP_CODE);

if ($status == 200) {
    $fp = fopen('output.pdf', "w");
    fwrite($fp, $response);
    fclose($fp);
    print("PDF saved to disk");
} else {
    print("Error: failed with status $status, response $response, curl_error " . curl_error($curl) . ", curl_errno " . curl_errno($curl));
}
