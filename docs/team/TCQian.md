---
layout: page
title: John Doe's Project Portfolio Page
---

## Project: MainCatalogue Level 3

MainCatalogue - Level 3 is a desktop main catalogue application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Model modification**: Refactored some attributes for Person in AB3 to attributes for Project in Taskmania.
  * What it means: refactors person.Phone -> project.Deadline, person.Email -> project.RepoUrl
  * What changes made: 
    * refactored based on attributes for Person in AB3;
    * changed all methods that has dependency on relevant attributes;
    * updated test cases accordingly.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Set up GitHub organization of team repository

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for advanced task-related features.
  * Developer Guide:
    * Came up with the outline of Use Cases.
    * Wrote the Use Cases for first draft of ProjectProfile Tracking System.
    * Wrote the Use Cases for first draft of Team Members Tracking System.

* **Community**:
  * Set up milestones in GitHub

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
