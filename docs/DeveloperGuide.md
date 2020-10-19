---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

Coming soon.

### UI component

Coming soon.

### Logic component

Coming soon.

### Model component

Coming soon.


### Storage component

Coming soon.

### Common classes

Coming soon.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### \[Proposed\] Priority Feature

#### Proposed Implementation

The proposed priority feature would involve the UI javafx feature as well as a newly implemented field for the Clientlist. 

Additionally, the priority feature would come with a command that sorts the entire list based on the priorities set for the Clientlist.

Note : The proposed sort feature will ONLY involve the UI in filtering BASED on the priority of the individuals rather than other fields. This means that sorting or filtering a list would NOT affect deleting or editing commands on users that are currently not shown in the filtered or sorted list. 

It would implement the following commands:
<to be filled in>
<filter /p important first or important last>

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. As the application is pre-loaded with data, it would look like this: <Insert png>
    
Step 2: The user decides to add a client into the clientlist. The user would set the priority of the user as 1 for example. 
Based on what the user has added, we would append/attach/add a box of a predetermined type and shape into the personcard fxml, changing the design of it. If we are unable to append any objects to the personcard fxml, we can redesign different personcard fxml files and add them based on the tag that the user has created.

Step 3. The user decides to filter the application based on a certain priority. In this case, we will assume that the priority would be 4 (High)

Firstly, there would need to be a comparator to be able to build it. 

Implementation idea 1: Changing the UI to use a FilteredList object only. 
To implement this, we would create a FilteredList fxml object and set the items inside into the ListView object that is currently used for the PersonListPanel class. 
https://stackoverflow.com/questions/30915007/javafx-filteredlist-filtering-based-on-property-of-items-in-the-list

However, the problem with this is that the FilteredList currently looks different compared to what the ListView looks like, so this might break/ not work. It is still worth a try since it is relatively easy to implement. 

But the problem after this would be trying to get the listener to work, I.E: telling when one should start to filter the listfilter object or whatnot. Therefore, an extra class would have to be created to handle that.

Implementation idea 2: Changing the way objects are added into the ListView object. 

The idea behind this would be to restart the entire list, and only ADD in objects to the ListView UI based on what is being filtered. I prefer this idea but I have not really implemented it. This is because we would have to create a filter class WITHIN the UI, which deals with the filtering of the clients based on the different steps being done. This idea can be supported because since the filterer is based on the UI only, and would not affect any changes inside the clientlist, and would be valid as part of the UI implementation. 

We have decided to change the colors of the boxes based on the 
We have decided to prioritise the implementation of the filter function based on the UI only and not affect other classes since the filtering should not "delete" or modify any other values inside the listview.  

The following sequence diagram shows how the Priority operation works:

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### Design consideration:

##### Aspect: How Priority filter works.

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

Coming soon.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

Insurance Agents - Financial Advisors
Insurance agents represent insurance companies, such as AIA and Prudential, as their sales person. They bring in 
clients and promote various insurance schemes. Their clients could be individuals or other businesses. Insurance agents’ 
mode of conducting their job generally involves talking directly to these clients in a face-to-face setting and giving 
their pitch. 

Insurance agents need to be familiar with their clients’ profile, such as their family status, financial needs and 
interests, to better promote their insurance schemes as it gives their pitch a personal touch. 

These users are likely used to typing notes and formulas to manage their clients into a tracker, for example in  Excel 
notebooks. They would add in attributes of users into the tracker, and add in additional functionalities using formulas. 
Thus, they are likely to be comfortable in using a command-line interface.

**Value proposition**: 

Remembering people like a good neighbour.

Successful Financial advisors can have more than a hundred clients. They may not be able to remember the personal 
details of all their clients at once. This product aims to provide a command-line interface for the financial agent to 
record the details of the client, from their scheme details, such as the policies they own, to their personal details, 
like their hobbies. As such, they would be better prepared for meetings and are able to provide a personal touch when 
giving their pitch.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                                     | I can …                        | So that I can…                                                         |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | regular user                               | add more clients               | handle more clients                                                    |
| `* * *`  | regular user                               | delete a client                | account for my clients who churned                                     |
| `* * *`  | first-time user                            | view all clients               | get a general overview                                                 |
| `* * *`  | first-time user                            | load existing data that I have on user personal details |                                               |
| `* * *`  | regular user                               | save my client information     | save my edits for next time use                                        |


### Use cases

(For all use cases below, the **System** is `I4I` and the **Actor** is the `user`, unless specified otherwise)

**UC01 - User adds Client**

**MSS**

1. User chooses to add new client with client details.
2. System gives success message.

    Use case ends.

**Extensions**

* 1a. User enters wrong add command format.

    * 1a1. System gives corresponding error message.

      Use case ends.

**UC02 - User deletes Client**

**MSS**

1. User requests to list persons.
2. I4I shows a list of persons.
3. User chooses to delete particular client.
4. System gives success message.

    Use case ends.

**Extensions**

* 2a. The list is empty.

      Use case ends.

* 3a. User enters wrong delete command format.

    * 3a1. System gives corresponding error message.

      Use case ends.
      
* 3b. User enters invalid index.

    * 3b1. System gives corresponding error message.

      Use case ends.

**UC03 - User lists all Clients**

**MSS**

1. User chooses to list all clients.
2. System shows all clients.

    Use case ends.

**Extensions**

* 1a. User enters wrong list command format.

    * 1a1. System gives corresponding error message.

      Use case ends.
      
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to store up to 1000 clients.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) 
should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Insurance4Insurance**: Name of the product.
* **I4I**: Short for Insurance4Insurance, the name of the product.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Coming soon.
