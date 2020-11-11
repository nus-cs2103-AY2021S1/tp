---
layout: page
title: Kenneth's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application used for managing user added books to manage book records such as
last read page (bookmark), added goals and notes. A user will interact with bookmark through a GUI created with JavaFx and
invoke commands through a Command Line Interface style.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=angrybunny123&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added the ability to sort books by name, genre, bookmark or reading progress.
  * What it does: Allows the user to sort books by a specified field in ascending order.
  * Justification: This feature improves the product significantly because a user can order their books based on their 
    chosen preference, which makes their book list more organised and easier to navigate.
  * Highlights: This enhancement involved updating of user preferences in the `preferences.json` file, so that books would 
    remain in sorted order even after exiting and restarting the application. Subsequent books that were added or edited 
    would be placed in the correct sorting order in the book list. The implementation was challenging as it 
    required an in depth understanding of how `userPrefs` were generated from the `preferences.json` folder, an aspect
    that was considered only by the sort feature. 
  * Code contributed for this enhancement: [#109](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/109), 
      [#119](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/119)

* **Enhancement to existing feature**: Enhanced the `find` command to allow finding of books by name, genre, tag and
    completion status. Prior to the enhancement, only finding of books by name was supported.
  * What it does: Allows the user to find books by name, genre, tag and completion status.
  * Justification: This feature improves the product significantly because a user can find books by a specific field, which
    makes it easier to find specific books as well as a certain subset of books e.g. all books that contain the same genre
    or all books that contain the same tag.
  * Highlights: This enhancement involved the creation of several predicate classes packaged within the
    `seedu.bookmark.model.book.predicates` package that are used to filter the book list according to user input. The chosen 
    implementation makes the code extremely maintainable: more filtering mechanisms can be easily added to produce additional 
    functionality within the `find` command.
  * Wrote additional tests for the `find` feature to increase coverage from 67% to 68%.
  * Code contributed for this enhancement: [#96](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/96), 
      [#109](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/109) 

* **Project management**:
  * Helped maintain the issue tracker, ensuring that all issues created were appropriately and promptly resolved with 
    corresponding pull requests.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and `sort` (Pull requests [#96](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/96), 
      [#109](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/109))
  * Developer Guide:
    * Added implementation details of the `find` and `sort` features. (Pull requests [#110](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/110),
      [#171](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/171))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#121](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/121), 
  [\#130](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/130), [\#164](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/164), 
  [\#180](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/180)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/141), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/170))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/angrybunny123/ped/issues/3), 
  [2](https://github.com/angrybunny123/ped/issues/4), [3](https://github.com/angrybunny123/ped/issues/14))
