---
layout: page
title: Javier's Project Portfolio Page
---

## Project: Insurance4Insurance (I4I)

Insurance4Insurance (I4I) is a desktop app for insurance agents to manage clients.  
It is optimized for use via a CLI, while still having the benefits of a GUI. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Create a list of policies the company has and allocate them to clients.
  * What it does : User can create a list of policies and link them to his clients, so that the user can track the policy the client has bought. 
  * Justification: This feature is especially useful for insurance agents because it gives a convenient method to track his client's policy for any follow-ups or claims.
  * Highlights: This enhancement stores policies as a policy list in a separate json file from the client list. This makes it easier to reference the policy data that has already been input. Thus, the feature provides an alternative, unique template for entering fields for client other than simple String-Class fields.
  * Challenges: Managing the policy list and the data of the clients at the same time was difficult. Plenty of time was spent contemplating ways to implement the feature since we want both the list and the data to be consistent (policies in clients should already be in the policy list). Additionally, making sure that the json files for policy list and policy classes were correct and functional was difficult.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ijavierja&tabRepo=AY2021S1-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
    * added a new field in `Person`, `ClientSource`. (Pull request [\#55](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/55))

* **Documentation**:
  * User Guide:
    * Added an individual section of the `policy` feature. (Pull requests [\#136](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/136)) 
  * Developer Guide:
    * Added implementation details of the `Policy` feature. (Pull requests [\#80](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/80))
    * Added/Maintained user stories, use cases and test cases for `add policy` & `clear policy` commands. (Pull requests [\#197](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/197))
    * Added/Maintained diagrams for `Model` & `Storage`. (Pull requests [\#198](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/198))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#121](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/121)