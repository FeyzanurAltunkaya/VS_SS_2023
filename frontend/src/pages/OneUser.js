import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import UserListService from "../services/UserListService";

const OneUser = props => {
    const { id }= useParams();
    let navigate = useNavigate();

    const initialUserState = {
        id: null,
        username: "",
        password: ""
    };
    const [currentUser, setCurrentUser] = useState(initialUserState);
    const [message, setMessage] = useState("");

    const getUser = id => {
        UserListService.get(id)
            .then(response => {
                setCurrentUser(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getUser(id);
    }, [id]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setCurrentUser({ ...currentUser, [name]: value });
    };



    const updateTutorial = () => {
        UserListService.update(currentUser.id, currentUser)
            .then(response => {
                console.log(response.data);
                setMessage("The tutorial was updated successfully!");
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteTutorial = () => {
        UserListService.delete(currentUser.id)
            .then(response => {
                console.log(response.data);
                navigate("/users-list");
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div>
            {currentUser ? (
                <div className="edit-form">
                    <h4>User</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="title">Username</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                name="title"
                                value={currentUser.username}
                                onChange={handleInputChange}
                            />

                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                name="password"
                                value={currentUser.password}
                                onChange={handleInputChange}
                            />
                        </div>

                    </form>



                    <button className="badge badge-danger mr-2" onClick={deleteTutorial}>
                        Delete
                    </button>

                    <button
                        type="submit"
                        className="badge badge-success"
                        onClick={updateTutorial}
                    >
                        Update
                    </button>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Please click on a Tutorial...</p>
                </div>
            )}
        </div>
    );
};

export default OneUser;