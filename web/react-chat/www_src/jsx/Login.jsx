var Login = React.createClass({
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
      <div className="jumbotron">
        <form className="form">
          <div className="input-group">
            <input type="text" placeholder="Your name" ref="name" className="form-control" autoFocus></input>
            <span className="input-group-btn">
              <button className="btn btn-success" type="submit" onClick={this.submit}>Start</button>
            </span>
          </div>
        </form>
      </div>
    );
  }
});
