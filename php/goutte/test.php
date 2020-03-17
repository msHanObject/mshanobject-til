<?php require __DIR__.'/vendor/autoload.php';
use Goutte\Client;
use Symfony\Component\HttpClient\HttpClient;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();
define('FILE_PATH', './link.json');

// crawlering loop test
function travers()
{
	$depth = 1;
	$links = get_uris('https://www.knicc.re.kr');
	foreach($links as $link)
	{
		save_file(FILE_PATH, $link);
		$next_link = get_uris($link);
		if (!empty($next_link)) {
			$depth++;
			$unique_links = array_keys(array_flip($links)+array_flip($next_link));
			file_put_contents(FILE_PATH, json_encode($unique_links));
		}
	}
}

// login test
function() get_login_user_name()
{
	$crawler = $client->request('GET', 'https://www.knicc.re.kr/login_form.php');
	$form = $crawler->selectButton('로그인')->form();
	$crawler = $client->submit($form, array('id' => 'tester1', 'password' => '111111'));
	$crawler = $client->request('GET', 'https://www.knicc.re.kr');
	$crawler->filter('.welcome')->each(function ($node) {
		print $node->text()."\n";
	});
}

function get_linebreak() {
	if (PHP_SAPI == "cli") {
		$lb = "\n";
	} else {
		$lb = "<br/>";
	}
	return $lb;
}

function show_dead_links()
{
	foreach($dead_links as $link) {
		echo 'Dead link: '.$link;
	}
}

function url_exist($url) {
	$ch = curl_init();//$ch is curl_handler
	curl_setopt($ch, CURLOPT_URL,$url);
	curl_setopt($ch, CURLOPT_HEADER,1);//get the header
	curl_setopt($ch, CURLOPT_NOBODY,1);//and *only* get the header
	curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);//get the response as a string from curl_exec(), rather than echoing it
	curl_setopt($ch, CURLOPT_FRESH_CONNECT,1);//don't use a cached version of the url
	curl_setopt($ch, CURLOPT_HTTPHEADER, array('Connection: close'));
	curl_exec($ch);
	$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);//for checking http status
	curl_close($ch);

	return $http_code < 400;
}

function get_uris($url)
{
	$client = new Client();
	if (!url_exist($url)) {
		$dead_links[] = $url;
		echo 'Dead link: '.$url;
		return;
	}
	$crawler = $client->request('GET', $url);
	$links = $crawler->filter('a')->each(function ($node) {
		$link = $node->link()->getUri();
		if (empty($link)) {
			return;
		}
		return $link;
	});
	if (empty($links)) {
		return;
	}

	return exclude_base_url($links);
}

function exclude_base_url($array)
{
	$new_array = array();
	foreach($array as $val) {
		if (preg_match('/^(http)?s?(:\/\/)?(www\.)?knicc\.re\.kr\/?#?$/', $val) == 1) {
			continue;
		}
		if (!in_array($val, $new_array)) {
			$new_array[] = $val;
			echo $val;
		}
	}

	return $new_array;
}

function save_file($file_name, $str)
{
	if (!isset($str) || !is_string($str)) {
		return;
	}
	$fh = get_file_handler($file_name);
	fwrite($fh, $str);
	fclose($fh);
}

function get_file_handler($file_name = './tmp.txt')
{
	return fopen($file_name, 'ab+');
}

function get_urn($url)
{
	$start_position = strpos($url, '://')+3;
	return $urn = substr($url, $start_position);
}

// Case 1: get URI of first target node
/*
$css_selector = 'a';
$things_to_scrape = array('_name', 'href', '_text', 'src', 'class');
$link_crawler = $crawler->filter($css_selector);
$link = $link_crawler->link();
$uri = $link->getUri();
print_r($uri);
*/

// Case 2: extract things from node of position
/*
$node_position = 49;
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
/*
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
*/
