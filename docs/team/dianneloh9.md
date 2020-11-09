---
layout: page
title: Dianne Loh Wen Hui's Project Portfolio Page
---  

#### Description of Project:

PropertyFree is a real estate management application meant for property agents to organize their properties and meetings more efficiently.
Other application can keep track of meetings and schedules but there are relatively few applications that help track properties 
and associate them with meetings with clients.  

For our project we hope to make it more streamlined for agents to keep track of their property and have clear information about those properties
and their clients.  

#### My Contributions to PropertyFree  

* **New Feature**: Added the ability to add properties to the property book.
  * What it does: allows the user to add properties to the property book with its relevant details, such as property name, type, address, seller id, whether it is a rental, and asking price.
  * Justification: This feature is crucial to the product because the purpose of the product is to manage properties.
  * Highlights: Other features are dependent on the addition of property. Many design alternatives were considered before settling on attaching a unique and unmodifiable id to each property for the other entities to reference. This concept is adapted by bidders and sellers as well. 

* **New Feature**: Added the ability to delete properties from the property book.
  * What it does: allows the user to delete properties from the property book.
  * Justification: This feature improves the product significantly because the property agent or seller may decide not to list a certain property anymore, for example, because it has been sold.  
  * Highlights: The implementation of cascade delete whereby the deletion of a property will delete all relevant bids and meetings was challenging. The final design sought to minimise coupling between different entities.  Added extra flexibility for user to delete by property id on top of index.     

* **New Feature**: Added the ability to find properties according to some criteria.
  * What it does: allows the user to find properties based on multiple criteria that they specify.
  * Justification: This feature improves the product because it allows users to search for the property that matches their criteria, especially if there are a lot of properties in the property book.
  * Highlights: The implementation allows for searching of multiple criteria at the same time and the results will display properties that fulfil all criteria. Of special mention is the price filter that allow users to filter properties compared to a given price.  
  
* **New Feature**: Added the ability to edit properties in the property book.
  * What it does: allows the user to edit property details.
  * Justification: This feature improves the product significantly because it allows users to edit the properties in case they made a mistake or if they want to update the details.
  * Highlights: Care was taken to ensure that the edited property is not a duplicate property.
  
* **New Feature**: Added the ability to list all properties in the property book.
  * Justification: This feature is crucial to the product so that the property agent can look at all the properties in the property book.

* **Design**: Leads design discussions to maximise code quality.  
    * What: Recommends software design approaches to solve problems, such as overall architecture, how each entity relates to each other, and implementations that follow best practices, etc.
  
* **Code Quality**: Implemented shared helper classes for the team to use easily.  
    * What: Designed, implemented and tested helper classes that most entities use, such as Price and Id classes.  
    * Justification: This increases code reuse and enforces the separation of concerns principle by reducing code overlap.  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=dianne&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=dianneloh9&tabRepo=AY2021S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed release `v1.3` on GitHub
  * Managed the issue tracker on GitHub
  * Creation of labels for clearer segmentation of issues and pull request

* **Documentation**:
  * User Guide:
    * Added documentation for the property-related features: `add-p`, `delete-p`, `find-p`, `edit-p`, `list-p`
  * Developer Guide:
    * Added implementation details of property-related features: `add-p`, `delete-p`, `find-p`, `edit-p`, `list-p`  
    * Added the initial user stories  
    * Added the manual testing guide for `bid`, `meeting`, `calendar` and `keyboard navigation`  

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#297](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/297), [\#192](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/192), [\#151](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/151), [\#127](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/127), [\#124](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/124), [\#76](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/76), [\#77](https://github.com/AY2021S1-CS2103-W14-1/tp/pull/77#discussion_r499703796)   
  * Contributed to forum discussions: [28](https://github.com/nus-cs2103-AY2021S1/forum/issues/28#issuecomment-675923713), [66](https://github.com/nus-cs2103-AY2021S1/forum/issues/66#issuecomment-679228904)  
  * Reported bugs and suggestions for other teams in the class 
