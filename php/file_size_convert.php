<?php
function convert_bytes_unit($bytes, $decimal_unit = 2)
{
    $file_size_units = array('B', 'KB', 'MB', 'GB', 'TB');

    $bytes = max($bytes, 0);
    $pow = floor(($bytes ? log($bytes) : 0) / log(1024));
    $pow = min($pow, count($file_size_units) - 1);

    // Uncomment one of the following alternatives
    $bytes /= pow(1024, $pow);    // Use for OS user
    //$bytes /= (1 << (10 * $pow));    // Use for HDD maker

    return round($bytes, $decimal_unit) . ' ' . $file_size_units[$pow];
}
echo convert_bytes_unit(10485760);
