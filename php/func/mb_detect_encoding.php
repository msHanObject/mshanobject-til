<?php

$str = 'áéóú'; // ISO-8859-1
/* Detect character encoding with current detect_order */
mb_detect_encoding($str, 'UTF-8'); // 'UTF-8'

//If you try to use mb_detect_encoding to detect whether a string is valid UTF-8, use the strict mode, it is pretty worthless otherwise.
mb_detect_encoding($str, 'UTF-8', true); // false

/* "auto" is expanded according to mbstring.language */
mb_detect_encoding($str, "auto");

/* Specify encoding_list character encoding by comma separated list */
mb_detect_encoding($str, "JIS, eucjp-win, sjis-win", true);

/* Use array to specify encoding_list  */
$ary[] = "ASCII";
$ary[] = "JIS";
$ary[] = "EUC-KR";
$euc_kr_str = "\xB9\xD0\xB0\xA1\xB7\xE7\x33\x67"; // 밀가루3g
echo mb_detect_encoding($euc_kr_str, $ary);
?>
