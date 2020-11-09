---
layout: page
title: Christopher Leo's Project Portfolio Page
---

#### Description of Project:

PropertyFree is a real estate management application meant for property agents to organize their properties and meetings more efficiently.
Other application can keep track of meetings and schedules but there are relatively few applications that help track properties 
and associate them with meetings with clients.  

For our project we hope to make it more streamlined for agents to keep track of their property and have clear information about those properties
and their clients.  

#### My Contributions to PropertyFree

* **Sort Meeting Feature**: Added a command to sort meetings either ascendingly or descendingly based on the meeting date.
* What it does: allows the user to sort the meetings in the meeting book according to the date of the meeting.
  * Justification: A user might want to sort the list of meetings that he has so that it is easier for him to identify his next meeting easily.
  * Highlights: The implementation of this command was challenging as it required the sorting of all 3 types of meeting according to the date object attribute it has.

* **Add Meeting Feature**: Added a command to add 3 types of meetings, paperwork, admin and viewing with various parameters.
* What it does: allows the user to add meetings in the meeting book based on the type of meeting he has.
* Justification: A user might want to add meetings to the meeting book so that he can track his meetings.
* Highlights: The implementation of this command was challenging as it required the storing of all 3 types of meeting and the various attributes that a meeting object has.

* **Edit Meeting Feature**: Added a command to edit one or more parameters of an existing meeting in the meeting book.
* What it does: allows the user to edit an existing meeting in the meeting book based on one or more parameters.
* Justification: A user will want to edit meetings in the meeting book so that he can easily change the meeting details if needed.
* Highlights: This implementation was straight forward, but enabling more than one parameter was challenging. It would improve the user's convenience significantly as the user could input all the edits into one command line.

* **Meeting Attributes**: Added the various attributes for the meeting objects.
* What it does: Allows the user to add the various parameters to create a meeting object.
* Justification: This feature allows the user to make the meeting object with the various parameters.
* Highlights: The team discussed heavily on the parameters of a meeting object that would benefit a property agent.

* **Design**: Lead the technical aspect for the meeting entity.
* What: Designed most of the methods in model, storage and logic for meeting so that the meeting entity is easily refactored or tested.

* **Test**: Made the Junit tests for meeting and meeting commands.  
* What: Implemented the various tests for all the MeetingCommandParser files and also for the meeting files under testutil.

* **Meeting Commands**: Added the commands for the meeting entity and also the tests that are associated with it.
* What it does: Creates the appropriate meeting command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=christopher&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=munharsha&tabRepo=AY2021S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
* Managed the weekly tasks and helped keep the team in check when we had slow pace or had fallen behind.

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage and one example is from 65% to 67% (Pull requests [\#272](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/272))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add-m`, `edit-m` and `sort-m` 
    * Did cosmetic tweaks and helped tidy up the existing command table to make it more presentable.
  * Developer Guide:
    * Added implementation details of the `LOGIC` component for the system.
    * Added implementation details of the `edit` feature.
    * Helped with the formatting and the standardisation of the overall document, and added use cases.

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
[\#208](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/208), [\#256](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/256), [\#134](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/134)]
  * Contributed to forum discussions (examples: [#222](https://github.com/nus-cs2103-AY2021S1/forum/issues/222), [#147](https://github.com/nus-cs2103-AY2021S1/forum/issues/147), [#223](https://github.com/nus-cs2103-AY2021S1/forum/issues/223))
  * Reported bugs and suggestions for other teams. Reported 14 bugs for other teams such as T14-2.
  [1](https://github.com/Christopher-LM/ped/issues/7)
  [2](https://github.com/Christopher-LM/ped/issues/6)
  [3](https://github.com/Christopher-LM/ped/issues/13)
  [4](https://github.com/Christopher-LM/ped/issues/3)
