---
layout: page
title: Joshua Wong's Project Portfolio Page
---

## Project: CAP5Buddy

### Project Overview
CAP5Buddy is a desktop module tracker application used to centralise key module details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.
### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=murtubak)

Given below are my contributions to the project.
* **New Feature**: Added the assignments and grades feature to the module.
  * What it does: Allows the user to store and represent their graded assignments for the module.
  * Justification: This feature improves the product significantly because it fulfills the user's need for storing and tracking information about their module.
  * Highlights: This enhancement was particularly challenging despite its similarity to how a Person was created/edited/deleted in `Addressbook-level3`.
    The assignments had to be stored within a module class which meant that the methods used for `Module` had to be recreated for `Assignment` and had to function within
    the `Module` component.
  * Credits: *`Addressbook-level3`'s `Person` class and their commands and parsers.*
  
* **Enhancements to existing features**:
  * Refactored basic commands for Module when we shifted our project to brownfield implementation (Pull request [\#200](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/200))
  * Updated the side panel to display the module lessons and zoom links hashmap (Pull request [\#569](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/569), [\#586](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/586))
  * Wrote additional tests for existing features(Pull requests [\#602](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/602)), increasing coverage by 6.73% (Pull Request [\#651](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/651))
  
* **Documentation**:
  * User Guide:
    * Added documentation for GradeTracker commands.
  * Developer Guide:
    * Added documentation for GradeTracker commands.
    * Added UML diagrams for components of the `Model`, `ContactList` and `ModuleList`.
   
* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#209](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/209), [\#596](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/596))
  * Reported bugs and suggestions for other teams in the class (Examples can be found in [here](https://github.com/murtubak/ped/issues))
