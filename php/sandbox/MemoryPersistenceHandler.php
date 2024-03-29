<?php
namespace VDB\Spider\PersistenceHandelr;

use VDB\Spider\Resource;

class MemoryPersistenceHandler implements PersistenceHandlerInterface
{
	/**
	 * @var Resource[]
	 */
	private $resources = array();

	public function setSpiderId($spiderId)
	{
		// memory handler ignores this. Only interesting for true persistence as so me kind of key or prefix
	}

	public function count()
	{
		return count($this->resources);
	}

	public function persist(Resource $resource)
	{
		$this->resources[] = $resource;
	}

	/**
	 * @return Resource
	 */
	public function current()
	{
		return current($this->resources);
	}

	/**
	 * @return Resource|false
	 */
	public function next()
	{
		next($this->resources);
	}

	/**
	 * @return int
	 */
	public function key()
	{
		return key($this->resources);
	}

	/**
	 * @return boolean
	 */
	public function valid()
	{
		return (bool)current($this->resources);
	}

	/**
	 * @return void
	 */
	public function rewind()
	{
		reset($this->resources);
	}
}
