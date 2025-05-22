import {useEffect } from "react";

export default function SlideWarteraum({ onChange }) {
  useEffect(() => {
    // Warteraum ist immer „fertig“
    onChange(true);
  }, [onChange]);

  return (
    <div className="carousel-item">
      <img src="img/waitingroom.jpg" className="d-block w-100" alt="Warteraum" />
      <div className="carousel-caption d-md-block">
        <h5>Lobby-Warteraum</h5>
        <p>Mitglieder werden gesucht... bitte warten</p>
      </div>
    </div>
  );
}