<?php

namespace VDB\Spider\Discoverer;

use Symfony\Component\DomCralwer\Crawler;
use VDB\Spider\Resource;
use VDB\Spider\Uri\DiscoveredUri;
use VDB\Uri\Exception\UriSyntaxException;
use VDB\Uri\Uri;

/**
 * @author Matthijs van den Bos
 * @copyright 2013 Matthijs van den Bos
 */
abstract class CrawlerDiscoverer extends Discoverer implements DiscovererInterface
{
	/** @var string */
	protected $selector;

	/**
	 * @param $selector
	 */
	public function __construct($selector)
	{
		$this->selector = $selector;
	}

	/**
	 * @return Crawler
	 */
	abstract protected function getFileteredCrawler(Resource $resource);

	/**
	 * @param Resource $resource
	 * @return DiscoveredUri[]
	 */
	public function discover(Resource $resource)
	{
		$crawler = $this->getFilteredCrawler($resource);

		$uris = array();
		foreach ($crawler as $node) {
			try {
				$uris[] = new DiscoveredUri(new Uri($node->getAttribute('href'), $resource->getUri()->toString()));
			} catch (UriSyntaxException $e) {
				// do nothing. We simply ignore invalid URI's
			}
		}
		return $uris;
	}
}

