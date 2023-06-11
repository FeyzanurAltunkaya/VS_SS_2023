import React, { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import GroupService from "../services/GroupService";
import { Link } from "react-router-dom";

const GroupsList = () => {
    const [groups, setGroups] = useState([]);
    const [currentGroup, setCurrentGroup] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    const navigate = useNavigate();

    useEffect(() => {
        retrieveGroups();
    }, []);

    const retrieveGroups = () => {
        GroupService.getAllGroups()
            .then(response => {
                setGroups(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const AddGroup =() =>{
        navigate("/add-group");
    }
    const refreshList = () => {
        retrieveGroups();
        setCurrentGroup(null);
        setCurrentIndex(-1);
    };

    const setActiveGroup = (tutorial, index) => {
        setCurrentGroup(tutorial);
        setCurrentIndex(index);
    };

    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Groups</h4>

                <ul className="list-group">
                    {groups &&
                        groups.map((group, index) => (
                            <li
                                className={
                                    "list-group-item " + (index === currentIndex ? "active" : "")
                                }
                                onClick={() => setActiveGroup(group, index)}
                                key={index}
                            >
                                <Link
                                    to={"/group/" + group.id}>
                                    {group.name}
                                </Link>

                            </li>
                        ))}
                </ul>

                <button
                    className="m-3 btn btn-sm btn-danger"
                    onClick={AddGroup}
                >
                    Add
                </button>

            </div>
            <div className="col-md-6">
                {currentGroup ? (
                    <div>
                        <h4>Groups</h4>
                        <div>
                            <label>
                                <strong>Group:</strong>
                            </label>{" "}
                            {currentGroup.name}
                        </div>

                    </div>
                ) : (
                    <div>
                        <br />
                        <p>click on a Group to edit...</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default GroupsList;
