const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch({headless:true});

    let stocks = [];
    const tickers = ['AAPL', 'TSLA', 'AMD', 'NVDA'];
    for (let i = 0; i < tickers.length; i++) {
        const page = await browser.newPage();

        await page.goto('https://finance.yahoo.com/quote/' + tickers[i] + '?p=' + tickers[i]);
        var summary = await page.evaluate(args => {
            var target = {};
            target.name = document.querySelector("#quote-header-info > div.Mt\\(15px\\) > div.D\\(ib\\).Mt\\(-5px\\).Mend\\(20px\\).Maw\\(56\\%\\)--tab768.Maw\\(52\\%\\).Ov\\(h\\).smartphone_Maw\\(85\\%\\).smartphone_Mend\\(0px\\) > div.D\\(ib\\) > h1").innerText;
            target.per_ttm = document.querySelector("#quote-summary > div.D\\(ib\\).W\\(1\\/2\\).Bxz\\(bb\\).Pstart\\(12px\\).Va\\(t\\).ie-7_D\\(i\\).ie-7_Pos\\(a\\).smartphone_D\\(b\\).smartphone_W\\(100\\%\\).smartphone_Pstart\\(0px\\).smartphone_BdB.smartphone_Bdc\\(\\$seperatorColor\\) > table > tbody > tr:nth-child(3) > td.Ta\\(end\\).Fw\\(600\\).Lh\\(14px\\) > span").innerText;

            return target;
        });

        await page.goto('https://finance.yahoo.com/quote/' + tickers[i] + '/key-statistics?p=' + tickers[i]);
        const statistics = await page.evaluate(args => {
            var target = {};
            target.forward_per = document.querySelector("#Col1-0-KeyStatistics-Proxy > section > div.Mstart\\(a\\).Mend\\(a\\) > div:nth-child(1) > div > div > div > div > table > tbody > tr:nth-child(4) > td.Fw\\(500\\).Ta\\(end\\).Pstart\\(10px\\).Miw\\(60px\\)").innerText;
            target.pegr_5y = document.querySelector("#Col1-0-KeyStatistics-Proxy > section > div.Mstart\\(a\\).Mend\\(a\\) > div:nth-child(1) > div > div > div > div > table > tbody > tr:nth-child(5) > td.Fw\\(500\\).Ta\\(end\\).Pstart\\(10px\\).Miw\\(60px\\)").innerText;
            target.trailng_pe = document.querySelector("#Col1-0-KeyStatistics-Proxy > section > div.Mstart\\(a\\).Mend\\(a\\) > div:nth-child(1) > div > div > div > div > table > tbody > tr:nth-child(3) > td.Fw\\(500\\).Ta\\(end\\).Pstart\\(10px\\).Miw\\(60px\\)").innerText;
            target.forward_earning_growth = target.trailng_pe / target.forward_per;

            return target;
        });

        stocks.push(Object.assign(summary, statistics));
    }
    console.log(stocks);

    await browser.close();
})();
