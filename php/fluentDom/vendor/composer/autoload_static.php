<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInite85857f94c6d150055bba96b0e8d42c7
{
    public static $files = array(
        '00032f3d1c63c80843348abd4f65f78f' => __DIR__ . '/..' . '/fluentdom/fluentdom/src/FluentDOM.php',
        'd7c9a5138b45deb428e175ae748db2c5' => __DIR__ . '/..' . '/carica/phpcss/src/PhpCss.php',
        'b7187e2c212dd315c666a01c3e4a54d2' => __DIR__ . '/..' . '/fluentdom/selectors-phpcss/src/plugin.php',
        '873b7d4c7165a46f8bfe6f253c23fb15' => __DIR__ . '/..' . '/fluentdom/selectors-symfony/src/plugin.php',
    );

    public static $prefixLengthsPsr4 = array(
        'S' =>
        array(
            'Symfony\\Component\\CssSelector\\' => 30,
        ),
        'P' =>
        array(
            'PhpCss\\' => 7,
        ),
        'F' =>
        array(
            'FluentDOM\\Symfony\\CssSelector\\' => 30,
            'FluentDOM\\PhpCss\\' => 17,
            'FluentDOM\\' => 10,
        ),
    );

    public static $prefixDirsPsr4 = array(
        'Symfony\\Component\\CssSelector\\' =>
        array(
            0 => __DIR__ . '/..' . '/symfony/css-selector',
        ),
        'PhpCss\\' =>
        array(
            0 => __DIR__ . '/..' . '/carica/phpcss/src/PhpCss',
        ),
        'FluentDOM\\Symfony\\CssSelector\\' =>
        array(
            0 => __DIR__ . '/..' . '/fluentdom/selectors-symfony/src',
        ),
        'FluentDOM\\PhpCss\\' =>
        array(
            0 => __DIR__ . '/..' . '/fluentdom/selectors-phpcss/src',
        ),
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
