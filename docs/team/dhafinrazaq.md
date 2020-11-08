---
layout: page
title: Dhafin Razaq Oktoyuzan's Project Portfolio Page
---

## Project: FitEgo

FitEgo is a desktop application for fitness instructors to schedule, and keep track of his/her customers' progress in one place.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add and delete schedules. (Pull Request [#81](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/81), [#96](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/96))
  * What it does: allows the user to create and delete schedules. A schedule contains information about a client and the attended session. Therefore, adding a schedule means that the specified client will attend the specified session. 
  * Justification: This feature is core to the product because the user needs to be able to associate a client to a session. This is necessary for the later enhancements (implemented by other members) in the project such as the ability of tracking the sessions attended by a client and tracking which clients will attend a session.
  * Highlights: Before implementing this feature, a Schedule model must be implemented first. When implementing the Schedule model (which is then implemented as an association class of client and session) and how it should be stored in the storage, in-depth design analysis were needed.
  * Credits: *{To some extent, Add and Delete Schedule command reused code from Add and Delete Session}*

* **New Feature**: Added the payment tracking feature (PR [#137](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/137))
  * What it does: allows the user to easily spot whether a client has paid for a session.
  * Justification: Associate each schedule (which contains a client and session) to a payment status (either paid or unpaid) and modify the right side bar and client detail's table to show the payment status.
  * Highlights: The implementation requires a good understanding in UI (including JavaFX and CSS coloring) and how it relates to the logic as in both Session List panel and client detail's table, different color will be displayed depending on the payment status.
  * Credits: *{Credit to Kelvin for creating the right side bar and Bennett for creating client's detail table}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=dhafinrazaq&tabRepo=AY2021S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)

* **Project management or team-based tasks**:
  * Contributed to `v1.3` demo for tutorial and non-feature-specific documentations
  * Maintained issue tracker

* **Enhancements to existing features**:
  * Modified and enhanced `cedit` from `edit` command. It will automatically show the edited Client detail after executing a `cedit` command (PR [#53](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/53), [\#165](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/165))
  * Modified Session model to use interval as unique identifier and its relation to Storage (PR [#95](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/95))
  * Added test cases for Schedule model (PR [#158](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/158))
  * Adapted saving to Storage to include Schedule objects (PR [#74](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/74))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `schadd` and `schdel`. (PR [\#143](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/143))
    * Added documentation for the feature `cedit`. (PR [\#242](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/242))
    * Added information on duplicate client. (PR [\#230](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/230))
  * Developer Guide:
    * Added MSS and manual testing instructions for `schadd` and `schdel` feature. (PR [\#149](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/149))
    * Added implementation details of the `schadd` feature. (PR [\#149](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/149))
    * Updated the diagram of the Storage component. (PR [\#149](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/149))

* **Review or mentoring contributions**:
  * Discuss design and implementation details with group mates.
  * Discuss bugs and proposals during team meeting or via issues (example: [issue \#141](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/141), [issue \#174](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/174)).
  * Review and comment on PRs regarding code quality (PR [#91](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/91)), implementation bug (PR [#226](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/226)), and other improvements.

* **Community**:
  * Asked questions in Gitter, one of which was regarding code integration.
  * Beyond this team: reported bugs and suggestion for other team via CATcher (examples: PR [#188](https://github.com/AY2021S1-CS2103T-W17-3/tp/issues/188))

