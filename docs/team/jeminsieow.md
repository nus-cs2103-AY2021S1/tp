---
layout: page
title: Sieow Je Min's Project Portfolio Page
---

## Project: FixMyAbs

FixMyAbs is a desktop app for managing exercises, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jeminsieow)

* **Core features**: 
    * Added the Log model (Pull request [\#40](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/40), [\#69](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/69))
        * What it does: It serves as a base to allow the user to create, edit, and delete exercises.
        * Justification: This feature allows the user to create their own exercises which is more user friendly.
        * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required using the current date and time in recording the Logs. This led to challenging design decisions in allowing JUnit tests to run well.
    * Added the ability to delete exercises (Pull request [\#84](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/84))
        * What it does: allows the user to delete exercises from the exercise list one at a time.
        * Justification: This feature improves usability, as user will be able to remove unwanted exercises, making the list more relevant to them.

* **Enhancements to existing features**:
    * Added placeholders in the empty command box for better user experience (Pull request [\#106](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/106))
    * Refactored many sections of the original AddressBook to LogBook.

* **Documentation**:
    * User Guide:
        * Added description of FixMyAbs (Pull request [\#175](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/175)
    * Developer Guide (Pull request [\#15](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/15), [\#23](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/23), [\#30](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/30)):
        * Added implementation details and diagrams for the `add` feature. 
        * Added implementation details and diagrams for the `addex` feature.
        * Added implementation details and diagrams for the `find` feature.
        * Added target user profile and user stories.
        * Added value proposition.
        * Added use cases.
        * Added non-functional requirements.
    
* **Testing**:
    * Wrote tests for all command parsers (Pull request [\#64](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/64))
    * Edited the testutil package to meet new requirements of FixMyAbs (Pull request [\#68](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/68))
    
* **Contributions to team-based tasks**:
    * Collaborated and designed the UI/UX of the FixMyAbs GUI.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/61)
