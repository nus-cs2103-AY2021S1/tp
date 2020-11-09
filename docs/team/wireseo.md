---
layout: page
title: Seo Yeryeon's Project Portfolio Page
---

## Project: Jarvis

Jarvis is a desktop app for CS1101S Teaching Assistants (Avengers) to help Avengers keep track of their grading duties and consultations.
The user interacts with it using CLI and it has a GUI created with JavaFX. It is written in Java and has more than 10kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view Consultations and Mastery Checks.
  * What it does: allows the user to view all/past/upcoming consultations or mastery checks.
  * Justification: This feature improves the product significantly because the user needs to view consultation appointments created within the software and easily switch between past, current and future views.
  * Highlights: Due to design concerns, the class structure of Student and Consultations had to be changed multiple times which resulted in multiple attempts to refactor and restructure, as well as rewriting all the tests. It required an in-depth analysis of design alternatives. Looking at the past/upcoming consultations also required the use of Java Streams, Optional and Predicate classes. Regarding the GUI, I implemented the Card, List, ListPanel classes for both classes as well as the fxml.

* **New Feature**: Added the ability to add and delete Consultations and Mastery Checks, which are automatically saved and loaded.
  * What it does: allows the user to add and delete consultations or mastery checks.
  * Justification: This feature improves the product significantly because the user needs to add and delete Consultation or Mastery Check appointments created within the software to manage their schedule.
  * Highlights: This enhancement required the construction of multiple new classes and tests (for both Consultation and Mastery Check variants of AddCommand, DeleteCommand, JsonAdapted, Builder, and various exceptions) that did not exist in the AB3 codebase that had to be designed from scratch and a sizable amount of modifications to the existing AB3 codebase.

* **New Feature**: Added the ability to edit Mastery Check scores.
  * What it does: allows the user to edit Mastery Checks scores to either pass (1) or fail (0).
  * Justification: This feature improves the product significantly because the user needs to edit Mastery Check scores to keep track of the students' grades.
  * Highlights: This enhancement required the construction of multiple new classes and tests (for both Consultation and Mastery Check variants of EditCommand, EditDescriptor and also TypicalMasteryChecks, TypicalConsultations etc.) that did not exist in the AB3 codebase and had to be designed from scratch.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wireseo)
* **Project management**:
  * In charge of implementation of all Consultations and Mastery Check related features and tests
  * Oversaw the implementation and design of the GUI
  * Ensured that project deliverables are delivered on time
  * Refactored and restructured classes to appropriate packages

* **Enhancements to existing features**:
  * Designed the new and improved GUI for Jarvis: [\#12](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/12)
  * Implemented and tested JsonAdaptedMasteryChecks and JsonAdaptedConsultations to save and load additional data: [\#217](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/217), [\#320](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/320)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `view -c`, `view -cp`, `view -cu`, `view -mc`, `view -mcp`,  `view -mcu`, `add -c`, `add -mc`, `delete -c`, `delete -mc`, and `edit -mc`: [\#34](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/34), [\#348](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/348), [\#292](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/292), [\#340](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/340)
    * Added explanation for Mastery Checks and Consultations in the Glossary: [\#246](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/246)
  * Developer Guide:
    * Added implementation details of the `delete` feature, including a sequence diagram, two class diagrams, and a path diagram: [\#341](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/341)
    * Added relevant use cases and user stories in the Appendix: [\#342](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/342)
    * Added the Effort Appendix: [\#387](https://github.com/AY2021S1-CS2103T-W11-2/tp/issues/387)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#371](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/371), [\#383](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/383#pullrequestreview-526100087), [\#290](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/290#pullrequestreview-522476413)
  * Reported bugs and suggestions for other teams in the class: [Dry Run PE](https://github.com/wireseo/ped/issues)
