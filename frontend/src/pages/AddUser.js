import React, { useState, useEffect } from "react";
import UserListService from "../services/UserListService";
import AuthService from "../services/AuthService";

const AddUser = () => {
    const initialUserState = {
        id: null,
        username: "",
        password: "",
        roles: "",
    };
    const [user, setUser] = useState(initialUserState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    };

    const handleSelectChange = (event) => {
        const { value } = event.target;
        setUser({ ...user, roles: value });
    };

    const saveTutorial = () => {
        const data = {
            id: user.id,
            username: user.username,
            password: user.password,
            roles: user.roles,
        };

        UserListService.register(data.username, data.password)
            .then((response) => {
                setUser({
                    id: response.data.id,
                    username: response.data.username,
                    password: response.data.password,
                    roles: response.data.roles,
                });
                setSubmitted(true);
            })
            .catch((e) => {
                console.log(e);
            });
    };

    const newTutorial = () => {
        setUser(initialUserState);
        setSubmitted(false);
    };

    useEffect(() => {
        if (submitted) {
            saveTutorial();
        }
    }, [submitted]);

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
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            value={user.username}
                            onChange={handleInputChange}
                            name="username"
                        />
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            name="password"
                            value={user.password}
                            onChange={handleInputChange}
                        />
                        <label htmlFor="roles">Role</label>
                        <select
                            className="form-control"
                            id="roles"
                            name="roles"
                            value={user.roles}
                            onChange={handleSelectChange}
                        >
                            <option value="">Select Role</option>
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                            <option value="ROLE_USER">ROLE_USER</option>
                        </select>
                    </div>
                    <button onClick={saveTutorial} className="submitButton">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddUser;
