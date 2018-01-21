var markdownpdf = require("markdown-pdf")
  , fs = require("fs")
  , split = require("split")
  , through = require("through")
  , duplexer = require("duplexer")
  , through2 = require('through2')

var options = {
    cssPath: "./main.css",
    paperBorder: "2cm",
    remarkable: {
        html: true,
        breaks: true,
        //plugins: [ require('remarkable-classy') ],
        syntax: [ 'footnote', 'sup', 'sub' ]
    }
};

var mdDocs = [
    "markdown/contact.md",
    "markdown/education.md",
    "markdown/experiences.md",
    "markdown/skills.md",
    "markdown/interests.md",
    "markdown/languages.md",
    "markdown/certifications.md",
]

function preProcessMd() {
    // Split the input stream by lines
    var splitter = split()

    // Replace occurences of "foo" with "bar"
    var replacer = through(function (data) {
        this.queue(data.replace(/foo/g, "bar") + "\n")
    })

    splitter.pipe(replacer)
    return duplexer(splitter, replacer)
}

function preProcessHtml() {
  var writeCount = 0
  return through2(function (data, enc, cb) {
    writeCount++;
    this.push(data);
    cb()
  })
}

options.preProcessMd = preProcessMd
options.preProcessHtml = preProcessHtml

markdownpdf(options).concat.from(mdDocs).to('cv.pdf', function () {
    console.log("Done")
})
