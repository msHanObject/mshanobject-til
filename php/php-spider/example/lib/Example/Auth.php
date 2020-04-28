<?php

namespace Example;

use Symfony\Component\EventDispatcher\Event;

class Auth
{
    public function setAuth(Event $event)
    {
        $client = $event->getSubject()->getRequestHandler()->getClient();
        $client->setDefaultOption('auth', array('tester1', '111111', 'Basic'));
    }
}
