---
layout: page
title: Wang Jun Hao's Project Portfolio Page
---

## Project: MediBook

MediBook is a **desktop medical records software** targeting **doctors and administrative staffs** in clinics or hospitals to 
help **manage patient details.** It is **optimized for use via a Command Line Interface** (CLI) while 
still having the benefits of a Graphical User Interface (GUI), implemented using JavaFX.

Main highlights of MediBook include:
* Ability to keep track of patients’ administrative and medical details
* Ability to store medical consultation notes for each patient
* Displaying of health metric charts such as `BMI`
* Login accounts for doctors for a more streamlined experience (e.g. auto-fill name and medical IC)

<br/>

**Given below are my contributions to the project.**

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wang-jun-hao)


* **New Feature**: Added the ability to create/edit/delete medical notes for each patient
  * What it does: Allows users to create, edit and delete medical consultation notes belonging to a patient, when logged in as a doctor
  and viewing a patient profile page. Autofills doctor's and patient's information.
  * Justification: This is a fundamental feature of a medical records software. By only allowing these commands when 
  logged in and viewing a patient profile page, the commands themselves can be streamlined as certain information 
  are automatically retrieved.
  * Highlights: This enhancement took significant effort and required the implementation of many underlying classes, such as `Doctor` and
  `MedicalNoteList`, and established a connection between these classes and `Patient`. It required an in-depth design analysis 
  to reduce coupling and avoid cyclic dependency. The auto-filling feature required the implementation of `ModelContext`.


* **New Feature**: Doctor verification when editing or deleting medical notes
  * What it does: Restricts editing and deleting of medical notes to the doctor who authored them.
  * Justification: Medical notes are important sensitive information that affect treatment decisions. The integrity of
  medical notes should be protected. This feature prevents tampering of medical notes by other doctors.
  * Highlights: This feature interacts with login and model context features to check for account match.


* **New Feature**: Display of medical notes in reverse chronological order


* **New Feature**: Added support for `BMI` for patients
  * What it does: Auto computation and storing of a patient's `BMI` from `Height` and `Weight` fields if present.


* **Enhancements to existing features**:
  * Implemented new `Weight` field for `add` feature (Pull requests [\#26](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/26))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addnote`, `editnote` and `deletenote` (Pull requests [\#154](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/154))
    * Added elaboration to introduction of UG (Pull requests [\#154](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/154))
    * Tweaked layout and re-ordered features so that related features are back-to-back (Pull requests [\#154](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/154))
    * Added elaboration on `login` and `edit` feature (Pull requests [\#121](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/121), [\#174](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/174))
  * Developer Guide:
    * Added implementation details of `MedicalNote` and `MedicalNoteList` (Pull requests [\#239](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/239))
    * Added implementation details of `addnote`, `editnote` and `deletenote` features (Pull requests [\#111](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/111), [\#122](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/122), [\#125](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/125), [\#247](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/247))

* **Project management**:
  * Managed releases `v1.2.1` - `v1.4` (3 releases) on GitHub
  * Managed milestones `v1.1` - `v1.4` on GitHub
  * Managed branches on team repo for integration

* **Contributions to team-based tasks**
  * Liaised with tutor on project matters
  * Created platform for team notes
  * Release and milestones management
  * Managed build number, javaFx version and enabled assertions in gradle (Pull requests [\#119](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/119), [\#127](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/127), [\#171](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/171), [\#230](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/230))
  * Updated help command (Pull requests [\#72](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/72))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#42](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/42), [\#56](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/56), [\#74](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/74), [\#75](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/75), [\#152](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/152)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/wang-jun-hao/ped/issues/11), [2](https://github.com/wang-jun-hao/ped/issues/10), [3](https://github.com/wang-jun-hao/ped/issues/8), [4](https://github.com/wang-jun-hao/ped/issues/7))
