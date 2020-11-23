---
layout: page
title: Zhang Yifan's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

### Summary of Contributions

#### Code contributed

Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=theyifan) to view the code that I have contributed.

#### Enhancements implemented
        
* **Create CRUD for `Hairdresser` Classes**: Designed and created `Add`, `Edit`, `Delete` and `List` commands for `Hairdresser` (Pull request [\#85](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/85), [\#101](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/101/))
   * What it does: The CRUD features allows the user to manage the `hairdresser` data. Also Encapsulates the logic that makes changes to the `Model` and `Storage` classes whenever hairdressers are manipulated in **HairStyleX**.
   * Justification: These commands are essential to ensuring that `hairdressers` are stored and saved correctly in **HairStyleX** whenever a user manipulates them.
   * Highlights:
       * CRUD for `hairdresser` was one of the first CRUD operations built for `HairStyleX`. This set a direction for adding features and tweaking `model`, `logic` and `storage` packages while morphing the original *AddressBook-3* into a multi-entity application, to ensure that the data will be handled in the correct and orderly manner. 
       * Improvements for better OOP are made along the way. For instance, `Specialisation` class was refactored to using `enum` ([\#160](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/160)), as the types of specialisations for hairdressers are rather fixed and abstracting to `enum` removes possibility of duplicates and increases accuracy for `hairdresser` data. 
       
* **Create `Print` command for exportation of data**: Designed and created `Print` command. (Pull request [\#202](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/202), [\#261](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/261))
    * What it does: The `Print` command allows the user to export the data of `hairdresser`, `client` and `appointment` into `.csv` files. 
    * Justification: The command gives the users an option to save data into an easy-to-read and easy-to-operate format.
    * Highlights:
        * `Print` command allows the all three lists of `hairdresser`, `client` and `appointment` to be exported into `.csv` format with user input of one single command, together with timestamp of time of creation written inside the files. 
        * `Print` command allows the concurrent exportation of the three lists using `executer` and multi-thread, thus reducing the overall runtime for potential large list exportation. 
    * Credit: the idea of `Print` is inspired by Warenager.
    
* **Integration and enhancement to `Id`** (Pull request [\#111](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/111), [\#278](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/278) (under [IdCounter.java](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/278/files#diff-ac1519e09f1f6c4cbc50784b7d08641f1cfdc57a59d183aa55268c794958c3ac)), [\#284](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/284))
    * What is does: Enforces the user to identify `Client`s, `Hairdresser`s and `Appointment`s by their unique `Id` instead of the default list index that is shown in *AddressBook-3*.
    * Justification: The `Id` classes are designed and created by [James](https://ay2021s1-cs2103t-t15-1.github.io/tp/team/iamjamestan.html). Integration of the `Id` classes into `hairdresser` and `client` and enhancement to `IdCounter` related classes are done later. 
    * Highlights:
        * Integrated the `PersonId` and `PersonIdCounter` classes into `hairdresser` and `client` classes and ensured that `Id`s are stored and saved correctly in **HairStyleX** whenever a user manipulates them. ([\#111](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/111))
        * `IdCounter#reset()` function was designed and implemented so that the stored `instance` for the singleton class `IdCounter` can be cleared and reset ([\#278](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/278) (under [IdCounter.java](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/278/files#diff-ac1519e09f1f6c4cbc50784b7d08641f1cfdc57a59d183aa55268c794958c3ac))). This is especially useful for testing purposes, where multiple `Model`s will be created and tested. 
        * Validation for `IdCounter` is enforced while constructing `HairStyleX`([\#284](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/284)). 
* **Testing**: Create tests for hairdresser related commands and classes that increased coverage by 19.85%. (Pull request [\#123](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/123), [\#278](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/278/))

#### Contributions to the User Guide

* Provided a detailed guide supplemented with screenshots and examples for [hairdresser management commands](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#43-hairdresser-management), including `add_hairdresser`, `list_hairdresser`, `edit_hairdresser`, `delete_hairdresser`, `filter_hairdresser`. (Pull request [\#190](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/190))

#### Contributions to the Developer Guide

* Added implementation details of the [Hairdresser](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#hairdresser-management-features) classes (Pull request [\#168](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/168)) and the [Print](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#print-feature) classes (Pull request [\#261](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/261))
* Recreated existing diagrams and updated implementation details for [Storage Component](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#storage-component) (Pull request [\#168](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/168))
* Added [value proposition](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#product-scope) (Pull request [\#65](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/65))

#### Contributions to the team-based tasks

* Set up github page and CI for the team repo
* Set up project boards used by the team in the early stages
* Created and uploaded releases for v1.2 and v1.4, and uploaded video for v1.2 demo
* Fixed miscellaneous bugs ([\#211](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/211)), updated UI ([\#317](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/317))

#### Review/mentoring contributions

* PRs reviewed (with non-trivial review comments): [\#255](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/255)
* Guided team members on the basic git workflow for team collaboration

#### Contributions beyond the project team

* Reported [10 bugs](https://github.com/theyifan/ped/issues) in group W11-4's tP during mock PE

