---
layout: page
title: Jonas Ng's Project Portfolio Page
---

#### Project: CAP 5 Buddy
#### Overview
CAP 5 Buddy is a desktop module tracker application used to centralise key module details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 30 kLoC.
#### Summary of Contributions
Given below are my contributions to the project.
* **Enhancement:** Implemented the feature to find items using multiple search parameters. 
                   This includes the following functionalities: find contact, find task.
  * **What it does:** This feature allows the user to filter contacts, tasks and events using multiple search parameters. 
  * **Justification:** This feature enhances the product significantly as users might store many contacts, tasks and events, and locating specific 
                   information may be tedious. 
  * **Highlights:** This feature required in-depth analysis of the implementation of Contact and Task. As users can provide a variable number of search parameters, defensive programming was employed
      to ascertain exactly which search parameters were provided before the command is executed.
  * Relevant Pull requests: [\#156](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/156)
                            [\#257](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/257)
                            [\#425](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/425)
                            [\#419](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/419)
                            [\#600](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/600)
* **Enhancement:** Implemented zoom link management feature. This includes the following functionalities: add zoom link, edit zoom link, delete zoom link
  * **What is does:** This feature allows users to manage a list of zoom links for various module lessons in each distinct module.           
  * **Justification:** This feature enhances the product significantly since users are enrolled in numerous modules 
                   and might have a substantial number of zoom links to keep track of.
  * **Highlights:** In-depth analysis of Module and its behaviour is required since ZoomLink and ModuleLesson is coupled with module.
                This is important as it prevents unexpected regressions when zoom link commands are modified.
  * Relevant Pull requests: [\#80](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/80)
                            [\#96](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/96)
                            [\#428](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/428)
                            [\#541](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/541)
                            [\#553](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/553)
                            [\#555](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/555)

<div style="page-break-after: always;"></div>        
                      
* **Enhancement:** Implemented contact list management feature. 
                   This feature includes the following functionalities: add contact, delete contact, edit contact, clear contact, list contact
  * **What it does:** This feature allows users to keep track of module related contacts while managing all their contact details.               
  * **Justification:** This feature can enhance the product significantly as module related contacts are one of the most vital module information
                   that users need to access on a regular basis. As users are enrolled in numerous lessons, the number of contacts to track can be overwhelming.                
  * **Highlights:**
    * This feature requires development in all the components of Cap 5 Buddy: `Logic`, `Model`, `Storage` and `Ui`. As such,
      in-depth analysis of how the components interacted with each other was required. Also, it provides flexibility for users 
      as not all contact fields are compulsory when creating contacts.
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
  * Performed GitHub team repo setup (enable issue tracker, set up product website etc) 
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
  * Managed and closed milestones `V1.1`, `V1.2`, `V1.3`. 
* **Enhancements to existing features**:
  * Updated the GUI color scheme for contact card (Pull request [\#582](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/582))
  * Refactored `Model` package to suit the implementation of CAP 5 Buddy (Pull request [\#197](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/197))
  * Wrote additional tests for existing features to increase coverage 
    (Pull requests [\#292](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/292), 
                   [\#295](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/295),
                   [\#571](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/571),
                   [\#598](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/598),
                   [\#621](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/621))
* **Documentation**:
  * User Guide:
    * Added documentation for contact list management features, zoom link management features and find task feature
  * Developer Guide:
    * Update documentation for the `Logic` component under design 
    * Added implementation details of the contact list management feature, zoom link management feature and find task feature.
    * Added user stories, user cases
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#226](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/226), 
                                                     [\#244](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/244), 
                                                     [\#306](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/306), 
                                                     [\#308](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/308),
                                                     [\#388](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/388),
                                                     [\#413](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/413),
                                                     [\#440](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/440))
  * Reported bugs and suggestions for other teams in the class: [Reported 7 bugs during PE dry run](https://github.com/jonasngs/ped/issues)
