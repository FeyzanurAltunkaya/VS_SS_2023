
import React, { Component } from "react";
import UserListService from "../services/UserListService";
import { Link } from "react-router-dom";
import "../index.css"


export default class UsersList extends Component {
    constructor(props) {
        super(props);

        this.retrieveUsers = this.retrieveUsers.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveUser = this.setActiveUser.bind(this);
        //this.searchUserName = this.searchUserName.bind(this);


        this.state = {
            users: [],
            currentUser: null,
            currentIndex: -1,
            //username: ""
        };
    }

    componentDidMount() {
        this.retrieveUsers();
    }



    retrieveUsers() {
        UserListService.getAllUsers()
            .then(response => {
                this.setState({
                    directories: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveUsers();
        this.setState({
            currentUser: null,
            currentIndex: -1
        });
    }

    setActiveUser(user, index) {
        this.setState({
            currentUser: user,
            currentIndex: index
        });
    }

/*
    searchUserName() {
        this.setState({
            currentUser: null,
            currentIndex: -1
        });

        UserListService.findByTitle(this.state.username)
            .then(response => {
                this.setState({
                    users: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

 */



    render() {
        const { //username,
              users, currentUser, currentIndex } = this.state;

        return (
            <div className="list row">
                <div className="col-md-8">
                    {/*
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search by title"
                            value={dirName}
                            onChange={this.onChangeSearchDirName}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.searchDirName}
                            >
                                Search
                            </button>

                           <Link to={"/add-directory"}>
                               Add
                           </Link>
                        </div>

                    </div>
                    */}
                </div>
                <div className="directories">
                    <h4>Users List</h4>

                    <ul className="directoriesList">
                        {users &&
                            users.map((user, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveUser(user, index)}
                                    key={index}
                                >
                                    {user.username}
                                </li>
                            ))}
                    </ul>


                </div>
                <div className="col-md-6">
                    {currentUser ? (
                        <div>
                            <h4>Directory</h4>
                            <Link
                                to={"directory/" + currentUser.id}
                                className="badge badge-warning"
                            >
                                {currentUser.username}
                            </Link>
                        </div>


                    ) : (
                        <div>
                            <br />
                            <p>Please click on a User...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}