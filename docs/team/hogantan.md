---
layout: page
title: Hogan Tan Shao Han's Project Portfolio Page
---

## Project: Reeve

## Overview

Reeve is a desktop companion application for one-on-one private tutors designed to help them better manage their students' academic and administrative needs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Summary of Contributions

* **Major enhancement implemented**: developed the **exam feature** of **Reeve** under academic features.
    * What it does: Allows the user to add/delete examination records to a particular student. It also allows users to view the exam statistics of a particular student.

    * Justification: This feature is crucial for a private tutor as it allows the private tutor to monitor his/her students' academic progress.
    In other words, his/she will be able to track each individual students' academic performance. As a result, the private tutor will be able to not only keep track of which students need more help while also gauging his/her capability as a teacher.

  * Highlights: Since this is a new feature with its own set of commands, the implementation required modifying the base student class which was quite tricky given that most other
  commands were tied to the base student class. Also the addition of opening a new window via a GUI also brings in many considerations to take into account when implementing such as 
  design considerations. 

* **Major enhancement implemented**: developed the **GUI** of **Reeve**.
    * What it does: This feature provides a pleasant interaction between User and **Reeve** by strategically deciding how to display information to users.

    * Justification: In most products, a well-developed UI/UX is imperative in not only landing a good first impression from potential users but it also keeps users interested in using the product.

    * Highlights: This enhancement saw a big change from the original AddressBook3 interface in terms of color choice, placement of the various UI components, etc. 
    Colours of each component in the application were carefully chosen to create a visually pleasing interface. Arrangement of UI components had to be arranged in a way
    that would be easy and intuitive for users to understand and get used to. 

* **Major enhancement implemented**: developed the **toggle feature** of **Reeve**.
    * What it does: Allows the user to toggle in between displaying administrative and academic details of students.

    * Justification: As aforementioned above, a well-developed UI/UX is imperative to users, hence this feature ensures that the
    display of student inform is kept clean and neat without much cluttering.
    
    * Highlights: This enhancement saw a need to consider various UI/UX considerations as well in order to make the feature
    intuitive and convenient for users as well as easy to understand. 
    
* **Minor enhancement implemented**:
    * Updated `add` command which allows users to add students along with their administrative details.

* **Code contribution**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W15-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=StopTakingAllTheNames&tabRepo=AY2021S1-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Set soft and hard deadlines for milestones `v1.1` - `v1.4`.
    * Reminded team of deliverables for each week.
    * Maintained issue tracker.
    * Maintained product website and README. ([\#63](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/63))([\#108](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/108))

* **Enhancements to existing features**:
    * Laid down the structure of the student class. ([\#42](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/42))
    * Updated the add command to accommodate student class. ([\#62](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/62))
    * Updated the GUI color scheme and layout. ([\#76](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/76), [\#81](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/81) and [\#233](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/233))
    * Updated UG and DG structure and flow. ([\#126](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/126) and [\#231](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/231))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add` ([\#82](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/82)), `exam` ([\#117](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/117) and [\#141](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/141)) and `toggle` ([\#134](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/134)).
        * Added introduction and prefaces to various sections ([\#126](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/126))
    * Developer Guide:
        * Added implementation details of the `add` ([\#100](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/100)), `exam` ([\#225](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/225)) `toggle` ([\#225](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/225)) features.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#77](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/77), [\#79](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/79), [\#102](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/102)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/hogantan/ped/issues/3), [2](https://github.com/hogantan/ped/issues/4), [3](https://github.com/hogantan/ped/issues/10))
