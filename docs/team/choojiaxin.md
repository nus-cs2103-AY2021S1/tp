---
layout: page
title: Choo Jia Xin's Project Portfolio Page
---

## Project: ProductiveNUS
ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:

* **Creating the deadline class** 
[\#39](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/39)

I refactored the phone field in AB3 to the deadline field of Task in ProductiveNUS. The validation regex for phone had to be deleted and replaced with `DateTimeFormatter` to check for valid dates and times. This process was quite tedious as it was easy to miss out some phone fields in the comments, JavaDocs and names of methods and classes. Refactoring the test cases for phone to take in valid deadlines was also tedious and took quite some time, especially since this was done near the beginning of the project when I was still familiarising myself with the code base.

* **Creating the abstract Task class** 
[\#62](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/62)
[\#129](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/129)

I implemented the abstract Task class, which is the parent class of `Assignment` and `Lesson`. The creation of this class improves the product as it not only allowed us to implement a `UniqueTaskList` to store a user's upcoming tasks, but it enforces OOP principles as well. The model package had to be refactored as well as the fields used in Task (`Name`, `Deadline` and `ModuleCode`) were currently in `Assignment` due to the way AB3 was implemented.

* **Set and remove reminders for assignments** 
[\#16](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/16)
[\#118](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/118)
[\#143](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/143)
[\#152](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/152)

I implemented the remind and unremind list command which allows the user to **set/remove reminders for one assignments at a time**. The user can set reminders for **multuiple assignments at a time** as well. This feature improves the product because a user may forget assignments that have faraway deadline and the app should provide a convenient way for users to be reminded to finish their assignments. A user can make mistakes when setting reminders for assignments as well, thus the app should provide a way for users to rectify their mistake. While implementing the unremind command, I also implemented the abstract `NegateCommand` class which `UnremindCommand` extends from. It was implemented to **enforce OOP principles** as other similar commands similar (unprioritize and undone) also extends `NegateCommand`.
  
* **Automated updating of task list**
[\#144](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/144)
[\#243](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/243)

This feature enables the app to check the user's upcoming tasks in real time and automatically updates removes any task that has passed. This feature improves the product significantly because a user will refer to their upcoming tasks frequently to quickly view what task they have next on their schedule. Thus, the app should accurately reflect the next task in their schedule without the need for a user to manually update it himself.

It was particularly challenging to implement as I had to figure out a way for the app to periodically check data that can be modified in real time (whenever a user adds/deletes/edits an assignment or imports lessons). It also required an **in-depth understanding of both multithreading and JavaFX** and a good amount of time was spent exploring multithreading using the typical Thread Java 11 API, before I found out that that would not be possible to use the typical Java 11 API for multithreading as JavaFX is not compatible with them.

**Credits**: Code implemented is adapted from one of the examples given in the [Task JavaFX API](https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html) as well as an answer from [StackOverflow](https://stackoverflow.com/questions/9966136/javafx-periodic-background-task).

* **Found bug in JsonSerializableAddressBookTest** 
[\#89](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/89)

During v1.2b, there was a bug in the code which was causing the test cases in `JsonSerializableAddressBookTest` to fail. Upon further inpsection of the code, I managed to determine the source of the bug, which was that the `addressbook.json` file contained some invalid and unknown fields. It was challenging to find the root of the bug as I had to spend a good amount of time exploring the code base using IntelliJ and understanding how `JsonSerializableAddressBook` was implemented.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=choojiaxin)

* **Contributions to team-based tasks:**

  * I maintained the issue tracker and I assigned and added relevant tags for issues.
  * I released v1.3 of ProductiveNUS before the deadline [v1.3 release](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases/tag/v1.3) and added user stories to the README. [\#54](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/54/files)
  * I contributed to team meeting notes and sent timelines of our deadline and deliverables to the group so that we would finish our tasks on time 

* **Review contributions:**
  * PRs reviewed (with non-trivial review comments): 
  [\#35](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/35),
  [\#61](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/61), 
  [\#65](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/65), 
  [\#68](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/68),
  [\#73](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/73),
  [\#74](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/74),
  [\#80](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/80),
  [\#81](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/81),
  [\#120](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/120),
  [\#122](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/122),
  [\#126](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/126),
  [\#163](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/163),
  [\#166](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/166),
  [\#182](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/182),
  [\#230](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/230),
  [\#241](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/241)

  * Reported bugs and suggestions for other teams in the class (examples: 
  [1](https://github.com/ChooJiaXin/ped/issues/1), 
  [2](https://github.com/ChooJiaXin/ped/issues/2), 
  [3](https://github.com/ChooJiaXin/ped/issues/3), 
  [4](https://github.com/ChooJiaXin/ped/issues/4), 
  [5](https://github.com/ChooJiaXin/ped/issues/5))

<div style="page-break-after: always;"></div>

* **My Contributions to User Guide**
  * I added documentation for remind, unremind and automated features and enhancements under features. I also added the icon usages, command syntax, as well as date and time format under About segment. I also helped to fix any formatting errors found in the User Guide. 
  [\#94](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/94), 
  [\#128](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/128), 
  [\#150](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/150), 
  [\#153](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/153), 
  [\#157](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/157), 
  [\#168](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/168), 
  [\#169](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/169), 
  [\#179](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/179), 
  [\#244](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/244), 
  [\#268](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/268), 
  [\#284](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/284) 

* **My Contributions to Developer Guide**
  * I contributed to the remind feature under Imeplementations and its Sequence diagram. I also included documentation on the NegateCommand and automated updating of task list and their UML diagrams. I also editted the Class diagram for Storage and Model. I also contributed to the Appendix section (Manual testing etc.) 
  [\#130](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/130), 
  [\#137](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/137), 
  [\#158](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/158), 
  [\#266](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/266), 
  [\#283](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/283)  
