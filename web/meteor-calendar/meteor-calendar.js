Events = new Mongo.Collection('events');
Rooms = new Mongo.Collection('rooms');

if (Meteor.isClient) {

    Meteor.subscribe('events');
    Meteor.subscribe('rooms');

    Router.configure({
        layoutTemplate: 'layout',
        notFoundTemplate: 'notFound',
        loadingTemplate: 'loading'
    });

    Router.onBeforeAction('loading');

    Router.map(function() {
        this.route('home', {
            path: '/',
            template: 'home'
        });
        this.route('schedule', {
            path: '/schedule/:_id',
            template: 'calendar',
            data: function() {
                return Rooms.findOne({_id: this.params._id});
            }
        });
        this.route('notFound', {
            path: '*',
            template: '404'
        });
    });

    Session.setDefault("lastMod", null);


    Template.calendar.helpers({
        lastMod: function(){
            return Session.get("lastMod");
        }
    });

    Template.calendar.rendered = function() {
        $('#calendar').fullCalendar('render');

        $('#calendar').fullCalendar({
            allDaySlot: false,
            defaultView: 'agendaWeek',
            weekNumbers: true,
            firstDay: 1,
            minTime: '06:00:00',
            timeFormat: 'H(:mm)',
            theme: false,
            themeButtonIcons: {
                prev: 'ui button',
                next: 'ui button',
                prevYear: 'ui button',
                nextYear: 'ui button'
            },
            events: function(start, end, timezone, callback) {
                var events = [];

                Events.find().forEach(function(event)  {
                    events.push({
                        id: event._id,
                        title: event.title,
                        start: event.start,
                        end: event.end
                    });
                });
                callback(events);
            },
            eventClick: function(calEvent, jsEvent, view) {
                alert(calEvent.title);
            },
            dayClick: function(date, allDay, jsEvent, view)  {
                Events.insert({
                    title: "New Event",
                    start: date.format(),
                    end: date.format()
                });
                Session.set("lastMod", new Date());
            }
        });
    }

    Template.card2.helpers({
        roomId: function() {
            return 1;
        },
        capacity: function() {
            return 10;
        },
        equiptment: function() {
            return [
                {type: "TV", count: 1},
                {type: "Prosjektor", count: 1},
                {type: "Tavle", count: 1}
            ];
        },
        booked: function(){
            // TODO: return status of room - is it booked or not?
            return false;
        },
        schedule: function() {
            // TODO: return room schedule
        },
        nextReservationStart: function() {
            return "17:30";
        },
        nextReservationEnd: function() {
            return "18:00";
        }
    });

    Template.card2.events({
        "click .wizard": function(event, template){
            console.log(template);
            $(template.firstNode).transition('slide right');
            $(template.firstNode).transition('slide right').delay(8000);
        }
    });


}

if (Meteor.isServer) {
    Meteor.publish('rooms', function() {
        return Rooms.find();
    });
    Meteor.publish('events', function() {
        return Events.find();
    });

    Meteor.startup(function() {
        // code to run on server at startup
    });
}
