---
layout: page
title: Lim Koon Kiat's Project Portfolio Page
---

## Project: TrackPad

TrackPad is a desktop application for planning trips and tracking tourist attractions. The user interacts with it using 
a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to create and delete itineraries.
  * What it does: Allows users to create itineraries to plan their trips.
  * Justification: This feature improves the product significantly by evolving it from a tracker for tourist attractions 
  into a functional travel planner. With it, users can do more than adding attractions and can now plan trips.
  * Highlights: This enhancement affects existing commands and commands to be added in the future, so many commands had to be changed. 
  Additionally, the implementation was challenging as it required modifying existing code and coming up with new code that do not 
  exactly follow what we already had. An in-depth analysis of the various design alternatives that must still conform to 
  the existing implementations was required. Furthermore, after implementation many other new components of the app had 
  to rely on this component, and it was difficult to ensure that everything can be intergrated seamlessly and thus, many 
  changes and further refinements had to be made. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=limkoonkiat&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Helped maintained issue trackers.
  * Helped review and approve PRs.

* **Enhancements to existing features**:
  * Added the location field to attractions [\#75](https://github.com/AY2021S1-CS2103T-T09-3/tp/commit/11dc1737827b1d7360eda72a60a3586cb9d212d0)
  * Wrote additional storage classes to convert the various components in model to and from the json format [\#98](https://github.com/AY2021S1-CS2103T-T09-3/tp/commit/46b84ba7d8e9ae37d340382e1ed3424a774b5653)
  * Wrote additional tests for features to increase coverage [\#198](https://github.com/AY2021S1-CS2103T-T09-3/tp/commit/2b1e2bd533882381aa4f7826c816b5226f4ebd9d), [\#189](https://github.com/AY2021S1-CS2103T-T09-3/tp/commit/1e4ffccec5330d5300d7cfb2c398f570d5a04ee1)

* **Documentation**:
  * User Guide:
    * Added documentation for the [itinerary features](../UserGuide.md#43-itinerary-features-koon-kiat)
  * Developer Guide:
    * Added documentation for section [3.5 Storage](../DeveloperGuide.md#35-storage)
    * Added implementation details for the [Itinerary Model](../DeveloperGuide.md#44-itinerary-model)
    * Added implementation details for the [Add Itinerary Feature](../DeveloperGuide.md#45-add-itinerary-feature)
    * Added additional test cases and manual tests [\#322](https://github.com/AY2021S1-CS2103T-T09-3/tp/commit/63de13d01366d21bf787328a9c6ea7a5252c6542)

* **Community**:
  * Reported bugs and suggestions for other teams during the PE dry run (examples: [1](https://github.com/limkoonkiat/ped/issues/3), [2](https://github.com/limkoonkiat/ped/issues/4), [3](https://github.com/limkoonkiat/ped/issues/5))
