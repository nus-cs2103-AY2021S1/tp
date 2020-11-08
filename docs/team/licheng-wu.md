---
layout: page
title: Wu Licheng's Project Portfolio Page
---

## Project: fitNUS

### Overview

fitNUS is a desktop application that is targeted at NUS students who are looking to improve their fitness.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10
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

* **New Feature**: Added Exercise class and its relevant commands
  * What it does: Allows the user to add his chosen exercises into fitNUS
  * Justification: This is a core feature of fitNUS and allows users to add a list of exercises into fitNUS. This will enable
  the user to create routines based on the exercises added, and subsequently add these routines to their timetable.
  * Highlights: Implementing the exercise class and its relevant commands required a clear understanding of how parsing
   and storage functioned in the original AB3. Understanding of how fitNUS retrieves data from the data file is also
   needed in order to ensure that commands work as intended.

* **New Feature**: Added the feature allowing users to switch between the home screen and timetable screen
  * What it does: Allows the user view his timetable on a separate screen from his exercises, routines, lessons, calorie and
  BMI tracker.
  * Justification: This user-centric feature greatly improves user experience by providing more space to display the timetable.
  This organizes the UI and makes it more convenient for users to find their next activity for the day.
  * Highlights: The implementation of multiple tabs required a solid understanding of how AB3 uses FXML to display the GUI. Furthermore,
  did independent research to learn how tabs work in FXML, as well as how to integrate tabs into the current GUI.
  
* **New Feature**: Implemented the Timetable display on GUI
  * What it does: Allows the user view his timetable easily, with all lessons and routines on separate days and slots.
  * Justification: This allows the user to easily view his schedule for the week. All his routines and lessons are grouped 
  based on the day, and arranged based on the time. This ease of viewing greatly improves user-experience .
  * Highlights: The implementation required additional thinking to filter and sort the routines and lessons chronologically,
  instead on based on when the user adds the activity to the timetable.

* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=licheng-wu&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to display exercises, routines and lessons added into 3 columns for ease of viewing.

## Contributions to the team-based tasks:

* Contributed to the planning and designing of the display and layout of fitNUS.
* Enhanced existing code written by teammates to ensure code quality and improve readability of code.
* Ensured code is well documented by checking for the necessary header comments in classes or methods to improve readability.

## Contributions to the User Guide:

* Contributed to the introduction, and the documentation for Exercise.

  * [Introduction](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#1-introduction)

  * [Exercise Commands](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#41-exercise)

## Contributions to the Developer Guide:

* Added implementation details of the command `find_exercises`, through the use of a sequence diagram
to illustrate the flow of method calls in fitNUS.

  * [Find Exercises](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#find-exercises)

* Updated the UI component from AB3 to better reflect how fitNUS works.

  * [UI Components](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#ui-component)

## Community
  * Reported bugs and suggestions for other teams in the class during PE Dry Run.
