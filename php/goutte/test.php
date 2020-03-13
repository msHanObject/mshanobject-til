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

function get_linebreak() {
	if (PHP_SAPI == "cli") {
		$lb = "\n";
	} else {
		$lb = "<br/>";
	}
	return $lb;
}

function url_exist($url){//se passar a URL existe
    $c=curl_init();
    curl_setopt($c,CURLOPT_URL,$url);
    curl_setopt($c,CURLOPT_HEADER,1);//get the header
    curl_setopt($c,CURLOPT_NOBODY,1);//and *only* get the header
    curl_setopt($c,CURLOPT_RETURNTRANSFER,1);//get the response as a string from curl_exec(), rather than echoing it
    curl_setopt($c,CURLOPT_FRESH_CONNECT,1);//don't use a cached version of the url
    if(!curl_exec($c)){
        //echo $url.' inexists';
        return false;
    }else{
        //echo $url.' exists';
        return true;
    }
    //$httpcode=curl_getinfo($c,CURLINFO_HTTP_CODE);
    //return ($httpcode<400);
}

function get_uris($url)
{
	$client = new Client();
	if (!url_exist($url)) {
		return;
	}
	echo 'Base URL: '.$url.PHP_EOL;
	$crawler = $client->request('GET', $url);
	$links = $crawler->filter('a')->each(function ($node) {
		$link = $node->link()->getUri();
		if (empty($link)) {
			return;
		}
		if (url_exist($link)) {
//			echo 'Child URL: '.$link.PHP_EOL;
			return $link;
		}
	});
	if (empty($links)) {
		return;
	}
	$unique_links = deduplicate($links);

	return $unique_links;
}

function deduplicate($arr)
{
	$unique_arr = array();
	foreach($arr as $val) {
		if (!in_array($val, $unique_arr)) {
			$unique_arr[] = $val;
		}
	}
	return $unique_arr;
}

function save_file($file_name, $str)
{
	if (!isset($str) || !is_string($str)) {
		return;
	}
	$fh = get_file_handler($file_name);
	fwrite($fh, $str."\n");
	fclose($fh);
}

function get_file_handler($file_name = 'tmp')
{
	$file_extention = '.txt';
	$file_name .= $file_extention;
	$file_handler = fopen($file_name, 'ab+');
	return $file_handler;
}

function get_urn($url)
{
	$start_position = strpos($url, '://')+3;
	return $urn = substr($url, $start_position);
}

// Case 1: get URI of first target node
/*
$css_selector = 'a';
$node_position = 49;
$things_to_scrape = array('_name', 'href', '_text', 'src', 'class');
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


//print_r(get_uris(TISTORY));

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
// Case 6-1: save data at one go
/*
$link_arr = get_uris('http://v2.parkingplay.kr');

foreach ($link_arr as $v) {
	echo $v."\n";
	$crawler = $client->request('GET', $v);
	$new_links = $crawler->filter('a')->each(function ($node) {
		$uri = $node->link()->getUri();
		echo $uri."\n";
		return $uri;
	});
	foreach($new_links as $a) {
		if (!in_array($a, $link_arr)) {
			$link_arr[] = $a;
			echo $a."\n";
		}
	}
}

echo 'unique count: '.count(array_unique($link_arr))."\n";
echo 'normal count: '.count($link_arr)."\n";
file_put_contents("depth2_links.json", json_encode($link_arr));
*/



// Case 7: set proxy
//$client = new Client(HttpClient::create(['proxy' => 'http://xx.xx.xx.xx:80']));

// Case 8: save links of naver
define('NAVER_URL', 'https://naver.com');
define('NAVER1', 'naver-1');
$depth_1_links = get_uris(NAVER_URL);
foreach($depth_1_links as $link)
{
	save_file(NAVER1, $link);
//	$next_link = get_uris($link)[0];
//	if (!empty($next_link)) {
//		$depth_2_links[] = $next_link;
//	}
}
exit();
define('NAVER2', 'naver-2');
foreach($depth_2_links as $link)
{
	save_file(NAVER2, $link);
}
