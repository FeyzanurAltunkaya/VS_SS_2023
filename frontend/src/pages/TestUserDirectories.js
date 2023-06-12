import React, { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import DirService from "../services/DirService";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
import UserDirService from "../services/UserDirService";

const TestUserDirectories = () => {
    const [directories, setDirectories] = useState([]);
    //const [currentDirectory, setCurrentDirectory] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    //const [user, setCurrentUser]=useState();
    const currentUser = AuthService.getCurrentUser();

    const navigate = useNavigate();

    useEffect(() => {
        retrieveTutorials();
    }, []);

    const retrieveTutorials = () => {
        UserDirService.getAllDirectoriesOfUser()
            .then(response => {
                setDirectories(response.data);
                //setCurrentUser(response.data.user)
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
        //setCurrentDirectory(null);
        setCurrentIndex(-1);
    };

    const setActiveTutorial = (tutorial, index) => {
        //setCurrentDirectory(tutorial);
        setCurrentIndex(index);
    };



    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Your Directories</h4>

                <ul className="list-group">
                    {directories &&
                        directories.map((tutorial, index) => {
                                return  (tutorial.user === currentUser) &&
                                    <li
                                        className={
                                            "list-group-item " + (index === currentIndex ? "active" : "")
                                        }
                                        onClick={() => setActiveTutorial(tutorial, index)}
                                        key={index}
                                    >
                                        <Link
                                            to={"/directories/" + tutorial.id}>
                                            {tutorial.directory.directoryName}
                                        </Link>

                                    </li>

                            }
                        )}
                </ul>

                    <button
                        className="m-3 btn btn-sm btn-danger"
                        onClick={AddDirectory}
                    >
                        Add
                    </button>

            </div>
        </div>
    );
};

export default TestUserDirectories;