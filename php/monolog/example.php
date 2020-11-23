<?php
include "vendor/autoload.php";

use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Handler\FirePHPHandler;
use Monolog\Formatter\LineFormatter;

// Create a log channel
$logger = new Logger(pathinfo(__FILE__, PATHINFO_BASENAME));

// the default date format is "Y-m-d\TH:i:sP"
$dateFormat = "Y n j, g:i a";
// the default output format is "[%datetime%] %channel%.%level_name%: %message% %context% %extra%\n"
$output = "%datetime% > %level_name% > %message% %context% %extra%\n";
// finally, create a formatter
$formatter = new LineFormatter($output, $dateFormat);

/* Create some handlers.
 * This logger's path is __DIR__.'/my_app.log'.
 * This logger level is DEBUG.
*/
$stream = new StreamHandler(__DIR__.'/my_app.log', Logger::DEBUG);
$stream->setFormatter($formatter);
$firephp = new FirePHPHandler();

// Create the main logger of the app
$logger = new Logger('my_logger');
$logger->pushHandler($stream);
$logger->pushHandler($firephp);

// Create a logger for the security-related stuff with a different channel
$securityLogger = new Logger('security');
$securityLogger->pushHandler($stream);
$securityLogger->pushHandler($firephp);

// Add records to the log
$logger->info('Log info is printed');
$logger->warning('Foo', array('name','foo', __DIR__));
$logger->error('Bar', array('filename' => __FILE__, 'err_line' => __LINE__));
