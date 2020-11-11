---
layout: page
title: Le Minh Giang's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application used for managing user added books to manage book records such as
last read page (bookmark), added goals and notes. A user will interact with bookmark through a GUI created with JavaFx and
invoke commands through a Command Line Interface style.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=mgiang2015&tabRepo=AY2021S1-CS2103T-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **New Feature**: Switch UI from overview to detailed view using `view` command.
  * What it does: Show more details of a specified book, including notes taken and hide
  other books from the UI.
  * Justification: `list` view should be kept short and organised, so that users can
  have a general overview of their books. `view` command brings users to detailed view, 
  where they can look at more specific details.
  * Highlights: Our Detailed View went through many iterations. As new features are added
  to _bookmark_, the team has to decide whether to include it in the List view or 
  Detailed view only. I picked up Javafx in the process of adding information such as Goal
  to the Detailed View UI.
  * Code contributed to this feature: Pull Request [\#90](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/90)

* **New Feature**: Add reading goals to a specific book
  * What it does: Users can set a reading goal, which consist of a target page and a deadline.
  * Justification: Tasks are more manageable once they are broken into smaller tasks. This Goal feature
  helps users to break their books into bite-size reading goals. This way, reading a book becomes more
  managable, reducing the chance of readers forgetting about their book and stop half-way.
  * Highlights: If users have set a goal for a book, Goal field is colorized in _bookmark_ Ui
  according to reader's progress towards the goal. 
  If users completed a goal, their goal will be green. If it's in progress, goal will be yellow. If the deadline
  passed, goal will be in red. I picked up CSS and JavaFx CSS styling to help implement this feature.
  * Code contributed to this feature: Pull requests [\#108](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/108), [\#127](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/127), [\#168](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/168).

* **Documentation**:
  * User Guide:
    * Added documentation for `View` and `Goal` features.
    
  * Developer Guide:
    * Added implementation details of `Goal` feature.

* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/mgiang2015/ped/issues/9), [2](https://github.com/mgiang2015/ped/issues/6))
