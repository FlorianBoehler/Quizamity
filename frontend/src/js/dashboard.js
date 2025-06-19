document.addEventListener("DOMContentLoaded", () => {
  // Carousel initialisieren mit der richtigen ID
  const carouselElement = document.querySelector("#carouselExampleCaptions");
  // Bootstrap Carousel-Instanz erstellen oder holen
  const carousel = bootstrap.Carousel.getOrCreateInstance(carouselElement);
  const formData = {};

  // THEMENAUSWAHL
  const topicBtnLabel = document.getElementById("topicButtonLabel");
  const nextTopic = document.getElementById("nextTopic");
  const dropdownToggle = document.getElementById("btn-small");
  const dropdownMenu = document.getElementById("categoryDropdown");

  // Button zuerst deaktivieren
  nextTopic.disabled = true;

  // Bootstrap Dropdown Instanz holen oder erstellen
  const dropdownInstance =
    bootstrap.Dropdown.getOrCreateInstance(dropdownToggle);

  fetch("http://localhost:9080/quizamity-1.0-SNAPSHOT/api/categories")
    .then((res) => res.json())
    .then((categories) => {
      dropdownMenu.innerHTML = ""; // leeren

      categories.forEach((cat) => {
        const li = document.createElement("li");
        const btn = document.createElement("button");
        btn.className = "dropdown-item";
        btn.textContent = cat.name;

        btn.addEventListener("click", () => {
          topicBtnLabel.textContent = cat.name;
          nextTopic.disabled = false;
          localStorage.setItem("selectedTopic", cat.name); //speichern

          // aktive Klasse setzen
          dropdownMenu
            .querySelectorAll(".dropdown-item")
            .forEach((i) => i.classList.remove("active"));
          btn.classList.add("active");

          dropdownInstance.hide();
        });

        li.appendChild(btn);
        dropdownMenu.appendChild(li);
      });
    })
    .catch(() => {
      dropdownMenu.innerHTML =
        '<li><span class="dropdown-item text-danger">Kategorien konnten nicht geladen werden</span></li>';
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
  nextMode.disabled = true;

  singleBtn.addEventListener("click", function () {
    const selectedMode = "Singleplayer";
    formData.mode = selectedMode;

    // Modus in localStorage speichern
    localStorage.setItem("selectedMode", selectedMode);

    this.classList.add("btn-light");
    multiBtn.classList.remove("btn-light");

    // Direkt weiterleiten, kein Weiter-Button nötig
    window.location.href = `${window.location.origin}/public/quiz.html`;
  });

  multiBtn.addEventListener("click", function () {
    const selectedMode = "Multiplayer";
    formData.mode = selectedMode;

    // Modus in localStorage speichern
    localStorage.setItem("selectedMode", selectedMode);

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
  const lobbyButtons = [
    "lobby-button1",
    "lobby-button2",
    "lobby-button3",
    "lobby-button4",
    "lobby",
  ];
  const prevLobby = document.getElementById("prevLobby");
  const nextLobby = document.getElementById("nextLobby");

  nextLobby.disabled = true; // Weiter-Button erstmal deaktivieren

  // Statische Lobbys mit Fragenanzahl
  const lobbys = [
    { name: "Christians Lobby", questionsCount: 10 },
    { name: "Selinas Lobby", questionsCount: 15 },
    { name: "Florians Lobby", questionsCount: 20 },
    { name: "Sams Lobby", questionsCount: 25 },
  ];

  prevLobby.addEventListener("click", () => {
    carousel.prev();
  });

  lobbyButtons.forEach((id) => {
    const btn = document.getElementById(id);
    if (btn) {
      btn.addEventListener("click", () => {
        let name = btn.textContent.trim();
        const index = name.indexOf(" (");
        if (index > -1) {
          name = name.substring(0, index);
        }
        console.log("Geklickter Lobby-Name:", name);

        formData.lobby = name;

        const lobby = lobbys.find(
          (l) => l.name.toLowerCase().trim() === name.toLowerCase().trim()
        );
        console.log("Gefundene Lobby:", lobby);

        formData.anzahlFragen = lobby ? lobby.questionsCount : 0;
        console.log("Anzahl Fragen gesetzt auf:", formData.anzahlFragen);

        nextLobby.disabled = false;

        lobbyButtons.forEach((otherId) => {
          const otherBtn = document.getElementById(otherId);
          if (otherBtn) otherBtn.classList.remove("active");
        });
        btn.classList.add("active");
      });
    }
  });

  nextLobby.addEventListener("click", () => {
    if (formData.lobby === "Eigene Lobby gründen") {
      carousel.next();
    } else {
      localStorage.setItem("selectedLobby", formData.lobby);
      localStorage.setItem("selectedQuestionsCount", formData.anzahlFragen);
      window.location.href = `${window.location.origin}/public/quiz.html`;
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

  // WARTERAUM-Buttons nach 5 Sekunden einblenden
  const lobbyButtonsContainer = document.getElementById(
    "lobbyButtonsContainer"
  );
  const waitingText = document.getElementById("waiting-text");
  let timer;

  carouselElement.addEventListener("slid.bs.carousel", () => {
    clearTimeout(timer);

    const activeSlide = carouselElement.querySelector(".carousel-item.active");

    if (activeSlide && activeSlide.id === "waiting-room-slide") {
      lobbyButtonsContainer.classList.add("d-none");
      waitingText.textContent = "Mitglieder werden gesucht... bitte warten";

      timer = setTimeout(() => {
        lobbyButtonsContainer.classList.remove("d-none");
        waitingText.textContent = "Mitglied gefunden";

        const lobbyBtn5 = document.getElementById("lobby-button5");
        if (lobbyBtn5) {
          // Event Listener einmalig setzen
          lobbyBtn5.onclick = () => {
            const selectedTopic = localStorage.getItem("selectedTopic") || "";
            const fragenWert =
              document.getElementById("anzahlFragen").value || "10";

            localStorage.setItem(
              "selectedLobby",
              "Christian ist beigetreten - Quiz starten"
            );
            localStorage.setItem("selectedTopic", selectedTopic);
            localStorage.setItem("selectedQuestionsCount", fragenWert);

            // Optional Button deaktivieren, um Mehrfachklick zu verhindern
            lobbyBtn5.disabled = true;

            window.location.href = `${window.location.origin}/public/quiz.html`;
          };
        }
      }, 5000);
    } else {
      lobbyButtonsContainer.classList.add("d-none");
    }
  });
});
