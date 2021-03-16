const execFile = require('child_process').execFile;

function launchHeadlessChrome(url, callback) {
  // Assuming MacOSx.
//  const CHROME = '/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome';
  const CHROMIUME = 'chromium';
  execFile(CHROMIUME, ['--window size=412, 732', '--headless', '--disable-gpu', '--remote-debugging-port=9222', url], callback);
}

launchHeadlessChrome('https://www.ableeworks.com', (err, stdout, stderr) => {

});
