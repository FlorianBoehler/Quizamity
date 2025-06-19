import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import Login from "../jsx/Login.jsx";

describe("Login", () => {
  test("zeigt Fehlermeldung, wenn Benutzername oder Passwort leer sind", () => {
    render(<Login />);

    // Klick auf Login-Button ohne Eingaben
    fireEvent.click(screen.getByText("Login"));

    // Fehlermeldung wird sofort angezeigt
    expect(
      screen.getByText("Benutzername oder Passwort sind erforderlich.")
    ).toBeInTheDocument();
  });

  test("zeigt Fehlermeldung bei falschen Anmeldedaten", async () => {
    render(<Login />);

    // Benutzername und Passwort eingeben
    fireEvent.change(screen.getByPlaceholderText("Benutzername"), {
      target: { value: "falsch" },
    });
    fireEvent.change(screen.getByPlaceholderText("Passwort"), {
      target: { value: "falsch" },
    });

    fireEvent.click(screen.getByText("Login"));

    // Warten, bis die Fehlermeldung erscheint (weil async)
    expect(
      await screen.findByText(
        "Benutzername oder Passwort ist falsch, bitte versuche es erneut."
      )
    ).toBeInTheDocument();
  });
});
