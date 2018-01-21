// Create new method on server
Meteor.methods({
    'submitPost': function(tit, bd) {
        console.log(tit + ' : ' + bd);

        // Insert new data into the blogs collection
        Blogs.insert({
            title: tit,
            body: bd
        });
    },
    'removePost': function(id) {
        console.log('req rm ' + id);

        // Delete blog post with given id
        Blogs.remove(id);

    }
});
