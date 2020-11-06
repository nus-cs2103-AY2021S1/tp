---
layout: page
title: Anthony Lie's Project Portfolio Page
---

## Project: HelloFile

HelloFile is a file management application created as an extension to AddressBook - Level 3 (AB3),
specifically made for tech savvy CS2103T CS students.
By using HelloFile, students can tag frequently used files/folders with a short nickname, and open their files
with intuitive commands.

Given below are my contributions to the project.

* **New Feature**: Added `show` command
(Pull request [\#88](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/88))
  * What it does: Allows the user to see the file path and label information of a tag.
  * Justification: This feature helps the user to see a specific tag's information if the information is too long for the UI to display it properly.
  * Highlights: This feature needs to find the tag according to the tag's nickname provided by the user. 
  Hence, I implemented `TagNameEqualsKeywordPredicate` to get the specified tag easier, and potentially helps to implement commands that takes in tag's nickname as argument in the future.

* **New Feature**: Added `unlabel` command
(Pull request [\#193](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/193))
  * What it does: Allows the user to delete multiple labels of a certain tag.
  * Justification: This feature improves the product significantly as managing tag's labels is important since labels can be used to open multiple files at the same time.
  * Highlights: This feature is able to take in multiple labels as argument and delete them. This helps the users to save time if a certain tag has a lot of labels that needed to be deleted at the same time.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=anthony6401&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Anthony6401&tabRepo=AY2021S1-CS2103T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Created some GitHub issues and assign them the corresponding labels and milestone.
  * Maintaining team tasks with other members.

* **Enhancements to existing features**:
  * Updated list command to not be able to take in any argument as well as change its command word
  (Pull request [\#122](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/122))
  * Updated storage in order to save the last file path 
  (Pull requests [\#151](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/151), 
  [\#157](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/157))
  * Updated some commands not to change the tag list order after execution
  (Pull request [\#265](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/265))

* **Documentation**:
  * User Guide:
    * Added documentation of expressions with and without `...`
      [\#266](https://github.com/nus-cs2103-AY2021S1/forum/issues/266)
    * Added description for the features `unlabel` and `retag` 
    [\#277](https://github.com/nus-cs2103-AY2021S1/forum/issues/277)
    
  * Developer Guide:
    * Added implementation details of `ShowCommand` and `ListCommand`.
    [\#165](https://github.com/nus-cs2103-AY2021S1/forum/issues/165)

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#91](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/91), 
  [\#120](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/120), 
  [\#158](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/158), 
  [\#162](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/162)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/anthony6401/ped/issues/2),
   [2](https://github.com/anthony6401/ped/issues/4), 
   [3](https://github.com/anthony6401/ped/issues/6))
   
## \[Optional\] Contributions to the User Guide (Extract)
   
Expressions with `...` at the end can be provided any number of times.<br>
e.g. `t/TAG [l/LABEL]...` can be used as `t/TAG`, `t/TAG l/label`, or `t/TAG l/label1 l/label2 l/label3`.
     
**:warning: Warning for multiple expressions**
Expressions without `...` at the end takes the last parameter as the argument when provided with multiple same expressions.<br>
e.g. `tag t/TAG1 t/TAG2` will take `TAG2` as the parameter, ignoring the parameter `TAG1`.
    
## \[Optional\] Contributions to the Developer Guide (Extract)

### Showing a tag's file path: ShowCommand

[ShowCommand](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/ShowCommand.java)
searches the list of Tags stored in `AddressBook` and shows the tag's file path in the `ResultDisplay`.
`CommandException` is thrown if tag is not present.

ShowCommand gets the specified tag by applying `TagNameEqualsKeywordPredicate` that extends from `java.util.function.predicate` to `ObservableList<Tag>`.

### Listing out all the tags: ListCommand

[ListCommand](https://github.com/AY2021S1-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/ListCommand.java)
lists the Tags stored in `AddressBook` and shows them as `TagCard` which is contained in `TagListPanel`.
ListCommand shouldn't take in any argument. `CommandException` will be thrown if the user's input contains an argument.

ListCommand updates the `ObservableList<Tag>` by using `java.util.function.predicate`.
   

