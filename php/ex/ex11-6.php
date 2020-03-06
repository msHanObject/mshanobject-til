<?php
$c = curl_init('http://numbersapi.com/03/06');

curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
$fact = curl_exec($c);
?>
Did you know that <?= $fact ?>
