// include library
const puppeteer = require('puppeteer');
const output = 'report' + Date.now() + '.pdf';

// declare function
(async () => {
    // run browser
    const browser = await puppeteer.launch();
    // create new tab
    const page = await browser.newPage();
    // go to page
    await page.goto('https://ableeworks.com/report/xhtml', {waitUntil: 'networkidle2'});
    
    // declare html markup for header
    footer =
`<div class="test" style="width:100%; padding:0 50px 10px; margin:0; overflow:hidden;">
    <p style="float:left;">
	    <span class="pageNumber" style="padding-right:30px; font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#424242;"></span>
		<span style="padding-right:10px; font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#000;">행정안전부</span>
		<span style="font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#424242;">웹 품질 모니터링 결과 보고서</span>
	</p>
	<div style="float:right;">
	    <a href="https://www.ableeworks.com" target="_blank" style="font-family:'Noto Sans Korean','Apple Gothic','HY Gulim','MalgunGothic','HY Dotum','Lexi Gulim','Helvetica Neue','Helvetica','Arial',sans-serif; font-size:14px; color:#2c46aa; text-decoration:none;">ABLEEWORKS 바로가기</a>
	</div>
</div>
`;
	header = '<div></div>';
	
    // set pdf options
    await page.pdf({
        path: output,
        displayHeaderFooter: true,
        printBackground: true,
        footerTemplate: footer,
        headerTemplate: header,
		margin: {
            top: 80,
            bottom: 80,
            left: 70,
            right: 70
        }
    });

    await browser.close();
})();

