---
layout: page
title: Gan Wan Cheng Isaac's Project Portfolio Page
---

## Project: InternHunter

InternHunter is a CLI-centric desktop application which aids university students in applying for tech internships.
It lets users manage their own customisable collection of companies, internships, internship applications and their 
own user profile, so that they can keep track of internships that they are interested in. It has a GUI created with
 JavaFX. It is written in Java, and has about 35k LoC.

## Summary of contributions

  * Parser and Command Structure Design (Pull request [\#118](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/118))
    * What it is: Allows one command word to be used for multiple entity types.
    * Highlights:  the parser is designed such that a single command word can be used to specify the operation for
     multiple entity types. Instead of the need to implement a long switch statement for all the command words (where more than
     20 commands words will need to be specified for every operation of each entity type). Similar commands
     classes for different entities all extend the same abstract class so that the the operation's CommandParser can
     leverage on polymorphism to return the command for execution. This is a useful design as it can be
     applied to all the other kinds of entities which share common operations.
  * Add internship to a company (Pull request [\#151](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/151/))
    * What it is: Allows the user to add an internship to a company's internship list.
    * Highlights: The add internship command is a challenging feature to implement as it
      needs to account for the composition relationship between the company and internship objects and how they interact. 
      The addInternshipCommand has retrieve the company targeted, check for duplicates in a company's internship list
      and then parse its fields carefully before it can be added. Careful consideration of how the addition of
      internship would affect subsequent features such as application objects applying to it is also needed.
      <div style="page-break-after: always;"></div>
  * Add Profile Items (Pull request [\#142](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/142))
    * What it is: Allows the user to add a profile item to the profile list 
    * Highlights: This enhancement affects other commands like the match command and commands to be added in future. It
     required an analysis of other design alternatives. It allows users to keep track of profile item which stores
      an title, category which is an enum type and a set of descriptors complying with a fixed set of characters.
  * Delete a Profile Item (Pull request [\#150](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/150))
  * Edit a Profile Item (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/154))
  * View a Profile Item (Pull request [\#155](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/155))
  * Find Profile Items (Pull request [\#253](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/253))
  * List all Profiles Items (Pull request [\#253](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/253))
  * Contributed significantly to improving the test coverage (Pull requests 
        [\#352](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/352), 
        [\#253](https://github.com/AY2021S1-CS2103-T15-4/tp/pull/253),
        [\#204](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/204), 
        [\#203](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/203),
        [\#168](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/168),
        [\#400](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/400))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=orzymandias&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added documentation for profile features - add, delete, edit, view, list, find.
    (Pull requests [\#82](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/82), 
    [\#260](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/260), [\#271](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/271))
    * Update overview portion and contents page.
  * Developer Guide:
    * Added implementation details and UML diagrams for the 'Edit a profile item' feature. (Pull request [\#216](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/216))
    * Added  details and UML diagrams for the 'Architecture'. (Pull request [\#288](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/288))
    * Added use cases for all profile items, find, list, help and exit commands. (Pull requests [\#92](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/92
    ), [\#124](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/124), [\#256](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/256))
    * Added Non Functional Requirements (Pull requests [\#92](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/92))
    * Added Effort Section (Pull request [\#363](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/363))
* **Team-based tasks**:
  * Maintaining the issue tracker and milestone management.
  * Integration code for common functions.
  * Documenting team effort.
* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#87](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/87),
  [\#98](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/98), 
  [\#104](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/104),
  [\#141](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/141), 
  [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258), 
  [\#140](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/140)
  * All other PRs reviewed: 
  [\#79](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/79),
  [\#80](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/80),
  [\#86](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/86),
  [\#95](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/95),
  [\#100](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/100),
  [\#122](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/122),
  [\#146](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/146),
  [\#164](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/164),
  [\#167](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/167),
  [\#179](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/179),
  [\#190](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/190),
  [\#197](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/197),
  [\#205](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/205),
  [\#217](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/217),
  [\#218](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/218),
  [\#285](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/285),
  [\#291](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/291)
  * Reported bugs and suggestions for other teams in the class. Bug reports:
  [1](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/199), 
  [2](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/198), 
  [3](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/197), 
  [4](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/196), 
  [5](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/195), 
  [6](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/194), 
  [7](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/193), 
  [8](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/192), 
  [9](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/191), 
  [10](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/190), 
  [11](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/189), 
  [12](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/188), 
  [13](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/187), 
  [14](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/186), 
  [15](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/185)
