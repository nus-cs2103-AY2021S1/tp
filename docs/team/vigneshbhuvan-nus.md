---
layout: page
title: Vignesh Bhuvan's Project Portfolio Page
---

## Project: Green Tea

Green Tea is a desktop app for learning a new language, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Implemented Storage System for Deck, Entry, Word and Translation

  - Pull requests: [#80](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/80)
  - What it does: Allows for the storage of decks and its entries in `wordbank.json`, and allows json files to be serialized back into the model.
  - Justification: Storage of data is essential to the functioning of the app, and allows for the sharing of progress between multiple users
  - Highlights:
    - Moderately difficult to implement, as I was new to the concept of JSON serialization. I got through this issue by understandning the initial source code, as well as hours of research online for the implementation of these concepts.

- **New Feature**: Implemented Storage System for QuizAttempt, QuestionAttempt and Score.

  - Pull requests: [#129](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/129)
  - What it does: Allows for the storage of quiz attempts, question attempts and their respective scores, and allows for json files to be serialized back into the model. 
  - Justification: Allows users to track their progress of all their previous attempts at the quiz, and essentially allows for the implementation of the Leitner system, in which a user's worst entries are tracked and sorted for the system to work. 
  - Highlights:
    - Moderately complex to implement. Although I learnt from my initial work with Deck Storage, this was different as I had to work with the storage of interfaces. I was stuck on a problem involving the serialization of the classes, and I got through it by researching (In one instance, I needed to create an empty constructor for the serialization to function properly)        

- **New Feature**: Helped in the implementation of Leitner System and Flashcard System

  - Pull requests: [#103](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/103)
  - What it does: Allows the users to quiz themselves on the different entries they have added to Green Tea.
  - Justification: This feature is essentially the core of the application. The entire application is built around this compulsory feature including other features such as
                     statistics and switching tabs.
  - Highlights:
    - I created a Answer and Question class that keeps the questions and answers separately, all being stored as a list in a Deck object
    - Each class had its own methods to check the validity of the answer, and grade the answer according to a scoring system (for example closeness of Strings)
    - QuestionList and AnswerList to store questions and answers
    - Leitner class to manage the implementation and management of the questions and answers
    - Was not merged into the final code because we eventually took a different approach

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=vigneshbhuvan-nus&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management**:

  - Managed releases `v1.2` - `v1,4` (3 releases) on GitHub

- **Enhancements to existing features**:

  - Add Storage system and helped with Leitner implementation
  - Wrote unit tests for storage related code.

- **Documentation**:

  - Developer Guide:

    - Wrote DG sections for storage system (3.7), Documentation and Testing (5), Use Cases (6.3) and Appendix for Manual Testing (7).

  - User Guide:

    - Wrote user guide sections for Entry Commands (5.2), Getting Started (3), About this document (2) and formatting of the pictures and Table of Contents
    
