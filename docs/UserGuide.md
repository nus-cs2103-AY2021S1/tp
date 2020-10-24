---
layout: page
title: User Guide
---

tCheck is a desktop application that helps bubble tea store managers manage the staffs’ contact information, 
store’s inventory and historical sales data. It is optimized for CLI users to update and retrieve the information 
more efficiently.


* Table of Contents
    * [Quick start](#quick-start)
    * [Features](#features)
        * [Commands - General](#commands-general)
        * [Commands - Contact details](#commands-contact-details) 
        * [Commands - Ingredients Tracking](#commands-ingredients-tracking)
        * [Commands - Sales Tracking](#commands-sales-tracking) 
    * [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start `[Release coming soon]` <a name="quick-start"></a>

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

* Items with `…`​ after them can be used once or multiple times.<br>
  e.g. `sales A/NUM B/NUM C/NUM …` can be used as `sales BSBM/100` or `sales BSBM/100 BSBBT/120`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### 1. Commands - General <a name="commands-general"></a>
#### 1.1 Viewing help : `help`

Displays a brief explanation of the list of commands, and a link to the help page, which is the user guide.

Format: `help`

#### 1.2 Exiting the program : `exit`

Exits the program.

Format: `exit`

#### 1.3 Saving the data :

All tCheck data (i.e. contact details, ingredient data, sales data) are saved in the hard disk automatically after any
 command that changes the data. There is no need to save manually.

### 2. Commands - Contact details <a name="commands-contact-details"></a>

#### 2.1 Adding a person : `add`

Adds a person to the contact list.

Format: `add n/NAME p/PHONE_NUMBER e/EMERGENCY_CONTACT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/81234567`.
* `add n/Betsy Crowe e/81234567 p/1234567 t/morning shift t/part-time`.

#### 2.2 Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`
  
#### 2.3 Editing a person : `edit`

Edits the corresponding contact information in the contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMERGENCY_CONTACT] [t/TAG] …​​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/81234567` Edits the phone number and emergency contact of the 1st person to be `91234567` and
 `81234567` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.


#### 2.4 Locating persons by keywords: `search`

Finds all contacts that contain the KEYWORD(s).

Format: `search KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find John` returns `john` and `John Doe`.
* `find alex david` returns `Alex Yeoh`, `David Li`.<br>

#### 2.5 Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the contact list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

#### 2.6 Clearing all entries : `clear`

Clears all entries from the contact list.

Format: `clear`

#### 2.7 Archiving a person : `archive`
Archives the specified employee's contact detail from the tCheck.

Format: `archive INDEX`

* Archives the employee at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `archive 2` archives the 2nd person in the employees' contact details.
* `find Betsy` followed by `archive 1` deletes the 1st person in the results of the `find` command.

#### 2.8 Archiving all employees : `archive all`
Archives all employees' contact detail from the tCheck.

Format: `archive all`

#### 2.9 Listing all archived employees : `archive list`
Shows a list of all archived employees' contact details in tCheck.

Format: `archive list`

### 3. Commands - Ingredients Tracking <a name="commands-ingredients-tracking"></a>

* Unit of measurement for ingredients: 
    * Unit for solid items / jelly (Pearl, Boba and Brown Sugar) : **KG**
    * Unit for liquids (Milk and Oolong Tea) : **L**
    
#### 3.1 Setting one ingredient's level : `i-set`

Sets the level of one single ingredient predefined in the ingredient book to the specified amount.

Format: `i-set i/INGREDIENT_NAME m/AMOUNT`

* Sets the `INGREDIENT_NAME` to the specified `AMOUNT`.

Example:
* `i-set i/Milk m/15`


<div markdown="span" class="alert alert-primary">:bulb: **Notice:**
All ingredients which are available and thus can be set are predefined in the ingredient book. 
Here is a comprehensive list of all available ingredients : Milk, Pearl, Boba, Oolong Tea, Brown Sugar. 
Please note that the ingredient names are case-sensitive.
</div>

#### 3.2 Setting all ingredients' levels to different specified amounts : `i-set-all`

Sets all ingredients' levels to different specified amounts according to user inputs.

Format: `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA O/AMOUNT_FOR_OOLONG_TEA S/AMOUNT_FOR_SUGAR`

* Sets all ingredients' levels as such : Milk : `AMOUNT_FOR_MILK` L, Pearl : `AMOUNT_FOR_PREAL` KG, Boba : `AMOUNT_FOR_BOBA` KG, Oolong Tea : `AMOUNT_FOR_OOLONG_TEA` L, Brown Sugar : `AMOUNT_FOR_BROWN_SUGAR` KG.

Example:
* `i-set-all M/20 P/20 B/20 O/50 S/50`

#### 3.3 Setting all ingredients to different levels : `i-set-default`

Sets all ingredients' levels to their pre-determined default levels for a stall.

Format: `i-set-default`

* Sets all ingredients' levels to their default levels : Milk : 50 L, Pearl : 20 KG, Boba : 20 KG, Oolong Tea : 50 L, Brown Sugar : 20 KG.

Example:
* `i-set-default`


<div markdown="span" class="alert alert-primary">:bulb: **Notice:**
Users inputs will be validated, if an invalid command is given, the program will show relevant error messages to notify the user to re-input.
Example of Error Message:  `No ingredient called ‘suger’ found !` .
</div>

#### 3.4 View a Single Ingredient Level: `ingredient single`
Prints the ingredient level for a particular type of ingredient which is specified by the user’s command.

Format: `ingredient single INGREDIENT_NAME`

Example:
* `ingredient single milk`

#### 3.5 Resetting all to zero : `ingredient resetAll`
Sets all ingredient levels to 0 by updating the database when the command is entered.

Format: `ingredient resetAll`

#### 3.6 Listing Ingredient Levels : `ingredient list`
Prints the ingredient levels for all ingredient types retrieved from the database.

Format: `ingredient list`

### 4. Commands - Sales Tracking <a name="commands-sales-tracking"></a>

#### 4.1 Updating the number of drinks sold for the day :
Asks the user to enter the number of each type of drink sold for the current day.

Format: `sales A/NUM B/NUM C/NUM ...`
* `A`, `B`, `C` are abbreviations for the drink types.
* `NUM` refers to the number of drinks sold.

Example:
* `sales BSBM/100 BSBBT/120` Updates the sales of Brown Sugar Boba Milk `BSBM` to 100 and
 Brown Sugar Boba Black Tea `BSBBT` to 120.

#### 4.2 Listing the number of drinks sold for the day :
Shows a list of all types of drinks sold for the current day.

Format: `sales list`

#### 4.3 Listing the sales record for a specific day :
Shows a sales record for each type of mile tea on a specific date.

Format: `sales YYYY-MM-DD`
* `YYYY-MM-DD` refers to date in YYYY-MM-DD format.

Example:
* `sales 2020-08-01` returns the sales record for each type of milk tea on 2020-08-01.
 


## Command summary <a name="command-summary"></a>

### General

Action | Format, Examples
-------|------------------------------
**Help** | `help`
**Exit** | `exit`

### Employees' Contact Details

Action | Format, Examples
-------|------------------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMERGENCY_CONTACT [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/81234567 t/morning shift`
**List** | `list`
**Edit** | `edit INDEX [n/NAME] [e/EMERGENCY_CONTACT] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/81234567`
**Search** | `search KEYWORD [MORE_KEYWORDS]`<br> e.g., `search James Jake`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**Archive** |  1. **Archive \(1 entry\):**  `archive INDEX`<br> e.g., `archive 1` <br>2. **Archive \(all\):**  `archvie all` <br>3. **List all archived data:**  `archvie list`</br>

### Ingredients  Tracking

Action | Format, Examples
-------|------------------------------
**i-set**  | `i-set i/INGREDIENT_NAME m/AMOUNT` <br> e.g., `i-set i/Milk m/20`
**i-set-all**  | `i-set-all M/AMOUNT_FOR_MILK P/AMOUNT_FOR_PEARL B/AMOUNT_FOR_BOBA O/AMOUNT_FOR_OOLONG_TEA S/AMOUNT_FOR_SUGAR` <br> e.g., `i-set-all M/20 P/20 B/20 O/50 S/100`
**i-set-default**  | `i-set-default` <br> e.g., `i-set-default`
**Get**| `ingredient single INGREDIENT_NAME`  <br> e.g., `ingredient single milk`
**Reset**| `ingredient resetAll`
**List**| `ingredient list`

### Sales Tracking

Action | Format, Examples
-------|------------------------------
**Update**| `sales A/NUM B/NUM C/NUM ...` <br> e.g., `sales BSBM/100 BSBBT/120`
**List**| `sales list`
**Find**| `sales YYYY-MM-DD`
 
