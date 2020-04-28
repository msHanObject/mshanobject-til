<?php

namespace VDB\Spider\PersistenceHandler;

use VDB\Spider\Resource;

interface PersistenceHandlerInterface extends \Iterator, \Countable
{
	/**
	 * @param string $spiderId
	 *
	 * @return void
	 */
	public function setSpiderId($spiderId);

	/**
	 * @return void
	 */
	public function persist(Resource $resource);
}
