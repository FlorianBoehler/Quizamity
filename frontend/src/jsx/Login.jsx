import React from "react";
import { useState, useEffect } from "react";
import { authenticateUser } from "../js/user"; // API-Funktionen importieren

function Login() {
  const [username, setUsername] = useState(""); // Benutzername im State
  const [password, setPassword] = useState(""); // Passwort im State
  const [error, setError] = useState(""); // Fehlernachricht im State
  const [redirect, setRedirect] = useState(false); // Weiterleitungszustand

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Überprüfen, ob beide Felder ausgefüllt sind
    if (!username || !password) {
      setError("Benutzername oder Passwort sind erforderlich.");
      return; // Verhindern, dass der Code weiterläuft
    }

    try {
      const token = await authenticateUser(username, password);
      localStorage.setItem("token", token); // Token speichern
      setRedirect(true);
    } catch {
      setError(
        "Benutzername oder Passwort ist falsch, bitte versuche es erneut."
      );
    }
  };

  // Weiterleitung nach erfolgreicher Anmeldung
  useEffect(() => {
    if (redirect) {
      window.location.href = `${window.location.origin}/public/dashboard.html`; // Weiterleitung auf das Dashboard
    }
  }, [redirect]); // Nur auslösen, wenn "redirect" wahr ist

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
          onChange={(e) => setUsername(e.target.value)} // Aktualisieren des Benutzernamens im State
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
          onChange={(e) => setPassword(e.target.value)} // Aktualisieren des Passworts im State
        />
      </div>
      {error && <p className="error">{error}</p>}{" "}
      {/* Fehleranzeige, wenn vorhanden */}
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
