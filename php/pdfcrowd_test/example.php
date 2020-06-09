<?php
require 'vendor/autoload.php';

try {
    // create the API client instance
    $client = new \Pdfcrowd\HtmlToPdfClient("mshanobject", "a114cea4130e8e088c3a88116cd6639e");

    // run the conversion and write the result to a file
    $client->convertUrlToFile("https://mainia.tistory.com/1983", "maina_tistory_com_1983.pdf");
} catch (\Pdfcrowd\Error $why) {
    // report the error
    error_log("Pdfcrowd Error: {$why}\n");

    // rethrow or handle the exception
    throw $why;
}
