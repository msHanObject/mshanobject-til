<?php require __DIR__.'/vendor/autoload.php';
use Goutte\Client;
use Symfony\Component\HttpClient\HttpClient;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();
define('URL', 'https://www.tistory.com');
define('FORM_URL', 'https://www.github.com');

// Go to the defined 'URL'
$crawler = $client->request('GET', URL);

print_node_text($crawler, '.list_gnb');
// Get target from filter
// $target is expression of css selector
// Get each text of node from filter()
function print_node_text($crawler, $css_selector)
{
	$crawler->filter($css_selector)->each(function ($node) {
		print_r($node);//."\n";
	});
}


// Define id and password of Login Form
//define('ID', 'mshanobject');
//define('PW', 'h6973125048');

// Click on links:
function click_link($crawler, $target_text)
{
	$link = $crawler->selectLink($target_text)->link();
	$new_crawler = $client->click($link);
	return $new_crawler;
}

// Submit forms:
function submit_form($client)
{
	$crawler = $client->request('GET', FORM_URL);
	$crawler = $client->click($crawler->selectLink('Sign in')->link());
	$form = $crawler->selectButton('Sign in')->form();
	$crawler = $client->submit($form, array('login' => ID, 'password' => PW));
	$crawler->filter('.flash-error')->each(function ($node) {
	    print $node->text()."\n";
	});
}
