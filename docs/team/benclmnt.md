---
layout: page
title: Bennett Clement's Project Portfolio Page
---

## Project: FitEgo

FitEgo is a desktop application for fitness instructors to schedule, and keep track of his/her customers' progress in one place.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add / delete sessions. (Relevant PR: [\#72](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/72), [\#97](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/97))
  * What it does: This feature allows the user to create and delete fitness sessions.
  * Justification: This feature is core to the product because the user needs to create a session before they can schedule a client.
  * Highlights: This enhancement required an in-depth analysis of design alternatives.
  The implementation was challenging as it deals with dates, the notion of "overlapping" dates, and handling schedules related to the session.

* **New Feature**: Added table to view a list of client's schedules
  * What it does: List client's schedules (with remarks, payment status and exercise type)
  * Justification: This feature makes it easy for the user to keep track of client's progress in one page.
  * Highlights: The implementation was challenging as it required the table to be resizable and to show updates as soon as user changes any details about the client / schedules.

* **New Feature**: Added the feature to write remarks for a schedule (Pull requests [\#140](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/140))
  * What it does: This feature allows the user to save notes / observation of the client for a given session. The remarks for each session are then shown together in table form with other client's details.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=benclmnt&tabRepo=AY2021S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)

* **Project management**:
  * Managed all releases `v1.1` - `v1.3` (4 releases) on GitHub
  * Set up the GitHub team org/repo
  * Set up tools (Gradle)
  * Maintained issue tracker by setting up milestones, label and triaging bugs

* **Enhancements to existing features**:
  * Wrote most of the tests for session model (PR: [\#140](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/140))
  * Adapted saving to storage to include session objects (PR: [\#72](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/72)) 
  * Repurposed tag to keep track of allergies / injury history (PR: [\#46](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/46))

* **Documentation**:
  * User Guide:
    * Updated the documentation for the features `sadd` and `sdel` (PR: [\#139](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/139))
    * Added an overview for FitEgo User Guide (PR: [\#139](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/139), [\#233](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/233))
    * Simplified the command summary section (PR: [\#233](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/233))
  * Developer Guide:
    * Added implementation details of the `sdel` feature. 
    * Updated the diagram for Model component (PR: [\#150](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/150))

* **Review/mentoring contributions**:
  * Regularly discussed implementation details in our group's communication channel.
  * Helped others debug and find solution during team meetings. For example, together with Wei Jie and Kelvin, we integrate the tab pane in client's information page.
  * Posted issues and discussions on issue tracker. (Some examples: 
  [\#98](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/98), [\#107](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/107), [\#223](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/223))

* **Tools**:
  * Integrated a new Github plugin (PlantUML) to the team repo.

