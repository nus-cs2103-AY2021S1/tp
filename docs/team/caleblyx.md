---
layout: page
title: Lin Yuan Xun, Caleb's Project Portfolio Page
---

## Project: Lifebook

Lifebook is a desktop task management application created during a collaborative project for a module that teaches Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New feature: TaskList and tasks**: Added the ability to create `Tasks`, and add or delete them to or from the `TaskList` respectively.
  * What it does: allows the user to create `Tasks` that may either be `Todos` or `Events` and keep a record of them in the `TaskList`.
  * Justification: This feature improves the product significantly because a user can now record information about tasks in Lifebook for future reference and planning.
  * Highlights: This required an implementation of the TaskList and Tasks (along with its subclasses). This a basis for more commands and features to be added in the future.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature List out Tasks, Events or Todos**: Implemented the ability for users to list out all `Tasks`, `Events`, or `Todos`.
  * What it does: allows the user to list out `Tasks`, `Events`, or `Todos`.
  * Justification: allows the user to view all `Tasks`, or if needed, a filtered list of all `Events` or `Todos`.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature: Mark tasks as done**: Implemented the ability for users to mark tasks as done.
  * What it does: allows the user to mark completed tasks as done.
  * Justification: allows users to have a record of the tasks that have been completed.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature: Sort TaskList and AddressBook** Implemented the ability for users to sort the `TaskList` and `AddressBook`.
  * What it does: allows users to sort the `TaskList` and `AddressBook` by name and date respectively. Users also have the option of restoring both lists to their natural order if needed.
  * Justification: provides users with intuitively sorted lists. Users normally prefer to look through contact details in alphabetical order, and task details in the order of imminence.
  * Highlights: To implement sorting easily, the `TaskList` and `AddressBook` had to be wrapped by the JavaFX sorted list class, since the JavaFX filtered list class does not support sorting. Also, thanks to bug reporting by peers, I was able to make further refinements to the sorting command in cases whereby the displayed filtered list is empty, or whereby the unfiltered list had no added items. 

* **New feature: TaskList GUI**: Created a simple GUI for the `TaskList` and `Tasks` in the first iteration, which was further enhanced and modified in proceeding iterations by other members.
  * What it does: allows users to view the `TaskList` and its contents.
  * Justification: provides a means for users to easily access the contents of the `TaskList` via a graphical representation.
  * Highlights: made it easier for the developing team to visualise any features that they may have implemented in regards to the `TaskList` and/or `Tasks`. Implementing this was initially challenging due to my lack of experience with JavaFX. It required me to scrap my initial implementation of the TaskList to create a new one that could provide an observable list to the GUI.
  * Credits: this GUI implementation for the `TaskList` was inspired by the GUI of contact list of AB3.

* **New feature: Storage for TaskList**: Implemented storage for the `TaskList`
  * What it does: allows users to store `TaskList` data.
  * Justification: provides a means for users to easily store and access the contents of the `TaskList` for future sessions.
  * Highlights: enables greater ease of implementing storage for data of other features pertaining to `Tasks` in the future. I had encountered some challenges due to my lack of familiarity with JSON. For instance, I was not aware I had to make additional annotations to serialize/deserialize polymorphic objects with JSON.
  * Credits: implementation inspired by the existing `Storage` component of address book. I referred to a [Stack Overflow discussion forum](https://stackoverflow.com/questions/30362446/deserialize-json-with-jackson-into-polymorphic-types-a-complete-example-is-giv) to gain insight on how to resolve my issue regarding JSON adapted polymorphic objects.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F12-4&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code~docs~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=caleblyx&tabRepo=AY2021S1-CS2103T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~docs~test-code)

* **Project management**:
  * Created release `v1.3` on [GitHub](https://github.com/AY2021S1-CS2103T-F12-4/tp/releases/tag/v1.3) 

* **Enhancements to existing features**:
  * Reduced coupling of `Model` and `Logic` in the implementation of the Recurrence feature.  [\#148](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/148)
  * Wrote unit tests for all added storage components that support storage of the contents of `TaskList`. [\#134](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/134)
  * Wrote unit tests for the logical components of the Sorting feature, and updated various test files to fix bugs after correcting the `equals` method of `ModelManager`.[\#248](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/248)
* **Documentation**:
  * User Guide:
    * Added documentation for the features:
        * `add todo`, `remove todo`, `mark todo as done` and `list todo`. These features were updated in future iterations. [\#20](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/20)
        * `sort contact`, `sort task`, and `sort clear` [\#110](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/110) [\#145](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/145)
  * Developer Guide:
    * Updated the model architecture diagram to include the newly added model components of Lifebook. [\#234](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/234)
    * Added implementation details of the `Add Task` feature. [\#120](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/120)
    * Added user stories, use cases, and manual testing for the features I implemented  [\#37](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/37/files), [\#234](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/234), [\#259](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/259)
    * Contributed to the "Effort" section of the appendix [\#276](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/276)
* **Community**:
  * Reviewed and merged multiple PRs. Examples: [\#149](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/149), [\#216](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/216), [\#221](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/221).
  * Resolve failing CI with PR: [\#150](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/150)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Caleblyx/ped/issues/6), [2](https://github.com/Caleblyx/ped/issues/4), [3](https://github.com/Caleblyx/ped/issues/3))

* **Other contributions**:
  * Contributed to the creation of Lifebook's demonstration video (script and voiceover).
