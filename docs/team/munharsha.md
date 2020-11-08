# Munnamgi Harsha Vardhan Reddy - Project Portfolio
___
## Project: PropertyFree

Given below are my contributions to the project.

Overview:

This document is a project portfolio that documents all contributions made to PropertyFree's development by Harsha.
___

## Description of Project:

PropertyFree is a real estate management application meant for property agents to organize their properties and meetings more efficiently.
Other application can keep track of meetings and schedules but there are relatively few applications that help track properties 
and associate them with meetings with clients.

For our project we hope to make it more streamlined for agents to keep track of their property and have clear information about those properties
and there clients.
___

##Developers of PropertyFree:

PropertyFree is developed by a group of second year Computer Science Students from the School of Computing, National
University if Singapore. This was developed for the team projects assignment fro the module CS2103 and is a
brown-field project adapting from existing projects.
___

## My Contributions to PropertyFree

* **Find Meeting Feature**: Added the ability to find meetings based on certain attributes.
  * What it does: Allows the user to find meetings based on various parameters and also allows the user to find based on multiple features also.
  * Justification: This feature improves the product significantly because a user might want to find the meeting with ease rather then filtering through the list manually.
  * Highlights: The implementation of this command was challenging as I had to make sure that users are able to find based on multiple number of attributes in different order.

* **Delete Meeting Feature**: Added the ability to delete meetings.
  * What it does: Allows the user to delete meetings based on the id of the meeting.
  * Justification: This feature allows the user to remove meetings that have been cancelled or the user feels is not necessary anymore.
  * Highlights: The implementation was rather straight forward however, there was some issues faced with zero indexing.

* **List Meeting Feature**: Added the ability to list meetings.
  * What it does: Allows the user to list all the meetings that have been made.
  * Justification: This feature allows the user see the list of all the meetings that have been made so that he is able to see his schedule and add another meeting.
  * Highlights: The implementation was rather straight forward.
  
* **Meeting Attributes**: Added the different types of meeting along with the attributes for the meeting.
  * What it does: Allows the user to add the different types of meeting.
  * Justification: This feature allows the user to make the different meetings with the different attributes.
  * Highlights: It was rather challenging at first on whether to use a flag system to differentiate the meetings or to use
  an abstract class. After trail and error, we realised it was better to use the abstract class method and we stuck to it.
 
* **Design**: Lead the basic overall design for the meeting entity.  
    * What: Made the basic framework in model, storage and logic for meeting so that the other commands could be easily added.

* **Test**: Made the Junit tests for meeting and meeting commands.  
    * What: Implemented the various tests for all the MeetingCommand files and also for the meeting files under model.
    
* **Meeting Display**: Added the javafx files for meeting which then displays the meetings on the GUI.
  * What it does: Allows the user see the different meetings on the GUI.
  * Justification: This feature allows the user to view the meetings on the GUI..
  * Highlights: There was a bit of a challenge here in debugging as there was instance of wrong names being used. Implementation
  was fairly straightforward.
  
* **Meeting Storage**: Added the storage files for the meeting and also the tests that are associated with it.
  * What it does: Stores the different types of meetings that are generated.
 
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=harsha&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=munharsha&tabRepo=AY2021S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed the deadlines for the different weekly tasks and made sure that the tasks are done by then.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add-m`, `find-m`, `sort-m` and `list-m` 
    * Did cosmetic tweaks to the UG by adding in more tables to make it more readable.
  * Developer Guide:
    * Added implementation details of the `sort` feature.
    * Helped with the formatting and the standardisation of the overall document.
    * Added implementation details of the `storage` feature.
    * Added user stories.

* **Community**:
  * PRs reviewed (with non-trivial review comments)
  * Reported bugs and suggestions for other teams in the class



