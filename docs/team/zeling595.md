---
layout: page
title: Long Zeling's Project Portfolio Page
---

## Project: ConciergeBook

ConciergeBook is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Ability to edit booking specified by booking ID. 
    * What it does: allow users to edit the start date, end date and room ID of an existing booking.
    * Justification: This feature is an essential feature of the product as editing a booking is a functionality that will be used very frequently. It is common for guests to change their bookings due to various reasons such as change of travel plan and emergencies. It is also common for the user to make mistakes when he/she keys in data. It is more convenient to edit than delete and add a new one.
    * Highlights: The implementation of the command is challenging as it needs to check for multiple errors such as duplicate booking error and conflicting booking error.
    * Credit: I have reused and modified code in AB3 to suit my needs.

* **New Feature**: Ability to list all bookings.
    * What it does: list all the bookings.
    * Justification: This feature is simple but necessary. It allows user to see all the bookings and relevant booking information. The booking ID is a mandatory input for many other commands and therefore this command precedes most other booking-related commands.
    * Credit: I have reused and modified code in AB3 to suit my needs.
 
* **Contribution to team-based project**: Implement BookingBook class 
    * Implement UniqueBookingList

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W14&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=zeling595&tabRepo=AY2021S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
    * Managed release `v1.3` (1 release) on GitHub

* **Enhancements to existing features**: 
    * Remove indexes from GUI (Pull Request [#198](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/198))
    * Update editPerson and deletePerson commands to use person ID instead indexes ([#201](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/201))

* **Documentation**:
  * Developer Guide:
    * Added sections for list booking and edit booking features.
    * Added appendix for manual testing.
  * About US:
    * Update information for team members.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#50](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/50), [\#122](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/122)


* **Tools**:
  * PlantUML.

