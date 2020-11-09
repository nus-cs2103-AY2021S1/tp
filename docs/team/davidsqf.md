---
layout: page
title: Song Qifeng's Project Portfolio Page
---

## Project: IntelliBrary

IntelliBrary is a desktop library management application used for library administrators to manage books. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code Contributed**:

See the link [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=davidsqf&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements Implemented**: 

    * Create a series of Model: Problem:

        * I proposed to create the `Problem` series of features.

        * I created the `Problem` series model, which is parallel to the existing `book`
series model. I wrote 4 models, i.e. `Problem.java`, `Severity.java`, `Description.java`, and `ProblemList.java`,
together they form the main structure of `Problem` model. I also wrote corresponding test code for these models.

    * Add Feature: ReportProblem:

        * I wrote `AddProblemCommand.java`, `AddProblemCommandParser.java` and 
made modification to other existing relevant files. I also wrote corresponding test code for this feature.

    * Add Feature: ViewProblem:

        * I wrote `ViewProblemCommand.java` and made modification to other
existing relevant files. I also wrote corresponding test code for this feature.

    * Add Attribute: Publisher:

        * I wrote the model for `Publisher`, i.e. `Publisher.java`. 

        * I made modifications to a dozen of other existing files.

        * I added test code for the newly added `Publisher` attribute. 

* **Contributions to Documentation**:

    * User Guide:
        * I wrote the sections of `ReportProblem` and `ViewProblem` features.
        * I proofread user guide, spot and fixed hidden problems like unavailable urls, typos, and not up-to-date or not reader-friendly sentences.
        * I updated the out-of-date images.
    * Developer Guide:
        * I was in charge of the `Model` section, where I also drew a class diagram.
        * I drew the sequence diagrams for `ReportProblem` and `ViewProblem`.
        * I wrote the section of `ReportProblem`. I explicitly showed the processing steps and explained why it was implemented
        this way (e.g. have the attribute `severity`) and proposed an alternative (i.e. 
        link problems to books).
        * I wrote the section of `ViewProblem`. I explicitly showed the processing steps.

* **Contributions to Team-Based Tasks**:
    * I set up the Github team organisation and the repository.
    * I proposed the idea of our project, i.e. an app for librarians.
    * I proposed the name of our app, i.e. `IntelliBrary`.
    * I wrapped up 2 milestones and released v1.3.
    * I contributed in documenting the target user profile and user stories.
    * I classified the problems reported from PE dry run into different types so that we could easily attribute each problem to a developer.
    * I made my effort to facilitate the team meetings and I was responsive and constructive during the team meetings.
    
* **Review Contributions**:
    I have reviewed the following PRs: 
    [PR25](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/25)
    [PR26](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/26)
    [PR27](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/27)
    [PR28](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/28)
    [PR30](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/30)
    [PR31](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/31)
    [PR33](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/33)
    [PR126](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/126)
    [PR223](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/223)
    [PR244](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/244)
    [PR265](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/265)
    [PR267](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/267)
    [PR273](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/273)
    [PR280](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/280)
