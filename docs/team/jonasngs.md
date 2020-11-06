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

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jonasngs)

* **Enhancement:** Implementation of a feature to find items using multiple search parameters

  * What it does: This feature allows the user to filter contacts, tasks and events using multiple search parameters. This facilitates 
                  an efficient search for the desired item and ensures that module related information and details can be retrieved easily whenever needed.
                  This feature includes the following functionalities: `find contact`, `find task`.
  
  * Justification: This feature enhances the product significantly by allowing users to conduct more accurate and refined searches for module details.
                   Since Cap 5 Buddy functions as a module tracker application, finding the necessary module details is crucial for users
                   who might store large numbers of different contacts, tasks and events in their application.
                   Implementing a feature which allows users to find items using multiple search parameter can narrow down the search results, increasing
                   the probability of users being able to retrieve their intended search result easily.
                   This can improve user experience as users might have to scan through the large number of search results to locate the desired information. 
  
  * Highlights: 
  
    * This feature required in-depth analysis of the underlying implementation of `Contact` and `Task` so that the appropriate 
      search parameters to find matching contacts and tasks with could be identified.
    
    * It is crucial that the implementation of the find feature allows for future extensions to accommodate new additional search parameters.
      This ensures that the feature will be consistent with the implementation of `Contact` and `Task`.
      
    * As users are allowed to provide a variable number of search parameters, the implementation of the feature was slightly complicated 
      as there was a need to ascertain exactly which search parameters were provided by the users before the command can be executed.
      
    * As this feature greatly depended on the use of `Predicate` to find matching contacts and tasks according to the search parameters provided,
      a deeper understanding of the java `Predicate` interface was required.
    
  
  
  * Relevant Pull requests: [\#156](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/156)
                            [\#257](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/257)
                            [\#425](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/425)
                            [\#419](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/419)
                            [\#600](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/600)


* **Enhancement:** Implementation of zoom link management feature

  * What is does: This feature allows users to manage a list of zoom links for various module lessons in each distinct module.
                  This feature includes the following functionalities: `add zoom link`, `edit zoom link`, `delete zoom link`
  
  * Justification: This feature to manage zoom links is essential for an application that functions as a module tracker, especially since zoom links
                   have become one of the most frequently accessed information related to a module due to the shifts to online learning.     
                   As students are enrolled in numerous modules, each with a different set of lessons, the number of zoom links to keep track of can 
                   be substantial. By introducing a mechanism for users to uniquely identify zoom links using module lessons, users can keep track and manage
                   them efficiently. 
  
  * Highlights:
  
    * In-depth analysis of `Module` and its behaviour is required since `ZoomLink` and `ModuleLesson` is coupled with module.
      This is important as it can prevent unexpected regressions when zoom link related commands are modified.
      
    * The implementation of this feature has to be open to future extensions since it would be practical to store 
      other types of module related website links as well. OOP principles have to be considered to determine how zoom links
      should be encapsulated. 
    
  
  * Relevant Pull requests: [\#80](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/80)
                            [\#96](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/96)
                            [\#428](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/428)
                            [\#541](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/541)
                            [\#553](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/553)
                            [\#555](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/555)
  
  
* **Enhancement:** Implement contact list management feature

  * What it does: This feature allows users to keep track of all module related contacts while managing all their contact details efficiently.
                  This feature includes the following functionalities: `add contact`, `delete contact`, `edit contact`, `clear contact`, `list contact`

  * Justification: This feature can enhance the product significantly as module related contacts are one of the most vital module information
                   that users need to access on a regular basis. Additionally, as users can be enrolled in several modules, each having
                   a distinct set of lessons, the number of contacts to track can be overwhelming. Allowing users to manage 
                   different contacts and their respective details, and access them whenever necessary, this can add value to Cap 5 Buddy as a module tracking application.
                   
  * Highlights:
    
    * This feature requires development in all the components of Cap 5 Buddy: `Logic`, `Model`, `Storage` and `Ui`. As such,
      in-depth analysis of how the components interacted with each other was required.
      
    * This feature considers that not all contact details might be available. As such, it would be impractical to specify that all contact
      fields are compulsory when creating contacts. This can encourage more flexibility for users.
      
    * Careful implementation of contact related commands and methods is required as certain contact details of a particular contact
      might be absent. Defensive programming was employed extensively to ensure such cases were handled appropriately 
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


* **Project management**:
  * Set up the GitHub team organization and repository
  * Performed GitHub team repo setup (enable issue tracker, enable GitHub actions, set up product website etc) 
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
  * Managed milestones `V1.1`, `V1.2`, `V1.3`. Closed the milestone when all issues have been completed before the stipulated deadline.

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:

  * User Guide:
    * Updated the class diagram of `Logic` to accurately explain how the `Logic` component of our product was designed.
    * Added documentation for the Contact list management features 
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
  * My implementation of the feature to find items (i.e. `Find Contact` and `Find Task`) was adopted by another teammate
    to implement the `Find Event` feature [\#545](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/545)
  
* **Tools**:


