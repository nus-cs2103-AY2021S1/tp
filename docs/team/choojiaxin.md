---
layout: page
title: Choo Jia Xin's Project Portfolio Page
---

## Project: ProductiveNUS
### Project Overview

ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### My Contributions
#### Code contributed
This is the link to the code contributed by me:
[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=choojiaxin)

#### Enhancements and features implemented

* **Creating the deadline class** 
[\#39](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/39)

I refactored the phone field in AB3 to the deadline field of Task in ProductiveNUS. The validation regex for phone had to be deleted and replaced with `DateTimeFormatter` to check for valid dates and times. This process was quite tedious as it was easy to miss out some phone fields in the comments, JavaDocs and names of methods and classes. Refactoring the test cases for phone to take in valid deadlines was also tedious and took quite some time, especially since this was done near the beginning of the project when I was still familiarising myself with the code base.

* **Creating the abstract Task class** 
[\#62](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/62)
[\#129](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/129)

I implemented the abstract Task class, which is the parent class of `Assignment` and `Lesson`. The creation of this class improves the product as it not only allowed us to implement a `UniqueTaskList` to store a user's upcoming tasks, but it enforces OOP principles as well.

The existence of the Task class also meant that the model package had to be refactored as the fields used in Task (`Name`, `Deadline` and `ModuleCode`) were currently in `Assignment` due to the way AB3 was implemented.

* **Set and remove reminders for assignments** 
[\#16](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/16)
[\#118](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/118)
[\#143](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/143)
[\#152](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/152)

I implemented the remind and unremind list command which allows the user to **set/remove reminders for one assignments at a time**. The user can set reminders for **multuiple assignments at a time** as well. This feature improves the product because a user may forget assignments that have faraway deadline and the app should provide a convenient way for users to be reminded to finish their assignments. A user can make mistakes when setting reminders for assignments as well, thus the app should provide a way for users to rectify their mistake. 

The remind command was especially challenging to implement as it was implemented near the beginning of the project when I was still familiarising myself with the code base. The test cases for `RemindCommand` especially took some trial and error as I was unsure of the difference between the unfiltered and filtered list in `DeleteCommandTest`, which was where I was referencing the test cases from.

While implementing the unremind command, I also implemented the abstract `NegateCommand` class which `UnremindCommand` extends from. The `NegateCommand` was implemented to **enforce OOP principles** as other commands similar to unremind (unprioritize and undone) also extends from `NegateCommand`.
  
* **Automated updating of task list**
[\#144](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/144)
[\#243](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/243)

I added automation of the task list so that it is updated in real time. This feature enables the app to check the user's upcoming tasks in real time and automatically updates removes any task that has passed. This feature improves the product significantly because a user will refer to their upcoming tasks frequently to quickly view what task they have next on their schedule. Thus, the app should accurately reflect the next task in their schedule without the need for a user to manually update it himself.

This feature was particularly challenging to implement as I had to figure out a way for the app to periodically check data that can be modified in real time as well (whenever a user adds/deletes/edits an assignment or imports lessons). The implementation too was challenging as it required an **in-depth understanding of both multithreading and JavaFX** and a good amount of time was spent exploring multithreading using the typical Thread Java 11 API, before I found out that that would not be possible to use the typical Java 11 API for multithreading as JavaFX is not compatible with them.

**Credits**: Code implemented is adapted from one of the examples given in the [Task<V> JavaFX API](https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html) as well as an answer from [StackOverflow](https://stackoverflow.com/questions/9966136/javafx-periodic-background-task).

#### Bug fixes
* **Found bug in JsonSerializableAddressBookTest** 
[\#89](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/89)

During v1.2b, there was a bug in the code which was causing the test cases in `JsonSerializableAddressBookTest` to fail. Upon further inpsection of the code, I managed to determine the source of the bug, which was that the `addressbook.json` file contained some invalid and unknown fields. This was due to our unfamiliarity with the code base at the time; hence we believed modifying the data stored in the `addressbook.json` file directly was safe.

The root of the bug took some time figuring out as I was unfamilair with the storage package in the code base as, at that point in time, I did not need to modify any code in that package. Therefore, it was challenging to find the root of the bug as I had to spend a good amount of time exploring the code base using IntelliJ and understanding how `JsonSerializableAddressBook` was implemented.

### My Contributions to User Guide
I added documentation for remind, unremind and automated features and enhancements under features. I also added the icon usages, command syntax, as well as date and time format under About segment. I also helped to fix any formatting errors found in the User Guide. 
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


### My Contributions to Developer Guide

I contributed to the remind feature under Imeplementations and its Sequence diagram. I also included documentation on the NegateCommand and automated updating of task list and their UML diagrams. I also editted the Class diagram for Storage and Model. I also contributed to the Appendix section (Manual testing etc.) 
[\#130](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/130), 
[\#137](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/137), 
[\#158](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/158), 
[\#266](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/266), 
[\#283](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/283)  


### My Contributions to team-based tasks

* I maintained the issue tracker and I assigned and added relevant tags for issues.
* I released v1.3 of ProductiveNUS before the deadline [v1.3 release](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases/tag/v1.3)
* I updated parts of the User/Developer Guide that are not specific to a feature
* I contributed to team meeting notes that were taken down during our weekly team discussions
* I sent a timeline of our deadline and deliverables to the group so that we would finish our tasks on time 
* I added user stories to the README. [\#54](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/54/files)


### Review contributions:
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
