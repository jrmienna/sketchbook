if (Meteor.isClient) {

    Template.soundcloud.rendered = function() {
        SC.initialize({
            client_id: "2158ee402eb018e7ef81d1fea0bca8a0",
        });
        SC.oEmbed("http://soundcloud.com/forss/sets/soulhack", {
            color: "ff0066"
        }, document.getElementById("soundcloudWidget"));
    };

    Template.soundcloud.events({
        'click #loadTracks': function() {
            SC.get("/tracks", {limit: 1}, function(tracks){
                var track = tracks[0];
                SC.oEmbed(track.uri, document.getElementById("track"));
            });
        }
    });

    Template.spotify.rendered = function() {

    };

}

if (Meteor.isServer) {
    Meteor.startup(function() {
        // code to run on server at startup
    });
}
