const CDP = require('chrome-remote-interface');
const fs = require('fs');

CDP(async (client) => {
    const {Page} = client;
    try {
        await Page.enable();
        await Page.navigate({url: 'https://www.ableeworks.com/report/xhtml/'});
        await Page.loadEventFired();
        const footer =
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
        const {data} = await Page.printToPDF({
            printBackground: true,
            displayHeaderFooter: true,
            headerTemplate: '<div></div>',
            footerTemplate: footer
        });
        fs.writeFileSync('output.pdf', Buffer.from(data, 'base64'));
    } catch (err) {
        console.error(err);
    } finally {
        await client.close();
    }
}).on('error', (err) => {
    console.error(err);
});
