<?php require __DIR__.'/vendor/autoload.php';
use Goutte\Client;
use Symfony\Component\HttpClient\HttpClient;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();
define('TISTORY', 'https://www.tistory.com');
define('REDDIT', 'https://www.reddit.com/');
define('GITHUB', 'https://www.github.com');

// Go to the defined 'URL'
$crawler = $client->request('GET', TISTORY);

$css_selector = 'a';
$node_position = 49;
$things_to_scrape = array('_name', 'href', '_text', 'src', 'class');


// Case 1: get URI of first target node
/*
$link_crawler = $crawler->filter($css_selector);
$link = $link_crawler->link();
$uri = $link->getUri();
print_r($uri);
*/

// Case 2: extract things from node of position
/*
$output = $crawler
    ->filter($css_selector) // CSS selector
    ->eq($node_position) // Access node by its position on the list
	->extract($things_to_scrape);  // DOM attribute to extract
print_r($output);
*/

// Case 3: get string array from nodes
/*
$node_key_val = $crawler->filter($css_selector)->each(function (Crawler $node, $i) {
	$index = $i+1;
	$str[] = $index.' '.$node->text().PHP_EOL;
	return $str;
});
print_r($node_key_val);
*/

// Case 4: get all links of URL
function get_uris($url)
{
	$client = new Client();
	$crawler = $client->request('GET', $url);
	$links[] = $crawler->filter('a')->each(function ($node) {
		return $node->link()->getUri();
	});
	return $links;
}
//print_r(get_uris(TISTORY)[0]);

// Case 5: scrape all links of URI
/*
$first_uris = get_uris(TISTORY);
foreach ($first_uris as $k => $v) {
	foreach($v as $uri) {
		$next_crawler = $client->request('GET', $uri);
		$links = array();
		$links[] = $next_crawler->filter('a')->each(function ($node) {
			return $node->link()->getUri();
		});
		print_r($links);
	}
}
*/

// Case 6: scape links of URI unquestioningly
$link_arr = get_uris(TISTORY)[0];
foreach ($link_arr as $v) {
	$crawler = $client->request('GET', $v);
	$new_links = array();
	$new_links[] = $crawler->filter('a')->each(function ($node) {
		return $node->link()->getUri();
	});
	foreach($new_links[0] as $a) {
		if (!in_array($a, $link_arr)) {
			$link_arr[] = $a;
		}
	}
}
echo 'unique count: '.count(array_unique($link_arr))."\n";
echo 'normal count: '.count($link_arr)."\n";
//file_put_contents("tistory_links.json", json_encode($link_arr));

// Case 7: scape links under the domain

