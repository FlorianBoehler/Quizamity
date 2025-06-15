package com.quizamity.init;

import com.quizamity.model.Category;
import com.quizamity.model.Question;
import com.quizamity.model.User;
import com.quizamity.service.CategoryService;
import com.quizamity.service.QuestionService;
import com.quizamity.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;
import jakarta.inject.Inject;

import java.util.List;

@Startup
@Singleton
@DependsOn({"CategoryInitializer", "UserInitializer"})
public class QuestionInitializer {

    @Inject
    private QuestionService questionService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        User student = userService.findByUsername("student")
                .orElseThrow(() -> new IllegalStateException("Student user not found"));
        User moderator = userService.findByUsername("moderator")
                .orElseThrow(() -> new IllegalStateException("Moderator user not found"));

        addQuestionsForCategory("IT-Projektmanagement", List.of(
                "Was ist ein Gantt-Diagramm?",
                "Welche Phasen umfasst der klassische Projektlebenszyklus?",
                "Was beschreibt der kritische Pfad in einem Projektplan?",
                "Was ist ein Lastenheft?",
                "Welche Methode wird bei agilen Projekten zur Sprintplanung verwendet?",
                "Was ist der Unterschied zwischen Meilenstein und Liefergegenstand?",
                "Welche Rolle hat ein Product Owner in Scrum?",
                "Was ist ein Projektstrukturplan (PSP)?",
                "Wie hilft ein Burndown-Chart beim Projektcontrolling?",
                "Was bedeutet das magische Dreieck des Projektmanagements?",
                "Was ist der Unterschied zwischen einem Projekt und einer Linie?",
                "Welche Aufgaben hat ein Projektleiter?",
                "Was versteht man unter Scope Creep?",
                "Was ist ein Kanban-Board und wie wird es eingesetzt?",
                "Wie funktioniert die Earned Value Analyse (EVA)?",
                "Was ist der Zweck eines Kick-off-Meetings?",
                "Was versteht man unter einem Stakeholder im Projektmanagement?",
                "Was ist ein Risiko-Managementplan?",
                "Welche Bedeutung hat die RACI-Matrix in Projekten?",
                "Wie unterscheiden sich agile und klassische Projektmanagement-Methoden?",
                "Was ist ein MVP (Minimum Viable Product) im Projektkontext?",
                "Welche Vorteile bietet das Timeboxing in Projekten?",
                "Was ist ein Change Request im Projektverlauf?",
                "Was beschreibt die MoSCoW-Priorisierung?",
                "Wie wird der Fortschritt eines Projekts gemessen?",
                "Was sind typische Gründe für das Scheitern von IT-Projekten?",
                "Welche Rolle spielt die Kommunikation im Projektteam?",
                "Was ist der Unterschied zwischen Aufwand und Dauer in einem Projektplan?",
                "Wie funktioniert ein Daily Stand-up Meeting?",
                "Was ist ein Sprint Review in Scrum?"
        ), student, moderator);

