---
layout: page
title: Robin's Project Portfolio Page
---

## Project: TrackPad

TrackPad is a sleek and simple itinerary planning app, with a preloaded database for Rediscover Singapore Attractions. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Integrated the UI to the app
  * What it does: Allows the user to view the changes and additions they make to the attraction list, itinerary list, as well as the itinerary attraction list.
  * Justification: This feature is extremely important as it gives the user a visual cue to observe what they are changing within the app.
  * Highlights: This feature also allows users to switch between the itinerary list and itinerary attraction list. The UI also updates live as and when the user inputs a new command. This feature was hard to implement as the existing UI feature for attractions list was not compatible with the itinerary and itinerary attractions,
    so I had to create additional classes and methods to accommodate how we accessed itinerary attractions, as there was no single global list of itinerary attractions, unlike attractions and itineraries.


* **New Feature**: Added a mark visited command for attractions
  * What it does: Allows user to easily mark attractions as visited.
  * Justification: This command is expected to be used a lot, as users would want to mark attractions as visited, so they can prioritise visiting attractions they have never been to before.
  * Highlights: Not only is this an additional field for the attraction class, I also added test cases to check for it, as well as ensuring that a separate error message appears if users try to mark an attraction that has already been visited.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=robinho98&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Helped to manage releases `v1.3` and `v1.4` (2 releases) on GitHub
  * Added deadlines for the above two releases on GitHub


* **Enhancements to existing features**:
  * Refactored all codes in Logic to fit our project [\#64](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/64)
  * UI boxes will vary based on the number of filled fields in attraction, itnerary and itinerary attractions [#297](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/297)
  
  
* **Documentation**:
  * User Guide:
    * Numbered all the section headers [\#173](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/173/files)
    * Added documentation for the features `markVisited-attraction`[\#178](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/178/files) and `Getting Started` [\#317](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/317/files)
  * Developer Guide:
    * Numbered all the section headers [\#173](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/173/files)
    * Added implementation details of the `markVisited-attraction` feature [\#343](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/343) and `UI` model. [\#349](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/349)


* **Community**:
  * Reported bugs and suggestions for other teams in the class during PE Dry Run. (examples: [\#3](https://github.com/Robinho98/ped/issues/3), [\#5](https://github.com/Robinho98/ped/issues/5), [\#9](https://github.com/Robinho98/ped/issues/9))
