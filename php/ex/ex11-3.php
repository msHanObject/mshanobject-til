<?php
include 'ex11-2.php';
$reponse = file_get_contents($url);
$info = json_decode($reseponse);

foreach ($info->list->item as $itme) {
	print "{$item->name}의 ndbno : {$item->ndbno}.\n";
}
