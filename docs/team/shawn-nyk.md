---
layout: page
title: Shawn Ng Yong Kwang's Project Portfolio Page
---

## Project: InternHunter

InternHunter is a CLI-centric desktop application which aids university students in applying for tech internships.
It lets users manage their own customisable collection of companies, internships, internship applications and their 
own user profile, so that they can keep track of internships that they are interested in.

It has a GUI created with JavaFX. It is written in Java, and has about 35k LoC.

### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=shawn-nyk&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Features and enhancements implemented**:
  * Add a company (Pull request [\#143](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/143))
    * What it does: Allows the user to add a company to the app's company list.
    * Highlights: How the company class would be structured was decided upon and it laid the structural foundation for
     the other core data types, namely internships and applications, and how these 3 data types would interact. It 
     required a thorough consideration of design alternatives, regarding how to best model a company in relation to 
     internships and internship applications in an OOP fashion.
  * Delete a company (Pull request [\#149](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/149))
    * What it does: Allows the user to delete a company from the app's company list.
    * Highlights: The implementation of this feature was challenging as it required an in-depth analysis of its design
     and design alternatives since it directly affects the relationship between the company, internship and 
     application data types and their interactive behaviour. As a result, this feature is the most complex of all 
     the deletion-related features in the application since its usage can lead to the deletion of 3 different data 
     types - company, internship and application.
  * Edit a company (Pull request [\#153](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/153))
    * What it does: Allows the user to edit a company from the app's company list.
    * Highlights: The implementation of this feature required sharp insight into the relationship between company and
     internship data types as it required honoring the established relationship that company and internship data types 
     have, since editing a company could potentially affect its internships.
  * View a company (Pull request [\#159](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/159))
    * What it does: Allows the user to view a company from the app's company list in full detail.
  * Find companies (Pull request [\#252](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/252))
    * What it does: Allows the user to find companies by name from the app's company list.
  * List all companies (Pull request [\#252](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/252))
    * What it does: Allows the user to list all companies in the app's company list.
  * Edit an internship (Pull request [\#163](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/163))
    * What it does: Allows the user to edit an internship from the app's internship list.
    * Highlights: The implementation of this feature required keen insight into the relationship between internship and
     application data types as it required honoring the established relationship that internship and application data types 
     have, since editing an internship directly affects an application made to it.
  * Contributed significantly to improving the test coverage (Pull requests [\#329](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/329),
  [\#369](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/369), [\#418](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/418),
  [\#424](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/424))

* **Documentation**:
  * User Guide:
    * Added documentation for all company features, i.e. add, delete, edit, view, find, list. (Pull requests [\#120](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/120), [\#252](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/252), [\#267](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/267))
    * Wrote the introduction and overview. (Pull request [\#252](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/252))
    * Standardized certain formatting aspects. (Pull request [\#266](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/266))
  * Developer Guide:
    * Added implementation details and UML diagrams for the 'delete a company' feature. (Pull request [\#213](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/213))
    * Added implementation details and UML diagrams for the 'Model' component. (Pull request [\#293](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/293))
    * Added the product scope. (Pull request [\#93](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/93))
    * Added the user stories. (Pull requests [\#93](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/93), [\#120](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/120))
    * Added use cases. (Pull requests [\#93](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/93), [\#120](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/120))
    * Standardized certain formatting aspects.

* **Team-based tasks**:
  * Maintaining the issue tracker and managing milestones.
  * Documenting the target user profile and value proposition.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#98](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/98), [\#129](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/129), [\#140](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/140), [\#164](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/164), [\#198](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/198)
  * All other PRs reviewed: [\#78](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/78), 
  [\#81](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/81), [\#85](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/85), 
  [\#88](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/88), [\#134](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/134), 
  [\#142](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/142), [\#144](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/144), 
  [\#157](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/157), [\#215](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/215), 
  [\#251](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/251), [\#256](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/256), 
  [\#257](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/257), [\#260](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/260), 
  [\#356](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/356), [\#371](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/371),
  [\#398](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/398), [\#409](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/409),
  [\#422](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/422)
  * Reported bugs and suggestions for other teams in the class. Bug reports:
  [1](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/240), [2](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/241), 
  [3](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/242), [4](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/243), 
  [5](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/244), [6](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/245), 
  [7](https://github.com/AY2021S1-CS2103T-T09-3/tp/issues/246)
