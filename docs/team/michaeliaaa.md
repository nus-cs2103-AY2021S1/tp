---
layout: page
title: Michaelia's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop app that allows doctors to manage patient records and schedule upcoming appointments.

Given below are my contributions to the project.

* **Morphing of AddressBook**:
  * Refactor Person to Patient. [\#51](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/51)

* **New Implementation**: Implemented Command History.
  * What it does: Store used commands.
  * Justification: Allows user to view their recently used commands.
  * Highlights: This implementation is useful for users, developers and testers who might not be able to replicate the exact command used. Difficulty wise, it is pretty simple as it make use of stack ADT.
  * Credits: 
    * Referred to past-year students' works to figure out the best way to implement command history and wrote it myself afterward: [BillBoard](https://github.com/AY1920S1-CS2103T-F12-4/main/blob/master/src/main/java/seedu/billboard/model/history/CommandHistory.java)

* **New Feature**: View recently used commands or command history
  * What it does: Show a list of recently used commands.
  * Justification: Allows user to view their recently used commands.
  * Highlights: Implemented the navigation shortcut, as stated below.
  * Credits: NIL
  
* **New Feature**: Added a navigation feature that allows the user to view and reuse previous commands using up/down arrow keys.
  * What it does: Display previous commands onto the command box for users to reuse.
  * Justification: Allows user to conveniently reuse commonly used previous commands and even check what they input wrong previously.
  * Highlights: Highly user-centric and is extremely convenient to use. A lot of my peers who tested our application really liked this feature and I use it a lot during testing and documentations. 
  Requires understanding of JavaFX's KeyEvent and KeyCode.
  * Credits: 
    * The implementation of such feature is inspired by: [BillBoard](https://github.com/AY1920S1-CS2103T-F12-4/main/blob/master/)
    * For understanding of KeyEvent and KeyCode:  [KeyEvent](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyEvent.html), [KeyCode](https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html)

* **New Feature**: Clear command history
  * What it does: Clear the list of recently used commands.
  * Justification: Allows user to clear the entire unwanted command history.
  * Highlights: NIL
  
* **New Feature**: Add appointment for existing patients
  * What it does: Add an appointment
  * Justification: Allows user to add appointments for existing patients.
  * Highlights: The add appointment command involves taking a snapshot of the patient's name and ic number from the provided patient index. 
  This feature is fairly tedious to implement as I have to study the existing AB3 code, implement new methods in Model's API and linking it to the UniqueAppointmentList.
  * Credits: 
    * Adopted similar solution used in existing AB3 codebase: [https://github.com/se-edu/addressbook-level3](https://github.com/se-edu/addressbook-level3)

* **New Feature**: Delete existing appointment
  * What it does: Delete an appointment
  * Justification: Allows user to deleting existing appointments.
  * Highlights: The delete appointment command is easy to implement after add appointment feature is set up properly.
  * Credits: Adopted similar solution used in existing AB3 codebase: [https://github.com/se-edu/addressbook-level3](https://github.com/se-edu/addressbook-level3)
  * Credits: NIL
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=michaeliaaa)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `history`, `clearhistory`, `up arrow arrow keys`, `addappt` and `deleteappt`. [\#59](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/59), [\#72](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/72), [\#161](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/161)
    * Did cosmetic tweaks to existing documentation: [\#177](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/177)
    * Added well-annotated screenshots: [\#263](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/263), [\#264](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/264)
  * Developer Guide:
    * Added glossary. [\#38](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/38)
    * Added implementation details and diagrams for `history`, `clearhistory` and `up down arrow keys` features. [\#100](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/100)
    * Added use cases for `addappt`, `history`, `clearhistory` and `up down arrow keys` features. [\#100](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/100)
    * Added manual testing instructions for `addappt` and `deleteappt` features. [\#100](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/100), [\#267](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/267)
    
* **Project management**:
  * Setup tP Team Organisation and Repo Setup.
        
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#235](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/235), [\#252](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/252)
  * Contributed to forum discussions (examples: [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/3), [147](https://github.com/nus-cs2103-AY2021S1/forum/issues/147))
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/Michaeliaaa/ped/issues)

