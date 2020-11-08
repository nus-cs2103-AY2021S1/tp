---
layout: page
title: Alex's Project Portfolio Page
---

## Project: Reeve

Reeve is a desktop companion application for one-on-one private tutors designed to help them better manage their students' academic and administrative needs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature added**: Added the **schedule** feature to **Reeve**.
  * What it does: Allows user to view classes on a timetable, rendered as a graphical user interface, either weekly or daily based on a date given.
  * Justification: This feature helps tutor to plan for their schedule and lessons either for the week or day. As is gives a whole view of the lessons ahead.
  * Highlights: This enhancement uses the current data and maps them to an entity that allows meaningful information to be shown.
  The implementation was challenging as it required integration of an external library with the current structure of the project. 
  Care have also been given to maintain the data integrity of the current system.
  * Credits: This feature uses the **[JFXtras](https://jfxtras.org/)** library which includes , [jfxtras-agenda](https://jfxtras.org/doc/8.0/jfxtras-agenda/index.html), [jfxtras-icalendarfx](https://jfxtras.org/doc/8.0/jfxtras-icalendarfx/index.html), [jfxtras-icalendaragenda](https://jfxtras.org/doc/8.0/jfxtras-icalendaragenda/index.html). 
                                                                                                                              
* **Other enhancements**:
  * Added the `Name` and `Year` fields in the `Student` class.
  * Updated json storage to be compatible with `Student` object.
  * Updated the `add` command for users to add `Student` object. This allows user to add a `Student`'s academic and admin fields.
  
* **Code contribution**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=alexcqy)

* **Project management**:
  * Helped to plan project base structure.
  * Laid out plans for `Student`, `Academic` and `Admin`.

* **Documentation**:
  * User Guide:
    * Added description for the feature `schedule` ([\#205](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/205)).
    * Added a visuals and description for users to understand the application interface.
  * Developer Guide:
    * Added implementation details of the `schedule`.
    * Updated design section for `Model` and `Ui` component.

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([#62](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/62), [#68](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/68))
  * Reported bugs and suggestions for other teams in the class (examples: [#1](https://github.com/AlexCQY/ped/issues/1), [#2](https://github.com/AlexCQY/ped/issues/2),  )

