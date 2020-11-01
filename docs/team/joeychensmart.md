---
layout: page
title: Joey Chen's Project Portfolio Page
---

## Project: McGymmy

McGymmy is a desktop diet tracker application for health-conscious software engineers.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Redesigned Feature**: Redesigned the logic component of McGymmy.
  * What I changed: Replaced the existing parser with the [Apache commons-cli](https://github.com/apache/commons-cli) library, and made the creation of commands follow
  a more declarative style. Also eliminated the need for parsers for each command.
  * Justification: I found that the old architecture was problematic as it required the programmer to create a lot of boilerplate just to achieve simple tasks.
  This new architecture also made it easier for me to implement the macro feature below. For more information on the justification, you can visit
  [the pull request](https://github.com/AY2021S1-CS2103T-W17-3/tp/pull/39).
  * Highlights: Developers now need to write about half the amount of code to get new features working (instead of creating a command and a parser class, now they only
  needed to create the command class). The code in each command class was also neater, and more intuitive by virtue of being more declarative.
  The design had to be carefully thought out to maximize developer productivity, and cost-benefit analysis had to be carried out for many design alternatives, including the original.
  The implementation was non-trivial, and required changing about 3 kLoC. The new API also had to be documented well enough for fellow developers to understand.
  * Credits: As mentioned above, this design made use of the [Apache commons-cli](https://github.com/apache/commons-cli) library for parsing text from the user, and automatically
  generating help text. All other work is solely by me.

* **New Feature**: Added the ability to create macros.
  * What it does: allows the user to create a 'macro' to run several commands in succession. Macros are saved on disk, and can be deleted in the CLI.
  * Justification: This feature allows users to automate routine, repetitive tasks by creating a macro or alias for it.
  For example, if I have a piece of toast and an egg for breakfast every morning, i would have to key in 2 commands `add -n toast -c 100` and `add -n egg -p 50` every morning.
  With this feature I can simply create a new macro called `breakfast`, and simply enter that instead of the same 2 commands every morning.
  * Highlights: This enhancement works for all commands in the app. The design had to be carefully crafted to ensure the implementation was easy to carry out and understand,
  and followed good design principles, and also to avoid bugs such as infinite loops. The implementation was also non-trivial, and required creating many different classes.
  * Credits: The design and implementation was inspired by [my ip](https://github.com/JoeyChenSmart/ip), but many design and implementation details were revisited and
  tweaked to fit with McGymmy (e.g. saving to storage). Both implementations also rely on the redesigned logic component above.
  

* **New Feature**: Added a help command.
  * What it does: shows a list of all commands to the user, along with a short description of each command. The user can also request for information on a specific command.
  * Justification: allows the user to very conveniently get assistance on how to use the app, within the app itself.
  * Highlights: This feature leverages on the above redesigned logic component. Cleverly reusing information provided by the developer using the declarative style,
  this feature automatically generates help strings for each command. The design was carefully thought out to follow the DRY (don't repeat yourself) principle, to minimize
  bugs due to code duplication.
  * Credits: The idea, design and implementation were all carried out by me.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=joeychensmart&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=joeychensmart&tabRepo=AY2021S1-CS2103T-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Add documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
