<?php
$data = "가나다라마바사";
$crypt_pass = "abcdefghij123456"; // 16자리
$crypt_iv = "abcdefghij123456"; // 16자리
// 암호화
$endata = @openssl_encrypt($data, "aes-128-cbc", $crypt_pass, true, $crypt_iv);
$endata = base64_encode($endata);
echo "ENCODE DATA : " . $endata . "\n";
// 복호화
$data = base64_decode($endata);
$endata = @openssl_decrypt($data, "aes-128-cbc", $crypt_pass, true, $crypt_iv);
echo "DECODE DATA : " . $endata . "\n";
