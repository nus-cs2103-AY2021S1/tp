---
layout: page
title: Jonas Ng's Project Portfolio Page
---

## Project: Cap 5.0 Buddy

## Overview

Cap 5 Buddy is a desktop module tracker application used to centralise key module details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

![Ui](../images/Ui.png)
Figure 1. UI of Cap 5 Buddy

## Summary of Contributions

Given below are my contributions to the project.

* **Enhancement:** Implemented the feature to find items using multiple search parameters

  * **What it does:** This feature allows the user to filter contacts, tasks and events using multiple search parameters. 
                  This includes the following functionalities: `find contact`, `find task`.
  
  * **Justification:** This feature enhances the product significantly as users might store large numbers of different contacts, tasks and events, and locating a specific 
                   information may be tedious. A feature to find items using multiple search parameters allows searches to be more accurate and refined, 
                   and can narrow down the search results. This increases the probability of users being able to retrieve their intended search result easily.
  
  * **Highlights:** 
  
    * This feature required in-depth analysis of the underlying implementation of `Contact` and `Task` so that the appropriate 
      search parameters to find matching contacts and tasks with could be identified.
    
    * The implementation and design of the find feature is open to future extensions to accommodate new additional search parameters.
      This ensures that the feature will be consistent with the implementation of `Contact` and `Task`.
      
    * As users are allowed to provide a variable number of search parameters, defensive programming was employed extensively
      to ascertain exactly which search parameters were provided before the command can be executed.
    
  * Relevant Pull requests: [\#156](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/156)
                            [\#257](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/257)
                            [\#425](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/425)
                            [\#419](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/419)
                            [\#600](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/600)

* **Enhancement:** Implemented zoom link management feature

  * **What is does:** This feature allows users to manage a list of zoom links for various module lessons in each distinct module.
                  This includes the following functionalities: `add zoom link`, `edit zoom link`, `delete zoom link`
  
  * **Justification:** This feature enhances the product significantly since users are enrolled in numerous modules 
                   and might have a substantial number of zoom links to manage and keep track of. These features can allow users to manage zoom links more efficiently.
  
  * **Highlights:** In-depth analysis of `Module` and its behaviour is required since `ZoomLink` and `ModuleLesson` is coupled with module.
                This is important as it can prevent unexpected regressions when zoom link related commands are modified.
 
  * Relevant Pull requests: [\#80](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/80)
                            [\#96](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/96)
                            [\#428](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/428)
                            [\#541](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/541)
                            [\#553](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/553)
                            [\#555](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/555)
  
* **Enhancement:** Implemented contact list management feature

  * **What it does:** This feature allows users to keep track of all module related contacts while managing all their contact details.
                  This feature includes the following functionalities: `add contact`, `delete contact`, `edit contact`, `clear contact`, `list contact`

  * **Justification:** This feature can enhance the product significantly as module related contacts are one of the most vital module information
                   that users need to access on a regular basis. As users are enrolled in numorous lessons, the number of contacts to track can be overwhelming. 
                   
  * **Highlights:**
    
    * This feature requires development in all the components of Cap 5 Buddy: `Logic`, `Model`, `Storage` and `Ui`. As such,
      in-depth analysis of how the components interacted with each other was required.
      
    * This feature considers that it would be impractical to specify all contact fields as compulsory when creating contacts. As such, several contact fields were made optional.
      Since certain contact details of a contact might be absent, defensive programming was employed extensively to ensure such cases were handled appropriately 
      to prevent potential bugs from surfacing.
      
  * Relevant Pull requests: [\#228](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/228)
                            [\#249](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/249)
                            [\#250](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/250)
                            [\#251](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/251)
                            [\#257](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/257)
                            [\#258](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/258)
                            [\#383](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/383)
                            [\#422](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/422)
                            [\#454](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/454) 
                            [\#592](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/592)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jonasngs)

* **Project management**:
  * Set up the GitHub team organization and repository
  * Performed GitHub team repo setup (enable issue tracker, enable GitHub actions, set up product website etc) 
  * Managed the issue tracker on GitHub
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
  * Managed milestones `V1.1`, `V1.2`, `V1.3`. Closed the milestone when all issues have been completed before the stipulated deadline.

* **Enhancements to existing features**:
  * Updated the GUI color scheme for contact card (Pull request [\#582](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/582))
  * Refactored the `Model` package in `Addressbook-level3` to follow the implementation of Cap 5 Buddy (Pull request [\#197](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/197))
  * Wrote additional tests for existing features to increase coverage 
    (Pull requests [\#292](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/292), 
                   [\#295](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/295),
                   [\#571](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/571),
                   [\#598](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/598),
                   [\#621](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/621))

* **Documentation**:

  * User Guide:
    * Added documentation for the following:
      * Contact list management features
      * Zoom link management features 
      * Find task feature
    
  * Developer Guide:
    * Update documentation for the `Logic` component
    * Added implementation details of the contact list management feature, zoom link management features and find task feature.
    * Added user stories, user cases

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#226](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/226), 
                                                     [\#244](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/244), 
                                                     [\#306](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/306), 
                                                     [\#308](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/308),
                                                     [\#388](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/388),
                                                     [\#413](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/413),
                                                     [\#440](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/440),
                                                     [\#574](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/574))
  * Reported bugs and suggestions for other teams in the class: [Reported 7 bugs during the PE dry run](https://github.com/jonasngs/ped/issues)
