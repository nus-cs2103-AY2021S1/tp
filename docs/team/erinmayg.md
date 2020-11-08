---
layout: page
title: Project Portfolio Page
---

## Project: FaculType

FaculType is a desktop app for managing faculty members and their modules, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented Modules (including its attributes and UniqueModuleList)
  * What it is: A representation of modules that are available in the user's university/college.
  * Justification: This feature improves the product by allowing users to insert modules into the app.
  * Highlights: This feature is a foundation for module-related commands to be added in the future. It required an in
  -depth analysis and understanding of how the application works as a whole (from displaying the components to how it
   stores the data).
  
* **New Feature**: Added the ability to list all modules
  * What it does: A command to show all available modules in the active semester in the module list.
  * Justification: This feature improves the product by allowing users to only reset the view of the module list
   while still keeping the filtered contacts list.
  * Highlights: This feature requires an understanding of the model class.

* **New Feature**: Added the ability to detect duplicate prefixes
  * What it does: Detects whether there's a duplicate prefix provided by the user.
  * Justification: Restricts the user by forbidding users to enter multiple prefixes in commands which doesn't
   support multiple prefixes of the same type.
  * Highlights: This feature requires an understanding of how the `ArgumentMultimap` class works.

* **New Feature**: Added the ability to detect any prefix from the list of required prefixes
  * What it does: Checks whether there exists at least one prefix from the list of supported prefixes by a command.
  * Justification: This feature improves the app by allowing commands (such as `find` and `findmod`) to be more
   flexible by not requiring users to provide all supported prefixes.
  * Highlights: This feature requires an understanding of how the `ArgumentMultimap` class works.
   
* **New Feature**: Displays the module list and a view of switching semesters
  * What it does: Switches the tabs in the GUI to represent the switching of semesters.
  * Justification: This feature improves the product as it gives users a graphical view of all the modules in
   the active semester (Semester 1 or Semester 2).
  * Highlights: This feature requires an in-depth understanding of how JavaFX works (both in the design aspect and
   the functional coding aspect) and an analysis of all existing components and its CSS attributes.
  * Credits: Module TabPane color scheme [link](https://stackoverflow.com/questions/30642032/how-to-get-rid-of-the-grey-selection-border-in-javafx).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=erinmayg&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~other~test-code&tabOpen=true&tabAuthor=erinmayg&tabRepo=AY2021S1-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&tabType=authorship)

* **Enhancements to existing features**:
  * Enables listing of all contacts, modules, or both (Pull requests 
  [\#96](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/96), 
  [\#98](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/96))
  * Allow finding by attributes (Pull requests 
  [\#105](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/105), 
  [\#117](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/117), 
  [\#124](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/124),
  [\#133](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/133))
  * Updated the GUI to better suit the app (Pull requests 
  [\#86](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/86), 
  [\#145](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/145), 
  [\#149](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/149),
  [\#171](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/171),
  [\#172](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/172),
  [\#174](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/174),
  [\#183](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/183),
  [\#187](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/187),
  [\#220](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/220),
  [\#226](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/226),
  [\#245](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/245),
  [\#247](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/247),)
  * Wrote additional test utils for new features (Pull request 
   [\#61](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/61))
  * Wrote additional test cases for new and existing features (Pull requests 
   [\#61](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/61),
   [\#66](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/66),
   [\#96](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/96),
   [\#98](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/98),
   [\#105](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/105),
   [\#117](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/117),
   [\#124](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/124))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `find`, `mlist`, and `clist`: 
    [\#96](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/96), 
    [\#98](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/98), 
    [\#105](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/149)
  * Developer Guide:
    * Updated the class diagrams of Model, Storage, and UI:
    [\#130](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/130),
    [\#239](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/239)
    * Added implementation details of the `find` feature:
    [\#130](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/130),
    [\#229](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/229)
    * Added the use cases for all features:
    [\#31](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/31),
    [\#96](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/96),
    [\#240](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/240)

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#64](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/64),
  [\#93](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/93),
  [\#121](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/121),
  [\#122](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/122),
  [\#123](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/123),
  [\#135](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/135),
  [\#222](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/222)
  * Reported bugs and suggestions for other teams in the class (examples: 
  [1](https://github.com/erinmayg/ped/issues/1), 
  [2](https://github.com/erinmayg/ped/issues/3), 
  [3](https://github.com/erinmayg/ped/issues/3), 
  [4](https://github.com/erinmayg/ped/issues/7), 
  [5](https://github.com/erinmayg/ped/issues/8), 
  [6](https://github.com/erinmayg/ped/issues/8))
