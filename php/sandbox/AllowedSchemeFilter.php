<?php

namespace VDB\Spider\Filter\Prefetch;

use VDB\Spider\Filter\PreFetchFilterInterface;
use VDB\Uri\UriInterface;

/**
 *@author amtthijs
 */
class AllowedSchemeFilter implements PreFetchFilterInterface
{
	private $allowedSchemes = array();

	/**
	 * @param string[] $schemes
	 */
	public function __construct(array $schmes)
	{
		$this->allowedSchemes = $schemes;
	}

	/**
	 * @return bool
	 */
	public function match(UriInterface $uri)
	{
		return !in_array($uri->getScheme(), $this->allowedSchemes);
	}
}
