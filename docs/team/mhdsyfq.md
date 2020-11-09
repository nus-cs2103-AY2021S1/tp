---
layout: page
title: Muhammad Syafiq Bin Abas's Project Portfolio Page
---

## Project: GradPad

GradPad provides an all-in-one solution to **Module Management** for Computer Science Undergraduates. It
**consolidates** all the processes required for students to **plan their modules** and **track their academic progress** 
into a single platform. The first of its kind - GradPad offers a **collaboration** like no other, allowing students to 
manage their modules more efficiently without the need to use several platforms all at once. Users interact with
GradPad using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mhdsyfq&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Features Implemented**: 
  * Added the ability to choose to `delete`/`clear` with a confirmation prompt, or to force delete or
  force clear using `fdelete`/`fclear`.

    * What it does:<br>
    Allows users to provide confirmation whether or not they want to proceed with deleting or clearing
    data, and also allows users to force delete or clear data without a confirmation prompt.
    
    * Justification:<br>
    This feature is significant because users can make mistakes in certain commands that results in them
    losing data and the app should provide a convenient way for users to avoid making those mistakes by prompting users
    for a confirmation before executing the commands. Some users (usually regulars or experts), however, may find it
    troublesome to have to constantly provide confirmation, so the app should also provide a way for users to
    instantly execute these commands.
    
    * Highlights:<br>
    This enhancement of adding a confirmation prompt affects some existing commands and commands to be 
    added in future. The implementation was fairly challenging and it required an in-depth analysis of design alternatives 
    as there is a need to stall the original command, prompt for a confirmation, and then execute or stop the execution 
    of the command (based on the confirmation provided). Also, there was a need to create a `YesCommand` which accepts any 
    of `y`, `ye`, or `yes` as a confirmation. After figuring out the implementation, however, the extension to other 
    existing commands was relatively straightforward.

  * Added the ability to check accumulated Modular Credits (MCs) using `checkmc`.

    * What it does:<br>
    Allows users to check how many Modular Credits they have attained based on the modules that they have
    completed/added into GradPad.
  
    * Justification:<br>
    This feature improves the application slightly as users might want to occasionally check how much 
    MCs they have attained so far so as to gauge how close they are to graduation without having to check the MCs for each
    module and manually calculate. 
    
* **Enhancements to features implemented by peers**:
  * Improved `gem` and `science` features to support auto update [\#182](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/182)
    
* **Enhancements to existing features**:

  * Refactored the Address Book 3 `ui` package and components [\#37](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/37)
  * Improved flexibility of `find` feature [\#77](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/77)
  * Improved implementation of `add` and `edit` features [\#111](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/111)
  * Added a 1.5 second delay before closing GradPad window for `exit` feature [\#95](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/95)
  * Added assertions to the `core`, `logic` and `module` packages [\#92](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/92)
  * Refactored Index package [\#219](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/219)
  * Changed the entire outlook of the Graphical User Interface (GUI)
    * Changed arrangement of GUI components [\#51](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/51)
    * Updated GUI color scheme [\#66](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/66)
    * Added introduction display with original GradPad logo [\#127](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/127)

* **Contributions to team-based tasks**:
  * Came up with product name
  * Changed product icon
  * Designed GradPad logo
  * Designed and added images with mark-ups for the User Guide
  * Documented Non-Functional Requirements and Glossary to the Developer Guide
  * Consolidated all messages into a single class
  * Updated User Guide according to peers' and tutor's comments
  * Improved readability and clarity for result display messages
  * Standardised JavaDoc comments
  
* **Documentation**:
  * User Guide:
    * Added documentation for `help`,`add` and `find` features [\#24](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/24)
    * Updated command descriptions [\#102](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/102)
    
  * Developer Guide:
    * Added documentation for `find` feature, including sequence diagram [\#62](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/62)
    * Added documentation for integration of NUSMods for `add` and `edit` features [\#214](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/214)
    * Added documentation for Command Stalling feature [\#217](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/217)
    * Updated documentation for `Ui` component, including class diagram [\#62](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/62)
    * Updated documentation for manual testing [\#87](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/87)   
    * Updated documentation for `find` feature [\#215](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/215) 
    * Updated documentation for `delete` feature [\#217](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/217)
    

* **Community**:
  * Assisted in testing and reporting bugs for another team's product:<br>
  [\#1](https://github.com/mhdsyfq/ped/issues/1),
  [\#2](https://github.com/mhdsyfq/ped/issues/2),
  [\#3](https://github.com/mhdsyfq/ped/issues/3),
  [\#4](https://github.com/mhdsyfq/ped/issues/4),
  [\#5](https://github.com/mhdsyfq/ped/issues/5),
  [\#6](https://github.com/mhdsyfq/ped/issues/6),
  [\#7](https://github.com/mhdsyfq/ped/issues/7),
  [\#8](https://github.com/mhdsyfq/ped/issues/8)
