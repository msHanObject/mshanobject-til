// Define font files
var fonts = {
    Roboto: {
        normal: "fonts/NotoSansKR-Regular.otf",
        bold: "fonts/Roboto-Medium.ttf",
        italics: "fonts/Roboto-Italic.ttf",
        bolditalics: "fonts/Roboto-MediumItalic.ttf",
    },
}

var PdfPrinter = require("pdfmake")
var printer = new PdfPrinter(fonts)
var fs = require("fs")

var docDefinition = {
    header: {
        text: "헤더 영역",
        margin:[10,10,10,10],
    },

    footer: {
        columns: [
            "띄어쓰기없이",
            {
                text: "한글 `~1!2@3#4$5%6^7&8*9(0)-_=+/?.>,<';:}][{\" 표현하다가 영문 쓰면 문제 되나?????",
                alignment: "right",
                margin:[0, 0],
            },
        ],
    },

    content: [
        {
            text: "This is a Content start position",
            style: "content",
        },
        "No styling here, 영문 한글 혼합",
        {
            text: "Another text",
            style: "anotherStyle",
        },
        {
            text: "Multiple styles applied",
            style: ["header", "anotherStyle"],
        },
    ],

    styles: {
        content: {
            fontSize: 22,
            bold: true,
        },
        anotherStyle: {
            italics: true,
            alignment: "right",
        },
    },
}

var options = {
    // ...
}

var pdfDoc = printer.createPdfKitDocument(docDefinition, options)
pdfDoc.pipe(fs.createWriteStream("output.pdf"))
pdfDoc.end()
