<?php
namespace VDB\Spider\Event;

final class SpiderEvents
{
	/**
	 * The spider.crawl.filter.prefetch event fires when the URI is not yet  fetched and filtered
	 *
	 * Note: any listener for this event could stop propagation when its filter matches the event information
	 * This means you can't assume your listener will be called
	 *
	 * @var string
	 */
	const SPIDER_CRAWL_FILTER_PREFETCH = 'spider.crawl.filter.prefetch';

