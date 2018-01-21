Draggables = new Meteor.Collection('draggables');
Dropzones = new Meteor.Collection('dropzones');

if (Meteor.isClient) {

    Template.dropzone.events({
        'dblclick .dropzone': function(evnt, templ) {
            evnt.preventDefault();
            evnt.stopPropagation();
            var id = Draggables.insert({
                title: 'New Draggable',
                left: (evnt.pageX - 100) + 'px',
                top: (evnt.pageY - 20) + 'px'
            });
            console.log(id);
            Session.set('editing', id);
        }
    });

    Template.draggablePanel.rendered = function() {
        $('.draggable').draggable({
            cursor: 'move',
            start: function(evnt, ui) {
                Session.set('dragging', $(this).attr('id'));
                $('.dropzone').addClass('dragging');
            },
            stop: function(evnt, ui) {
                var left = ui.position.left;
                var top = ui.position.top;
                Draggables.update($(this).attr('id'), {$set: {left: left+'px', top: top+'px'}});
                Session.set('dragging', '');
                $('.dropzone').removeClass('dragging');
            },
            revert: function(evnt, ui) {

            }
        });

        $('.resizable').resizable({
            minHeight: 10,
            minWidth: 10
        });
    };

    Template.draggablePanel.events({
        'click .close': function(evnt, templ) {
            evnt.preventDefault();
            evnt.stopPropagation();
            Draggables.remove({_id: this._id});
        },
        'dblclick .panel-title': function(evnt, templ) {
            evnt.preventDefault();
            if(Session.get('editing') !== this._id) {
                Session.set('editing', this._id);
            }
        },
        'focus .draggable': function(evnt, templ) {
            console.log("focus fire!");
        }
    });

    Template.home.helpers({
        draggable: function() {
            return Draggables.find();
        }
    });
}

if (Meteor.isServer) {
    Meteor.startup(function () {
        // code to run on server at startup
    });
}
