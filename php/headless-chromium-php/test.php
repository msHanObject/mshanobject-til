<?php
require_once __DIR__ . "/vendor/autoload.php";

use HeadlessChromium\BrowserFactory;

//$browserFactory = new BrowserFactory();
$browserFactory = new BrowserFactory('chromium');    // replace default 'chrome' with 'chromium-browser'

// starts headless chrome
$browser = $browserFactory->createBrowser([
    'headless'        => true,
    'connectionDelay' => 0.8,
]);

try {
    // creates a new page and navigate to an url
    $page = $browser->createPage();
    $page->navigate('https://www.ableeworks.com/report/xhtml')->waitForNavigation();

    // get page title
//    $pageTitle = $page->evaluate('document.title')->getReturnValue();
//    echo '페이지 제목: ' . $pageTitle;

    // screenshot - Say "Cheese"! 😄
    $page->screenshot()->saveToFile('screenshot.png');

    // set footer template
    $footer = <<<HTML
<div class="test" style="width:100%; padding:0 50px 10px; margin:0; overflow:hidden;">
    <p style="float:left;">
	    <span class="pageNumber" style="padding-right:30px; font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#424242;"></span>
		<span style="padding-right:10px; font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#000;">행정안전부</span>
		<span style="font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#424242;">웹 품질 모니터링 결과 보고서</span>
	</p>
	<div style="float:right;">
	    <a href="https://www.ableeworks.com" target="_blank" style="font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#2c46aa; text-decoration:none;">ABLEEWORKS 바로가기</a>
	</div>
</div>
HTML;

    // set pdf options
    $page->pdf([
        'printBackground' => true,
        'displayHeaderFooter' => true,
        'marginTop' => 0.4,
        'marginBotttom' => 2.8,
        'marginLeft' => 0.7,
        'marginRight' => 0.7,
        'headerTemplate' => '<div></div>',
        'footerTemplate' => $footer
    ])->saveToFile('output.pdf');
} finally {
    // bye
    $browser->close();
}
