---
layout: page
title: Li Huankang's Project Portfolio Page
---

## Project: CAP5Buddy

### Project Overview
CAP5Buddy is a desktop module tracker application used to centralise key module details.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.
### Summary of contributions

* **New Feature**: Added the ability to create and add modules into the list.
* What it does: allows the user to store modules into the program with the given information.
* Highlights: This enhancement allows more complicated and advanced commands to be executed with the introduction of the different extensions. It required an in-depth analysis of design alternatives. The implementation was not very difficult as this is just the basic block for future functions.
* Credits: We refactored the original AB3's code to change from a AddPerson to a AddModule.

* **New Feature**: Added the Scheduler Feature.
* What it does: allows the user to store events into the program to track it in the Calendar.
* Highlights: This feature allows user to match its information with the GUI Calendar.
* Credits: I referred to the original AB3 coding style as inspiration.

* **New Feature**: Added the GUI Calendar Feature.
* What it does: links the EventList to the Calendar.
* Highlights: is able to account for leap years.
* Credits: Referred to [W14-1/tp](https://github.com/AY2021S1-CS2103-W14-1/tp/tree/master/src/main/java/seedu/address) on their FXML for the Calendar as I had problems trying to place the Labels properly.

* **New Feature**: Added Tabs window Feature in the GUI.
* What it does: able to switch between different windows in the GUI to see each of the lists.
* Highlights: is able to retain information in each window even after switching to another tab.


* **Enhancements to existing features**:
  * Updated the GUI to fit our project design as we needed to display more information. (Pull requests [\#168]('https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/168'), [\#218]('https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/218'))
  * Refactored Ui components to change the GUI display from showing a list of persons to modules. (Pull requests [\#218]('https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/218'))
  * Added and updated tests for the main application, module tracker.
  * Beautified the existing GUI.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=matthiaslhk)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub
  * Approved pull requests
  * Managed project meeting agendas
  * Lead team discussions
  * 

* **Documentation**:
  * User Guide: [User Guide](https://ay2021s1-cs2103t-f12-3.github.io/tp/UserGuide.html)
    * Added documentation for the features `addmodule`.
    * Added the UI screenshot for demonstration purposes.
    * Added documentation for all Scheduler commands.
    * Created Command Summary Tables for each feature.
    * Added GUI Snapshot.
    * Added GUI explanantion.

  * Developer Guide: [Developer Guide](https://ay2021s1-cs2103t-f12-3.github.io/tp/DeveloperGuide.html)
    * Added the UML diagram for the overall program.
    * Added the implementation for the `addmodule` feature.
    * Created the Sequence Diagrams for Logic component.
    * Added explanation for Logic Implementation.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#146]('https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/146')
  * Contributed to forum discussions (examples: [#304](https://github.com/nus-cs2103-AY2021S1/forum/issues/304), [#278](https://github.com/nus-cs2103-AY2021S1/forum/issues/278), [#249](https://github.com/nus-cs2103-AY2021S1/forum/issues/249), [#94](https://github.com/nus-cs2103-AY2021S1/forum/issues/94))
  * Reported bugs and suggestions for other teams in the class [PE Dry Run](https://github.com/MatthiasLHK/ped/issues)

