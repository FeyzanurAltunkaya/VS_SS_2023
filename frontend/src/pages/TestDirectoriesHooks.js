import React, { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import DirService from "../services/DirService";
import { Link } from "react-router-dom";

const TestDirectoriesHooks = () => {
    const [directories, setDirectories] = useState([]);
    const [currentDirectory, setCurrentDirectory] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [searchDirName, setSearchDirName] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        retrieveTutorials();
    }, []);

    const onChangeSearchTitle = e => {
        const searchDirName = e.target.value;
        setSearchDirName(searchDirName);
    };

    const retrieveTutorials = () => {
        DirService.getAll()
            .then(response => {
                setDirectories(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

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
                <h4>Directories</h4>

                <ul className="list-group">
                    {directories &&
                        directories.map((tutorial, index) => (
                            <li
                                className={
                                    "list-group-item " + (index === currentIndex ? "active" : "")
                                }
                                onClick={() => setActiveTutorial(tutorial, index)}
                                key={index}
                            >
                                <Link
                                    to={"/directories/" + tutorial.id}>
                                    {tutorial.directoryName}
                                </Link>

                            </li>
                        ))}
                </ul>

                <div class="buttonsList">
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