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
    const username=AuthService.getCurrentUser().username;
    const userId=AuthService.getCurrentUser().id;


    const handleInputChange = event => {
        const { name, value } = event.target;
        setDirectory({ ...directory, [name]: value });
    };

    const saveTutorial = () => {

        const data = {
            directoryName: directory.directoryName,
            user: {id: userId, username: username}
        };

        DirService.createDirectory(directory)
            .then(response => {
                setDirectory({
                    id: response.data.id,
                    directoryName: response.data.directoryName,
                    user:{ id: response.data.user.id, username: response.data.user.username}
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const addUserToDirectory = () => {
        const directoryId = directory.id;

        DirService.createDirectoryByUser(userId, directoryId)
            .then((response) => {
                console.log(response.data);
                // Handle success, e.g., show a success message or update the component state.
            })
            .catch((error) => {
                console.log(error);
                // Handle error, e.g., show an error message or perform additional actions.
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
                     <button className="btn btn-success" onClick={addUserToDirectory}>
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

                    <button onClick={addUserToDirectory} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddDirectory;