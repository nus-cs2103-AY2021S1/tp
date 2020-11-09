---
layout: page
title: Wang Qian's Project Portfolio Page
---

## Project: tcheck

tCheck is a desktop application that offers an integrated system to efficiently manage a bubble tea shop, of the
 (imaginary) brand T-sugar, by providing sales tracking, ingredient tracking and manpower management. It is optimized
  for CLI users to update and retrieve the information more efficiently.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=persdre&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed code quality
  * Managed DDLs
 
* **Added _emergency-contact_ feature**: Allows users to add employees' emergency contact by using `c-add`. Emergency contact's information can be edited by `c-edit`.
  * Commands: `c-add`, `c-edit`
  * What it does: Allow users to add emergency contact details when adding an employee. Respectively, users can change the emergency contact details of an employee.
  * Justification: A bubble tea shop manager may want to know employees' emergency contact details so that they can handle some emergency scenarios. 
  * Scenario: An employee Alice suddenly faints at the bubble tea shop. The manager wants to contact her families or friends to tell them what happens.
  * Pull Request: [#63](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/63), [#84](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/84)   
  
* **Added _sales-find_ feature**: Allows users to find specific drinks' sales data by using `s-find`. 
  * Commands: `s-find`
  * What it does: Allow users to find specific drinks' sales data in Sales Tracker.
  * Justification: A bubble tea shop manager may want to know specific drinks' sales data to estimate whether the inventory is enough when drinks' kinds are too many to locate them with eyes.
  * Scenario: The bubble tea shop manager wants to know BSBBT's sales data.
  * Pull Request: [#136](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/136)  

* **Enhancements to existing features**:
  * Add restrictions to phone numbers and tag's length.
  
    1. For example:
    
        Previously, the phone number just needed to be longer than or equals to 3 digits. But it does not fit in Singapore.
        So I set the limit to only 8 digits' number starting with 8 or 9.
        
    1. Pull Request: [#262](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/262)
    
  * Change original command words to be more consistent with others' commands
  
    1. For example:
    
        Previously, commands are all based on the contact information so they didn't have prefixes. But after we involved
        sales of drinks and ingredients part, these commands shouldn't be as it was. So I added a "c-" prefix to them,
        and rephrased "c-search" to "c-find" to match others' commands.
        
    1. Pull Request: [#169](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/169), [#88](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/88)

* **Contributions to team-based tasks**:
    * Set up the GitHub team organization and repository.
    * Set up CI.
    * Managed v1.2 release.
    * Schedule every week's meeting and provided zoom meeting rooms for members' casual needs.
    * Remind DDLs.
    * PRs reviewed (with non-trivial review comments):
     [#70](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/70),
     [#72](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/72),
     [#91](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/91),
     [#92](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/92),
     [#99](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/99),
     [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141),
     [#157](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/157),
     [#253](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/253),
     [#254](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/254),
     [#265](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/265)  
   
* **Contributions beyond the Project Team**:
  * Helped others on the forum:
    [#26](https://github.com/nus-cs2103-AY2021S1/forum/issues/26)

* **Documentation**:
  * AboutUs page:
    * Updated my information in the AboutUs page of the project website.
    (Pull Request: [#12](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/12))    
  * User Guide:
    * Added documentation for the features `c-add`, `c-edit`, `c-delete`, `c-find`, and `c-clear` in v1.1.
    (Pull Request: [#14](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/14))
    (Pull Request: [#30](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/30))
    * Updated documentation for the features `c-add`, `c-edit`, `c-delete`, `c-find`, and `c-clear` in v1.3.
    (Pull Request: [#117](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/117))
    * Updated documentation for the features `c-add`, `c-edit`, `c-delete`, `c-find`, `c-clear`, and `s-find` in v1.4.
    (Pull Request: [#169](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/169))
    (Pull Request: [#237](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/237))   
  * Developer Guide:
    * Change formats, screenshots and table of contents in v1.4.
    (Pull Request: [#275](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/275))
    * Added `c-add`, `c-delete`, `c-find`, `c-clear`, `c-edit` and `s-find` features in v1.4.
    (Pull Request: [#259](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/259))
    * Added Model Overview UML and sequence diagram in v1.4.
    (Pull Request: [#259](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/259))
 