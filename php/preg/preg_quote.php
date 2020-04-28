<?php
$word = '*very*/s$b';
//$word = '$40 for a g3/400';
$result = preg_quote($word, '/');
echo $result;
