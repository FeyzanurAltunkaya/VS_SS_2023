import React, { Component } from "react";
import DirService from "../services/DirService";

export default class AddDirectory extends Component {
    constructor(props) {
        super(props);
        this.onChangeDirName = this.onChangeDirName.bind(this);
        this.saveDirectory = this.saveDirectory.bind(this);
        this.newDirectory = this.newDirectory.bind(this);

        this.state = {
            id: null,
            dirName: "",
            //users :"",
            submitted: false
        };
    }

    onChangeDirName(e) {
        this.setState({
            dirName: e.target.value
        });
    }


    saveDirectory() {
        const data = {
            dirName: this.state.dirName,
        };

        DirService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    dirName: response.data.dirName,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newDirectory() {
        this.setState({
            id: null,
            dirName: "",

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={this.newDirectory}>
                            Add
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                required
                                value={this.state.dirName}
                                onChange={this.onChangeDirName}
                                name="title"
                            />
                        </div>

                        <button onClick={this.saveDirectory} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}