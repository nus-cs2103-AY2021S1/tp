# Tiffany Chan - Project Portfolio

## Project: TrackIt@NUS

This portfolio aims to document the contributions that I have made to the **TrackIt@NUS** project. The Github Link to
 **TrackIt@NUS** can be found [here](https://github.com/AY2021S1-CS2103T-W13-4/tp).
 
## 1. Overview
**TrackIt@NUS** is a desktop application to provide a simple an easy way for students to manage their academic and
 social life. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

## 2. Summary of Contributions

### 2.1 Code Contributed - RepoSense [link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nottiffchan&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### 2.2 Design of App 
* We recognised the importance of the layout and aesthetics of the interface for a user and put an emphasis on being able to complement our back-end methods with a UI that would facilitate user flow and maximise user experience using our app.
* Throughout v1.2, v1.3 and v1.4, we have constantly revised and updated the interface design in a way that suits the evolution of the functionalities.
* Our designs (on Figma) can be viewed [here](https://www.figma.com/file/4CJHXSfo1oevJtZrUQrzbK/CS2103T-W13-4-TrackIt-NUS?node-id=1%3A38)

### 2.3 UI Development
 
#### 2.3.1 Upcoming Tab (Major Enhancement)
* I have added a [Upcoming Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#upcomingtab) feature. 
* This is a core feature, and the main default face of our application. It shows a calendar style view of all the user's tasks and lessons, sorted by date.
* This includes the "Overdue" and "Future" sections as well, which houses the tasks that lie before and after the scope of the current week.
* I introduced a few new classes to achieve this, namely the `UpcomingSection`, `UpcomingSectionCard`, `UpcomingSectionDayCard`, `UpcomingLessonCard` (which extends `LessonCard`), `UpcomingTaskCard` (which extends `TaskCard`) and `OverdueFutureTaskCard` (which also extends `TaskCard`).


#### 2.3.2 Help Tab (Major Enhancement)
* I have added the [Help Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#helptab) feature. 
* I redesigned the entire Help Tab from the default AB3 help window, opting to use a tab rather than a pop-up as it complements the functionalities of our app more.
* This required redoing this aspect and populating the Help Panel.
* I opted to use a table instead of just a list or just the link to our UG, to display a summary of the app's commands.

#### 2.3.3 Overall Populating of Data to the Relevant Tabs
* Linked the back-end methods to the various tabs we had
* Fixed merge conflicts in merging together the front-end and back-end codebase

### 2.4 Project Management
* Actively voiced my opinions and gave suggestions as well as constructive feedback on the core functionalities of the application.
* Requested the necessary methods and implementations from the back-end in order to do up the interface.
* Managed and reviewed PRs for the front-end developments.

## 3. Contributions to the User Guide

* Explained and provided writeups for the [Application Layout](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#layout) section, namely the Upcoming, Contacts, Module and Help tab.
* Did the [Format and Usage](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#about) section, including tables and relevant detailed explanations for how to read the User Guide.
* The [Contacts](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#contact) section.
* Overall reformatting of the entire User Guide.

## 4. Contributions to the Developer Guide
* Included the UI component as part of my contribution to code base [UI Component](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#ui) and [Upcoming Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#module-tab).
* Did a UML class diagram and a UML sequence diagram as part of the [Upcoming Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#module-tab) elaboration, it being a hefty component of the app.
* [Use Cases](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#appen-c)
* [User Stories](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#appen-b)
* Overall re-formatting of the entire Developer Guide.

## 5. Review/mentoring contributions:
* Worked closely with Wei Hong for the implementation of the UI segment where we consistently reviewed and gave detailed feedback on each other's code and PRs
