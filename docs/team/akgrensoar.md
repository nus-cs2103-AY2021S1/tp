---
layout: page
title: Yap Jie Xiang's Project Portfolio Page
---

## Project: Projact

Projact is a **project management app that helps NUS computing students to organise their fellow computing students' contacts and their teams' meeting links and tasks**. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

#### New Features
* **Feature**: Added the UI for displaying the list of tags. (Pull request [\#52](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/52))
  * What it does: allows the user to view the tag list.
  * Justification: A user might want to view all the members of a particular tag.
  * Highlights: This feature is a precondition for the implementation of commands that are related to Tag.

* **Feature**: Added the TagFind command. (Pull request [\#65](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/65))
  * What it does: allows the user to find tags containing a given keyword.
  * Justification: When there are a large number of tags, this feature allows a user to quickly navigate to a tag.

#### Enhancements to Existing Features
* **Enhancement**: Updated ModelManager fascade with Tag operations. (Pull request [\#86](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/86))
    * Justification: Adhere to the fascade design pattern.
* **Enhancement**: Added TagTask in Model. (Pull request [\#121](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/121))
* **Enhancement**: Updated Person to reference TagName instead of Tag. (Pull request [\#121](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/121))
    * Justification: Adhere to the Law of Demeter principle.
* **Enhancement**: Added listener to url links. (Pull request [\#141](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/141))
    * Justification: Users can quickly open a url link with a single click.

#### Code Contributed
* [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=akgrenSoar&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Documentation
* **ReadMe**:
    * Linked github actions and codecov badges [\#23](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/23)
    * Added project description and documentation links [\#23](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/23)
* **User Guide**:
    * Added documentation for the features `tagfind` [\#65](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/65)
    * Managed documentation for the features `add` [\#238](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/238)
    * Reorganised command summary: [\#150](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/150)
* **Developer Guide**:
    * Replaced use case of the `tagview` feature into `tagfind`. [\#239](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/239)
    * Added UML diagram of the `tagfind` feature. [\#91](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/91)
