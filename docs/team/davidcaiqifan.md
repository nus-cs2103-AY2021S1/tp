---
layout: page
title: David Cai Qifan's Project Portfolio Page
---

## Project: Cap 5.0 Buddy

##Project Overview
Cap 5 Buddy is a desktop module tracker application used to centralise key module details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

![Ui](../images/Ui.png)
Figure 1. UI of Cap 5 Buddy.

##Summary of contributions
Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *AB3's developer's guide described the concept of undo/redo commands, and I made use of the concepts mentioned*

* **New Feature**: Added command that allows the user to calculate his/her CAP(Cumulative Average Point).
  * What it does: Calculates the user's CAP based on completed modules that the user has stored in the application.
  * Justification: This feature improves the product significantly and fits the purpose of the application very well because the application is academic focused. The user most likely will want to be able to assess his/her academic score which in this case CAP5Buddy calculates as CAP.
  * Highlights: This enhancement is much more useful when used in conjunction with other commands. Therefore, it required careful design considerations in order to make the feature work best with related commands. For example, I designed the `addcmodule` command to make this enhancement better.

* **New Feature**: Added command that allows the user to archive/unarchive his/her modules.
  * What it does: CAP5Buddy keeps track of two lists for its module tracker component : A _Regular_ module list , and a _Archived_ module list. This enhancement allows users to move modules between the two lists and archive/unarchive modules on command.
  * Justification: This feature improves the product significantly and fits the purpose of the application very well because the application is academic focused. It is likely that the user wants to keep track of some modules that do not have information that need to be accessed as frequently. Therefore, the user may opt to archive such modules instead to reduce clutter in the main module list.
  * Highlights: I designed this enhancement to enhance user quality of life and alleviate issues of other commands. For example, the `calculatecap` command feature requires a often large list of modules to be of practical use and this enhancement prevents cluttering of the _regular_ module list.
  
* **New Feature**: Added command that allows the user to find a module and display it in the GUI
  * What it does: Allows the user to search for a module by its name.
  * Justification: This enhancement allows the user to find a module quickly and will be high useful especially when it is likely the user will be storing and accessing many modules.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. 

* **New Feature**: Added command that allows the user receive relevant details on how to achieve his/her target CAP.
  * What it does: Calculates the CAP needed for planned modules to achieve target cap specified. Also displays the planned amount of modular credits.
  * Justification: This feature improves the product significantly and fits the purpose of the application very well because the application is academic focused. The user likely want to be able to plan his/her future academic score.
  * Highlights: This enhancement required careful design considerations because it made use of multiple properties across modules. It also required certain mathematical formulas to be crafted in which made it more challenging than usual to implement.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=davidcaiqifan)
* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub
  * Approved pull requests

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Updated the side panel to display the module assignment list (Pull request [\#577]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())
  * Wrote additional tests for existing features (Pull requests [\#549](), [\#579](), [\#596](), [\#607](), [\#631](), 
  [\#634](), [\#637](), [\#649](), [\#650]())
* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo` and `redo` (Pull request [\#352]())
    * Added documentation for the features `calculatecap` and `targetcap` (Pull request [\#447]())
    * Added documentation for the feature `addcompletedmodule` (Pull request [\#463]())
    * Added documentation for the features `archivemodule`, `unarchivemodule`, `listmodule` and `viewarchive` (Pull request [\#481]())
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: (Pull request [\#TBD]())
    * Added documentation for the features `viewmodule` and `find` (Pull request [\#TBD]())
  * Developer Guide:
    * Added design architecture details for the `Storage` component. (Pull request [\#326]())
    * Added implementation details of the `undo`, `redo` feature. (Pull request [\#TBD]())
    * Added implementation details of `calculatecap` feature. (Pull request [\#299]())

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#402](), [\#295]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3](), [TBD]())

* **Tools**:[TBD]()
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
