---
layout: page
title: Lim Hai Shan's Project Portfolio Page
---

## Project: Wanderlust

WanderLust is a desktop travel planner application. It allows users to plan their trips in a structured and efficient manner.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

## Summary of Contributions

- Code contributed: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=underthehai)

* **Major Enhancements**:
    - Modified **Edit Feature** from AB3 to Wanderlust
        - What it does: The command modifies object's parameters.
        - Justifications: As a user, they need to be able to modify and update old details easily. There is a variety of parameters that
        can be edited. The command will validate parameters provided by the user according to specified type of object 
        before editing.
        - Highlights: 
            - The feature is available for all 4 different types of objects with Wanderlust (`TravelPlan`, `Activity`, `Accommodation`, `Friend`)
        - Relevant pull requests: [\#96](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/96), [\#101](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/101)
        
    - Modified **Find Feature** from AB3 to Wanderlust
        - What it does: The command show a list of object whose name matches the keyword provided.
        - Justification: Within the travelplanner, the user needs to be able to find key items quickly. `Name` can help to identity specific objects. User can have an easier time finding object they are interested in.
        - Highlights: 
            - The feature is available for the 3 travelplan objects (`Activity`, `Accommodation`, `Friend`)
            - The feature will display a list of object that matches the keyword.
        - Relevant pull requests: [\#137](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/137)
        
        
        <div style="page-break-after: always;"></div>

* **Minor Enhancements**:
    - Implemented `Friend` in model [\#54](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/54)
    - What it does: Allows the user to add a person that contains details about the passport, mobile and name.
    - Justifications: The user may be traveling in groups. Hence, there is a need to keep a list of contact.
    
* **Testing, Bug Fixing & Defensive Programming**:
    - Added Test Cases for EditCommand and FindCommand [\#109](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/109), [\#137](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/137)
    - Added Defensive code to `EditCommand` and `FindCommand` classes [\#178](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/178), [\#180](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/180)
    - Fixed Bugs found in PE [\#244](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/244)

* **Project management**:
  * Attend weekly meetings
  * Take down notes in shared team project document
  * Manage milestone

* **Enhancements to existing features**:
  * Updated the `edit` and `find` features from AB3 to fit in Wanderlust [\#101](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/101), [\#137](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/137)
  * Wrote test cases and defensive code for `edit` and `find` features with test coverage 90% and 96% respectively [\#137](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/137), [\#109](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/109)
  * Add test cases for `friend` class with test coverage 91% [\#104](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/104)
  
* **Documentation**:
  * User Guide:
    * Add documentation for quick start, tutorial guide section and updated directories section [\#244](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/244)
    * Update existing documentation of feature `clear` and `edit`: [\#244](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/244)
    * Add documentation for `edit` feature [\#96](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/96)
    * Add images to show the UI
    
  * Developer Guide:  
    * Added implementation details of the `edit` feature. [\#256](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/256)
    * Add UML diagram for Edit feature[\#145](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/145)
    * Add tests cases for Manual Testing Portion [\#278](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/278)
    * Add 7 use cases and glossary to DG [\#45](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/45)
    * Remove AB3 documentation [\#146](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/146)
    
* **Community**:
  * Total PRs reviewed: 16
  * PRs reviewed (with non-trivial review comments):  [\#99](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/99)
  * Reported bugs and suggestions for other teams in the class [Reported 11 Bugs in PED](https://github.com/underthehai/ped/issues)
