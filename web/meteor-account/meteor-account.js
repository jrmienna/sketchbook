Accounts.config({
    sendVerificationEmail: false,
    forbidClientAccountCreation: true
});


if (Meteor.isClient) {

    Accounts.ui.config({
        passwordSignupFields: "USERNAME_ONLY"
    });

    Template.menubar.events({
        "click #logoutButton": function(event, template) {
            Meteor.logout();
        }
    });

    Template.login.rendered = function(){
        $('.dropdown').dropdown();
    }

    Template.login.events({
        "submit #loginForm": function(event, template){
            event.preventDefault();

            var username = template.find("#loginUsername").value;
            var password = template.find("#loginPassword").value;

            // TODO: trim and validate input

            Meteor.loginWithPassword(username, password, function(err){
                if(err) {
                    // handle login error
                } else {
                    // the user has been loged in
                    console.log("success");
                }
            });
            return false;
        }
    });


}

if (Meteor.isServer) {

    Meteor.startup(function(){

        // TODO: remove this
        Meteor.users.remove({});

        if (Meteor.users.find().count() === 0) {
            Accounts.createUser({
                username: "start",
                password: "start123",
                email: "leder@startntnu.no",
                profile: {

                }
            });
            Accounts.createUser({
                username: "es",
                password: "es123",
                email: "leder@es.ntnu.no",
                profile: {

                }
            });
            Accounts.createUser({
                username: "spark",
                password: "spark123",
                email: "leder@spark.ntnu.no",
                profile: {

                }
            });
        }
    });
}
