---
layout: page
title: Lysire's Project Portfolio Page
---

## Project: ResiReg

ResiReg is a desktop residential management application used for managing a residential college such as Cinnamon College. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `rooms` model to ResiReg.
  * What it does: Equips ResiReg with the notion of a room, and provide the foundations for
    listing all rooms and storing room objects.  
  * Justification: This feature is fundamental for ResiReg, since it needs to be equipped with a notion of a room for it to
    represent rooms and thus manage a residential college such as Cinnamon College.
  * Highlights: This enhancement affects existing classes and classes to be added in the future. It required an in-depth analysis of
    the design considerations in AB3. The implementation too was challenging as it required the creation of new classes as well as changes
    to existing classes.

* **New Feature**: Added a history command that allows the user to view previously entered nonempty
    commands, either through typing the command itself or using its associated keyboard shortcut.
  * What it does: The command lists all previously entered nonempty commands (including invalid ones) in chronological order, along with their
    command numbers which specify the commands' position in the execution sequence. Additionally, it allows the user to
    navigate between the previously entered commands using the up/down arrow keys.
  * Justification: This feature greatly adds to the utility of the product since it provide useful information when the user
    tries to track down how recent system changes might have brought about. It also simplifies not only the process of repeating previous commands 
    but also the process of making invalid commands valid as the user can simply navigate to the command and make minor modifications
    instead of having to type the entire command.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it 
    required changes to existing commands. While setting up the keyboard shortcuts for the history command,
    a `KeyboardShortcutsEnum` class similar to the `CommandWordEnum` class was first created to prevent code duplication and allow easier addition of keyboard
    shortcuts in the future. The keyboard shortcut was then set up in this new class. 
  * Credits: Code for the history command is adapted from addressbook-level4, which can be found at https://github.com/se-edu/addressbook-level4. 
      
* **New Feature**: Added the ability to undo/redo previous commands that change the state of ResiReg, either through
    typing the commands themselves or using their associated keyboard shortcuts.
  * What it does: Allows the user to undo previously executed commands sequentially. Additionally, these undo commands can be reversed through redo commands.
  * Justification: This feature greatly adds to the utility of the product since 
    it provides the user with a convenient way to rectify mistakes made with regard to command execution.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it 
    not only required changes to existing commands but changes to support handling of keyboard shortcuts.
  * Credits: Code for the undo/redo command is adapted from addressbook-level4, which can be found at https://github.com/se-edu/addressbook-level4.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#search=lysire&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=Lysire&zR=AY2021S1-CS2103-T16-3%2Ftp%5Bmaster%5D&zACS=370.3076923076923&zS=2020-08-14&zFS=lysire&zU=2020-11-06&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Contributed to releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo`, `redo` and `history` [\#119](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/119)
    * Did cosmetic tweaks to existing documentation for the `rooms` command e.g. [\#91](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/91)
  * Developer Guide:
    * Added implementation details of the `undo`/`redo` feature. [\#119](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/119)
    * Added class diagram that shows the class structure regarding the `undo`/`redo` feature. [\#181](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/181)
    * Updated storage class diagram. [\#132](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/132)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#178](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/178), 
  [\#179](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/179), 
  [\#185](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/185)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Lysire/ped/issues), 
  [2](https://github.com/AY2021S1-CS2103-F09-1/tp/issues/237), [3](https://github.com/AY2021S1-CS2103-F09-1/tp/issues/238))

