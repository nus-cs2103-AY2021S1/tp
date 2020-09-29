---
layout: page
title: User Guide
---

insurance4Insurance (i4I) is an app for insurance agents to manage clients. It helps manage client profile information for insurance agents to remember personal details about his/her client. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphic User Interface (GUI). 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start

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

### Adding a client : `add`

Adds a person to the list with a note beside their name. 

Format: `add n/NAME o/NOTE`

Example: `add n/John Doe o/My daddy`

### Listing all clients : `list`

Lists the entire list of clients in the list.

Format: `list`

### Deleting a client : `delete`

Deletes the client at the given index

Format: `delete CLIENT_INDEX`

Example: `delete 1`

Notes: 

* Deletes the person at the specified INDEX.

* The index refers to the index number shown in the displayed person list.

* The index must be a positive integer 1, 2, 3, …​


--------------------------------------------------------------------------------------------------------------------

## FAQ

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME o/NOTE` <br> e.g., `add n/John Doe o/My daddy`
**List** | `list`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
