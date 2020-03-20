<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInite85857f94c6d150055bba96b0e8d42c7
{
    public static $files = array(
        '00032f3d1c63c80843348abd4f65f78f' => __DIR__ . '/..' . '/fluentdom/fluentdom/src/FluentDOM.php',
    );

    public static $prefixLengthsPsr4 = array(
        'F' =>
        array(
            'FluentDOM\\' => 10,
        ),
    );

    public static $prefixDirsPsr4 = array(
        'FluentDOM\\' =>
        array(
            0 => __DIR__ . '/..' . '/fluentdom/fluentdom/src/FluentDOM',
        ),
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixLengthsPsr4 = ComposerStaticInite85857f94c6d150055bba96b0e8d42c7::$prefixLengthsPsr4;
            $loader->prefixDirsPsr4 = ComposerStaticInite85857f94c6d150055bba96b0e8d42c7::$prefixDirsPsr4;
        }, null, ClassLoader::class);
    }
}