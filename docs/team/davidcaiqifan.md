---
layout: page
title: David Cai Qifan's Project Portfolio Page
---

### Project: CAP5Buddy

### Project Overview
CAP5Buddy is a desktop module tracker application used to centralise key module details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

### Summary of contributions

Given below are my contributions to the project.
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *AB3's developer's guide described the concept of undo/redo commands implemented*
  
* **New Feature**: Added features that allows the user to calculate relevant CAP details.
  * What it does: Calculates the user's CAP details based on completed modules that the user has stored in the application.
  * Justification: This feature fits the purpose of the application very well because the application is academic focused. The user most likely will want to be able to calculate his/her academic details.
  * Highlights: This enhancement is much more useful when used in conjunction with other commands. For example, the `addcmodule` command was designed to enhance this feature.
<div style="page-break-after: always;"></div>     
* **New Feature**: Added command that allows the user to archive/unarchive his/her modules.
  * What it does: CAP5Buddy keeps track of two lists for its module tracker component : A _Regular_ module list , and a _Archived_ module list. This enhancement allows users to move modules between the two lists and archive/unarchive modules on command.
  * Justification: This feature fits the purpose of the application very well because the application is academic focused. The user might want to keep track of completed modules. Therefore, the user may opt to archive such modules instead to reduce clutter in the main module list.
  * Highlights: I designed this enhancement to enhance user quality of life and enhance other commands. For example, CAP calculator features may require a long list of modules and the ability to archive modules may be helpful.
* **New Feature**: Added command that allows the user to find a module and display it in the GUI
  * What it does: Allows the user to search for a module by its name.
  * Justification: This enhancement allows the user to find a module quickly and will be high useful especially when it is likely the user will be storing and accessing many modules.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=davidcaiqifan)
* **Project management**:
  * Contributed to milestone management `v1.1` - `v1.4` (5 milestones) on GitHub
  * Approved pull requests
  * Managed project meeting agendas
  
* **Enhancements to existing features**:
  * Updated the side panel to display the module assignment list (Pull request [\#577](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/577))
  * Wrote additional tests for existing features that increased coverage(Pull requests [\#549](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/549), [\#579](), [\#596](), [\#607](), [\#631](), 
  [\#634](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/634), [\#637](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/637), [\#649](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/649), [\#650](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/650))
  
* **Documentation**:
  * User Guide:
    * Added documentation for CAP calculator features, module archiving features and undo/redo features
  * Developer Guide:
    * Added design architecture details for the `Storage` component.
    * Added implementation details for undo/redo, module archiving and CAP calculator features.
   
* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#402](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/402), [\#295](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/295))
  * Reported bugs and suggestions for other teams in the class (Examples can be found in [here](https://github.com/davidcaiqifan/ped/issues))
