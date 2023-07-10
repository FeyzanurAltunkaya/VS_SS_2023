import React, {Component} from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faFolder, faShare, faTrash, faUpload} from "@fortawesome/free-solid-svg-icons";
import DirService from "../services/DirService";
import {withRouter} from "../services/withRouter";


class Directory extends Component{

    constructor(props) {
        super(props);
        this.onChangeDirName = this.onChangeDirName.bind(this);
        this.getDirectory = this.getDirectory.bind(this);
        this.updateDirectory = this.updateDirectory.bind(this);
        this.deleteDirectory = this.deleteDirectory.bind(this);

        this.state = {
            currentDirectory: {
                id: null,
                dirName: "",
            },
            message: ""
        };
    }


    componentDidMount() {
        this.getDirectory(this.props.router.params.id);
    }

    onChangeDirName(e) {
        const dirName = e.target.value;

        this.setState(function(prevState) {
            return {
                currentDirectory: {
                    ...prevState.currentDirectory,
                    dirName: dirName
                }
            };
        });
    }

    getDirectory(id) {
        DirService.get(id)
            .then(response => {
                this.setState({
                    currentDirectory: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateDirectory() {
        DirService.update(
            this.state.currentDirectory.id,
            this.state.currentDirectory
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The Directory was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteDirectory() {
        DirService.delete(this.state.currentDirectory.id)
            .then(response => {
                console.log(response.data);
                this.props.router.navigate('/directories');
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {

        const {currentDirectory} = this.state;

        return(
            <>
                {currentDirectory? (
                    <div className="your-folders">

                        <label htmlFor="title">Title</label>
                        <input
                            type="text"
                            className="form-control"
                            id="directory"
                            value={currentDirectory.title}
                            onChange={this.onChangeDirName}
                        />

                        <button
                            className="badge badge-danger mr-2"
                            onClick={this.deleteDirectory}
                        >
                            Delete
                        </button>

                        <button
                            type="submit"
                            className="badge badge-success"
                            onClick={this.updateDirectory}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>

                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Tutorial...</p>
                    </div>
                )}


            </>

        )
    }
}

export default withRouter(Directory);