---
layout: page
title: Timothy Ong Jing Kai's Project Portfolio Page
---

## Project: Wanderlust

WanderLust is a desktop travel planner application. It allows users to plan their trips in a structured and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 16 kLOC.

## Summary of Contributions

- Code contributed: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=timjkong)

- **Major Enhancements**:

    - Implemented **copy and move features**
        - What it does: The copy feature allows users to copy an `activity` from the wish list to a travel plan. The move feature essentially does the same thing but removes the `activity` from the
        wish list after copying it to the travel plan.
        - Justifications: This enhancement improved the product significantly as users will be able to easily copy or move activities that they have planned for their trips
        from the wish list to a travel plan instead of adding the activity to the travel plan, which would be much more cumbersome.
        - Highlights:
            - The copy and move commands differ from other commands in that they are the only commands that modify the list of activities of a travel plan not in the current directory.
        - Relevant pull requests: [\#135](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/135)

    - Modified **add feature** from AB3 to fit Wanderlust
        - What it does: Allows user to add a `travelplan` or a `travelplanobject` in the application.
        - Highlights:
            - This feature relies on abstraction. The `AddTravelPlanCommand`, `AddActivityCommand`, `AddAccommodationCommand` and `AddFriendCommand` classes extend the `AddCommand` class which extends the `Command` class.
        - Relevant pull requests: [\#89](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/89)

    - Implemented the `Accommodation` class
        - Relevant pull requests: [\#58](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/58)

- **Minor Enhancements**:
    - Formatted and organised exception messages for all commands
        - What it does: When a user enters an incorrect format for the command, the application responds with an exception message, providing the user with the correct format for the command and/or an example.
        - Justification: Wanderlust contains quite a few different commands and it is possible that a user may forget the correct format for certain commands. Providing them with the correct format and an example will allow them to quickly correct their invalid inputs.
        - Relevant pull requests: [\#248](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/248), [\#295](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/295)

* **Project management**:
  * Provide suggestions during weekly meetings.
  * Assist group members with tasks.

* **Enhancements to existing features**:
  * Updated the add feature. ([\#89](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/89))
  * Wrote tests for the `add` feature with 100% coverage for all related command classes. ([\#111](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/111))
  * Wrote tests for the `copy` and `move` features with 93.33% and 90.63% coverage respectively. ([\#289](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/289))
  * Wrote tests for the `Accommodation` and `UniqueAccommodationList` classes with 84% and 80.77% coverage respectively. ([\#111](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/111))

* **Documentation**:
  * User Guide:
    * Added documentation for the `add`,`delete`,`edit`,`copy` and `move` features ([\#17](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/17), [\#107](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/107), [\#188](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/188/files))
  * Developer Guide:
    * Added non-functional requirements. ([#46](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/46))
    * Added implementation details of the `add` feature. ([#142](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/142))

* **Community**:
  * Total PRs reviewed: 4
  * PRs reviewed with non-trivial review comments: ([\#59](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/59))
  * Reported bugs and suggestions for other teams in the class: [Reported 6 Bugs in PED](https://github.com/timjkong/ped/issues)
