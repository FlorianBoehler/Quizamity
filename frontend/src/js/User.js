/* import axios from 'axios';

// API-Basis-URL
const API_URL = 'http://localhost:9080/quizamity-1.0-SNAPSHOT/api/users';

// GET - Alle Benutzer abrufen
export const getAllUsers = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Fehler beim Abrufen der Benutzer:", error);
     throw error;  // Fehler weiterwerfen, damit er vom Aufrufer behandelt werden kann
  }
};

// GET - Benutzer nach ID abrufen
export const getUserById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error("Fehler beim Abrufen des Benutzers:", error);
    throw error;  // Fehler weiterwerfen, damit er vom Aufrufer behandelt werden kann
  }
};

// POST - Neuen Benutzer erstellen
export const createUser = async (user) => {
  try {
    const response = await axios.post(API_URL, user);
    return response.data;
  } catch (error) {
    console.error("Fehler beim Erstellen des Benutzers:", error);
    throw error;  // Fehler weiterwerfen, damit er vom Aufrufer behandelt werden kann
  }
};

// PUT - Benutzer aktualisieren
export const updateUser = async (id, updatedUser) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, updatedUser);
    return response.data;
  } catch (error) {
    console.error("Fehler beim Aktualisieren des Benutzers:", 
        error);
        throw error;  // Fehler weiterwerfen, damit er vom Aufrufer behandelt werden kann
  }
};

// DELETE - Benutzer löschen
export const deleteUser = async (id) => {
  try {
    const response = await axios.delete(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error("Fehler beim Löschen des Benutzers:", error);
    throw error;  // Fehler weiterwerfen, damit er vom Aufrufer behandelt werden kann
  }
};

*/