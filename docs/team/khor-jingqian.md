---
layout: page
title: Khor Jing Qian's Project Portfolio Page
---

### Project: fitNUS

#### Overview

fitNUS is a desktop address book application that is targeted at NUS students that are looking to improve their fitness.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15
kLoC.

#### About the Team

We are a group of 5 Computer Science undergraduates from Year 2 and Year 3 reading CS2103T: Software Engineering.

##### About the Project

fitNUS represents a culmination of our team's efforts in morphing an existing [Address Book Level 3 (AB3) project](https://github.com/se-edu/addressbook-level3)
into a new product via Brownfield software development. fitNUS is a personal fitness tracker assistant which allows
users to create customised workout routines, by having the flexibility to add exercises to the routines they have
created. To facilitate workouts around their busy schedules, users have an added option of slotting lessons and routines
to their timetable.

### Summary of Contributions

* **New Feature**: Added Routine classes and implemented its relevant implementations.
  * What it does: allows the user to store pre-existing Exercises and users are able to add Routine into their timetable.
  * Justification: This is a core feature of fitNUS and allows users to customise what exercises they want in a routine and the time and date of when they want to try out this Routine.
  * Highlights: The implementation was made even more challenging by the fact that it was built on Exercise and maintaining low coupling was tough and introduced many issues. Whenever an Exercise was deleted or edited, the corresponding Routine that has implemented it has to remove or edit the Exercise as well.


* **New Feature**: Added DailyCalorie classes and implemented its relevant implementations.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: Calories is an important part of tracking your fitness.
  * Highlights: Calorie tracking is done on a daily basis because users should not be able to change future or past calorie count.  Implementation was tricky as I wanted any calorie features to be isolated and be able to run by itself. This meant that the calorie classes had to use LocalDate. This increased the difficulty of testing and equality checks.


* **New Feature**: Implemented the graph for calorie tracking on the fitNUS GUI.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: To appeal to the visual users of fitNUS to allow them to follow their progress using graphics.
  * Highlights: It was difficult to learn the JavaFX for LineChart and dynamically shifting it. I chose to implement the feature as an ObservableList of calorie entries for every day that the user has edited their calories. encapsulating the date and calorie amount. This ObservableList is passed to the CalorieGraph class to translate into a Series and then plotted on the GUI.
  * Credits: Oracle document on LineChart, xAxis, yAxis and their tutorial.


* **New Feature**: Implemented the BMI display on GUI and Body class that supported it.
    * What it does: allows the user to input their height and weight, displaying their BMI.
    * Justification: body metrics are important in tracking your fitness journey.
    * Highlights:  Implementation of the GUI treats the Body as an Observable object that detects any change in the Height or Weight classes that it contains. The GUI listens for changes, and updates the BMI accordingly. Body is wrapped in ObservableList and then in an unmodifiable copy of this. I considered refactoring and using ChangeListener to detect changes in Body but decided against it because it meant exposing Body and the current implementation is more defensive.


* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=khor-jingqian&tabRepo=AY2021S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * Managed releases `v1.3(trial)` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to fit our vision for fitNUS, adjusting the arrangement. [#104](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/104) [#100](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/100) [#184](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/184) [#188](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/188)
  * Wrote test cases for Routine, UniqueRoutineList, DailyCalorie, CalorieLog, Body, Height and Weight and all the
  linked classes. [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195) [#194](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/194) [#193](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/193) [#199](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/199)
  * Disallow the input of repeated prefixes for all the classes in fitNUS. [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)
  * Prevent input for Tag that is more than 50 characters, enhancing user-friendliness.  [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)
  * Disallow finding symbols, because it is a logical flaw.  [#210](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/210)
  * Improve defensive code when reading JSON files, in the event of tampering. [#213](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/213)

### Contributions to the team-based tasks:

* Set up assertions in Gradle for fitNUS. [#75](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/75)
* Release management, handled v1.3(trial) and v1.3.
* Maintaining issue-tracker and milestone, timely reminders to team mates to close issues.
* Necessary code enhancements such as renaming it to fitNUS, finding a suitable product icon, recommending a layout.

### Contribution to the User Guide:

* Writeup for Exercise, Routine and BMI, excluding pictures. [#76](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/76) [#85](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/85) [#90](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/90) [#92](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/92)

* Contributed some pictures for UG (refer to above links to UG edits).

* Wrote the command summary for User Guide. [#76](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/76)

### Contributions to the Developer Guide:

* Added implementation details of the command `routine_create`, showing in depth understanding. [#85](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/85) [#92](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/92)

* Refactored architecture diagrams from AB3 to better reflect how fitNUS works. (refer to above links to DG edits)

* Wrote User Stories and Manual testing for the group. [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195) [#214](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/214)

* Added an Activity Diagram  and design considerations for `routine_create` for better clarity [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)

### Community
  * Reported feature flaws and suggestions for other teams in the class during PE Dry Run. [#1](https://github.com/khor-jingqian/ped/issues/1) [#2](https://github.com/khor-jingqian/ped/issues/1) [#3](https://github.com/khor-jingqian/ped/issues/3)

  * Left non-trivial comments on team mate's PR. [#196](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/196)

