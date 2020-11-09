---
layout: page
title: Joven Heng's Project Portfolio Page
---

## Project: Insurance4Insurance

Insurance4Insurance (I4I) is a desktop app for insurance agents to manage clients.  
It is optimized for use via a CLI, while still having the benefits of a GUI. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Priority Feature
  * What it does: The priority feature serves a dual purpose, which is to allow an agent to quickly identify the priorities of different clients visually and which section of the 
  client list one is on. 
  * Justification: Insurance agents would have to prioritise different clients based on real world factors such as potential to purchase products or
  to provide leads for the agent. This feature would be a quick way for the agent to quickly identify different clients without having to sieve through
  chunks of texts from each client. This is extremely useful when the agent would be doing cold calls and can easily sieve through a large number of
  potential clients, to only select those that are the most likely to give him leads. 
  * Highlights: <br>
  Although the idea of the priority feature seemed simple at first glance - change color for clients with different priorities, there were a few challenges that came
  with it. 
    * Firstly, there was the user experience side where changing the entire person card would make it less professional and overbearing for the user. There were concerns that simply changing the color of the person
    card will be quite blinding since there would be multiple colors due to multiple priority cards, but at the same time, if a small indicator or text color were to be changed, it may not be obvious to the user 
    that the priority was changed. Hence, I decided on this rectangular implementation.
    * Secondly, the actual implementation involved tweaking the fxml files in personcard. It was not as simple as just adding a single rectangle into the personcard field or a list. 
    This is because the rectangle is immutable and would not change size based on the size of the GUI. There were many workarounds for this which involved implementing a new rectangle
    class. But I realised that one can just extend the personcard with a region followed by a Hbox, and coloring the Hbox. This ensures that the priority indicator rectangle would change 
    size based on the size of the GUI. 
    * Lastly, I wanted to tie this in with another feature that my teammate did since our UI still looked like the basic AB3 UI. 
    Hence, I decided to change the color of the priority indicator when we are in a different archived list, making it easier for one to realise that they are in a different list intuitively. 
  * Credit: Idea for using a region field to ensure that the size of the rectangles do not change even if the app size is scaled up. [Link](https://stackoverflow.com/questions/39626621/automatic-resizing-of-rectangles-in-a-pane-using-java-fx-8)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=t16&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=Joven-Heng&zR=AY2021S1-CS2103-T16-2%2Ftp%5Bmaster%5D&zACS=272.1138211382114&zS=2020-08-14&zFS=t16&zU=2020-11-05&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Enhancements to existing features**: Note Feature
  * What it does: The note feature allows an agent to add short notes regarding a user when the agent is adding a client to the clientlist. 
  * Justification: As insurance agents would have to deal with many clients over the course of just one day, the notes feature allows the agent to quickly add small notes
      to clients that the agent has added.
      
* **Team Task**: Morphed AB3 to I4I
  * What it does: Changed the names and app icon of the existing AB3 class and methods to I4I.
  * Justification: To give a personalised brand for the app and make it different from the AB3. 
  * Highlights: Changed all references of AddressBook/AB3 to ClientList/I4I in all instances of the code, class names and all documentation except for the tutorials.
  * Credits: flaticon for the (icon)[https://www.flaticon.com/free-icon/family_3076656]

* **Documentation**:
  * User Guide:
    * Added documentation for the following features: `Priority` and `add`
    * Did cosmetic tweaks to existing documentation of the following features:`clear` and `exit`
    
  * Developer Guide:
    * Added implementation details and manual testing of the `priority` feature.
    * Added manual testing details for the `add`, `exit` and `clear` commands.
    * Tweaked the logic and UI design diagram to match our current project.
    * Did cosmetic tweaks on the sections of Documentation, logging, testing, configuration, dev-ops to ensure they fit our project.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#96](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/96), [#160](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/160) [#194](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/194)
  * Contributed to forum discussions [Automated Text UI testing issues](https://github.com/nus-cs2103-AY2021S1/forum/issues/7), [Helped to fix error regarding Smoke testing](https://github.com/nus-cs2103-AY2021S1/forum/issues/265)
  * Reported bugs and suggestions for other teams in the class (Done in the Mock Practical Exam for F09-1): [#136](https://github.com/AY2021S1-CS2103-F09-1/tp/issues/136) [#135](https://github.com/AY2021S1-CS2103-F09-1/tp/issues/135)
  [#141](https://github.com/AY2021S1-CS2103-F09-1/tp/issues/141)

