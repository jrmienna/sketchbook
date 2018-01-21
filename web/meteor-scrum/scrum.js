if (Meteor.isClient) {
    
    Router.route('/', function() {
        this.render('home');
    });

}

if (Meteor.isServer) {

}
