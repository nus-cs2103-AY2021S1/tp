---
layout: page
title: Gabriel Tan's Project Portfolio Page
---

## Project: Productiv

Productiv is a one-stop desktop app for product managers like yourself to organise your contacts, deliverables and 
meetings, so that you can track your product’s development easily. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=gabztcr)

##### New features and enhancements

* **New Feature**: Deliverables (specifically add, edit and delete commands).
  * What it does: This feature allows the user to track their product's deliverables easily with basic CRUD features.
  * Justification: This key feature improves the product significantly because the user is a product manager who needs to have his deliverables organised so that he can work better towards production deadlines.
  * Highlights: While this mode has its own UI screen in Productiv, the deliverable list itself would eventually be referenced in the dashboard. 
  As such, the implementation had to be done carefully with forward-thinking to ensure that such referencing would be smooth and adhere to coding standards, e.g. abstractions.
  * Credits: Most of the code was reused from the original AddressBook, less minor tweakings for deliverable fields and test cases.

* **New Feature**: Overall Completion Percentage (OCP).
  * What it does: This feature gives the user an overview of the progress of the product' development using a donut chart. It is found in the left panel of the dashboard.
  * Justification: This feature enhances the product greatly because the user is able to immediately see how much of the product is completed and adjust their priorities accordingly to meet production deadlines.
  * Highlights: The OCP's colour and animation were adjusted to suit the app and make its general UI more appealing to users.
  Although the library and its tutorial were available, implementing it was very challenging since the code bases were very different.
  * Credits: The third-party library [fx-progress-circle](https://github.com/torakiki/fx-progress-circle/) and its [tutorial](https://youtu.be/9SEE8UP17jo) were used to implement this feature. 

* **Enhancements to existing features**:
  * Implement autosort feature for deliverables that sorts them chronologically by deadline (PR [\#123](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/123)).
  * Disabled clearing/listing of empty deliverable, meeting or contact lists (PR [\#240](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/240)).
  * Enabled `find` command to accommodate punctuations when matching keywords to deliverables, meetings or contacts (PR [\#250](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/250)).

##### Contributions to team-based tasks
  * Created a shortened URL link of the User Guide for the GUI's help window.
  * Wrote additional tests for deliverables to increase coverage (PRs [\#112](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/112), [\#123](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/123)).
  * Amended all error messages for the feedback box in Productiv (PR [\#228](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/228)).
  * Amended the alignment of and ordering within the UI of Productiv (PR [\#256](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/256)).

##### Project management

  * Set up [GitHub Projects](https://github.com/AY2021S1-CS2103T-F11-2/tp/projects/1) to track issues and PRs for Productiv.
  * Hosted two sprint planning meetings to sync up on issues and discuss technical solutions.
  * Managed milestone `mid-v1.4`.
  * Created a timetable for PR merging to minimise merge conflicts when the team was working on our documentation together.

##### Documentation

  * User Guide:
    * Created the skeleton version from AddressBook (PR [\#29](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/29)).
    * Added documentation for `list`, `find` and `clear` commands for contacts (PR [\#99](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/99)).
    * Updated the notes about the command format under Features (PRs [\#214](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/214), [\#265](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/265)).
    * Reordered the fields for all existing commands (PR [\#256](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/256)).
    * Oversaw the general accuracy, consistency and formatting.
  * Developer Guide:
    * Added implementation details of the OCP feature (PR [\#107](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/107)).
    * Amended User Stories and Use Cases (PR [\#265](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/265)).

##### Community

  * Reviewed PRs with non-trivial comments (PRs [\#188](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/188), [\#189](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/189)).
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/gabztcr/ped/issues/7), [2](https://github.com/gabztcr/ped/issues/6), [3](https://github.com/gabztcr/ped/issues/5)).
