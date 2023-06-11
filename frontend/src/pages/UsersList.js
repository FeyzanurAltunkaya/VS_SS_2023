
import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import UserListService from "../services/UserListService";
import { Link } from "react-router-dom";
import "../index.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserPen} from "@fortawesome/free-solid-svg-icons";





const UsersList = () => {


    const [users , setUsers] = useState();
    const [currentUser, setCurrentUser] = useState();
    const [currentIndex, setCurrentIndex] = useState(-1);

    const navigate = useNavigate();

    useEffect(() => {
        retrieveUsers();
    }, []);



    const retrieveUsers = () => {
        UserListService.getAll()
            .then(response => {
                setUsers(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const AddUser =() =>{
        navigate("/add-user");
    }
    const refreshList = () => {
        retrieveUsers();
        setCurrentUser(null);
        setCurrentIndex(-1);
    };

    const setActiveTutorial = (tutorial, index) => {
        setCurrentUser(tutorial);
        setCurrentIndex(index);
    };

    const deleteUser = () =>{
        navigate("/edit-user");
    }

    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Users</h4>

                <ul className="list-group">
                    {users &&
                        users.map((user, index) => (
                            <li
                                className={
                                    "list-group-item " + (index === currentIndex ? "active" : "")
                                }
                                onClick={() => setActiveTutorial(user, index)}
                                key={index}
                            >
                                <Link
                                    to={"/users/" + user.id}>
                                    {user.username}
                                </Link>

                                <div className="icons">

                                    <Link to={"/edit-user"}>
                                        <FontAwesomeIcon icon={faUserPen} className="edit-icon"/>
                                    </Link>



                                </div>

                            </li>
                        ))}
                </ul>


                <button
                    className="m-3 btn btn-sm btn-danger"
                    onClick={AddUser}
                >
                    Add
                </button>
            </div>
            <div className="col-md-6">
                {currentUser ? (
                    <div>
                        <h4>User</h4>
                        <div>
                            <label>
                                <strong>Title:</strong>
                            </label>{" "}
                            {currentUser.username}
                        </div>

                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Tutorial...</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default UsersList;