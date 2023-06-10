import React, { useState } from "react";
import UserListService from "../services/UserListService";
import UserService from "../services/UserService";


const AddUser = () => {
    const initialUserState = {
        id: null,
        username: "",
        password: "",
    };
    const [user, setUser] = useState(initialUserState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    };

    const saveTutorial = () => {
        const data = {
            username: user.username,
            password: user.password

        };

        UserListService.create(data)
            .then(response => {
                setUser({
                    id: response.data.id,
                    username: response.data.username,
                    password: response.data.password
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const newTutorial = () => {
        setUser(initialUserState);
        setSubmitted(false);
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newTutorial}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="title">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            id="title"
                            value={user.username}
                            onChange={handleInputChange}
                            //name="title"
                        />
                        <label htmlFor="title">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            name="password"
                            value={user.password}
                            onChange={handleInputChange}
                        />
                    </div>


                    <button onClick={saveTutorial} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddUser;