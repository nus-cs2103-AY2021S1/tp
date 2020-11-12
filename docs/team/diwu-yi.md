---
layout: page
title: Zhang Yi's Project Portfolio Page
---
## Project: tCheck
tCheck is a desktop application that offers an integrated system to efficiently manage a bubble tea shop, of the (imaginary) brand T-sugar, by providing sales tracking, ingredient tracking and manpower management. It is optimized for CLI users to update and retrieve the information more efficiently.
  
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=diwu-yi&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **New Features**:
  * Built ingredient model and ingredient book model for ingredient tracking purposes.
    * What it does : Allow us to model any ingredient and build an ingredient book with the model built.
    * Justification : This is a must-have feature for the project because all ingredient-related commands will depend on the ingredient model and ingredient book model.
    * Related PR(s) (non-exhaustive):
    [#62](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/62)
    [#72](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/72)
    [#127](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/127)
  * Added `i-set i/INGREDIENT_NAME m/AMOUNT` command.
    * What it does : Allow a user (a store manager) to set one single ingredient's level to the specified amount in his/her T-Sugar stall.
    * Justification : This is a must-have feature for the application because it is necessary to offer users a command which they can use to adjust the ingredients' levels so that the ingredient tracking purpose can be achieved.
    * Related PR(s) (non-exhaustive):
    [#105](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/105)
    [#161](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/161)
  * Added `i-set-default` command.
    * What it does : Allow a user to set all ingredients' levels to a pre-determined default level for his/her T-Sugar stall.
    * Justification : This is a nice-to-have feature for the application because it acts as a shortcut for stall managers to start using tCheck. Instead of manually adjusting each of the ingredient's level, all levels can be set to default using a short command.
    * Related PR(s) (non-exhaustive):
    [#78](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/78)
<div style="page-break-after: always;"></div>

* **New Features(more)**:
  * Added `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_BROWN_SUGAR` command.
    * What it does : Allow a to set all ingredients' levels to different specified amounts for all available ingredients in a T-sugar stall.
    * Justification : This is also a nice-to-have feature for the application. It enables expert users to conveniently set all available ingredients' levels in one line.
    * Related PR(s) (non-exhaustive):
    [#82](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/82)
  * Implemented Ingredient Tracker Graphic User Interface (GUI) and integrate the Ingredient Tracker to the _Main Window_.
    * What it does : Allow users to view all available ingredients' levels via a dedicated GUI part for ingredient tracking purposes.
    * Justification : This is a must-have feature for the application because if a user cannot view directly the ingredients' levels through the GUI, the usability of the application will suffer.
    * Related PR(s) (non-exhaustive):
    [#146](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/146)
    [#152](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/152)
* **Project management**:
  * Added issues assigned to milestone v1.1, v1.2, 1.3 and v1.4 and managed v1.4 release.
* **Quality assurance**:
  * Took charge of overall testing of functionalities of tCheck.
  * Monitored test cases written for all teammates and reminded teammates to add tests.
* **Documentation**:
  * AboutUs page:
    * Updated my information in the AboutUs page of the project website.
  * User Guide:
    * Added documentation for the features `i-set i/INGREDIENT_NAME m/AMOUNT` , `i-set-default` and `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_SUGAR`.
    * Authored three sections of About Us section (sub-section 1, 2.1 and 2.4).
    * Added notes and explanations for the designing of commands under the ingredient tracking section.
    * Added visuals such as screenshots for setting ingredients' levels feature and tCheckInfographic for better presenting of User Guide.
    [#254](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/254)
    [#118](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/118)
  * Developer Guide:
    * Added documentation and diagrams for commands to set ingredients' levels, use cases and manual testing instructions.
    * Added a diagram for the ingredient model.
    * Authored audience part in the introduction section and Glossary with others.
    [#91](https://github.com/AY2021S1-CS2103T-T12-2/tp/pull/91)
