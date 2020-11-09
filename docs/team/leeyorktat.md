---
layout: page
title: York Tat's Project Portfolio Page
---

## Project: TrackPad

TrackPad is a clean and simple itinerary planning app, with a preloaded database for Rediscover Singapore Attractions. 
It is written and compiled in java, hence it's available on all platforms. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added SelectItinerary command
  * What it does: Allows the user to select an itinerary to perform ItineraryAttraction commands.
  * Justification: The SelectItinerary feature is important and expected to be used a lot as it has to be used before any ItineraryAttraction command.
  * Highlights: It required an in-depth analysis of how `Itinerary` and `ItineraryList` classes work. I also added testcases for this command to make
  sure it fits well with the existing implementation of TrackPad.
  
* **New Feature**: Added EditItinerary, FindItinerary, ListItinerary command
  * What it does: Allows the user to select an itinerary to perform ItineraryAttraction commands as well as edit, find and list their itineraries.
  * Justification: These commands allow the user to perform edit, find and list commands for their itineraries.
  * Highlights: It required an in-depth analysis of how `Itinerary` and `ItineraryList` classes work.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=leeyorktat&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false)

* **Project management**:
  * Helped review and approve PRs.

* **Enhancements to existing features**:
  * Refactored all codes in Model to fit our project [\#65](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/65)
  * Added a split pane to the UI so users are able to make either of the panels bigger [\#106](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/106)

* **Documentation**:
  * User Guide:
    * Added Introduction and About This Document sections [\#190](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/190)
    * Added documentation for Attraction features [\#214](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/214)
  * Developer Guide:
    * Added details to the UI section [\#161](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/161)
    * Added implementation details of edit-itinerary, find-itinerary, select-itinerary features [\#350](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/350)

* **Community**:
  * Reported bugs and suggestions for other teams in the class during PE Dry Run. (examples: [\#3](https://github.com/leeyorktat/ped/issues/3), [\#9](https://github.com/leeyorktat/ped/issues/9), [\#10](https://github.com/leeyorktat/ped/issues/10))


