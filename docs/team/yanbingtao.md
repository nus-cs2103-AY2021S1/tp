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
 (Pull Request: [#66](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/66),
 [#77](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/77))
  
* **Added _unarchive_ feature**: Allows user to unarchive a single employee's contact information by using
 commands `c-unarchive`. (Pull Request: [#76](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/76))
  
* **Added _list-all-archived-employees_ feature**: Allows user to see a list of all archived employees. 
(Pull Request: [#67](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/67))
  
* **Added _find-today's-available-manpower_ feature**: Allows user to see a list of today's available employees.
(Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141))
  
* **Added _find-tomorrow's-available-manpower_ feature**: Allows user to see a list of tomorrow's available employees.
(Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141))

* **Added _find-tag_ feature**: Allows user to find all the matched employees whose tag(s) contains the given keyword(s).
(Pull Request: [#141](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/141))

* **Implemented _`salesbook` storage_**: can save the sales record data in `salesbook` in json format and read it
 back. (Pull Request: [#96](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/96))

* **Implemented _calendar-pane_ in GUI**: can show a calendar for the current month.
(Pull Request: [#135](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/135))


* **Separated the main pane to 4 panes**: can display sales record data, ingredient tracking data,
 employees' contact info, and a calendar independently. 
 (Pull Request: [#89](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/89))

* **Enhancements to existing features**:
  * Improved the contact GUI by adding a title in front of each data. 
  (Pull Request: [#89](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/89))
    For example, instead of displaying "81234567", now the contact pane shows "Contact Number: 81234567". 

    
  * Improved the GUI by wrapping around when the displayed content is too long. 
  (Pull Request: [#228](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/228)) After I implemented
  this feature, the text will be auto wrap around, and start a new line if one line is not enough to display. 
        
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
     [#169](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/169),
     [#254](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/254),
     [#257](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/257)
    
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
    * Added documentation for the features `c-today`, `c-tomorrow`, `c-tag-find` in v1.3.
    (Pull Request: [#145](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/145))
    * Added documentation for feature `calendar` and edited documentation for the features `c-today`, `c-tomorrow`,
    `c-tag-find`, `c-archive`, `c-archive-all`, `c-archive-list`, `storage` in v1.4.
    (Pull Request: [#252](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/252))
  * Developer Guide:
    * Added documentation for Non-functional requirements of the product under _Appendix: Requirements_ in v1.1.
    (Pull Request: [#26](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/26))
    * Added documentation (explanations, sequence diagrams, activity diagrams, class diagrams, etc) for the features
    `c-archive`, `c-unarchive`, `c-archive-all` in v1.3.
    (Pull Request: [#99](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/99))
    * Added documentation and edited the class diagram for UI component, Storage component under _Design_ in v1.4.
    (Pull Request: [#253](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/253))
    * Added documentation and the class diagram for `Person` sub-component in Model component under _Design_ in v1.4.
    (Pull Request: [#253](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/253))
    * Edited use cases (UC01 & UC02) based on our implementation under _Appendix: Requirements_ in v1.4.
    (Pull Request: [#253](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/253))
    * Added documentation for manual testing ("Archiving an employee" section) under _Appendix: Instructions for manual
     testing_ in v1.4.
    (Pull Request: [#253](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/253))