        addQuestionsForCategory("Qualitätssicherung im Softwareprozess", List.of(
                "Was versteht man unter statischer Codeanalyse?",
                "Welche Aufgaben hat ein Software-Testmanager?",
                "Was ist der Unterschied zwischen Verifikation und Validierung?",
                "Welche Teststufen gibt es im V-Modell?",
                "Was versteht man unter Testautomatisierung?",
                "Welche Ziele verfolgt ein Review im Softwareprozess?",
                "Was ist ein Regressionstest?",
                "Was beschreibt der Begriff 'Testabdeckung'?",
                "Welche Rolle spielt die Qualitätssicherung in agilen Methoden?",
                "Was ist eine Fehlermöglichkeits- und -einflussanalyse (FMEA)?",
                "Was ist der Unterschied zwischen White-Box- und Black-Box-Testing?",
                "Welche Arten von Softwaretests gibt es?",
                "Was versteht man unter einem Testfall?",
                "Was ist eine Testumgebung?",
                "Was ist der Zweck eines Unit-Tests?",
                "Wie funktioniert ein Integrationstest?",
                "Was sind typische Metriken in der Qualitätssicherung?",
                "Was bedeutet der Begriff 'Defect Density'?",
                "Welche Bedeutung hat Continuous Integration für die Qualitätssicherung?",
                "Was ist ein Smoke-Test?",
                "Was sind die Vorteile von Test Driven Development (TDD)?",
                "Was ist eine Code Coverage Analyse?",
                "Welche Bedeutung hat ein Traceability-Matrix?",
                "Was ist ein Explorativer Test?",
                "Wie wird ein Fehler im Fehlerbericht dokumentiert?",
                "Was ist der Unterschied zwischen einem Fehler, einem Defekt und einem Bug?",
                "Welche Rolle spielen Reviews und Inspektionen im QS-Prozess?",
                "Was ist eine ISO 25010-Norm?",
                "Was ist ein Abnahmetest?",
                "Welche Risiken können durch mangelhafte Qualitätssicherung entstehen?"
        ), student, moderator);

        addQuestionsForCategory("IT-Recht", List.of(
                "Was ist die DSGVO und wann gilt sie?",
                "Welche personenbezogenen Daten sind besonders schützenswert?",
                "Was regelt das Urheberrecht im IT-Kontext?",
                "Welche Anforderungen stellt die DSGVO an die Datenverarbeitung?",
                "Was bedeutet Privacy by Design?",
                "Welche Rolle spielt der Datenschutzbeauftragte in einem Unternehmen?",
                "Was sind die rechtlichen Grundlagen für die Auftragsverarbeitung?",
                "Was ist eine Einwilligung im Sinne der DSGVO?",
                "Welche rechtlichen Risiken bestehen beim Einsatz von Open-Source-Software?",
                "Was bedeutet IT-Compliance?",
                "Was ist der Unterschied zwischen Verantwortlichem und Auftragsverarbeiter nach DSGVO?",
                "Welche Sanktionen drohen bei Verstößen gegen die DSGVO?",
                "Was versteht man unter einem Datenschutz-Folgenabschätzungsverfahren?",
                "Welche Rechte haben Betroffene laut DSGVO?",
                "Wann ist eine Datenschutzverletzung meldepflichtig?",
                "Was ist das Recht auf Vergessenwerden?",
                "Welche Informationspflichten bestehen bei der Datenerhebung?",
                "Was regelt das Telemediengesetz (TMG)?",
                "Welche Anforderungen stellt die DSGVO an technische und organisatorische Maßnahmen (TOMs)?",
                "Wie ist der Begriff 'Datenpanne' rechtlich definiert?",
                "Welche Lizenztypen gibt es bei Open-Source-Software?",
                "Was ist ein Impressum und wann ist es erforderlich?",
                "Welche Bedeutung hat das IT-Sicherheitsgesetz?",
                "Was ist die ePrivacy-Verordnung und wie unterscheidet sie sich von der DSGVO?",
                "Was regelt das Urheberrechtsgesetz in Bezug auf Software?",
                "Welche Rolle spielt die Zustimmung bei der Verwendung von Cookies?",
                "Was versteht man unter IT-Forensik im rechtlichen Kontext?",
                "Was ist ein ADV-Vertrag (Auftragsdatenverarbeitungsvertrag)?",
                "Welche Bedeutung hat das Markenrecht im digitalen Umfeld?",
                "Wie funktioniert das Widerrufsrecht bei digitalen Produkten?"
        ), student, moderator);
    }

    private void addQuestionsForCategory(String categoryName, List<String> questionTexts, User student, User moderator) {
        Category category = categoryService.findByName(categoryName)
                .orElseThrow(() -> new IllegalStateException("Category '" + categoryName + "' not found"));

        for (String text : questionTexts) {
            questionService.findByText(text).orElseGet(() -> {
                Question q = new Question(text, 3, category, student, moderator, true);
                questionService.createDirect(q);
                return q;
            });
        }
    }
}
