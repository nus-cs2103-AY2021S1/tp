---
layout: page
title: Lim Zi Yang's Project Portfolio Page
---

## Project: Common Cents

Common Cents is a desktop money-tracking application to help small business owners to organise their expenses. With Common Cents,
a user can hold monetary information such as expenses and revenue in multiple accounts in which each account represents a business. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Project Design
* **Designed and implemented the model and code structure of the project.**
  * Justification: The model and code structure served as initial stages of the project, for example the structure of accounts, entries and the overall app
  became the base foundation of the project. Hence, all the features were implemented based on the model and code structure.
  * Highlights: While the design was inspired by Addressbook-level3 as mentioned below, the implementation was not easy as there is
  a lot of content to be coded and integrated as a whole. A total of about 2 kLoC was written as the model of the project. 
  * Credits: The design was inspired by [Addressbook-level3](https://github.com/se-edu/addressbook-level3)
  * Related Pull Request: [\#28](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/28)

* **Implemented the logic execution of features in the app.**
  * Justification: The logic aspect of the app interacts with the model and the storage of the app based on a command executed. Thus,
  it is a crucial component as it serves as the "brain" of the app, interacting various components of the app to produce the desired
  output based on the user's command.
  * Highlights: While the design was inspired by Addressbook-level3 as mentioned below, the implementation was not easy since the 
  components of the app is different from Addressbook-level3. Hence, the overall integration and interactions with the app required 
  extensive modification and adding of new aspects to the logic execution.
  * Credits: The logic execution was inspired by [Addressbook-level3](https://github.com/se-edu/addressbook-level3)
  * Related Pull Request: [\#51](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/51)

### Features
* **Added the ability to undo/redo previous commands.**
  * What it does: allows the user to undo commands that add, edit, or delete entries. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. 
  The implementation too was challenging as it required changes to existing structure of the app.
  * Related Pull Request: [\#149](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/149)

* **Added all account-level commands.**
  * What are these features: Account-level commands are commands that interacts with the accounts of Common Cents rather than the entries.
  These features include adding of account, deleting of account, editing of account name, listing of accounts, switching of accounts.
  * Justification: These features serves as the essential aspect of the app, especially for our targeted audience, as it allow users to manage finances
  of separate businesses.
  * Highlights: The implementation was challenging as an account serves as a level that is sandwiched between entries and the app. As such,
  the implementation required modifications to most parts of the app for compatibility and integration. 
  * Related Pull Request: [\#123](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/123)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Ziyang-98&tabRepo=AY2021S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### Project Management

* **Led the overall direction and development of the project**
* **Managed releases `v1.2`(namely `v1.3.trial`), `v1.3`, and `v1.4` (3 releases) on GitHub**

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [\#33](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/33), 
  [\#59](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/59), 
  [\#131](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/131), 
  [\#208](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/208), 
  * Reported bugs and suggestions for other teams in the class (examples: 
  [\#188](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/188), 
  [\#189](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/189), 
  [\#190](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/190), 
  [\#191](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/191), 
  [\#192](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/192), 
  [\#193](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/193), 
  [\#194](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/194))

### Documentation

#### User Guide:
* **Added `How to identify notations` section and the relevant notations**: [#2](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#2-how-to-identify-notations) 
* **Added documentation for the `undo` command and all the account-level commands**:  
[#5.2.8](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#528-undoing-entry-level-commands-undo), 
[#5.3](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#53-account-level-commands)

#### Developer Guide:
  * **Added details to `Logic` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#logic-component)
  * **Added details to `Model` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#model-component)
  * **Added implementation details of the `undo` feature**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#undo-feature)
  * **Added implementation details of the `edit account` feature**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#edit-account-feature)
  * **Added some details to `Use case` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#use-cases)
  * **Added details to `Appendix: Instructions for manual testing` section**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#appendix-instructions-for-manual-testing)
  




