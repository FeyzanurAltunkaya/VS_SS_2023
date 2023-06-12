import React, {useState, useEffect, useCallback} from "react";
import {useNavigate} from "react-router-dom";
import DirService from "../services/DirService";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
import UserDirService from "../services/UserDirService";
import dirService from "../services/DirService";
import {response} from "express";

const TestDirectoriesHooks = () => {
    const [directories, setDirectories] = useState([]);
    const [currentDirectory, setCurrentDirectory] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [searchDirName, setSearchDirName] = useState("");
    //const [user, setCurrentUser]=useState();
    const currentUser = AuthService.getCurrentUser().id;
    const username = AuthService.getCurrentUser().username;
    //const currentUsername = user.username;
    console.log(currentUser);

    const navigate = useNavigate();



    const onChangeSearchTitle = e => {
        const searchDirName = e.target.value;
        setSearchDirName(searchDirName);
    };

    useEffect(() => {
        retrieveTutorials();
    }, );

    const retrieveTutorials = () =>{
        dirService.getAll().then(response =>{
            setDirectories(response.data);
        }).catch(e => {
            console.log(e)
        })
    }
/*
    const retrieveTutorials = useCallback(() => {
        DirService.getAllDirectoriesByUser(currentUser)
            .then(response => {
                setDirectories(response.data);
                //setCurrentUser(response.data.user)
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }, [currentUser]);


 */
    useEffect(() => {
        retrieveTutorials();
    }, [retrieveTutorials]);

    const AddDirectory =() =>{
        navigate("/add-directory");
    }
    const refreshList = () => {
        retrieveTutorials();
        setCurrentDirectory(null);
        setCurrentIndex(-1);
    };

    const setActiveTutorial = (tutorial, index) => {
        setCurrentDirectory(tutorial);
        setCurrentIndex(index);
    };

    const removeAllTutorials = () => {
        DirService.removeAll()
            .then(response => {
                console.log(response.data);
                refreshList();
            })
            .catch(e => {
                console.log(e);
            });
    };

    const findByTitle = () => {
        DirService.findByTitle(searchDirName)
            .then(response => {
                setDirectories(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Your Directories</h4>

                <ul className="list-group">
                    {
                        directories.map((tutorial, index) => {
                            console.log(tutorial.user.username);
                            if ( tutorial.user.username === username) {
                                return (
                                    <li
                                        className={
                                            "list-group-item " + (index === currentIndex ? "active" : "")
                                        }
                                        onClick={() => setActiveTutorial(tutorial, index)}
                                        key={index}
                                    >
                                        <Link to={"/directories/" + tutorial.id}>
                                            {tutorial.directoryName}
                                        </Link>
                                    </li>
                                );
                            } else {
                                return null; // Skip rendering directories that do not belong to the current user
                            }
                        }
                        )}
                </ul>

                <div className="buttonsList">
                <button
                    className="m-3 btn btn-sm btn-danger"
                    onClick={removeAllTutorials}
                >
                    Remove All
                </button>

                <button
                    className="m-3 btn btn-sm btn-danger"
                    onClick={AddDirectory}
                >
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