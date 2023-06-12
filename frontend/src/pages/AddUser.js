import React, { useState } from "react";
import UserListService from "../services/UserListService";
import AuthService from "../services/AuthService";


const AddUser = () => {
    const initialUserState = {
        id: null,
        username: "",
        password: "",
        roles: [],
    };
    const [user, setUser] = useState(initialUserState);
    const [submitted, setSubmitted] = useState(false);
    const [selectValue , setSelectValue] = useState();

    const handleInputChange = event => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    };

    const handleSelectChange = event =>{
        const value = event.target.value;
        setSelectValue(value);
    }

    const saveTutorial = () => {
        const data = {
            id: user.id,
            username: user.username,
            password: user.password,
            roles: user.roles
        };

        UserListService.create(data)
            .then(response => {
                setUser({
                    id: response.data.id,
                    username: response.data.username,
                    password: response.data.password,
                    roles: response.data.roles
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
                            name="username"
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


                        <select onChange={handleInputChange} className="form-control" >
                            <option defaultValue disabled> Select Role</option>
                            <option value={user.roles} name="roles">ROLE_ADMIN</option>
                            <option value={user.roles} name="roles">ROLE_USER</option>
                        </select>
                        {/*selectValue && <h3>{selectValue}</h3>*/}
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