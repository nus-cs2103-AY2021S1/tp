---
layout: page
title: Yao Yuan's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers from budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that routine tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

### Summary of Contributions

#### Code contributed

Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ya0-yuan) to view the code that I have contributed.

#### Enhancements implemented

* **Create `Filter` method for `Client` and `Hairdresser` classes**: (Pull request
[\#176](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/176),
[\#287](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/287))
   * What it does: Allows the user to filter the client/hairdresser list with the client/hairdresser’s `name`. Also encapsulates the logic that make changes to the `Model` class whenever client/hairdresser list is filtered.
   * Justification: These commands are essential to the product as the client/hairdresser list can be extremely long especially when the database is large, and the app should provide a convenient way for the user to quickly find one or few client(s)/hairdresser(s).
   * Highlights: Careful consideration of good OOP design was made when creating filter commands for these two classes. `FilterEntityCommand` class is created and `FilterClientCommand` and `FilterHairdresserCommand` classes inherit from it.

* **Create `Filter` method for `Appointment` class**: (Pull request
[\#176](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/176),
[\#287](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/287))
   * What it does: Allows the user to filter the appointment list with `hairdresser’s id`, `client’s id`, `appointment’s date`, `appointment’s status` or a combination of any of them. Also encapsulates the logic that makes changes to the `Model` class whenever appointment list is filtered.
   * Justification: These commands are essential to the product as it contributes to one of the most important features – appointment management system. By allowing the user to filter appointments by a specific hairdresser/client, a specific date or appointment’s status, it enables the user to track all appointments easily and avoid appointment conflicts effectively.
   * Highlights: 
       * Filter command for appointments takes in one or more predicates which allows multi-purpose filtering within just one command line. This is done by appending all predicates to a list and checking them sequentially.
       * Meanwhile, it ensures the number of user input for the same prefix is no more than one which minimises the possibility of unwanted list being returned when the user accidentally inputs predicates of different values for the same prefix .

* **Ui enhancement**: Convert the mock-ui into code and link it to be functional code. (Pull request
[\#95](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/95),
[\#103](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/103),
[\#109](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/109),
[\#121](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/121),
[\#183](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/183),
[\#250](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/250),
[\#264](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/264))
   * Highlights:
       * There were much consideration into designing and implementing the Ui to display the 3 types of data we store. Careful use of generics are required even at the Ui side. As there were quite a number of commands, the Ui have to be carefully implemented to avoid any Ui bugs.
       * It has a design that is customised to `HairStyleX`, with a special icon for each entity type, and for our app, thus make the app more pleasant for users.

* **Testing**: Create tests for all three filter commands, and their corresponding parser classes. (Pull request
[\#204](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/204))

#### Contributions to the User Guide

* Provided a detailed guide supplemented with screenshots and examples for [client management commands](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#42-client-management), including `add_client`, `list_client`, `edit_client`, `delete_client`, `filter_client`. (Pull request
[\#194](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/194))

#### Contributions to the Developer Guide

* Added [user stories](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#user-stories) (Pull request
[\#59](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/59))
* Added implementation details of the [Filter Feature](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#filter-feature) classes. (Pull request
[\#172](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/172))
* Recreated existing diagrams and updated implementation details for [Ui Component](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#ui-component) (Pull request
[\#172](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/172))
* Added instructions for manual testing for client section (Pull request
[\#311](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/311))

#### Contributions to the team-based tasks

* Set up project board: [checklist for v1.4](https://github.com/AY2021S1-CS2103T-T15-1/tp/projects/6) and managed remaining tasks to be completed
* Checked for all commands and UserGuide for possible bugs
* Took down meeting minutes for group meetings
* Created customised icon for our product


#### Review/mentoring contributions
* Pull requests reviewed (with non-trivial review comments): 
[\#265](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/265),
[\#282](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/282),
[\#307](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/307)

* Contributed to documentation for other teammates and fixed typos:
    [\#57](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/57), 
    [\#287](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/287)

#### Contributions beyond the project team
* [Reported 14 bugs](https://github.com/ya0-yuan/ped/issues) in group W13-1's tP during mock PE

