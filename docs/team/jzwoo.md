---
layout: page
title: Project Portfolio Page
---

## Project: FaculType

FaculType is a desktop app for managing faculty members and their modules, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java.  

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete existing modules.
  * What it does: Allows the user to delete existing modules in the module list.
  * Justification: This feature improves the product because it allows the user to remove modules as part of the module management capability of FaculType.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it required the use of module codes instead of the index number for deletion.

* **New Feature**: Added a search function for modules. 
  * What it does: Allows the user to search for modules by different parameters.
  * Justification: This feature improves the product significantly because it allows for more convenient management when there are a lot of modules involved.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging due to the different parameters involved. There was a need to make the search function focused but also flexible enough such that a more general search could be performed.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zFR=false&zA=jzwoo&zR=AY2021S1-CS2103-T14-1%2Ftp%5Bmaster%5D&zACS=261.0693710993759&zS=2020-08-14&zFS=&zU=2020-11-08&zMG=false&zFTF=commit&zFGS=groupByRepos)

* **Enhancements to existing features**:
  * Added icons for contact attributes (Pull requests [\#150]())
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#69](), [\#89](), [\#242]())
  * Added a model stub class for testing (Pull requests [\#146]())
  * Added checks for duplicate prefixes of multiple commands (Pull requests [\#222]()) 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delmod` and `findmod` [\#27](), [\#228]() 
  * Developer Guide:
    * Added implementation details of the `delmod` and `findmod` feature. [\#136](), [\#228]()  
    * Added and modified glossary [\#24](), [\#243]()   

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#105]()

* _{you can add/remove categories in the list above}_