---
layout: page
title: Jerry Ho's Project Portfolio Page
---

## Project: ProductiveNUS

ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

**Code Contributed**

This is the link to the code contributed by me: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=printinghelloworld)

**Enhancements and Features Implemented**

* **Added the Ability to Mark Assignments as Done and Undone** [\#135](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/135), [\#234](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/234)
    * **What it does**: Allows the user to mark (multiple) completed assignments as done and uncompleted assignments as undone.
    * **Justification**: This feature improves the product because a user can now keep track of the assignments that he/she has completed and focus on those that are not yet completed.
    * **Highlights**: This enhancement was not easy as I had to allow users to mark multiple assignments as done. Dealing with multiple indexes can be difficult as it involves more checking and more complex error handling. 
    * **Credits**: Some references were made to Remind feature.

* **Updated Add Assignment Feature** [\#74](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/74), [\#154](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/154)
    * **What**: I helped to update the add assignment feature to ensure that users are able to add assignments with reminders set and/or with a priority level. 
    * **Justification**: This feature improves the product as it adds convenience for the users. For example, instead of first adding the assignment and then set the priority level, users can now do it in just a single add command.
    * **Highlights**: This enhancement was not easy as the 2 additional parameters added were optional (priority has a prefix while remind does not) thus I had figure how to check for their presence and display accurate error messages.
  This was achieved using Regex commands, which was something unfamiliar to me and involved numerous testings.     
  
* **Updated Remind Feature** [\#280](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/280)
    * **What**: I helped to ensure that the error messages are still accurate after we added a feature to allow users to remind multiple assignments.
    * **Justification**: This improves the product as users will now be able to see where the error actually occurs when they are setting reminders to multiple assignments instead of simply being notified that there is an error.
    * **Credits**: This enhancement was first implemented by myself in Done feature and I modified it for Remind feature.  
    
* **Updated Help** [\#146](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/146), [\#233](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/233)
    * **What**: Updated the Help feature to make it informative by providing a comprehensive summary of all the features in our application.

**Team-based tasks contributions**:
  * Removed multiple classes and attributes from the existing classes to facilitate the morphing process of our project from Addressbook to ProductiveNus [\#66](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/66), [\#67](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/67)
  * Renamed Deadline class to Time class as Time is a more suitable name for the parent Task class. [\#230](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/230)
  * Helped to create team meeting notes and facilitate our discussions during our weekly team meeting. 

**Documentation**:
  * User Guide:
    * Added documentation for the features `Done` and `Undone` [\#135](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/135)
    * Added documentation for the features `Help` and `Clear` and for the FAQ section [\#166](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/166), [\#182](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/182)
    * Added Sequence Diagram for `Add` and `Done` features, and Class Diagram for `Task`. [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279), [\#290](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/290)
    
  * Developer Guide:
    * Added implementation details of the `Add assignment` and `Mark assignments as done`  feature. [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279)
    * Modified implementation details for the `Set reminders to assignments` feature (Combined it together with `Mark assignments as done` feature). [\#279](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/279)
    * Contributed to the Appendix section (Manual Testing). [\#290](https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/290)

**Community**:
  * Reported bugs and suggestions for other teams in the class [1](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/223), [2](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/222), [3](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/217), [4](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/219), [5](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/220), [6](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/221), [7](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/216), [8](https://github.com/AY2021S1-CS2103T-W10-1/tp/issues/218)
  * Contributed to forum discussions [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/359)

