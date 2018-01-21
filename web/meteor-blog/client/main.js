Template.blog.rendered = function() {
    $('#postButton').addClass('disabled');
};

Template.blog.events({
    'submit #blogForm': function(e) {

        e.preventDefault();

        var title = $('#blogTitle').val().trim();
        var body = $('#blogBody').val().trim();

        // Call the method submitPost() from server
        Meteor.call('submitPost', title, body);
        $('#blogForm')[0].reset();
    },

    'keyup #blogTitle': function(e) {
        e.preventDefault();
        var title = $('#blogTitle').val().trim();
        var body = $('#blogBody').val().trim();

        if(title.length === 0) {
            $('#postButton').addClass('disabled');
        } else {
            $('#postButton').removeClass('disabled');
        }
    },

    'click #postButton': function(e) {
        $('.alert-success').fadeIn().delay(2000).fadeOut();
        $('#postButton').addClass('disabled');
    },

    

});

Template.listBlogs.events({
    'click .close': function(e) {
        Meteor.call('removePost', this._id);
        $('.alert-danger').fadeIn().delay(2000).fadeOut();
    }
});

Template.listBlogs.blogs = function() {
    return Blogs.find().fetch().reverse();
};
