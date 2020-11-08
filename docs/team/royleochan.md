---
layout: page
title: Roy Chan's Project Portfolio Page
---

## Project: Modduke

Modduke - Modduke is a desktop app targeted towards NUS students. It allows them to easily manage their contacts, modules and meetings during the semester. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the feature where users can manage agendas and notes for meetings in the meeting add and meeting view command. ([\#159](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/159))
  * What it does: Allows the user to add and edit both agendas and notes for meetings where the data will persist and be saved in storage.
  * Justification: This feature improves the product significantly for a NUS student who have to manage information regarding project meetings or consultations for a module. For example, if a student has a consultation with a professor or ta, the student can add all the concepts he wishes to clarify as agendas for easy reference during the consultation. After the consultation, he can consolidate and add all the notes he took down during the consultation.
  * Highlights: This enhancement required understanding across several architectural components of the code which includes the logic, model and storage.

* **New Feature**: Added a meeting view command that allows users to view a selected meeting's full details. ([\#159](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/159), [\#163](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/163))
  * What it does: Allows the user to view all the details for the selected meeting. The user can toggle between viewing the meeting's agendas and the meeting's notes using the GUI's tab menu. Any updates to the meeting will also be immediately reflected and updated.
  * Justification: This feature improves the product significantly because a NUS student can make reference to the meeting's agendas and notes during the meeting to stay on track throughout the meeting or before the meeting to prepare adequately for the meeting. This was a particularly challenging and large feature because the new GUI had to be built from scratch to incorporate Java FX's Tab pane. Furthermore, the user interface had to observe for any changes to the selected meeting such that it will get updated immediately. This includes several commands such as all the edit and delete commands for contact, module and meeting which can all affect the selected meeting's details.
  * Highlights: This enhancement affects existing commands and commands to be added in future. Hence, it required an in-depth analysis of design alternatives.

* **New Feature**: Added a light command and dark command that allows users to switch between light and dark theme in Modduke. ([\#244](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/244))
  * What it does: Allows the user to switch between dark and light themes according to their preference.
  * Justification: This feature improves the product by enhancing overall user experience. Many applications allow users the flexibility to change between their preferred mode since for some users, dark mode reduces eye strain. There were altogether 3 separate windows which needed to be styled and updated upon a change of theme: the main window, the help window as well as the timeline window. The implementation was challenging as it required the application to support dynamic styling for all 3 windows where the command would activate and update each window with the correct stylesheet. Additionally, it required a lot of time on experimenting with color palettes.
  * Highlights: This enhancement not only required understanding of good UI/UX design, but also  consideration of the appropriate design pattern since there might be more themes that can be added in the future making extensibility of the design important.

* **Enhancements to existing features**:
  * Updated the GUI dark color scheme according to Material UI design standards ([\#110](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/110/files))
  * Changed add command to contact add command ([\#77](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/77))
  * Updated contact names to be unique and changed edit and delete commands to contact edit and contact delete commands. Also switched from index-based commands to name-based commands. ([\#80](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/80), [\#87](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/87))
  * Ensured that editing or deleting a contact is integrated correctly with modules and meetings if the edited or deleted contact is part of any module or meeting. ([\#134](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/134))
  * Extended the person class to have 3 types: Contact, Professor and TA. ([\#147](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/147))
  * Extended help command to give a clickable link to Modduke's user guide. ([\#160](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/160))

* **Refactoring**:
  * Removed address from the application ([\#76](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/76)))
  * Refactored and standardised the command format to have command category and command verb for all Modduke commands ([\#77](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/77/files))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=royleochan&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Set up the GitHub team organisation and repository
  * Managed releases `v1.2.1` - `v1.3` (2 releases) on GitHub
  * Edited and uploaded video demonstrations for release `v1.2` - `v1.3` ([v1.2](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/110/files), [v1.3](https://www.youtube.com/watch?v=3WY8xRT7VIg&ab_channel=RoyChan))
  * Managed issue tracker
  * Regularly updated user guide and test cases upon adding new functional code

* **Documentation**:
  * Modduke Product Website:
    * Changed website theme to use solarized minima skin ([\#64](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/64/files))
  * User Guide:
    * Added documentation for the commands  `light`, `dark`, `contact add`, `contact delete`, `contact edit` and `meeting view`
  * Developer Guide:
    * Added implementation details of the `meeting view` feature including alternative design considerations.
    * Added implementation details of the `light` and `dark` feature.

* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/36#issuecomment-677478476), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/52#issuecomment-678850772), [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/147#issuecomment-684919663), [4](https://github.com/nus-cs2103-AY2021S1/forum/issues/173#issuecomment-687621301))

* **Tools**:
  * Integrated jekyll-remote-theme to the project ([\#64](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/64/files))
