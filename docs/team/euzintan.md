---
layout: page
title: Tan Eu Zin's Project Portfolio Page
---

## Project: Trackr

### Project Overview
Trackr is an application for teaching assistants (TAs) who prefer to use a desktop application for managing their
student records. It is uses a Command Line Interface (CLI), while still retaining the benefits of a Graphical User
Interface (GUI). 

### Summary of Contributions

- Code contributed: [tP Code Dashboard](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=euzintan&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&until=2020-11-09)

- **Class Implementation**: Designed and Implemented TutorialGroup class and all other classes required for its implementation.
    -   Ensured TutorialGroup class works well with Module and Students.
    -   Implemented UniqueTutorialGroupList class to ensure Tutorial Group works with Trackr's storage.

- **New Feature**: Implemented all Tutorial Group related features, including addTG, deleteTG, editTG, findTG and viewStudent.
This includes the Parsers for each of the commands as well.
    - What it does: Allows TAs to add different Tutorial Groups to different Modules
    - Justifications: TAs could be teaching multiple Tutorial Groups across different Modules. This feature helps them
    to keep their Students organised.
    - Highlights: This feature is fundamental to the functionality of Trackr. It was a challenge because its contained in
    the Module class and stores the Student class which added a layer of complexity when commands made additions, 
    deletions or changes.

- **Testing**: 
    - Wrote all test cases for all classes related to Tutorial Groups, including all parsers and commands.
    - Wrote test cases for UniqueTutorialGroupList.
    
- **Others**:
    - Fixes all bugs to do with Tutorial Group surfaced by PE-D
    - All my pull requests: [Pull Requests](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+author%3A%40me+is%3Aclosed)

**Documentation**:
- User Guide:
    - Wrote the section for Tutorial Group Features
    - Created command summary for Tutorial Group Features
    - Took screenshots for Tutorial Group Features
- Developer Guide:
    - Created all activity diagrams for Add, Delete, Edit, Find, List and View Commands
    - Created Class Diagrams for Logic, Model and UI classes
    
- **Team-based Tasks**:
      - Review and merge PRs
      - Debugging
    
- **Beyond Team**:
      - Helped to surface bugs for another team. [Issues](https://github.com/euzintan/ped/tree/main/files)
