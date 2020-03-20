<?php

interface FilterableInterface
{
    public function setFiltered($filtered = true, $reason = '');

    public function is_filtered();
    public function getFilter_reason();

    public function getIdentifier();
}
