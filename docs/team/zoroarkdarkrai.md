---
layout: page
title: Fidella Widjojo's Project Portfolio
---

## Project: InternHunter

InternHunter is a CLI-centric desktop application which aids university students in applying for tech internships.
It lets users manage their own customisable collection of companies, internships, internship applications and their 
own user profile, so that they can keep track of internships that they are interested in.

It has a GUI created with JavaFX. It is written in Java, and has about 35k LoC.

### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#search=ZoroarkDarkrai&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Features added**:
  * Added storage to InternHunter (Pull request [\#141](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/141), [\#158](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/158))
    * What it does: Allows InternHunter to read and save its data to JSON files.
    * Highlights:
      * Created the `JsonAdaptedItem` abstract class which represent the Jackson-friendly version of an `Item`. 
      * Created generic `JsonItemListStorage` and `JsonSerializableItemList` classes that work for all classes extending `JsonAdaptedItem`.
      * Updated the `JsonUtil#readJsonFile()` method to allow Jackson deserializing to a generic class.
      * Lets InternHunter reads and saves `ApplicationItemList`, `CompanyItemList`, and `ProfileItemList`.
      * Ensures the consistency between internships in application and company lists.
  * Created generic `FilterableItemList` and `ItemListManager` classes. (Pull request [\#108](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/108))
    * What it does: Lets future addition of `Item` subtypes to the model easy and reduce current code duplication.
    * Highlights: `FilterableItemList` and `ItemListManager` work for all classes extending the `Item` class. A list of 
    `Item` objects have many common methods that can lead to much code duplication. Using generic classes ensures InternHunter
    adheres to the DRY principle.
  * Added exit dialog  (Pull request [\#96](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/96), [\#101](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/101))
    * What it does: Prevents users from accidentally quitting InternHunter.
    * Highlights: A confirmation dialog pops up when users click on the "x" button or enter the `exit` command.
  * Added clear command (Pull request [\#221](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/221))
    * What it does: Lets users reset InternHunter data.
    * Highlights: Users can enter `clear` command to delete all of their entries from InternHunter.
  * Added sample data (Pull request [\#96](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/96), [\#101](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/101))
      * What it does: Provides users with sample data to try InternHunter.
      * Highlights: When users launch InternHunter for the first time, sample data fill the app, so users can immediately see how InternHunter works.

* **Documentation**:
  * User Guide:
    * Added command summary. (Pull requests [\#80](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/80), 
        [\#122](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/122), [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258))
    * Added clear command documentation. (Pull requests [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258), 
    [\#346](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/346))
    * Added switch, help, and exit command documentation. (Pull requests [\#80](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/80))
    * Added questions about sample data to the FAQ. (Pull requests [\#365](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/365))
    * Updated the internship commands documentation.  (Pull requests [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258),
    [\#346](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/346))
  * Developer Guide:
    * Added storage architecture diagram. (Pull requests [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258))
    * Added implementation details and UML diagrams for the `Storage` component. (Pull requests  [\#218](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/218), [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258))
    * Added implementation details and UML diagrams for the 'clear' feature. (Pull requests [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258))
    * Added company use cases (Pull requests [#91](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/91))
    * Added `clear` user story and use case (Pull requests [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258))

* **Team-based tasks**:
  * Maintaining the issue tracker
  * Created the team organization and repository on GitHub.
  * Created labels for issues.

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
   [\#217](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/217), [\#92](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/92),
   [\#97](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/97), [\#140](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/140),
   [\#214](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/214), [\#132](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/132),
   [\#285](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/285), [\#288](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/288),
   [\#156](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/156), [\#78](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/78),
   [\#276](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/276), [\#125](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/125)
  * All other PRs reviewed: [\#62](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/62), 
  [\#367](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/367), [\#366](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/366),
  [\#364](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/364), [\#351](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/351),
  [\#269](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/269), [\#126](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/126),
  [\#206](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/206), [\#347](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/347),
  [\#207](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/207), [\#152](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/152),
  [\#201](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/201), [\#220](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/220),
  [\#82](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/82), 
  
  * Reported bugs and suggestions for other teams in the class. Bug reports:
  [1](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/113), [2](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/114), 
  [3](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/115), [4](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/116), 
  [5](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/117), [6](https://github.com/AY2021S1-CS2103T-T17-2/tp/issues/118), 

