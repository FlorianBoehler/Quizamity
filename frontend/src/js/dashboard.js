document.addEventListener('DOMContentLoaded', () => {
  // Carousel initialisieren mit der richtigen ID
  const carousel = bootstrap.Carousel.getOrCreateInstance(document.querySelector('#carouselExampleCaptions'));
  const formData = {};

  // THEMENAUSWAHL
  const topicBtnLabel = document.getElementById("topicButtonLabel");
  const nextTopic = document.getElementById("nextTopic");
  const dropdownToggle = document.getElementById("btn-small");

  // Button zuerst deaktivieren
  nextTopic.disabled = true;

  // Bootstrap Dropdown Instanz holen oder erstellen
  const dropdownInstance = bootstrap.Dropdown.getOrCreateInstance(dropdownToggle);

  const dropdownItems = document.querySelectorAll(".dropdown-item");

  dropdownItems.forEach(item => {
    item.addEventListener("click", () => {
      const selectedTopic = item.textContent.trim();
      formData.topic = selectedTopic; // Optional: speichern

      // Text im Button aktualisieren
      topicBtnLabel.textContent = selectedTopic;

      // Weiter Button aktivieren
      nextTopic.disabled = false;

      // Aktive Klasse setzen
      dropdownItems.forEach(i => i.classList.remove("active"));
      item.classList.add("active");

      // Dropdown schließen
      dropdownInstance.hide();
    });
  });

  nextTopic.addEventListener("click", () => {
    carousel.next();
  });

  // SPIELMODUS
const singleBtn = document.getElementById("singleplayer-button");
const multiBtn = document.getElementById("multiplayer-button");
const prevMode = document.getElementById("prevMode");
const nextMode = document.getElementById("nextMode");

 // Button zuerst deaktivieren
  nextTopic.disabled = true;

singleBtn.addEventListener("click", function () {
  const selectedMode = this.textContent.trim();
  formData.mode = selectedMode;

  this.classList.add("btn-light");
  multiBtn.classList.remove("btn-light");

  // Direkt weiterleiten, kein Weiter-Button nötig
  window.location.href = `${window.location.origin}/public/quiz.html`;
});

multiBtn.addEventListener("click", function () {
  const selectedMode = this.textContent.trim();
  formData.mode = selectedMode;

  this.classList.add("btn-light");
  singleBtn.classList.remove("btn-light");

  // Weiter-Button aktivieren, weil Multi Player noch weitere Schritte hat
  nextMode.disabled = false;
});

prevMode.addEventListener("click", () => {
  carousel.prev();
});

nextMode.addEventListener("click", () => {
  carousel.next();
});

  // LOBBY
const lobbyButtons = ["lobby-button1", "lobby-button2", "lobby-button3", "lobby-button4", "lobby"];
const prevLobby = document.getElementById("prevLobby");
const nextLobby = document.getElementById("nextLobby");

nextLobby.disabled = true; // Weiter-Button erstmal deaktivieren

prevLobby.addEventListener("click", () => {
  carousel.prev();

});

lobbyButtons.forEach(id => {
  const btn = document.getElementById(id);
  if (btn) {
    btn.addEventListener("click", () => {
      // Lobby-Name speichern
      const name = btn.textContent.trim();
      formData.lobby = name;

      // Weiter Button aktivieren
      nextLobby.disabled = false;

      // Aktive Klasse für optische Markierung
      lobbyButtons.forEach(otherId => {
        const otherBtn = document.getElementById(otherId);
        if (otherBtn) otherBtn.classList.remove("active");
      });
      btn.classList.add("active");
    });
  }
});

// Weiter-Button klick: Carousel weiter oder Seite wechseln
nextLobby.addEventListener("click", () => {
  if (formData.lobby === "eigene Lobby gründen") {
    carousel.next(); // Zum nächsten Slide im Carousel
  } else {
    window.location.href = `${window.location.origin}/public/quiz.html`; // Direkt zur Quiz-Seite
  }


});

  // ANZAHL FRAGEN
const range = document.getElementById("anzahlFragen");
const display = document.getElementById("fragenWert");
const prevFragenBtn = document.getElementById("prevQuestion");
const nextFragenBtn = document.getElementById("nextQuestion");

nextFragenBtn.disabled = true; // Weiter-Button erstmal deaktivieren

// Anfangswert anzeigen und speichern
formData.anzahlFragen = Number(range.value);
display.textContent = range.value;

// Event bei Änderung des Sliders
range.addEventListener("input", () => {
  display.textContent = range.value;
  formData.anzahlFragen = Number(range.value);
});

 // Weiter Button aktivieren
      nextFragenBtn.disabled = false;

prevFragenBtn.addEventListener("click", () => {
  carousel.prev();
});

// Beim Klick auf "Weiter" zur Warteraum-Slide springen
nextFragenBtn.addEventListener("click", () => {
  carousel.next(); // Springt zur nächsten Slide, z. B. Warteraum
});

});

});

