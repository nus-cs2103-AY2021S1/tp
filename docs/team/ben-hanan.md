---
layout: page
title: Ben-Hanan's Project Portfolio Page
---
<img src="../images/ben-hanan.png" width="200px">

[[github](http://github.com/Ben-Hanan)]

## Overview - Project: Eva

Eva is a desktop **HR management application** built in the java language. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Eva was developed in semester 1 of AY20/21 as a team project in the NUS SOC module **CS2103T Software Engineering** and 
is based on the existing java application [Address Book (Level 3)](https://se-education.org/addressbook-level3/).

Together with [my team](https://ay2021s1-cs2103t-w13-1.github.io/tp/AboutUs.html), we morphed Address Book (level 3) 
over a period of 7 weeks to what Eva is today.

Given below are my contributions to the project.

### Code contributed:
You can find all records of the code I contributed [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Ben-Hanan&tabRepo=AY2021S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

### Enhancements Implemented:
- Major Enhancements
    - Implemented leave management functionality
        - What it does: allows user add and delete leave records from a staff, auto updates total number of leave taken.
        - Justification: a leave management system allows users to keep track of their staff leaves.
        - Highlights: added leaves will be marked as "Approved", "Taken" or "On Current Leave" automatically based on the user's local time.
        - Non trivial pull requests: [#51](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/51), [#62](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/62), [#70](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/70), [#215](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/215)
    - Implemented functionality to view individual staff and applicant profiles, and different lists.
        - What it does: allows user to view different pages of the app.
        - Justification: this feature makes the app less cluttered and gives users a better experience
        - Highlights: this allows the app to remember which page the user is on. So the next time the app is launched, it will launch into the appropriate page.
        - Non trivial pull requests: [#105](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/105), [#132](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/132)
    - Customized the GUI and came up with design of the application
        - What it does: separates Eva from address book (level 3) and gives users a better experience.
        - Justification: a good looking user interface is what users expect. It also allows future developers to easily understand the code due to it's modular design.
        - Highlights: Made profile pages modular where each element in the page is a separate component by itself.
        - Non trivial pull requests: [#77](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/77), [#108](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/108)
- Minor Enhancements:
    - Enabled last viewed list to persist after app closure
        - After closing the application, Eva remembers which list the user last viewed and will open to that list the next time Eva is run.

### Contribution to Documentation:
- User Guide
    - added documentation for get started, leaves, list and view
    - added visual aids for the commands
    - improved flow, structure and phrasing of the ug to make it more user friendly
- Developer Guide
    - Updated design changes for Ui, model and common classes
    - Added implementations for the leave system and application panels
    - Updated testing guide
- Non trivial pull requests: [#87](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/87), [#236](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/236), [#251](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/251)

### Contribution to Team-Based Tasks:
- Called for regular meetings and provided a clear agenda
- Divided the labor between group mates
- Consistently gave constructive feedback to members
- Managed v1.4 release
- Non trivial code reviews: [#69](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/69), [#213](https://github.com/AY2021S1-CS2103T-W13-1/tp/pull/213)
