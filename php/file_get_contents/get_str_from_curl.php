<?php
$ch = curl_init('http://numbersapi.com/01/01');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$fact = curl_exec($ch);
?>
Did you know that <?= $fact ?>
