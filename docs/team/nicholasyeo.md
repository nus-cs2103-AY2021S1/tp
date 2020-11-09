---
layout: page
title: Nicholas Yeo's Project Portfolio Page
---

## Project: fitNUS

### Overview

fitNUS is a desktop application that is targeted at NUS students who are looking to improve their fitness.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20
kLoC.

### About the Team

We are a group of 5 Computer Science undergraduates from Year 2 and Year 3 reading CS2103T: Software Engineering.

### About the Project

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

* **New Feature**: Added Timetable and Slot classes together with their relevant implementations.
  * What it does: Allows the user to add existing Routines or Lessons in fitNUS into their timetable by creating a Slot.
  The Timetable class has a list of Slots, where each Slot encapsulates an Activity, the Day and Duration.
  * Justification: This is a core feature of fitNUS and allows users to have the flexibility of adding Routines/Lessons
  to any day and time slot of their choice in their timetable. This allows users to integrate their workout sessions
  into their lesson timetable. To allow the adding of either Routine or Lesson into a Slot, both the Routine and Lesson
  classes extend from the parent Activity class.
  * Highlights: Implementing Timetable and Slot classes required a deep understanding of how the original AB3 functioned.
  Furthermore, the Slot class stores Routines or Lessons and it requires both classes to be implemented well before
  Slot can properly encapsulate them. Whenever a Routine or Lesson is edited or deleted, the corresponding slot(s) 
  in the timetable that contain them have to be edited or deleted accordingly as well.

* **New Feature**: Added the ability to store height and weight in fitNUS.
  * What it does: Allows the user to store his height and weight so that he can keep track of his fitness progress.
  * Justification: Such a feature improves our application significantly as it helps users to better track their fitness
  levels and plan their fitness journey properly, such as the need for weight loss or weight gain.
  * Highlights: The initial implementation of height and weight stores them as the primitive data type double.
  I decided to improve the implementation by adding Height and Weight classes in fitNUS so that it
  follows closely from the existing implementation of the Name class in Address Book Level 3. This is to provide
  consistency throughout the code base and makes the relevant command and parser classes easier to implement
  while ensuring high code quality at the same time.

* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=nicholasyeo&tabRepo=AY2021S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code).

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Wrote test cases for Timetable, Slot, and the relevant classes that they encapsulate for the model, logic and storage components.
  Added more tests to the general classes and utility classes such as FitNusParser, FitNus, and ParserUtil.
  [#201](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/201) [#204](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/204)
  * Fixed the case-sensitive issues of allowing duplicate Routine, Exercise or Lesson to be added into fitNUS.
  Fixed the issue of having too long names by restricting it to maximum of 50 characters. [#187](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/187)

## Contributions to the team-based tasks:

* Contributed to the planning and designing of the display and layout of fitNUS.
* Enhanced existing code written by teammates to ensure code quality and improve readability of code.
* Ensured code is well documented by checking for the necessary header comments in classes or methods to improve readability.

## Contributions to the User Guide:

* Contributed to the documentation for Exercise, Lesson, and Timetable. [#77](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/77)

* Contributed to the command summary for User Guide. (refer to above link)

* Contributed to the structure of the User Guide such that there is better flow and improved readability.

* Contributed to the language and style of the User Guide by making it more reader-centric through the use of
active voice and you-language. [#93](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/93)

## Contributions to the Developer Guide:

* Added implementation details of the command `timetable_add_routine`, through the use of a sequence diagram
to illustrate the flow of method calls in fitNUS. [#88](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/88)

* Updated the Model component from AB3 to better reflect how fitNUS works.

## Community
  * Reported bugs/feature flaws and suggestions for other teams in the class during PE Dry Run.
  [#1](https://github.com/nicholasyeo/ped/issues/1) [#2](https://github.com/nicholasyeo/ped/issues/2) [#3](https://github.com/nicholasyeo/ped/issues/3)
