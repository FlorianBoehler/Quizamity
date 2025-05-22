import { useState, useEffect } from "react";

export default function SlideThemenauswahl({ onChange }) {
  const [selectedTopic, setSelectedTopic] = useState(""); // keine Auswahl zu Beginn

  // Callback an Parent, wenn Auswahl sich ändert
  useEffect(() => {
    onChange(selectedTopic);
  }, [selectedTopic, onChange]);

  return (
    <div className="carousel-item active">
      <img src="img/topicChoice.jpg" className="d-block w-100" alt="Themenauswahl" />
      <div className="carousel-caption d-md-block">
        <h5>Themenauswahl</h5>
        <div className="dropdown-center">
          <button
            className="btn btn-secondary dropdown-toggle btn-small"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            {selectedTopic || "Wähle aus deinen aktuell aktiven Modulen"}
          </button>
          <ul className="dropdown-menu">
            <li>
              <a
                className="dropdown-item"
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  setSelectedTopic("Organizational Behavior_DLBBWOB01");
                }}
              >
                Organizational Behavior_DLBBWOB01
              </a>
            </li>
            <li>
              <a
                className="dropdown-item"
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  setSelectedTopic("Seminar Software Engineering_ISEE01");
                }}
              >
                Seminar Software Engineering_ISEE01
              </a>
            </li>
            <li>
              <a
                className="dropdown-item"
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  setSelectedTopic("Projekt Software Engineering_ISEF01");
                }}
              >
                Projekt Software Engineering_ISEF01
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}
