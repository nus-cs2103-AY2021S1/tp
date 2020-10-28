---
layout: page
title: User Guide
---

Insurance4Insurance (I4I) is an app for insurance agents to manage clients. It helps manage client profile information 
for insurance agents to remember personal details about his/her client. It is optimized for use via a Command Line 
Interface (CLI) while still having the benefits of a Graphic User Interface (GUI). 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start [coming soon]

--------------------------------------------------------------------------------------------------------------------
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [s/CLIENTSOURCE]` can be used as `n/John Doe s/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[s/CLIENTSOURCE]…​` can be used as ` ` (i.e. 0 times), `s/friend`, `s/friend s/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a client : `add`

Adds a client to I4I.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of client sources (including 0)
</div>

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/CLIENTSOURCE]…​ [l/PRIORITY]`

Examples: 
   
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/h`
   * `add n/Betsy Crowe s/friend from jac e/betsycrowe@example.com a/Newgate Prison p/1234567 o/This client is new.`

Note: If the priority field is empty, I4I will set the priority of the client to undefined. 
See [Priority Feature](#Priority Feature) for more information.

### Listing all clients : `list`

Lists the entire list of clients in I4I.

Format: `list`

### Deleting a client : `delete`

Deletes the client at the given index

Format: `delete CLIENT_INDEX`

Example: `list` followed by `delete 2` deletes the 2nd client in I4I.

Notes: 

* Deletes the client at the specified `INDEX`.

* The index refers to the index number shown in the displayed client list.

* The index must be a positive integer 1, 2, 3, …​

### Clearing all entries : `clear`

Clears all entries from the client list.

<div markdown="span" class="alert alert-primary"> **Warning:**
This command clears all clients, from both the active list and the archive.
</div>

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Features

### Priority Feature

There are four priority settings to the priority feature - Undefined, Low, Medium and High.
I4I would show different colored bars at the right of each client depending on the priority assigned to each client.

They are: 
 
|Priority Type   |Syntax  |Picture   |
|---|---|---|
|Undefined   |`l/undefined`,`l/u`, `l/U`   |![help message](images/Priority Bar/UndefinedPriority.png)  |
|Low   |`l/low`,`l/l`, `l/L`    |![help message](images/Priority Bar/LowPriority.png)   |
|Medium   |`l/medium`,`l/m`, `l/M`    |![help message](images/Priority Bar/MediumPriority.png)   |
|High   |`l/high`,`l/h`, `l/H`    |![help message](images/Priority Bar/HighPriority.png)   |

Note: 
* If a client is not assigned a priority, I4I would automatically assign an undefined priority to the client.

* Currently, one can only set the priority on adding the client. 

Example: 

* `add n/Jojo l/h` would add a user named Jojo with a high priority. 
* `add n/Giorno` would add a user named Giorno with an undefined priority.


--------------------------------------------------------------------------------------------------------------------

## FAQ [coming soon]

--------------------------------------------------------------------------------------------------------------------



## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/CLIENTSOURCE]…​ [l/PRIORITY]` <br>e.g., `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 o/This client is new. s/friend from jc s/Jack's Girlfriend l/h`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Exit** | `exit`
**List** | `list`
**Help** | `help`
