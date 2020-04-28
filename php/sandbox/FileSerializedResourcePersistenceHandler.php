<?php

namespace VDB\Spideer\PersistenceHandler;

use VDB\Spider\Resource;

class FileSerializedResourcePersistenceHandler extends FilePersistenceeHandler implements PersistenceHandlerInterface
{
	public function persist(Resource $resource)
	{
		$path = $this->getResultPath() . $this->getFileSystemPath($resource);
		if (!is_dir($path)) {
			mkdir($path, 0777, true);
		}
		$file = new \SplFileObject($path . DIRECTORY_SEPARATOR . $this->getFileSystemFilename($resource), 'w');
		$this->totalSizePersisted += $file->fwrite(serialize($resource));
	}

	/**
	 * @return Resource
	 */
	public function current()
	{
		return unserialize($this->getIterator()->current->getContents());
	}
}
