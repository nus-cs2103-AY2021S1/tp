---
layout: page
title: Jeanne Toh's Project Portfolio Page
---
## Project: Wanderlust

WanderLust is a desktop travel-planning application that allows users to plan their trips in a structured and efficient manner.
It is optimised for CLI-proficient users, and comes with a GUI created with JavaFX.
It is written in Java, and has approximately 16 kLOC. 

## Summary of Contributions

Code contributed: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jeannetoh99)

**Major Enhancements**:
- Implemented **UI** ([\#81](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/81), [\#113](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/113),
    [\#193](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/193), [\#286](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/286))
    - **What it does**: The UI is the main interface that interacts with the user. It listens to user interaction,
          executes user commands using the `Logic` component and observes changes to data in `Model`, updating
          accordingly.
    - **Highlights**:
        - This enhancement requires in-depth knowledge of the entire architecture, especially the `Logic` and `Model` components.
        - Multiple rounds of experimentation and code refactoring to find the ideal way to display the 3 types of `TravelPlanObject`s
            since JavaFX `ListView` does not take in wildcard types. Ultimately, tab panes were used.
        - Implemented the overall layout of the UI and updated its styling to BlueTheme.
        - Implemented auto-tabbing feature, where application automatically changes to the correct tab when specific commands are called.

- Implemented **Directory and ObservableDirectory** ([\#86](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/86), [\#110](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/110))
    - **What it does**: `Directory` provides an abstraction for TravelPlan and Wishlist and facilitates the implementation
        of the navigation feature. `ObservableDirectory` represents the current directory, and is observed by the UI for updates.

- Add **TravelPlan and Wishlist Packages** in model ([\#59](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/59))
    - **What it does**: Each TravelPlan has its own name, start date and end date, and wraps its own list of activities,
        accommodations and friends. The wishlist wraps a list of pinned activities for future travels.

<div style="page-break-after: always;"></div>

**Minor Enhancements**:
- Implemented WanderlustDate in model ([\#59](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/59),
    [\#78](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/78),
    [\#79](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/79))
- Updated **Help Feature** to include link to Wanderlust User Guide.
- Implemented **Clear Feature** ([\#277](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/277))
- Added **SampleWanderlustDataUtil** in Model ([\#81](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/81))

**Contributions to User Guide**:
- Added documentations for `show` feature ([\#18](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/18)),
    `move` feature ([\#186](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/186)),
    `clear` feature ([\#277](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/277)), and
    `goto` feature (explaining about directories in Wanderlust, written in a shared document before UG was transferred to UserGuide.md)
- Added Table of Contents ([\#18](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/18)) and Ui.png ([\#53](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/53), [\#291](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/291))

**Contributions to Developer Guide**:
- Added documentations for the `copy` and `move` feature, including CopyActivityDiagram and CopySequenceDiagram.
([\#144](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/144), [\#150](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/150))
- Added use case to clear Wanderlust. ([\#277](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/277))

**Testing, Bug Fixing & Defensive Programming**:
- Added Test Cases for TravelPlan Package ([\#85](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/85)),
    Wishlist Package ([\#280](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/280)),
    LogicManager ([\#106](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/106)), and
    ClearCommand ([\#277](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/277))
- Fixed Bugs found in PED and throughout Wanderlust (
    [\#197](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/197),
    [\#199](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/199),
    [\#202](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/202),
    [\#203](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/203),
    [\#222](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/222),
    [\#224](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/224),
    [\#235](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/235),
    [\#241](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/241),
    [\#288](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/288),
    [\#291](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/291),
    [\#292](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/292),
    [\#311](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/311)
  )

**Project management**:
- Schedule and attend weekly meetings.
- Lead and contribute to discussions during weekly meetings.
- Maintain issue tracker and milestone.

**Contributions to team-based tasks**:
- Helped teammates with debugging and understanding codebase.
- Linked Up Ui with Logic, Model and MainApp. ([\#88](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/88))

**Community**:
- Total PRs reviewed: 31
- PRs reviewed with non-trivial review comments:
    [\#75](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/75),
    [\#84](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/84),
    [\#132](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/132),
    [\#135](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/135),
    [\#251](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/251),
    [\#284](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/284)
- Reported bugs and suggestions for other teams in the class:
    [Reported 7 Bugs in PED](https://github.com/jeannetoh99/ped/issues)
