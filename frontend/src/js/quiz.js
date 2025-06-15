document.addEventListener("DOMContentLoaded", async () => {
  const quizForm = document.getElementById("quizForm");
  const selectedCategory = localStorage.getItem("selectedTopic");
  const mode = (localStorage.getItem("selectedMode" ) || "Singleplayer").toLowerCase(); // Modus aus localStorage lesen, Default Singleplayer

  if (!selectedCategory) {
    quizForm.innerHTML = "<p>Keine Kategorie gewählt.</p>";
    return;
  }

  try {
    const res = await fetch("http://localhost:9080/quizamity-1.0-SNAPSHOT/api/questions");
    const allQuestions = await res.json();
   const selectedCount = parseInt(localStorage.getItem("selectedQuestionsCount"), 10);
const questions = allQuestions
  .filter(q => q.categoryName === selectedCategory)
  .slice(0, selectedCount || allQuestions.length);

    if (questions.length === 0) {
      quizForm.innerHTML = "<p>Keine Fragen gefunden.</p>";
      return;
    }

    let index = 0;
    let score = 0;

    await renderQuestion(questions[index]);

    async function renderQuestion(question) {
      const aRes = await fetch(`http://localhost:9080/quizamity-1.0-SNAPSHOT/api/answers/question/${question.id}`);
      const answers = await aRes.json();

      quizForm.innerHTML = `
        <h2>${question.text}</h2>
        ${answers.map((a, i) => `
          <div class="form-check">
            <input class="form-check-input" type="radio" name="frage1" id="antwort${i}" value="${a.id}">
            <label class="form-check-label" for="antwort${i}">${a.text}</label>
          </div>
        `).join("")}
        <div class="nextbutton">
          <button type="submit" class="button">nächste Frage</button>
        </div>
      `;

      quizForm.onsubmit = (e) => {
        e.preventDefault();
        const selected = quizForm.querySelector('input[name="frage1"]:checked');
        if (!selected) {
          alert("Bitte eine Antwort auswählen.");
          return;
        }

        const selectedAnswer = answers.find(a => a.id == selected.value);
        if (selectedAnswer?.isCorrect) score++;

        index++;
        if (index < questions.length) {
          renderQuestion(questions[index]);
        } else {
          let gegnerScoreText = "";
          if (mode !== "singleplayer") {
            const gegnerScore = Math.floor(Math.random() * (questions.length + 1));
            gegnerScoreText = `<p>Dein Gegner hat <strong>${gegnerScore}</strong> von <strong>${questions.length}</strong> richtig.</p>`;
          }

          quizForm.innerHTML = `
            <h2>Quiz beendet!</h2>
            <p>Du hast <strong>${score}</strong> von <strong>${questions.length}</strong> richtig.</p>
            ${gegnerScoreText}
          `;
        }
      };
    }

  } catch (err) {
    console.error(err);
    quizForm.innerHTML = `<p class="text-danger">Fehler beim Laden.</p>`;
  }
});
