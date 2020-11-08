---
layout: page
title: Khor Jing Qian's Project Portfolio Page
---

## Project: fitNUS

### Overview

fitNUS is a desktop address book application that is targeted at NUS students that are looking to improve their fitness.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15
kLoC.

### About the Team

We are a group of 5 Computer Science undergraduates from Year 2 and Year 3 reading CS2103T: Software Engineering.

#### About the Project

fitNUS represents a culmination of our team's efforts in morphing an existing [Address Book Level 3 (AB3) project](https://github.com/se-edu/addressbook-level3)
into a new product via Brownfield software development. fitNUS is a personal fitness tracker assistant which allows
users to create customised workout routines, by having the flexibility to add exercises to the routines they have
created. To facilitate workouts around their busy schedules, users have an added option of slotting lessons and routines
to their timetable.

The team has come up with a vast number of user-centric features to make fitNUS well-suited in order to provide both
convenience and utility in the long-run. Aimed at NUS students who are keen on improving their fitness levels, fitNUS is
the perfect solution in which the Graphical User Interface is integrated with Command Line Interface to provide a
wholesome user experience.

## Summary of Contributions

* **New Feature**: Added Routine classes and implemented its relevant implementations.
  * What it does: allows the user to store pre-existing Exercises and users are able to add Routine into their timetable.
  * Justification: This is a core feature of fitNUS and allows users to customise what exercises they want in a routine and the time and date of when they want to try out this Routine.
  * Highlights: Implementing Routine required a deep understanding of how the original AB3 functioned. The implementation was made even more challenging by the fact that
  it was built on Exercise and maintaining low coupling was tough and introduced many issues. Whenever an Exercise was deleted or edited, the corresponding Routine that has implemented it has to remove or edit the Exercise as well.

* **New Feature**: Added DailyCalorie classes and implemented its relevant implementations.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: Calories is an important part of tracking your fitness and this implementation allows fitNUS to better help their users.
  * Highlights: Calorie tracking is done on a daily basis because the team believes that users should not be able to change future or past calorie count.  Implementation was tricky as I wanted any calorie features to be isolated and be able to run by itself. This meant that the calorie classes had to use LocalDate to remove the dependency on the user. This increased the difficulty of testing and equality checks.

* **New Feature**: Implemented the graph for calorie tracking on the fitNUS GUI.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: To appeal to the visual users of fitNUS to allow them to follow their progress using graphics.
  * Highlights: Calorie tracking is done on a daily basis. It was difficult to learn the JavaFX for LineChart and implementing such that it responds to user input of calories by actively charting new entries. I chose to implement the feature as an ObservableList of calorie entries for every day that the user has edited their calories. This way, the information of calories per day and which day its recording is encapsulated. This ObservableList is passed to the CalorieGraph class to translate into a Series and then plotted on the GUI.
  * Credits: Oracle document on LineChart, xAxis, yAxis and their tutorial.

* **New Feature**: Implemented the BMI display on GUI and Body class that supported it.
    * What it does: allows the user to input their height and weight, displaying their BMI.
    * Justification: body metrics are important in tracking your fitness journey, so we felt that it was essential to have this graphics on the GUI.
    * Highlights:  Implementation of the GUI treats the Body as an Observable object that detects any change in the Height or Weight classes that it contains. The GUI listens for changes, and updates the BMI accordingly. Body is wrapped in ObservableList and then in an unmodifiable copy of this. I considered refactoring and using ChangeListener to detect changes in Body. In this case, Weight and Height classes will be of SimpleObjectProperty and the listener will detect if there are changes to it. However, this meant exposing the Body object to Logic and Manager in order to pass the observed variables to MainWindow, whereas the other classes utilised the unmodifiable version of the ObservableList. Ultimately, I chose to implement it as defensively as possible so I chose to use ObservableList.

* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=khor-jingqian&tabRepo=AY2021S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * Managed releases `v1.3(trial)` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to fit our vision for fitNUS, adjusting the arrangement. [#104](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/104) [#100](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/100) [#184](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/184) [#188](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/188)
  * Wrote test cases for Routine, UniqueRoutineList, DailyCalorie, CalorieLog, Body, Height and Weight and all the
  linked classes. [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195) [#194](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/194) [#193](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/193) [#199](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/199)
  * Disallow the input of repeated prefixes for all the classes in fitNUS where AB3 would take the latest prefix command to be accurate, now only allowing 1 prefix when appropriate.  [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)

## Contributions to the team-based tasks:

* Set up assertions in Gradle for fitNUS. [#75](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/75)
* Release management, handled v1.3(trial) and v1.3.
* Maintaining issue-tracker and milestone, reminding team mates to make sure their workload is reflected in the issues.
* Timely reminders to close their issues and finish up before milestone deadline.
* Necessary code enhancements such as renaming it to fitNUS, finding a suitable product icon, recommending a layout.

## Contribution to the User Guide:

* Writeup for Exercise, Routine and BMI, excluding pictures. [#76](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/76) [#85](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/85) [#90](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/90) [#92](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/92)

* Contributed some pictures for UG (refer to above links to UG edits).

* Wrote the command summary for User Guide(refer to above links to UG edits).

## Contributions to the Developer Guide:

* Added implementation details of the command `routine_create`, showing in depth understanding of the sequence of method calls in fitNUS. [#85](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/85) [#92](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/92)

* Refactored architecture diagrams from AB3 to better reflect how fitNUS works. (refer to above links to DG edits)

* Wrote User Stories and Manual testing for the group. [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)

* Added an Activity Diagram for `routine_create` for better clarity [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)

* Wrote design considerations for `routine_create` [#195](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/195)

## Community
  * Reported feature flaws and suggestions for other teams in the class during PE Dry Run. [#1](https://github.com/khor-jingqian/ped/issues/1) [#2](https://github.com/khor-jingqian/ped/issues/1) [#3](https://github.com/khor-jingqian/ped/issues/3)

  * Left non-trivial comments on team mate's PR. [#196](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/196)

