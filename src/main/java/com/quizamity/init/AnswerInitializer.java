package com.quizamity.init;

import com.quizamity.model.Answer;
import com.quizamity.model.Question;
import com.quizamity.service.AnswerService;
import com.quizamity.service.QuestionService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;
import jakarta.inject.Inject;

import java.util.List;

@Startup
@Singleton
@DependsOn("QuestionInitializer")
public class AnswerInitializer {

    @Inject
    private AnswerService answerService;

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {

        // IT-Projektmanagement Antworten
        createAnswersForQuestion("Was ist ein Gantt-Diagramm?", List.of(
                new Answer(null, "Ein Werkzeug zur Budgetplanung", false),
                new Answer(null, "Ein Balkendiagramm zur Darstellung von Projektplänen", true),
                new Answer(null, "Ein Kommunikationsmodell", false),
                new Answer(null, "Ein agiles Framework", false)
        ));

        createAnswersForQuestion("Welche Phasen umfasst der klassische Projektlebenszyklus?", List.of(
                new Answer(null, "Initiierung, Planung, Durchführung, Abschluss", true),
                new Answer(null, "Planung, Testen, Deployment", false),
                new Answer(null, "Kickoff, Umsetzung, Maintenance", false),
                new Answer(null, "Scrum, Kanban, XP", false)
        ));

        createAnswersForQuestion("Was beschreibt der kritische Pfad in einem Projektplan?", List.of(
                new Answer(null, "Der günstigste Verlauf eines Projekts", false),
                new Answer(null, "Die kürzeste Abfolge an Vorgängen", false),
                new Answer(null, "Die längste notwendige Abfolge ohne Puffer", true),
                new Answer(null, "Ein alternativer Notfallplan", false)
        ));

        createAnswersForQuestion("Was ist ein Lastenheft?", List.of(
                new Answer(null, "Ein internes Projektdokument zur Zeiterfassung", false),
                new Answer(null, "Ein Dokument mit den Anforderungen aus Sicht des Auftraggebers", true),
                new Answer(null, "Ein rechtlicher Vertrag zwischen Projektpartnern", false),
                new Answer(null, "Eine Liste von Softwarelizenzen", false)
        ));

        createAnswersForQuestion("Welche Methode wird bei agilen Projekten zur Sprintplanung verwendet?", List.of(
                new Answer(null, "PERT-Diagramm", false),
                new Answer(null, "Netzplantechnik", false),
                new Answer(null, "Planning Poker", true),
                new Answer(null, "SWOT-Analyse", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen Meilenstein und Liefergegenstand?", List.of(
                new Answer(null, "Ein Meilenstein ist ein konkretes Arbeitsergebnis, ein Liefergegenstand nicht", false),
                new Answer(null, "Ein Liefergegenstand ist ein Ergebnis, ein Meilenstein ein Zeitpunkt im Projekt", true),
                new Answer(null, "Beide Begriffe sind synonym", false),
                new Answer(null, "Meilensteine werden nur in agilen Projekten genutzt", false)
        ));

        createAnswersForQuestion("Welche Rolle hat ein Product Owner in Scrum?", List.of(
                new Answer(null, "Er plant den Sprint und teilt Aufgaben zu", false),
                new Answer(null, "Er priorisiert das Product Backlog und vertritt die Kundeninteressen", true),
                new Answer(null, "Er kontrolliert die Codequalität", false),
                new Answer(null, "Er verwaltet das Projektbudget", false)
        ));

        createAnswersForQuestion("Was ist ein Projektstrukturplan (PSP)?", List.of(
                new Answer(null, "Ein Plan zur grafischen Darstellung des Zeitmanagements", false),
                new Answer(null, "Ein hierarchisch gegliederter Plan aller Projektaufgaben", true),
                new Answer(null, "Ein Kostenkalkulationsblatt", false),
                new Answer(null, "Ein Standard in agilen Methoden", false)
        ));

        createAnswersForQuestion("Wie hilft ein Burndown-Chart beim Projektcontrolling?", List.of(
                new Answer(null, "Es zeigt die verbleibende Arbeitslast im Zeitverlauf", true),
                new Answer(null, "Es zeigt den Cashflow des Projekts", false),
                new Answer(null, "Es dokumentiert den Projektstart", false),
                new Answer(null, "Es enthält alle genehmigten Anforderungen", false)
        ));

        createAnswersForQuestion("Was bedeutet das magische Dreieck des Projektmanagements?", List.of(
                new Answer(null, "Es beschreibt die Beziehung zwischen Qualität, Zufriedenheit und Zeit", false),
                new Answer(null, "Es bezeichnet das Gleichgewicht zwischen Zeit, Kosten und Leistung", true),
                new Answer(null, "Es ist ein Kommunikationsmodell für Projektteams", false),
                new Answer(null, "Es beschreibt agile Prinzipien", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen einem Projekt und einer Linie?", List.of(
                new Answer(null, "Ein Projekt ist dauerhaft, eine Linie zeitlich begrenzt", false),
                new Answer(null, "Eine Linie ist zielgerichtet, ein Projekt nicht", false),
                new Answer(null, "Ein Projekt ist einmalig und zeitlich begrenzt, eine Linie ist dauerhaft organisiert", true),
                new Answer(null, "Eine Linie hat kein Budget, ein Projekt schon", false)
        ));

        createAnswersForQuestion("Welche Aufgaben hat ein Projektleiter?", List.of(
                new Answer(null, "Nur die technische Umsetzung eines Projekts", false),
                new Answer(null, "Koordination, Planung und Steuerung des Projekts", true),
                new Answer(null, "Ausschließlich Personalverantwortung", false),
                new Answer(null, "Erstellung von Code und Designs", false)
        ));

        createAnswersForQuestion("Was versteht man unter Scope Creep?", List.of(
                new Answer(null, "Ein plötzlicher Abbruch des Projekts", false),
                new Answer(null, "Ein ungeplanter Anstieg des Projektumfangs", true),
                new Answer(null, "Ein Fehler im Projektzeitplan", false),
                new Answer(null, "Ein Werkzeug zur Risikobewertung", false)
        ));

        createAnswersForQuestion("Was ist ein Kanban-Board und wie wird es eingesetzt?", List.of(
                new Answer(null, "Ein Finanzplanungswerkzeug", false),
                new Answer(null, "Ein Tool zur Visualisierung und Steuerung von Arbeitsprozessen", true),
                new Answer(null, "Ein Dokumentenarchiv", false),
                new Answer(null, "Ein Tool zur Erstellung von Lastenheften", false)
        ));

        createAnswersForQuestion("Wie funktioniert die Earned Value Analyse (EVA)?", List.of(
                new Answer(null, "Durch subjektive Bewertung des Projektstands", false),
                new Answer(null, "Durch Vergleich von geplantem, aktuellem und geleistetem Aufwand", true),
                new Answer(null, "Durch Analyse der Teamstimmung", false),
                new Answer(null, "Durch manuelle Abschätzung von Risiken", false)
        ));

        createAnswersForQuestion("Was ist der Zweck eines Kick-off-Meetings?", List.of(
                new Answer(null, "Vertragsunterzeichnung", false),
                new Answer(null, "Offizieller Projektstart und Abstimmung aller Beteiligten", true),
                new Answer(null, "Erstellung des Projektabschlussberichts", false),
                new Answer(null, "Einführung in die Programmiersprache", false)
        ));

        createAnswersForQuestion("Was versteht man unter einem Stakeholder im Projektmanagement?", List.of(
                new Answer(null, "Ein Projektteammitglied", false),
                new Answer(null, "Jede Person oder Gruppe mit Interesse oder Einfluss auf das Projekt", true),
                new Answer(null, "Nur der Projektauftraggeber", false),
                new Answer(null, "Ein externer Berater", false)
        ));

        createAnswersForQuestion("Was ist ein Risiko-Managementplan?", List.of(
                new Answer(null, "Ein Plan zur Kostenreduktion", false),
                new Answer(null, "Ein Dokument zur Identifikation, Bewertung und Steuerung von Risiken", true),
                new Answer(null, "Eine Checkliste für die Projektübergabe", false),
                new Answer(null, "Ein Marketingkonzept", false)
        ));

        createAnswersForQuestion("Welche Bedeutung hat die RACI-Matrix in Projekten?", List.of(
                new Answer(null, "Sie dient zur Zeiterfassung", false),
                new Answer(null, "Sie klärt Verantwortlichkeiten und Rollen im Projekt", true),
                new Answer(null, "Sie beschreibt die Kommunikationsstrategie", false),
                new Answer(null, "Sie stellt das Budget dar", false)
        ));

        createAnswersForQuestion("Wie unterscheiden sich agile und klassische Projektmanagement-Methoden?", List.of(
                new Answer(null, "Agile Methoden basieren auf festen Phasen, klassische auf Flexibilität", false),
                new Answer(null, "Klassische Methoden arbeiten iterativ, agile folgen einem Wasserfallmodell", false),
                new Answer(null, "Agile Methoden sind flexibel und inkrementell, klassische linear und planbasiert", true),
                new Answer(null, "Es gibt keinen Unterschied", false)
        ));

        createAnswersForQuestion("Was ist ein MVP (Minimum Viable Product) im Projektkontext?", List.of(
                new Answer(null, "Ein vollständiges Produkt mit allen Features", false),
                new Answer(null, "Ein Produktentwurf ohne Funktionalität", false),
                new Answer(null, "Eine erste, funktionsfähige Version mit minimalem Funktionsumfang", true),
                new Answer(null, "Ein internes Tool zur Budgetkontrolle", false)
        ));

        createAnswersForQuestion("Welche Vorteile bietet das Timeboxing in Projekten?", List.of(
                new Answer(null, "Erhöht die Projektkosten", false),
                new Answer(null, "Hilft bei der zeitlichen Begrenzung und Fokussierung auf das Wesentliche", true),
                new Answer(null, "Verlängert die Projektlaufzeit gezielt", false),
                new Answer(null, "Wird nur in der Abschlussphase eingesetzt", false)
        ));

        createAnswersForQuestion("Was ist ein Change Request im Projektverlauf?", List.of(
                new Answer(null, "Ein Antrag auf Budgeterhöhung", false),
                new Answer(null, "Eine formale Anfrage zur Änderung von Projektumfang, Zeit oder Kosten", true),
                new Answer(null, "Ein Kündigungsschreiben", false),
                new Answer(null, "Ein Prüfbericht", false)
        ));

        createAnswersForQuestion("Was beschreibt die MoSCoW-Priorisierung?", List.of(
                new Answer(null, "Ein geografisches Modell zur Ressourcenverteilung", false),
                new Answer(null, "Eine Methode zur Priorisierung von Anforderungen (Must, Should, Could, Won’t)", true),
                new Answer(null, "Ein Framework zur Teamentwicklung", false),
                new Answer(null, "Ein Reporting-Tool", false)
        ));

        createAnswersForQuestion("Wie wird der Fortschritt eines Projekts gemessen?", List.of(
                new Answer(null, "Anhand der Anzahl der Teammitglieder", false),
                new Answer(null, "Über Meilensteine, Kennzahlen und Zeitpläne", true),
                new Answer(null, "Durch Kundenfeedback", false),
                new Answer(null, "Mit dem Zufallsprinzip", false)
        ));

        createAnswersForQuestion("Was sind typische Gründe für das Scheitern von IT-Projekten?", List.of(
                new Answer(null, "Zu viele Entwickler", false),
                new Answer(null, "Unklare Anforderungen, schlechte Kommunikation, unrealistische Planung", true),
                new Answer(null, "Zu lange Testphasen", false),
                new Answer(null, "Nutzung moderner Technologien", false)
        ));

        createAnswersForQuestion("Welche Rolle spielt die Kommunikation im Projektteam?", List.of(
                new Answer(null, "Keine relevante Rolle", false),
                new Answer(null, "Nur zur Dokumentation wichtig", false),
                new Answer(null, "Sie ist entscheidend für Zusammenarbeit, Transparenz und Projekterfolg", true),
                new Answer(null, "Nur für Führungskräfte relevant", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen Aufwand und Dauer in einem Projektplan?", List.of(
                new Answer(null, "Es gibt keinen Unterschied", false),
                new Answer(null, "Aufwand beschreibt den Arbeitsumfang, Dauer den Zeitrahmen", true),
                new Answer(null, "Aufwand ist nur bei agilen Projekten relevant", false),
                new Answer(null, "Dauer berücksichtigt keine Arbeitszeit", false)
        ));

        createAnswersForQuestion("Wie funktioniert ein Daily Stand-up Meeting?", List.of(
                new Answer(null, "Als langes Planungstreffen", false),
                new Answer(null, "Als tägliches, kurzes Statusmeeting zur Abstimmung im Team", true),
                new Answer(null, "Als Einzelgespräch mit dem Projektleiter", false),
                new Answer(null, "Als wöchentlicher Jour Fixe", false)
        ));

        createAnswersForQuestion("Was ist ein Sprint Review in Scrum?", List.of(
                new Answer(null, "Eine Phase zur Fehlerbehebung", false),
                new Answer(null, "Ein Meeting zur Präsentation der Arbeitsergebnisse am Sprintende", true),
                new Answer(null, "Ein informeller Austausch unter Entwicklern", false),
                new Answer(null, "Ein Bericht zur Teamleistung", false)
        ));

        // Qualitätssicherung im Softwareprozess"

        createAnswersForQuestion("Was versteht man unter statischer Codeanalyse?", List.of(
                new Answer(null, "Die Ausführung des Codes mit verschiedenen Eingaben", false),
                new Answer(null, "Die manuelle Durchsicht des Codes ohne Werkzeuge", false),
                new Answer(null, "Die Analyse des Quellcodes ohne dessen Ausführung", true),
                new Answer(null, "Ein Lasttest bei hoher Serverauslastung", false)
        ));

        createAnswersForQuestion("Welche Aufgaben hat ein Software-Testmanager?", List.of(
                new Answer(null, "Er entwickelt ausschließlich Softwaremodule", false),
                new Answer(null, "Er verantwortet Planung, Steuerung und Überwachung von Testaktivitäten", true),
                new Answer(null, "Er führt alle Tests selbst durch", false),
                new Answer(null, "Er verwaltet nur die Testumgebung", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen Verifikation und Validierung?", List.of(
                new Answer(null, "Verifikation prüft, ob das Produkt korrekt gebaut wurde, Validierung ob das richtige Produkt gebaut wurde", true),
                new Answer(null, "Verifikation bedeutet, dass der Kunde das Produkt akzeptiert", false),
                new Answer(null, "Es gibt keinen Unterschied, beides meint das Gleiche", false),
                new Answer(null, "Validierung erfolgt vor der Verifikation", false)
        ));

        createAnswersForQuestion("Welche Teststufen gibt es im V-Modell?", List.of(
                new Answer(null, "Unit-Test, Integrationstest, Systemtest, Abnahmetest", true),
                new Answer(null, "Nur Systemtest und Abnahmetest", false),
                new Answer(null, "Unittest, Modultest, Netzwerktest", false),
                new Answer(null, "Explorativer Test, TDD-Test, Usertest", false)
        ));

        createAnswersForQuestion("Was versteht man unter Testautomatisierung?", List.of(
                new Answer(null, "Manuelles Ausführen von Tests durch Skripte", false),
                new Answer(null, "Automatisiertes Planen von Entwicklungssprints", false),
                new Answer(null, "Automatisches Ausführen von Tests durch Tools zur Effizienzsteigerung", true),
                new Answer(null, "Ein Prozess zur Codeoptimierung", false)
        ));

        createAnswersForQuestion("Welche Ziele verfolgt ein Review im Softwareprozess?", List.of(
                new Answer(null, "Nur formale Prüfung auf Rechtschreibung", false),
                new Answer(null, "Frühes Erkennen von Fehlern und Verbesserung der Qualität", true),
                new Answer(null, "Reduzierung der Hardwarekosten", false),
                new Answer(null, "Automatisierte Ausführung von Modultests", false)
        ));

        createAnswersForQuestion("Was ist ein Regressionstest?", List.of(
                new Answer(null, "Ein Test der Datenbankstruktur", false),
                new Answer(null, "Ein Test, der prüft, ob neue Änderungen alte Funktionen beeinträchtigen", true),
                new Answer(null, "Ein spezieller Test für Legacy-Systeme", false),
                new Answer(null, "Ein Test zur Bewertung der Projektzeitplanung", false)
        ));

        createAnswersForQuestion("Was beschreibt der Begriff 'Testabdeckung'?", List.of(
                new Answer(null, "Anteil des Codes, der durch Tests ausgeführt wird", true),
                new Answer(null, "Anzahl der Benutzer, die getestet haben", false),
                new Answer(null, "Dauer der Testdurchführung", false),
                new Answer(null, "Größe der Testumgebung", false)
        ));

        createAnswersForQuestion("Welche Rolle spielt die Qualitätssicherung in agilen Methoden?", List.of(
                new Answer(null, "Sie findet nur am Projektende statt", false),
                new Answer(null, "Sie ist integriert und erfolgt kontinuierlich während der Entwicklung", true),
                new Answer(null, "Sie wird durch die Projektleitung ersetzt", false),
                new Answer(null, "Sie wird ausgelagert an externe Teams", false)
        ));

        createAnswersForQuestion("Was ist eine Fehlermöglichkeits- und -einflussanalyse (FMEA)?", List.of(
                new Answer(null, "Ein Verfahren zur Priorisierung von Anforderungen", false),
                new Answer(null, "Eine Technik zur Identifikation potenzieller Fehlerquellen und deren Auswirkungen", true),
                new Answer(null, "Ein Test zur Überprüfung der Benutzerfreundlichkeit", false),
                new Answer(null, "Ein Auditverfahren für Sicherheitsrichtlinien", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen White-Box- und Black-Box-Testing?", List.of(
                new Answer(null, "White-Box-Testing kennt die innere Logik des Codes, Black-Box-Testing nicht", true),
                new Answer(null, "Black-Box-Testing testet nur die Benutzeroberfläche", false),
                new Answer(null, "White-Box-Testing wird nur von Testern ohne Programmierkenntnisse durchgeführt", false),
                new Answer(null, "Es gibt keinen Unterschied, beide testen das gleiche", false)
        ));

        createAnswersForQuestion("Welche Arten von Softwaretests gibt es?", List.of(
                new Answer(null, "Nur Unit-Tests", false),
                new Answer(null, "Nur Systemtests", false),
                new Answer(null, "Unit-, Integrations-, System- und Abnahmetests", true),
                new Answer(null, "Nur manuelle Tests", false)
        ));

        createAnswersForQuestion("Was versteht man unter einem Testfall?", List.of(
                new Answer(null, "Ein vollständiges Programm zur Durchführung eines Tests", false),
                new Answer(null, "Ein einzelner Nutzerbericht", false),
                new Answer(null, "Eine konkrete Eingabe-Ausgabe-Situation zur Überprüfung einer Funktion", true),
                new Answer(null, "Ein Codeabschnitt, der getestet wird", false)
        ));

        createAnswersForQuestion("Was ist eine Testumgebung?", List.of(
                new Answer(null, "Ein Bereich in der Software, in dem Benutzer Tests durchführen", false),
                new Answer(null, "Die Gesamtheit der Hardware, Software und Konfigurationen für Tests", true),
                new Answer(null, "Ein spezielles Testdokument", false),
                new Answer(null, "Ein Plan für die Teststrategie", false)
        ));

        createAnswersForQuestion("Was ist der Zweck eines Unit-Tests?", List.of(
                new Answer(null, "Testen des Zusammenspiels mehrerer Komponenten", false),
                new Answer(null, "Testen der Benutzeroberfläche", false),
                new Answer(null, "Testen einzelner Programmfunktionen oder -module", true),
                new Answer(null, "Testen der gesamten Software durch den Endnutzer", false)
        ));

        createAnswersForQuestion("Wie funktioniert ein Integrationstest?", List.of(
                new Answer(null, "Er testet die grafische Benutzeroberfläche", false),
                new Answer(null, "Er testet einzelne Codezeilen", false),
                new Answer(null, "Er testet das Zusammenspiel mehrerer Module", true),
                new Answer(null, "Er testet die Hardware", false)
        ));

        createAnswersForQuestion("Was sind typische Metriken in der Qualitätssicherung?", List.of(
                new Answer(null, "Codefarbe, Bildschirmgröße, Mausgeschwindigkeit", false),
                new Answer(null, "Fehlerdichte, Testabdeckung, Anzahl gefundener Fehler", true),
                new Answer(null, "Mitarbeiterzufriedenheit, Raumtemperatur, Stromverbrauch", false),
                new Answer(null, "Benutzerbewertungen, Anzahl der Tastenanschläge", false)
        ));

        createAnswersForQuestion("Was bedeutet der Begriff 'Defect Density'?", List.of(
                new Answer(null, "Die Geschwindigkeit, mit der ein Fehler auftritt", false),
                new Answer(null, "Die Anzahl von Benutzern mit Problemen", false),
                new Answer(null, "Die Anzahl von Fehlern pro Codeeinheit (z.B. KLOC)", true),
                new Answer(null, "Die Größe eines Fehlers im Speicher", false)
        ));

        createAnswersForQuestion("Welche Bedeutung hat Continuous Integration für die Qualitätssicherung?", List.of(
                new Answer(null, "Es ist ein manuelles Testverfahren", false),
                new Answer(null, "Es erlaubt häufiges Integrieren von Code und frühzeitige Fehlererkennung", true),
                new Answer(null, "Es ersetzt die gesamte Qualitätssicherung", false),
                new Answer(null, "Es ist ein Dokumentationswerkzeug", false)
        ));

        createAnswersForQuestion("Was ist ein Smoke-Test?", List.of(
                new Answer(null, "Ein Test mit erhöhter CPU-Temperatur", false),
                new Answer(null, "Ein vollständiger Systemtest", false),
                new Answer(null, "Ein einfacher Test zur Überprüfung grundlegender Funktionen", true),
                new Answer(null, "Ein Test zur Prüfung von Rauchmeldern", false)
        ));

        createAnswersForQuestion("Was sind die Vorteile von Test Driven Development (TDD)?", List.of(
                new Answer(null, "Tests werden erst am Ende geschrieben", false),
                new Answer(null, "Es verbessert die Wartbarkeit und Qualität des Codes", true),
                new Answer(null, "Es erhöht die Komplexität des Codes", false),
                new Answer(null, "Es ersetzt alle manuellen Tests", false)
        ));

        createAnswersForQuestion("Was ist eine Code Coverage Analyse?", List.of(
                new Answer(null, "Ein Verfahren zur Optimierung von Speicherzugriffen", false),
                new Answer(null, "Eine Methode zur Überprüfung, welche Codebereiche durch Tests abgedeckt sind", true),
                new Answer(null, "Eine Liste aller Bugs im Code", false),
                new Answer(null, "Ein Design-Diagramm des Codes", false)
        ));

        createAnswersForQuestion("Welche Bedeutung hat ein Traceability-Matrix?", List.of(
                new Answer(null, "Sie stellt die Verbindung zwischen Anforderungen und Testfällen her", true),
                new Answer(null, "Sie visualisiert das Netzwerkprotokoll", false),
                new Answer(null, "Sie ist eine grafische Darstellung der Benutzeroberfläche", false),
                new Answer(null, "Sie zeigt die Rechenleistung des Systems", false)
        ));

        createAnswersForQuestion("Was ist ein Explorativer Test?", List.of(
                new Answer(null, "Ein Test, bei dem der Tester das System frei und ohne festgelegte Fälle prüft", true),
                new Answer(null, "Ein Test nur für geografische Anwendungen", false),
                new Answer(null, "Ein vollständig automatisierter Test", false),
                new Answer(null, "Ein Test, bei dem ausschließlich Anforderungen geprüft werden", false)
        ));

        createAnswersForQuestion("Wie wird ein Fehler im Fehlerbericht dokumentiert?", List.of(
                new Answer(null, "Mit einer Liste aller Systemdaten, aber ohne Beschreibung", false),
                new Answer(null, "Durch Angabe von ID, Beschreibung, Reproduktionsschritte, Status", true),
                new Answer(null, "Nur mit einem Screenshot", false),
                new Answer(null, "Mit einer Bewertung des Testers", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen einem Fehler, einem Defekt und einem Bug?", List.of(
                new Answer(null, "Keiner, es sind verschiedene Begriffe für dasselbe", true),
                new Answer(null, "Ein Fehler ist im Code, ein Defekt in der Hardware, ein Bug im Design", false),
                new Answer(null, "Fehler ist schlimmer als ein Bug", false),
                new Answer(null, "Defekte gibt es nur in Automobilsoftware", false)
        ));

        createAnswersForQuestion("Welche Rolle spielen Reviews und Inspektionen im QS-Prozess?", List.of(
                new Answer(null, "Sie helfen, Fehler frühzeitig im Entwicklungsprozess zu finden", true),
                new Answer(null, "Sie ersetzen Unit-Tests", false),
                new Answer(null, "Sie dienen nur zur Einhaltung gesetzlicher Vorschriften", false),
                new Answer(null, "Sie werden nur nach Projektende durchgeführt", false)
        ));

        createAnswersForQuestion("Was ist eine ISO 25010-Norm?", List.of(
                new Answer(null, "Eine Sicherheitsvorschrift für Netzwerke", false),
                new Answer(null, "Ein Standard zur Bewertung der Softwarequalität", true),
                new Answer(null, "Eine Programmierregel für Java", false),
                new Answer(null, "Eine Hardwarezertifizierung", false)
        ));

        createAnswersForQuestion("Was ist ein Abnahmetest?", List.of(
                new Answer(null, "Ein Test zur Überprüfung der Stromversorgung", false),
                new Answer(null, "Ein Test zur Validierung durch den Endbenutzer vor Inbetriebnahme", true),
                new Answer(null, "Ein spezieller Test nur für Entwickler", false),
                new Answer(null, "Ein Test der Netzwerkanbindung", false)
        ));

        createAnswersForQuestion("Welche Risiken können durch mangelhafte Qualitätssicherung entstehen?", List.of(
                new Answer(null, "Höhere Kundenzufriedenheit", false),
                new Answer(null, "Weniger Fehlerkosten", false),
                new Answer(null, "Systemausfälle, Sicherheitslücken, höhere Wartungskosten", true),
                new Answer(null, "Schnellere Entwicklung ohne Konsequenzen", false)
        ));

        // IT-Recht Antworten

        createAnswersForQuestion("Was ist die DSGVO und wann gilt sie?", List.of(
                new Answer(null, "Ein US-amerikanisches Gesetz zur Netzneutralität", false),
                new Answer(null, "Ein deutsches Gesetz zur Telekommunikation", false),
                new Answer(null, "Eine EU-Verordnung zum Schutz personenbezogener Daten, gültig für alle EU-Mitgliedstaaten und Unternehmen, die Daten von EU-Bürgern verarbeiten", true),
                new Answer(null, "Ein freiwilliger Kodex für Online-Händler", false)
        ));

        createAnswersForQuestion("Welche personenbezogenen Daten sind besonders schützenswert?", List.of(
                new Answer(null, "Öffentlich zugängliche Adressdaten", false),
                new Answer(null, "E-Mail-Adressen von Unternehmen", false),
                new Answer(null, "Daten über Gesundheit, sexuelle Orientierung, religiöse Überzeugung u. a.", true),
                new Answer(null, "Nutzungsstatistiken von Webseiten", false)
        ));

        createAnswersForQuestion("Was regelt das Urheberrecht im IT-Kontext?", List.of(
                new Answer(null, "Es regelt den Zugriff auf fremde Server", false),
                new Answer(null, "Es schützt Software als geistiges Eigentum des Entwicklers", true),
                new Answer(null, "Es erlaubt die freie Nutzung von Open-Source-Code", false),
                new Answer(null, "Es verpflichtet IT-Firmen zur Lizenzvergabe", false)
        ));

        createAnswersForQuestion("Welche Anforderungen stellt die DSGVO an die Datenverarbeitung?", List.of(
                new Answer(null, "Sie muss automatisiert erfolgen", false),
                new Answer(null, "Sie muss effizient und kostensparend sein", false),
                new Answer(null, "Sie muss rechtmäßig, zweckgebunden, transparent, und auf das notwendige Maß beschränkt sein", true),
                new Answer(null, "Sie muss nur durch Behörden durchgeführt werden", false)
        ));

        createAnswersForQuestion("Was bedeutet Privacy by Design?", List.of(
                new Answer(null, "Der Nutzer darf sein eigenes Datenschutzsystem entwickeln", false),
                new Answer(null, "Datenschutz wird nachträglich in Systeme eingebaut", false),
                new Answer(null, "Datenschutz wird von Anfang an in Systeme und Prozesse integriert", true),
                new Answer(null, "Die Datenverarbeitung erfolgt nur lokal", false)
        ));

        createAnswersForQuestion("Welche Rolle spielt der Datenschutzbeauftragte in einem Unternehmen?", List.of(
                new Answer(null, "Er erstellt das IT-Budget", false),
                new Answer(null, "Er berät und überwacht die Einhaltung datenschutzrechtlicher Vorschriften", true),
                new Answer(null, "Er entwickelt neue Softwarelösungen", false),
                new Answer(null, "Er ist für den Verkauf von Nutzerdaten zuständig", false)
        ));

        createAnswersForQuestion("Was sind die rechtlichen Grundlagen für die Auftragsverarbeitung?", List.of(
                new Answer(null, "Ein mündlicher Vertrag reicht aus", false),
                new Answer(null, "Es ist keine Vereinbarung erforderlich", false),
                new Answer(null, "Ein schriftlicher Vertrag nach Art. 28 DSGVO ist erforderlich", true),
                new Answer(null, "Die Einwilligung des Mitarbeiters ist ausreichend", false)
        ));

        createAnswersForQuestion("Was ist eine Einwilligung im Sinne der DSGVO?", List.of(
                new Answer(null, "Eine mündliche Zustimmung durch Dritte", false),
                new Answer(null, "Eine freiwillige, informierte und unmissverständliche Zustimmung zur Datenverarbeitung", true),
                new Answer(null, "Eine automatische Zustimmung beim Öffnen einer Website", false),
                new Answer(null, "Eine implizite Zustimmung durch Nutzung des Internets", false)
        ));

        createAnswersForQuestion("Welche rechtlichen Risiken bestehen beim Einsatz von Open-Source-Software?", List.of(
                new Answer(null, "Keine, Open Source ist immer frei verwendbar", false),
                new Answer(null, "Es besteht das Risiko von Lizenzverletzungen und daraus resultierenden rechtlichen Konsequenzen", true),
                new Answer(null, "Open-Source-Code ist nur für Behörden erlaubt", false),
                new Answer(null, "Es besteht nur ein Sicherheitsrisiko, kein rechtliches", false)
        ));

        createAnswersForQuestion("Was bedeutet IT-Compliance?", List.of(
                new Answer(null, "Die Verwendung der neuesten Technologien", false),
                new Answer(null, "Die Einhaltung gesetzlicher, regulatorischer und interner IT-Vorgaben", true),
                new Answer(null, "Die Gestaltung benutzerfreundlicher Webseiten", false),
                new Answer(null, "Die Kontrolle von Softwarepreisen", false)
        ));

        createAnswersForQuestion("Was ist der Unterschied zwischen Verantwortlichem und Auftragsverarbeiter nach DSGVO?", List.of(
                new Answer(null, "Der Auftragsverarbeiter entscheidet über die Zwecke der Verarbeitung", false),
                new Answer(null, "Der Verantwortliche entscheidet über Zwecke und Mittel der Verarbeitung, der Auftragsverarbeiter handelt nur im Auftrag", true),
                new Answer(null, "Beide Begriffe bezeichnen dieselbe Rolle", false),
                new Answer(null, "Der Auftragsverarbeiter ist für die Einhaltung der DSGVO verantwortlich", false)
        ));

        createAnswersForQuestion("Welche Sanktionen drohen bei Verstößen gegen die DSGVO?", List.of(
                new Answer(null, "Eine lebenslange Freiheitsstrafe", false),
                new Answer(null, "Abmahnungen durch Mitbewerber", false),
                new Answer(null, "Bußgelder von bis zu 20 Millionen Euro oder 4 % des weltweiten Jahresumsatzes", true),
                new Answer(null, "Ausschluss aus der EU", false)
        ));

        createAnswersForQuestion("Was versteht man unter einem Datenschutz-Folgenabschätzungsverfahren?", List.of(
                new Answer(null, "Eine Analyse der finanziellen Folgen eines Datenverlusts", false),
                new Answer(null, "Ein Verfahren zur Bewertung der Notwendigkeit von Cookies", false),
                new Answer(null, "Eine Bewertung der Risiken für Rechte und Freiheiten bei risikoreichen Datenverarbeitungen", true),
                new Answer(null, "Eine Methode zur Ermittlung der besten Cloud-Anbieter", false)
        ));

        createAnswersForQuestion("Welche Rechte haben Betroffene laut DSGVO?", List.of(
                new Answer(null, "Recht auf Gewinnbeteiligung bei Datennutzung", false),
                new Answer(null, "Recht auf Datenübertragbarkeit, Auskunft, Löschung u.a.", true),
                new Answer(null, "Recht auf Veröffentlichung persönlicher Daten", false),
                new Answer(null, "Recht auf Zugang zu behördlichen Datenbanken", false)
        ));

        createAnswersForQuestion("Wann ist eine Datenschutzverletzung meldepflichtig?", List.of(
                new Answer(null, "Immer, wenn personenbezogene Daten gespeichert werden", false),
                new Answer(null, "Nur bei Hackerangriffen", false),
                new Answer(null, "Wenn ein Risiko für Rechte und Freiheiten der Betroffenen besteht", true),
                new Answer(null, "Nie, wenn die Daten verschlüsselt waren", false)
        ));

        createAnswersForQuestion("Was ist das Recht auf Vergessenwerden?", List.of(
                new Answer(null, "Das Recht, Daten auf einem lokalen Gerät zu löschen", false),
                new Answer(null, "Das Recht, die Löschung personenbezogener Daten zu verlangen", true),
                new Answer(null, "Das Recht, anonym im Internet zu surfen", false),
                new Answer(null, "Das Recht auf Zugang zu alten Datenbanken", false)
        ));

        createAnswersForQuestion("Welche Informationspflichten bestehen bei der Datenerhebung?", List.of(
                new Answer(null, "Es bestehen keine, wenn Daten freiwillig angegeben wurden", false),
                new Answer(null, "Nur Unternehmen über 500 Mitarbeiter müssen informieren", false),
                new Answer(null, "Betroffene müssen über Zweck, Empfänger, Rechte u.a. informiert werden", true),
                new Answer(null, "Nur bei schriftlicher Einwilligung müssen Informationen gegeben werden", false)
        ));

        createAnswersForQuestion("Was regelt das Telemediengesetz (TMG)?", List.of(
                new Answer(null, "Die Besteuerung von Online-Diensten", false),
                new Answer(null, "Den Datenschutz in sozialen Netzwerken", false),
                new Answer(null, "Die rechtlichen Rahmenbedingungen für elektronische Informations- und Kommunikationsdienste", true),
                new Answer(null, "Nur den Handel mit Softwarelizenzen", false)
        ));

        createAnswersForQuestion("Welche Anforderungen stellt die DSGVO an technische und organisatorische Maßnahmen (TOMs)?", List.of(
                new Answer(null, "TOMs sind nur bei öffentlichen Stellen notwendig", false),
                new Answer(null, "TOMs müssen angemessen sein, um ein Schutzniveau entsprechend dem Risiko zu gewährleisten", true),
                new Answer(null, "TOMs sind freiwillig", false),
                new Answer(null, "TOMs dürfen nur einmal jährlich geprüft werden", false)
        ));

        createAnswersForQuestion("Wie ist der Begriff 'Datenpanne' rechtlich definiert?", List.of(
                new Answer(null, "Ein Fehler bei der Dateneingabe", false),
                new Answer(null, "Ein Verlust oder unrechtmäßiger Zugriff auf personenbezogene Daten", true),
                new Answer(null, "Ein Stromausfall im Rechenzentrum", false),
                new Answer(null, "Ein Softwarefehler ohne Datenbezug", false)
        ));

        createAnswersForQuestion("Welche Lizenztypen gibt es bei Open-Source-Software?", List.of(
                new Answer(null, "Nur kommerzielle und private Lizenzen", false),
                new Answer(null, "GPL, MIT, Apache, BSD u.a.", true),
                new Answer(null, "Nur GPL-Lizenzen", false),
                new Answer(null, "Nur Closed-Source-Lizenzen", false)
        ));

        createAnswersForQuestion("Was ist ein Impressum und wann ist es erforderlich?", List.of(
                new Answer(null, "Ein Kontoauszug bei Unternehmen", false),
                new Answer(null, "Eine steuerliche Anmeldung", false),
                new Answer(null, "Eine Anbieterkennzeichnung auf Webseiten, erforderlich bei geschäftsmäßigem Online-Angebot", true),
                new Answer(null, "Ein Vertrag über Webdesign", false)
        ));

        createAnswersForQuestion("Welche Bedeutung hat das IT-Sicherheitsgesetz?", List.of(
                new Answer(null, "Es regelt ausschließlich militärische Cyberabwehr", false),
                new Answer(null, "Es schafft Regelungen zum Schutz kritischer Infrastrukturen und verpflichtet Unternehmen zur IT-Sicherheit", true),
                new Answer(null, "Es betrifft nur Bundesbehörden", false),
                new Answer(null, "Es ersetzt die DSGVO", false)
        ));

        createAnswersForQuestion("Was ist die ePrivacy-Verordnung und wie unterscheidet sie sich von der DSGVO?", List.of(
                new Answer(null, "Sie regelt die Rechte von Cloud-Anbietern", false),
                new Answer(null, "Sie ergänzt die DSGVO im Bereich der elektronischen Kommunikation, insbesondere bei Cookies und Tracking", true),
                new Answer(null, "Sie ist identisch mit der DSGVO", false),
                new Answer(null, "Sie gilt nur in den USA", false)
        ));

        createAnswersForQuestion("Was regelt das Urheberrechtsgesetz in Bezug auf Software?", List.of(
                new Answer(null, "Den Datenschutz bei Softwareentwicklung", false),
                new Answer(null, "Die Lizenzkosten für kommerzielle Programme", false),
                new Answer(null, "Die Rechte des Urhebers an Quellcode und Programmen", true),
                new Answer(null, "Das Vertragsrecht bei App-Entwicklung", false)
        ));

        createAnswersForQuestion("Welche Rolle spielt die Zustimmung bei der Verwendung von Cookies?", List.of(
                new Answer(null, "Sie ist nicht notwendig bei Werbecookies", false),
                new Answer(null, "Sie muss vor dem Setzen nicht eingeholt werden", false),
                new Answer(null, "Sie ist notwendig für nicht-technisch notwendige Cookies", true),
                new Answer(null, "Sie ist nur bei mobilen Webseiten notwendig", false)
        ));

        createAnswersForQuestion("Was versteht man unter IT-Forensik im rechtlichen Kontext?", List.of(
                new Answer(null, "Die Entwicklung neuer Softwarelösungen", false),
                new Answer(null, "Die Analyse von IT-Systemen zur Aufklärung von Sicherheitsvorfällen", true),
                new Answer(null, "Das Schreiben von Code unter gerichtlicher Aufsicht", false),
                new Answer(null, "Das Backup von Daten in der Cloud", false)
        ));

        createAnswersForQuestion("Was ist ein ADV-Vertrag (Auftragsdatenverarbeitungsvertrag)?", List.of(
                new Answer(null, "Ein Vertrag zwischen Kunden und Steuerberater", false),
                new Answer(null, "Ein Vertrag über den Kauf von Daten", false),
                new Answer(null, "Ein Vertrag zur Regelung der Verarbeitung personenbezogener Daten durch einen Dienstleister im Auftrag", true),
                new Answer(null, "Ein Vertrag zur Weitergabe von Gesundheitsdaten", false)
        ));

        createAnswersForQuestion("Welche Bedeutung hat das Markenrecht im digitalen Umfeld?", List.of(
                new Answer(null, "Markenrecht spielt online keine Rolle", false),
                new Answer(null, "Es schützt Domainnamen", false),
                new Answer(null, "Es schützt Marken vor unberechtigter Nutzung im Internet, z. B. durch Produktpiraterie", true),
                new Answer(null, "Es regelt die Entlohnung von Influencern", false)
        ));

        createAnswersForQuestion("Wie funktioniert das Widerrufsrecht bei digitalen Produkten?", List.of(
                new Answer(null, "Es besteht immer, unabhängig von der Nutzung", false),
                new Answer(null, "Es entfällt bei ausdrücklicher Zustimmung zum Beginn der Ausführung vor Ablauf der Widerrufsfrist", true),
                new Answer(null, "Es ist auf 90 Tage ausgeweitet", false),
                new Answer(null, "Es betrifft nur physische Datenträger", false)
        ));
    }

    private void createAnswersForQuestion(String questionText, List<Answer> answers) {
        questionService.findByText(questionText).ifPresent(question -> {
            if (answerService.getAnswersByQuestion(question.getId()).isEmpty()) {
                for (Answer answer : answers) {
                    answer.setQuestion(question);
                    answerService.createDirect(answer);
                }
            }
        });
    }

}
