import { useState } from "react";
function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (username === "Tobias Brückmann" && password === "quizamity") {
      window.location.href = "dashboard.html";
    } else {
      setError("Benutzername oder Passwort falsch, bitte versuche es erneut.");
    }
  };

  return (
    <form class="loginform" onSubmit={handleSubmit}>
      <div class="form-group">
        <label for="inputUsername">Benutzername</label>
        <input
          type="text"
          class="form-control"
          id="inputUsername"
          placeholder="Benutzername"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div class="form-group">
        <label for="inputPassword">Passwort</label>
        <input
          type="password"
          class="form-control"
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
      <div class="loginbutton">
        <button type="submit" class="button" href="dashboard.html">
          Login
        </button>
      </div>
    </form>
  );
}
export default Login;
