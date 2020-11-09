---
layout: page
title: Soon Xiang, Yan's Project Portfolio Page
---

## Project: GradPad

GradPad is an offline computer application meant to help Computer Science students from the
National University of Singapore (NUS) plan their modules with more ease. All module information is
displayed through our simple and organised Graphical User Interface (GUI) created with JavaFX.
GradPad is also optimised for users who prefer working on a Command Line Interface (CLI). It is written
in Java, and has about 13k LoC.


Given below are my contributions to the project.

* **New Feature**: Added `required` command to check left-over required modules from the Computer Science Curriculum.

  * What it does: Allows the user to check any undone required modules by cross-referring their current list of completed modules
  against the list of all the required modules that we store within GradPad. Modules are split into separate categories
  (Eg. Foundation, GE, Etc) for enhanced readability. The command also accounts for equivalent modules and preclusion (Eg. CS1010X & CS1101S). 
  
  * Justification: This feature enhances the product significantly as users no longer have to refer manually to the NUS
  website, just to see a list of modules that they have not completed. Also, they do not have to manually filter our their
  completed modules, just to check what are the left-over modules, saving them loads of time during their module planning.
  
  * Highlights: This enhancement is a vital selling point of GradPad and will affect our future features. As the command
  deals with a lot of data and functionality, it required an in-depth analysis of design alternatives and OOP. The implementation
  was also tedious as it required a lot of testing. Initially, we used the provided JsonGradPadStorage class to read JSON
  file paths. However, we realised that our JAR release was not able to load the JSON files properly in runtime, due to
  pathing issues. Hence, with help from fellow teammate Lau Siaw Sam and some further study, we managed to find another
  way to read JSON files during runtime with the help of a ClassLoader, allowing our app to run smoothly during our release.
  
  * Credits: [Read a file from resources folder](https://mkyong.com/java/java-read-a-file-from-resources-folder/)

* **New Feature**: Added `science` and `gem` commands to check all the available Science and General Education Modules
under the Computer Science Curriculum and in NUS respectively.
  
  * What it does: Allows the user to check all the undone and available Science and General Education modules
  in the curriculum, based on their current list of modules accomplished.
  
  * Justification: This feature enhances the product as users no longer have to refer to the School website just to view
  the available Science Modules. It is separated from the `required` command from making the required command too
  cluttered, thus improving overall user experience. Separating the two commands also makes it easier to update our storage
  as we can now update our database for science modules without touching the other databases. Furthermore, General Education
  modules are also split according to Semester Availability which saves users more time.
  
  * Highlights: This enhancement is vital to GradPad as it is part of our core `required` command feature. The command deals
  with a fair bit of data and testing, making the implementation slightly difficult. Faced the same issue as described above 
  regarding accessing JSON files in our JAR release.
  
  * Credits: [Read a file from resources folder](https://mkyong.com/java/java-read-a-file-from-resources-folder/)
 
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yan-soon&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Minutes Taker during meetings and consultations.
  * Assign weekly workload to team.
  * Provide feedback constantly, to teammate's code.
  [Comments made](https://nus-cs2103-ay2021s1.github.io/dashboards/contents/tp-comments.html)

* **Enhancements to existing features**:
  * Refactored `Command`, `Parser` and `Storage` Component of AB3 code base
  (Pull request [\#35](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/35))
  * Updated `help` command to display instructions directly instead of a link to the UG.
  (Pull request [\#70](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/70))
  * Added Assertions to Model Component.
  (Pull request [\#99](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/99))
  * Added tests to existing features to improve overall Test Coverage.
  (Pull requests [\#197](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/197),
  [\#204](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/204))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `required`, `science`, `gem`, `checkmc`, and `exit`.
    (Pull requests [\#16](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/16),
    [\#117](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/117),
    [\#134](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/134))
    * Used Markdown to write Tips and Notes to make UG more visually stunning.
    (Pull requests [\#134](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/134),
    [\#218](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/218))
    * Improved sentence structuring and language use in UG to make it more user-centric.
    (Pull requests [\#45](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/45),
    [\#70](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/70),
    [\#117](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/117),
    [\#134](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/134))
    
  * Developer Guide:
    * Added implementation details for the features `add`, `required`, `science` and `gem`
    and the Storage Component.
    (Pull requests [\#45](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/45),
    [\#180](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/180)
    [\#218](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/218))
    * Added Introduction and 'About This Guide' section to the DG.
    (Pull request [\#70](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/70))
    * Added Target User Profile, Value Proposition, User Stories and Use Cases to the DG.
    (Pull request [\#19](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/19))
    * Added Manual Testing for all features to the DG.
    (Pull request [\#180](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/180))
    * Refactored `Logic` component portion of DG.
    (Pull request [\#45](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/45))

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  [\#100](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/100),
  [\#111](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/111)
  * Reported bugs and suggestions for other teams in the class
  (examples: [\#1](https://github.com/yan-soon/ped/issues/1),
  [\#2](https://github.com/yan-soon/ped/issues/2),
  [\#3](https://github.com/yan-soon/ped/issues/3),
  [\#4](https://github.com/yan-soon/ped/issues/4),
  [\#5](https://github.com/yan-soon/ped/issues/5),
  [\#6](https://github.com/yan-soon/ped/issues/6),
  [\#7](https://github.com/yan-soon/ped/issues/7),
  [\#8](https://github.com/yan-soon/ped/issues/8),
  [\#9](https://github.com/yan-soon/ped/issues/9),
  [\#10](https://github.com/yan-soon/ped/issues/10))
