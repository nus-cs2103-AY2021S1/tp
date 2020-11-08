---
layout: page
title: Juan Davin Lie's Project Portfolio Page
---

## Project: Trackr

### Project Overview
Trackr is an application that is made specially for Teaching Assistants(TAs) to manage and analyze their student records.
It takes in user input in the form of Command Line Interface(CLI) and automatically stores it in an organized manner.
Not only for storage, Trackr can also be used by TAs to do analytics on their students' performances thus making it 
easier for them to cater to their students' needs. It currently has over 10kLoC

### Summary of Contributions
Code contributed: [tp Code Dashboard](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=juandavinlie&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

Given below is a summary of my contributions.

* **New Feature**: Created the Module class along with its commands such as add, edit, delete, find and list.
  * What it does: Allows TAs to add modules they are teaching.
  * Justification: This feature helps TAs to organize their student records in an organized manner. Instead of having one list of all students, TAs can split students into different modules. This way, it is easier to manage the records.
  * Highlights: This feature is a foundational block of Trackr as Trackr's default view is the module view. Users can then "zoom" into one Module to get more information on their students. The implementation was challenging as it did not exist before and is different from the already implemented classes.

* **New Feature**: Added the participationBelow and attendanceBelow commands.
  * What it does: Allows TAs to filter their students based on their participation and attendance score.
  * Justification: This feature provides TAs with the relevant analytics on their students so they would know which students need more of their help and support.
  * Highlights: This feature is crucial to Trackr's mission which is to help TAs to analyze their students better.

* **Other Enhancements**: 
  * Engineered the mechanisms of switching between the different views (Module, Tutorial Group, Students) Trackr has.
  * Engineered the way Trackr stores all data and how they communicate to one another.
  * Added tests for classes.

* **Documentation**:
  * User Guide:
    * Wrote the section for Module Features and added a command summary table for Module Features
    * Wrote the section for universal commands such as 'clear' and 'exit'.
  * Developer Guide:
    * Wrote the user stories table.

* **Team-based Tasks**:
  * Review PRs. [Example](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+reviewed-by%3Ajuandavinlie+)
  * Debugging

* **Beyond Team**:
  * Helped to notify bugs for another team. [Issues](https://github.com/juandavinlie/ped/tree/main/files)
