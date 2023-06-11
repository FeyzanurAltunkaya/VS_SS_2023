import React, { useState } from "react";
import GroupService from "../services/GroupService";


const AddGroup = () => {
    const initialDirectoryState = {
        id: null,
        name: "",
        users: [{}]
    };
    const [group, setGroup] = useState(initialDirectoryState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setGroup({ ...group, [name]: value });
    };

    const saveTutorial = () => {
        const data = {
            name: group.name,
            users: group.users
        };

        GroupService.create(data)
            .then(response => {
                setGroup({
                    id: response.data.id,
                    name: response.data.name,
                    users: response.data.users
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const newTutorial = () => {
        setGroup(initialDirectoryState);
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
                        <label htmlFor="title">Title</label>
                        <input
                            type="text"
                            className="form-control"
                            id="title"
                            value={group.name}
                            onChange={handleInputChange}
                            name="name"
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

export default AddGroup;