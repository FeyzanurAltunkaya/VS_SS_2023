import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "./App.css";
import "./index.css"

import AuthService from "./services/AuthService";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Directories from "./pages/Directories";


// import AuthVerify from "./common/auth-verify";
import EventBus from "./services/EventBus";
import FileUpload from "./pages/FileUpload";
import AddDirectory from "./pages/AddDirectory";
import UploadFiles from "./pages/UploadFiles";
import UsersList from "./pages/UsersList";
import GroupsList from "./pages/GroupsList";

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
                    <div className="navbar-container">

                        <li className="nav-item">
                            <a href="/home" className="nav-link" >
                                Home
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={this.logOut}>
                                LogIn
                            </a>
                        </li>
                    </div>>
                </nav>
                {(currentUser ) ? (
                    <nav className="navbar">

                        <div className="navbar-container">
                            <li className="nav-item">
                                <Link to={"/directories"} className="nav-link" className="files">
                                    MyDirectories
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link to={"/upload"} className="nav-link" className="files">
                                    MyFiles
                                </Link>
                            </li>

                        </div>
                            <div className="navbar-container2">
                            <li className="nav-item">
                                <Link to={"/profile"} className="nav-link">
                                    {currentUser.username}
                                </Link>
                            </li>

                            <li className="nav-item">
                                <a href="/login" className="nav-link" onClick={this.logOut}>
                                    LogOut
                                </a>
                            </li>
                            </div>

                    </nav>

                ) : (
                    <div className="LoginForm"/>
                )}




                <div className="container">
                    <Routes>

                        <Route path="/" element={<Home/>}/>
                        <Route path="/home" element={<Home/>}/>
                        <Route path="/users-list" element={<UsersList/>}/>
                        <Route path="/groups-list" element={<GroupsList/>}/>
                        <Route path="/add-directory" element={<AddDirectory/>}/>
                        <Route path="/directories" element={<Directories/>}/>
                        <Route path="/FileUpload" element={<FileUpload/>}/>
                        <Route path="/upload" element={<UploadFiles/>}/>
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/profile" element={<Profile />} />


                    </Routes>
                </div>

                {/* <AuthVerify logOut={this.logOut}/> */}
            </>
        );
    }
}

export default App;