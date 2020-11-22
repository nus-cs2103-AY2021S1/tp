---
layout: page
title: Low Jie Feng's Project Portfolio Page
---

## Project: Hall-y

Hall-y is a hall residents contact management application. It helps to keep track and update the residents' record, so the hall admin staff can easily find the residents based on the saved records.

Given below are my contributions to the project:

* **New Feature**: Adding of hall events
  * What it does: Allows the user to create hall events.
  * Justification: This feature allows users to keep track of events that are happening in the halls.
  * Highlights: This enhancement required a new Event Module to be created - i.e. being able to model hall events in the memory. It is also required to be saved to the hard drive.
    This was more challenging than expected as I had to study the call stack of the various commands related to the Person module. The need to save the data to the hard drive also added complexity as I had to have a rough understanding of the Jackson library.
    There was also a need to find a way to save the event's attendees. Fortunately, the team discussed and the use of matriculation number would be the most apt as it should uniquely identify each individual.
  * Credits: The code closely resembles the existing Person Module.
  
* **New Feature**: Deleting of hall events
  * What it does: Allows the user to delete hall events.
  * Justification: This feature allows users to remove existing events that are already over or have been canceled.
  * Highlights: This requirement relies on the event's index in the displayed events list. But, the UI and the command had to be developed concurrently.
  * Credits: The code closely resembles the existing DeleteCommand.
  
* **New Feature**: Editing of hall events
  * What it does: Allows the user to edit hall events.
  * Justification: This feature allows users to edit events that is being tracked within the application.
  * Credits: The code closely resembles the existing EditCommand.

* **Code contributed**: 
  * [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lowjiefeng1998)

* **Project management**:
  * Managed milestones `v1.1` and `v1.2` on Github

* **Enhancements to existing features**:
  * Updated the UI so that it uses `BorderPane` instead of various `StackPane` - this made it easier to understand the UI layout

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add-event`, `delete-event`, `edit-event`: [\#223](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/223)
  * Developer Guide:
    * Updated the class diagrams for the `Model` and `Storage` components [\#291](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/291)
    * Added implementation details for the `add-event` feature: [\#110](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/110)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#67](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/67), [\#126](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/126)
  * Reported bugs and gave suggestions for other teams in the class (example: [#6](https://github.com/lowjiefeng1998/ped/issues/6))
