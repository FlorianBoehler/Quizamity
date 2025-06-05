import { useState, useEffect } from "react";
import { getAllUsers } from "./User"; // API-Funktionen importieren

function Login() {
  const [username, setUsername] = useState("");  // Benutzername im State
  const [password, setPassword] = useState("");  // Passwort im State
  const [error, setError] = useState("");  // Fehlernachricht im State
  const [redirect, setRedirect] = useState(false);  // Weiterleitungszustand

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Überprüfen, ob beide Felder ausgefüllt sind
    if (!username || !password) {
      setError("Benutzername oder Passwort sind erforderlich");
      return;  // Verhindern, dass der Code weiterläuft
    }

    try {
      // Alle Benutzer abrufen
      const users = await getAllUsers();

      // Benutzer mit passendem Benutzernamen und Passwort suchen
      const user = users.find((user) => user.username === username && user.password === password);

      if (user) {
        setRedirect(true);  // Weiterleitung setzen
      } else {
        setError("Benutzername oder Passwort falsch, bitte versuche es erneut.");
      }
    } catch (err) {
      setError("Fehler beim Abrufen der Benutzer, bitte versuche es später erneut.");
      console.error("Fehler beim Abrufen der Benutzer:", err);
    }
  };

  // Weiterleitung nach erfolgreicher Anmeldung
  useEffect(() => {
    if (redirect) {
     window.location.href = `${window.location.origin}/dashboard.html`;// Weiterleitung auf das Dashboard
    }
  }, [redirect]);  // Nur auslösen, wenn "redirect" wahr ist

  return (
    <form className="loginform" onSubmit={handleSubmit}>
      <div className="form-group">
        <label htmlFor="inputUsername">Benutzername</label>
        <input
          type="text"
          className="form-control"
          id="inputUsername"
          placeholder="Benutzername"
          value={username}
          onChange={(e) => setUsername(e.target.value)}  // Aktualisieren des Benutzernamens im State
        />
      </div>
      <div className="form-group">
        <label htmlFor="inputPassword">Passwort</label>
        <input
          type="password"
          className="form-control"
          id="inputPassword"
          placeholder="Passwort"
          value={password}
          onChange={(e) => setPassword(e.target.value)}  // Aktualisieren des Passworts im State
        />
      </div>
      {error && <p className="error">{error}</p>}  {/* Fehleranzeige, wenn vorhanden */}
      <div id="loginlinks">
        <ul>
          <li>
            <a href="">Passwort vergessen?</a>
          </li>
          <li>
            <a href="">Noch kein Konto? Jetzt registrieren</a>
          </li>
        </ul>
      </div>
      <div className="loginbutton">
        <button type="submit" className="button">
          Login
        </button>
      </div>
    </form>
  );
}

export default Login;
