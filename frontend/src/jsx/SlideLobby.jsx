import { useState, useEffect } from "react";

export default function SlideLobby({ onChange }) {
  const [lobby, setLobby] = useState("");

  useEffect(() => {
    onChange(lobby);
  }, [lobby, onChange]);

  const handleClick = (name) => {
    if (name === "eigene Lobby gründen") {
      setLobby(name); // Status setzen, weiter zum Warteraum oder anderen Logik
    } else {
      // Direkte Weiterleitung zur quiz.html
      window.location.href = "/quiz.html";
    }
  };

  return (
    <div className="carousel-item">
      <img src="img/lobby.jpg" className="d-block w-100" alt="Lobby" />
      <div className="carousel-caption d-md-block">
        <h5>Lobby beitreten</h5>
        <div className="button-wrapper">
          {[
            "Christians Lobby (10 Fragen)",
            "Selinas Lobby (15 Fragen)",
            "Florians Lobby (20 Fragen)",
            "Sams Lobby (25 Fragen)",
            "eigene Lobby gründen"
          ].map((name, i) => (
            <button
              key={i}
              onClick={() => handleClick(name)}
              className={lobby === name ? "btn btn-primary" : "btn btn-secondary"}
            >
              {name}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
}
