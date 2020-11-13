---
layout: page
title: Cao Wenjie's Project Portfolio Page
---

### Project: Productiv

Productiv is a one-stop desktop app for product managers like yourself to organise your eliverables, meetings and contacts, 
so that you can track your product’s development easily. 

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=shadowezz&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

##### New Features and Enhancements

* **New Feature**: Added the ability to complete deliverables.
  * What it does: This allows the user to mark specific deliverables as completed after accomplishing them.
  * Justification: This feature improves the product by allowing user to keep track of the completion status of different deliverables and 
  focus on those that are not completed yet.
  * Highlights: This enhancement is used to display the project completion percentage in the dashboard (implemented by Gabriel) so 
  that the user can have a big picture of the progress of his/her product.
  
* **New Feature**: Added the ability to mark deliverables as on-going.
  * What it does: This allows the user to revert the completion status of deliverables from completed back to on-going.
  * Justification: This feature complements the previous feature by providing the user with the flexibility of updating the completion
  status of deliverables with changing requirements. It also allows easy amendments when the user accidentally completes the wrong deliverable,
  so that he/she does not have to delete the original deliverable to create a new one. 
 
* **New Feature**: Added the view feature.
    * What it does: This allows the user to view the full details of a specific deliverable, meeting or contact.
    * Justification: This feature makes the application neater and more user-friendly as now the list of items in the left panel
    can be succinct to show only what is important to the user. Full details of each item can be displayed on the right panel using
    the view command based on user's discretion.
    * Highlights: This feature is challenging to implement as it requires a good understanding of JavaFX to ensure that the display is 
    rendered properly. Furthermore, there are also many considerations regarding how the view panel should change with each command the user
    inputs, which needs to be carefully thought out.

**Major Enhancement**:
  * Upgraded edit commands to allow clearing an optional field
    * What it does: Allows the user to clear away information from an optional field of an existing deliverable, meeting or contact through the 
    edit command.
    * Justification: As optional fields can be left empty, this feature will provide the user with the convenience of removing optional fields that 
    are no longer relevant, instead of having to delete the entire item and add it again.
    
**Enhancements to existing features**:
  * Upgraded deliverable to include a new milestone field. [\#72](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/72)
  * Updated the GUI to display the milestone and completion status of a deliverable as tags. [\#55](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/55) 

##### Contributions to team-based tasks
   * Created the skeleton code for deliverable. [\#35](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/35)
   * Implemented find, list and clear commands for deliverable. [\#116](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/116)

##### Project management
  * Led two team meetings, which involves setting the agenda and documenting what was discussed.
  * In charge of opening, closing and assigning issues for the earlier milestones.
  * Reviewed and provided constructive feedback for teammates' PR. [\#211](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/211),
  [\#229](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/229)

##### Documentation
  * User Guide:
    * Added documentation for the commands `done`, `undone` and `view`. [\#195](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/195),
    [\#260](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/260)
    * Added some FAQs [\#126](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/126)
  * Developer Guide:
    * Added description and `UiClassDiagram` of the UI component. [\#253](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/253)
    * Added implementation details of the `done` and `view` feature. Included UML diagrams `DoneCommandSequenceDiagram` and 
    `ViewCommandSequenceDiagram`. [\#253](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/253)
