
import React, { Component } from "react";
import DirService from "../services/DirService";
import { Link } from "react-router-dom";


export default class Directories extends Component {
    constructor(props) {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.retrieveDirectories = this.retrieveDirectories.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveDirectory = this.setActiveDirectory.bind(this);
        this.searchTitle = this.searchTitle.bind(this);


        this.state = {
            directories: [],
            currentDirectory: null,
            currentIndex: -1,
            searchTitle: ""
        };
    }

    componentDidMount() {
        this.retrieveDirectories();
    }

    onChangeSearchTitle(e) {
        const searchTitle = e.target.value;

        this.setState({
            searchTitle: searchTitle
        });
    }


    retrieveDirectories() {
        DirService.getAllDirectories()
            .then(response => {
                this.setState({
                    directories: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveDirectories();
        this.setState({
            currentDirectory: null,
            currentIndex: -1
        });
    }

    setActiveDirectory(directory, index) {
        this.setState({
            currentDirectory: directory,
            currentIndex: index
        });
    }


    searchTitle() {
        this.setState({
            currentDirectory: null,
            currentIndex: -1
        });

        DirService.findByTitle(this.state.searchTitle)
            .then(response => {
                this.setState({
                    directories: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }



    render() {
        const { searchTitle, directories, currentDirectory, currentIndex } = this.state;

        return (
            <div className="list row">
                <div className="col-md-8">
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search by title"
                            value={searchTitle}
                            onChange={this.onChangeSearchTitle}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.searchTitle}
                            >
                                Search
                            </button>

                           <Link to={"/add-directory"}>
                               Add
                           </Link>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <h4>Tutorials List</h4>

                    <ul className="list-group">
                        {directories &&
                            directories.map((directory, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveDirectory(directory, index)}
                                    key={index}
                                >
                                    {directory.dirName}
                                </li>
                            ))}
                    </ul>


                </div>
                <div className="col-md-6">
                    {currentDirectory ? (
                        <div>
                            <h4>Directory</h4>
                            <Link
                                to={"directory/" + currentDirectory.id}
                                className="badge badge-warning"
                            >
                                {currentDirectory.dirName}
                            </Link>
                        </div>


                    ) : (
                        <div>
                            <br />
                            <p>Please click on a Tutorial...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}