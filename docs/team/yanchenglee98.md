---
layout: page
title: Lee Yan Cheng's Project Portfolio Page
---

## Project: Hall-y

Hall-y is a hall residents' contact management application. It helps to keep track and update the residents' record, so the hall admin staff can easily find the residents based on the saved records.

Given below are my contributions to the project.

* **New Feature**: Exporting of residents' details (i.e. email)
  * What it does: Allows the user to export the list of residents' emails.
  * Justification: This feature allows users to quickly export the list of emails to efficiently send a mass email when required.
  * Highlights: This enhancement required it to be adaptive to the current resident list. It also required it to write to the hard drive. 
  * Credits: java.io

* **New Feature**: Assigning of residents to events
  * What it does: Allows the user to assign residents to events.
  * Justification: This feature helps users to keep track of which residents are attending which event, effectively creating a nominal roll.
  * Highlights: This enhancement required it to modify the attendee list of Event class in a way that still preserved immutability. 

* **New Feature**: Clearing attendee list of events
  * What it does: Allows the user to clear the attendee list of events.
  * Justification: This feature helps users to quickly clear the attendee list to allow re-population of the event for future reuse. 

* **New Feature**: Filtering of residents by events
  * What it does: Allows the user to list the attendees of a specific event.
  * Justification: This feature allows users to view the details of the attendees of a specific event and allows them to export their emails if required.
  * Highlights: This enhancement required it to access the Event's attendee list which caused it to have a slightly different logic from the Find command. Thus, a entirely new command had to be created to allow this command to access the specified event's attendee list and create a new predicate.

* **New Feature**: Adding the Event list to the UI
  * What it does: Allows the user to see the list of events and any updates to it.
  * Justification: This feature allows users to easily view events and check the attendee list of an event in a glance.
  * Highlights: This enhancement required it to work with JavaFX's ObservableList. Thus, any changes to the Event class would require the list of events to be updated through set, add or delete methods in order for the UI to pick up the new changes.
  * Credits: JavaFX

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yanchenglee98)

* **Project management**:
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
  * Managed and closed milestone `v1.3b` on GitHub
  
* **Enhancements to existing features**:
  * Updated the URL in the help window (Pull requests [\#61](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/61))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `assign`,`clear-event-attendees` `export` and `list-event-attendees`: [\#43](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/43), [\#140](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/140)
    * Updated existing documentation of features `delete`, `help` and `list`: [\#166](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/166/files), [\#182](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/182)
  * Developer Guide:
    * Added implementation details of the `export` feature: [\#103](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/103)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#66](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/66), [\#104](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/104), [\#110](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/110), [\#111](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/111), [\#131](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/131)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/yanchenglee98/ped/issues))
