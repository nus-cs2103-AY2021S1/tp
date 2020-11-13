---
layout: page
title: Kenneth's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop app that allows doctors to manage patient records and schedule upcoming appointments.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add profile pictures.
  * What it does: Allows the user to add a profile picture to each patient profile.
  * Justification: This feature improves the product significantly because the user can now quickly identify patients using their profile pictures, instead of relying on other forms of identification
                   such as names which can be duplicated.
  * Highlights: User can use their mouse to drag and drop profile pictures onto the patient's profile. This makes it easier for the user to add profile pictures, without having
                to waste time specifying the image's filepath.
  * Credits: 
    1. Manipulation of images in JavaFX: [Tutorialspoint](https://www.tutorialspoint.com/javafx/javafx_images.htm)
    2. Implement drag and drop feature: [JavaCodeGeeks](https://examples.javacodegeeks.com/desktop-java/javafx/event-javafx/javafx-drag-drop-example/),
       [GenuineCoder](https://www.genuinecoder.com/drag-and-drop-in-javafx-html/)
<br/><br/>
* **New Feature**: Added a profile display command.
  * What it does: Displays the patient's profile on a separate window.
  * Justification: Allows user to view the patient's profile and access relevant details such as Blood Type, Address, etc.
  * Highlights: This command displays the patient's profile on a separate popup window, which allows the user to focus on one individual patient. This helps
                to declutter the main window.
  * Credits: 
    1. Popup windows: [Quollwriter](https://quollwriter.wordpress.com/2019/04/08/how-to-create-a-popup-pane-in-javafx/), [VISIT Github](https://github.com/AY1920S1-CS2103T-F12-2/main), [Jewelsea Github](https://gist.github.com/jewelsea/1926196)
    2. Populate separate panel with custom list: [Oracle](https://docs.oracle.com/javafx/2/ui_controls/list-view.htm)
    3. Custom list cell: [StackOverflow](https://stackoverflow.com/questions/25246496/javafx-custom-list-cell-updateitem-being-called-a-lot)
    2. Existing AB3 codebase: [AB3 Github](https://github.com/se-edu/addressbook-level3)
<br/><br/> 
* **New Feature**: Added the ability to add a new visitation log.
  * What it does: Adds a new visit for the specified patient.
  * Justification: This features improves the product significantly because the user can now record visitation details (eg. Diagnosis) and store them in CliniCal.
  * Highlights: User can enter relevant details about the visitation in a separate popup window. This helps to declutter the main window.
  * Credits: 
    1. Existing AB3 codebase: [AB3 Github](https://github.com/se-edu/addressbook-level3)
<br/><br/> 
* **New Feature**: Enhanced the find command to allow partial matching, and included IC number as an additional search parameter
  * What it does: Locates a patient after the user specifies either the patient's name or IC number.
  * Justification: User can quickly locate a specific patient by searching the patient's unique IC number, instead of using names which can be duplicated.
  * Highlights: Enhanced partial matching functionality allows user to type in the first few digits of the patient's IC number, or the first few characters of the patient's name to 
                locate the patient, instead of having to specify the entire IC number or name.
  * Credits: NIL
<br/><br/>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=afroneth)
<br/><br/>
* **Project management**:
  * Managed release `v1.2.1` on GitHub
<br/><br/>
* **Enhancements to existing features**:
  * Assisted in styling of GUI ([\#149](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/149), [\#233](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/233))
  * Wrote additional tests for `ParserUtil`, `CommandResult`, `LogicManager` classes ([\#164](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/164), [\#168](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/168), [\#169](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/169))
<br/><br/>
* **Documentation**:
  * User Guide:
    * Added documentation for `addpicture`, `addvisit` and `profile` features ([\#148](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/148))
    * Improve overall styling ([\#255](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/255))
    * Removed discrepancies on formatting issues ([\#265](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/265))
  * Developer Guide:
    * Added implementation details for the `addpicture`, `addvisit` and `profile` features ([\#83](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/83))
    * Added new use cases for `addpicture` and `profile` features ([\#251](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/251))
    * Added manual testing instructions for `addpicture`, `addvisit` and `profile` features ([\#266](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/266))
    * Added brief preface to each section ([\#141](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/141))
<br/><br/> 
* **Community**:
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/afroneth/ped/issues)
  * PR reviewed (with non-trivial review comments) ([\#252](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/252))
<br/><br/>
* **Tools**:
  * Integrated a third party library (Commons IO) to the project ([\#61](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/61))
