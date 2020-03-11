<?php
include 'PHPCrawl_083/libs/PHPCrawler.class.php';
include_once 'simplehtmldom_1_9_1/simple_html_dom.php';

class MyCrawler extends PHPCrawler
{
	function handleHeaderInfo(PHPCrawlerResponseHeader $header)
	{
		print_r($header);
		
		if ($header->content_type != "text/html") {
			return -1;
		}
	}

	function handleDocumentInfo(PHPCrawlerDocumentInfo $DocInfo)
	{
		$lb = get_linebreak();
		$content = content_convert_to_utf8($DocInfo->content);

		if (!isset($content)) {
			echo 'No content to output';
		} else {
			parsing_tag($content, 'img');
		}
		
		echo "Page requested: <a href=\"$DocInfo->url\">$DocInfo->url</a>"."(".$DocInfo->http_status_code.")".$lb;
		echo 'Referer-page: '.$DocInfo->referer_url.$lb;

		if ($DocInfo->received == true) {
			echo 'Content received: '.$DocInfo->bytes_received.'bytes'.$lb;
		} else {
			echo 'Content not received'.$lb;
		}
		echo 'Page depth: '.$DocInfo->url_link_depth.$lb;
		echo 'Header_send:'.$DocInfo->header_send.$lb;

		echo $lb;
		flush();
	}
}

$crawler = new MyCrawler();

define('URL', 'https://www.tistory.com');
$crawler->setURL(URL);

//$post_data = array('id' => 'test_id', 'pw' => '12123', 'submit' => 'submit');
//$crawler->addPostData('#snchmsobj\.sncl\.kr/test/login_success.php#', $post_data);
$crawler->addContentTypeReceiveRule('#text/html#');
$crawler->addURLFilterRule('#\.(jpg|jpeg|gif|png)$# i');
$crawler->enableCookieHandling(true);
$crawler->setUserAgentString('Mozilla/5.0 (Windows NT 10.0; Win64; rv:65.0) Gecko/20100101 Firefox/65.0');
$crawler->obeyRobotsTxt(false);
$crawler->setFollowMode(1);
$crawler->setFollowRedirects(true);
$crawler->setRequestDelay(0);
$crawler->setWorkingDirectory('dev');
$crawler->setUrlCacheType(PHPCrawlerUrlCacheTypes::URLCACHE_SQLITE);
$crawler->enableResumption();

$tmp = 'tmp/mycrawlerid_for_php.net.tmp'; 
if (!file_exists($tmp)) {
//	$crawler_id = $crawler->getCrawlerId();
//	file_put_contents($tmp, $crawler_id);
} else {
//	$crawler_id = file_get_contents($tmp);
//	$crawler->resume($crawler_id);
}

$crawler->setRequestLimit(10);
$crawler->go();
unlink($tmp);

$lb = get_linebreak();
$report = $crawler->getProcessReport();
echo 'Summary:'.$lb;
echo 'Links followed: '.$reeport->links_followed.$lb;
echo 'Documents received: ' .$report->files_received.$lb;
echo 'Bytes received: '.$report->bytes_received.'bytes'.$lb;
echo 'Process runtimee: '.$report->process_runtime.' sec'.$lb;

function content_convert_to_utf8($content) {
	$enc = mb_detect_encoding($content, array('EUC-KR', 'UTF-8', 'shift_jis', 'CN-GB'), true);
	if ($enc != 'UTF-8') {
		$ct = iconv($enc, 'UTF-8', $content);
	}
	return $ct;
}

function parsing_tag($content, $tag_str) {
	$html = new simple_html_dom();
	$html->load($content);

	foreach ($html->find($tag_str) as $element) {
		if ($tag_str == 'img') {
			show_img($element);
		} elseif ($tag_str == 'title') {
			show_title($element);
		}
	}
}

function show_title($element) {
	echo $element->node[0];
	echo '<br>';
}

function show_img($element) {
	$src_url = 'http://'.URL.$element->src;
	echo "<a href=\"$src_url\" target=\"_blank\"><$element->tag src=\"$src_url\" alt=\"$element->alt\">$element->alt</a><br>";
}

function get_linebreak() {
	if (PHP_SAPI == "cli") {
		$lb = "\n";
	} else {
		$lb = "<br/>";
	}
	return $lb;
}
