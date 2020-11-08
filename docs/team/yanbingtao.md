---
layout: page
title: Yan BingTao's Project Portfolio Page
---

## Project: tCheck

tCheck is a desktop application that offers an integrated system to efficiently manage a bubble tea shop, of the
 (imaginary) brand T-sugar, by providing sales tracking, ingredient tracking and manpower management. It is optimized
  for CLI users to update and retrieve the information more efficiently.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=T12-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed Pull Requests on GitHub's team repository.
  
* **Added _archive_ feature**: Allows user to archive a single/all employees' contact information by using
 commands, `c-archive` or `c-archive-all`, respectively.
  * Commands: `c-archive` or `c-archive-all`
  * What it does: Allows users to archive an employee or all employees from the employee directory.
  * Justification: A bubble tea shop manager may want to remove some employees from the employee directory, but still
   keep their contact information for future use.
  * Scenario: A part-time employee Alice has left the bubble tea shop, but she plans to come back to work 2 months
   after. The manager wants to archive Alice's data, and retrieve her data back when she comes back again in the future. 
  * Pull Request: [#66](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/66),
  [#77](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/77)
  
* **Added _unarchive_ feature**: Allows user to unarchive a single employee's contact information by using
 commands `c-unarchive`.
  * Command: `c-unarchive`
  * What it does: Allows users to unarchive an employee from the archived employee directory.
  * Justification: A bubble tea shop manager may want to move back some employees from the archived employee directory.
  * Scenario: A part-time ex-employee Alice worked in the bubble tea shop before, but she left for a few months.
  The manager wants to retrieve Alice's data back from the archived database. 
  * Pull Request: [#76](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/76)
  
* **Added _list-all-archived-employees_ feature**: Allows user to see a list of all archived employees.
  * Command: `c-archive-list`
  * What it does: Scans through every employee and lists down all archived employees.
  * Justification: A bubble tea shop manager may want to see a list of all archived employees.
  * Scenario: A manager wants to get a contact number of an ex-employee. He/She could use this feature to find it.
  * Pull Request: [#67](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/67)
  
* **Added _find-today's-available-manpower_ feature**: Allows user to see a list of today's available employees.
  * Command: `c-today`
  * What it does: Scans through every employee and lists down those whose tags contain today's day (i.e. Wednesday
  , Tuesday, etc).
  * Justification: When manager doing work schedule planning, he/she may want to know who are available to work today.
  * Scenario: A part-time employee Alice took a MC in the morning and informed the manager that she couldn't go to
   work today. The manager could use this feature to see who are available today, and find another employee to
   replace Alice.
  * Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141)
  
* **Added _find-tomorrow's-available-manpower_ feature**: Allows user to see a list of tomorrow's available employees.
  * Command: `c-tomorrow`
  * What it does: Scans through every employee and lists down those whose tags contain tomorrow's day (i.e. Wednesday
  , Tuesday, etc).
  * Justification: When manager doing work schedule planning, he/she may want to know who are available to work
   tomorrow.
  * Scenario: A manager may want to know who are available tomorrow, so that he/she could plan the work schedule.
  * Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141)

* **Added _find-tag_ feature**: Allows user to find all the matched employees whose tag(s) contains the given keyword(s).
  * Command: `c-tag-find`
  * What it does: Scans through every employee and lists down those whose tags contain one or more keywords(given by
   the user).
  * Justification: Manage needs to do a multi-level search to get a specific group of employees.
  * Scenario: A manager may want to know who are the part-time employees that are available on Friday when doing work
   planning. So he/she can use the command `c-tag-find friday PartTime` in this case to find all part-time employees
    that are available on Friday.  
  * Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141)
  
* **Implemented _`salesbook` storage_**: can save the sales record data in `salesbook` in json format and read it
 back.
  * Command: tCheck will auto-save the data after each command executed. When program starts, data will be read from
   the json file to application.
  * What it does: After each command executed, tCheck will save the sales record data in `salesbook` to a json file
   and read the data back from the json file when application starts.
  * Justification: Sales record data needs to be stored in a file, so that it can be read into application in the
   future.
  * Pull Request: [#96](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/96)
  
* **Implemented _calendar-pane_ in GUI**: can show a calendar for the current month.
  * Command: The calendar will always be shown in the GUI.
  * What it does: When application starts, the calendar for the current month will be shown in the calendar pane.
  * Justification: Manager may need a calendar to refer to for work planning.
  * Scenario: In Nov 2020, the manager wants to know what day is it on 20/Nov/2020. He/She could refer to the
   calendar view to know 20/Nov/2020 is Friday.
  * Pull Request: [#135](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/135)

* **Separated the main pane to 4 panes**: can display sales record data, ingredient tracking data,
 employees' contact info, and a calendar independently. 
  * Pull Request: [#89](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/89)

* **Enhancements to existing features**:
  * Improved the contact GUI by adding a title in front of each data.
  
    1. For example:
    
        Instead of displaying "81234567", now the contact pane shows "Contact Number: 81234567". 
        
    1. Pull Request: [#89](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/89)

* **Contributions to team-based tasks**:
    * Changed the product icon (Pull Request: [#89](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/154))
    * Changed the main window title name to our product name (Pull Request: [#154](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/89))
    * Maintained all meeting minutes.
    * Contributed to set meeting agenda before each meeting.
    * Managed all Pull Requests in team repository (by adding correct labels, linking to correct milestone, linking to
     correct issues if any).
    * PRs reviewed (with non-trivial review comments):
     [#20](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/20),
     [#24](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/24),
     [#62](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/62),
     [#105](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/105),
     [#168](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/168),
     [#169](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/169)
    
* **Documentation**:
  * AboutUs page:
    * Updated my information in the AboutUs page of the project website.
  * User Guide:
    * Added documentation for the features `c-archive`, `c-archive-all`, `c-archive-list`, `storage` in v1.1.
    (Pull Request: [#27](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/27))
    * Added table-of-contents and internal links in v1.1.
    (Pull Request: [#36](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/36))
    * Added numberings in the _Features_ section in v1.1.
    (Pull Request: [#36](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/36))
    * Added documentation for the features `c-unarchive` in v1.3.
    (Pull Request: [#112](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/112))
    * Updated documentation for the features `c-archive`, `c-archive-all`, `c-archive-list`, `storage` in v1.3.
    (Pull Request: [#112](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/112))
    * Added documentation for the features `c-today`, `c-tomorrow`, `c-tag-fin` in v1.3.
    (Pull Request: [#145](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/145))
  * Developer Guide:
    * Added documentation for Non-functional requirements of the product under _Appendix: Requirements_ in v1.1.
    (Pull Request: [#26](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/26))
    * Added documentation (explanations, sequence diagrams, activity diagrams, class diagrams, etc) for the features
    `c-archive`, `c-unarchive`, `c-archive-all` v1.3.
    (Pull Request: [#99](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/99))

