<?php
require 'vendor/autoload.php';

use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;

$spreadsheet = new Spreadsheet();
$sheet = $spreadsheet->getActiveSheet();

function get_row_col($cell)
{
    $cell_splits = str_split($cell);
    for ($i = 0; count($cell_splits); $i++) {
        if (is_numeric($cell_splits[$i])) {
            $col_char = substr($cell, 0, $i);
            $row_num = substr($cell, $i);
            return array($col_char, $row_num);
        }
    }
    return array('A', 1);
}

$data = array(
    array(
            "seq" => 1,
            "code" => 'https://www.example.com/st/?code=072b030ba126',
            "stamp_name" => 'Stamp A',
            "geopark_name" => '지질Park A',
            "lat" => 35.9960190,
            "lng" => 127.7953070,
            "visits" => 10
        ),
    array(
            "seq" => 2,
            "code" => 'https://www.example.com/st/?code=093f65e080a29',
            "stamp_name" => 'Stamp B',
            "geopark_name" => '지질Park B',
            "lat" => 35.8431800,
            "lng" => 127.6825570,
            "visits" => 20
        ),
    array(
            "seq" => 3,
            "code" => 'https://www.example.com/st/?code=66f041e16a69',
            "stamp_name" => 'Stamp C',
            "geopark_name" => '지질Park C',
            "lat" => 35.9753270,
            "lng" => 127.4055170,
            "visits" => 30,
        )
);
$start_cell = 'A1';
list($col_char, $row_num) = get_row_col($start_cell);
// Set row header
$row = array_keys($data[0]);
foreach ($row as $cell) {
    $sheet->setCellValue($col_char++ . $row_num, $cell);
}
// Set row value
$row_num = 2;
foreach ($data as $row) {
    $row= array_values($row);
    $col_char = 'A';
    foreach ($row as $cell) {
        $sheet->setCellValue($col_char++ . $row_num, $cell);
    }
    $row_num++;
}

$writer = new Xlsx($spreadsheet);
$writer->save('파일이름.xlsx');
