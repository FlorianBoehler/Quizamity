import { screen } from "@testing-library/dom";

// quiz.js lädt automatisch beim DOMContentLoaded
import "../js/quiz.js";

describe("Quiz", () => {
  beforeEach(() => {
    // leeres HTML-Formular simulieren
    document.body.innerHTML = `<div id="quizForm"></div>`;
    localStorage.clear(); // localStorage leeren
  });

  test("zeigt Fehlermeldung, wenn keine Kategorie gewählt ist", async () => {
    // Kein selectedTopic → soll "Keine Kategorie gewählt." anzeigen

    // DOMContentLoaded auslösen
    document.dispatchEvent(new Event("DOMContentLoaded"));

    // Text prüfen
    expect(screen.getByText("Keine Kategorie gewählt.")).toBeInTheDocument();
  });

  test("zeigt Fehlermeldung, wenn keine Fragen gefunden werden", async () => {
    localStorage.setItem("selectedTopic", "TestKategorie");

    // fetch mocken, damit keine Fragen zurückkommen
    global.fetch = jest.fn(() =>
      Promise.resolve({
        json: () => Promise.resolve([]),
      })
    );

    document.dispatchEvent(new Event("DOMContentLoaded"));

    // Warte auf das Ergebnis, weil async
    await new Promise((r) => setTimeout(r, 0));

    expect(screen.getByText("Keine Fragen gefunden.")).toBeInTheDocument();
  });
});
