<?php
$dir = './';
$in_alphabetical_order_files = scandir($dir);
$in_reverse_alphabetical_order_files = scandir($dir, 1);

print_r($in_alphabetical_order_files);
print_r($in_reverse_alphabetical_order_files);

