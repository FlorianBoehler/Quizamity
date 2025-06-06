import axios from 'axios';

const API_AUTH_URL = 'http://localhost:9080/quizamity-1.0-SNAPSHOT/api/authenticate';

export const authenticateUser = async (username, password) => {
  try {
    // API erwartet: /authenticate/{username}/{password}
    const response = await axios.get(`${API_AUTH_URL}/${username}/${password}`, {
      headers: { Accept: 'application/json' },
      responseType: 'text'  // Token als Text zurück
    });
    return response.data; // JWT Token als String
  } catch (error) {
    console.error("Authentifizierung fehlgeschlagen:", error);
    throw error;
  }
};