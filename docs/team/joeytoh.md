---
layout: page
title: Toh Hai Jie Joey's Project Portfolio Page
---

## Project: Projact

Projact is a contact and project management app that helps NUS computing students to organise their contacts and their projects' meeting links and tasks. 

Given below are my contributions to the project.

#### New Features
* **Feature**: Added the ability to delete a tag. (Pull request [\#118](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/118))
  * What it does: allows the user to delete a tag that has been created. 
  * Justification: This feature allows the user to remove any tags that they deem are irrelevant or unnecessary so as to not clutter up the list of tags.
  
* **Feature**: Implement the MeetingLink class. (Pull request [\#134](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/134))
    * What it does: Adds a `link` field to a tag. This is an optional field.
    * Justification: This feature allows user to add a link to a tag so as to facilitate group projects et cetera.
    
* **Feature**: Added the command to add a link to a tag. (Pull request [\#140](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/140))
  * What it does: allows the user to tie the specified link to a tag, which can be directly clicked on from the tag list.
  * Justification: This feature provides a quick and easy way for the user to manage the links he/she has on hand. It enhances users' experience as they can add a meeting link to their project group tag or add a module website link to their module tag. 

#### Enhancements to Existing Features
* **Enhancement**: Redesigned the Tag class to support new features. (Pull request [\#58](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/58))
    * What it does: Instead of having the Tag class as a field under a Person, the Tag class is now a standalone.
    * Justification: This allows for the adding of fields to a Tag, such as a `link` field and `task` field.

<div style="page-break-after: always;"></div> 

#### Code Contributed
* [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=joeytoh&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=JoeyToh&tabRepo=AY2021S1-CS2103T-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

#### Project Management
* Aided in maintenance of issue tracker at the end of `v1.3`

#### Documentation    
* **User Guide**:
    * Updated the Introduction and About the User Guide sections (Pull request [\#88](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/88))
    * Managed documentation for the features `taglist`, `tagdelete`, `linkadd` (Pull request [\#148](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/148))
* **Developer Guide**:
    * Added implementation details and the UML diagrams of the `tagdelete`, `linkadd` features (Pull request [\#94](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/94), [\#148](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/148), [\#240](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/240))
    * Added the non-functional requirements (Pull request [\#13](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/13))
    * Added the use cases for `taglist` (Pull request [\#13](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/13))
    * Update manual instruction for testing for most commands (Pull request [\#240](https://github.com/AY2021S1-CS2103T-T17-4/tp/pull/240))
