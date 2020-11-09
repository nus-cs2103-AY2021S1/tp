# Nguyen Dinh Quang Minh - Project Portfolio

## Project: TrackIt@NUS

This portfolio aims to document the contributions that I have made to the **TrackIt@NUS** project. The Github Link to
 **TrackIt@NUS** can be found [here](https://github.com/AY2021S1-CS2103T-W13-4/tp).
 
## Overview

**TrackIt@NUS** is a desktop application to provide a simple an easy way for students to manage their academic and
 social life. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

## Summary of Contributions

### Code contributed: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nguyendqminh&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
 
### Enhancements implemented
  * Implemented features to add, delete, and edit a lesson in **TrackIt@NUS**.
      * Justification: A `Lesson` is a recurring (most typically weekly) task that students have to manage.
      Using the already existed `Task` type to deal with lessons, while viable, makes the user experience extremely painful,
      since users will have to add the same task every week. `Lesson` provides a more convenient and intuitive way for
      users to manage their recurring tasks.
      * Highlights: Because of the recurring nature of Lessons, implementing those features and their underlying methods
      (e.g. sorting lessons by days from the current date) requires a lot of considerations.
      
  * Implemented a feature to allow users to edit module codes and automatically update relevant tasks, lessons, and contacts.
      * Justification: As module codes are the identity of modules, edit them is not allowed in previous versions.
      However, having to delete the module and all its tasks and lessons just to change the module code is suboptimal.
      By changing the syntaxes of module-related features, we were able to allow users to edit module codes.
      Additionally, every related task and lesson will have its code updated, as well as every contact tag that matches the old module code.
      * Highlights: The implementation was rather tricky since it had to deal with not only the edited module, but tasks, lessons,
      and contacts as well.
 
### Contributions to the User Guide
  * Added documentations for lesson-related features: [#176](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/176), [#235](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/235).
 
### Contributions to the Developer Guide
  * Added implementation details for Contact Manager and relevant UML diagrams: [#345](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/345). 
  * Added implementation details for Lesson Manager and relevant UML diagrams: [#345](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/345).
  * Added some user stories and use cases: [#22](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/22), [#28](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/28).
  
### Community
  * PRs reviewed: [#61](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/61), [#99](https://github.com/AY2021S1-CS2103T-W13-4/tp/pull/99).
