---
layout: page
title: Peter Yeh's Project Portfolio Page
---

## Project: TrackPad

TrackPad is a clean and simple desktop application for tracking past itineraries and creating future ones. 
It is written and compiled in java, hence it's available on all platforms. 
The user interacts with it using a CLI, and it has a beautiful GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Maintained `ItineraryAttraction`.
  * What it does: allows the user to create attractions and manage it in their itinerary.
  * Justification: This feature improves the product significantly because it gives the user the ability to 
  add start and end time to the attractions created.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging as 
  it requires writing up and testing multiple approaches to see which solution best fit into the existing implementation of `TrackPad`.


* **New Feature**: Added and maintained `ItineraryAttractionCommands`.
  * What it does: allows the user to add/edit and delete attractions in their itineraries to plan their trips.
  * Justification: This feature improves the product significantly because it allows the user to do manipulations of attractions inside an itinerary.
  * Highlights: This enhancement required an in-depth understanding of how the current `AttractionCommands` work to identify the best design based on the constrains.
  The `ItineraryAttraction` was stored in the `Itinerary`'s `Day` unliked `Attraction` which was stored in `AttractionList`.
  This difference introduced lots of limitations which were challenging and required writing up new commands and
  new test cases due to the different implementations compared to `AttractionCommands`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=peter-yeh)

* **Project management**:
  * Helped maintained issue trackers.
  * Helped review and approve PRs.

<div style="page-break-after: always;"></div>

* **Enhancements to existing features**:
  * Converted email, phone and address from compulsory to optional fields to give users more flexibility and control on the fields they want in their attractions. 
  (Pull requests [\#74](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/74), [\#79](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/79))
  * Enhanced the AB3's find by name feature to find by any fields inside an attraction, allowing a more sophisticated find method. 
  (Pull requests [\#92](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/92), [\#97](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/97), [\#100](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/100)) 
  * Wrote additional tests for enhancements added to maintain test coverage.
  (Pull requests [\#99](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/99), [\#97](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/97/commits/f0ffdfe3378fa3a14bce510b6bc69396cbc4f128))


* **Documentation**:
  * User Guide:
    * Added and maintained the [Itinerary Attraction Features](../UserGuide.md#44-itinerary-attraction-features-yeh-yu-chun).
  * Developer Guide:
    * Added and maintained the [Itinerary Attraction Model](../DeveloperGuide.md#49-itinerary-attraction-model).
    * Added and maintained the [Adding Itinerary Attraction](../DeveloperGuide.md#410-adding-itinerary-attraction).


* **Community**:
  * PRs reviewed: [\#156](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/156), [\#297](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/297), [\#149](https://github.com/AY2021S1-CS2103T-T09-3/tp/pull/149).
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/peter-yeh/ped/issues/6), [2](https://github.com/peter-yeh/ped/issues/10), [3](https://github.com/peter-yeh/ped/issues/7))
