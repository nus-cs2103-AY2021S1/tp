---
layout: page
title: Rachel Gina's Project Portfolio Page
---

## Project: Bagel

Bagel is a desktop flashcard application used for studying and memorisation purposes.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an add command that allows the user to view the list of flashcards.
  * What it does: allows the user to add a flashcard to the total list of flashcards.
  * Justification: This feature is a basic critical function for an MVP for Bagel, without this function, there will be no way to have any flashcards.

* **New Feature**: Added a delete command that allows the user to delete a specified index of a flashcard from the list of flashcards.
  * What it does: allows the user to delete a flashcard from the list of flashcards.
  * Justification: This feature is a basic critical function for Bagel, without this function, there will be no way to remove any flashcards.

* **New Feature**: Added a tag command that allows the user to add, edit and delete tags of a flashcard.
  * What it does: allows the user to tag a flashcard (can tag during adding, can edit tag and delete tag.)
  * Justification: This feature is a good enhancement for flashcards as users can tag them by keywords, looking for them using those tags later on (mix sets)

* **New Feature**: Added a clear command that allows the user to clear out all the current flashcards.
  * What it does: allows the user to clear flashcards that are currently in the system.
  * Justification: This feature is a useful as users can restart from scratch upon getting familiar with the app, removing sample flashcards used to familiarise themselves with the app.

* **New Feature**: Added a help command that helps users learn the commands available for Bagel.
  * What it does: shows the user a message explaining how to access the help page.
  * Justification: This feature is a useful as users access the User Guide easily to find out about available commands they can use.

* **New Feature**: Added a side bar to the graphical user interface (GUI) that helps users navigate their flashcards by sets in Bagel.
  * What it does: shows the user what sets they have added, buttons showing the sets will also vanish when sets are emptied or cleared.
  * Justification: This feature is a useful as users view and navigate sets of flashcards they have added easily.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Replaced the original files in AB3 to Flashcard files (Folders: Ui, AddressBookParser). (Pull request [\#45, #40]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add`, `delete`, `tag`, `clear` and `help`.
    * Added documentation for Command Summary
    * Added examples for commands in Quick Start
  * Developer Guide:
    * Added implementation details of the `add`, `delete`, `tag`, `clear` and `help` features.
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#269](https://github.com/nus-cs2103-AY2021S1/ip/pull/269), [\#326](https://github.com/nus-cs2103-AY2021S1/ip/pull/326)
