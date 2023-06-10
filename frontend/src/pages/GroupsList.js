
import React, { Component } from "react";
import GroupService from "../services/GroupService";
import { Link } from "react-router-dom";
import "../index.css"


export default class GroupsList extends Component {
    constructor(props) {
        super(props);

        this.retrieveGroups = this.retrieveGroups.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveGroup = this.setActiveGroup.bind(this);
        //this.searchUserName = this.searchUserName.bind(this);


        this.state = {
            groups: [],
            currentGroup: null,
            currentIndex: -1,
            //username: ""
        };
    }

    componentDidMount() {
        this.retrieveGroups();
    }



    retrieveGroups() {
        GroupService.getAllGroups()
            .then(response => {
                this.setState({
                    groups: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveGroups();
        this.setState({
            currentGroup: null,
            currentIndex: -1
        });
    }

    setActiveGroup(group, index) {
        this.setState({
            currentGroup: group,
            currentIndex: index
        });
    }

    /*
        searchUserName() {
            this.setState({
                currentUser: null,
                currentIndex: -1
            });

            UserListService.findByTitle(this.state.username)
                .then(response => {
                    this.setState({
                        users: response.data
                    });
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        }

     */



    render() {
        const { //groupname,
            groups, currentGroup, currentIndex } = this.state;

        return (
            <div className="list row">
                <div className="col-md-8">
                    {/*
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search by title"
                            value={dirName}
                            onChange={this.onChangeSearchDirName}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.searchDirName}
                            >
                                Search
                            </button>

                           <Link to={"/add-directory"}>
                               Add
                           </Link>
                        </div>

                    </div>
                    */}
                </div>
                <div className="directories">
                    <h4>Groups List</h4>

                    <ul className="directoriesList">
                        {groups &&
                            groups.map((group, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveGroup(group, index)}
                                    key={index}
                                >
                                    {group.groupname}
                                </li>
                            ))}
                    </ul>


                </div>
                <div className="col-md-6">
                    {currentGroup ? (
                        <div>
                            <h4>Directory</h4>
                            <Link
                                to={"directory/" + currentGroup.id}
                                className="badge badge-warning"
                            >
                                {currentGroup.username}
                            </Link>
                        </div>


                    ) : (
                        <div>
                            <br />
                            <p>Please click on a Group...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}