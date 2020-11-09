---
layout: page
title: Foo Jing Yi's Project Portfolio Page
---

## Project: MediBook

MediBook is a desktop application used for managing patient records, targeted at doctors and administrative staff working at clinics and hospitals.
This app is optimised for use via a Command Line Interface (CLI) whilst still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, MediBook can help you manage your patient records faster than traditional GUI apps. 
This allows you to reduce administrative overhead in your clinic or hospital, thereby also reducing waiting time for your patients.

The user interacts with MediBook using a CLI, and receives feedback from the app through the GUI. MediBook is written in Java, with its GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added a patient profile as a GUI element.
  * What it does: allows the user to view all of a specified patient's information in one window as a patient profile. 
  * Justification: This feature allows the app to display only summarised information in the main window of the app so users can see an interface that is less cluttered, and only view more information when required.
  * Highlights: Implementing this feature required extensive research and experimentation in JavaFX. 
  It was challenging to finalise the sizing of each sub-element of the patient profile since JavaFX elements were reactive to each other.
  Object-oriented programming (OOP) was used in designing the sub-elements as separate classes before containing them in the main `PatientProfile` class to achieve the desired GUI.

* **New Feature**: Added medical details **(allergy, condition and treatment)** as patient fields
  * What it does: allows the user to add a patient's allergies, their diagnosed medical conditions and treatments that patient 
  is currently undergoing to a patient's records.
  * Justification: This feature allows users to tag patients in MediBook with their relevant medical details. Doctors using the app will then be able to
  better prescribe the right treatments for each patient and assess them appropriately based on their medical details.
  * Highlights: This enhancement utilised existing designs of the app that MediBook was based on (since MediBook is a brownfield project). The `Allergy`, `Condition` and `Treatment` classes
  extend from the original `Tag` class. While the existing infrastructure allowed for structured implementation of the new classes and compatibility between the app and these new classes,
  this enhancement was challenging as many files had to be changed to both add the new classes and remove `Tag`s as a patient field.
  
* **New Feature**: Add **date of birth** as a patient field.
  * What it does: allows the user store a patient with their date of birth.
  * Justification: This allows users to know a patient's age which is a critical information and providing healthcare for that patient.
  * Highlights: The implementation of this feature has since been modified, but the original implementation had already provided the desired 
  input and output format of dates.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=foojingyi)

* **Project management**:
  * Helped to keep track of small details to change e.g. variable names, errors and typos in code documentation, decisions to keep in view
  * Reviewed elements related to code quality i.e. variable naming, documentation, application of SLAP

* **Contributions to team-based tasks**
  * Drew and added new logo and icon (Pull requests [\#172](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/172), [\#237](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/237))
  * Reviewed pull requests for refactoring done to fit the product i.e.:
      * Person to Patient (Pull request [\#55](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/55), 5 comments)
      * AddressBook to MediBook(Pull request [\#60](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/60), reviewed >4 times)

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull request [\#212](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/212))
  * Resized GUI elements for more user-friendly viewing (Pull request [\#212](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/212))

* **Documentation**:
  * User Guide (UG):
    * Added documentation for the patient profile under the `access` feature: [\#128](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/128)
    * Added documentation for medical details: [\#173](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/173)
    * Reformatted UG and added details to clarify command validity and requirements: [\#223](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/223)
    * Specified behaviour for adding medical details: [\#223](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/223)
  * Developer Guide (DG):
    * Added implementation details of the patient profile GUI element including diagrams: [\#144](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/144)
    * Added medical details to `Model` components design: [\#238](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/238)

* **Community**:
  * PRs reviewed and [approved](https://github.com/AY2021S1-CS2103T-F13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Afoojingyi+)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/foojingyi/ped/issues/3), [2](https://github.com/foojingyi/ped/issues/4))

