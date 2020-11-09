---
layout: page
title: Cai Jialin's Project Portfolio Page
---

## Project: AddressBook Level 3

DSAce is a desktop definition book application used for teaching Data Structures and Algorithms principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view past quiz records.
  * What it does: allows user to retrieve and revise their past quiz attempts. User will be able to see their score, and the correct and wrong answers
  for each quiz question in the specific attempt
  * Justification: This feature improves the user experience because the user can track their learning process, and also find out which quiz questions
  they commonly get wrong
  * Highlights: The implementation required three new commands: `performance`, `list`, `view`. The implementation required the creation of many many classes, and
    the amount of UI code is equivalent to the original ab3.

* **New Feature**: Added the ability for find command to search for tags and priority.
  * What it does: allows the user to search for flashcards using tags and priority keywords as well. Multiple keywords will follow the `AND` search
  i.e only flashcards that fulfills all keywords will be returned
  * Justification: This feature improves the user experience because the user can revise flashcards by topic (e.g Sorting) and/or by priority (e.g priority: high)
  * Highlights: The implementation required changes to existing commands, as well as the creation of three new predicate classes.
  
* **New Feature**: Added the ability to toggle through past commands using up and down arrows.
    * What it does: allows the user to enter commands faster, especially for minor modifications to long commands
    * Justification: This feature improves the user experience because the user do not need to retype long commands
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jialin7878&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Code UI to reflect users' answers for the quiz (Pull request [\#153]())
  * Refactored code to more suit our application (Pull request [\#38]())
  * Create and refactor classes for our quiz feature (Pull request [\#82]())
  * Added sample quiz questions (Pull request [\#82]())
  * Wrote additional tests for existing features (Pull request [\#82]())
  * Fix PED bug reports (Pull request [\#133]())

* **Documentation**:
  * User Guide:
    * Fix PED documentation bugs (Pull request [\#133]())

* **Community**:
  * PRs reviewed: [\#9](), [\#59](), [\#60](), [\#72](), [\#74](), [\#83](), [\#85](), [\#95](), [\#143]()


