---
layout: page
title: User Guide
---

Insurance4Insurance (I4I) is an app for insurance agents to manage clients. It helps manage client profile information for insurance agents to remember personal details about his/her client. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphic User Interface (GUI). 

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
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

[image coming soon]

Format: `help`

### Adding a client : `add`

Adds a client to I4I.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/SOURCE]…​`

Examples: 
   
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
   * `add n/Betsy Crowe s/friend from jac e/betsycrowe@example.com a/Newgate Prison p/1234567 o/This client is new.`


### Listing all clients : `list`

Lists the entire list of clients in I4I.

Format: `list`

### Deleting a client : `delete`

Deletes the client at the given index

Format: `delete CLIENT_INDEX`

Example: `list` followed by `delete 2` deletes the 2nd person in I4I.

Notes: 

* Deletes the person at the specified `INDEX`.

* The index refers to the index number shown in the displayed person list.

* The index must be a positive integer 1, 2, 3, …​

--------------------------------------------------------------------------------------------------------------------

## FAQ [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/SOURCE]…`​<br>e.g., `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 o/This client is new. s/friend from jc s/Jack's Girlfriend`
**List** | `list`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Help** | `help`
