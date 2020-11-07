---
layout: page
title: Ernest Lim's Project Portfolio Page
---

## Project: Supper Strikers

SupperStrikers is a food ordering application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to display and switch between vendors. (Pull requests [#78](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/78), [#86](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/86))

  * What it does: Allows the user to select a vendor to order from. When a vendor has been successfully selected, the GUI portion will display the menu of the selected vendor.
  * Justification: This feature is required to allow users to select a vendor so that they are able to view the menu corresponding to that vendor.
  * Highlights: This enhancement creates a new vendor mode for SupperStrikers, where the menu based commands will return an error message in vendor mode. Corresponding commands such as VendorCommand and SwitchVendorCommand are implemented to switch between vendor mode and menu mode.

* **New Feature**: Added the ability to display the menu of the selected vendor in menu mode. (Pull requests [#78](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/78), [#86](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/86))

  * What it does: Allows the user to view the menu of a selected vendor in the GUI.

  * Justification: This feature reduces clutter in the screen as the vendor list is not important to the user after the user has

    selected a vendor.

* **New Feature**: Added the ability for users to save and load their order items in their presets. (Pull request [#142](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/142))

  * What it does: Allows the user to load their presets from a json file. Users are able to create a preset for that vendor and give the preset a name. The user is able to load the preset using its name and they can modify the order items after the preset is loaded before submitting. 
  
  * Highlights: If the preset is saved with an already existing name, it will be overwritten. Implemented execute portion of save and load preset commands.

    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ernestlim8&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:

* Set up team repository for SupperStrikers

  * Managed release `v1.2`  by creating issues on GitHub

  * Adapted AddressBook to create MenuManager, OrderManager and its related classes ([#43](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/43))

  * Refactor Command classes (Pull Request [#124](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/124))

  * Refactor AddressBook to VendorManager (Pull Request [#214](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/214))

    

* **Enhancements to existing features**:

  * Updated the GUI to display the list of vendors in vendor mode and display the menu in menu mode (Pull requests [#43](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/43))
  * Updated the GUI to support images for the menu items (Pull requests [#196](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/196), [#205](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/205))
  * Wrote additional tests for existing features to increase test coverage (Pull requests [#52](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/52), [#119](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/119), [#124](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/124), [#191](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/191),[#218](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/218) )
  * Change Vendors in SupperStrikers to reflect actual vendors (Pull Request [#195](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/195))

* **Documentation**:

  * User Guide:
    * Added documentation for the features `VendorCommand`  (Pull Request [#135](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/135))

  * Developer Guide:

    * Added implementation details of the `VendorCommand` feature. (Pull Request [#92](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/92), [#95](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/95))
    * Added implementation details of the `SwitchVendorCommand` feature. (Pull Request [#116](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/116))
    * Added implementation details and use cases of the `LoadPresetCommand` and `SavePresetCommand`. (Pull Request [#226](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/226))
    * Improved use cases portion of some commands (Pull Request [#122](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/122))
    * Improved UI, Storage and Logic sections (Pull Request [#208](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/208))

* **Community**:

  * PRs reviewed (with non-trivial review comments [#13](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/13)): 
  * Reported bugs and suggestions for other teams in the class (examples: [6](https://github.com/ernestlim8/ped/issues/6), [10](https://github.com/ernestlim8/ped/issues/10), [15](https://github.com/ernestlim8/ped/issues/15))

