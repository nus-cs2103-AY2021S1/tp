---
layout: page
title: Lau Siaw Sam's Project Portfolio Page
---

## Project: GradPad

GradPad is a one-stop solution to **module management** for Computer Science Undergraduates.
GradPad **consolidates** the modules you have taken and **displays** the remaining required modules to
ease your module planning process. A **module searching platform** is also included in GradPad, providing easy
**navigation** of modules.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=silvernitro&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Implemented the back-end logic to fetch and use module data from the NUSMods API.
  * What it does: Allows GradPad to send HTTP requests to the public NUSMods API to retrieve module summaries 
  and specific module information data. Additionally, it also has a script to scrape all CS modules to save locally.
  
  * Justification: This feature is core to GradPad's module searching platform as it allows users to view
  the most up-to-date information, that can't be achieved if GradPad simply used an archive of pre-fetched data.
  Additionally, since GradPad must not be reliant on an internet connection, we also had to scrape and save CS
  module data to a local file and ship that with GradPad releases, so that users can still search for CS modules
  even when offline.
  
  * Highlights: The feature was not trivial to implement as it consists of several major parts: fetching module
   summaries, fetching module information, deserializing JSON data received into Java objects, serializing these
   objects into JSON save files compatible with GradPad, and also making module info available even without an internet
   connection. On the whole, I found it difficult to handle API requests and JSON data in Java and had to read up
   quite a bit on the topic.
   To make it easier for other developers to use NUSMods data,
   I designed the NUSMods package with a high degree of encapsulation and a lean public interface.
   As such, client code elsewhere in GradPad can simply pass in module codes and get back module info using simple
   function calls. There is no need to handle the fetching, storing, or the fall-back to local data (when offline)
   anywhere else in GradPad.
    
  * Credits: Code reuse was minimal. However, the basic logic to make HTTP GET requests is rather boilerplate and may
   seem to be the same in any Java application.

* **New Feature**: Added a feature to allow filtering of modules by tags
    * What it does: Allows users to key in tags that they wish to search for, and view all modules with those tags.
    
    * Justification: This feature allows users to use tags to organize their list of modules and navigate that list
    conveniently when their no. of modules are large.
    
    * Highlights: To implement this feature, I had to create a new predicate to test if a module contained certain tags.
    After that, I had to tweak the existing `find` command to allow it to search by tags too. However, in order to
    retain the original functionality of searching by module code keywords, I had to come up with a way to combine
    both search predicates. In order to make things more extensible, I decided to create a new compound predicate class
    that handles the chaining of multiple predicates.      
    
<div style="page-break-after: always;"></div>
     
* **New Feature**: Added a command to allow users to view all tags in GradPad
    * What it does: Allows users to view a list of all the tags they've added.
    
    * Justification: This feature allows users to check if a certain tag exists. This is especially useful when they
     need to know what tags they can use to filter their list of modules (see feature above).
    
    * Highlights: The implementation of this feature is not as straightforward as it sounds. In the original AB3
    project, a module's tags were implemented by storing a list of `Tag` objects within each `Module`.
    However, this provided no way to easily retrieve all tags without duplicates. To do so, one would
    have to iterate through every module in GradPad, iterate through each module's list of `Tag` objects, filter them,
    and then display them.
    As such, I re-implemented the tags feature by creating a central class to hold a collection of unique tags in
    GradPad. All `Module` objects thus reference unique `Tag` objects within this collection, rather than store their
    own duplicate copies of `Tag`s. Printing out all tags would just involve printing this collection out. This
    also meant that I had to rework the implementation for the addition, editing, and deletion of modules to use
    this new collection of unique tags.

* **Team tasks**:
  * Set up assertions in Gradle.
  * Updated the project's readme page to reflect the project's features and direct all hyperlinks to GradPad pages.
  * Updated GradPad's Jekyll config (remove AB3-specific content).
  * Added feature summary list and command summary table to the user guide.
  
* **Project management**:
  * Managed releases `v1.3-trial` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Refactored the entire AB3 `Person` package and its containing classes to GradPad's `Module` package.
  * Added assertions to the `NUSMods` package.
  * Allow multi-word tags and substring module searches by tags.
  * Add case-insensitive check for duplicate tags when users add a module.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `tag` [\#131](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/131)
    * Added documentation for the feature `edit` [\#64](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/64)
    * Added documentation for the feature `clear` [\#216](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/216)
    * Added documentation to explain the automatic addition of module titles and modular credits 
    [\#83](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/64)
    * Updated documentation for the features `add`, `find`, `edit` 
    [\#131](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/131)
    * Updated introduction to include the purpose of the guide
    [\#113](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/113)
  * Developer Guide:
    * Added architecture details and design considerations for the `NUSMods` component, including a class diagram.
    [\#78](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/78)
    * Added implementation details of the `tags` feature, including sequence diagrams.
    [\#192](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/192)
    * Added implementations details of GradPad's NUSMods integration, including sequence diagrams.
    [\#198](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/198)
    * Added implementation details of the `edit`, `list`, `checkmc`, `required`, `search` command features, including
    all sequence diagrams.
    [\#78](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/78) & 
    [\#42](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/42)
    * Updated the `Storage` component class diagram.
    [\#42](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/42)

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [\#94](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/94), 
  [\#97](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/97), 
  [\#111](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/111),
  [\#133](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/133), 
  [\#169](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/169),
  [\#176](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/176),
  [\#197](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/197)
  * Assisted teammates with any Git queries or issues.
  * Assisted in the program design of adding confirmation prompts when executing `delete` and `clear` commands.
  
* **Community**:
  * Assisted in testing and reporting bugs for another team's product:
  [\#1](https://github.com/Silvernitro/ped/issues/1),
  [\#2](https://github.com/Silvernitro/ped/issues/2),
  [\#3](https://github.com/Silvernitro/ped/issues/3),
  [\#4](https://github.com/Silvernitro/ped/issues/4),
  [\#5](https://github.com/Silvernitro/ped/issues/5),
  [\#6](https://github.com/Silvernitro/ped/issues/6),
  [\#7](https://github.com/Silvernitro/ped/issues/7)
