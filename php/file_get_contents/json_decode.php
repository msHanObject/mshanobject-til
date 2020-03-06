<?php
include 'http_build_query.php';
$response = file_get_contents($url);
$info = json_decode($response);
foreach ($info->list->item  as $item) {
	print "{$item->name}의 ndbno : {$item->ndbno}.\n";
}
