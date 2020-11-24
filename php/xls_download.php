<?php
$data = 'aaa,bbb,ccc,ddd,eee
123,456,789,20394
한글,출력,엑셀,문제';
header('Content-Type: application/vnd.ms-excel; charset=UTF-8');
header('Expires: 0');
header('Content-Disposition: attachment; filename=test.xls');
header('Content-Transfer-Encoding: binary');
echo $data;
