---
layout: page
title: Tee Kok Siang's Project Portfolio Page
---

## Project: Hall-y

Hall-y is a hall residents' contact management application. It helps to keep track and update the residents' record, so the hall admin staff can easily find the residents based on the saved records.

Given below are my contributions to the project.

* **New Feature**: Adding of student groups for residents
  * What it does: Allows the user to add student groups for residents.
  * Justification: This feature allows users to add student groups for residents to efficiently filter residents by student groups when required.
  * Highlights: This enhancement required adding of student groups to be unique. Hence, I used Set data structure to ensure the uniqueness of student groups. 
  * Credits: The code closely resembles the existing Tag module.
  
* **New Feature**: Listing of student groups
  * What it does: Allows the user to list all student groups.
  * Justification: This feature allows users to have a quick glance of all student groups.
  * Highlights: This enhancement required adding of student groups' name to be unique. Hence, I used Set data structure to ensure the uniqueness of student groups' name. Then, it sorts the student groups' names alphabetically and displays it.
  * Credits: The code utilised Set and Stream to convert the student group Set to a String message. 
  
* **New Feature**: Filtering residents by student groups 
  * What it does: Allows the user to filter residents by student groups.
  * Justification: This feature allows users to filter residents by student groups.
  * Highlights: This enhancement is capable of filtering residents by multiple student groups concurrently. Hence, it gives a more precise filter results. 
  * Credits: The feature is build on top of the existing FindCommand implementation, and the code closely resembles Predicate module.

* **New Feature**: Editing the block, floor and room configurations 
  * What it does: Allows the user to edit the block, floor and room configurations in preferences.json with command.
  * Justification: This feature allows users to set the block, floor and room number range to prevent them for adding residents with invalid block letter, floor and room number.
  * Highlights: This enhancement required a robust validation check for the block, floor and room number range to ensure the block, floor and room number is within the allowed range. 
  * Credits: The feature is build on top of the existing UserPrefs implementation.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=teekoksiang)

* **Project management**: 
  * Managed milestones `v1.3` on Github

* **Enhancements to existing features**: 
  * Added parameter check for commands without parameter [\#221](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/221)

* **Documentation**:
  * User Guide: 
    * Added documentation for the features `list-group`: [\#138](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/138)
    * Added documentation for the features `edit-block-range`, `edit-floor-range`, `edit-room-range`: [\#253](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/253)
  * Developer Guide:
    * Added implementation details for the `list-group` feature: [\#107](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/107)
    * Added implementation details for the `edit-floor-range` feature: [\#256](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/256)
    
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#114](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/114), [\#79](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/79), [\#219](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/219)
  * Reported bugs and suggestions for other teams in the class (examples: [\#6](https://github.com/teekoksiang/ped/issues/6))
