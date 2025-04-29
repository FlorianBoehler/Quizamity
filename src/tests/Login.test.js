import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import Login from "../../frontend/src/jsx/Login.jsx";

describe("Login", () => {
  test("zeigt Fehlermeldung bei ungültigen Anmeldedaten", () => {
    render(<Login />);

    const usernameInput = screen.getByPlaceholderText("Benutzername");
    const passwordInput = screen.getByPlaceholderText("Passwort");
    const submitButton = screen.getByText("Login");

    fireEvent.change(usernameInput, { target: { value: "invalidusername" } });
    fireEvent.change(passwordInput, { target: { value: "wrongpassword" } });
    fireEvent.click(submitButton);

    expect(
      screen.getByText(
        "Benutzername oder Passwort falsch, bitte versuche es erneut."
      )
    ).toBeInTheDocument();
  });

  test("zeigt Fehler bei leerem Benutzernamen oder Passwort", () => {
    render(<Login />);

    const submitButton = screen.getByText("Login");

    // Klickt auf den Button ohne Benutzernamen oder Passwort
    fireEvent.click(submitButton);

    // Überprüfen, ob keine Weiterleitung stattfindet und eine Fehlermeldung angezeigt wird
    expect(
      screen.getByText(
        "Benutzername oder Passwort falsch, bitte versuche es erneut."
      )
    ).toBeInTheDocument();
  });
});
