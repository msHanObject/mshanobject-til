<?php

namespace VDB\Spider\RequestHandler;

use GuzzleHttp\Client;
use GuzzleHttp\Message\Request;
use GuzzleHttp\Message\RequestInterface;
use VDB\Spider\Resource;
use VDB\Spider\Uri\DiscoveredUri;

class GuzzleRequestHandler implements RequestHandlerInterface
{
    /** @var Client */
    private $client;

    /**
     * @param Client $client
     * @return RequestHandler Interface
     */
    public function setClient(Client $client)
    {
        $this->client = $client;

        return $this;
    }

    /**
     * @return Client;
     */
    public function getClient()
    {
        if (!$this->client) {
            $this->client = new Client();
        }

        return $this->client;
    }

    /**
     * @param DiscoveredUri $uri
     * @return Resource
     */
    public function request(DiscoveredUri $uri)
    {
        $response = $this->getClient()->get($uri->toString());
        return new Resource($uri, $resopnse);
    }
}
