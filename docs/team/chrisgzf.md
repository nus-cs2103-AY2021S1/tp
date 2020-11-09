---
layout: page
title: Christopher Goh's Project Portfolio Page
---

## Project: TAsker

TAsker is a desktop app for Teaching Assistants (TAs) to manage student administration, optimized for use via a Command Line Interface (CLI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

- **New Feature**: Implement the Attendance model

  - What it does: allows the user to save student's class attendance.
  - Justification: This feature is one of the core features of TAsker, attendance tracking. TAs would like to easily be able to keep track of which ones of their classes their students have missed or attended, so that they can easily keep track of their student's progress, as well as assign participation marks (if linked to attendance).
  - Highlights: This enhancement required an in-depth analysis of design alternatives to best store attendance data. After comparing between a few alternatives, we decided that attendance data should be tagged primarily to the students, and hence it guided us in the way we shape our data, in our persisted user data storage. The implementation too was challenging, as it required careful design and treatment to ensure that the Person model's stored Attendance data is the single source of truth, to prevent future potential bugs.
  - Credits: The Jackson library was used to serialize the attendance data into our persisted JSON data storage file.
  
- **New Feature**: Implement the Command History

    - What it does: Keeps track of the commands that the user has run previously, so that they can easily browse through and re-run past commands.
    - Justification: TAsker is an app meant primarily for use for fast-typists, and performing repeated commands like marking attendance for most of your students in your class can be very tedious. This feature makes it easy for the user to re-run previously run commands.
    - Highlights: The `CommandHistory` class was implemented with a Singleton design pattern as it made sense to me that the application should only have 1 command history, and hence it should only be instantiated at most once, and also, it was my first time implementing the Singleton pattern, so I wanted to give it a try. However, after implementing it, I can attest to all the Singleton haters out there that this design pattern is indeed horrible and is more of a anti-pattern. This is due to how difficult it is to write good, isolated tests for a Singleton class.
    
- **New Feature**: Design and implement the new TAsker UI

    - What it does: New UI following fresh design trends and fresh colour palettes
    - Justification: As computing TAs are usually very busy, tired, and packed with work, we wanted to come up with a UI that is inviting, happy, and keeps the TA awake. Fresh design trends such as rounded rectangles and pastel colour palettes are used to create a sense of happiness and ease in the user. The theme is also primarily a light-theme (instead of a dark-theme), so that the TA will feel less tired while working on administrative tasks.

- **New Feature**: Refactor UI CSS to use CSS variables

    - What it does: Better CSS code quality for the application
    - Justification: CSS variables allow us to set consistent colour palettes, and tune them at will in the future. This also provides great extensibility for future themes, as "themes" can really just be setting a few CSS variables. Use of CSS also allows us to use CSS pseudo-classes, to allow us to have a more interactive UI.

- **New Feature**: Refactor UI CSS to use system fonts

    - What it does: Use OS's preferred sans-serif fonts for the UI
    - Justification: Provides greater sense of familiarity and consistency with the app when their familiar system fonts (e.g. San Francisco for macOS) is used

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=totalCommits&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=chrisgzf&tabRepo=AY2021S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

- **Project management**:

  - Managed release `v1.3(trial)` on GitHub

- **Enhancements to existing features**:

  - Updated the GUI color scheme (Pull requests [\#59](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/59))
  - Wrote additional tests for existing features to increase coverage from 69% to 74% (Pull requests [\#188](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/188), [\#182](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/182), [\#185](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/185))

- **Documentation**:

  - User Guide:
    - Updated app screenshots [\#211](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/211)
    - Edited UG extensively to improve readability [\#204](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/204)
    - Fixed Table of Contents generation [\#85](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/85)
  - Developer Guide:
    - Added implementation details of the UI and CSS
    - Added implementation details of the `CommandHistory` feature
    - Fixed Table of Contents generation [\#85](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/85)
    - Added non-functional requirements [\#35](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/35)

- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#172](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/172), [\#161](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/161)
  - Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/52#issuecomment-678835959), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/172#issuecomment-690681241)
  - Contributed to se-edu's SE Textbook: [\#91](https://github.com/se-edu/se-book/pull/91)
  - Provided suggestions to enhance my classmates' workflow with their IDE: [\#58](https://github.com/nus-cs2103-AY2021S1/forum/issues/58)

- **Tools**:

  - Improved code review workflow by enforcing local CI checks before `git push` with gradle Git Hooks: see [\#88](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/88)
  - Integrated a code static analysis tool, Codacy, to provide us with more feedback on our code [\#192](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/192)
  - Added a markdown code formatter, prettier, to improve our editing workflow [\#40](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/40)
  - Enabled assertions [#\67](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/67)
