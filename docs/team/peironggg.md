---
layout: page
title: Wu Peirong's Project Portfolio Page
---

## Project: Jarvis

Jarvis is a desktop app for CS1101S Teaching Assistants (Avengers) to help Avengers keep track of their grading duties and consultations.
The user interacts with it using CLI and it has a GUI created with JavaFX. It is written in Java and has more than 10kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to scrape Sourceacademy.nus.edu.sg for grading information.
  * What it does: Automatically logs in to SA with the user login details and parses for student names, current missions and quests to be graded.
  * Justification: This feature improves the product significantly because an Avenger can get all the grading information he needs simply by starting up the app.
  * Highlights: Memorable learning experience was implementing multithreading into the fetching from SA and updating of JavaFX's UI-update thread. A real educational experience by learning about the nuances of when threads run and how to make them run when you want them to.
  * Credits: *Google Chrome's Chrome Driver for the ability to launch a headless Chrome browser to scrape for information.*

* **New Feature**: Added an edit command that allows users to edit their login information.
  * What it does: Edits the nusnetid and password required to login so that different Avengers can access the application.
  * Justification: This allows greater portability and ease of entering login details into the app since login details can be changed on the fly.

* **New Feature**: Added an edit command that allows users to edit their student information.
  * What it does: Edits the username, telegram username and email of the fetched students from SA so that they can remain updated.
  * Justification: This allows Avengers to use the app as a one-stop hub of all information they need to carry out their TA duties.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=peironggg)

* **Project management**:
  * Managed milestones `v1.2 - v1.3` on GitHub
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Added a flags parser to parse users' commands with flags
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#170](), [\#181]())

* **Documentation**:
  * User Guide:
    * Updated UG introduction on Jarvis and its underlying workings.
    * Added documentation on how to set up Jarvis [\#150](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/150)
    * Added documentation for the features `edit student`, `edit login` [\#268](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/268)
  * Developer Guide:
    * Updated existing UML diagrams to fit new architecture.
    * Updated the color scheme for the DG.
    * Added implementation details of the `Scraper Manager` class and its accompanying class diagrams [\*123](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/123)
    * Added details of `getMissions()` function and accompanying sequence and path diagrams [\#156](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/156)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#272](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/272), [\#231](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/231), [\#208](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/208)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103T-T12-4/tp/issues/149))

* **Tools**:
  * Integrated a third party library (Selenium Chrome Driver) to the project ([\#53](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/53))
