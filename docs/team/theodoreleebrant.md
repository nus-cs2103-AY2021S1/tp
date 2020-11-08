---
layout: page
title: Theodore Leebrant's Project Portfolio Page
---

## Project: TAskmaster

TAskmaster is a desktop app for managing students, optimised for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI). If you are a CS Teaching Assistant (TA) who can type fast, 
TAskmaster can help you track your students' attendance and class participation faster than traditional GUI apps.

This application consists of two major parts:
1. A database of students. Each entry keeps track of the name of the student, 
his unique NUSNET ID, Telegram handle, as well as email address. 
The supported operations for this database of students are:
    * Adding students
    * Editing students
    * Deleting students  
2. Tutorial sessions. TAs can create and delete tutorial sessions. Every tutorial session 
will have an list of student records for that particular session. Each student record consist of
the student's name, NUSNET ID, attendance mark, as well as participation score.
The supported operations for each sessions are:
    * Assigning class participation score
    * Marking attendance of students

Given below are my contributions to the project.
* **New Features**: 
  * Added a Participation Record, which is part of a Student Record in every class session.
    * What it does: Allow students' participation to be scored in every class session.
    * Justification: This feature is essential to the product, 
    as the TA needs to keep track of how active the student has been participating in the class.
    * Highlights: 
        * The decision process in the implementation of the Participation Record has undergone through several
        iterations, with changing specification over time. Having modified the Participation Record according to the 
        ever-changing specification was challenging, as design choices affect the implementation of the corresponding 
        command (see below), as well as tests and documentation. The full elaboration is in the Developer Guide.
        * The choice to separate the Participation Record from the
        Student Record also underwent thorough discussion and ultimately was a good decision, as it has allowed me to
        change the Participation Record without touching other classes due to decoupling.
  * Implemented the corresponding command to score the participation of the student in a session.
    * What it does: Allow TAs to score the participation of students in class.
    * Justification: This is a corresponding change to the above Participation Record, to allow end-users to
    change the Participation Record of the student.
    * Highlights:
        * With the changes of the Participation Record, there needs to be changes made to the command and it took
        several times to finalize the design of the Participation Record, causing an equivalent number of changes in the
        newly-made Score Command.
        * The extension to score all present students had required some creativity in using Java libraries (e.g. Streams)
        to ensure that the command was made without any bugs. This has posed a significant difficulty in the development
        of this feature, because of the sheer number of pitfalls that this command has.
  
  
* **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=theodoreleebrant)

* **Project management**:
  * Set up the GitHub repository and team organization.
  * Included the CI and codecov badge inside the project.
  * Managed the overall code coverage of the project.
  * Created the pull request template which includes a checklist of what needs to be done for an approval.
  * Created bug report and features request template to make it more descriptive.

* **Enhancements to existing features**:
    * Changed the address field to one that supports NUSNET ID.
      * Updated the regular expression validation for the field.
      * Justification: TAs do not need to know students' address, but an NUSNET ID would be useful. Additionally, it
      provides a unique identifier for the student as there is a one-to-one correspondence between a student and his
      NUSNET ID.
    * Changed the phone number to a telegram account.
      * Updated the input validation to one that conforms to 
      [Telegram's API](https://core.telegram.org/method/account.checkUsername).
      * Justification: NUS classes now mainly use the Telegram chatting client instead of text or phone-number based
      chats (e.g. WhatsApp). This needs to have a corresponding change in the input validation, according to what
      the API of Telegram specifies in their website.
    * Refactored Addressbook to Taskmaster.
    * Wrote additional tests for existing features to 
    increase coverage from 59.8% to 64.2% (Pull request [\#105]()).
    * Adapted the file writing behaviour of the original storage to include a new line at end-of-file to conform with 
      checkstyle.
        * Justification: Save files were violating CheckStyle tests, and changing this behaviour resulted in a higher
        quality of life to the developers as this file is created automatically and needs to be pushed to the repository.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `score` and `score all`: [\#109]()
    * Fixed the overall formatting for all tables and headings, as well as broken links: [\#113](), [\#114]()
  * Developer Guide:
    * Added implementation details, diagram, as well as manual test cases of Class Participation Scoring (`score` command)

* **Community**:
  * PRs reviewed and approved [[Link]](https://github.com/AY2021S1-CS2103-F09-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Atheodoreleebrant)
  , as well as reported bugs and suggested features in the team repository [[Link]](https://github.com/AY2021S1-CS2103-F09-1/tp/issues?q=is%3Aissue+author%3A%40me+is%3Aclosed)
  .
  * Contributed to forum discussions 
  (examples: [(Question) 1](https://github.com/nus-cs2103-AY2021S1/forum/issues/12), 
  [(Question) 2](https://github.com/nus-cs2103-AY2021S1/forum/issues/23), 
  [(Question) 3](https://github.com/nus-cs2103-AY2021S1/forum/issues/98), 
  [(Tip) 4](https://github.com/nus-cs2103-AY2021S1/forum/issues/264),
  [(Question) 5](https://github.com/nus-cs2103-AY2021S1/forum/issues/307),
  [(Answering) 6](https://github.com/nus-cs2103-AY2021S1/forum/issues/90))
  * Reported bugs and suggestions for other teams in the class (examples: 
  [1](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/112), 
  [2](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/113), 
  [3](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/117))

* **Tools**:
    * Managed and configured the code coverage tools (using [codecov.io](https://www.codecov.io))
    * Configured the github CI to include the codecov tool after intermittent failure
    * Configured the YAML file for code coverage settings
    
