# Ng Wei Hong - Project Portfolio

## Project: TrackIt@NUS

This portfolio aims to document the contributions that I have made to the **TrackIt@NUS** project. The Github Link to
 **TrackIt@NUS** can be found [here](https://github.com/AY2021S1-CS2103T-W13-4/tp).
 
## 1. Overview
**TrackIt@NUS** is a desktop application to provide a simple an easy way for students to manage their academic and
 social life. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java. 

## 2. Summary of Contributions
I am in-charge of the **UI/UX** of the **front-end development** of TrackIt@NUS. Reposense Code - [link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=justweihong&tabRepo=AY2021S1-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code)
### 2.1 Design of App 
* With a focus of a user-centric design, we wanted to create a UI that will not only fulfil the solution that
 addresses our target audience, but also a design that keeps users coming back. 
* We have drafted out various mock up designs for v1.2, v1.3 and v1.4 using Figma. 
* Our designs can be viewed [here](https://www.figma.com/file/4CJHXSfo1oevJtZrUQrzbK/CS2103T-W13-4-TrackIt-NUS?node-id=1%3A38)
### 2.2 UI Development
#### 2.2.1 Side Panel (Major Enhancement)
* I have added a [Side Panel](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#layout) feature. This feature allow users to toggle between the different tabs easily. 
* Initially, tabs elements were used in at the top of the views (.fxml) for initial v.1.2.
* However, the design of the tabs vertically does not give users a good experience. 
* After considerations and further discussion with the backend team, we decided to include this for further degree of customisations of the app.
* This component is necessary because users need a section in the app to toggle between the upcoming view, the module view, the contact view and the help view.
#### 2.2.2 Module View (Major Enhancement)
* I have added the [Module Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#moduletabs) feature. 
* This feature is one of the core features, and it allow users to see their lessons, tasks and contacts related to a specific module in one view. 
* This a necessary view for users to view the all their school related stuff for a specific module in a specific way. 
* ithin the Module View, there is TaskPanel, LessonPanel and ContactPanel, all of which are adapted and extended from those in AB3.
#### 2.2.3 Contacts View (Minor Enhancement)
* I have added the [Contact Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#contactstab) feature.
* This feature is necessary, and is not to be confused with the contact panel in the module tab.
* This feature is necessary for users to include contacts that are not taking the same modules, but still relevant to user's school work. 
* More info can be found in the user guide. Similar to the module view, it contains a ContactPanel which is adapted and extended from those in AB3.
### 2.3 Project Management
* Participated actively and discussed critically on the core functionality, as well as the directions for the team for both v1.3 and v1.4.
* Facilitates the progress of Front-end team and request the necessary methods and implementations from the back-end.
* Manages the PRs for the front-end team before merging to master, in which PR will be further reviewed by back-end team.
## 3. Contributions to the User Guide
* Oversees the formatting of the user guide and the numbering of TOC, logo and logo footer
* Included : [Application Layout](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#layout) and [FAQs](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#faq)
* Help Formatted (significantly) : [Introduction](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#introduction), [Quick Start](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#quick-start), [Glossary](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#glossary) and [Command Summary](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#command-summary). 
* Help Formatted : the [Features](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/UserGuide.md#features) sections.
## 4. Contributions to the Developer Guide
* Formatted the numbering of TOC, logo and logo footer
* Included the UI section as part of my contribution to code base [UI Component](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#ui) and [Module Tab](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#module-tab)
* Included Appendix sections for [Use Cases](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#appen-c) and [User Stories](https://github.com/AY2021S1-CS2103T-W13-4/tp/blob/master/docs/DeveloperGuide.md#appen-b).
