import React, { useState } from "react";
import UserDirService from "../services/UserDirService";
import AuthService from "../services/AuthService";


const AddUserDirectory = () => {
    const initialDirectoryState = {
        id: null,
        directory: {},
        user: {}

    };
    const [userDirectory, setUserDirectory] = useState(initialDirectoryState);
    const [submitted, setSubmitted] = useState(false);


    const handleInputChange = event => {
        const { name, value } = event.target;
        setUserDirectory({ ...userDirectory, [name]: value });
    };

    const saveTutorial = () => {
        const username=AuthService.getCurrentUser().username;
        const id=AuthService.getCurrentUser().id;
        const data = {
            directory: userDirectory.directory,
            user: userDirectory.user
        };

        UserDirService.createUserDirectory(data)
            .then(response => {
                setUserDirectory({
                    id: response.data.id,
                    directory: response.data.directory,
                    user: response.data.user
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const newTutorial = () => {
        setUserDirectory(initialDirectoryState);
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
                            //id="title"
                            value={userDirectory.directory.directoryName}
                            name="directoryName"
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

export default AddUserDirectory;