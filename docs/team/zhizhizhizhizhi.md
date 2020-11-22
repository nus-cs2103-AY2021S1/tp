---
layout: page
title: Xu ZhiZhi's Project Portfolio Page
---

## Project: AddressBook Level 3

ForgetfulNUS is a desktop glossary app for students taking German 1 (LAG1201) and German 2 (LAG2201) in NUS to practise and test their vocabulary. This app is optimised for use via a Command Line Interface (CLI).
Given below are my contributions to the project.

* **New Feature**: Quizzing
  * What it does: Allows the user to enter quiz mode on the app, where the definitions for flashcards will be hidden. Users can type in the definition and the app will grade them.
  * Justification: This feature lets the user test themselves on how well they remember the definitions to the German phrases. 
  * Highlights: I implemented a quiz mode for the app which keeps track of the attributes (flashcards in quiz, scores etc.) of a quiz from its start to end. These attributes are recorded by the save quiz scores feature implemented by my team mates and can be retrieved with the `score` command. The attributes will reset at the start of each new quiz.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w16&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=zhizhizhizhizhi&tabRepo=AY2021S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Enhancements to existing features**:
  * Classified all commands into commands that can be executed only in quiz mode, only in normal mode or in both modes. Added a method under abstract Command class such that all sub-types of Command (FindCommand, TryCommand etc.) will be checked before execution to ensure that they only run in the intended modes and improve code quality. (Pull request: [\#106](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/106))
  * Implemented auto-scrolling of the flashcard list during quiz in the GUI. This ensures that the flashcard being tested is always in sight. (Pull requests: [\#230](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/230), [\#232](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/232))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `try` and `next` (Pull request: [\#136](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/136))
    * Rearranged the order of commands and other small tweaks to make the User Guide more coherent: (Pull request: [\#241](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/241))
  * Developer Guide:
    * Added implementation details of the `Quizzing` feature. (Pull request: [\#123](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/123))

* **Community**:
  * Reported bugs and suggestions for other teams ([examples](https://github.com/zhizhizhizhizhi/ped/issues))
  
* **Credits**:
  * This project is based on the AddressBook-Level3 project created by the SE-EDU initiative.

