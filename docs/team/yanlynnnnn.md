---
layout: page
title: Tan Yan Lyn's Project Portfolio
---

### Project: Homerce
Homerce is an all-in-one application that helps home-based beauty salon owners consolidate their business details - 
such as their schedule, appointments, clients, and services - into a single application. It also keeps track of the business's
revenue and expenses, and supports simple visualization of monthly finances.
The application uses a Command Line Interface (CLI).

This portfolio aims to document the contributions that I have made to Homerce <br>
Click [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=yanlynnnnn&tabRepo=AY2021S1-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
to view my code contributions.

**Enhancements Implemented:** <br>

<u>Implementation of Expense Tracker</u> <br>
The Expense Tracker was implemented to help users keep track of their expenses.

1. Implementation of 5 basic commands (Pull Request [#68](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/68),
Pull Request [#99](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/99))
    * `addexp` command to add an expense entry to the tracker
    * `editexp` command to edit an expense entry in the tracker
    * `deleteexp` command to delete an expense entry from the tracker
    * `listexp` command to display a list of all expense entries in the tracker
    * `clearexp` command to clear all expense entries from the tracker

1. Additional commands for extra functionality
    * `sortexp` command to sort expenses based on their value, in ascending or descending order (Pull Request [#191](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/191))
    * `findexp` command to find a specific set of expenses based on their description, date, tag or isFixed attribute (Pull Request [#68](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/68), Pull Request [#125](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/125))

1. Implementation of Recurring Expenses <br>
    * Implemented a feature to automatically duplicate fixed expenses every month, so that users do not have to enter the same
    entries every month (Pull Request [#180](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/180))
    * For example, the monthly air-conditioning bill is a fixed expense that recurs monthly, so this feature
    automatically duplicates and records the same air-conditioning expense every month

All the above features include the implementation of components from __Logic__, __Storage__, and __Model__.

<u>2. Enhancement of GUI</u>
1. Customized the GUI for the Client Manager, Service Manager, Appointment Tracker,
Expense Tracker, and Revenue Tracker panels (Pull Request [#223](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/223))
1. Customized the GUI for the `breakdownfinance` window (Pull Request [#298](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/298))
1. Creation of Expense Card and Expense Panel (Pull Request [#134](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/134))

<u>3. Testing</u>
1. Included JUnit Tests for the Expense Tracker, with over 90% line coverage on average (Pull Request [#203](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/203), Pull Request [#212](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/212))
1. Included Integration Tests across logic, model and storage components for the Expense Tracker (Pull Request [#203](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/203), Pull Request [#212](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/212))

<u>4. Others</u>
1. Implemented the logic to generate revenue and expense lists filtered by month and year,
  for use in the `breakdownfinance` feature (Pull Request [#187](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/187))
1. Designed the GUI Mock-Up for all panels (Pull Request [#25](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/25))

**Contributions to User Guide:** <br>
1. Introduction (Pull Request [#169](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/169))
1. Quick Start section (Pull Request [#169](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/169))
    * Instructions on how to set-up and run the application
1. About section (Pull Request [#169](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/169))
    * Structure of the document
    * Common terminology, symbols, and syntax used throughout the document
1. Expense Tracker (Pull Request [#60](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/60), Pull Request [#215](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/215))
    * Command Parameter Summary Table
    * Commands Documentation, including command parameters, command format, command examples, 
    and command outcomes with annotated screenshots (Pull Request [#301](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/301), Pull Request [#315](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/315))
    * Command Summary 
1. Future Enhancements
1. FAQ section (Pull Request [#169](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/169))

**Contributions to Developer Guide:** <br>
1. Expense Tracker (Pull Request [#228](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/228))
    * Rationale
    * Current implementation
    * Activity Diagram for `sortexp` command
    * Sequence Diagram for `sortexp` command
    * Design considerations for Recurring Expenses
1. Appendix B: User Stories (Pull Request [#52](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/52))
1. Appendix F: Manual Testing (Pull Request [#356](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/356))
1. Appendix G: Effort (Pull Request [#356](https://github.com/AY2021S1-CS2103T-W13-3/tp/pull/356))

**Contributions to documentation (Extracts)**<br>
The contributions listed in this section will not be extensive, please refer to the respective documents for the full contributions.

**Developer Guide**

<u>Diagrams</u>
![Activity diagram for sortexp_command](../images/SortExpenseActivityDiagram.png)

_Figure 1. Activity Diagram for `sortexp` command_

![Sequence diagram for sortexp command](../images/SortExpenseSD.png)

_Figure 2. Sequence Diagram for `sortexp` command_

```
When the user enters the `sortexp` command to sort the expense list, the user input command undergoes the same command parsing as described in
[Section 3.3 Logic Component](#33-logic-component). During the execution of `SortExpenseCommand`, Homerce will access the expense tracker
and sort the expense list based on the value of the expenses. For example, if the user specifies the order as "asc", the expense list will
be sorted in ascending order based on value, from lowest to highest value.

The following steps will describe the execution of the `SortExpenseCommand` in detail, assuming that no errors are encountered.
1. When the `execute()` method of the `SortExpenseCommand` is called, the `ModelManager`'s `getExpenseTracker()` method is called.
2. The `ModelManager` will return the existing `ExpenseTracker` to the `SortExpenseCommand`.
3. The `ModelManager` will call the `sort(isAsceding)` method on the `ExpenseTracker`.
4. The `ExpenseTracker` then calls the `sort(isAscending)` method on `NonUniqueList`, which sorts the expense list based on the order specified.
5. The `ObservableList` of expenses is updated to reflect the newly sorted list.
6. The `Ui` component will detect this change and update the GUI.
7. Assuming that the above steps are all successful, the `SortExpenseCommand` will then create a `CommandResult` object and return the result.
```

_Extract 1. Description of Sequence Diagram for the `sortexp` command_

```
**Aspect: Duplicating a fixed expense every month**

|              | **Pros**   | **Cons** |
| -------------|-------------| -----|
| **Option 1** <br> Immediately create 12 duplicate expenses upon the creation of a fixed expense for the next 12 months, creating another 12 duplicate expenses after every 12 months. | Users can edit the fixed expense records in a period of 12 months by editing any one of the 12 duplicate expenses. | Users are unable to create a fixed expense that only recurs over a period of less than 12 months. <br> For example, a user might want to add a fixed expense that he will incur only for the next 6 months. However, the application will create 12 duplicate expenses in advance. |
| **Option 2 (current choice)** <br> Create one duplicate expense for each fixed expense per month. | Duplicate expenses are only recorded when the month comes or the month has passed. | Users can only edit the next duplicate expense by editing the most recent duplicate expense. |

Reason for choosing option 2:
* It is more intuitive to the user to record an expense only when it has been incurred.
* Users can have more flexibility in creating fixed expenses that recur for a period that is not a multiple of 12 months.
## Contributions to User Guide
```

_Extract 2. Design Consideration for Fixed Expense_

**User Guide**

```
Are you an independent home-based beauty salon owner, scrambling between your calendar, client contacts, 
and notebook to keep track of all your appointments, revenue and expenses? 
Do you spend hours tabulating all your revenue and expenses just to get an idea of how your business is doing?
Fret not, our application, Homerce, will reduce the hassle of managing your business and save your valuable time. 
Homerce is an all-in-one application that helps home-based beauty salon owners consolidate their business details - 
such as their appointments, revenue and expenses - into a single application. 
```
_Extract 1. Introduction_
```
This section gives you step-by-step instructions on how to download and open the application.
1. Ensure you have Java `11` or above installed in your Computer. You may install it [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). 
```
_Extract 2. Quick Start_
```
We have structured this User Guide in a way to help you find what you need easily and quickly. 
In the next subsection, [Section 3.2 - reading this document](#32-reading-this-document), you can find several useful tips on how to read this guide.
The following section, [Section 4 - Features](#4-features), documents the seven main features in **Homerce**, namely:
```
_Extract 3. Structure of this document_
```
You can use this command to sort expenses in Homerce.
Format : `sortexp ORDER`
<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Refer to [Expense Tracker Command Parameters](#441-expense-tracker-command-parameters) for more details about each parameter.
</div>
Example :
You wish to view your expenses from highest to lowest cost to determine which expenses are contributing most to your total expenditure.
You can follow the steps below to sort your list of expenses.
Steps :
1. Type `sortexp desc` into the _Command Box_.
2. Pre
Outcome :
1. It will display a success message, as shown below.
2. Homerce will list out all the expenses sorted from highest to lowest cost.
![sortexp](images/sortexp.png) <br>
Figure 1 - GUI for `sortexp`
```
_Extract 4. `sortexp` command description_

