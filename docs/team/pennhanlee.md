---
layout: page
title: Penn Han's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application used for managing user added books to manage book records such as
last read page (bookmark), added goals and notes. A user will interact with bookmark through a GUI created with JavaFx and
invoke commands through a Command Line Interface style.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F13&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=pennhanlee&tabRepo=AY2021S1-CS2103T-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added the ability to find Word Suggestions for typing errors during `Find` command.
  * What it does: When `Find` command is invoked, there may be spelling errors or inaccurate queries provided by the user. The feature will help to search for
  words that are similar and return the closest word as a suggestion to the user. However, if the given word to search has no similar words, there will not be
  any suggestions returned and a standard No Suggestion message will be returned to the user.
  * Justification: This feature improves the product significantly because a user can make mistakes when finding books. This is likely to occur often as _bookmark_ uses the CLI to invoke commands. 
  _bookmark_ will provide suggestions and improve the User Experience as users will be able to find their intended books more easily.
  * Highlights: This enhancement require the creation with a new data structure into model to store the individual words in each book's name, genre and tag. 
  * Credits: The suggestion feature is based off the damerau-levenstein algorithm. [Algorithm_link](https://github.com/KevinStern/software-and-algorithms/blob/master/src/main/java/blogspot/software_and_algorithms/stern_library/string/DamerauLevenshteinAlgorithm.java)
  * Pull requests for this feature: [#107](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/107), [#122](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/122)

* **New Feature**: Improved Help Window to display Command Summary for quick access.
  * What it does: when `help` command is activated, the Help Window will display a summarised version of the User Guide displaying the 
  commands available and the CLI format that it should follow.
  * Justification: This feature improves the User Experience of _bookmark_ significantly as the newly added commands adds to a long list of
  prefixes and CLI formats that a user will have to follow to navigate through the application. As opposed to providing a link to the User Guide, 
  experienced Users who have already visited the User Guide can look for what they need in the Help Window quickly.
  * Highlights: This enhancement resulted in the need for creation of different help page command classes. It will need to be maintained once new commands are added or existing commands are updated.
  * Credits: JavaFX Scenebuilder
  * Pull requests for this feature: [#94](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/94), [#128](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/128)


* **Enhancements to existing features**:
  * Updated codebase for Add Command and other relevant classes to accept Books and its new attributes. <br> (Pull requests [#84](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/84))
  * Updated constants in various TestUtil to be used in tests and updated tests to accommodate to the revamped Add Command <br> (Pull requests [#84](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/84))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Add`, `Suggestion Feature` and `Help` <br> (Pull requests [#111](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/111), [#129](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/129))
    * Edited Quickstart to installation and starting the application with Images <br> (Pull requests [#129](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/129))
    * Standardise all commands to follow a format: Command Overview Examples, Important Detail, Image example and Warning to existing documentation. <br> (Pull request [#172](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/172))
    * Cleaned up the UG to present the information in a clearer manner. <br> (Pull request [#175](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/175))
    
  * Developer Guide:
    * Added implementation details of the similar features such as
      * `add`
      * `list`
      * `edit` 
      
      under XYZCommand. <br> 
      Pull Request: [#175](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/175)
    * Added implementation details of `Suggestion` feature. <br> 
      Pull Request: [#111](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/111)

* **Community**:
  * PRs reviewed (with non-trivial review comments): <br> 
  [#85](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/85), [#96](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/96), 
  [#97](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/97), [#98](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/98),
  [#103](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/103), [#115](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/115),
  [#119](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/119), [#130](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/130),
  [#167](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/167), [#170](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/170),
  [#179](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/179)
  
  * Contributed to forum discussions <br>
  [#118](https://github.com/nus-cs2103-AY2021S1/forum/issues/118)
  * Nontrivial bugs reported for other project team <br> 
  [1](https://github.com/pennhanlee/ped/issues/8), [2](https://github.com/pennhanlee/ped/issues/7), [3](https://github.com/pennhanlee/ped/issues/6),
  [4](https://github.com/pennhanlee/ped/issues/5)
