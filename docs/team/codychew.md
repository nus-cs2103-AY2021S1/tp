---
layout: page
title: Cody's Project Portfolio Page
---

## Project: Police Investigation Virtual Organisational Tool 

PIVOT is a desktop application to assist the police investigators in keeping track of their investigations and relevant information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to open documents from the application.
  * What it does: allows the user to open a file stored in their local computer directly from the program. 
  * Justification: This feature improves the product significantly there can be many files saved in the program, and the user
  can conveniently open relevant files while using the program.
  * Highlights: The main challenge was to store the references to work with the logic of the program as users are able 
  to directly manipulate files in their local folder from outside the program. As the program does not know the different 
  "states" of the files, there needs to be separate checks for validity of the reference as well as its existence when the 
  user opens the file.
  * Credits: 
    - The `Desktop` class allows us to open the file directly from the program.
    - `JUnit 5` Temporary Directory support allows me to test directory and file creation locally without interfering with 
  user program files. It also aids in the clean up of created files during unit testing.
    
* **New Feature**: Added commands to edit title, status and documents from a case.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=codychew&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Released `v1.3.trial` - `v1.3` on GitHub

* **Enhancements to existing features**:
  * Abstract out common Naming classes (Pull requests [\#82](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/82), [\#89](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/89))
  * Included `ReferenceStorage` to `StorageManager` to handle references storage on program initialization (Pull requests [\#137](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/137))

* **Documentation**:
  * User Guide:
    * Added documentation for the features related to `documents` and `references`.
    * Did cosmetic tweaks to existing documentation of features in the `case page`: [\#236](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/236)
  * Developer Guide:
    * Added implementation details of the `document` feature [\#72](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/148)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#81](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/81), [\#119](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/119)
  * Contributed to forum discussions (examples: [\#124](https://github.com/nus-cs2103-AY2021S1/forum/issues/124#issuecomment-685717298), [\#25](https://github.com/nus-cs2103-AY2021S1/forum/issues/25))
  * Reported bugs and suggestions for other teams in the class (examples: [\#29](https://github.com/nus-cs2103-AY2021S1/forum/issues/29#issuecomment-676010186), [\#5](https://github.com/nus-cs2103-AY2021S1/forum/issues/5#issuecomment-676537747))
  * Got help from a classmate when failing Github CI when testing the `document` feature ([\#296](https://github.com/nus-cs2103-AY2021S1/forum/issues/296#issuecomment-704173027))

