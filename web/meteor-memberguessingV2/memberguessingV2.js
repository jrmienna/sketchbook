if (Meteor.isClient) {

    Accounts.ui.config({
        requestPermissions: {
            facebook: [
                'email', 
                'user_friends', 
                'user_location', 
                'user_events', 
                'friends_events', 
                'friends_location',
                'friends_about_me', 
                'user_status',
                'friends_status',
                'read_friendlists',
            ]
        }
    });

    Router.route('/', function () {
      this.render('home', {});
    }); 

    Template.home.rendered = function()  {
        //FB.api(
            //'/167037616647269/members',
            //function(response) {
                //if (response && !response.error) {
                    //console.log(response);
                //}
            //}
        //);

    };

    Template.home.helpers({});

    Template.home.events({
        'click #btn-user-data': function(e) {
            Meteor.call('getUserData', function(err, data) {
                if(err) {
                    console.log(err);
                }
                $('#result').text(JSON.stringify(data, undefined, 4));
            });
        }
    });
}

if (Meteor.isServer) {
    Meteor.startup(function() {
        // code to run on server at startup

        function FB(accessToken) {
            this.fb = Meteor.require('fbgraph');
            this.accessToken = accessToken;
            this.fb.setAccessToken(this.accessToken);
            this.options = {
                timeout: 3000,
                pool: {
                    maxSockets: Infinity
                },
                headers: {
                    connection: 'keep-alive'
                }
            };
            this.fb.setOptions(this.options);
        }

        FB.prototype.query = function(query, mtd) {
            var self = this;
            var method = (typeof mtd === 'undefined') ? 'get' : mtd;
            var data = Meteor.sync(function(done) {
                self.fb[method](query, function(err, res) {
                    done(null, res);
                });
            });
            return data.result;
        };

        FB.prototype.getUserData = function() {
            return this.query('me');
        };

        FB.prototype.getFriendsData = function() {
            return this.query('/me/friendlist');
        };

        Meteor.methods({
            getUserData: function() {
                var fb = new Facebook(Meteor.user().services.facebook.accessToken);
                var data = fb.getUserData();
                return data;
            },
            getFriendsData: function() {
                var fb = new Facebook(Meteor.user().services.facebook.accessToken);
                var data = fb.getFriendsData();
                return data;
            }
        });
    });
}
