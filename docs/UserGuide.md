---
layout: page
title: User Guide
---


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## GradPad

We are a team of **Computer Science Undergraduates** working together to solve **module management** and **graduation planning** problems
faced by fellow Computer Science students. Below is the detailed User Guide for effective use of GradPad which can 
ease and improve your **module planning process**.

GradPad is a one-stop solution to **module management** for Computer Science Undergraduates.
Planning for modules has always been a tedious process but it does not have to be.

The current approach to planning and tracking graduation requirements is to open up tabs after tabs of NUS resources such as 
[NUSMods](https://nusmods.com/) and  School of Computing 
[websites](https://www.comp.nus.edu.sg/programmes/ug/cs/curr/), which can be messy at times. 
So, our team has come up with the idea of an **easy-to-use**, **all-in-one application** that
can ease the process of **module management** for Computer Science Undergraduates.

GradPad **consolidates** the modules you have taken and **displays** the remaining required modules to
ease your module planning process. A **module searching platform** is also included in GradPad, providing easy
**access** to module information.

This user guide introduces what you can do with GradPad and the steps needed to do them. In addition,
it includes a [quick start guide](#quick-start) that walks you through the installation and set-up process.
With this guide, you'll be ready to use GradPad in no time.

--------------------------------------------------------------------------------------------------------------------
## NUSMods integration
![NUSMods](images/nusmods.png) 

To streamline your module searching process with added convenience, GradPad integrates data from [NUSMods](https://nusmods.com/). With this, 
you no longer have to visit NUSMods separately to look up module information. Instead, GradPad offers you all that data 
in the same app you use to plan your modules.

We have requested and was granted permission from NUSMods to utilise their platform to retrieve module data. These data
are retrieved in **realtime**, directly from NUSMods.

--------------------------------------------------------------------------------------------------------------------

## Quick start

Follow the steps below to learn how to get started with GradPad!

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `gradPad.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your GradPad.

1. Ensure you have **internet connectivity** to fully utilise GradPad with the latest data from [NUSMods](https://nusmods.com/).

1. Double-click the file to start the app. The Main Page similar to the image below should appear in a few seconds. Note
 how the app contains some sample data.<br>

   ![Ui](images/AnnotatedUi.png)

1. Type a command in the _Command Box_ and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
 display the help page.<br>
   Some other example commands you can try:
   
   * **`required`** : Shows a list of required modules in the CS curriculum.
   
   * **`search`** `CS2103T` : Searches for module named `CS2103T` from the NUSMods database 
   (if connected to the internet) or from a local saved file.

   * **`add`** `CS2103T` : Adds a module named `CS2103T` to the GradPad.

   * **`delete`** `CS2103T` : Deletes the module named `CS2103T` in the current list.

   * **`exit`** : Exits the app.
   


<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
* You can still use GradPad without an internet connection. However, you will only be able to access modules
within the NUS Computer Science [curriculum](https://www.comp.nus.edu.sg/programmes/ug/cs/curr/) while you're offline.
* Detailed explanation of each command can be found [here](#commands).
* Refer to the [Feature Summary List](#feature-summary-list) below for a summary of all features.
</div>

--------------------------------------------------------------------------------------------------------------------

## Feature Summary List
A consolidated view of GradPad's features is listed in the table below for easy reference.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
* Italicised words (e.g. *Completed Modules List*) refer to actual sections on the GradPad user interface.
</div>

 Features                         |    Description
 ----------------------------------------|------------
 Add a Module                            | Allows you to add any module of choice to the *Completed Modules List* for tracking purposes
 View Added Modules                      | Allows you to check all the modules you've added  to *Completed Modules List*
 Find Modules in Completed Modules       | Allows you to look for modules in *Completed Modules List* using keywords
 Delete a Module                         | Allows you to delete unwanted modules from *Completed Modules List*
 Edit a Module                           | Allows you to edit the details of a module you've already added to *Completed Modules List*
 Check Modular Credits                   | Allows you to check your current modular credit tally
 View all Required Modules               | Allows you to display all required modules in the Computer Science curriculum onto the *Result Display Box*
 View all General Education Modules      | Allows you to display all available General Education Modules offered in NUS onto the *Result Display Box*
 View all Science Modules                | Allows you to display all available Science modules in the Computer Science curriculum onto the *Result Display Box*
 Search for a Module                     | Allows you to search for any module available in NUS and display the module details onto the *Result Display Box*
 Find Tags from Completed Modules        | Allows you to check all tags that are used in *Completed Modules List*
 Clear all Modules                       | Allows you to delete all the modules from *Completed Modules List*
 View Help                               | Allows you to view a list of all possible commands
 Exit the Program                        | Allows you to exit GradPad


Refer to [Commands](#commands) below for details on all commands.

## Commands

This section contains detailed explanations of all commands and their respective usages.

Each command explanation starts with a usage scenario, followed by an explanation of what the command does, and ends
off by showing you how to use it. 
 
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Command Format and Feature Descriptions:**<br>

* When a command requires parameters, they can be entered in any order.<br>
For example, if the command specifies `edit MODULE_CODE c/NEW_MODULE_CODE t/core`, `edit MODULE_CODE t/core c/NEW_MODULE_CODE
` is also acceptable.

* When a command does not require parameters, e.g. `help`, adding extraneous words, e.g `help me` will not affect the
 command, allowing the command to run as per normal.

* Highlighted words (e.g. `this`) refer to actual commands.

* Italicised words (e.g. *Completed Modules List*) refer to actual sections on the GradPad user interface.

</div>

### Adding a module: `add`

Scenario:<br>
You have just completed the CS2101 module, and you want to add the module into GradPad to track your academic progress. 
You found the module to be enjoyable so you would like to add tags to remind yourself of how fun the module was.

The `add` command allows you to add a module you have completed into the *Completed Modules List*.

You can add a module by specifying a valid module code. You can also include multiple tags using the prefix `t/` but they are optional.
Conveniently, you don't have to specify the module title or modular credits of the module you are adding - GradPad
automatically retrieves them for you using data from NUSMods.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
* You cannot add a module that has been previously added as GradPad does not allow duplicate modules.
* You can use multiple words in a tag. However, there should only be a single space between any 2 words.
* Tags **within a single module** are case-insensitive, i.e. "core", "CORE", and "CoRe" are all considered the same.
In such a case, only the first tag amongst a group of duplicates will be added. For e.g.
`add cs2103t t/core t/CORE t/CoRe` results in only "core" being added.

</div>

To add the module:

1. Type `add cs2101 t/superbmodule t/superfun` into the *Command Box*, and press **Enter** to execute it.<br>
![Add1](images/Add1.png)

2. The *Result Display Box* will display the message "The following module has been successfully added", along with
 the details of the module. You will then be able to see the CS2101 module at the bottom of the *Completed Modules List*
 .<br>
![Add2](images/Add2.png)

### Viewing all added modules: `list`

Scenario:<br>
You have filtered the _Completed Modules List_ to show modules whose module codes contain 'cs1', and you wish to
restore the list to view all the modules you have added.

The `list` command allows you to view all the modules you have added into the _Completed Modules List_ in GradPad
using this command. This is especially useful when you have previously used a command that filters the list (i.e. `find`), and you wish to restore the list to its original state.

To view all modules:

1. Type `list` into the _Command Box_, and press **Enter** to execute it.
![List1](images/List1.png)

2. The _Result Display Box_ will display the message "All your modules have been listed!", and you will be able to
view all your previously added modules in the _Completed Modules List_.
![List2](images/List2.png)

### Viewing all added tags: `tags`

Scenario:<br>
You wish to filter your _Completed Modules List_ to display modules tagged as "foundation". However, you've added
tons of tags to tons of modules and so you can't remember if this tag actually exists. 

The `tags` command allows you to view all the tags currently in your _Completed Modules List_ in
 GradPad. This is useful when you want to see what tags you can filter your _Completed Modules List_ by (see the 
 `find` command below).

To view all tags:

1. Type `tags` into the _Command Box_, and press **Enter** to execute it.
![Tags1](images/Tags1.png)

2. The _Result Display Box_ will display a list of all existing tags.
![Tag2](images/Tags2.png)

### Editing module details: `edit`

Scenario:<br>
When you were a freshman, you added a module with tags "superbmodule" and "superfun". But now that you're graduating,
you actually think that the module was the best you'd taken in NUS.

The `edit` command allows you to edit the details of a module that you have added into GradPad.

You can edit a module by specifying the module code of the module in the _Completed Modules List_, followed by the
fields you wish to edit with their respective prefixes, `c/` for new module code, `t/` for new tag. You may edit
multiple fields in a single `edit` command. 

The respective module title and modular credits for a module code will be automatically updated with data from NUSMods.
 
 <div markdown="block" class="alert alert-info">
 
 **:information_source: Note:**<br>
* When you edit tags, instead of changing the descriptions of the existing tags, editing tags replaces
 them with new ones.

* Instead of deleting that module and adding it again with new tags, you can simply edit the module and replace the tags.
 
 </div> 

To edit the tag of an existing module:

1. Type `edit cs2101 t/bestmodule` into the _Command Box_, and press **Enter** to execute it.<br>
![Edit1](images/Edit1.png)

2. The _Result Display Box_ will display the message "The following module has been successfully edited",
along with the newly edited details of the module. You will also be able to see that the tags of the
module displayed in the _Completed Modules List_ has been replaced.

![Edit2](images/Edit2.png)

### Finding a specific module or a group of modules: `find`

Scenario:<br>
Let's say you have been adding a huge number of modules into GradPad, and you have lost track of the modules you 
have added. You want to check and see all MA-coded modules with the "bestmodule" tag.

The `find` command allows you to filter the _Completed Modules List_ to display the modules that you want to see. 
You can do this by specifying parts of the module code of the module(s) you wish to display.
Additionally, you can also specify the tags of the module(s) you wish to display. Likewise, you can also simply
specify parts of a tag instead of the entire tag.

Instead of scrolling through the long _Completed Modules List_ and checking the modules one by one, you can easily
filter the list to display that module by `find`-ing said module.

To find the module:

1. Type `find ma bestmodule` into the _Command Box_, and press **Enter** to execute it.<br>
![Find1](images/Find1.png)

2. The _Result Display Box_ will display the message "2 modules found!" and you will be able to see only the modules 
whose module codes contain "ma" and modules with "bestmodule" as a tag in the _Completed Modules List_.
![Find2](images/Find2.png)

### Deleting a Module: `delete`

Scenario:<br>
You have added several modules into GradPad but you realised that you accidentally added an additional module 'CS2107'
that you have not completed yet. If you no longer want that module in your _Completed Modules List_,
you can simply delete it.

The `delete` command allows you to remove a module you have added into GradPad.

You can delete a module by specifying the **module code** of the module. (Case insensitive)

To delete the module:

1. Type `delete cs2107` into the _Command Box_, and press **Enter** to execute it.<br>
![Delete1](images/Delete1.png)

2. When you are prompted for a confirmation, type `yes` into the _Command Box_, and press **Enter**.<br>
![Delete2](images/Delete2.png)

3. The _Result Display Box_ will display the message "The following module has been successfully deleted" along
with the details of the module you deleted.
![Delete3](images/Delete3.png)

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>
For experienced users, you can use the `fdelete` command instead, to bypass the confirmation step!

</div>

### Checking total modular credits: `checkmc`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
A **Modular Credit** (MC) is a unit of the effort, stated in terms of time, expected of a typical student in managing his/her workload, with
an average of 20 MCs per semester.

</div>

Scenario:<br>

You want to make sure you have hit the minimum criteria of 70MCs required for applying internship modules or
the 160MCs graduation requirements.

The `checkmc` command allows you to check the total amount of modular credits you have accumulated so far using this command.
This gives you a rough gauge of the progress of your graduation planning.

To check total modular credits:

1. Type `checkmc` into the _Command Box_, and press **Enter** to execute it.
![CheckMc1](images/CheckMc1.png)

2. The _Result Display Box_ will display a message telling you how many modular credits you have accumulated so far.
![CheckMc2](images/CheckMc2.png)

### Checking required modules: `required`

Scenario: <br>
You have added several modules into your GradPad so far but you are unsure if they are part of
the Computer Science curriculum. You can simply check by using the `required` command.

The `required` command allows you to check the modules of the Computer Science curriculum you have yet to take,
based on your _Completed Modules List_.

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>
The `required` command is able to identify equivalent and precluded Modules! (Eg. CS1010X and CS1101S)

</div>

To check required modules:

1. Type `required` into the _Command Box_, press **Enter** to execute it.
![Required1](images/Required1.png)

2. The _Result Display Box_ will then display all the modules you have yet to take for each particular field, as shown below.
![Required2](images/Required2.png)

### Checking General Education Modules: `gem`

Scenario: <br>
You wish to take some GE modules to clear your GE requirement, but you are unsure what modules are available.
You can simply check by using the `gem` command.

The `gem` command allows you to check all the available General Education (GE) modules that are available in NUS.
To ease your planning, the `gem` command indicates which modules are available in Semester 1 and/or 2.

To check GE modules:

1. Type `gem` into the _Command Box_, press **Enter** to execute it.
![Gem1](images/Gem1.png)

2. The _Result Display Box_ will then display all the available GE modules that you can take,
categorised into Semester 1 and 2, as shown below.
![Gem2](images/Gem2.png)

### Checking Science modules: `science`

Scenario: <br>
You wish to take a Science module to clear your Science requirement, but you forgot what modules are available.
You can simply check by using the `science` command.

The `science` command allows you to check all the available Science modules of the Computer Science curriculum.

To check Science modules:

1. Type `science` into the _Command Box_, press **Enter** to execute it.
![Science1](images/Science1.png)

2. The _Result Display Box_ will then display all the available Science modules that you can take, as shown below.
![Science2](images/Science2.png)

### Searching for a module: `search`

Scenario: <br>
After viewing the required modules you have yet to take, you wish to find out more about those modules (Eg. What are they about? 
Do they have any pre-requisites?). You can simply look up those details by using the `search` command.

The `search` command allows you to search for any module available in NUS.

You can search for a module by specifying the module code of the module. The essential module details will then be retrieved 
from NUSMods database directly and displayed in the _Result Display Box_.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>
If you are not connected to the internet, you can only search for modules that are in the NUS Computer Science
 curriculum.

</div>

1. Type `search MA1521` into the _Command Box_, press **Enter** to execute it.
![Search1](images/Search1.png)

2. The _Result Display Box_ will then display all the relevant information about the module "MA1521", as shown below.
![Search2](images/Search2.png)

### Clearing all modules in GradPad: `clear`

Scenario: <br>
You used GradPad to draft a plan for the modules you're going to take, but now you wish to erase all of that and
 start a new plan. Instead of deleting every module one by one, you can simply delete them all at one go.

The Clear command allows you to erase all modules from the *Completed Modules List* at one go with a single command.

To clear all modules:

1. Type `clear` into the _Command Box_, and press **Enter** to execute it.<br>
![Clear1](images/Clear1.png)

2. When you are prompted for a confirmation, type `yes` into the _Command Box_, and press **Enter**.<br>
![Clear2](images/Clear2.png)

3. The *Result Display Box* will then display the message "GradPad has been cleared!" and the 
*Completed Modules List* will now be empty, as shown below.
![Clear3](images/Clear3.png)

<div markdown="block" class="alert alert-primary">

:bulb: Tip:<br>
For experienced users, you can use the `fclear` command instead, to bypass the confirmation step!

</div>

### Showing the help display: `help`

Scenario: <br>
You are in the midst of planning your modules, but you suddenly forgot what commands are available to you.
Simply refresh your memory by using the `help` command to see all available commands and their respective functions.

The `help` command displays all of GradPad's available commands and how to use them, to you.
This command is useful for first-timers or users who generally do not know how to use GradPad.

To show the help display:

1. Type `help` into the _Command Box_, and press **Enter** to execute it.<br>
![Help1](images/Help1.png)

1. The _Result Display Box_ will display all of GradPad's commands along with examples on how to use them.<br>
![Help2](images/Help2.png)

### Exiting GradPad: `exit`

Apart from clicking the "close" button (red button) at the top of the GradPad window, you can exit GradPad using the `exit` command.

To exit:

1. Type `exit` into the _Command Box_, and press **Enter** to execute it.<br>
![Exit](images/Exit1.png)

2. The _Result Display Box_ will display the message "Take care!" and after a 1.5 second delay, the application will terminate.<br>
![Exit2](images/Exit2.png)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**:
1. In your old computer, find the `gradPad.json` file within your GradPad home folder and transfer this file
to your new computer.

2. On your new computer, launch GradPad once. In GradPad's home folder, you should now see that
an empty `gradPad.json` file has been created.

3. Delete this empty file and replace it with the `gradPad.json` file from your old computer.

--------------------------------------------------------------------------------------------------------------------

## Command summary

This section is the table of all the commands available, and their respective usage example in GradPad.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Command summary:**<br>

* Words in curly braces (e.g. `{module code}`) refers to **compulsory** input that is required for the command to function.

* Words in square brackets (e.g. `[c/module code]`) refers to **optional** input that can be entered.

* For the `edit` command, at least the module code or tag is required for the command to function.

</div>

Action | Format | Example
--------|-------|----------
**Add** | `add {module code} [t/tags]` | `add CS2100 t/core`
**Edit** | `edit {module code} [c/module code] [t/tags]` | `edit cs2103 c/CS2103T t/core`
**Delete** | `delete {module code}` | `delete CS2103T`
**Force Delete** | `fdelete {module code}` | `fdelete CS2103T`
**Completed Modules** | `list` | `list`
**Find** | `find {module code or tag}` | `find CS2103T foundation fun`
**Check MCs** | `checkmc` | `checkmc`
**Required Modules** | `required` | `required`
**General Education Modules** | `gem` | `gem`
**Science Modules** | `science` | `science`
**Search module details** | `search {module code}` | `search CS2103T`
**Check existing Tags** | `tags` | `tags`
**Clear** | `clear` | `clear`
**Force Clear** | `fclear` | `fclear`
**Help** | `help` | `help`
**Exit** | `exit` | `exit`
