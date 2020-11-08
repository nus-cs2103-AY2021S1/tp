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

## Quick start

1. Ensure you have Java `11` installed in your Computer. 
   You can check the version by opening a command window, and running the `java -version` command.

2. Download the latest `[CS2103-T16-2][Insurance4Insurance].jar` from [here](https://github.com/AY2021S1-CS2103-T16-2/tp/releases).

3. Copy the file to the **empty folder** you want to use as the _home folder_.

4. Launch the app by using the `java -jar` command (do not use double-clicking). 
   The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all active clients.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/h` : 
   Adds a contact named `John Doe` to the client list.

   * **`delete`**`3` : Deletes the 3rd client shown in the current list.

   * **`exit`** : Exits the app.

6. Refer to the [Commands](#commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [s/CLIENT_SOURCE]` can be used as `n/John Doe s/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[s/CLIENT_SOURCE]…​` can be used as ` ` (i.e. 0 times), `s/friend`, `s/friend s/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
**:information_source: Notes and terminologies on archiving:**<br>

* Clients not in the archive are said to be "active clients" in the "active list".
 
* Upon starting up the app, users would see the active list by default.
 
* In the "active mode", users can view the active list. In the "archive mode", users can view the archive.

</div>

### Viewing help : `help`

Shows a message explaining how to access the User Guide.

![help message](images/helpMessage.png)

Format: `help`

### Adding a client : `add`

Adds a client to I4I's active list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of client sources (including 0)
</div>

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/CLIENT_SOURCE]…​ [l/PRIORITY] [pn/POLICY_NAME]`

Examples: 
   
   * `add n/Cai Shen Ye`
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 l/h`
   * `add n/Betsy Crowe s/friend s/enemy from jac e/betsycrowe@example.com a/Newgate Prison p/1234567 
   o/This client is new. l/high pn/Life Time Policy`

Notes: 

* If the priority field is empty, I4I will set the priority of the client to undefined. 
    See [Priority Feature](#priority-feature) for more information.

* The policy must already exist in the policy list. You can do so by using the
`addp` command. See [Add Policy Command](#adding-a-policy--addp) for more information.

* This command should not be used when viewing the archived client list.  

### Adding a policy : `addp`

Adds a Policy into the policy list.

Format : `addp pn/POLICY_NAME pd/POLICY_DESCRIPTION`

Examples :

* `addp pn/Medishield pd/Covers COVID`

Note :

* Policy added must not have the same name as an existing policy.

### Listing all active clients : `list`

Lists the active clients in I4I.

Format: `list`

### Archiving an active client : `archive`

Archives the client at the given index.

Format: `archive CLIENT_INDEX`

Example: `list` followed by `archive 2` archives the 2nd active client in I4I.

Notes: 

* Archives the client at the specified `INDEX`.

* The index refers to the index number shown in the displayed client list.

* The index must be a positive integer 1, 2, 3, …​

* This command should not be used in the archive mode.

### Listing all archived clients : `list r/`

Lists the archived clients in I4I.

Format: `list r/`

### Unarchiving an archived client : `unarchive`

Unarchives the client at the given index.

Format: `unarchive CLIENT_INDEX`

Example: `list r/` followed by `unarchive 2` unarchives the 2nd archived client in I4I.

Notes: 

* Unarchives the client at the specified `INDEX`.

* The index refers to the index number shown in the displayed client list.

* The index must be a positive integer 1, 2, 3, …​

* This command should not be used in the active mode.

### Deleting a client : `delete`

Deletes the client at the given index.

Format: `delete CLIENT_INDEX`

Example: `list` followed by `delete 2` deletes the 2nd active client in I4I.

Notes: 

* Deletes the client at the specified `INDEX`.

* The index refers to the index number shown in the displayed client list.

* The index must be a positive integer 1, 2, 3, …​

### Clearing all clients : `clear`

Clears all entries from the client list.

<div markdown="span" class="alert alert-primary"> **Warning:**
This command clears all clients, from both the active list and the archive.
</div>

Format: `clear`

Notes: 

* This command does not clear the policy list.

### Clearing all policies : `clearp`

Clears all entries from the policy list.

Format : `clearp`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Additional Features

### Priority Feature

There are four priority settings to the priority feature - Undefined, Low, Medium and High.
I4I would show different colored bars at the right of each client depending on the priority assigned to each client.

They are: 
 
|Priority Type   |Syntax  |Picture   |
|---|---|---|
|Undefined   |`l/undefined`,`l/u`, `l/U`   |![Undefined Priority](images/Priority Bar/UndefinedPriority.png)  |
|Low   |`l/low`,`l/l`, `l/L`    |![Low Priority](images/Priority Bar/LowPriority.png)   |
|Medium   |`l/medium`,`l/m`, `l/M`    |![Medium Priority](images/Priority Bar/MediumPriority.png)   |
|High   |`l/high`,`l/h`, `l/H`    |![High Priority](images/Priority Bar/HighPriority.png)   |

Note: 
* If a client is not assigned a priority, I4I would automatically assign an undefined priority to the client.

* Currently, one can only set the priority on adding the client. 

Example: 

* `add n/Jojo l/h` would add a client named Jojo with a high priority. 
* `add n/Giorno` would add a client named Giorno with an undefined priority.

### Policy Feature

Insurance policies belonging to the user's company can be added into I4I's policy list.
A client can then be added with the corresponding policy.

Policies have a name and a description.

The policy list is also saved to the hard disk.

Currently, 2 commands, `addp` & `clearp`, are used to control the policy list.

### Archive Feature

The archive is meant as a way to store clients which may not be currently relevant to the user. 
For example, clients which are no longer managed by the user can be stored in the archive, with the `archive` command. 
The user may decide not to delete the client straight away, 
in case they happen to start interacting again, so the users can store them in the archive.

The archive has a separate view from the active list, so that the user would not be distracted by archived clients. 
The default view upon starting up the app is the active list.
The user has the option to switch the view to the archive if needed, with `list r/`. 
They can also switch the view back to the active list with `list`.

The user can unarchive an archived client, for which case the client would become active, with `unarchive`.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app in the other computer, and overwrite the empty data files it creates with the files that contain 
the data of your previous I4I home folder. By default, the 2 data files are named `clientlist.json` and `policylist.json`.

--------------------------------------------------------------------------------------------------------------------



## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [o/NOTE] [s/CLIENT_SOURCE]…​ [l/PRIORITY]` <br>e.g., `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 o/This client is new. s/friend from jc s/Jack's Girlfriend l/h`
**Add Policy** | `addp pn/POLICY_NAME pd/POLICY_DESCRIPTION` <br>e.g., `addp pn/Medishield pd/Covers COVID`
**Archive** | `archive INDEX`<br> e.g., `archive 3`
**Clear Clients** | `clear`
**Clear Policies** | `clearp`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Exit** | `exit`
**List Active Clients** | `list`
**List Archived Clients** | `list r/`
**Help** | `help`
**Unarchive** | `unarchive INDEX`<br> e.g., `unarchive 3`
