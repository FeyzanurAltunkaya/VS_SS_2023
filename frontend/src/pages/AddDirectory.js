import React, { useState } from "react";
import DirService from "../services/DirService";
import AuthService from "../services/AuthService";


const AddDirectory = () => {
    const initialDirectoryState = {
        id: null,
        directoryName: "",
        user : {id: null, username: ""},

    };
    const [directory, setDirectory] = useState(initialDirectoryState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setDirectory({ ...directory, [name]: value });
    };

    const saveTutorial = () => {
        const username=AuthService.getCurrentUser().username;
        const id=AuthService.getCurrentUser().id;
        const data = {
            directoryName: directory.directoryName,
            user: {id: id, username: username}
        };

        DirService.create(data)
            .then(response => {
                setDirectory({
                    id: response.data.id,
                    directoryName: response.data.directoryName,
                    user:data.user
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const newTutorial = () => {
        setDirectory(initialDirectoryState);
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
                            value={directory.directoryName}
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

export default AddDirectory;