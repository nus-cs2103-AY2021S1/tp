---
layout: page
title: User Guide
---

<img src="images/tCheckLogo.png" width="200" height="200" />

Welcome to the tCheck User Guide! Thank you for choosing tCheck, the most popular command-line milk tea shop management desktop application in the T-Sugar chain!

tCheck offers an integrated system to efficiently manage a T-Sugar shop, by providing sales tracking, ingredient tracking and employees' contact management in one platform.

This guide will help you to get started with tCheck, and provide useful tips along the way.



* Table of Contents
    * [About this document](#about-this-document)
    * [Quick start](#quick-start)
    * [Features](#features)
        * [Commands - Sales Tracking](#commands-sales-tracking) 
        * [Commands - Ingredients Tracking](#commands-ingredients-tracking)
        * [Commands - Manpower Management](#commands-manpower-management)
        * [Commands - General](#commands-general)
    * [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## About this document  <a name="about-this-document"></a>
This document introduces the features of tCheck. 

The following are symbols and formatting used in this document:

:bulb: 
Tips are used to describe small features or techniques
that may come in handy when using tCheck!


:information_source: 
Notes describe certain features or behaviour that may
not be so obvious!

--------------------------------------------------------------------------------------------------------------------

## Quick start <a name="quick-start"></a>

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tCheck.jar` from [here](https://github.com/AY2021S1-CS2103T-T12-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your tCheck.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/81234567 t/part time` : Adds an employee's contact
    named `John Doe` to tCheck.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features <a name="features"></a>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `ingredient NAME`, `NAME` is a parameter which can be used as `ingredient milk`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `...`​ after them can be used once or multiple times.<br>
  e.g. `sales A/NUM B/NUM C/NUM ...` can be used as `sales BSBM/100` or `sales BSBM/100 BSBBT/120`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### 1. Commands - Sales Tracking <a name="commands-sales-tracking"></a>

<div markdown="span" class="alert alert-primary">

**:information_source: Notes about available drinks:**<br>

Currently, there are 6 types of drinks that can be tracked by tCheck's sales tracker. The drinks and their respective
 abbreviations are shown as below:<br>
 
* `BSBM`  : Brown Sugar Boba Milk<br>

* `BSBBT` : Brown Sugar Boba Black Tea<br>

* `BSBGT` : Brown Sugar Boba Green Tea<br>

* `BSPM`  : Brown Sugar Pearl Milk<br>

* `BSPBT` : Brown Sugar Pearl Black Tea<br>

* `BSPGT` : Brown Sugar Pearl Green Tea<br>

</div>


#### 1.1 Updating the number of drinks sold : `s-update`
Updates the number of drinks sold for each drink type as given in the user input.


Format: `s-update A/NUM B/NUM C/NUM ...`
* `A`, `B`, `C` are abbreviations for the drink types.
* `NUM` refers to the number of drinks sold. It should be a non-negative integer.

Example:
* `s-update BSBM/100 BSBBT/120` Updates the sales of Brown Sugar Boba Milk `BSBM` to 100 and
 Brown Sugar Boba Black Tea `BSBBT` to 120.
 
 <div markdown="span" class="alert alert-primary">
 
:information_source: Note about the update: <br>

The record in tCheck will be overwritten by the input that you provide. <br>
 
 </div>

#### 1.2 Listing the number of drinks sold : `s-list`
Shows a list of all types of drinks sold in the Sales Tracker.

Format: `s-list`

#### 1.3 Finds the number of drinks by keywords : `s-find`

Finds all drinks where their abbreviated names match the KEYWORD(s).

Format: `s-find KEYWORD [MORE_KEYWORDS] ...`

* The search is case-insensitive. e.g `bsbbt` will match `BSBBT`.
* Only the drink's abbreviated name is searched.
* Only full words will be matched e.g. `BSB` will not match `BSBBT`.
* Drinks matching at least one keyword will be returned.
  e.g. `BSBBT BSBM` will return `BSBBT`, `BSBM`.

Examples:
* `s-find BSBBT` returns `BSBBT`'s sales data.
* `s-find BSBBT BSBM` returns `BSBBT`'s sales data and `BSBM`'s sales data.<br>
 
#### 1.4 Ranking the list of drinks sold : `s-rank`
Shows a ranked list of all types of drinks sold in the Sales Tracker. The list of drinks
is ranked from the most popular to least popular.
  
Format: `s-rank`

 
### 2. Commands - Ingredients Tracking <a name="commands-ingredients-tracking"></a>

    
<div markdown="span" class="alert alert-primary">

**:information_source: Notes about ingredients:**<br>

* Unit of measurement for ingredients:<br>
    * - Unit for solid items / jelly (Pearl, Boba and Brown Sugar) : **KG**<br>
    * - Unit for liquids (Milk, Black Tea and Green Tea) : **L**<br>

* All ingredients' levels are set to 0.<br>

* All ingredients which are available and thus can be set are predefined in the ingredient book.<br>
  
* Please note that the ingredient names are case-sensitive.<br>

* Here is a comprehensive list of all available ingredients :<br>
    * - Milk<br>
    * - Pearl<br>
    * - Boba<br>
    * - Black Tea<br>
    * - Green Tea<br>
    * - Brown Sugar<br>

</div>

#### 2.1 Setting one ingredient's level : `i-set`

Sets the level of one single ingredient predefined in the ingredient book to the specified amount.

Format: `i-set i/INGREDIENT_NAME m/AMOUNT`

* Sets the `INGREDIENT_NAME` to the specified `AMOUNT`.

Example:
* `i-set i/Milk m/15`


#### 2.2 Setting all ingredients' levels to different specified amounts : `i-set-all`

Sets all ingredients' levels to different specified amounts according to user inputs.

Format: `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_BROWN_SUGAR`

* Sets all ingredients' levels as such : Milk : `AMOUNT_FOR_MILK` L, Pearl : `AMOUNT_FOR_PEARL` KG, Boba : `AMOUNT_FOR_BOBA` KG, Black Tea : `AMOUNT_FOR_BLACK_TEA` L, Green Tea: `AMOUNT_FOR_GREEN_TEA`, Brown Sugar : `AMOUNT_FOR_BROWN_SUGAR` KG.

Example:
* `i-set-all M/20 P/20 B/20 L/50 G/80 S/50`

#### 2.3 Setting all ingredients to different levels : `i-set-default`

Sets all ingredients' levels to their pre-determined default levels for a stall.

Format: `i-set-default`

* Sets all ingredients' levels to their default levels : Milk : 50 L, Pearl : 20 KG, Boba : 20 KG, Black Tea : 50 L, Green Tea : 50 L, Brown Sugar : 20 KG.

Example:
* `i-set-default`

#### 2.4 Listing all ingredients' levels : `i-list`
Lists the ingredient's levels of all ingredient types.

Format: `i-list`

#### 2.5 Viewing a single ingredient's level : `i-view-single`
Shows the ingredient's level of a particular type of ingredient that is specified by the user’s command.

Format: `i-view-single i/INGREDIENT_NAME`

Example:
* `i-view-single i/Green Tea`
Shows the amount of green tea recorded by tCheck.

#### 2.6 Resetting all ingredients' levels to zero : `i-reset-all`
Resets all types of ingredients' levels to zero in tCheck.

Format: `i-reset-all`

#### 2.7 Listing all ingredients that user should restock : `i-restock`
Lists the ingredient's levels of all ingredient types that fall below their minimum stock levels and require the user to 
restock. 

The table below shows the minimum stock levels of different types of ingredients:

Ingredient Type | Minimum Stock Level
-------|------------------------------
**Milk** | 5 L
**Pearl** | 5 KG
**Boba** | 5 KG
**Black Tea** | 5 L
**Green Tea** | 5 L
**Brown Sugar** | 5 KG

Format: `i-restock`

### 3. Commands - Manpower Management <a name="commands-manpower-management"></a>

#### 3.1 Adding an employee : `c-add`

Adds an employee to the contact list.

Format: `c-add n/NAME p/PHONE_NUMBER e/EMERGENCY_CONTACT a/ADDRESS [t/TAG] ...`

<div markdown="span" class="alert alert-primary">:bulb: 

**Tip:**
An employee can have any number of tags (including 0)
</div>

Examples:
* `c-add n/John Doe p/98765432 e/81234567 a/Blk 123 ABC Road`.
* `c-add n/Betsy Crowe e/81234567 p/1234567 a/Blk 120 ABC Road t/Friday t/PartTime`.

#### 3.2 Listing all employees : `c-list`

Shows a list of all employees in the contact list.


Format: `c-list`
  
#### 3.3 Editing a person : `c-edit`


Edits the corresponding contact information in the contact list.

Format: `c-edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMERGENCY_CONTACT] [a/ADDRESS] [t/TAG] ...`

* Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, ...​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the employee will be removed i.e adding of tags is not cumulative.
* You can remove all the employees’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `c-edit 1 p/91234567 e/81234567` Edits the phone number and emergency contact of the 1st employee to be `91234567` and
 `81234567` respectively.
*  `c-edit 2 n/Betsy Crower t/` Edits the name of the 2nd employee to be `Betsy Crower` and clears all existing tags.


#### 3.4 Locating persons by keywords: `c-find`

Finds all active(unarchived) contacts that contain the KEYWORD(s) in their names.

Format: `c-find KEYWORD [MORE_KEYWORDS] ...`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Employees matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `c-find John` returns `john` and `John Doe`.
* `c-find alex david` returns `Alex Yeoh`, `David Li`.<br>

#### 3.5 Locating employees based on matching tags: `c-tag-find`

Finds all employees whose tags contain the one or more `KEYWORD(s)`.

Format: `c-tag-find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. For example, `Friday` will match `friday`.<br>
* The order of the keywords does not matter. For example, `monday friday` will match `monday friday`.<br>
* Only the tag names are searched.<br>
* Only full words will be matched. For example, `Fri` will not match `Friday`.<br>
* Employees whose tag(s) matching at least one keyword will be listed in the Employee
 Directory inside the _Main View_ (i.e. `OR` search).  For example, `c-tag-find Monday Friday` will find employees whose
  tags contain `Monday` or `Friday`.<br>

<div markdown="span" class="alert alert-primary">

**:information_source: Note:**<br>
In tCheck, if an employee contains a tag for a specific day, it means this employee is available on that day
. You may set an employee's tags when adding the employee into tCheck (Refer to `c-add` command), or edit the
tag(s) by editing employee's information feature (Refer to `c-edit` command).<br>

</div>

Example:
Let's say you want to find out who is available to work on `Monday` or `Friday`. In this case, the two 
keywords are `Monday` and `Friday`. You can follow these instructions:

Steps to find employees who are available to work on `Monday` or `Friday`:
1. Type `c-tag-find monday friday` into the _Command Box_.
2. Press "Enter" to execute.

Outcome:
1. The _Result Display_ will show the total number of employees who are can work on `Monday` or `Friday`.

2. Employees who are can work on `Monday` or `Friday` will be listed in the Employee Directory inside the _Main View_.

{Screenshot}

#### 3.6 Locating all employees who are available to work today: `c-today`

Finds employees whose tags contain today's day (i.e. `Wednesday`, `Thursday`, etc).

Format: `c-today`

* The search is case-insensitive (e.g. `Friday` will match `friday`).<br>
* The application will automatically get today's day based on the computer's current date.<br>
* Only full words will be matched (e.g. `Fri` will not match `Friday`).<br>

<div markdown="span" class="alert alert-primary">

**:information_source: Note:**<br>
In tCheck, if an employee contains a tag for a specific day, it means this employee is available on that day
. You may set an employee's tags when adding the employee into tCheck (Refer to `c-add` command), or edit the
tag(s) by editing employee's information feature (Refer to `c-edit` command).<br>

</div>

Example:
Let's say today is `Thursday`. You want to find out who are the available manpower for today. 
You can follow these instructions:

Steps to find today's available manpower:
1. Type `c-today` into the _Command Box_.
2. Press "Enter" to execute.

Outcome:
1. The _Result Display_ will show 3 information:
    
    a. Today's day. (e.g. Today is THURSDAY.)
    
    b. Total number of employees who are available today.
    
    c. The success message.
2. The available manpower for today will be listed in the Employee Directory inside the _Main View_.

{Screenshot}

#### 3.7 Locating all employees who are available to work tomorrow: `c-tomorrow`

Finds employees whose tags contain tomorrow's day (i.e. `Wednesday`, `Thursday`, etc).

Format: `c-tomorrow`

* The search is case-insensitive. For example, `Friday` will match `friday`.<br>
* The application will automatically get tomorrow's day based on the computer's current date.<br>
* Only full words will be marched (e.g. `Fri` will not match `Friday`).<br>

<div markdown="span" class="alert alert-primary">

**:information_source: Note:**<br>
In tCheck, if an employee contains a tag for a specific day, it means this employee is available on that day
. You may set an employee's tags when adding the employee into tCheck (Refer to `c-add` command), or edit the
tag(s) by editing employee's information feature (Refer to `c-edit` command).<br>

</div>

Example:
* Let's say tomorrow is `Friday`. You want to find out who are the available manpower for tomorrow. 
You can follow these instructions:

Steps to find tomorrow's available manpower:
1. Type `c-tomorrow` into the _Command Box_.
2. Press "Enter" to execute.

Outcome:
1. The _Result Display_ will show 3 information:
    
    a. Tomorrow's day. (e.g. Today is FRIDAY.)
    
    b. Total number of employees who are available tomorrow.
    
    c. The success message.
2. The available manpower for tomorrow will be listed in the Employee Directory inside the _Main View_.

{Screenshot}

#### 3.8 Deleting a person : `c-delete`

Deletes the specified employee from the contact list.

Format: `c-delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...​

Examples:
* `c-list` followed by `c-delete 2` deletes the 2nd employee in the contact list.
* `c-find Betsy` followed by `c-delete 1` deletes the 1st employee in the results of the `c-find` command.

#### 3.9 Clearing all entries : `c-clear`

Clears all entries from the contact list.

Format: `c-clear`

#### 3.10 Archiving an employee : `c-archive`

Archives the specified employee from tCheck.

Format: `c-archive INDEX`

* Archives the employee at the specified `INDEX` in the Employee Directory inside the _Main View_.<br>
* The `INDEX` refers to the index number shown in the current Employee Directory inside the _Main View_.<br>
* The `INDEX` **must be a positive integer** (e.g. 1, 2, 3, ...​).<br>
* The `INDEX` **should not be larger than** the total number of employees listed in the current 
_Employee Directory_ pane. For example, there are 2 employees listed in the current Employee Directory inside the _Main
 View_. The `INDEX` should be less or equal to 2.<br>

<div markdown="span" class="alert alert-primary">

**:information_source: Notes about Employee Directory:**<br>

If _Employee Directory_ currently shows a list of archived employees (archived employees are displayed with a red
 color label, named as `[Archived]`, in front of the name), command `c-active-list` needs to be used first to see a
  list of all your employees. Then, `c-archive INDEX` can be used to archive a specified employee identified
  by `INDEX`.<br>

</div>

Example:
Let's say one of your employees, Alex Yeoh, is no longer working in your bubble tea shop. But he may come back
to work again in the future. You, as a store manager, want to remove him from your employee directory without
permanently deleting his information, so that you can retrieve/move back his information to your
employee directory when he comes back to work again. To archive the first employee, Alex Yeoh, in the sample data, you
can follow these instructions:
 
1. Type `c-archive 1` into the _Command Box_.
2. Press "Enter" to execute.


Outcome:
1. The _Result Display_ will show a success message with the employee's name that you have archived.
2. Alex Yeoh's information will be removed from Employee Directory inside the _Main View_.

{Screenshot}

#### 3.11 Archiving all employees : `c-archive-all`

Archives all employees in the Employee Directory inside the _Main View_.

Format: `c-archive-all`

<div markdown="span" class="alert alert-primary">

**:information_source: Notes about Employee Directory:**<br>

If _Employee Directory_ currently shows a list of archived employees (archived employees are displayed with a red
 color label, named as `[Archived]`, in front of their names), command `c-active-list` needs to be used first to see a
  list of all your employees. Then, `c-archive-all` can be used to archive all employees.<br>

</div>

Example:
Let's say after you familiarize yourself with tCheck, you want to remove all sample data in employee directory. But
 you don't want to permanently delete those data, so that you can use them as a reference in the future. To archive
  all employees, you can follow these instructions:

Steps to archive all employees in the sample data:
1. Type `c-archive-all` into the _Command Box_.
2. Press "Enter" to execute.


Outcome:
1. The _Result Display_ will show a success message.
2. All employees will be removed from Employee Directory inside the _Main View_.

{Screenshot}

#### 3.12 Listing all archived employees : `c-archive-list`

Shows a list of all archived employees in Employee Directory inside the _Main View_.

Format: `c-archive-list`

<div markdown="span" class="alert alert-primary">

**:information_source: Notes about displaying archived employees in tCheck:**<br>

Archived employees will be shown with a red color label, named as `[Archived]`, in front of their names. You may
 refer to the screenshot below.<br>

</div>

Example:
Let's say you want to see an archived employee's information. You can follow these instructions:

Steps to see archived employees:
1. Type `c-archive-list` into the _Command Box_.
2. Press "Enter" to execute.


Outcome:
1. The _Result Display_ will show a success message.
2. All archived employees will be listed in Employee Directory inside the _Main View_.

{Screenshot}

#### 3.13 Unarchiving an employee : `c-unarchive`
Unarchives the specified employee from the archived employee list.

Format: `c-unarchive INDEX`

* Unarchives the employee at the specified `INDEX` in the Employee Directory inside the _Main View_.<br>
* The `INDEX` refers to the index number shown in the current Employee Directory inside the _Main View_.<br>
* The `INDEX` **must be a positive integer** (e.g. 1, 2, 3, ...​).<br>
* The `INDEX` **should not be larger than** the total number of employees listed in the current
Employee Directory inside the _Main View_. For example, there are 2 employees listed in the current Employee
 Directory inside the _Main View_. The `INDEX`should be less or equal to 2.<br>

<div markdown="span" class="alert alert-primary">

**:information_source: Notes about Employee Directory:**<br>

If _Employee Directory_ currently shows a list of active/unarchived employees (Archived employees are displayed with a
 red color label, named as `[Archived]`, in front of their names. While employees in the active/unarchived employee list
  don't have this red label.), command `c-archive-list` needs to be used first to see a list of all your archived
  employees. Then, `c-unarchive INDEX` can be used to unarchive a specified
   employee identified by `INDEX`.<br>

</div>

Example:
Let's say one of your ex-employees, Alex Yeoh, is now rehired to work in your bubble tea shop. Since he worked in
 the store before, and you archived his data when he left last time. Now, instead of typing his information again and
  use `c-add` command to add him into your employee directory. You can just unarchive him
 and move his data to your employee directory. You can follow these instructions:

Steps to unarchive the first employee, Alex Yeoh, in the archived employee list:
1. Type `c-unarchive 1` into the _Command Box_.
2. Press "Enter" to execute.


Outcome:
1. The _Result Display_ will show a success message with the employee's name that you have unarchived.
2. Alex Yeoh's information will be removed from the archived employee list shown in Employee Directory inside
 the _Main View_.

{Screenshot}

### 4. Commands - General <a name="commands-general"></a>
#### 4.1 Viewing help : `help`

Displays a brief explanation of the list of commands, and a link to the help page, which is the user guide.

Format: `help`

#### 4.2 Exiting the program : `exit`

Exits the program.

Format: `exit`

#### 4.3 Saving the data :
All tCheck data (i.e. ingredient data, sales data, employees' data) are saved in the hard disk automatically after each
 command. There is no need to save manually by entering any command.
 
 
<div markdown="span" class="alert alert-primary">

 **:information_source: Notes about data files:**<br>
User should not attempt to make any change in all data files.<br>

</div>

#### 4.4 Calendar :

tCheck also shows a current month's calendar when you start the application. You may use it for your working planning.


<div markdown="span" class="alert alert-primary">

 **:information_source: Notes:**<br>
 * In the current version, tCheck can only show the calendar for the current month.<br>
 
 </div>
 
 {Screenshot}
 
## Command summary <a name="command-summary"></a>

### Sales Tracking

Action | Format, Examples
-------|------------------------------
**Update**| `s-update A/NUM B/NUM C/NUM ...` <br> e.g., `s-update BSBM/100 BSBBT/120`
**List**| `s-list`
**Find**| `s-find KEYWORD [MORE_KEYWORDS] ...` <br> e.g., `s-find BSBM BSBBT`
**Rank**| `s-rank`


### Ingredients  Tracking

Action | Format, Examples
-------|------------------------------
**Set a single ingredient**  | `i-set i/INGREDIENT_NAME m/AMOUNT` <br> e.g., `i-set i/Milk m/20`
**Set all ingredients**  | `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA L/AMOUNT_FOR_BLACK_TEA G/AMOUNT_FOR_GREEN_TEA S/AMOUNT_FOR_BROWN_SUGAR` <br> e.g., `i-set-all M/20 P/20 B/20 L/50 G/20 S/100`
**Set all ingredients to default**  | `i-set-default` <br> e.g., `i-set-default`
**View a single ingredient**| `i-view-single i/INGREDIENT_NAME`  <br> e.g., `i-view-single i/Milk`
**Reset all ingredients**| `i-reset-all`
**View all ingredients that the user should restock**| `i-restock`
**List**| `i-list`

### Manpower Management

Action | Format, Examples
-------|------------------------------
**Add** | `c-add n/NAME p/PHONE_NUMBER e/EMERGENCY_CONTACT a/ADDRESS [t/TAG] ...` <br> e.g., `c-add n/James Ho p/22224444 e/81234567 a/Blk 123 ABC Road t/Friday t/PartTime`
**List** | `c-list`
**Edit** | `c-edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMERGENCY_CONTACT] [t/TAG] ...`<br> e.g.,`c-edit 2 n/James Lee e/81234567`
**Find by name** | `c-find KEYWORD [MORE_KEYWORDS] ...`<br> e.g., `c-find James Jake`
**Find by tag(s)** | `c-tag-find KEYWORD [MORE_KEYWORDS]`<br> e.g., `c-tag-find Friday Monday PartTime`
**Find available manpower** | 1. **For today:**  `c-today`<br>2. **For the next day:**  `c-tomorrow` <br>
**Delete** | `c-delete INDEX`<br> e.g., `c-delete 3`
**Clear** | `c-clear`
**Archive** |  1. **Archive \(1 employee\):**  `c-archive INDEX`<br> e.g., `c-archive 1` <br>2. **Archive \(all employees\):**  `c-archive-all` <br>3. **List all archived data:**  `c-archive-list`</br>
**Unarchive** | `c-unarchive INDEX`<br> e.g., `c-unarchive 1` 

### General

Action | Format, Examples
-------|------------------------------
**Help** | `help`
**Exit** | `exit`

 
