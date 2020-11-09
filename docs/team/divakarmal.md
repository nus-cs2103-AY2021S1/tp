---
layout: page
title: Divakar Malhotra's Project Portfolio Page
---

## Project: MediBook

MediBook is a desktop app for managing patients, optimised for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you are a doctor or an administrative staff in a
hospital or clinic and can type fast, MediBook can help you keep track of patients faster than traditional apps.

MediBook contains a central database of patients and supports operations to add, delete and edit patients. The
app keeps track of patient's personal details, `IC Number, Name, Date of Birth, Phone Number, Email Address`
medical details, `Height, Weight, BMI, BloodType`. 

MediBook also supports operations to add, delete and edit medical notes for patients. Each note contains a date,
doctor and the contents of the note.

Given below are my contributions to the project.

**New Feature:** Changed list view to table view for main page.
* What it does: Allows the user to view patient details in a consolidated way.
* Justification: Since we added many new fields to the Patient object, a table was a better alternative as compared to a list view of individual Patient cards.
* Highlights: This feature required the implementation of many UI features. I made use of JavaFx `TableView` class. Styling of the table was done using a css file.
The table was populated by an ObservableList of Patients.

**New Feature:** Added height, weight and BMI charts for patient profile
* What it does: Allows the user to view line graphs of a patient's height, weight and BMI.
* Justification: This feature serves as one of the differentiating feature when compared to other applications for managing patients.
The charts provide an easy visual way to track a patient's progress over time.
* Highlights: This feature required the use of JavaFx `LineChart` class. Each patient has 2 HashMaps which store their height and weight records. 
These are then used to plot the points on the graph. The BMI chart is generated from the height and weight records. A BMI data point is only plotted
if we have both the height and the weight on a particular date.

**New Feature:** Added Blood type field for patients.
* What it does: Allows the user to store patient's blood type.
* Justification: Blood type is an important medical detail that might be required in case of an emergency.
* Highlights: This feature required changing Patient class to store blood types. `BloodTypeEnum` was used to enum the blood types.
New test cases for testing this additional field were added and existing test cases were modified.

**Code Contributed:** [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=divakarmal)

**Project management**:
* Ensured proper documentation was done for all features and enhancements.
* Closed issues that were completed or issues that we decided not to resolve on GitHub.

**Enhancements to Existing Features:**
* Refactored `Person` to `Patient`.
* Refactored `AddressBook` to `MediBook`.

**Documentation:**
* User Guide:
    * Added documentation for charts feature.
    * Added instruction for valid input formats.
    * Added screenshots in User Guide.
    
* Developer Guide
    * Added documentation for UI segment.
    * Added documentation for charts.

**Community:**
* PRs reviewed and [approved](https://github.com/AY2021S1-CS2103T-F13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Adivakarmal+)
