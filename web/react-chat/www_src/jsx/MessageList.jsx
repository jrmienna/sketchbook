var MessageList = React.createClass({
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
      <div className="list-group">
      
      {this.props.messages.map(message =>
         <Message text={message.text} username={message.username} timestamp={message.timestamp}/>
         )}

        
      </div>
    );
  }
})
