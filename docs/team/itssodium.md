---
layout: page
title: Noorul Azlina's Project Portfolio Page
---

## Project: Covigent

Covigent is a desktop application that is used to aid hotel staff handle the Covid-19 situation better. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to search for an empty room.
  * What it does: allows user to find out an empty room, if there are any. This information can be used to add in the
  * Justification: This feature allows the user to check the room number for an empty room, then inserting the patient there.

* **New Feature**: Added the ability to initialise the a certain number of rooms.
  * What it does: allows user to define a certain number of rooms to exist in the hotel.
  * Justification: This feature allows the user to continuously define the number of rooms as needed as the users can make a mistake in defining them.
  * Highlights: This feature retains the information that is previously stored in the rooms and does not delete them. However, if user were to define the number of rooms less than existing number of occupied rooms, then error is thrown as it is not practical for visitors to leave hotel.

* **New Feature**: Added the ability to list all rooms defined by user
  * What it does: allows user to look at all rooms defined by user as the UI can change when displaying an empty room.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=itssodium)

* **Enhancement to existing features**:
  * move tab panel from top to the left [\#105](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/105)
  * wrote unit test cases for `initroom` and `findemptyroom` commands and room attributes. [\#21](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/21)

* **Additional Code to aid Feature**:
  * Added most of the classes for rooms [\#26](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/26)
  * Handled storage in Json format for Rooms and Tasks. [\#64](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/64), [\#84](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/84)

* **Documentation**:
  * User Guide:
    * Added documentation for features `initroom`, `findemptyroom` and `listroom`
    * Add FAQ section for UG to answer common questions asked my users
  * Developer Guide:
    * Updated the implementation for Storage
    * Added implementation details for Room
    
* **Community**:
  * Reported Bugs and suggestions for another team, TrackIt [link](https://github.com/itssodium/ped/issues)
  * PRs reviewed (with non-trivial review comments): [\#69](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/69), [\#85](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/85) and [\#90](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/90)
