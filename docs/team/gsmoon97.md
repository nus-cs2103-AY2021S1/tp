---
layout: page
title: Moon Geon Sik's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop application that allows doctors to manage patient records and schedule upcoming appointments. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

**New Implementation**: Implemented Appointment.
  * What it does: Appointment stores the details of an upcoming appointment with a patient, including patient name, patient NRIC, appointment time, and appointment duration.
  * Justification: This implementation allows the user to schedule upcoming appointments and prevent any time conflicts. Appointment objects are also integrated with calendar to allow the user to view the weekly schedule.
  * Highlights: This implementation is fundamental for managing patient appointments, which is one of the main features of the application. It required an extensive understanding of the jfxtras library and its components. The implementation too was challenging as Appointment had to be converted to VEvent objects for integration with the calendar feature.
  * Credits:
    1. For understanding of iCalendar and its components : [iCalendar.org](https://icalendar.org)
    2. For implementation of VEventUtil : [Njoy Teaching Assistant's EventUtil](https://github.com/AY1920S1-CS2103T-W13-2/main/blob/master/src/main/java/seedu/address/commons/util/EventUtil.java)

**New Implementation**: Implemented JSON Serialization for Appointment.
  * What it does: Appointment objects are serialized into json file format and clinical.json is deserialized into Appointment objects.
  * Justification: This implementation allows the user to automatically save the appointments in json file format and also to load the previously saved appointments when the application is restarted.
  * Highlights: This implementation is fundamental for saving and loading patient appointments, which is one of the main features of the application. It required an in-depth understanding of JSON serialization.
  
<div style="page-break-after: always;"></div>
    
**New Implementation**: Added new field objects for patients.
  * What it does: New patient field objects allow the user to manage the extensive details of a patient, including NRIC, sex, blood type, and allergies.
  * Justification: This addition improves user's management of patient records because a user can store and view patient details that are essential for consultations.
  * Highlights: This enhancement affects existing and future Patient commands. It required an in-depth understanding of regular expression(regex) to check the validity of the patient fields.
    
**New Implementation**: Implemented the base code for Visit and VisitHistory.
  * What it does: Visit stores the details of a patient's visit, including visit date, patient name, diagnosis, prescription, and comment. VisitHistory stores the list of visits for each patient.
  * Justification: This implementation enhances user's management of patient records since a user can store details of a patient visit and refer to past visits for more accurate diagnosis.
  * Highlights: This implementation is fundamental for managing patient visit records, which is one of the main features of the application.
  * Credits:
    1. For implementation of Visit: [VISIT's VisitReport](https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/model/person/VisitReport.java)
    1. For implementation of VisitHistory: [VISIT's VisitList](https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/model/person/VisitList.java)
    
**New Feature**: Added the ability to edit appointments.
  * What it does: The new feature allows the user to edit details of existing appointments.
  * Justification: This feature improves user's management of appointments because a user can make mistakes in scheduling appointments and the app should provide a convenient way to rectify them.
  * Highlights: This implementation required a careful consideration of design alternatives and validity checking mechanisms as schedule conflicts and addition of overdue appointments had to be prevented, based on the current time.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=gsmoon97)

* **Project management**:
  * Created the `AboutUs` page on product website

* **Enhancements to existing features**:
  * Updated the styling of GUI (Pull requests [\#260](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/260), [\#73](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/73))

<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add`, `edit`, `delete`, and `editappt` [\#235](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/235), [\#165](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/165)
    * Added screenshots for all features `history`, `clearhistory`, `undo`, `redo`, `add`, `addpicture`, `edit`, `delete`, `clear`, `find`, and `list` [\#274](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/274) [\#120](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/120)
    * Made improvements to existing documentation for consistent styling and format [\#253](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/253), [\#140](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/140)
  * Developer Guide:
    * Added documentation for some parts of the Appointment Feature section [\#279](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/279)
    * Added new use cases for the features `add`, `edit`, `delete`, `addappt`, `editappt`, and `deleteappt` [\#288](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/288), [\#96](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/96), [\#35](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/35)
    * Added new instruction for manual testing `add`, `edit`, and `editappt` [\#288](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/288),
    * Updated details of the diagrams `ModelClassDiagram` and `BetterModelClassDiagram` [\#96](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/96)
    * Added an activity diagram `DeleteAppointmentActivityDiagram` and `BetterModelClassDiagram` [\#279](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/279) [\#96](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/96)
    * Made improvements to existing documentation for consistent styling and format [\#288](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/288), [\#140](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/140)

* **Community**:
  * Reported bugs and suggestions for other teams in the class (example: [Practical Exam Dry Run](https://github.com/gsmoon97/ped/issues))
  * PRs reviewed (with non-trivial review comments): [\#234](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/234) [\#252](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/252) [\#67](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/67)
  * Contributed to forum discussions (example: [Class Diagram of the AB3 Model](https://github.com/nus-cs2103-AY2021S1/forum/issues/398))

