---
layout: page
title: Zhang Yi's Project Portfolio Page
---

## Project: tCheck
tCheck is a desktop application that offers an integrated system to efficiently manage a bubble tea shop, of the (imaginary) brand T-sugar, by providing sales tracking, ingredient tracking and manpower management. It is optimized for CLI users to update and retrieve the information more efficiently.
  
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link]()

* **New Features**:

  * Built ingredient model and ingredient book model for ingredient tracking purposes.
    * What it does : Allow us to model any ingredient and build an ingredient book with the model built.
    * Justification : This is a must-have feature for the project because all ingredient-related commands will depend on the ingredient model and ingredient book model.
  
  * Added `i-set i/INGREDIENT_NAME m/AMOUNT` command.
    * What it does : Allow a user (a stall manager) to set one single ingredient's level to the specified amount in his/her T-Sugar stall.
    * Justification : This is a must-have feature for the application because it is necessary to offer users a command which they can use to adjust the ingredients' levels so that the ingredient tracking purpose can be achieved.
  
  * Added `i-set-default` command.
    * What it does : Allow a user to set all ingredients' levels to a pre-determined default level for his/her T-Sugar stall.
    * Justification : This is a nice-to-have feature for the application because it acts as a shortcut for stall managers to start using tCheck. Instead of manually adjusting each of the ingredient's level, all levels can be set to default using a short command.
    
  * Added `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_BROWN_SUGAR` command.
    * What it does : Allow a to set all ingredients' levels to different specified amounts for all available ingredients in a T-sugar stall.
    * Justification : This is also a nice-to-have feature for the application. It enables expert users to conveniently set all available ingredients' levels in one single command.
    
  * Implemented Ingredient Tracker Graphic User Interface (GUI).
    * What it does : Allow users to view all available ingredients' levels via a dedicated GUI part for ingredient tracking purposes.
    * Justification : This is a must-have feature for the application because if a user cannot view directly the ingredients' levels through the GUI, the usability of the application will suffer.
  
* **Project management**:

  * Added issues assigned to milestone v1.1, v1.2, 1.3 and v1.4.
  * Managed v1.4 release on GitHub.
  * Actively reviewed teammates' pull requests and assisted merging.
  
* **Quality assurance**:

  * Took charge of overall testing of functionalities of tCheck.
  * Monitored test cases written for all teammates and reminded teammates to add tests when the test coverage is not satisfactory.

* **Documentation**:

  * AboutUs page:
    * Updated my information in the AboutUs page of the project website.
 
  * User Guide:
    * Added documentation for the features `i-set i/INGREDIENT_NAME m/AMOUNT` , `i-set-default` and `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_SUGAR`.
    * Authored the first half of About Us section.
    * Added notes and explanations for the designing of commands under the ingredient tracking section.
    
  * Developer Guide:
    * Added documentation for the section of use cases.
    * Added documentation and diagrams for commands to set ingredients' levels.
    * Added a diagram for ingredient model.
    * Authored audience part in the introduction section.

* **Community involvement**:

  * Contributed to forum discussion on GitHub.
  * Offered non-trivial suggestions to pull requests reviewed.

* _{you can add/remove categories in the list above}_
