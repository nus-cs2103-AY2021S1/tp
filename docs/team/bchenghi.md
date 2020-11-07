# Boh Cheng Hin - Project Portfolio

## Project: SimplyKitchen

![Application Logo](../images/Logo.png)

![Labelled Ui Diagram](../images/SimplyKitchenPPP.png)

# Overview
SimplyKitchen is a desktop application for food inventory management.
More importantly, SimplyKitchen is optimised for users who prefer working with a Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).
With intuitive and user-friendly features, SimplyKitchen facilitates food management tasks which can be done faster and more efficiently than traditional GUI applications.
Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

## Summary of contributions
* **Enhancements**:
  * Implemented `Description` field for `Food`.
    * What it does: User can specify a description for each food item to identify them in the app.
    * Justification: A description suits our application better as in addition to a food item's name, a description of the food item will allow the user to provide more information of the food, making it easier to identify each food item.
    * Highlights: This update required good coordination with team members, as they were updating different fields as well. Our updates often occurred on the same files, resulting in many merge conflicts each time a member merged their changes. We managed to streamline the merging process by communicating the order of merge, to reduce merge conflicts.

  * Implemented undo/redo commands.
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature benefits users greatly as it provides them with an easy way to remedy any incorrect input entered into the application, improving the user-friendliness of the application.
    * Highlights: This enhancement affected commands that were added after implementation, and will affect commands added in the future. It required discussions with team members who implemented commands that were undoable, and we discussed the method of implementation for those commands.
    * Credits: *{https://github.com/se-edu/addressbook-level4 was referenced for this feature, but changes were made to fit into the architecture of the application}*

  * Added conditions for duplicate food items.
    * What it does: This requires users to add a `Food` that do not have the same `Description`, `ExpiryDate` and `Tags` as another `Food`. The app shows an appropriate error message when the user attempts to add a duplicate `Food` or edit an existing `Food` to be a duplicate.
    * Justification: Previously, a `Food` was a duplicate if it had the same `Description`, and either the same `Priority` or `ExpiryDate`. This was not very applicable to the application, and it would have caused confusion to users. The duplicate condition was changed to check for fields that we thought were appropriate fields for identifying food items.
    * Highlights: This required changes to test cases on duplicate food items as well as the error messages for commands due to duplicate food items.

* **Enhancements to existing features**:
  * Expired and Expiring lists shows tags for each `Food`, and ensured the tags are always visible by wrapping them if they are larger than the width of the GUI. Implementation was difficult as it required an understanding of FlowPane, widthProperty of Nodes and use of listeners to update width of nodes in JavaFX for the wrapping to work correctly. It also involved many tests and trials. (Pull request [\#173](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/173))
  * The order of food cards in the Expired and Expiring lists used to change unexpectedly when fields of foods in the lists were edited. This was remedied by sorting them by their `Description` first, then `ExpiryDate`, when previously it was sorted by just `ExpiryDate`. (Pull request [\#181](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/181))

* **Project management and contributions to team-based tasks**:
  * Created the fridge icon in the SimplyKitchen logo.
  * Contributed to planning and ideation of application.
  * Responsible for all documentations: responsible for the quality of all project documentation.
  * Responsible for testing: ensured proper testing and the completion of testing within a time frame.
  * Responsible for integration: responsible for the versioning of the code, maintaining the code repository and integrating various parts of the software to create a whole.
  
* **Code contributed**:
[[Functional code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=bchenghi&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=bchenghi&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)]  [[Testing code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=bchenghi&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=bchenghi&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)]

* **Documentation**:
  * User Guide:
    * Updated the documentation for `delete`, `find`, `help` commands. [\#32](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/32), [\#75](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/75)
    * Added guides for `undo` and `redo` commands. [\#87](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/87)
    * Added benefits to each command description for user to understand the purpose of each command better. [\#131](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/131)
  * Developer Guide:
    * Added use cases, user stories, and description of implementation for `undo` and `redo`. [\#87](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/87), [\#128](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/128)
    * Updated all existing UML diagrams to have class and object names used in SimplyKitchen, and updated occurrences of AddressBook and its related terms in the guide to terms for SimplyKitchen. [\#84](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/84)
    * Added a table of contents, added \"Back to top buttons\" and updated the \"About this document\" section with hyperlinks. [\#182](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/182)

* **Review/mentoring contributions:**:
  * PRs reviewed (with non-trivial review comments): [\#50](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/50), [\#59](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/59), [\#83](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/83)

* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/25), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/222), [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/223))
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/bchenghi/ped/issues/2), [2](https://github.com/bchenghi/ped/issues/9), [3](https://github.com/bchenghi/ped/issues/12))
