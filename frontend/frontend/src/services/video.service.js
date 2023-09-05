import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://localhost:8080/api/v1';

class VideoService {
    getLive(id) {
        return axios.get(
            API_URL + '/video/'+ id +'/live.mp4', 
            { headers: authHeader() });
    }

}

export default new VideoService();