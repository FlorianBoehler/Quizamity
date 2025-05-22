import { useState, useEffect } from "react";

export default function SlideAnzahlFragen() {
  const [anzahl, setAnzahl] = useState(10); //Startwert

    // Immer wenn sich anzahl ändert, Callback aufrufen
  useEffect(() => {
    onChange(anzahl);
  }, [anzahl, onChange]);

  return (
    <div className="carousel-item">
      <img src="img/questions.jpg" className="d-block w-100" alt="Anzahl Fragen" />
      <div className="carousel-caption d-md-block">
        <h5>Anzahl der Fragen</h5>
        <p>Wähle die Anzahl an Fragen oder starte mit der Standardeinstellung (20 Fragen)</p>
        <div className="mb-4">
          <label htmlFor="anzahlFragen" className="form-label">
            Anzahl Fragen: <span>{anzahl}</span>
          </label>
          <input
            type="range"
            className="form-range"
            min="10"
            max="30"
            value={anzahl}
            step="2"
            id="anzahlFragen"
            onChange={(e) => setAnzahl(Number(e.target.value))}
          />
        </div>
      </div>
    </div>
  );
}