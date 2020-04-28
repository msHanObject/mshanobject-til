<?php
require __DIR__.'/vendor/autoload.php';

$file_path = 'sample.html';

$document = FluentDOM::load(
    $file_path,
    'text/html',
    [FluentDOM\Loader\Options::ALLOW_FILE => true]
);
$links = [];
foreach ($document('//a[@href]') as $a) {
    $links[] = [
    'caption' => (string)$a,
    'href' => $a['href']
  ];
}
var_dump($links);
