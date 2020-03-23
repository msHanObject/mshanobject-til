<?php
//  echo var_export(unserialize(file_get_contents('http://www.geoplugin.net/php.gp?ip='.$_SERVER['REMOTE_ADDR'])));
$user_ip = getenv['REMOTE_ADDR'];
$geo = unserialize(file_get_contents("https://www.geoplugin.net/php.gp?ip=$user_ip"));
$country = $geo["geoplugin_countryName"];
$region = $geo["geoplugin_regionName"];
$city = $geo["geoplugin_city"];

echo 'Country: ', $country, '<br>';
echo 'Region: ', $region, '<br>';
echo 'City: ', $city, '<br>';
