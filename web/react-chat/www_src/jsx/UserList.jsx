var UserList = React.createClass({
  render: function() {
    return (
      <div className="panel panel-default">
        <div className="panel-heading">
          Users
        </div>
        <ul className="list-group">
            {this.props.users.map(user =>
                <li className="list-group-item">
                    <h3>{user.username}</h3>
                </li>
                )}
        </ul>
        <LogoutButton/>
      </div>
    );
  }
});
