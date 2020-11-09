---
layout: page
title: Muhammad Iqbal Bin Imran's Project Portfolio
---

## Project: fitNUS

### Overview

This portfolio page highlights some of my contributions to fitNUS - a Software Engineering Project developed in my third
year of undergraduate studies in the National University of Singapore (NUS).

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

* **Major enhancement**: I implemented the Lesson class and its associated commands.
  * What it does: This allows users to add, delete, edit, find and list their lessons or modules in fitNUS.
  * Justification: This feature is one of the building blocks of fitNUS because users can now manage their set of Lesson items and subsequently add them to their customisable Timetable.
  * Highlights: This enhancement requires a deep understanding of how parsing and storage worked in the original AB3. Applying these concepts to our application's architecture is also
  required to ensure that the commands work as intended.

* **Major enhancement**: I implemented the `help` command.
  * What it does: This allows users to do a look-up of a full command list supported by fitNUS by redirecting them to a pop-up window. I also added the option of finding a group of commands by searching `help` followed by a command keyword substring.
  * Justification: This allows users to streamline their search for more efficient searches. Users no longer have to click the link to go to the full User Guide once the pop-up window appears.
  They can simply search for the commands they wish to execute directly from the pop-up window.
  * Highlights: This enhancement requires cosmetic tweaks to the UI and understand how to utilize a LinkedHashMap to do a filtered search if the keyword matches a substring of any command.
  
* **Minor enhancement**: I implemented substring search using `find` command across fitNUS.
  * What it does: This allows users to search for Routine, Exercise and Lesson entries by inputting incomplete keywords to search for a group of commands containing these substrings.
  * Justification: Users no longer require to type the full keyword. Results can still be shown if they enter an incomplete keyword for optimization.
  * Highlights: This enhancement relies on Predicate s across Routine, Exercise and Lesson to do a categorical search.

* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=iqbxl&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * My product idea of an application that is geared towards fitness was implemented by the team.
  * Created milestone v1.4, facilitated issue tracker and user testing.

* **Enhancements to existing features**:
  * Refactored the entire code base of fitNUS so that there are no references to AB3 while packaging the classes appropriately. [#185](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/185)
  * Wrote test cases for Lesson features, storage and logic components to increase coverage. [#106](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/106) [#171](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/171)
  * Added updated pictures for all commands in the User Guide. [#206](https://github.com/AY2021S1-CS2103T-T09-2/tp/pull/206)

## Contribution to the User Guide:
    Given below are sections I contributed to the User Guide. They showcase my ability to write documentation targeting end-users.

[../UserGuide.md](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#46-calorie) (Added documentation for the features `calorie_add` and `calorie_minus`)<br/>
[../UserGuide.md](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#471-help-help) (Added documentation for the feature `help`)<br/>
[../UserGuide.md](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#2-additional-information) (Added documentation for the section Additional Information)<br/>
[../UserGuide.md](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#3-quick-start) (Did cosmetic tweaks to existing documentation: Quick Start section)

## Contributions to the Developer Guide:
    Given below are sections I contributed to the Developer Guide. They showcase my ability to write technical documentation.

[../DeveloperGuide.md](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#delete-lesson) (Added implementation details of the `lesson_delete` feature)<br/>

## Community:
  * Reported feature flaws, functionality bugs and constructive suggestions for other teams during PE Dry Run. [#1](https://github.com/iqbxl/ped/issues/1) [#2](https://github.com/iqbxl/ped/issues/2)
  [#3](https://github.com/iqbxl/ped/issues/3) [#4](https://github.com/iqbxl/ped/issues/4) [#5](https://github.com/iqbxl/ped/issues/5) [#6](https://github.com/iqbxl/ped/issues/6)
