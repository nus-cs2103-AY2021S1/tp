---
layout: page
title: Wu Yujin's Project Portfolio Page
---

## Project: ConciergeBook

ConciergeBook is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - faster than traditional GUI apps.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W14-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=SherryWu178&tabRepo=AY2021S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

Given below are my contributions to the project.

* **New Feature**: Add the ability to find Booking with different parameters [#68](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/68)
    * What it does: allows the user to find booking(s) with specific room ID, person ID, start date, end date and isArchived state.
    * Justification: This feature improves the product significantly because a user need this command to find the booking when he/she only has partial information about the booking, e.g. to find out which room a customer is allocated to within the start date and end date period.
    * Highlights: This enhancement takes multiple parameters, and they can also be optional. The implementation also involves writing multiple predicates and compressing multiple predicates into one in the command level to feed into the filteredList in model. 

* **New Feature**: Add the ability to delete booking [#89](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/89) [#109](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/109)
    * What it does: allows the user to delete the booking permanently with the given booking ID. This is different from archiving as it wipes out the entry from the database entirely.
    * Justification: This feature is essential for the users to manage bookings.

* **New Feature**: Implement the Booking class [#50](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/50)
    * What it does: The Booking class keeps information about a booking in the form of person ID, room ID, start date and end date and archive state.
    * Justification: It is essential to the app's main purpose: manage bookings.
    * Highlights: This enhancement include change to both model and storage, which require in-depth knowledge about the programme flow. It is also closely related to the Person and Room class.

* **Contribution to team-based task**: Implement BookingBook [#58](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/58)
    * Modify model and storage 
    * Add tests for JsonAdaptedBooking, JsonBookingBookStorage [#136](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/136)
  
* **Contribution to team-based task**: Refactor addressBook to bookingBook [#199](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/199)
    * Rename occurrence in relevant code and .json files
 
* **Enhancements to existing features**:
  * Modify delete person feature so that when a person is deleted, all the related booking(s) are deleted (cascading delete). [#04](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/204)

* **Documentation**:
  * Developer Guide:
    * Add the section on findBooking, deleteBooking and Booking class.
    * Add the UML sequence diagram for findBooking and deleteBooking.
    
  * User Guide:
    * Add the section on findBooking and deleteBooking command.



