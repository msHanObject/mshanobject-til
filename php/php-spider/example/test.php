<?php

use Symfony\Component\EventDispatcher\Event;
use VDB\Spider\Discoverer\XPathExpressionDiscoverer;
use VDB\Spider\Discoverer\CssSelectorDiscoverer;
use VDB\Spider\Event\SpiderEvents;
use VDB\Spider\EventListener\PolitenessPolicyListener;
use VDB\Spider\Filter\Prefetch\AllowedHostsFilter;
use VDB\Spider\Filter\Prefetch\AllowedSchemeFilter;
use VDB\Spider\Filter\Prefetch\UriWithHashFragmentFilter;
use VDB\Spider\Filter\Prefetch\UriWithQueryStringFilter;
use VDB\Spider\QueueManager\InMemoryQueueManager;
use VDB\Spider\Spider;
use VDB\Spider\StatsHandler;
use Example\LogHandler;
//use Example\Auth;
use GuzzleHttp\Middleware;


require_once('example_complex_bootstrap.php');

// The URI we want to start crawling with
//$seed = 'https://tistory.com';//pass
$seed = 'https://naver.com';//pass
//$seed = 'https://knicc.re.kr';//pass
//$seed = 'http://blog.snclab.kr/';//pass
//$seed = 'http://dmoztools.net/Computers/Internet/';//pass
//$seed = 'https://egovframe.go.kr';//error

// We want to allow all subdomains of dmoz.org
$allowSubDomains = true;

// Create spider
$spider = new Spider($seed);
$spider->getDownloader()->setDownloadLimit(5);

$statsHandler = new StatsHandler();
$LogHandler = new LogHandler();

$queueManager = new InMemoryQueueManager();

$queueManager->getDispatcher()->addSubscriber($statsHandler);
$queueManager->getDispatcher()->addSubscriber($LogHandler);

// Set some sane defaults for this example. We only visit the first level of www.dmoz.org. We stop at 10 queued resources
//$spider->getDiscovererSet()->maxDepth = 2;
//$spider->getQueueManager()->maxQueueSize = 10;

// This time, we set the traversal algorithm to breadth-first. The default is depth-first
$queueManager->setTraversalAlgorithm(InMemoryQueueManager::ALGORITHM_BREADTH_FIRST);
//$queueManager->setTraversalAlgorithm(InMemoryQueueManager::ALGORITHM_DEPTH_FIRST);

$spider->setQueueManager($queueManager);

// We add an URI discoverer. Without it, the spider wouldn't get past the seed resource.
//$spider->getDiscovererSet()->set(new CssSelectorDiscoverer("a")); //fail
$spider->getDiscovererSet()->set(new XPathExpressionDiscoverer("//a")); // if there is no empty string node, this spider will be infinitely crawling

// Let's tell the spider to save all found resources on the filesystem
// This download success is depend on $seed. When I use 'knicc.re.kr' to $seed, This code bring me errors.
$spider->getDownloader()->setPersistenceHandler(
    new \VDB\Spider\PersistenceHandler\FileSerializedResourcePersistenceHandler(__DIR__ . '/results')
);


// Add some prefetch filters. These are executed before a resource is requested.
// The more you have of these, the less HTTP requests and work for the processors
$spider->getDiscovererSet()->addFilter(new AllowedSchemeFilter(array('http')));
$spider->getDiscovererSet()->addFilter(new AllowedHostsFilter(array($seed), $allowSubDomains));
$spider->getDiscovererSet()->addFilter(new UriWithHashFragmentFilter());
$spider->getDiscovererSet()->addFilter(new UriWithQueryStringFilter());

// We add an eventlistener to the crawler that implements a politeness policy. We wait 450ms between every request to the same domain
$politenessPolicyEventListener = new PolitenessPolicyListener(100);
$spider->getDownloader()->getDispatcher()->addListener(SpiderEvents::SPIDER_CRAWL_PRE_REQUEST,array($politenessPolicyEventListener, 'onCrawlPreRequest'));

