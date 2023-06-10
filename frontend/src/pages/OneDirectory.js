import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import DirService from "../services/DirService";

const OneDirectory = props => {
    const { id }= useParams();
    let navigate = useNavigate();

    const initialDirectoryState = {
        id: null,
        dirname: "",
    };
    const [currentDirectory, setCurrentDirectory] = useState(initialDirectoryState);
    const [message, setMessage] = useState("");

    const getDirectory = id => {
        DirService.get(id)
            .then(response => {
                setCurrentDirectory(response.data);
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

    const updatePublished = status => {
        var data = {
            id: currentDirectory.id,
            dirname: currentDirectory.dirname,

        };

        DirService.update(currentDirectory.id, data)
            .then(response => {
                setCurrentDirectory({ ...currentDirectory, published: status });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
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
        DirService.remove(currentDirectory.id)
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
                                name="title"
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