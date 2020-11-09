---
layout: page
title: Hwang Yong Kang's Project Portfolio Page
---

## Project: ProductiveNUS

### Project Overview

ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### My Contributions to the Code

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hyngkng)

* **Created the Lesson class**
  [\#68](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/68/files)
  * **What:** A separate class that stores the user's lesson information which will be imported using an import feature.
  * **Why:** Lessons are meant to be viewed only and cannot be modified by the user, unlike the Assignment class.
  * **Credits:** Code implemented is adapted from existing Person class in AddressBook3.

* **Import Command**
  [\#73](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/73/files),
  [\#80](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/80/files),
  [\#241](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/241/files),
  [\#276](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/276/files)
  * **What:** Allows the user to import their NUSMods lesson information into ProductiveNUS.
  * **Why:** This feature improves the product significantly because a user can conveniently refer to their timetable on ProductiveNUS instead of having to go to NUSMods. Other commands such as Schedule also works best if the user can import their weekly timetable.
  * **Note:** This enhancement retrieves data from the NUSMods API by sending a HTTP GET request. A new model class Lesson is also implemented to support the import command.
  * **Credits:** Code implemented is adapted from examples on [Baldeung](https://www.baeldung.com/java-http-request) and the [NUSMods API](https://api.nusmods.com/v2/).

<div style="page-break-after: always;"></div>

* **Created the Priority class**
  [\#124](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files)
    * **What:** An assignment field that is optional. Users can set priorities to their assignments.
    * **Why:** It is likely that users have assignments of different importance and hence, having a priority tagged to the assignment would help them better manage their work.
    * **Credits:** Code implemented is adapted from existing fields in AddressBook3.

* **Prioritize/Unprioritize Command**
  [\#124](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files),
  [\#167](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/167/files),
  [\#276](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/276/files)
    * **What:** Allows the user to set the priority level of their assignments in ProductiveNUS, which is displayed as a coloured tag in the assignment card.
    * **Why:** This feature improves the user's experience as it is easier for them to spot assignments that are of greater importance so as to better plan their schedule.
    * **Note:** This enhancement is compatible with the Find feature, where users are able to list assignments of a specific priority tag.

### My Contributions to the Documentation
  * User Guide:
    [\#36](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/36/files),
    [\#95](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/95/files),
    [\#132](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/132/files),
    [\#163](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/163/files),
    [\#170](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/170/files),
    [\#248](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/248/files)
    * I added the Import and Prioritize/Unprioritize segments of the User Guide.
    * I also made format changes to ensure that the document is standardised.

  * Developer Guide:
    [\#59](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/59/files),
    [\#61](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/61/files),
    [\#134](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/134/files),
    [\#272](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/272/files)
    * I added the Import and Prioritize/Unprioritize segments of the Developer Guide.
    * I also made format changes to ensure the document is standardised.

### My Contributions to team-based tasks
  [\#180](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/180/files),
  [\#254](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/254/files)
  * I helped to refactor and rename all "AddressBook" to "ProductiveNUS".
  * I also helped with formatting issues with the User Guide.

### My Review Contributions
  * **PRs reviewed:**
    [\#58](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/58),
    [\#60](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/60),
    [\#65](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/65),
    [\#120](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/120),
    [\#126](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/126),
    [\#131](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/131),
    [\#145](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/145),
    [\#148](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/148),
    [\#150](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/150),
    [\#154](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/154),
    [\#173](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/173)
  * **Bugs found for PED:**
    [1](https://github.com/hyngkng/ped/issues/1),
    [2](https://github.com/hyngkng/ped/issues/2),
    [3](https://github.com/hyngkng/ped/issues/3),
    [4](https://github.com/hyngkng/ped/issues/4)
