<?php
require_once __DIR__.'/vendor/autoload.php';
use Spatie\Crawler\tests\CrawlerTest;
CrawlerTest::setUp()->it_will_crawl_all_found_urls();
