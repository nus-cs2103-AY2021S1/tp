---
layout: page
title: Teo Jia Wei's Project Portfolio Page
---

## Project: Wanderlust

WanderLust is a desktop travel planner application. It allows users to plan their trips in a structured and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 18 kLOC.

## Summary of Contributions

- Code contributed: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jiaweiteo)

- **Major Enhancement**:
    - Implemented **sort feature** [#158](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/158), [#265](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/266), [#270](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/270)
        - What it does: Allow users to sort `activity/ accommodation/ friend` based on their desired view. (Etc. Sort activity by cost)
        - Justifications: As our target users are travel goers, they would likely want to view a sorted list of activities/ accommodation/ friend that
        is neat and easy to analyse. For example, a user may want to sort its activities by date so that he/she will know the list of events that will be happening in chronological order.
        This enhancement improved the product significantly as users will have a convenient way to view their desired order of items.

        - Highlights:
            - This enhancement transcends across all 4 components of Wanderlust: `Ui`, `Logic`, `Model` and `Storage`.
            - This enhancement is highly coupled with the edit/delete features as these two features make use of the index of a `travelplanobject`.
            - For example, when a user sort his activities, the activity list in the `Model` will be updated. In order to use the index reflected in the
            `Ui` on the new activity, the corresponding activity list in `Storage` must be updated and synced with the sorted ordered list reflected in the `Ui`.

    - Implemented **navigation feature** [\#108](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/108), [\#100](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/100)
        - What it does: Allows users to navigate to different `travelplan` or `wishlist` using the Command Line input.
        - Justifications: Users will like to navigate to their desired `travelplan` or `wishlist` so that they are able to view the content in the directory on the application and plan their travel visually.
        This enhancement improved the product significantly as it provides user a convenient and fast way to go to their desired `travelplan/wishlist`, improving users' experience.

- **Minor Enhancement**:
    - Implemented **Command Line Tab Management** [\#132](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/132), [\#265](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/265)
        - What it does: Allows users to switch tabs between `activity`, `accommodation` and `friend` within a `travelplan` using the Command Line input.

    - Implemented **feature for the Graphical User Interface**: [\#157](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/157), [\#158](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/158)
        - What it does: GUI shows the total cost of each `travelplan`.

    - Implemented **time logic** [\#242](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/242)
        - What it does: Prevent users from entering invalid dates.

* **Project management**:
  * Provide direction during weekly meetings.
  * Delegate tasks weekly to team members.
  * Schedule weekly meetings.

* **Enhancements to existing features**:
  * Modified the delete feature from AB3 to fit Wanderlust  ([\#84](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/84), [\#264](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/264))
  * Wrote additional tests for existing features to increase coverage from 66% to 70% (Pull requests [\#84](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/84), [\#99](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/99), [\#100](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/100), [\#266](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/266), [\#265](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/265))

* **Community**:
  * PRs reviewed with non-trivial review comments: [\#32](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/32), [\#59](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/59), [\#103](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/103), [#145](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/145), [#174](ttps://github.com/AY2021S1-CS2103-T14-3/tp/pull/174), [#278](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/278), [#279](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/279)
  * Reported bugs and suggestions for other teams in the class: [Reported 15 Bugs in PED](https://github.com/jiaweiteo/ped/issues)

## Contribution to User Guide:
  * Added documentation for the features `add`, `show` and `sort` ([\#172](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/172), [\#19](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/19))
  * Added documentation for Introduction, Table of Contents, Quickstart and Command Summary ([\#19](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/19))
  * Refine and finalise user guide. ([\#293](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/284))

## Contribution to Developer Guide:
   * Added implementation details of the `delete` feature. ([#82](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/82), [\#173](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/173))
   * Added implementation details of the `goto` feature. ([#83](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/83), [\#139](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/139))
   * Use Cases, user stories and manual testing. ([#284](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/284))
   * Refine and finalise developer guide. ([\#293](ttps://github.com/AY2021S1-CS2103-T14-3/tp/pull/293))

