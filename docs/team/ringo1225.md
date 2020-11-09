---
layout: page
title: Cao Qin's Project Portfolio Page
---

## Project: Hospify

Hospify is an application to help hospitals and clinics maintain patients' information and medical records.

Given below are my contributions to the project.

* **New Features**:
  * Added `addApptCommandParser` class. 
    * What it does: helps to parse the user input into a `addApptCommand`.
    
  * Added `addApptCommand` class. 
    * What it does: helps to add an appointment and a short description of that appointment to a specific patient. 
    
  * Added the ability to `delete` patient by both index and nric number. 
        
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ringo1225)


* **Project management**:
  * Manage the issue tracker. 
  * Manage and clean Pull Request tracker.
  * Put up some weekly meeting agendas on team meeting notes.
  * Update User Guide and Developer Guide regularly.
  

* **Enhancements to existing features**:
  * Supported `Nric` as input for appointment commands.
    * What it does: users can then use `Nric` as input to locate the patient who they want to execute `addAppt`, `deleteAppt` or `editAppt` on.
    
  * Included `description` field in  `Appointment` related classes.
      * What it does: makes users be able to keep a description of his/her appointment.
      
  * Included `description` field in  `editAppt` related commands.
    * What it does: makes user to be able to edit the `description` of an appointment.
    
  * Revised the implementation of `delete` method. 
  
  * Revised `Appointment` class and related methods. 
  
  * Included `DuplicatedNricException` and related code. 

<div style="page-break-after: always;"></div>

* **Documentation**: 
  * User Guide:
    * Added `edit` command and explanation.
    * Updated `delete` command and explanation.
    * Updated `clear` command and explanation.
    * Updated product screenshots and included some new screenshots.
    
  * Developer Guide:
    * Added `find` feature and implementation details.    
    * Generated Target user profile, value proposition, and user stories.
    * Generated Use cases.
    * Generated Non-functional requirements.
    * Generated Glossary.
    
    
* **Contributions to team-based tasks**:
    * Maintain issue tracker.
    * Maintain Pull Request tracker.
    * Update User Guide and Developer Guide regularly.
    * Write testcases for some general features and appointment related features. 
    * Refactoring fields and methods to fit our application's context.
    * Arrange the packages in project.
    * Update Aboutus page.
    * Enable assertion detection in Gradle file. 
    * Set up some weekly meeting agendas.
    * Fix some general checkstyle errors.
    

