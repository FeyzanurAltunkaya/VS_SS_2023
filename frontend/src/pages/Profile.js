import React, { Component } from "react";
import {Link, Navigate} from "react-router-dom";

import AuthService from "../services/AuthService";

export default class Profile extends Component {
    constructor(props) {
        super(props);

        this.state = {
            redirect: null,
            userReady: false,
            currentUser: { username: "" }
        };
    }

    componentDidMount() {
        const currentUser = AuthService.getCurrentUser();

        if (!currentUser) this.setState({ redirect: "/home" });
        this.setState({ currentUser: currentUser, userReady: true })
    }

    render() {
        if (this.state.redirect) {
            return <Navigate to={this.state.redirect} />
        }

        const { currentUser } = this.state;

        return (
            <>
            {(this.state.userReady) ?
                <div>
                    <header className="jumbotron">
                        <h3>
                            <strong>{currentUser.username}</strong> Profile
                        </h3>
                    </header>
                    <ul>
                        <li>
                            <strong>Token:</strong>{" "}
                            {currentUser.accessToken.substring(0, 20)} ...{" "}
                            {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
                        </li>
                        <li>
                            <strong>Id:</strong>{" "}
                            {currentUser.id}
                        </li>
                        <li>
                            <strong>Authorities:</strong>
                            <ul>
                                {currentUser.roles &&
                                    currentUser.roles.map((role, index) => <li key={index}>{role}</li>)

                                }

                            </ul>

                        </li>


                    </ul>


                    {currentUser.roles &&
                        currentUser.roles.map((role) =>  role == "ROLE_ADMIN" ?
                            <div>
                                <h2>Hello Admin</h2>
                            <Link to="/users-list">
                                Manage Users
                            </Link>
                            </div>: <h2>Hello User</h2>)
                    }


                </div>: null}

            </>

        );
    }
}