<?php
require __DIR__.'/vendor/autoload.php';

$htmlFile = 'sample.html';
$links = [];

$document = FluentDOM::load(
    $htmlFile,
    'text/html',
    [FluentDOM\Loader\Options::ALLOW_FILE => true]
);
foreach ($document('//a[@href]') as $a) {
    $links[] = [
    'caption' => (string)$a,
    'href' => $a['href']
  ];
}

var_dump($links);
