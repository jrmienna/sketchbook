function hideShit() {
    $('.FieldLabel').hide();
    $('img').hide();
    $('form').hide();
}

function createNavbar() {
    var nav = $('<nav class="nav navbar navbar-default"></nav>');
    var brand = $('<img class="navbar-brand" src="./images/logo.png"></img>').prependTo(nav);
    return nav;
}

function createGrid() {
    var containerFluid = $('<div class="container-fluid"></div>');
}

function createForm() {
    var row = $('<div class="row row-centered"></div>');
    var col = $('<div class="col-lg-3 col-centered"></div>').appendTo(row);
    var form = $('<form name="aspnetForm" method="post" action="Login.aspx" id="aspnetForm" class="WRBForm">').appendTo(col);
    $('<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwULLTE5OTI3NjY5NjYPZBYCZg9kFgICAw9kFgICAw9kFg4CAQ8PFgIeBFRleHQFCklubmxvZ2dpbmdkZAIDDw8WAh8ABSJMb2dnIHDDpSBtZWQgYnJ1a2VybmF2biBvZyBwYXNzb3JkZGQCBQ8PFgIfAAUKQnJ1a2VybmF2bmRkAgkPDxYCHwAFB1Bhc3NvcmRkZAINDw8WAh8ABQhMb2dnIGlubmRkAg4PDxYEHwAFCVJlZ2lzdHJlch4HVmlzaWJsZWhkZAIQDw8WAh8ABR1GZWlsIGJydWtlcm5hdm4gZWxsZXIgcGFzc29yZGRkZI4CedhubLmHzss6xYMLCVN3Sh9o" class="form-control">').appendTo(form);
    $('<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="C2EE9ABB" class="form-control">').appendTo(form);
    $('<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWBAK5/M7+BQLBqueXCwK/1uzbCQL/pJy+BIE4nvZdtYWoTWEbWa+Bym0/M1sT" class="form-control">').appendTo(form);
    $('<input name="ctl00$Main$UsernameBox" type="text" id="ctl00_Main_UsernameBox" class="form-control" placeholder="Brukernavn">').appendTo(form);
    $('<input name="ctl00$Main$PasswordBox" type="password" id="ctl00_Main_PasswordBox" class="form-control" placeholder="Passord">').appendTo(form); 
    $('<input type="submit" name="ctl00$Main$LoginBtn" value="Logg inn" id="ctl00_Main_LoginBtn" class="form-control">').appendTo(form);
    return row;
}

function createDOM() {
    createNavbar().prependTo('body');
    createForm().appendTo('body');
}
function prettify() {
    $('#ctl00_Main_UsernameBox').attr("placeholder", "Brukernavn");
    $('#ctl00_Main_PasswordBox').attr("placeholder", "Passord");
    $('input').removeClass();
    $('.MasterMain').removeClass().addClass('container');
    $('.MasterHeader').removeClass().addClass('container-fluid');
    $('.Banner').removeClass().addClass('col-lg-4');
}

function boostrapify() {
    $('input').addClass('form-control');
    $('button').addClass('btn');
    $('.LabelControlPair').addClass('form-group');
}

function main() {
    hideShit();
    createDOM();
    prettify();
    boostrapify();
}

$(document).ready(main);
