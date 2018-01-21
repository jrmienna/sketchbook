/** @jsx React.DOM */
var Header = React.createClass({displayName: 'Header',
  render : function () {
    return React.DOM.h1(null, this.props.title)
  }
})

/** @jsx React.DOM */
var Login = React.createClass({displayName: 'Login',
  submit: function (e) {
    e.preventDefault();
    
    var username = this.refs.name.getDOMNode().value.trim();
    
    if (!username) {
      return;
    }
    
    //create user here
    emit("newUser", username);

  },
  render: function() {
    return (
      React.DOM.div({className: "jumbotron"}, 
        React.DOM.form({className: "form"}, 
          React.DOM.div({className: "input-group"}, 
            React.DOM.input({type: "text", placeholder: "Your name", ref: "name", className: "form-control", autoFocus: true}), 
            React.DOM.span({className: "input-group-btn"}, 
              React.DOM.button({className: "btn btn-success", type: "submit", onClick: this.submit}, "Start")
            )
          )
        )
      )
    );
  }
});

/** @jsx React.DOM */
var LogoutButton = React.createClass({displayName: 'LogoutButton',
  logout: function (e) {
    e.preventDefault();
    localStorage.removeItem('user');
    window.location.reload();
  },
  render: function() {
    return (
      React.DOM.div({className: "clearfix"}, 
        React.DOM.button({onClick: this.logout, className: "btn btn-danger pull-right"}, "Log out")
      )
    );
  }
});

/** @jsx React.DOM */
var Message = React.createClass({displayName: 'Message',
  render : function () {
    return (
      React.DOM.div({className: "list-group-item"}, 
        React.DOM.h4({className: "list-group-item-heading"}, 
        
        this.props.text
          
        ), 
        React.DOM.strong({className: "list-group-item-text"}, 
        
        this.props.username
          
        ), 
        " • ", 
        React.DOM.span({className: "list-group-item-text text-muted"}, 
        
        this.props.timestamp
          
        )
      )
    );
  }
})

/** @jsx React.DOM */
var MessageBoard = React.createClass({displayName: 'MessageBoard',
  render : function () {
    return (
      React.DOM.div({className: "panel panel-default"}, 
        React.DOM.div({className: "panel-heading"}, 
          "Messages"
        ), 
        
        MessageList({messages: this.props.messages}), 
        
        React.DOM.div({className: "panel-footer"}, 
        
        MessageInput(null)
          
        )
      )
    );
  }
})

/** @jsx React.DOM */
var MessageInput = React.createClass({displayName: 'MessageInput',
  submit : function  (e) {
    e.preventDefault();
    var message = this.refs.message.getDOMNode().value.trim();
    if (!message) {
      return;
    }
    //publishMessage to server
    emit('publishMessage', message)
    //clear the input element
    this.refs.message.getDOMNode().value = ''; 
  },
  render : function () {
    return (
      React.DOM.form({className: "form"}, 
        React.DOM.div({className: "input-group"}, 
          React.DOM.input({type: "text", className: "form-control", placeholder: "Your message", ref: "message", autoFocus: true}), 
          React.DOM.span({className: "input-group-btn"}, 
            React.DOM.button({className: "btn btn-success", type: "submit", onClick: this.submit}, "Send")
          )
        )
      )
    );
  }
})

/** @jsx React.DOM */
var MessageList = React.createClass({displayName: 'MessageList',
    scrollToBottom: function(){
    var elem = this.getDOMNode();
    elem.scrollTop = elem.scrollHeight;
    },
    componentDidMount: function() {
        this.scrollToBottom();
    },
    componentDidUpdate: function() {
        this.scrollToBottom();
    },
    render : function () {
    return (
      React.DOM.div({className: "list-group"}, 
      
      this.props.messages.map(function(message) 
         {return Message({text: message.text, username: message.username, timestamp: message.timestamp});}
         )

        
      )
    );
  }
})

/** @jsx React.DOM */
var UserList = React.createClass({displayName: 'UserList',
  render: function() {
    return (
      React.DOM.div({className: "panel panel-default"}, 
        React.DOM.div({className: "panel-heading"}, 
          "Users"
        ), 
        React.DOM.ul({className: "list-group"}, 
            this.props.users.map(function(user) 
                {return React.DOM.li({className: "list-group-item"}, 
                    React.DOM.h3(null, user.username)
                );}
                )
        ), 
        LogoutButton(null)
      )
    );
  }
});

/** @jsx React.DOM */
window.emit = null;

window.onload = function(){ 
  
  var socket = io();

  var messages = [];
  var me = {};
    
  var username = localStorage.getItem('user');
  if(username) {
    socket.emit('newUser', username);
  } else {
    React.renderComponent(Login(null), document.querySelector("#Login"));
  }
  React.renderComponent(Header({title: "React chat"}), document.querySelector("#Header"));
  React.renderComponent(Login(null), document.querySelector("#Login"));

  socket.on('welcome', function(user){

    me = user;
    console.log("welcome", user);
    localStorage.setItem('user', user.username);

    React.unmountComponentAtNode(document.querySelector("#Login"))
    React.renderComponent(MessageBoard(null), document.querySelector("#MessageBoard"));
  });  

  socket.on('users', function(data){
    console.log("users", data);
    React.renderComponent(UserList({users: data}), document.querySelector("#UserList"));
  });

  socket.on('messages', function(data){
    console.log("messages", data);
    React.renderComponent(MessageBoard({messages: data}), document.querySelector("#MessageBoard"));
  });
     
  window.emit = function(event, data){
    socket.emit(event, data);
  }
  
}
