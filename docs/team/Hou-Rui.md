--
layout: page
title: Hou Rui's Project Portfolio Page
---

<img src="images/hou-rui.png" width="100px">

## Project: Eva

Eva is a desktop app for human resource management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Eva can get your human resource management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.


* **New Feature**

- Load script
    - What it does: Load a JavaScript script to dynamically execute Eva commands.
    - Justification: The script should be written in JavaScript. The JavaScript engine is implemented using Oracle Nashorn. Only ES5 syntax is supported.
    - Highlights: Allow users to have full control of Eva and add the features they want.
- Find applicant and staff records
    - What it does: Find the records of applicants or staff by names
    - Justification: The command will bring the user to corresponding GUI panel, saving the time for switching.

* **Code contributed**

[RepoSense Link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=Hou-Rui&zR=AY2021S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&zACS=305.6120031176929&zS=2020-08-14&zFS=&zU=2020-10-30&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

- Write the parser and executing logic for load command
- Rewrite find command to make it usable for `find a-` and `find s-`
- Adapt the storage structure for Eva
- Change unit tests to make them usable for Eva

* **Project management**

- Solve issues or link them to the corresponding PR.
- Reviewed several PRs.

* **Enhancements to existing features**

- Adapt the storage to make it usable for Eva, storing applicant database and staff database respectively.

* **Documentation**

- Write the documentation (User Guide and Developer Guide) for load command.
- Write user guide for the improved find command.
- Rewrite the developer guide and redraw the diagrams for storage structure.
