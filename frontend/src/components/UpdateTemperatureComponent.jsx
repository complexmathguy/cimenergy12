import React, { Component } from 'react'
import TemperatureService from '../services/TemperatureService';

class UpdateTemperatureComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                multiplier: '',
                unit: ''
        }
        this.updateTemperature = this.updateTemperature.bind(this);

        this.changeMultiplierHandler = this.changeMultiplierHandler.bind(this);
        this.changeUnitHandler = this.changeUnitHandler.bind(this);
    }

    componentDidMount(){
        TemperatureService.getTemperatureById(this.state.id).then( (res) =>{
            let temperature = res.data;
            this.setState({
                multiplier: temperature.multiplier,
                unit: temperature.unit
            });
        });
    }

    updateTemperature = (e) => {
        e.preventDefault();
        let temperature = {
            temperatureId: this.state.id,
            multiplier: this.state.multiplier,
            unit: this.state.unit
        };
        console.log('temperature => ' + JSON.stringify(temperature));
        console.log('id => ' + JSON.stringify(this.state.id));
        TemperatureService.updateTemperature(temperature).then( res => {
            this.props.history.push('/temperatures');
        });
    }

    changeMultiplierHandler= (event) => {
        this.setState({multiplier: event.target.value});
    }
    changeUnitHandler= (event) => {
        this.setState({unit: event.target.value});
    }

    cancel(){
        this.props.history.push('/temperatures');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Temperature</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Multiplier: </label>
                                            #formFields( $attribute, 'update')
                                            <label> Unit: </label>
                                            #formFields( $attribute, 'update')
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateTemperature}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateTemperatureComponent
