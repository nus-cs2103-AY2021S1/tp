# Jonah Tan - Project Portfolio

# Project: SimplyKitchen

![Application Logo](../images/Logo.png)

![SimplyKitchen UI](../images/SimplyKitchenPPP.png)

## Overview

SimplyKitchen is a desktop app for food inventory management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). With intuitive and practical features, SimplyKitchen can get food management tasks done faster and more efficiently than traditional GUI apps.
Users interact with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

## Summary of contributions

### New Features:

  * Added **Priority attribute to food items**.
    * What it does: Allows the user to specify a priority, either high, medium or low, for a food item.
    * Justification: This feature provides a convenient way for the user to mark the importance of different food items.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It requires the parser to identify a new prefix and perform necessary validation to ensure that the priority specified in the command is valid.

  * Added a **popup window to show a list of expired food items**.
    * What it does: If there are expired items in the inventory, a popup will automatically be launched during the start-up of the application, showing a list of expired food items. This popup can also be launch using the `expired` command.
    * Justification: This feature alerts the user of expired items in their inventory whenever the application is launched so that they will be reminded to dispose these items and not accidentally consume them. The user can also open this popup using the `expired` command so that they can access this list at any point of time.
    * Highlights: This enhancement requires an additional `FilteredList` and `Predicate`. A new command needs to be added for the user to open the popup on call. During the initialization of the application, it will need to check if there exists expired items. If no expired items are in the inventory, then the popup will not show up. 

  * Ability to **search for food items using description, expiry date, priority and/or tags**.
    * What it does: This feature allows the user to search for food items using one or more of the above parameters. All food items matching all the specified parameters will be shown.
    * Justification: This allows the user to be able to quickly and efficiently find food items with common attributes or specific food items, depending on the specificity of their search. 
    * Highlights: The existing find feature only allows for searching of descripton of food items. This enhancement affects existing commands and commands to be added in future. It required the parser to identify prefixes in the find command and filter the list according to the parameters of the search.

### Enhancements to existing features:

  * Updated the GUI (Pull requests [\#170](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/170), [\#108](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/108), [\#129](https://github.com/AY2021S1-CS2103T-F13-4/tp/pull/129))
    * Change colour scheme of application
    * Added icons to attributes
    * Added labels for items that are expiring soon or have expired
    * Added colours to priorities
    * Cosmetic tweaks to enhance UI/UX
  * Added validation for user input
    * Check for invalid prefixes in command
    * Check for duplicate prefixes that are not supposed to be repeated in the command 
  * Wrote additional tests for new and existing features to increase coverage.

#### Code contributed: 
[[Functional code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jonahtanjz&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&until=2020-11-04&tabAuthor=jonahtanjz&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)] [[Test code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jonahtanjz&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&until=2020-11-04&tabAuthor=jonahtanjz&tabRepo=AY2021S1-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)]


### Documentation:
  * User Guide:
    * Added documentation for the features `find` and `expired`
    * Did cosmetic tweaks to existing documentation
  * Developer Guide:
    * Added implementation details of the `find` feature including sequence and class diagram
    * Updated UML class diagram of Ui component
    * Added user stories

#### Documentation contributed: 
[[User Guide](https://ay2021s1-cs2103t-f13-4.github.io/tp/UserGuide.html)] [[Developer Guide](https://ay2021s1-cs2103t-f13-4.github.io/tp/DeveloperGuide.html)]

### Project management:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

### Community:
  * Reviewed PRs of peers
  * Reported bugs and suggestions for other teams
