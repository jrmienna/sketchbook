function generatePost(txt) {
    var listItem = $('<li>');
    listItem.addClass('post');

    var usrIcon = $('<span>').addClass('fa fa-user');
    usrIcon.text(' whoami:\t' + txt);
    usrIcon.prependTo(listItem);

    var closeButton = $('<button>').addClass('fa fa-times fa-sm close');
    closeButton.prependTo(listItem);

    return listItem;
}
function main() {
    var first = generatePost("random");
    first.addClass('list-group-item').prependTo('.posts');

    $('.btn').click(function() {
        var txt = $('.status-box').val();
        var post = generatePost(txt);
        post.addClass('list-group-item').prependTo('.posts');

        $('.status-box').val('');
        $('.counter').text('140');
        $('.btn').addClass('disabled');
    });

    $('.close').click(function() {
        $(this).parent().remove();
    });

    $('.status-box').keyup(function() {
        postLength = $(this).val().length;
        charactersLeft = 140 - postLength;
        $('.counter').text(charactersLeft);
        if (charactersLeft == 140){
            $('.btn').addClass('disabled');
        } else if (charactersLeft >= 0) {
            $('.btn').removeClass('disabled');
        } else {
            $('.btn').addClass('disabled');
        } 
    });

    $('.status-box').keypress(function(e) {
        if (e.which == 13) {
            e.preventDefault();
            $('.btn').click();
        }
    });

    $('.btn').addClass('disabled');
}
$(document).ready(main);
