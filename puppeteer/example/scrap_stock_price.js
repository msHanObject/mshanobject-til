const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch({headless:true});
    const page = await browser.newPage();

    await page.goto('https://kr.investing.com/equities/south-korea');
    
    const stocks = await page.evaluate(args => {
        const stocks = [];
        document.querySelectorAll('#cross_rate_markets_stocks_1 > tbody > tr').forEach((element, i) => {
            const stock = {};
            stock.index = i;
            stock.name = element.children[1].innerText;
            stock.current_price = element.children[2].innerText;
            stock.high_price = element.children[3].innerText;
            stock.low_price = element.children[4].innerText;

            stocks.push(stock);
        });

        return stocks;
    });
    console.log(stocks);

    await browser.close();
})();
