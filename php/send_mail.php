<?php
// The message
$message = "Line 1\r\nLine 2\r\nLine 3Line 1 asdfLine 2Line 3Line 1Line 2nLine 3Line 1nine 2asdfkjasdfkjasdfkjasdkfjaskdfkjine 3";

// In case any of our lines are larger than 70 characters, we should use wordwrap()
$message = wordwrap($message, 70, "\r\n");

$headers = array(
    'From' => 'mshanobject@gmail.com',
    'Reply-To' => 'mshanobject@gmail.com',
    'X-Mailer' => 'PHP/' . phpversion()
);

// Send
mail('mshan@snclab.kr', 'My test', $message, $headers);
