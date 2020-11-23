---
layout: page
title: Toh Zhen Yu, Nicholas's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.
### Summary of Contributions
#### Code contributed
Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nicktohzyu) to view the code that I have contributed.

#### Enhancements implemented
* **Create Appointment and UniqueAppointmentList class**: Designed and created `Appointment` and `UniqueAppointmentList` classes to be used in **HairStyleX** (Pull request 
[\#89](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/89), 
[\#93](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/93), 
[\#97](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/97)).
    * What it does: Defines the blueprint and rules of creating appointments in **HairStyleX**.
    * Justification: These classes are fundamental to the entire project, since they define an interaction between a client and hairdresser, and other attributes an appointment should have.
    * Highlights:
        * Careful consideration of good OOP design was made when creating these classes. The Appointment class was made to be immutable; encapsulating classes for various attributes such as `AppointmentDate`, `AppointmentTime`, and `AppointmentStatus` were created.

* **Testing for Client class**: Created tests for `Client` class; created testing utilities such as `ClientBuilder`, `TypicalClients` (Pull requests 
[\#131](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/131), 
[\#297](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/297)).

* **Refactor Gender representation from String to Enum**: Changed the representation of gender from using `String` to `Enum` (Pull request [\#159](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/159)).
    * The enhancement also included a process to convert the gender to a `String` representation for display purposes.

* **Added feature to prevent clashing appointments**: Added checks to the "add appointment" process such that no client or hairdresser can be involved in simultaneous appointments (Pull requests
[\#178](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/178), 
[\#198](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/198), 
[\#301](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/301)).

* **Testing for Appointment class**: Created tests for `Appointment` class, including extensive testing for the clash-avoidance feature (Pull requests 
[\#210](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/210),
 [\#322](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/322), 
 [\#331](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/331)).

#### Contributions to the User Guide
* Responsible for the section on [appointments](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#44-appointment-management). 
* Provided a detailed guide supplemented with screenshots and examples for relevant commands including `add_appt`, `edit_appt`, `delete_appt`, `filter_appt`.

#### Contributions to the Developer Guide
* Responsible for the section on implementation of the [appointment](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#appointment-feature) feature. 
* Added a detailed sequence diagram representing the process involved in validating and executing an "add appointment" command.
* Proposed future improvements which improve the runtime performance of HairStyleX.
* Contributed the appendix section on effort.

#### Contributions to the team-based tasks
* Maintained the majority of issue trackers and projects.

#### Review/mentoring contributions
* Reviewed pull requests including 
[\#55](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/55), 
[\#85](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/85), 
[\#161](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/161).

* Contributed to documentation for other teammates and fixed typos .
    * Through pull requests including 
    [\#286](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/286), 
    [\#305](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/305).
    
    * Through informal communication channels such as Telegram.

#### Contributions beyond the project team
* [Reported 13 bugs](https://github.com/iamjamestan/ped/issues) in group T-17-3's tP during mock PE.
