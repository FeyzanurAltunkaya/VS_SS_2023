import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "./App.css";
import "./index.css"
import GroupService from "./services/GroupService";
import AuthService from "./services/AuthService";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import AddGroup from "./pages/AddGroup"
//import Directories from "./pages/Directories";


// import AuthVerify from "./common/auth-verify";
import EventBus from "./services/EventBus";
import AddDirectory from "./pages/AddDirectory";
import UploadFiles from "./pages/UploadFiles";
import UsersList from "./pages/UsersList";
import GroupsList from "./pages/GroupsList";
import TestDirectoriesHooks from "./pages/TestDirectoriesHooks";
import OneDirectory from "./pages/OneDirectory";
import OneUser from "./pages/OneUser";
import AddUser from "./pages/AddUser";
import TestUserDirectories from "./pages/TestUserDirectories";
import AddUserDirectory from "./pages/AddUserDirectory";


class App extends Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            showAdminBoard: false,
            currentUser: undefined,
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();

        if (user) {
            this.setState({
                currentUser: user,
                showAdminBoard: user.roles.includes("ROLE_ADMIN"),
            });
        }

        EventBus.on("logout", () => {
            this.logOut();
        });
    }

    componentWillUnmount() {
        EventBus.remove("logout");
    }

    logOut() {
        AuthService.logout();
        this.setState({
            showAdminBoard: false,
            currentUser: undefined,
        });
    }

    render() {
        const { currentUser } = this.state;

        return (
            <>

                <nav className="navbar">
                    <Link to={"/"} className="navbar-brand">
                        MyFilesFilter
                    </Link>
                    <div className="navbar-container2">


                            <a href="/home" className="nav-link" >
                                Home
                            </a>

                            <a href="/login" className="nav-link" onClick={this.logOut}>
                                LogIn
                            </a>

                    </div>
                </nav>
                {(currentUser ) ? (
                    <nav className="navbar">

                        <div className="navbar-container">
                            <div>
                                <Link to={"/directories"} className="nav-link" >
                                    MyDirectories
                                </Link>
                            </div>

                            <div>
                            <a href="/upload" className="nav-link" >
                                MyFiles
                            </a>
                            </div>

                        </div>
                            <div className="navbar-container2">

                                <Link to={"/profile"} className="nav-link">
                                    {currentUser.username}
                                </Link>

                                <a href="/login" className="nav-link" onClick={this.logOut}>
                                    LogOut
                                </a>

                            </div>

                    </nav>

                ) : (
                    <div className="LoginForm"/>
                )}




                <div className="container">
                    <Routes>

                        <Route path="/" element={<Home/>}/>
                        <Route path="/home" element={<Home/>}/>
                        <Route path="/users/:id" element={<OneUser/>}/>
                        <Route path="/add-user" element={<Register/>}/>
                        <Route path="/users-list" element={<UsersList/>}/>
                        <Route path="/groups-list" element={<GroupsList/>}/>
                        {/*<Route path="/directories" element={<TestDirectoriesHooks/>}/>*/}
                        <Route path="/directories" element={<TestDirectoriesHooks/>}/>
                        <Route path="/directories/:id" element={<OneDirectory/>}/>
                        <Route path="/add-directory" element={<AddDirectory/>}/>
                        <Route path="/upload" element={<UploadFiles/>}/>
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/profile" element={<Profile />} />
                        <Route path="/add-group" element={<AddGroup />} />



                    </Routes>
                </div>

                {/* <AuthVerify logOut={this.logOut}/> */}
            </>
        );
    }
}

export default App;