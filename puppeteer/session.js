const WebSocket = require('ws');
const puppeteer = require('puppeteer');
const SEND = require('./SEND');
const fsp = require('fs').promises;

(async () => {
    // Launch a headless browser so that we can get the result.
    const browser = await puppeteer.launch({ headless: true });

    // Create a websocket to issue CDP commands.
    const ws = new WebSocket(browser.wsEndpoint(), { perMessageDeflate: false });
    await new Promise((resolve) => ws.once("open", resolve));

    // Get list of all targets and find a "page" target.
    const targetsResponse = await SEND(ws, {
        id: 1,
        method: "Target.getTargets",
    });
    const pageTarget = targetsResponse.result.targetInfos.find(
        (info) => info.type === "page"
    );

    // Attach to the page target.
    const sessionId = (
        await SEND(ws, {
            id: 2,
            method: "Target.attachToTarget",
            params: {
                targetId: pageTarget.targetId,
                flatten: true,
            },
        })
    ).result.sessionId;

    // Navigate the page using the session.
    await SEND(ws, {
        sessionId,
        id: 1, // Note that IDs are independent between sessions.
        method: "Page.navigate",
        params: {
            url: "https://ableeworks.com/report/xhtml",
        },
    });

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
    const res = await SEND(ws, {
        sessionId,
        id: 1,
        method: "Page.printToPDF",
        params: {
            displayHeaderFooter: true,
            printBackground: true,
            headerTemplate: '<div></div>',
            footerTemplate: footer,
		    margin: {
              top: 80,
              bottom: 80,
              left: 70,
              right: 70
            },
        },
    });
    const buff = Buffer.from(res.result.data, 'base64');
    const filename = 'CDP_output' + Date.now() + '.pdf';
    await fsp.writeFile(filename, buff); 

    await browser.close()
})();
