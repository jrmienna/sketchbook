if (Meteor.isClient) {

    Template.example.rendered = function() {
        var mainContext = famous.Engine.createContext();

        var firstSurface = new Surface({
            content: 'Example surface',
            size: [200, 200],
            properties: {
                backgroundColor: 'red',
                textAlign: 'center',
                padding: '5px',
                border: '2px solid blue',
                marginTop: '50px'
            }
        });

        mainContext.add(firstSurface);
    };

    Template.example.helpers({

    });

    Template.example.events({

    });
}

if (Meteor.isServer) {
    Meteor.startup(function() {
        // code to run on server at startup
    });
}
