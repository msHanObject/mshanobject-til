<?php

namespace VDB\Spider\QueueManager;

use Symfony\Component\EventDispatcher\EventDispatcher;
use Symfony\Component\EventDispatcher\EventDispatcher;
use Symfony\Component\EventDispatcher\GenericEvent;
use VDB\Spider\Event\SpiderEvents;
use VDB\Spider\Exception\QueueException;
use VDB\Spider\Uri\DiscoveredUri;

class InMemoryQueueManager implements QueueManagerInterface
{
	/** @var int The maximum size of the process queue for this spider. 0 means inite  */
	public $maxQueueSize = 0;

	/** @var int the amount of times a Resource was enqueued */
	private $currentQueueSize = 0;

	/** @var DiscoveredUri[] the list of URIs to process*/
	private $traversalQueue = array();

	/** @var in the traversal algorithm to use. Choose from the class constants */
	private $traversalAlgorithm = self::ALGORITHM_DEPTH_FIRST;

	/** @var EventDispatcherInterface */
	private $dispatcher;

	/**
	 * @param int $traversalAlgorithm Choose from the class constants
	 *  TODO: This should be extracted to a Strategy pattern
	 */
	public function setTraversalAlgorithm($traversalAlgorithm)
	{
		$this->traversalAlgorithm = $traversalAlgorithm;
	}

	/**
	 * @return int
	 */
	public function getTraversalAlgorithm()
	{
		return $this->traversalAlgorithm;
	}

	/**
	 * @param EventDispatcherInterface $eventDispatcher
	 * @return $this
	 */
	public function setDispatcher(EventDispatcherInterface $eventDispatcher)
	{
		$this->dispatcher = $eventDispatcher;

		return $this;
	}

	/**
	 * @return EventDispatcherInterface
	 */
	public function getDispatcher()
	{
		if (!$this->dispatcher) {
			$this->dispatcher = new EventDispatcher();
		}
		return $this->dispatcher;
	}

	/**
	 * @param DiscoveredUri
	 * @throws QueueException
	 */
	public function addUri(DiscoveredUri $uri)
	{
		if ($this->maxQueueSize != 0 && $this->currentQueueSize >= $this->maxQueueSize) {
			throw new QueueException('Maximum Queue Size of ' . $this->maxQueueSize . 'reached');
		}

		$this->currentQueueSize++;
		array_push($this->traversalQueue, $uri);

		$this->getDispatcher()->dispatch(
			SpiderEvents::SPIDER_CRAWL_POST_ENQUEUE,
			new GenericEvent($this, array('uri' => $uri))
		);
	}

	public function next()
	{
		if ($this->traversalAlgorithm === static::ALGORITHM_DEPTH_FIRST) {
			return array_pop($this->traversalQueue);
		} elseif ($this->traversalAlgorithm === static::ALGORITHM_BREATH_FIRST) {
			return array_shift($this->traversalQueue);
		} else {
			throw new \LogicException('No search algorithm set');
		}
	}
}
