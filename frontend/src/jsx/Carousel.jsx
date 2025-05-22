import { useState} from "react";
import SlideThemenauswahl from "./SlideThemenauswahl.jsx";
import SlideSpielmodus from "./SlideSpielmodus.jsx";
import SlideLobby from "./SlideLobby.jsx";
import SlideAnzahlFragen from "./SlideAnzahlFragen.jsx";
import SlideWarteraum from "./SlideWarteraum.jsx";

export default function Carousel() {
  const [step, setStep] = useState(0);

  // States für Pflichtfelder je Slide (können auch in Slides bleiben, hier nur Beispiel)
  const [selectedTopic, setSelectedTopic] = useState("");
  const [selectedMode, setSelectedMode] = useState("");
  const [selectedLobby, setSelectedLobby] = useState("");
  const [selectedQuestionCount, setSelectedQuestionCount] = useState(20);
  const [readyInLobby, setReadyInLobby] = useState(false);

  // Prüfen, ob "Next" erlaubt ist für aktuellen Step
  const canGoNext = () => {
    switch (step) {
      case 0:
        return selectedTopic !== "";
      case 1:
        return selectedMode !== "";
      case 2:
        return selectedLobby !== "";
      case 3:
        return selectedQuestionCount >= 10;
      case 4:
        return readyInLobby === true;
      default:
        return false;
    }
  };

  // Step wechseln
  const next = () => {
    if (canGoNext() && step < 4) setStep(step + 1);
  };
  const prev = () => {
    if (step > 0) setStep(step - 1);
  };

  return (
    <main>
      <div id="carouselExampleCaptions" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-indicators">
          {[0, 1, 2, 3, 4].map((index) => (
            <button
              key={index}
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to={index}
              className={index === step ? "active" : ""}
              aria-current={index === step ? "true" : undefined}
              aria-label={`Slide ${index + 1}`}
              onClick={() => setStep(index)}
            />
          ))}
        </div>

        <div className="carousel-inner">
          <div className={`carousel-item ${step === 0 ? "active" : ""}`}>
            <SlideThemenauswahl selected={selectedTopic} onChange={setSelectedTopic} />
          </div>
          <div className={`carousel-item ${step === 1 ? "active" : ""}`}>
            <SlideSpielmodus selected={selectedMode} onChange={setSelectedMode} />
          </div>
          <div className={`carousel-item ${step === 2 ? "active" : ""}`}>
            <SlideLobby selected={selectedLobby} onChange={setSelectedLobby} />
          </div>
          <div className={`carousel-item ${step === 3 ? "active" : ""}`}>
            <SlideAnzahlFragen value={selectedQuestionCount} onChange={setSelectedQuestionCount} />
          </div>
          <div className={`carousel-item ${step === 4 ? "active" : ""}`}>
            <SlideWarteraum ready={readyInLobby} onChange={setReadyInLobby} />
          </div>
        </div>

        <button className="carousel-control-prev" type="button" onClick={prev} disabled={step === 0}>
          <span className="carousel-control-prev-icon" aria-hidden="true" />
          <span className="visually-hidden">Previous</span>
        </button>

        <button
          className="carousel-control-next"
          type="button"
          onClick={next}
          disabled={!canGoNext()}
        >
          <span className="carousel-control-next-icon" aria-hidden="true" />
          <span className="visually-hidden">Next</span>
        </button>
      </div>
    </main>
  );
}
