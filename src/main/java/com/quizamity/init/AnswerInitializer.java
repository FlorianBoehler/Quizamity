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
    }

    private void createAnswersForQuestion(String questionText, List<Answer> answers) {
        questionService.findByText(questionText).ifPresent(question -> {
            if (answerService.getAnswersByQuestion(question.getId()).isEmpty()) {
                for (Answer answer : answers) {
                    answer.setQuestion(question);
                    answerService.createAnswer(answer);
                }
            }
        });
    }
}
