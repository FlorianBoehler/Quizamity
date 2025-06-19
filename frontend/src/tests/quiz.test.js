import React from "react";
import { screen, fireEvent } from "@testing-library/react";
import { quiz } from "../js/quiz.js";

beforeEach(() => {
  // HTML Grundstruktur für das Quiz-Formular
  document.body.innerHTML = `<form id="quizForm"></form>`;
});

test("zeigt Fragen und Antworten an und zählt Punkte", async () => {
  // Mock für fetch - 2 Aufrufe: Fragen und Antworten
  global.fetch = jest
    .fn()
    // Erster fetch-Aufruf: Fragen zurückgeben
    .mockResolvedValueOnce({
      json: async () => [{ id: 1, text: "Frage 1?", categoryName: "Test" }],
    })
    // Zweiter fetch-Aufruf: Antworten zurückgeben
    .mockResolvedValueOnce({
      json: async () => [
        { id: 10, text: "Antwort 1", isCorrect: false },
        { id: 11, text: "Antwort 2", isCorrect: true },
      ],
    });

  // Starte das Quiz, das deine Funktion macht (z.B. renderQuestion etc.)
  await quiz();

  // Frage wird angezeigt
  expect(screen.getByText("Frage 1?")).toBeInTheDocument();

  // Antworten sind sichtbar
  expect(screen.getByLabelText("Antwort 1")).toBeInTheDocument();
  expect(screen.getByLabelText("Antwort 2")).toBeInTheDocument();

  // Wähle die richtige Antwort
  fireEvent.click(screen.getByLabelText("Antwort 2"));

  // Klicke auf "nächste Frage" (Button im Formular)
  fireEvent.click(screen.getByText("nächste Frage"));

  // Da nur eine Frage, wird Endbildschirm gezeigt (du musst hier anpassen je nach End-UI)
  expect(screen.getByText(/Quiz beendet/i)).toBeInTheDocument();
  expect(screen.getByText(/1 von 1 richtig/i)).toBeInTheDocument();
});
