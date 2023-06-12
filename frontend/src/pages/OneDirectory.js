import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import DirService from "../services/DirService";
import AuthService from "../services/AuthService";

const OneDirectory = props => {
    const { id }= useParams();
    let navigate = useNavigate();

    const initialDirectoryState = {
        id: null,
        dirname: "",
        user: {id: null,
            username : ""}
    };
    const [currentDirectory, setCurrentDirectory] = useState(initialDirectoryState);
    const [message, setMessage] = useState("");

    const userId = AuthService.getCurrentUser().id;
    const userName = AuthService.getCurrentUser().username;

    const getDirectory = id => {

        const data = {
            dirname: currentDirectory.dirname,
            user: { id: userId,
                    username: userName}
        }
        DirService.getOneDirectoryOfUser(userId, id)
            .then(response => {
                setCurrentDirectory({
                    id: response.data.id,
                    dirname: response.data.dirname,
                    user: response.data.user
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getDirectory(id);
    }, [id]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setCurrentDirectory({ ...currentDirectory, [name]: value });
    };

    const updateTutorial = () => {
        DirService.update(currentDirectory.id, currentDirectory)
            .then(response => {
                console.log(response.data);
                setMessage("The tutorial was updated successfully!");
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteTutorial = () => {
        DirService.deleteOneDirectoryOfUser(userId , id)
            .then(response => {
                console.log(response.data);
                navigate("/tutorials");
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div>
            {currentDirectory ? (
                <div className="edit-form">
                    <h4>Directory</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="title">Directory name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                name="dirname"
                                value={currentDirectory.dirname}
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
                    <p>Please click on a Directory...</p>
                </div>
            )}
        </div>
    );
};

export default OneDirectory;