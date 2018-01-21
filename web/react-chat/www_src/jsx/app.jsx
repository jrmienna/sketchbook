window.emit = null;

window.onload = function(){ 
  
  var socket = io();

  var messages = [];
  var me = {};
    
  var username = localStorage.getItem('user');
  if(username) {
    socket.emit('newUser', username);
  } else {
    React.renderComponent(<Login/>, document.querySelector("#Login"));
  }
  React.renderComponent(<Header title="React chat"/>, document.querySelector("#Header"));
  React.renderComponent(<Login/>, document.querySelector("#Login"));

  socket.on('welcome', function(user){

    me = user;
    console.log("welcome", user);
    localStorage.setItem('user', user.username);

    React.unmountComponentAtNode(document.querySelector("#Login"))
    React.renderComponent(<MessageBoard/>, document.querySelector("#MessageBoard"));
  });  

  socket.on('users', function(data){
    console.log("users", data);
    React.renderComponent(<UserList users={data}/>, document.querySelector("#UserList"));
  });

  socket.on('messages', function(data){
    console.log("messages", data);
    React.renderComponent(<MessageBoard messages={data}/>, document.querySelector("#MessageBoard"));
  });
     
  window.emit = function(event, data){
    socket.emit(event, data);
  }
  
}
