---
layout: page
title: Jerry Ho's Project Portfolio Page
---

## Project: ProductiveNUS

ProductiveNUS is a desktop application created for School of Computing students studying in the National University of Singapore (NUS) to manage and schedule their academic tasks more effecively.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=printinghelloworld)

* **New Feature**: Added the ability to mark assignments as done and undone.
  * What it does: Allows the user to mark (multiple) completed assignments as done and uncompleted assignments as undone. Allows the user to easily differentiate between completed assignments and uncompleted assignments simply by looking at the Graphical User Interface (GUI). 
  * Justification: This feature improves the product because a user can now keep track of the assignments that he/she has completed and focus on those that are not yet completed. In the event that the user realised that he/she an uncompleted assignment marked as done, he/she can simply remove the done status of the assignment using the undone command.
  * Highlights: This enhancement affects several other classes and some of the existing features. The implementation required changes to the existing GUI, CommandParser and AssignmentBuilder.
  * Credits: Some reference was made to RemindCommand*

* **Enhancements to existing features**:
  * Updated the Add Assignment feature to handle the new features (Remind & Priority) added by my teammates (Pull requests [\#74](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/74), [\#154](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/154))
  * Updated the Help feature to make it informative by providing a comprehensive summary of the features in our project (Pull requests [\146](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/146), [\233](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/233))
  * Updated the Remind feature and made the error messages more informative (added support for multiple remind) (Pull request [\])

* **Team-based tasks contributions**:
  * Removed multiple classes and attributes from the existing classes to facilitate the morphing process of our project from an addressbook to a task scheduler and manager. (Pull requests [\66](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/66), [\67](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/67))
  * Renamed Deadline class to Time class as Time is a more suitable name for the parent Task class. (Pull request [\230](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/230))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Done` and `Undone` (Pull request [\#135](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/135))
    * Added documentation for the features `Help` and `Clear` (Pull request [\#166](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/166))
    * Added documentation for the FAQ section: (Pull requests [\#166](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/166), [\#182](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/182))
    
  * Developer Guide:
    * Added implementation details of the `Add assignment` feature. (Pull request [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279))
    * Added implementation details for the `Mark assignments as done` feature. (Pull request [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279)) 
    * Modified implementation details for the `Set reminders to assignments` feature (Combined it together with `Mark assignments as done` feature). (Pull request [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