// add Authentication
//$authListener = new Auth();
//$spider->getDownloader()->getDispatcher()->addListener(SpiderEvents::SPIDER_CRAWL_PRE_REQUEST,array($authListener, 'setAuth'));

$spider->getDispatcher()->addSubscriber($statsHandler);
$spider->getDispatcher()->addSubscriber($LogHandler);

// Let's add something to enable us to stop the script
$spider->getDispatcher()->addListener(
    SpiderEvents::SPIDER_CRAWL_USER_STOPPED,
    function (Event $event) {
        echo "\nCrawl aborted by user.\n";
        exit();
    }
);

// Let's add a CLI progress meter for fun
echo "\nCrawling";
$spider->getDownloader()->getDispatcher()->addListener(
    SpiderEvents::SPIDER_CRAWL_POST_REQUEST,
    function (Event $event) {
        echo '.';
    }
);

// Set up some caching, logging and profiling on the HTTP client of the spider
$guzzleClient = $spider->getDownloader()->getRequestHandler()->getClient();
$tapMiddleware = Middleware::tap([$timerMiddleware, 'onRequest'], [$timerMiddleware, 'onResponse']);
$guzzleClient->getConfig('handler')->push($tapMiddleware, 'timer');

// Execute the crawl
$result = $spider->crawl();

// Report
echo "\n\nSPIDER ID: " . $statsHandler->getSpiderId();
echo "\n  ENQUEUED:  " . count($statsHandler->getQueued());
echo "\n  SKIPPED:   " . count($statsHandler->getFiltered());
echo "\n  FAILED:    " . count($statsHandler->getFailed());
echo "\n  PERSISTED:    " . count($statsHandler->getPersisted());

// With the information from some of plugins and listeners, we can determine some metrics
$peakMem = round(memory_get_peak_usage(true) / 1024 / 1024, 2);
$totalTime = round(microtime(true) - $start, 2);
$totalDelay = round($politenessPolicyEventListener->totalDelay / 1000 / 1000, 2);
echo "\n\nMETRICS:";
echo "\n  PEAK MEM USAGE:       " . $peakMem . 'MB';
echo "\n  TOTAL TIME:           " . $totalTime . 's';
echo "\n  REQUEST TIME:         " . $timerMiddleware->getTotal() . 's';
echo "\n  POLITENESS WAIT TIME: " . $totalDelay . 's';
echo "\n  PROCESSING TIME:      " . ($totalTime - $timerMiddleware->getTotal() - $totalDelay) . 's';

// Finally we could start some processing on the downloaded resources
echo "\n\nDOWNLOADED RESOURCES: ";
$downloaded = $spider->getDownloader()->getPersistenceHandler();
foreach ($downloaded as $resource) {
//    $title = $resource->getCrawler()->filterXpath('//title')->text();
    $contentLength = (int) $resource->getResponse()->getHeaderLine('Content-Length');
    $contentLengthString = '';
	if ($contentLength >= 1024) {
        $contentLengthString = str_pad("[" . round($contentLength / 1024), 4, ' ', STR_PAD_LEFT) . "KB]";
    } else {
        $contentLengthString = str_pad("[" . $contentLength, 5, ' ', STR_PAD_LEFT) . "B]";
    }
    // do something with the data
	$uri_obj = $resource->getUri();
	$uri = $uri_obj->toString();
	$host = $uri_obj->getHost();
	echo "\n Content Length - " .$contentLengthString;
	echo "\n Host name - $host / URI - $uri\n";
    $img_arr = $resource->getCrawler()->filter('img')->extract(['_name', 'src', 'alt']);
	foreach ($img_arr as $img) {
		echo '    IMG -name: ', $img[0], ' -src: ', $img[1], ' -alt: ', $img[2], "\n";
	}
}
echo "\n";
