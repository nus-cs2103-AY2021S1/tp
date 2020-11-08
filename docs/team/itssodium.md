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

* **Enhancements to existing features**:
  * Wrote additional test cases for some of the features and classes that I created

* **Additional Code to aid Feature**:
  * Added most of the classes for rooms, including reading, writing and storing room classes
