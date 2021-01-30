# Headless Mode Chrome Usage
This contents clone from [developers.google.com](https://developers.google.com/web/updates/2017/04/headless-chrome)

## Make alias for command
```
alias chrome="/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome"
alias chrome-canary="/Applications/Google\ Chrome\ Canary.app/Contents/MacOS/Google\ Chrome\ Canary"
alias chromium="/Applications/Chromium.app/Contents/MacOS/Chromium"
```

## Printing the DOM
```
chrome --headless --disable-gpu --dump-dom https://www.chromestatus.com/
```

## Create a PDF
```
chrome --headless --disable-gpu --print-to-pdf https://www.chromestatus.com/
```

## Taking screenshots
```
chrome --headless --disable-gpu --screenshot https://www.chromestatus.com/

# Size of a standard letterhead.
chrome --headless --disable-gpu --screenshot --window-size=1280,1696 https://www.chromestatus.com/

# Nexus 5x
chrome --headless --disable-gpu --screenshot --window-size=412,732 https://www.chromestatus.com/
```
