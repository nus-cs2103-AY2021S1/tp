---
layout: page
title: Preston Toh's Project Portfolio Page
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

**New Feature:** Added the ability to create accounts and login into MediBook.
* What it does: Allows the user to create an account linked with their MCR, which is the identification number for
a doctor's medical license and login to the MediBook system using that account.
* Justification: Since medical data is considered private information and not everyone should have access to it,
the implementation of a login feature ensures that only doctors and medical staff can access the system and the
private information inside.
* Highlights: This feature required the implementation of multiple UI elements, `LoginWindow` and `CreateAccountWindow`.
It also required an `Account` class and `UserAccountsList` as a list of accounts to check against when logging in.
This implementation was challenging as it required learning and understanding how JavaFX interacts with the program and
adding a way to store account information.

**New Feature:** Added the ability to access a patient's profile, which shows all their information.
* What it does: Allows the user to access a patient's profile, which shows all of a patient's information along with
medical notes pertaining to the patient.

<div style="page-break-after: always;"></div>

* Justification: This feature improves the usability of the product. Since each patient has many fields of information
along with plenty of medical notes, having all of the information 
displayed while viewing the list of patients will make
it cluttered and difficult to use.
* Highlights: This feature required a command, which is parsed by the logic manager to also interact with the UI element
of the application. This was challenging as it required changing how the CommandResult worked, so that when the
MainWindow parses the CommandResult, it knows if it should switch the regions shown on the UI.

**New Feature:** Reworked the patient model such that some fields can be optional when creating a new patient.
* What it does: Allows the user to create a new patient with only the compulsory fields of `IC, Name, Date of Birth and
Phone Number`.
* Justification: As not all fields may be available to the user at the point of patient creation, fields that are not
important for identifying a patient are made optional and can be empty.
* Highlights: This feature required changing how `AddCommand` and `EditCommand` interacted with the `Patient` class.
This was challenging as the use of `Optionals` had to be implemented throughout the commands and how they are parsed.

**Code Contributed:** [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=prestontyr)

**Project management**:
* Ensured milestones set were met by project team members.
* Closed issues that were completed or issues that we decided not to resolve on GitHub.

**Enhancements to Existing Features:**
* Wrote additional test cases for existing features. (Pull requests [\#76](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/76))
* Fixed bugs in the product before each major release. (Pull requests [\#150](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/150),
[\#151](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/151), [\#159](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/159),
[\#167](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/167), [\#207](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/207),
[\#211](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/211), [\#224](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/224),
[\#244](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/244))

**Documentation:**
* User Guide:
    * Added documentation for logging in and creating account features. (Pull request [\#152](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/152))
    * Added documentation for logout feature. (Pull request [\#242](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/242))
    * Added documentation for `edit` feature. (Pull request [\#118](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/118))
    
<div style="page-break-after: always;"></div>
    
* Developer's Guide:
    * Added implementation details for creating accounts and logging in. (Pull requests [\#152](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/103),
    [\#242]((https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/242)))
    * Added details for optional fields. (Pull request [\#103](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/103))
    * Added sequence diagram for accessing patient's profile. (Pull request [\#231](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/231))

**Community:**
* PRs reviewed and [approved](https://github.com/AY2021S1-CS2103T-F13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3APrestonTYR+)
* Reported bugs and suggestions for other teams in the class (Examples: [1](https://github.com/PrestonTYR/ped/issues/1),
[2](https://github.com/PrestonTYR/ped/issues/2), [3](https://github.com/PrestonTYR/ped/issues/3))
