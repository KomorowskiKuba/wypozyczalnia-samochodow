import React from "react";
import CarsService from "../services/CarsService";

class CarComponent extends React.Component {
    
    constructor(props) {
        super(props)
        this.state = {
            cars: []
        }
    }

    componentDidMount() {
        CarsService.getCars().then((response) => {
            this.setState({cars: response.data});
        });
    }

    render() {
        return (
            <div>
                <h1 className = "text-center"> Cars list </h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td> Car id </td>
                            <td> Car brand </td>
                            <td> Car model </td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.cars.map(
                                car => 
                                <tr key = {car.id}>
                                    <td> {car.id} </td>
                                    <td> {car.brand} </td>
                                    <td> {car.model} </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default CarComponent