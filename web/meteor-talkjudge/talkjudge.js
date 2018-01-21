Talks = new Meteor.Collection("talks");

Meteor.methods({
    upvote: function (talkId) {
        var talk = Talks.findOne(talkId);
        Talks.update(
            talkId,
            {$set: {votes: talk.votes + 1}}
        );
    },
    downvote: function (talkId) {
        var talk = Talks.findOne(talkId);
        Talks.update(
            talkId,
            {$set: {votes: talk.votes - 1}}
        );
    }
});

if (Meteor.isClient) {
    // Store published talks in the clients own collection
    Meteor.subscribe("talks");

    Template.talksList.talks = function() {
        return Talks.find({}, {
            sort: {votes: -1}
        });
    };

    Template.talksList.events({
        "click .upvote": function() {
            Meteor.call("upvote", this._id);
        },
        "click .downvote": function() {
            Meteor.call("downvote", this._id);
        }
    });
}


if (Meteor.isServer) {

    Meteor.publish("talks", function() {
        return Talks.find();
    });

}
