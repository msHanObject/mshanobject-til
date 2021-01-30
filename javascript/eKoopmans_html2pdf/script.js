function addScript(url) {
    var script = document.createElement('script');
    script.type = 'application/javascript';
    script.src = url;
    document.head.appendChild(script);
}
addScript('https://raw.githack.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js');

// 브라우저에서 실행시 위에까지만 입력하고 나서 나머지 입력
var element = document.body;
var opt = {
  margin:       0,
  filename:     'output.pdf',
  html2canvas:  { scale: 1 },
  jsPDF:        { orientation: 'portrait', unit: 'mm', format: 'a4', compressPDF: true }
};
// scale 조정 및 width 제한해도 실패

// New Promise-based usage:
html2pdf().from(element).set(opt).toPdf().get('pdf').then(function (pdf) {
  var totalPages = pdf.internal.getNumberOfPages();

  for (i = 1; i <= totalPages; i++) {
    pdf.setPage(i);
    pdf.setFontSize(10);
    pdf.setTextColor(150);
    pdf.text('Page ' + i + ' of ' + totalPages, pdf.internal.pageSize.getWidth() - 100, pdf.internal.pageSize.getHeight() - 30);
  }
}).save();
