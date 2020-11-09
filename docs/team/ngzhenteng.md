---
layout: page
title: Ng Zhen Teng's Project Portfolio Page
---

## Project: Jarvis

Jarvis is a desktop app for CS1101S Teaching Assistants (Avengers) to help Avengers keep track of their grading duties and consultations.
The user interacts with it using CLI and it has a GUI created with JavaFX. It is written in Java and has more than 10kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the Automatic Tab Switching feature to Jarvis.
  * What it does: Automatically switches to relevant tabs in the GUI when a command is inputted. This allows the user to quickly look at the effect of their input command.
  * Justification: This feature improves the product as it is important for the user to visualize the result of his
  /her input command. This feature also improves the cohesiveness of the application.
  * Highlights: This feature required me to fully understand the interactions between GUI and non-GUI
   components as well as the importance of abstraction principles in the implementation of new features.
   
* **New Feature**: Added the Quick Summary feature to Jarvis
     * What it does: Provides the user with a concise and efficient overview of critical information stored
      within Jarvis. Critical information includes the ungraded missions and quests, upcoming consultations and
      mastery checks as well as remaining tasks. The summary is presented as a single sentence making it easy for the
       user to digest. An example of the summary would be "Remaining - Missions: 2, Tasks: 4".
     * Justification: This feature enhances the efficiency and cohesiveness of Jarvis, enabling it to become a one-stop
      solution for keeping track of personal and CS1101S teaching related tasks. The metrics presented are essential
       as they inform the teaching assistant on what has to be completed.
     * Highlights: This feature required me to properly understand the functions of the various command-related and
      memory-related components, as well as understand the code implementation of my teammates. The process once again
       required me to apply abstraction and Object-Oriented-Programming principles in order to make the feature
        reliable, and practice internalising code written by other people.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ngzhenteng)

* **Project management**:
  * Managed milestones `v1.1` - `v1.3` on GitHub
  * Ensured the team's completion of weekly deliverables such as demo videos.

* **Enhancements to existing features**: Added the ability to view a specific student simply using student's name
 substrings.
    * What it does: Allows the user to look for a student using a portion of the full name.
    * Justification: This feature improves the product as it can be difficult to remember a student
    's full name. THe ability to search for a student using portions of the full name allows the user to achieve the
     true purpose of quickly and efficiently finding student information without the burden of memorisation.
    * Highlights: This enhancement is an enhanced version of the find feature in ab3, however this implementation
     displays all students that match the substring inputted by the user, regardless of whether the input is a full word.
    * Credits: *{adapted from ab3}*

* **Documentation**:
  * User Guide:
    * Created the first copy of the UserGuide in markdown format. [\#18](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/18)
    * Added documentation for the features `View Students`
    * Added documentation for the UI Overview [\#327](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/327)
  * Developer Guide:
    * Updated existing UML diagrams under Architecture section, to fit the modified implementation.
    * Added implementation details of the `View One Student` and `View All Students` features.
    * Added implementation details of `Automatic Tab Switch` and `Summary` feature [\#367](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/367)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#207](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/207
  ), [\#53](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/53), [\#326](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/326), [\#304](https://github.com/AY2021S1-CS2103T-W11-2/tp/pull/304)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103T-T11-4/tp/issues/249), [2](https://github.com/AY2021S1-CS2103T-T11-4/tp/issues/258), [3](https://github.com/AY2021S1-CS2103T-T11-4/tp/issues/255), [4](https://github.com/AY2021S1-CS2103T-T11-4/tp/issues/250) [5](https://github.com/AY2021S1-CS2103T-T11-4/tp/issues/253))

* **Tools**:
  * Used JavaFX libraries and CSS to create portions of the Graphic User Interface.
