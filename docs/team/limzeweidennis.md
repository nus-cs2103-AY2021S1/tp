---
layout: page
title: Dennis's Project Portfolio Page
---

## Project: Warenager

Warenager is an **inventory application** to help tech-savvy warehouse managers to keep track of items in their warehouse.
It **optimizes management tasks** for warehouse managers including but not exhaustive of updating,
searching and ordering supplies, via Command Line Interface (CLI).

Given below are my contributions to the project.
* Code Contribution
  * Here is a link to [my code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=dennis&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=AudreyFelicio&tabRepo=AY2021S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) 
  on the tP Code DashBoard.

* Enhancement Implemented
    * **New Feature**: Added the ability to list existing items in the storage. (Pull Request 
    [#116](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/116))
      * What it does: Allows the user to view the desired stocks.
      * Justification: This feature improves the user experience as user can easily view the details of the desired
      stocks. Users can now view the desired stocks quickly and conveniently,
      by using any of the fields (all, bookmark, low) to find stocks that are bookmarked or low in quantity or all stocks
      respectively. 
      * Highlights: This feature enables the user to view all stocks, or bookmarked stocks or stocks with low quantity.
      
    * **New Feature**: Added the ability to bookmark existing items in the storage. (Pull Request 
    [#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150),
    [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155))
      * What it does: Allows the user to bookmark the desired stocks.
      * Justification: This feature improves the user experience as user can bookmark the stocks so as to push the stock
      to the toip of stock for easier viewing.
      * Highlights: This feature coupled with the list feature allows the user to view all the bookmarked stocks.
      
    * **New Feature**: Added the ability to unbookmark bookmarked items in the storage. (Pull Request 
    [#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150),
    [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155))
      * What it does: Allows the user to unbookmark bookmarked stocks.
      * Justification: This feature improves the user experience as user can remove the priority from the bookmarked stocks
      once the user deem the importance of the stock is reduced. This way the other stocks not bookmarked 
      can be seen as well.
      * Highlights: This feature enables the user to unbookmark the stock if the user makes a mistake or when the user
      deem that the stock is of less importance.  
      
    * **New Feature**: Added the ability for the user to find help. (Pull Request 
    [#99](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/99), 
    [#106](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/106), 
    [#132](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/132))
      * What it does: Allows the user to get help.
      * Justification: This feature improves the user experience as the user is able to use this feature to find out more
      about how the commands in Warenager can be used, should the user be unsure of how to use the commands. The help 
      window also allows the user to view the user guide for more detailed help.
      * Highlights: This feature enables the user to seek help when using Warenager.  

* **Testing**:
  * Created unit tests for `bookmark` feature (bookmarkCommandParser, bookmarkCommand). (Pull Request 
  [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155),
  [#291](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/291))
  * Created integration tests for `bookmark` feature (bookmarkCommand). (Pull Request 
  [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155))
  * Created unit tests for `unbookmark` feature (unbookmark command parser, unbookmark command). (Pull Request 
  [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155),
  [#291](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/291))
  * Created integration tests for `unbookmark` feature (unbookmark command). (Pull Request 
  [#155](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/155))
  * Created unit tests for `help` feature (helpCommandParser).
  * Improved coverage by creating tests for `LogicManager`.
  * Reviewed other team members' tests and ensure the tests are adequate and working.
  * Help other team members debug testing when the tests failed and they don't know what causes the tests to fail.

* **Documentation**:
  * User Guide: (Pull Request 
  [#176](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/176),
  [#179](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/179),
  [#210](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/210),
  [#263](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/263))
    * Added documentation for the feature `bookmark`.
    * Added documentation for the feature `unbookmark`.
    * Added documentation for the feature `list`.
    * Added documentation for the feature `help`.
    * Added components of GUI into the UG.
    * Fix markdown formatting errors.

  * Developer Guide: (Pull Request [#281](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/281))
    * Added implementation details of the `bookmark` feature.
    * Added implementation details of the `unbookmark` feature.
    * Added implementation details of the `list` feature.
    * Added implementation details of the `help` feature.
    * Added class diagrams for `UI`.
    * Added UML diagrams for `bookmark`, `unbookmark` feature.

* **UI**:
  * Came up with the design of the UI (stockTable, resultDisplay, commandBox and enterButton). (Pull Request 
  [#114](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/114)
  [#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150))
  * Edited the colour scheme using recommendations from groupmates. (Pull Request 
  [#114](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/114))
  * Created the table in help window. (Pull Request 
  [#207](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/207))
  * Updated the help window to include a clickable link. (Pull Request
  [#114](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/114))
  
* **Git & Repository**:
    * Used GitHub Projects feature to create kanban boards to track user stories.
    * Used Github commit to inform my groupmates of updated that I did.

* **Reviews & Merging**:
    * Helped to review pull requests.
    * Helped to merge approved pull requests after thorough review and GitHub action checks.
