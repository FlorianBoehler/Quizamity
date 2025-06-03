const carousel = bootstrap.Carousel.getOrCreateInstance(document.querySelector('#carouselExample'));
const formData = {};

// Themenauswahl
const topicBtnLabel = document.getElementById("topicButtonLabel");
const nextTopic = document.getElementById("nextTopic");
document.querySelectorAll(".topic-option").forEach(item => {
  item.addEventListener("click", (e) => {
    e.preventDefault();
    const topic = e.target.textContent;
    topicBtnLabel.textContent = topic;
    formData.topic = topic;
    nextTopic.disabled = false;
  });
});
nextTopic.addEventListener("click", () => carousel.next());

// Spielmodus
let selectedMode = '';
document.querySelectorAll(".mode-btn").forEach(btn => {
  btn.addEventListener("click", () => {
    selectedMode = btn.textContent;
    formData.mode = selectedMode;
    document.querySelectorAll(".mode-btn").forEach(b => b.classList.remove("btn-light"));
    btn.classList.add("btn-light");
    document.getElementById("nextMode").disabled = false;
  });
});
document.getElementById("nextMode").addEventListener("click", () => carousel.next());

// Lobby
document.getElementById("lobbyButtons").addEventListener("click", (e) => {
  if (!e.target.classList.contains("btn")) return;
  const name = e.target.textContent;
  formData.lobby = name;
  if (name === "eigene Lobby gründen") {
    carousel.next();
  } else {
    window.location.href = "/quiz.html";
  }
});

// Anzahl Fragen
const range = document.getElementById("anzahlFragen");
const display = document.getElementById("anzahlValue");
range.addEventListener("input", () => {
  display.textContent = range.value;
  formData.anzahlFragen = Number(range.value);
});
document.getElementById("nextFragen").addEventListener("click", () => carousel.next());

