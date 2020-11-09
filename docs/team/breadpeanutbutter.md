---
layout: page
title: Lee Joon Jie's Project Portfolio Page
---

## Project: CANdidates

CANdidates is a powerful contact management desktop App optimised for job recruiters to manage job applicants and job listings.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 25 kLoC.
### Given below are my contributions to the project

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=breadpeanutbutter&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false)

* **New Feature: URL Link Information field**     
  * What it is for: A field used to represent a URL link to a candidate's profile such as his/her LinkedIn or Github page. 
    It is an optional input field for candidates since not all candidates have an online profile page. Internally, it is represented as a UrlLink object.
  * Justification: This Information field allows job recruiters to have easy access to the URL link to profiles and resumes of candidates. 
  * Highlight: This field was the first information field that was optional for candidates and also have no default value. It was a challenge to
    implement as I had to consider how to make it reflect no value while adhering to good coding practices. I wanted to avoid using
    null values for this as using nulls can be "overwhelmingly likely to cause errors" as said by Brian Goetz, Java's language architect. 
    Hence, I decided to utilise Java's Optional class to contain UrlLink in an Optional object for safer results. 
  * Hightlight: It was also a challenge to validate the format of URL links as there are numerous possibilities. I decided to use a third party library, 
    Apache Commons Validator, to validate the format for this.

* **New Feature: Blacklist Information field**           
  * What it is for: A field used to represent the blacklist status of candidates, i.e whether a candidate has been blacklisted.
  * Justification: This Information field allows job recruiters to keep track of candidates who have been blacklisted and have bad records. 
    This field is also useful when used as input for the Sort and Find features. <br>

* **Project management**: 
  * In charge of looking after code quality and ensuring adherence to coding standards such as SLAP and KISS principles
  * Regularly reviewed pull requests to ensure good code quality and coding standards
  * Manage release of Candidates.jar v1.4 <br>
  
<div style="page-break-after: always;"></div>
 
* **Enhancements to existing features**: 
  * Modified AddressBookParser class of AB3 to be able to parse new commands of CANdidates
    (Pull requests [\#86](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/86))
  * Implemented editing of candidates and jobs: `add can` and `add job`
    (Pull requests [\#86](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/86), [\#90](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/90))
  * Implemented deleting of candidates and jobs: `delete can` and `delete job`
    (Pull requests [\#86](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/86), [\#90](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/90))
  * Implemented clearing of candidates and jobs: `clear can` and `clear job`
    (Pull requests [\#86](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/86), [\#90](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/90))
  * Added command summary table to Help Window
    (Pull requests [\#260](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/260))
  * Created several test classes used to test new Information fields and new commands, improved code coverage by %7.80 in one PR
    (Example Pull requests [\#97](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/97), [\#119](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/119), 
    [\#121](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/121))

* **Documentation**:
  * User Guide:
    * Wrote documentation for Editing a candidate: `edit can` and Editing a job: `edit job`
      (Pull requests [\#160](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/160))
    * Wrote documentation for Deleting a candidate: `delete can` and Deleting a job: `delete job`
      (Pull requests [\#160](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/160))
    * Wrote documentation for Clearing all candidate entries: `clear can` and Clearing all job entries: `clear job`
      (Pull requests [\#160](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/160))
    * Wrote documentation for Detecting duplicate entries 
      (Pull requests [\#228](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/228))
    * Wrote Section 2.1 Understanding this Document (Pull requests [\#333](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/333))
    * Wrote some portions of the Introduction, Preface, FAQ, Command Summary and Glossary
      (Example Pull Requests [\#70](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/70), [\#298](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/298), 
      [\#304](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/304), [\#333](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/333)) 
  * Developer Guide:
    * Updated the Design of the Model component including the UML diagram along with team mate [Amelia Tan](https://ay2021s1-cs2103t-t17-3.github.io/tp/team/ameliatjy.html)
      (Pull requests [\#139](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/139))
    * Wrote documentation for implementation of Edit feature including sequence diagram
      (Pull requests [\#133](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/133))
    * Updated Product Scope with Target user profile and Value proposition
      (Pull requests [\#226](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/226))
    * Updated Instructions for Manual Testing with tests for Adding a candidate, 
      Editing a candidate, Detecting duplicate candidates, Deleting a candidate and Clearing all entries
      (Pull requests [\#276](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/276))

* **Community**: 
  * PRs reviewed (with non-trivial review comments): [\#287](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/287), 
    [\#302](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/302)
  * Reported bugs for and gave suggestions to other teams in the class: <br>
    * Team T15-1: [\#270](https://github.com/AY2021S1-CS2103T-T15-1/tp/issues/270), [\#271](https://github.com/AY2021S1-CS2103T-T15-1/tp/issues/271) <br>
    * Team F11-1: Smoke tested their app and read DG and UG to find bugs
    * PE-D testing: [\#1](https://github.com/BreadPeanutButter/ped/issues/1), [\#2](https://github.com/BreadPeanutButter/ped/issues/2), 
    [\#3](https://github.com/BreadPeanutButter/ped/issues/3), [\#6](https://github.com/BreadPeanutButter/ped/issues/6)
  * Contributed to forum discussions: [\#147](https://github.com/nus-cs2103-AY2021S1/forum/issues/147)

* **Tools**: 
  * Integrated a third party library (Apache Commons Validator) to the project used to 
    check the validity of the format of URL links in the UrlLink class ([\#119](https://github.com/AY2021S1-CS2103T-T17-3/tp/pull/119))


