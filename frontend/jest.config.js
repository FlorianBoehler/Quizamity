module.exports = {
  testMatch: [
    "**/?(*.)+(spec|test).[tj]s?(x)", // Sucht nach Testdateien wie .test.js, .test.jsx, .spec.js, .spec.jsx
    "**/tests/**/*.[tj]s?(x)", // Sucht im tests-Ordner
  ],
  testPathIgnorePatterns: [
    "\\node_modules\\", // Ignoriert den node_modules-Ordner
  ],

  verbose: true, // Zeigt detailliertere Testausgaben an
  testEnvironment: "jsdom",
  transform: {
    "^.+\\.[t|j]sx?$": "babel-jest", // Wandelt JSX und JS mit Babel um
  },
};
