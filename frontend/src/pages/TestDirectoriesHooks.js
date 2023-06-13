import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import DirService from "../services/DirService";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
import { FaTrash, FaEdit } from "react-icons/fa";

const TestDirectoriesHooks = () => {
    const [directories, setDirectories] = useState([]);
    const [currentDirectory, setCurrentDirectory] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    const currentUser = AuthService.getCurrentUser().id;
    const navigate = useNavigate();

    useEffect(() => {
        retrieveDirectories();
    }, []);

    const retrieveDirectories = () => {
        DirService.getDirectoriesByUserId(currentUser)
            .then((response) => {
                setDirectories(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getOneDirectory = (directoryId) => {
        DirService.getOneDirectoryByOneUser(currentUser, directoryId)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const addUserToDirectory = (directoryId) => {
        DirService.addUserToDirectory(currentUser, directoryId)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const createDirectory = (directory) => {
        DirService.createDirectory(directory)
            .then((response) => {
                console.log(response.data);
                retrieveDirectories();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const updateDirectory = (directoryId, directory) => {
        DirService.updateDirectory(directoryId, directory)
            .then((response) => {
                console.log(response.data);
                retrieveDirectories();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteDirectory = (directoryId) => {
        DirService.deleteDirectory(directoryId)
            .then((response) => {
                console.log(response.data);
                retrieveDirectories();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const setActiveDirectory = (directory, index) => {
        setCurrentDirectory(directory);
        setCurrentIndex(index);
    };

    const addDirectory = () => {
        navigate("/add-directory");
    };

    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Your Directories</h4>

                <ul className="list-group">
                    {directories.map((directory, index) => (
                        <li
                            className={
                                "list-group-item " + (index === currentIndex ? "active" : "")
                            }
                            onClick={() => setActiveDirectory(directory, index)}
                            key={index}
                        >
                            <Link to={"/directories/" + directory.id}>
                                {directory.directoryName}
                            </Link>
                            <div className="Listicons">
                                <FaTrash
                                    className="icon"
                                    onClick={() => deleteDirectory(directory.id)}
                                />
                                <FaEdit
                                    className="icon"
                                    onClick={() => updateDirectory(directory.id, directory)}
                                />
                            </div>
                        </li>
                    ))}
                </ul>

                <div className="buttonsList">
                    <button className="m-3 btn btn-sm btn-danger" onClick={addDirectory}>
                        Add
                    </button>
                </div>
            </div>
            <div className="col-md-6">
                {currentDirectory ? (
                    <div>
                        <h4>Tutorial</h4>
                        <div>
                            <label>
                                <strong>Title:</strong>
                            </label>{" "}
                            {currentDirectory.directoryName}
                        </div>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>click on a Directory to edit...</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default TestDirectoriesHooks;
