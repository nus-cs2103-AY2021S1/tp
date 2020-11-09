---
layout: page
title: Chua Eugene's Project Portfolio Page
---

## Project: ConciergeBook

ConciergeBook (CB) is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 
ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - 
faster than traditional GUI apps.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=iamgenechua&tabRepo=AY2021S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

#### Implementation
* **New user feature**: Add Booking [#51](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/51), [#213](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/213)
    1. What it does: allows the user to add booking with various parameters tied to the booking.
    These parameters are the person ID, room ID, start date and end date.
    2. Justification: this feature is essential, as bookings need to be made to book the room for the hotel's guests
    so that they can stay at the hotel. Without this service, the hotel would not be able to house guests and be
    profitable.
    3. Highlight: the implementation does multiple validation checking of dates, ensuring that the start date is before the end date, and that the dates are not more than 30 nights apart.

* **New user feature**: Unarchive Booking [#216](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/216)
    1. What it does: allows the user to unarchive bookings, so that they can restore an archived booking in the future 
    should they change their mind, or if they accidentally archived a booking that was not meant to be archived.  
    2. Justification: this feature is a good-to-have for users, as an unarchive functionality serves as an 'undo' function
    for users who want to restore an archived booking back to an active booking. This provides an excellent opportunity
    for users to recover from mistakes, which is aligned with rule number #9 of Jakob Nielsen's 10 Usability Heuristics for User Interface Design.  

* **New user feature**: Get Bill [#71](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/71)
    1. What it does: allows the user to get the current bill of a particular booking.
    2. Justification: this feature is essential for users. A hotel is only profitable when it is able to bill its customers.
    This feature is useful when the customer wants to enquire about their bill for that particular booking, or when the hotel intends to charge the 
    customer. This feature allows users to see how much the customer owes the hotel so that the customer is being billed fairly.
    3. Highlight: The bill displayed is broken down into the base cost (price of staying in the room) and the additional cost 
    incurred from ordering room services. This allows the user to understand how the bill is derived in case customers dispute
    the costs.

* **Enhancements to existing features**: Allowed user input for ordering room service to be case-insensitive [#213](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/213)
    * User may find it troublesome to have to type the room services fully in capital letters

* **Contribution to team-based tasks**: Implemented Storage Feature for Room Service Book [#97](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/97)
    * Ensure that room service details are saved even if the application is closed and reopened again

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#230](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/230#discussion_r519246460)

#### Documentation
   1. User Guide:
        1. Revamped the user guide to remove multiple typos  
        2. Documented usage for addBooking feature [#225](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/225)
        3. Improved Documentation usage for getBill feature [#225](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/225)
   2. Developer Guide:
        1. Documented implementation for AddBooking feature [#227](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/227)        
        2. Documented implementation for UnarchiveBooking feature to merge with ArchiveBooking feature [#233](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/233)
        3. Made initial draft for Use Cases [#34](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/34)
        4. Update Use Case for Archive and Unarchive feature [#233](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/233)  

