<?php
require 'vendor/autoload.php';

use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;

$spreadsheet = new Spreadsheet();
$sheet = $spreadsheet->getActiveSheet();

$data = array(
    array(
            "seq" => 60,
            "code" => 'https://www.example.com/st/?code=072b030ba126',
            "stamp_name" => 'Stamp A',
            "geopark_name" => '지질Park A',
            "lat" => 35.9960190,
            "lng" => 127.7953070,
            "visits" => 1
        ),
    array(
            "seq" => 59,
            "code" => 'https://www.example.com/st/?code=093f65e080a29',
            "stamp_name" => 'Stamp B',
            "geopark_name" => '지질Park B',
            "lat" => 35.8431800,
            "lng" => 127.6825570,
            "visits" => 2
        ),
    array(
            "seq" => 58,
            "code" => 'https://www.example.com/st/?code=66f041e16a69',
            "stamp_name" => 'Stamp C',
            "geopark_name" => '지질Park C',
            "lat" => 35.9753270,
            "lng" => 127.4055170,
            "visits" => 3,
        )
);
$i = 1;
foreach ($data as $row) {
    $row = array_values($row);
    $c = 'A';
    foreach ($row as $cell) {
        $sheet->setCellValue($c++.$i, $cell);
    }
    $i++;
}

$path = 'resource/';
$writer = new Xlsx($spreadsheet);
$writer->save($path . 'hello 월드.xlsx');
