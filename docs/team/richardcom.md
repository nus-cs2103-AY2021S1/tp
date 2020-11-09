---
layout: page
title: Liu Yangming's Project Portfolio Page
---
## Project: IntelliBrary

IntelliBrary is a desktop library management application used for library administrators to manage books. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and uses JavaFX for the GUI. Listed below are my contributions to this project.

* **Code contributed(contributed 4800 lines of code)**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=richardcom&tabRepo=AY2021S1-CS2103-F09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
 
 
* **New Features** Created new Storage and UI for problem reports; In addition, implemented Search (`findpr`), edit (`editpr`), and delete (`deletepr`) problem reports.
    * What it does: Stores reports and better UI; Allows the librarian to search for reports that contain keywords, edit the reports, and delete reports.
    * Justification: This feature improves the product significantly because it librarians are now able to manage problem reports besides books management. They can also gather relevant problem reports for more efficient processing.
    * Highlights: The implementation affects the structure of storage and UI of the application. In-depth analysis of the storage structure and UI structure is needed during implementation. 
    
* **New Features** Find the most popular book `findpop`; Randomized selection of books `random`.
    * What it does: 1. allows the librarian to find the most popular book of each category; 2. allows librarians to have randomized selection of books of each categories.
    * Justification: This feature improves the product significantly because it enables librarians to analyze popularity of books of various categories, and conveniently conduct valid random sampling data analysis.

* **New Features** Delete book efficiently by book name or ISBN; Batch deletion of unpopular books which are rarely borrowed. `DeleteBy`
    * What it does: allows the librarian directly delete a book by name or ISBN. Librarians can also delete all unpopular books which are rarely borrowed.
    * Justification: This feature improves the product significantly because it makes it easier for librarians to efficiently delete books without the need to find it first. Librarians
    can also efficiently batch manage and clean up unpopular books.
* **Tests**  Created and added tests for `add`, `deleteBy`, `findpr`, `deletepr`, `editpr`, `random`, and `findpop` commands, which improve the test coverage of our application.
* **Enhancements to existing features**:
<div style="page-break-after: always;"></div>
  * Enhanced the original `add`. Created new Model `Author` and allow `add` to add a Book Model. Set new constraints for book's attributes.(pull requests [#88](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/88))
  * Created new user interface MainWindow and Problem card to accommodate book and report management. Fixed previous UI bugs. (pull requests [#222](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/222) [#224](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/224))
    * Implemented UI component `ProblemReportListCard` and `ProblemReportListPanel`

* **Project management**:
  * Managed the issue tracking of and project task of Milestone v1.4


* **Documentation** (Contributed 1300 lines to documentation according to Reposense):
  * User Guide:
    * Explained the constraints on `add` command and relating attributes.
    * Explained `random`, and `findpop` commands and the usage for target users.
    * Explained `deleteBy` command and its behaviors in different situations.
    * Explained `findpr`, `deletepr`, `editpr` commands.
    * Updated command summary, added visual illustrations, and formatted User Guide styling.
  * Developer Guide:
    * Upadated `Architecture` and `Logic` Component, corresponding Logic Component diagrams, and example sequence diagrams of delete, class diagram for Problem Model.
    * Created sequence diagrams for `add`, `deleteBy`, `findpr`, `editpr`, `random`, and `findpop` commands, and class diagram for Problem class.
    * Explained `add`, `deleteBy`, `findpr`, `deletepr`, `editpr`, `random`, and `findpop` commands.
    * Added six user stories and six use cases.
    * Created Non-Functional Requirements.

* **Contributions to the Team-Based Tasks**:
    * Morph the original AB3 from `Person` to the new `Book`.
    * Managed branch protection.
    * Reset(Git) our master branch to resolve Reposense detection errors.
    * Manage Milestone v1.4 and helped release v1.3 Jar File.
    * Changed footer of the application `intellibrary`

   
* **Review contributions**:
    pull requests review comments
    [PR301](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/301)  , [PR275](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/275) , [PR245](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/245)
    , [PR244](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/244), [PR242](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/242), [PR241](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/241)
    , [PR237](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/237),  [PR235](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/235) , [PR231](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/231)
    , [PR226](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/226) , [PR221](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/221) , [PR226](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/226)
    , [PR158](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/158),  [PR41](https://github.com/AY2021S1-CS2103-F09-3/tp/pull/41).

* **Community contributions**

Helped classmates on forum: [issue359](https://github.com/nus-cs2103-AY2021S1/forum/issues/359), [issue221](https://github.com/nus-cs2103-AY2021S1/forum/issues/221), [issue221](https://github.com/nus-cs2103-AY2021S1/forum/issues/221), [issue107](https://github.com/nus-cs2103-AY2021S1/forum/issues/107), [issue15](https://github.com/nus-cs2103-AY2021S1/forum/issues/15)

Helped other teams identify flaws: [issue195](https://github.com/AY2021S1-CS2103-F10-2/tp/issues/195), [issue194](https://github.com/AY2021S1-CS2103-F10-2/tp/issues/194), [issue191](https://github.com/AY2021S1-CS2103-F10-2/tp/issues/191), , [issue192](https://github.com/AY2021S1-CS2103-F10-2/tp/issues/192), etc.
