import axios from 'axios';

const CARS_REST_API_URL = 'http://localhost:8080/carsapi/v1/cars';

class CarsService {
    getCars() {
        return axios.get(CARS_REST_API_URL);
    }
}

export default new CarsService();