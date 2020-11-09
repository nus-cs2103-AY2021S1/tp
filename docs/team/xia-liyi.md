---
layout: page
title: Xia Liyi's Project Portfolio Page
---

## Project: Ace CS2103/T

Ace CS2103/T is a desktop task manager application used for keeping track of tasks for CS2103/T Software Engineering. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense_link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=T14&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=XIA-LIYI&tabRepo=AY2021S1-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

* **Model Component**: Added Model Component
    * What it does: In charge of most Model components including creation, bug shooting, maintenance, etc.
    * Justification: The Model component is the foundation of the whole application. Any other components should be based on a good and effective design of model component.
    * Task, TaskList, UiTaskList: Task is basic unit of operation. TaskList is a container of all tasks. UiTaskList is a container of tasks to be displayed.
    (Pull Request [#57](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/57) [#73](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/73))
    * ModelManager: It is an interface for other components to access model.
    (Pull Request [#80](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/80))

* **Storage Component**: Added Storage Component
    * What it does: In charge of Storage component including creation, bug shooting, maintenance, etc.
    * Justification: The Storage component is to store user's data (tasks) and preferences. A good Storage component ensures the user data can be written and read back correctly.
    * Highlight: It uses Json to be data type.
    * Related Pull Request: [#73](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/73)
* **UI Component**: Helped UI Component
    * What it does: Helped UI team add some features, i.e. Progress Bar, add CSS file.
    * Justification: These features can make UI look better which benefits the users.
    * Related Pull Request: [#145](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/145) [#157](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/157)
* **Documentation**:
  * User Guide:
    * Added the documentation for `find` and `list`.

  * Developer Guide:
    * Added Model part and its diagram to elaborate the working flow of Model component.
    * Added Storage part and its diagram to elaborate the working flow of Storage component.
    * Example of Pull Request: [#135](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/135)

* **Contributions to team-based tasks**:
  * Cleaned the code to pass CI for several times: Pull Requests [#91](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/91) [#94](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/94) [#240](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/240)
  * Helped teammates to debug the code.
  * Added the test code: Pull Request [#222](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/222)

* **Community**:
  * Bugs reported to other teams: [\link](https://github.com/XIA-LIYI/ped/issues)
