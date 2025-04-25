import { useState, useEffect } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [redirect, setRedirect] = useState(false); // Zustand für die Weiterleitung

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!username || !password) {
      setError("Benutzername oder Passwort sind erforderlich.");
      return; // Verhindern, dass der Code weiterläuft
    }

    // Überprüfen der Anmeldedaten
    if (username === "Tobias Brückmann" && password === "quizamity") {
      setRedirect(true); // Weiterleitung setzen
    } else {
      setError("Benutzername oder Passwort falsch, bitte versuche es erneut.");
    }
  };

  // Weiterleitung nach erfolgreicher Anmeldung
  useEffect(() => {
    if (redirect) {
      window.location.href = "/dashboard.html"; // Weiterleitung auf das Dashboard
    }
  }, [redirect]); // Die Weiterleitung wird nur ausgelöst, wenn "redirect" wahr ist

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
          onChange={(e) => setUsername(e.target.value)}
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
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      {error && <p className="error">{error}</p>}
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
        <button type="submit" class="button" href="dashboard.html">
          Login
        </button>
      </div>
    </form>
  );
}
export default Login;
