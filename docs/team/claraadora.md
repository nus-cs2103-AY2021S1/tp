---
layout: page
title: Clara Adora's Project Portfolio Page
---

### Project: Productiv

Productiv is a desktop application for product managers to organise their product-related information. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=claraadora)

##### New features and Enhancements

* **New Feature**: Added the Calendar feature. 
   * What it does: displays all `Deliverable`s and `Meeting`s in a single sorted-by-time list in the dashboard
   * Justification: allows product managers to check their deliverables and meetings together in one place
   * Highlights: This new feature requires a thorough design analysis. Amendments and discussions were done to prevent couplings and preserve cohesiveness
* **New Feature**: Added the auto-sorting of `Deliverable`s and `Meeting`s
   * What it does: auto-sorts `Deliverable`s and `Meeting`s in ascending chronological order.
   * Justification: allows product managers to view their most urgent deliverables and meetings quickly, in neat sorted-by-time lists
   * Highlights: Before settling with the current sorting implementation, an in-depth analysis of the `PriorityQueue` and binary search implementations was done
* **Enhancement**: Designed the current UI/UX of Productiv.
   * What it does: enhances the UI/UX of Productiv to be slick, clean, and intuitive. 
   * Justification: Good UI/UX plays a significant role in user satisfaction and retention. 
   * Highlights: Designing the UI/UX is challenging but essential as Productiv has four modes. 
    Many designs were made and improved upon, before reaching the current clutter-free design.  

##### Project management
* Organised and led some weekly team meetings.
* Fascilitated and delegated tasks.
* Ensured that tasks were completed on time.

##### Contributions to team-based tasks
* Restructured AB3 to allow the addition of the deliverable, meeting, and mode components
[\#38](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/38)
* Created the skeleton of the Meeting component
[\#47](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/47)
* Filled application with synchronized seed data that make sense and reflective of product managers' busy schedule
[\#237](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/237)
* Updated user and developer guides that are not specific to my responsibilities

##### Community
* Reported bugs and suggestions for a team [(link to issues)](https://github.com/claraadora/ped/issues)
* Reviewed PRs with non-trivial comments (examples: 
[\#32](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/32), 
[\#98](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/98), 
[\#108](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/108), 
[\#52](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/52))
* Total PRs reviewed: 20+, Total comments given: 80+ 

##### Documentation
* User Guide
   * Wrote the Introduction section [\#296](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/269/files)
   * Co-wrote the Meeting subsection in the Feature section 
   * Added and edited all diagrams [\#296](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/269/files)
   * Wrote the Command Summary tables [\#194](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/194)
* Developer Guide
   * Updated the Architecture sequence diagram and Model component object diagram [\#242](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/242)
   * Added the implementation details and diagrams of the Calendar and auto-sort feature [\#242](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/242)
