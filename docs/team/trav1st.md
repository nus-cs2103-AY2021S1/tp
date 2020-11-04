---
layout: page
title: Travis Toh's Project Portfolio Page
---

## Project: ChopChop

ChopChop is a food recipe and ingredient inventory management system, which aims to make it easier for people to manage their recipes and ingredients in an easy and effective manner. It features a keyboard-driven command interface and a GUI written in JavaFX.

A summary of code contributions can be found here: [reposense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=trav1st).

### New Features

#### Statistics

This feature saves a persistent record of the recipes made and the ingredients consumed in the making of the recipes in the storage. The user can access these records with the commands described below. Relevant information are displayed in a Statistic panel on the bottom right hand side of ChopChop so that the user can execute other commands without the Statistic's results being overwritten by the output of other commands.

PR: [#130](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/130).

#### Statistics commands

##### View top recipes command

Shows the recipes that were made the most number of times.

##### View recent recipes/ingredients commands

Shows the 10 most recently made recipes / recently used ingredients. It is capped at 10 to keep the list short and concise. If the user would like to view records that are even older (i.e. View the previous items that are not in this list of 10), the user can use view recipes made/ view ingredients used commands and specify the exact time frame.

##### View recipes/ingredients that were made / used commands

Shows the recipes made and ingredients used in the given time frame. If the user does not specify any time frame (i.e. `/before` and `after` tags are missing in user's input), we assumed that the user wants to view recipes made/ ingredients used on the day itself.

##### Clear recipes/ingredient usage records commands

Sometimes, the usage history can get too long and cluttered or the user simply wants their history to be cleared for privacy reasons. Clear commands delete all saved records.It is an undoable command, so if the user deletes by accident the deleted data can be recovered by executing the `undo` command.


### Other Contributions

#### Enhancements to Existing Features

1. Improve the functionality of CommandResult to account for outputs from Statistics commands [#201](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/201).

2. Improve the output messages ([#165](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/165)).



#### Documentation
**User Guide**

Author for the Statistic commands section of User Guide. Added detailed examples for the [StatisticsCommands](https://github.com/AY2021S1-CS2103T-T10-3/tp/blob/master/docs/UserGuide.md#56statistics-commands-travis).

**Developer Guide**

Added implementation details of the Statistics model (UsageList). [UsageList](https://github.com/AY2021S1-CS2103T-T10-3/tp/blob/master/docs/DeveloperGuide.md#45usagelist-model).


#### Community

Contributed detailed bug reports for other teams during PE-D: [ped repo](https://github.com/trav1st/ped/issues)
