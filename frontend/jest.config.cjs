module.exports = {
  testEnvironment: "jest-environment-jsdom", // Wichtig für das Testen von React-Komponenten
  transform: {
    "^.+\\.jsx?$": "babel-jest", // Sorgt dafür, dass Jest JSX richtig verarbeitet
  },
  moduleFileExtensions: ["js", "jsx", "json", "node"], // Stellt sicher, dass .jsx erkannt wird
  setupFilesAfterEnv: ["@testing-library/jest-dom"], // Fügt nützliche Jest-DOM-Erweiterungen hinzu
  testPathIgnorePatterns: ["/node_modules/", "/dist/"], // Ignoriert Tests in node_modules und dist
};
