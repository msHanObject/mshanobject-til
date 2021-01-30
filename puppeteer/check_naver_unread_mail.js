const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch({headless:false});
    const page = await browser.newPage();

    await page.goto('https://nid.naver.com/nidlogin.login?url=http://mail.naver.com/');
    await page.type('#id', '아이디', {delay:50});
    await page.type('#pw', '비밀번호', {delay:50});
    await page.click('[type="submit"]');
    await page.waitForNavigation();
    
    // 읽지 않은 메일 정보 가져오기
    const unread_mails = await page.evaluate(args => {
        const mails = [];
        document.querySelectorAll('#list_for_view>ol>li.notRead>.mTitle>.subject>a>span>strong').forEach((element, i) => {
            const mail = {};
            mail.index = i;
            mail.sequence = element.getAttribute('mailsn');
            mail.title = element.innerText;

            mails.push(mail);
        });

        return mails;
    });
    console.log(unread_mails);

    // 읽지 않은 메일들을 체크
    for (let i = 0; i < unread_mails.length; i++) {
        await page.click('#mailCheck_' + unread_mails[i].sequence);
    }

    // '읽음' 버튼을 클릭하여 체크된 메일들을 '읽음' 상태로 변경
    await page.click('#listBtnMenu > div.buttonSet > button.do_read');


    await browser.close();
})();
