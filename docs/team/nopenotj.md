---
layout: page
title: Jay Chua's Project Portfolio Page
---

## Project: Modduke

Modduke is a desktop app targeted towards NUS students. It allows them to easily manage their contacts, modules and meetings during the semester. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Autocomplete TextField Module (Pull Requests [\#143](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/143), [\#249](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/249), [\#265](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/265))
  * What it does: Allows users to generate suggestions for contact names, module names and meeting names.
  * Justification: This feature improves the product significantly because some of our commands require the full names of contacts, meetings or modules
  that may be laborious to type it all out. This feature saves the user time and effort.
  * Highlights: Because the feature does not follow the existing AB3 command pattern, this enhancement required some preliminary research into the JavaFX's
  TextField library to figure out how to trigger and execute autocompletion. As such it required an in-depth analysis of design alternatives, and a lot of time
  doing trial and error with different implementations. Additionally, the implementation required an understanding of the JavaFx's event handling mechanisms which
  was challenging especially when it came to dealing with edge cases. 
  * Credits: Took inspiration from the zsh autocomplete function.

* **New Feature**: Fuzzy Find Completion TextField Module (Pull Requests [\#236](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/236))
  * What it does: Allows users to generate suggestions for any user input. This feature was made available in v1.4 and provide all the benefits of the Autocomplete module and more.
  * Justification: This feature improves the product significantly compared to the autocomplete function released in v1.3 because the command prefix for Autocomplete may be unintuitive to fill in for some users
  and also there was a lack of a GUI to show available suggestions. This feature greatly improves the UX.
  * Highlights: While searching online, found a very interesting solution on Stack Overflow (Link stated in credits), however it was not just a plug and play solution as the solution only supported
  the autocompletion of only 1 word for the whole field and it is not ideal for Modduke. So in coming up with a solution, I made this new Fuzzy Find Completion (fzf) module that injects fzf capabilities to a JavaFx TextField. 
  It was quite a difficult task because I wanted the menu to appear right at caret's position where the FZF was triggered and also allow users to continue to use the arrow keys to navigate the fzf suggestions. The most arduous part of this implementation
  was finetuning how the fzf was triggered and how to properly detect the "query" with which the suggestions will be generated on. This includes edge cases where users activate fzf in the middle of a String of text.
  Additionally, setting up testFx and getting it to work was also very time-consuming.
  * Credits: Took inspiration from [this solution](https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nopenotj&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to show Meeting and Module Panels (Pull requests [\#109](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/109), [\#93](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/93))
  * Made Datetime in Meetings to be more user-friendly for readers and sort it by date and time (Pull requests [\#114](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/114), [\#120](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/120))
  * Wrote tests for meetings to increase coverage from 51% to 52% (Pull requests [\#121](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/121))
  * Wrote tests for FZF and Autocomplete Modules to increase coverage from 0% to ~100% for these 2 classes (Pull requests [\#264](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/264) , [\#249](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/249))
  * Add shortcuts for CommandBox (Pull requests [\#143](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/143), [\#236](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/236))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Autocompletion` and `Fuzzy Find Completion` [\#143](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/143) , [\#236](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/236)
  * Developer Guide:
    * Added implementation details of the `Autocomplete` feature. [\#161](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/161) , [\#271](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/271)
    * Added implementation details of the `Fuzzy Find Completion` feature. [\#278](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/278)
    
* **Tools**:
  * Integrated a TestFx to the project ([\#249](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/249))

