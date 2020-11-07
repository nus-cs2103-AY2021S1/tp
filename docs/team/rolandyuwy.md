# Roland Yu - Project Portfolio

## Project: SimplyKitchen

![Application Logo](../images/Logo.png)

![Labelled Ui Diagram](../images/TentativeLabelledUIDiagram.png)

## Overview

SimplyKitchen is a desktop application for food inventory management.
More importantly, SimplyKitchen is optimised for users who prefer working with a Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).
With intuitive and user-friendly features, SimplyKitchen facilitates food management tasks which can be done faster and more efficiently than traditional GUI applications.
Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

## Summary of contributions
* **Code contributed**:  [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#search=rolandyuwy&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&until=2020-12-02)
//**to update link**//

* **Enhancements**:
    * Added the **ability to sort food items** by description, priority and expiry date
      * What it does: allows the user to sort the list of food items permanently.
      * Justification: This feature improves the product significantly because the user's experience when navigating the list of food items in the app relies on their preference on the list's order.
      * Highlights: This feature involved changes in the way the food list is processed, where a `Comparator` attribute was added to handle different ways of sorting for each of the sorting commands created. A new class `ComparatorUtil` was created to allow for scalability, when new sorting mechanisms are implemented in the future.

    * Revamped user preferences to **allow storing of sorting preferences**
      * What it does: allows the sorting preference to be stored when the user sorts the list of food items.
      * Justification: This enhancement improves the product significantly because the food items are updated dynamically according to the stored sorting preference. For instance, when a food item's priority is edited, if the current stored sorting preference is in accordance to priority, the item will be ordered to the correct position and index in the list immediately.
      Furthermore, when the user closes and reopens the application, the list of food items remains ordered according to the stored sorting preference.
      * Highlights: This enhancement affects existing commands and commands to be added in future. It involved changes in the way the food list is processed when it changes, for commands such as `add` and `edit`.

* **Project management** and **Contributions to team-based tasks**:
    * Conceptualised project idea (Management of food inventory to reduce food wastage)
    * Designed product icon
    * Conceptualised project theme and colour scheme
    * Scheduled and led weekly project meetings
    * Assisted in setting up the GitHub team org/repo
    * Created labels for issues and pull requests on GitHub.
    * Responsible for scheduling and tracking all project tasks
    * Responsible for maintaining the issue tracker
    * Responsible for ensuring all project deliverables and deadlines are met
    * Refactored AddressBook-Level3 project code to fit project idea (Pull request [\#59]()) **TO UPDATE LINKS**
    * Managed shared team project document
    * Consolidated and vetted through all bugs from Mock Practical Exam

* **Enhancements to existing features**:
    * Improved aesthetics of GUI by fixing bugs (Pull request [\#176]())
    * Conceptualised the GUI color scheme (Pull request [\#170]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `clear`, `edit`, `sortdesc`, `sortexpiry` and `sortpriority` (Pull requests [\#73]() and [\#118]())
        * Added Ui Diagram at the introduction **TO UPDATE**
    * Developer Guide:
        * Added implementation details of the sorting feature. (Pull request [\#95]())

* **Review/mentoring contributions**:
//to update: Links to PRs reviewed, instances of helping team members in other ways//

* **Community**:
  * Pull requests reviewed (with non-trivial review comments): [\#50](), [\#55](), [\#96](), [\#123](), [\#172]()
  * Reported bugs and suggestions for other teams (examples: [1](), [2](), [3]()) **TO UPDATE**

* **Tools**:
  * Initial setup of project tools - Github organisation
  * Proposed and setup the use of the Github project board and issue tracker for project management, milestones and bugs.
