---
layout: page
title: Kimberly's Project Portfolio Page
---

## Project: SWEe!

SWEe! is a desktop application used for managing CS2103T learning progress mainly through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kimberlyohq)

* **Enhancements implemented**:
    - Refactored `Person` to `Flashcard` and updated relevant test cases[#26](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/26)
    -  Added the ability to favourite and unfavourite flashcards [#73](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/73) + [#92](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/92)
        * What it does: Allows the user to favourite and unfavourite flashcards via `fav` and `unfav` command
        * Justification: This feature improves the product significantly especially in terms of user experience as it allows users to favourite flashcards. This allow users to better customize the flashcards, providing greater room for filtering flashcards to refine their list of flashcards, which will be especially helpful in the future when they have a lot of flashcards.
        * Highlights: This enhancement involves adding new commands(`fav` and `unfav`) and updating UI for favourite feature.
    - Added the ability to find flashcards using keywords
        * What it does: Allows the user to find flashcards using keywords via the `find` command
        * Justification: This feature improves the product significantly in terms of efficiency as it allows users to find flashcards easily.
        * Highlights: This enhancement affected the existing implementation of `find` command in AB3. This is because the `find` searches for the keywords in the entire flashcard, which includes `Flashcard`'s `Question`, `Answer`, `Category`, `Tags` and `Note` 
                      as compared to the previous implementation which only looks at `Person#Name`. Hence, `FindCommand` needed to be reimplemented to check for various fields.  Since the flashcard can contain special characters, the implementation also includes handling of special characters while searching for matching keywords in flashcard 
          <div style="page-break-after: always;"></div>                   
    - Added test cases for `review` command - `ReviewCommandTest` [#40](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/40)
    - Added test cases for `quiz` command - `QuizCommandTest` [#253](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/253)
    - Added test cases for `StudyManager` - `StudyManagerTest`
                      

* **Contributions to User Guide:**:
    * Added documentation for `find` command [#100](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/100)
    * Added documentation for `fav` commmand [#82](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/82)
    * Added documentation for `unfav` command [#82](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/82)
    * Added documentation for `clear` command [#159](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/159)
    
* **Contributions to DG:**:
    * Refactored DG from AB3 to SWEe! [#108](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/108)
        * Updated UML diagrams:
            * Sequence Diagrams: `ArchitectureSequenceDiagram`, `DeleteSequenceDiagram`, `LogicSequenceDiagram`
            * Class Diagrams: `LogicClassDiagram`, `StorageClassDiagram`, `UiClassDiagram`
            * Activity Diagrams: `CommitActivityDiagram`
    * Added implementation details for `fav` and `unfav` feature [#77](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/77)
        * Added UML diagrams: `FavouriteSequenceDiagram`, `FavouriteUnfavouriteSequenceDiagram` and relevant state diagrams
    * Add Product Scope and User Stories
    
* **Contributions to team-based tasks:**:
    * Maintaining the issue-tracker
    * Refactoring DG from AB3 to SWEe!
    * Refactor Person in AB3 to Flashcard.
    * Set up hyperlinks for DG's table of contents

* **Review/Mentoring Contributions**:
    * PR reviews: [#219](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/219),[#224](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/224), [#213](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/213), [#219](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/219), [#25](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/25), [#36](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/36), [#37](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/37), [#41](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/41)
     ,[#52](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/52), [#57](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/57), [#75](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/75), [#84](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/84), [#93](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/93), [#99](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/99), [#153](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/153), [#157](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/157), [#171](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/171)
      
* **Contributions beyond project team**:
    * Bugs reported during PE Dry Run: [Issue Tracker](https://github.com/kimberlyohq/ped/issues)
    * Bugs reported for CS2103T-T17-1: [Bugs](https://docs.google.com/document/d/1-k0i_tu2YgV0siT0XVdLkNy0LIJMigC3J7IWJ0qCTT8/edit)

