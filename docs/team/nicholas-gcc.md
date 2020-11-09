---
layout: page
title: Nicholas Canete's Project Portfolio Page
---

## Project: Common Cents

Common Cents is a desktop money-tracking application to help small business owners to organise their expenses. With Common Cents,
a user can hold monetary information such as expenses and revenue in multiple accounts in which each account represents a business. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Project Design
* **Helped devise a solution to create a default account once Common Cents is launched.**
  * Justification: The initial problem was that early in the project there was no robust UI layer between
  `Model` and `Account` and this problem was found when I was implementing `AddCommand`, where added entries had 
  nowhere to go because `Model` was supposed to contain several `Account` objects, but it was difficult to
  target an existing, selected account. The immediate solution I helped devise with the team was to either prompt
  the user to create an account before using the app, or create an account at launch. Upon further discussion,
  we had gone with the latter option, resulting in the `ActiveAccount` interface being implemented by the team.
  * Credits: The design was implemented by [@Ziyang-98](https://github.com/Ziyang-98)
  * Related Pull Request: [\#57](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/57)


### Features
* **Added the ability to add entries**
  * What it does: allows the user to add `Expense` or `Revenue` in Common Cents with a compulsory description,
  amount and optional tags.
  * Justification: This is a core feature because it allows the app to track `Expense` and `Revenue` in the
  first place and fulfills the fundamental need for the app for budgeting.
  * Highlights: The implementation of this feature revealed an early problem of a weak UI layer between 
  `Model` and `Account`, as it was difficult to specify where the added entries would go to. This also revealed
  a problem of users being able to use Common Cents without a default account, which paved the way for the
  implementation of `ActiveAccount`.
  * Related Pull Request: [\#58](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/58/files)

* **Added ability to edit entries**
  * What it does: allows the user to modify an existing `Expense` or `Revenue` in a selected account to change
  either its description, amount, tags or all of them.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands
   or the information about a transaction can change anytime in the real world and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was challenging because certain design choices such as making the `Category`
  field in the command compulsory had to be made, at the expense of user convenience and ease of implementation.
  For instance, making an edit command while also including the category may slow down users in typing commands.
  Furthermore, adapting `EditCommand` from AddressBook-level3 was more challenging than expected because while
  AB3 made all fields optional, I had to make bigger modifications to `EditCommand` and `EditCommandParser`
  to account for the compulsory `Category`.
  * Related Pull Request: [\#131](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/131)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=nicholas-gcc&tabRepo=AY2021S1-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

### Project Management

* **Helped to upkeep code quality** (examples: 
[\#217](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/217),
[\#279](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/279))

* **Community**:
  * PRs reviewed (with non-trivial changes to codebase): 
    [\#52](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/52), 
  [\#86](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/86)
  * Reported bugs and suggestions for other teams in the class (examples: 
  [\#171](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/171), 
  [\#172](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/172), 
  [\#173](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/173), 
  [\#174](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/174), 
  [\#175](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/175), 
  [\#176](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/176), 
  [\#177](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/177),
  [\#178](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/178),
  [\#179](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/179),
  [\#181](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/181)
  [\#185](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/185))
  
* **Enhancements to existing features**
    * Wrote tests for existing features to improve code coverage (examples: 
      [\#87](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/87), 
      [\#93](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/93), 
      [\#154](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/154),
      [\#159](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/159),
      [\#217](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/217))
    * Created static helper/stub classes to aid in testing for other teammates (examples: 
    [\#87](https://github.com/AY2021S1-CS2103T-T13-4/tp/pull/87), 
    [\#154](https://github.com/AY2021S1-CS2103T-W11-3/tp/issues/154))
<div style="page-break-after: always;"></div>

### Documentation

#### User Guide:
* **Added User Guide introduction section**: [Introduction](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html) 
* **Added documentation for the `Components` section**: [#4](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#4-components)
* **Added General Inquiries into FAQ section:** [#6.1](https://ay2021s1-cs2103t-t13-4.github.io/tp/UserGuide.html#61-general-inquiry)
#### Developer Guide:
* **Added implementation details of the `add` entry feature**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#add-entries-feature)
* **Added implementation details of the `edit` entry feature**: [Link](https://ay2021s1-cs2103t-t13-4.github.io/tp/DeveloperGuide.html#edit-entries-feature)
