<?php
require __DIR__.'/vendor/autoload.php';
use Goutte\Client;
use Symfony\Component\HttpClient\HttpClient;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();

// Go to the symfony.com website
$crawler = $client->request('GET', 'https://www.tistory.com');

// Click on the "Security Advisories" link
$link = $crawler->selectLink('스토리')->link();
$crawler = $client->click($link);

// Get the latest post in this category and display the titles
print_r($crawler->filter('ul')->each(function ($node) {
    print $node->text()."\n";
}));

// Define id and password of Login Form
//define('ID', 'mshanobject');
//define('PW', 'h6973125048');

//$node = 'div.mt-5 > div > ul > li > div > a > span';
//submit_form($client, $node);

// Click on links:
function click_link($crawler, $client)
{
	// Click on the "Security Advisories" link
	$link = $crawler->selectLink('Security Advisories')->link();
	$crawler = $client->click($link);
}

// Extract data:
function extract_data($crawler, $node)
{
	// Get the latest post in this category and display the titles
	$crawler->filter('h2 > a')->each(function ($node) {
	    print $node->text()."\n";
	});
}

// Submit forms:
function submit_form($client, $node)
{
	$crawler = $client->request('GET', 'https://github.com/');
	$crawler = $client->click($crawler->selectLink('Sign in')->link());
	$form = $crawler->selectButton('Sign in')->form();
	$crawler = $client->submit($form, array('login' => ID, 'password' => PW));
	$crawler->filter('.flash-error')->each(function ($node) {
	    print $node->text()."\n";
	});
}
