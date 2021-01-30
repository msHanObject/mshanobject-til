// include library
const puppeteer = require('puppeteer');

// declare function
(async () => {
    // run browser
    const browser = await puppeteer.launch();
    // create new tab
    const page = await browser.newPage();
    // go to page
    await page.goto('타겟 URL', {waitUntil: 'networkidle2'});
    
    // declare html markup for header
    header = 
`<div style="font-size:15px; padding-top:8px; text-align:center; width:100%;">
    <span>This is a 헤더</span> - <span class="pageNumber"></span>
</div>
`;
    footer =
`<div style="font-size:20px; padding-bottom:8px; text-align:right; width:100%;">
    <span>This is a 푸터</span> - <span class="totalPages"></span>
</div>
`;
    // set pdf options
    await page.pdf({
        path: 'output.pdf',
        displayHeaderFooter: true,
        printBackground: true,
        headerTemplate: header,
        footerTemplate: footer,
        margin: {
            top: 80,
            bottom: 80,
            left: 40,
            right: 40
        }
    });

    await browser.close();
})();
