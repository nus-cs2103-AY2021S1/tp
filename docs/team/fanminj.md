---
layout: page
title: Jian Fanmin's Project Portfolio Page
---

## Project: Trackr

### Project Overview
Trackr is an application for teaching assistants (TAs) who prefer to use a desktop application for managing their
student records. It is uses a Command Line Interface (CLI), while still retaining the benefits of a Graphical User
Interface (GUI). It is written in Java, and currently has over 10kLoC.

### Summary of Contributions

- Code contributed: [tP Code Dashboard](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=fanminj&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- Enhancements implemented:
    - New feature: Added the ability to keep track of student's attendance on top of their personal details.
        - What it does: This feature allows the user to mark a student as present or absent. Additionally, the student's
        participation score during tutorial can also be recorded.
        - Justification: This feature improves the product's user experience significantly because the user does not
        have to open up multiple software to keep track of their students' contact details and tutorial participation
        levels. It also allows the user to monitor the students that are falling behind (e.g. low attendance /
        participation scores), instead of having to manually manage their student.
        - Highlights: This enhancement involves changing the implementation of how the Student fields are stored. New
        design alternatives for the classes within the Storage components have to be devised.
    - Other changes:
        - Implemented the Student class
            - Refactored the Person class and added new fields for Student class such as StudentID and Attendance.
        - New commands to manage Student data
            - Added new commands such as add, edit, delete and find Student data.

- Contributions to User Guide:
    - Drafted the outline (e.g. table of contents) and the various sections of the User Guide.
    - Wrote the section for Student features and provided a summary table for the Student commands.

- Contributions to the Developer Guide:
    - Added the sequence diagram for the command `addMod`.
    - Wrote the implementation steps from section 4.1 to section 4.6 and appendix C and D.

- Contributions to team-based tasks:
    - Set up the GitHub team organisation and repo (including creating issue labels and milestones).
    - Managed the deadline and closing of milestones - `v1.1` to `v1.4`.
    - Released `v1.3`.
    - Updated the index.md page (e.g. changing of the icons for CI Status and codecov).

- Reviewing / mentoring contributions:
    - PRs reviewed: [link](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+reviewed-by%3Afanminj)

- Contributions beyond the project team:
    - Bugs reported during PE Dry Run: [link](https://github.com/fanminj/ped/issues)
