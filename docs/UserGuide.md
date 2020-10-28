---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
# 1. About
**Common Cents** is your convenient at-hand **expense-tracking tool**, meant for **anyone who runs a small-scale business**. 
With just a few commands, _Common Cents_ will keep track, categorise and calculate your income and expenditure for you! 
You can even keep our expenses in multiple, separate accounts for seamless organisation. Amalgamating the features of a 
Command Line Interface (CLI) with a Graphical User Interface (GUI), _Common Cents_ is meant to be functional, intuitive and 
faster than most traditional GUI expense-trackers out there!

--------------------------------------------------------------------------------------------------------------------
# 2. User Guide Overview
This User Guide will show you how to navigate the User Interface (UI), give you step-by-step instructions on using commands, tips and tricks on how to maximise your experience with _Common Cents_ and clarify most doubts that you may have.

The _Common Cents_ interface and commands consist of a few levels: `Application` level, `Account` level and `Entry` level.<br>
* `Application` level commands deal with interactions with the app itself.<br>
* `Account` level commands deal with managing the different accounts in the app.<br>
* `Entry` level commands deal with managing the entries which are either expenses or revenues in an account.

--------------------------------------------------------------------------------------------------------------------
[comment]: <> (Copy the blocks below and edit your message)
# 3. How to identify notations
These blocks are a few examples of notations that will be used in this document. Each block and icon 
has a different meaning so do look out for them under our features.

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:** 

Important notes to read regarding the feature. 
</div>

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:**

Ways to better your experience with _Common Cents_.
</div>

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

An example, or multiple examples to follow.
</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Important cautions that needs to be known before using the feature.
</div>


--------------------------------------------------------------------------------------------------------------------

# 4. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `commoncents.jar`.

1. Copy the file to the folder you want to use as the _home folder_ for CommonCents.

1. Double-click the file to start the app. You should see the  Graphical User Interface (GUI) similar 
to the one below in a few seconds. 
Note how the app contains some sample expenses and revenues.<br>

 ![Ui](images/Ui.png)
 <p align="center"> <sub> <b>Figure</b>: Default GUI </sub> </p>

1. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add c/expense d/buy lunch a/5.20 t/food` : Adds an expense named "buy lunch" to the expense list.
   * `delete 1 c/expense` : Deletes the 1st entry in the expense list.
   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
# 5. User Interface Overview
As you can see, **figure** below shows the default user interface (UI) of *_Common Cents_* with its components.  
![annotatedUi](images/commands/annotatedUi.PNG)
<p align="center"> <sub> <b>Figure</b>: Default user interface of <i>Common Cents</i> </sub> </p>

You can find the description for each UI component shown in the table below.<br> 

UI component | Description
--------|------------------
*Menu bar* | Displays the menu bar of _Common Cents_. You can use this to access the User Guide or exit the application. 
*Active account name* | Displays the account you are currently on.
*Display picture* | Displays the default display picture of the application.
*Command box* | Displays a text field for you to type the commands.
*Result display* | Displays the result of your command. If the command is invalid, an error message will be displayed to prompt and guide you to input valid correct command.
*Account summary chart* | Displays the total expenses and revenues in your current account in a pie chart.
*Chart legend* | Displays the legend for the account summary chart.
*Status bar* | Displays the current date.
*Expense list* | Displays all the expenses in your current account in a panel.
*Revenue list* | Displays all the revenues in your current account in a panel.

--------------------------------------------------------------------------------------------------------------------
# 6. Components
This section describes the various components that comprises Common Cents, mainly Account and Entries. If you
are new to Common Cents, do not fret as this section will guide you step by step in understanding the components
available! This will better your experience with Common Cents and your usage of features!

## 6.1 Accounts

### 6.1.1 Name

### 6.1.2 Expense List

### 6.1.3 Revenue List

## 6.2 Entries

### 6.2.1 Category

### 6.2.2 Description

### 6.2.3 Amount 

### 6.2.4 Tags

--------------------------------------------------------------------------------------------------------------------

# 7. Features

<div markdown="block" class="alert alert-info">

:information_source: **About the command format:**<br>

* Words in UPPERCASE are the parameters to be supplied by the user.<br>
  e.g. in `add c/CATEGORY...`, `CATEGORY` is a parameter which can be used as `add c/REVENUE...`

* Items in square brackets are optional.<br>
  e.g `...a/AMOUNT [t/TAG]` can be used as `...a/50.10 t/Supplies` or as `...a/50.10`.

* Parameters **preceded by a prefix** can be in any order.<br>
  e.g. if the command specifies `c/CATEGORY d/DESCRIPTION...`, `d/DESCRIPTION c/CATEGORY...` is also acceptable.

* **Numeric** parameters (e.g. `ENTRY_INDEX`) must be in the correct order.<br>
  e.g. if the command specifies `ENTRY_INDEX c/CATEGORY`, only `ENTRY_INDEX c/CATEGORY` is acceptable 
  and `c/CATEGORY ENTRY_INDEX` is invalid. 
  
* Commands with no prefix parameters (e.g. `clear`, `profit`, `listacc`) can take in extra words after the command word
  without affecting its execution.<br>
  e.g. the command `profit abc` works the same as `profit` and is considered valid.

</div>

The commands are separated into three categories: App-Level commands, Entry-Level commands and Account-Level Commands.

Each level interacts with _Common Cents_ differently to maximise your experience. More details about each level are 
found in its own section below.  

## 7.1 App-Level Commands
App-Level commands deals with interactions with the app, _Common Cents_, itself.

### 7.1.1 Viewing help : `help`

You can use this command when you are at a lost or want to view an overview of how to use the application.
This command will return a message explaining how to access the help page (Figure). 
You can click on the `Copy URL ` button to copy the link to clipboard and paste it in your web browser. <br> 

**Format**: `help`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `help`: Returns help prompt.

</div>

![helpMessage](images/helpMessage.png)
<p align="center"> <sub> <b>Figure</b>: Help message </sub> </p>
 
### 7.1.2 Exiting the program : `exit`

You can use this command when you are done with what you need to do and want to close the application.

**Format:** `exit`
 
<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `exit`: Returns exit message then quits the program in 1.5 seconds.

</div>

![exitCommand](images/commands/exitCommand.png)
<p align="center"> <sub> <b>Figure</b>: Exit message appears in Result display </sub> </p>

## 7.2 Entry-Level Commands
Entry-Level commands involves managing the entries which are either expenses or revenues in an account. 

### 7.2.1 Adding an entry: `add`

You can use this command when you want to add an entry (expense/revenue) to the tracker.

**Format:** `add c/CATEGORY d/DESCRIPTION a/AMOUNT [t/TAG]`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `add c/expense d/buying supplies a/10.10 t/blogshop t/eCommerce` (**Figure**)
* `add c/revenue d/sale of clothes a/200 t/blogshop t/eCommerce`

</div>

![addCommand](images/commands/addCommand.png)
<p align="center"> <sub> <b>Figure</b>: A new expense called "buying supplies" is added </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* Adds an entry to either category.
* The category refers to the classification of the entry in the entry lists.
* The category must be either 'expense' or 'revenue'.

</div>


### 7.2.2 Deleting an entry: `delete`

You can use this command to remove an entry (expense/revenue) from the tracker when you do not want to keep track of 
it anymore.

**Format:** `delete ENTRY_INDEX c/CATEGORY`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `delete 1 c/expense` : Deletes first entry in expense category
* `delete 2 c/revenue` : Deletes second entry in the revenue category (**Figure**)

</div>

![deleteCommand1](images/commands/deleteCommand1.png)
<p align="center"> <sub> <b>Figure</b>: The revenue at index 2 is deleted </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* Deletes the entry at the specified INDEX.
* The index refers to the index number shown in the displayed entry lists.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the INDEX given cannot be > 10)

</div>

### 7.2.3 Editing an entry: `edit`

You can use this to make edits to any existing entries in the tracker when the entry's details are wrongly keyed.

**Format:**
* `edit 1 c/CATEGORY d/DESCRIPTION a/AMOUNT t/TAG`
* `edit 2 c/CATEGORY d/DESCRIPTION`
* `edit 3 c/CATEGORY a/AMOUNT`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `edit 2 c/expense d/buy McSpicy a/8.60`: Changes the name and the amount of entry 2 to 
`eating McSpicy` and `$8.60` respectively (**Figure**)
* `edit 2 c/revenue d/sell McNuggets`: Changes the name of entry 2 to `sell McNuggets` 
* `edit 2 c/expense a/5.50`: Changes the amount of entry 2 to `$5.50`

</div>

![beforeEditCommand](images/commands/beforeEditCommand.png)
<p align="center"> <sub> <b>Figure</b>: The targeted expense at index 2 before editing </sub> </p>

![afterEditCommand](images/commands/afterEditCommand.png)
<p align="center"> <sub> <b>Figure</b>: The targeted expense at index 2 after editing </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* Edits an entry that matches the entry name and changes the parameters of an entry depending on which are specified
* The tags d/ and a/ have to be used to indicate which field specifies DESCRIPTION and which field specifies AMOUNT
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries 
(e.g. if there are 10 entries, the `INDEX` given cannot be > 10)

</div>


### 7.2.4 Locating entries by description: `find`

You can use this command to list expenses and/or revenues that have the given keyword(s) in their descriptions. This is 
useful when you want to find some particular entries to [`delete`](#deleting-an-entry-delete) or [`edit`](#editing-an-entry-edit). 
If there is no expense or revenue found, the respective list will be empty.

**Format:** `find [c/CATEGORY] k/KEYWORDS`

<div markdown="block" class="alert alert-success">

:green_book: **Example:** 

* `find c/expense k/watercolours canvas`: Finds all expenses with keywords `watercolours` and/or `canvas` in their 
description.
* `find c/revenue k/phone`: Finds all revenues with keyword `phone` in their 
description.
* `find k/canvas earrings`: Finds all expenses and revenues with keywords `canvas` and/or `earrings` in their 
description (Figure).
 
</div>

<br>![findCommand](images/commands/findCommand.PNG)
<p align="center"> <sub> <b>Figure</b>: Find command successfully executed </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* The search is case-insensitive. e.g. `grocery` will match `grocery` and `Grocery`.
* Only the entry's description is searched.
* The search does not match partial words. e.g. `food` will match `food` and `fast food` but not `fastfood`.
* Entries matching at least one keyword will be returned (i.e. OR search). e.g. `Food` will return `Hawker food`, 
`Restaurant Food`.
* The `c/CATEGORY` is optional.

</div>

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:** When there are a lot of entries, and you want to delete something, 
you can use `find` to search for it in the list and use [`delete ENTRY_INDEX`](#deleting-an-entry-delete) to remove it from the updated
list, with the updated `ENTRY INDEX`.

</div>


### 7.2.5 Listing all entries: `list`

You can use this command to list all entries in the current account when you want to have an overview of you account.

**Format:** `list`
 
<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `list`: Returns the latest list of all entries

</div>


### 7.2.6 Clearing all expenses or revenue: `clear`

You can use this command to clear all entries from a particular category when you do not need to track them anymore.
 
**Format:** `clear`
 
<div markdown="block" class="alert alert-success">

:green_book: **Example:**


* `clear c/expense`: clears all entries visible in expense list (**Figure**). 
* `clear c/revenue`: clears all entries visible in revenue list.

</div>

![clearCommand](images/commands/clearCommand.png)
<p align="center"> <sub> <b>Figure</b>: The targeted expense at index 2 after editing </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

The `clear` command will clear all entries in the stipulated category. If you would like to delete selected
entries, use the `delete` command instead.

</div>


### 7.2.7 Calculating net profits based on expenses and revenues: `profit` 

If you wish to calculate the profits you have currently, you may use this command to do so.
 
**Format:** `profit`
 
<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `profit`: Returns the profits (**Figure**) by taking the difference between the expenses and revenues.

</div>

![profitCommand](images/commands/profitCommand.PNG)
<p align="center"> <sub> <b>Figure</b>: Profit command successfully executed </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

The `profit` function will show deficits as a negative number should expenses be greater than revenue.
</div>


### 7.2.8 Undoing Entry-level commands: `undo`

You can use this command to return to the state of entries prior to previous command.

 
**Format:** `undo`
 
<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `undo`: Returns the state of expenses and revenues prior to the previous command. For example, if the 
[`delete`](#deleting-an-entry-delete) command was used (**Figure**), using `undo` returns account to the state before the 
[`delete`](#deleting-an-entry-delete) command was used (**Figure**).

</div>

![deleteCommand](images/commands/deleteCommand.png)
<p align="center"> <sub> <b>Figure</b>: Before the undo command, an expense was deleted </sub> </p>

![undoCommand](images/commands/undoCommand.png)
<p align="center"> <sub> <b>Figure</b>: After the undo command, the deleted expense is added again </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Do note that the `undo` command can only undo  [`add`](#adding-an-entry-add), [`delete`](#deleting-entries-delete),
[`edit`](#editing-an-entry-edit) and [`clear`](#clearing-all-expenses-or-revenue-clear) commands at the entry-level. 
Account-level commands cannot be reverted using the `undo` command.

</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

* There is no `redo` command to revert your `undo` command. Do use the `undo` command with caution!
* Once you exit the app, all the previous states will be lost. Do confirm your changes to the entries in the account 
before you exit the app!

</div>

### 7.2.9 Calculating total expenses or revenues: `total`

You can use this command to do calculate the total expenses or revenues in your current account.

**Format:** `total c/CATEGORY`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `total c/expense`: Returns the sum of all the expenses.
* `total c/revenue`: Returns the sum of all the revenues.

</div>

(add screenshots to show the total expense/revenue message)





## 7.3 Account-level Commands
Account-Level commands involves managing the different accounts in the app. 

### 7.3.1 Adding new account: `newacc`

You can create a new account to manage a different set of entries with this command. 

**Format**: `newacc n/NAME`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `newacc n/My Flower Shop`: adds a new account, `My Flower Shop`, with no expenses or revenues.
* `newacc n/$uper $avers`: adds a new account, `$uper $avers`, with no expenses or revenues (**Figure**). 

</div>

![addAccountCommand](images/commands/addAccountCommand.png)
<p align="center"> <sub> <b>Figure</b>: A new account called $uper $avers is added </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* The app does not automatically switch to the new account. Once the account is created, you can check it using  
[`listacc`](#listing-accounts-you-have-listacc) or switch to it using [`switchacc`](#switching-accounts-switchacc). 
* You cannot create a account with the same name of an existing account.

</div>

### 7.3.2 Deleting an account: `deleteacc`

You can remove the account using this command. This command is useful for clearing accounts that you might not be using
anymore.

**Format:** `deleteacc INDEX`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `deleteacc 3`: Deletes third account based on list generated by using [`listacc`](#listing-accounts-you-have-listacc)
 (**Figure**).

</div>

![deleteAccountCommand](images/commands/deleteAccountCommand.png)
<p align="center"> <sub> <b>Figure</b>: An account called My Flower Shop is deleted </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Do note that _Common Cents_ prevents you from deleting the account if you are currently on that account
or if the account is your only account left.
</div>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

If you are unsure of the account index, use [`listacc`](#listing-accounts-you-have-listacc) to check the index!
</div>


<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Deleting your account means all the data on entries in the account is lost as well. Also, deleting is an 
irreversible action and cannot be undone. Do delete your account with caution!

</div>

### 7.3.3 Editing the name of the current account: `editacc`

You can edit the name of the current account you are on using this command.

**Format:** `editacc n/NAME`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `editacc n/Bob's Bakery`: Replaces the name of the current account, `Default Account 1` to `Bob's Bakery` (**Figure**).

</div>

![editAccNameCommand](images/commands/editAccNameCommand.png)
<p align="center"> <sub> <b>Figure</b>: The currently active account, "Default Account 1", is renamed to "Bob's Bakery" </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* You cannot edit account names other than the account you are currently on. To edit the name
of another account, you can use [`switchacc`](#switching-accounts-switchacc) to switch to the other account
and edit the name from there.
* You cannot edit the account name to a name of another existing account.

</div>

### 7.3.4 Listing accounts you have: `listacc`

You can check the accounts you have in the app by using this command. Each account will be numbered with an index and 
displayed as their names.

**Format:** `listacc`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `listacc`: Shows a numbered list of the account names as a message (**Figure**).

</div>

![listAccCommand](images/commands/listAccCommand.png)
<p align="center"> <sub> <b>Figure</b>: All the accounts are listed in the Result Display </sub> </p>

### 7.3.5 Switching accounts: `switchacc`

You can use this command to switch to the desired account. This is useful if you want to manage entries in the 
account or edit the name of the desired account.

**Format:** `switchacc INDEX`

<div markdown="block" class="alert alert-success">

:green_book: **Example:**

* `switchacc 2`: Switches to the second account based on list generated by 
[`listacc`](#listing-accounts-you-have-listacc) (**Figure**).

</div>

![switchAccCommand](images/commands/switchAccCommand.png)
<p align="center"> <sub> <b>Figure</b>: Current account has been switched to "Default account 2" </sub> </p>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

If you are unsure of the account index, use [`listacc`](#listing-accounts-you-have-listacc) to check the index!

</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Once you use the `switchacc` command, all the previous states for the [`undo`](#undoing-entry-level-commands-undo) will
be lost. This would mean that if you choose to switch back to the account, you cannot undo the previous entry-level
commands anymore. Do confirm your changes to the entries in the account before you switch account!

</div>

--------------------------------------------------------------------------------------------------------------------
# 8. Frequently Asked Questions (FAQ)

This section contains a few frequently asked questions with regard to _Common Cents_.

## 8.1 General Inquiry
This section features general questions about Common Cents that are not specific to the features.

1. **Question:** How do I transfer my data to another Computer?<br>
**Answer:** Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous CommonCents home folder.

2. **Question:** Where can I make bug reports?<br>
**Answer:** You may submit a bug report directly to our team [here].



## 8.2 Features Inquiry
This section features feature-related questions about Common Cents.
1. **Question:** What happens if I forget my account names?<br>
**Answer:** You may use the `listacc` command to see a full list of your existing accounts.

2. **Question:** I encountered this situation when using the features as shown in the screenshot below. I understand that the 
prefix indicated is wrong after checking `add` section in the User Guide. However, the error message states that my category is
wrong. Can I clarify on this situation?<br>
*(Insert screenshot for the case "edit 2 c/expense n/buy McSpicy a/8.60")*

   **Answer:** 
Yes, the prefix indicated is wrong as it should be `d/` instead of `n/`. As a result, the category parameter is read as
`expense n/buy McSpicy a/8.60` instead of `expense`. Do remember to check that the **prefixes and parameters required for a command
are correct!** If you are unsure of the prefixes and parameters required, do check the sections under features above! 
--------------------------------------------------------------------------------------------------------------------

# 9. Command summary

The table below summarises the above commands in the order of appearance in this User Guide for your reference. 

Action | Format, Examples
--------|------------------
[**Help**](#viewing-help--help) | `help`
[**Exit**](#exiting-the-program--exit) | `exit`
[**Add**](#adding-an-entry-add) | `add c/CATEGORY d/DESCRIPTION a/AMOUNT [t/TAG]`
[**Delete**](#deleting-an-entry-delete) | `delete ENTRY_INDEX c/CATEGORY`
[**Edit**](#editing-an-entry-edit) | `edit ENTRY_INDEX c/CATEGORY [d/DESCRIPTION] [a/AMOUNT] [t/TAG]`
[**Find**](#locating-entries-by-description-find) | `find k/KEYWORD [MORE_KEYWORDS]`
[**List**](#listing-all-entries-list) | `list`
[**Clear**](#clearing-all-expenses-or-revenue-clear) | `clear c/CATEGORY`
[**Profit**](#calculating-net-profits-based-on-expenses-and-revenues-profit) | `profit`
[**Undo**](#undoing-entry-level-commands-undo) | `undo`
[**Total**](#calculating-total-expenses-or-revenues-total) | `total c/CATEGORY`
[**New Account**](#adding-new-account-newacc) | `newacc n/NAME`
[**Delete Account**](#deleting-an-account-deleteacc) | `deleteacc INDEX`
[**Edit Account's Name**](#editing-the-name-of-the-current-account-editacc) | `editacc n/NAME`
[**List Account**](#listing-accounts-you-have-listacc) | `listacc`
[**Switch Account**](#switching-accounts-switchacc) | `switchacc INDEX`

--------------------------------------------------------------------------------------------------------------------

# Acknowledgement

* The project is a brown-field project, based on AddressBook-Level3 by the [SE-EDU initiative](https://se-education.org/).
* The background image is [Designed by Freepik](http://www.freepik.com). 
