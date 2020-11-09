---
layout: page
title: Kor Ming Soon's Project Portfolio Page
---

#### Description of Project:

PropertyFree is a real estate management application meant for property agents to organize their properties and meetings more efficiently.
Other application can keep track of meetings and schedules but there are relatively few applications that help track properties 
and associate them with meetings with clients.  

For our project we hope to make it more streamlined for agents to keep track of their property and have clear information about those properties
and their clients.  

#### My Contributions to PropertyFree

Below are the details of my contribution:
* **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kormingsoon&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

##### Bidder and Seller Functions
* **New Feature**: Implemented the `Add` function for `Bidder` and `Seller`
  * **What it does**: allows the user to add and `Bidder` and `Seller`, who are the clients engaged by the user (a Property Agent).
  
  * **Justification**: This feature is pivotal as the other entities, namely, `Bid`, `Property` and `Meeting` all require and interact
  with the `Bidder` and `Seller`. Such as how each`Seller` is required to be tied with a property listed on PropertyFree 
  or how each `Bidder` ties with `Bid`. 
  
  * **Highlights**: 
    - Establishing a way of uniquely identifying `Bidder` and `Seller`, which we eventually decided on `Id` class.
    - Establishing threshold limits for parameters such as `Name` and `Phone`, such the maximum input length acceptable.
  * Credits: *To fellow group mate Dianne for creation of the `Id` class which was easily adapted into `Bidder` and `Seller`*

* **New Feature**: Implemented the `Delete` function for `Bidder` and `Seller`
  * **What it does**: allows the user to delete and `Bidder`s and `Seller`s, who are the clients engaged by the user (a Property Agent).
  
  * **Justification**: This feature is important as the user may want to remove certain clients for any reasons such as client's loss of 
  interest or if the deal is already closed. 
  
  * **Highlights**: Implementing "cascading" deletion function where the deletion of:
        - `Bidder` also subsequently deletes all `Meeting` and `Bid` that it is tied to. This is done by leveraging on the unique `Id` that is assigned
        to `Bidder` and captured by `Meeting` and `Bids`.
        - `Seller` also subsequently deletes all `Property` that it is tied to. This is done by leveraging on the unique `Id` that is assigned 
        to `Seller` and captured by `Property`.

* **New Feature**: Implemented the `Find`, `List` and `Edit` function for `Bidder` and `Seller`
  * **What it does**: Allows the user to edit`Bidder` and `Seller`, or find clients based on their names. Lastly to list it all after being filtered.
  
  * **Justification**: This feature is important as the user may have to change certain information, filter out and list all information.
  
  * **Highlights**:
   My implementation of the `Edit` function prevents any clients from having the same name or same phone number 
      
##### User Interface Functions

Major edits mostly done in `.fxml` to fit the look to PropertyFree.

* **New Feature**: Implemented the `AutoTab` switching function for PropertyFree
  * **What it does**: Changes the tab from entity to entity depending on the command executed by the user.
  
  * **Justification**:  Since PropertyFree uses a TabBar interface in JavaFx for displaying the various list of entities,
  the TabBar actually defied the constraint of the user being more inclined with command line interface as the user had to click
  on the various tabs to navigate. Hence, the `AutoTab` feature was implemented to turn this disadvantage around. 
   
* **New Feature**: Implemented the key-press function for PropertyFree
  * **What it does**:  navigates the calendar to the next month and to previous month. Automatically focuses on the TextField in 
    command box so that the user is able to type commands.
  
  * **Justification**: The above-mentioned key press functions in PropertyFree helps the user to easily navigate PropertyFree.
  The button control helps the user to easily navigate through the calendar to identify which dates and days are important.

* **New Feature**: Implemented the `next` and `prev` command for navigating the calendar.
  * **What it does**: Changes the `CalendarView` from month to month depending on the command executed by the user.
  
  * **Justification**: To cater to different keyboard layouts, `next` and `prev` command serve as an alternate option for users
  to navigate the calendar.
  The `CalendarView` (both `.java` and `.fxml`) was referenced from [Senior Team Project](https://github.com/SirGoose3432/javafx-calendar/blob/master/src/FullCalendarView.java) 
* **Project management**:
  * Managed releases `v1.3a`, `v1.3b`, `v1.4` (3 releases) on GitHub
  * Managed milestones for `v1.2` and `v1.3`

* **Enhancements to existing features**:
  * Complete overhaul of the GUI (Pull requests [\#194](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/194), [\#182](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/182))
  * Wrote additional tests for existing features to increase coverage with respective to `Bidder` and `Seller` commands.

* **Community**:
  * PRs reviewed (with non-trivial review comments) (examples:
  [\#201](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/201),
  [\#208](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/208),
  [\#150](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/150),
  )
  * Contributed to forum discussions (examples: 
  [\#172 Guide on PR to Master](https://github.com/nus-cs2103-AY2021S1/forum/issues/172), 
  [\#153](https://github.com/nus-cs2103-AY2021S1/forum/issues/153), 
  [\#207](https://github.com/nus-cs2103-AY2021S1/forum/issues/207),
  [\#213](https://github.com/nus-cs2103-AY2021S1/forum/issues/213))
  * Reported bugs and suggestions for other teams in the class 


