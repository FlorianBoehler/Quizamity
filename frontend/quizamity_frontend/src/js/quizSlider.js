// Event-Listener für das Laden der Seite hinzufügen
document.addEventListener("DOMContentLoaded", () => {
  // Hole den Slider und das Ausgabe-Element mit den entsprechenden IDs
  const slider = document.getElementById("anzahlFragen");
  const output = document.getElementById("fragenWert");

  // Setze den Startwert des Sliders im span
  output.textContent = slider.value; // Setze den Anfangswert des span auf den aktuellen Slider-Wert

  // Event-Listener für den Slider hinzufügen
  slider.addEventListener("input", () => {
    output.textContent = slider.value; // Aktualisiere den Wert im span, wenn der Slider bewegt wird
  });
});
