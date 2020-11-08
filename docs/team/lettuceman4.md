---
layout: page
title: Le Hue Man's Project Portfolio Page
---

## Project: Common Cents

Common Cents is a desktop money-tracking application to help small business owners to organise their expenses. With Common Cents,
a user can hold monetary information such as expenses and revenue in multiple accounts in which each account represents a business.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Project Design

* **Implemented and wrote tests for the storage features in the app.**
  * Justification: The storage feature is crucial in the execution of the app. It has multiple layers and 
  a new layer of logic (Account) as well as subclasses of Entry also needed to be considered during the implementation. 
  * Highlights: While the design was inspired by Addressbook-level3 as mentioned below, the implementation was not easy since the 
  components of the app is different from Addressbook-level3. Moreover, it was implemented at the start of our team project, 
  hence it was difficult to test and had to rely heavily on stubs. However, after the first draft of implementation, only small 
  twists were needed during the integration process, saving a lot of debugging time.
  * Credits: The storage was inspired by [Addressbook-level3](https://github.com/se-edu/addressbook-level3)
  * Related Pull Request: [\#33](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/33)
  
* **Improved code quality of the command result.**
  * Justification: The Addressbook-level3 used a lot of booleans in its command result's constructor. A factory method was
  created to improve the code quality. 
  * Related Pull Requests: [\#152](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/152).
  
### Features
* **Added and wrote tests for the ability to find specific entries.**
  * What it does: allows the user to find specific entries/revenues that have a keyword or a list of keywords.
  * Justification: This feature takes into account that user is used to the separation of expenses and revenues of the app, allowing 
  them to find them separately or find both at the same time. 
  * Highlights: Since it allows more flexibility for user, additional exceptions (apart from those that already handled by Addressbook-level3)
  need to be considered as well.
  * Related Pull Requests: [\#120](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/120), 
                           [\#124](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/124)
                           
* **Improved the existing GUI of the project.**
  * Justification: The User Interface, although not graded, is important in keeping group's interests and motivation high. The 
  use of JavaFX was also not thoroughly taught in the syllabus so it requires a lot of independence in learning to design and
  implement an intuitive and visually appealing UI. 
  * Highlights: The code design shares a lot of similarities with that of the Addressbook-level3 but the visual design was modified
  a lot to match the requirements of the app for the specific target user. 
  * Credits: The layout was inspired by [Addressbook-level3](https://github.com/se-edu/addressbook-level3)
  * Related Pull Requests: [\#86](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/86), 
                           [\#150](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/150)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lettuceman4&tabRepo=AY2021S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

<div style="page-break-after: always;"></div>

### Project Management:

* **Managed the punctual delivery of deliverables:** Reminded teammates of tasks and their deadlines. Helped with unfinished tasks.

* **Community**: 
  * Reported bugs and suggestions for other teams in the class (examples: 
  [\#240](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/240), 
  [\#241](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/241), 
  [\#242](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/242), 
  [\#243](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/243), 
  [\#244](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/244), 
  [\#245](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/245), 
  [\#246](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/246), 
  [\#247](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/247), 
  [\#248](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/248), 
  [\#249](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/249), 
  [\#250](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/250), 
  [\#251](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/251), 
  [\#252](https://github.com/AY2021S1-CS2103T-W12-3/tp/issues/252),  

### Documentation

#### User Guide:
* **Added `User Interface Overview` section and the relevant notations**: [#3](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#3-user-interface-overview) 
* **Added links for the `Command summary` section**:  [#7](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#7-command-summary)  
* **Added documentation for the `find` command**:  [#5.2.4](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#524-locating-entries-by-description-find)
* **Added screenshots and notations for all sections.** 

#### Developer Guide:
  * **Added details to `Storage` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#storage-component)
  * **Added prefaces to all headers**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html)
  * **Added implementation details of the `Find entries` feature**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#find-entries-feature)
  * **Added some details to `Use case` section (use cases for `find`)**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#use-cases)
  * **Added some details to `User stories` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#user-stories)
  * **Added details to `Appendix: Instructions for manual testing` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#appendix-instructions-for-manual-testing)
  * **Added prefaces for all headings**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html)
