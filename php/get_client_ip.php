<?php
echo get_client_ip_env();

// Function to get the client ip address
function get_client_ip_env()
{
    $ipaddress = '';
    if (getenv('HTTP_CLIENT_IP')) {
        $ipaddress = getenv('HTTP_CLIENT_IP');
    } elseif (getenv('HTTP_X_FORWARDED_FOR')) {
        $ipaddress = getenv('HTTP_X_FORWARDED_FOR');
    } elseif (getenv('HTTP_X_FORWARDED')) {
        $ipaddress = getenv('HTTP_X_FORWARDED');
    } elseif (getenv('HTTP_FORWARDED_FOR')) {
        $ipaddress = getenv('HTTP_FORWARDED_FOR');
    } elseif (getenv('HTTP_FORWARDED')) {
        $ipaddress = getenv('HTTP_FORWARDED');
    } elseif (getenv('REMOTE_ADDR')) {
        $ipaddress = getenv('REMOTE_ADDR');
    } else {
        $ipaddress = 'UNKNOWN';
    }
 
    return $ipaddress;
}

// Function to get the client ip address
function get_client_ip_server()
{
    $ipaddress = '';
    if ($_SERVER['HTTP_CLIENT_IP']) {
        $ipaddress = $_SERVER['HTTP_CLIENT_IP'];
    } elseif ($_SERVER['HTTP_X_FORWARDED_FOR']) {
        $ipaddress = $_SERVER['HTTP_X_FORWARDED_FOR'];
    } elseif ($_SERVER['HTTP_X_FORWARDED']) {
        $ipaddress = $_SERVER['HTTP_X_FORWARDED'];
    } elseif ($_SERVER['HTTP_FORWARDED_FOR']) {
        $ipaddress = $_SERVER['HTTP_FORWARDED_FOR'];
    } elseif ($_SERVER['HTTP_FORWARDED']) {
        $ipaddress = $_SERVER['HTTP_FORWARDED'];
    } elseif ($_SERVER['REMOTE_ADDR']) {
        $ipaddress = $_SERVER['REMOTE_ADDR'];
    } else {
        $ipaddress = 'UNKNOWN';
    }

    return $ipaddress;
}
