import React, { Component } from 'react'
import PssPTIST1Service from '../services/PssPTIST1Service'

class ListPssPTIST1Component extends Component {
    constructor(props) {
        super(props)

        this.state = {
                pssPTIST1s: []
        }
        this.addPssPTIST1 = this.addPssPTIST1.bind(this);
        this.editPssPTIST1 = this.editPssPTIST1.bind(this);
        this.deletePssPTIST1 = this.deletePssPTIST1.bind(this);
    }

    deletePssPTIST1(id){
        PssPTIST1Service.deletePssPTIST1(id).then( res => {
            this.setState({pssPTIST1s: this.state.pssPTIST1s.filter(pssPTIST1 => pssPTIST1.pssPTIST1Id !== id)});
        });
    }
    viewPssPTIST1(id){
        this.props.history.push(`/view-pssPTIST1/${id}`);
    }
    editPssPTIST1(id){
        this.props.history.push(`/add-pssPTIST1/${id}`);
    }

    componentDidMount(){
        PssPTIST1Service.getPssPTIST1s().then((res) => {
            this.setState({ pssPTIST1s: res.data});
        });
    }

    addPssPTIST1(){
        this.props.history.push('/add-pssPTIST1/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">PssPTIST1 List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addPssPTIST1}> Add PssPTIST1</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.pssPTIST1s.map(
                                        pssPTIST1 => 
                                        <tr key = {pssPTIST1.pssPTIST1Id}>
                                             <td>
                                                 <button onClick={ () => this.editPssPTIST1(pssPTIST1.pssPTIST1Id)} className="btn btn-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deletePssPTIST1(pssPTIST1.pssPTIST1Id)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewPssPTIST1(pssPTIST1.pssPTIST1Id)} className="btn btn-info btn-sm">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListPssPTIST1Component
