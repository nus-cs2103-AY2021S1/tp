---
layout: page
title: Rayasam Harshini's Project Portfolio Page
---
## Project: ForgetfulNUS

ForgetfulNUS is a desktop glossary app for students taking German 1 (LAG1201) and German 2 (LAG2201) in NUS to practise and test their vocabulary. 
This app is optimised for use via a Command Line Interface (CLI).

Here are my contributions to this project:

* **New Feature**: Added the ability to save quiz scores.
  * What it does: Allows the user to view scores for previous rounds 
  of testing, along with a list of German words tested in each round. Scores are saved to local
  storage and can be accessed even after exiting and reopening the application.
  * Justification: This feature improves the product significantly because a user can keep
  track of their progress when quizzing. Not only can users view previous scores
  to check if they are making progress, they can also see the words tested to identify potential
  weak areas.
  * Highlights: This enhancement required significant additions to the Storage component to save scoring
   information in a separate .json file. Given that scores are saved in a similar way to flashcards in the glossary,
   this enhancement was challenging as it required analysis of code to extract several class methods into interfaces to minimise code duplication.
   Significant modifications were also made to the Model component to allow flashcards tested to be tracked along with the scores.

* **New Feature**: Added the ability to clear all quiz scores.
    * What it does: allows the user to permanently delete the history of saved scores, 
    including all scores saved in past sessions.
    * Justification: This feature improves the user experience by allowing users to get a 
    fresh start. This could be especially useful for returning users picking up the application after a while, 
    who may feel that previous scores do not reflect their current understanding.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rayasamhr&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=rayasamhr&tabRepo=AY2021S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Added all original user stories as issues to the project Kanban board.

* **Enhancements to existing features**:
  * Renamed classes, methods and variables from AddressBook-3 to suit our application;
  removed unnecessary fields from AddressBook-3 and modified test code accordingly
  (Pull requests 
  [\#77](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/77),
  [\#85](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/85),
  [\#89](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/89))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `scores` and `reset scores` (Pull requests 
    [\#148](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/148),
    [\#236](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/236))
    * Refined project description and made cosmetic tweaks to each command 
    (Pull request [\#140](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/140))
  * Developer Guide:
    * Significantly changed UML class diagram of Storage component from AddressBook-3 to reflect modified class relationships; 
    edited UML class diagram of Model to reflect classes created when adding the `score` feature (Pull request [\#242](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/242))   
    * Added implementation details of the `scores` feature. (Pull requests [\#122](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/122),
    [\#249](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/249))
    * Added score testing workflow in the 'Instructions for Manual Testing' (Pull request [\#242](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/242))   
    * Added non-functional requirements and glossary
    based on team discussion (Pull request [\#72](https://github.com/AY2021S1-CS2103T-W16-2/tp/pull/72))
