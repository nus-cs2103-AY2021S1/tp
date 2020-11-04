---
layout: page
title: Wang Mo's Project Portfolio Page
---
## Project: tCheck

tCheck is a desktop application that offers an integrated system to efficiently manage a bubble tea shop, of the 
(imaginary) brand T-sugar, by providing sales tracking, ingredient tracking and manpower management. It is optimized 
for CLI users to update and retrieve the information more efficiently.

Given below are my contributions to the project.

* **New Feature**: Added the ability to list the ingredients that require user to restock with the command `i-restock`.
  * What it does: Allows the user to see the list of ingredients that are in shortage and should be restocked. The feature also allows the user to see the amount of the ingredient required to reach the minimum stock level.
  * Justification: This feature enhances the user experience because without this command, a user would need to check through the list of ingredients to find the ingredients that are in shortage. 
  Since the user needs to check which ingredients should be restocked frequently, this feature saves the user's time and effort in checking which ingredient is in shortage and the amount required to restock.
  * Highlights: The logic of this feature allows ingredients' minimum restock levels to be checked based on whether they are solid or liquid, which makes it more easily extensible in the future when the types of ingredients increase.

* **New Feature**: Added the ability to find ingredients by keywords with the command `i-find`.
    * What it does: Allows the user to find ingredients which have names containing the keyword(s).
    * Justification: This feature allows the user to search for some particular ingredients quickly by entering keywords. Since our target users are fast typists, it would improve their experience
    using the application as they would not need to scroll down the ingredient list and check line by line to find the ingredients.
    * Highlights: This feature can accommodate the increase in the types of ingredients in the future.   
    
* **New Feature**: Added a command that allows the user to reset all ingredients' levels to zero.

* **New Feature**: Added a command that allows the user to view the list of ingredients.

* **Code contributed**:[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wang%20mo&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=WM71811&tabRepo=AY2021S1-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code)

* **Project management**:
  * Managed the release `v1.2.1` on Github
  * Created the label type.Story

* **Enhancements to existing features**:
  * Edited the help command so that it directs the user to the application's user guide. (Pull requests: [\#234](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/234))
  
* **Documentation**:
  * AboutUs page:
    * Updated my information in the AboutUs page of the project website.(Pull requests: [\#37](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/37), [\#39](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/39))
  * User Guide:
    * Added documentation for the features `i-list`, `i-find`, `i-restock` and `i-reset-all`
     with regard to ingredients tracking: (Pull requests [\#116](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/116),
     [\#132](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/132))
    * Did cosmetic tweaks to existing documentation of the feature `help`: (Pull request [\#61](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/61))
  * Developer Guide:
    * Added implementation details for the features `i-list`, `i-find` and `i-reset-all`. (Pull requests [\#94](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/94), [\#104](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/104))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#]()) [fill in later]
  * Contributed to forum discussions (examples: [360](https://github.com/nus-cs2103-AY2021S1/forum/issues/360))
