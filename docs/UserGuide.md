---
layout: page
title: User Guide
---
Welcome to the User Guide for Eva! 
If you are viewing this on the pdf version, use Adobe Acrobat Reader and zoom in to 200% for the best viewing experience!

* Table of Contents
{:toc}

## 1. Introduction

<p align="center">
   <img src="images/ugimages/evalogo.png" alt="Eva logo" width="500" height="500"  />
</p>
Welcome and thanks for downloading Eva!

What is _Eva_?

Eva is a simple and lightweight desktop application that **handles HR related administrative tasks**, 
like managing your company staffs and recording details related to recruitment.<br>
Eva comes with a Command Line Interface (CLI) which is faster than a typical mouse/GUI driven app after you or your staffs get used to it.<br>

We hope that through our app, you and your company will be empowered and able to solve your basic HR needs.<br><br>
This user guide will take you through the basics of Eva and help you get moving straightaway.
There are 3 types of messages you can look out for that will further enhance your experience in using Eva with this user guide. <br>

<div markdown="block" class="alert alert-info">
**:information_source: Note:**
These notes are meant for additional information that will help you understand the segment better. 
Do take note of these messages as you read the user guide
</div> 

<div markdown="block" class="alert alert-primary">
:bulb: **Tip:**
These tips will enable you to use Eva in a more efficient manner. So it will be beneficial for you to pay attention to these tips!
</div>

<div markdown="block" class="alert alert-danger">
:exclamation: **Important!**<br>
These messages are meant to alert you of certain consequences involved in the feature that might be important to you.
</div>
<br>

------------------------------------------------------------------------------------------------------------------------

## 2. Getting Started
Before you begin, here's a quick start guide on how to get Eva up and running on your computer!

1. Ensure you have [Java `11`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed in your Computer.

1. Download the latest `Eva.jar` from [here](https://github.com/AY2021S1-CS2103T-W13-1/tp/releases).

1. Copy the downloaded jar file to the folder you want to use as the _home folder_ for Eva.

1. Double-click the file to start the app. If you face any issues, refer to our [Frequently Asked Questions (FAQ) Section](#4-faq) 
below. A GUI similar to the one below should appear in a few seconds. 
Note how the app generate some sample data when you first launch it!<br> 
   ![Ui](images/Ui.png)

1. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   
   Here are some example commands you can try:

   * **`list a-`** : Lists all applicants, changes the panel to display the applicant list.
   
   * **`adda`**`  n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.
   
   * **`list s-`** : Lists all staffs, changes the panel to display the staff list.

   * **`adds`**`  n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`dels`**` 3` : Deletes the 3rd staff shown in the staff list.
   
   * **`addl`**` 1 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020` : Adds two leave records with dates `08/10/2020 to 10/10/2020` and `20/10/2020` to the 1st staff shown in the current list.

   * **`dell`**` 1 10/10/2020` : Deletes the leave record containing the date `10/10/2020` from the 1st person in the current list.

   * **`find s-`**` Doe` : Finds the staff whose name contains "Doe".
   
   * **`clear s-`** : Clears the staff database.

   * **`exit`** : Exits the app.

1. That's it! Now, you can refer to the [Features](#3-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
Here are some general information you would need to know about our commands as you read through the user guide.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
* Upon the accidental inclusion of fields that are not meant to be repeated, Eva only takes the last parameter entered.<br>
  e.g. The command `adds n/John Doe n/Alex Yeoh p/98765432 e/email@email.com a/123 test st p/23456789`, will be recognised as `adds n/Alex Yeoh e/email@email.com a/123 test st p/23456789`

</div>

<div markdown="span" class="alert alert-info">

:information_source: **Notes about input format:** <br>

In Eva, using our commands, you can store and access information about staff and applicants. Information includes details like name, phone number 
and more. As such, in order to store and access these details in a neat and tidy manner, be sure to follow these guidelines
on storing and accessing these details as shown in the table below.

</div>

<table>
<tr><th>Field</th><th>Constraints</th><th>Example</th></tr>
<tr><td><code><strong>NAME</strong></code></td><td>can contain only alphanumeric characters, cannot be blank, and has a character limit of 70 characters.</td><td><code>John Doe</code></td></tr>
<tr><td><code><strong>EMAIL</strong></code></td><td>should be of the format local-part@domain <br>where the local-part should contain alphanumeric characters or special characters which includes the characters !#$%&'*+/=?`{&#124;}~^.-_ <br>The domain name should have at least 2 characters and contain only alphanumeric with a period or a hyphen for the characters in between if needed.</td><td><code>john_Doe12345@example123.com</code></td></tr>
<tr><td><code><strong>PHONE</strong></code></td><td>can only contain numbers, and must be between 8 and 20 digits long. </td><td><code>6590018978</code> </td></tr>
<tr><td><code><strong>TAG</strong></code></td><td> can only contain alphanumeric characters, cannot be blank, and has a character limit of 25 characters.</td><td><code>hardworking</code></td></tr>
<tr><td><code><strong>INDEX</strong></code></td><td>The index of the staff or applicant<br> <br> It must be a valid index number.</td><td><code>2</code> <br>Note: This index will represent the staff/applicant with index-2 in the displayed list.</td></tr>
<tr><td><code><strong>DATE</strong></code></td><td>must be in the format <code>DD/MM/YYYY</code></td><td><code>06/07/2020</code> represents the 6th of July 2020<br> Note: Eva automatically corrects wrong dates like <code>30/02/2020</code> and <code>31/11/2020</code> to <code>29/02/2020</code> and <code>30/11/2020</code> respectively.</td></tr>
</table>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

Eva automatically saves any changes made to the records in your hard disk when using the commands below!
There is no need to save manually! <br>
You can also find the records in the `data` folder where the `eva.jar` file is located.

</div>

Here is a brief overview on how this section is arranged. 

The first section brings you through the 
[different panels](#31-eva-gui) you will see in our application. After getting familiar with that, we will share some 
[general system commands](#32-system-commands) you can use at any point in time. 

In the third section, 
we will bring you through the [features we have implemented for staff](#33-staff-commands). 

Following which, you will be introduced to the [features for managing applicant records](#34-applicant-commands). 

Finally, you will learn about [how to add and delete comments to applicants and staff](#35-comment-commands) and 
[our very own unique script engine](#36-script-engine-experimental) (experimental feature). By then, you will definitely become a master in using Eva! 
So let's get started! 

### 3.1. Eva GUI

Before we dive into the features, let us help you familiarize yourself with our GUI!

As of `v1.4`, Eva currently has four different panels it can switch between:

#### 3.1.1. Staff List : `Staff List Panel`

This panel is where you can see all your staffs in a condensed list view.

![staffListPanel intro](images/ugimages/Intro1.png)

#### 3.1.2. Staff Profile : `Staff Profile Panel`

This is where you can zoom in on an individual staff and view more information about their leaves and comments.

![staffProfilePanel intro](images/ugimages/Intro2.png)

#### 3.1.3. Applicant List : `Applicant List Panel`

Similar to the staff list, this is where you can see all your applicants in a condensed list view.

![applicantListPanel intro](images/ugimages/Intro3.png)

#### 3.1.4. Applicant Profile : `Applicant Profile Panel`

This is also similar to the staff profile where you can zoom in on an individual applicant and view more information about them.

However, notice how the placement of the comments section differs from the [`staff list`](#311-staff-list--staff-list-panel).

![applicantProfilePanel intro](images/ugimages/Intro4.png)

These four panels form the core of Eva to be sure to familiarize yourself with them! Once ready, move on to the [Commands](#32-system-commands)!

<div markdown="block" class="alert alert-info">

**:information_source: Note:**
Eva launches into the [`Staff List Panel`](#311-staff-list--staff-list-panel) by default.
However, Eva remembers which list you last looked at and will open at [`Applicant List Panel`](#313-applicant-list--applicant-list-panel) if you exited the app from there!

</div>

### 3.2. System Commands

#### 3.2.1. Open help window : `help`

If you're not sure of how to use Eva at any point in time, use the `help` command!

The `help` command shows a message explaining how to access the help page.

Format: `help`

![help message](images/ugimages/Help.png)

#### 3.2.2. Exit the program : `exit`

Done using Eva? The `exit` command exits the program!

Format: `exit`

<div markdown="block" class="alert alert-info">

**:information_source: Note:** The exit command immediately closes the app upon execution.<br>

As mentioned earlier in [Features](#3-features), there is no need to manually save data! So don't worry if you accidentally close your app!

</div>

![exit](images/ugimages/Exit.png)

### 3.3. Staff commands

Eva makes use of these staff commands to give you the ability to keep track of your staffs which help you make business and manpower decisions.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** <br>

* All commands under staff can only be done on either staff list or staff profile panel except `adds`, which can be done on any panel.<br>
 
* On a staff profile, only the details of the profile that is being viewed can be changed.<br>

</div>


#### 3.3.1. List all staff : `list s-`

The staff list panel serves as your primary tool to give you an overview of all the staffs in your company.

This command changes the panel to the [`Staff List`](#31-eva-gui) and shows a list of all staffs in the Eva Database.

Format: `list s-`

The image below shows what you would see after executing this example.

![listStaff](images/ugimages/ListStaff.png)

#### 3.3.2. Add a staff : `adds`

As storing staff records is a primary activity in Eva, you can use this command to add a staff record into Eva. 

<div markdown="block" class="alert alert-info">

**:information_source: Note:** <br>

A staff record can contain the details listed below:
 - Name*
 - Phone Number*
 - Email*
 - Address*
 - Tags
 - Comments
 - Leaves 
 
The fields marked with a asterisk (*) are compulsory and have to be provided in the parameters below.
Without any one of these fields, Eva will not accept your input. <br>
Tags and comments can be added with this `adds` command as shown in the examples. <br>
To manage leave records for each staff, please refer to the commands 
[`addl`](#337-record-leave-taken-by-staff-addl) and [`dell`](#338-delete-leave-taken-by-staff-dell). <br>
 
Below are some notes about the parameters you can add:
 - A staff can have any number of tags (including 0) <br>
 - A staff can have any number of comments (including 0) <br>
 - Each staff is uniquely identified by their name. In the event you wish to add 2 staff records with the same name,
   please ensure that both their phone number and email addresses are different

</div>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:** <br>

 - The details of each field can be provided in any order. <br>
 
 - In the case of multiple similar prefixes, the argument of the last prefix will be used (excluding tags and comments).<br>
 
</div>

Format: `adds n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`
 
Examples:
* To add only the necessary fields <br>
`adds n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* To add a comment along with the necessary fields <br>
`adds n/Kristina Ryan e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/12345678
 c/ ti/Behaviour d/20/12/2020 desc/Very enthusiastic in meetings`
* To add a tag along with the necessary fields <br>
`adds n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/12345678 t/Developer`

The image below shows what you would see after executing the last example given. Notice that Betsy Crowe is now recorded as a staff in our database.

![addStaff](images/ugimages/AddStaff.png)

#### 3.3.3. Find a staff : `find s-`

The command serves as a tool to help you quickly find all the staffs with the given name in your company.

This command will show a list of staffs whose name contains one of the given names. It changes the panel to the [`Staff List`](#31-eva-gui) to display the result.

Format `find FIND_TYPE- KEYWORD`

Examples:
* `find s- Doe`

* `find s- Alex`

The image below shows what you would see after executing the last example given. Notice that no other staffs are listed as there is only one Alex.

![findStaff](images/ugimages/FindStaff.png)

#### 3.3.4. View : `view`

To get to the [`Staff Profile Panel`](#312-staff-profile--staff-profile-panel) mentioned earlier, 
use this command from the [`Staff List Panel`](#311-staff-list--staff-list-panel). Over here, you can take a closer look at the leaves and comments of a staff.

This command brings you to the profile panel of the staff at the specified index. 

Format: `view INDEX`

Example:
* `view 1`

The image below shows what you would see after executing the example given.

![viewStaff](images/ugimages/ViewStaff.png)

#### 3.3.5. Delete a staff: `dels`

Deletes a staff from Eva. 

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br>
Please take note that this action is irreversible.**

</div>

Format: `dels INDEX`

Example:
* `dels 1`

* `dels 7`

The image below shows what you would see after executing the last example given. Notice that the last staff in the list now has index 6.

![deleteStaff](images/ugimages/DeleteStaff.png)

#### 3.3.6. Edit a staff: `edits`

Edits general details of a staff from eva (excluding leave taken)

Format: `edits INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [t/TAG] [c/ ti/COMMENT_TITLE d/COMMENT_DATE desc/NEW_DESCRIPTION]`

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**<br>

* Only description of comments can be edited. Title and Date cannot be edited.
 
* In the case of multiple similar prefixes, the description of the last prefix will be used (excluding tags and comments).

</div>

Example:
* `edits 1 n/John Doe p/99999999 a/John Street e/NEW@example.com`

* `edits 1 n/Balakrishnan Roy`

The image below shows what you would see after executing the last example given. Notice how Roy Balakrishnan has changed to Balakrishnan Roy.

![editStaff](images/ugimages/EditStaff.png)


#### 3.3.7. Record leave taken by staff: `addl`

In a company, it is important to keep track of your manpower and know who will be working on which day.
As such, Eva allows you to record down the leaves taken by a staff and show you the total number of leaves taken as well.

Format: `addl INDEX l/d/DATE [d/DATE] [l/d/DATE [d/DATE]]…​`

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**<br>

* Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).<br>

* Dates can be input in any order. Eva will sort the leaves and dates according to which date comes first.<br>

* Eva automatically tells you if a staff has either already taken the leave, is in the process of taking it or has not taken the leave. This can be seen on the [Staff Profile](#312-staff-profile--staff-profile-panel). <br>

* Eva also automatically tallies up the total number of leave days taken by a staff and displays the total below the leaves! This can be seen on the [Staff Profile](#312-staff-profile--staff-profile-panel). <br>

</div>

Examples:
* `list s-` followed by `addl 2 l/d/20/10/2020` adds the leave record with the given date(s) to the 2nd person in the shown list.
* `find s- Betsy` followed by `addl 1 l/d/20/10/2020` adds the leave to the 1st person in the results of the `find s-` command.
* `addl 1 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`
* `addl 7 l/d/09/09/2020`
* `addl 7 l/d/10/10/2020 d/08/10/2020`

The image below shows what you would see after executing the last example given above. Notice how the leave appears on Betsy's record.

![addLeave](images/ugimages/AddLeave.png)

#### 3.3.8. Delete leave taken by staff: `dell`

Sometimes a staff becomes unable to take their leave and cancels or postpones, 
Eva allows you to delete their recorded leaves. The total leave taken count is also automatically updated.

This command removes the record of leave taken by a staff, specified by the date given.<br>

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br>
Please take note that this action is irreversible.**

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Note:**
If a leave record has a date range that lasts from 08/11/2020 to 12/11/2020, any date keyed in that date range will cause the whole record to be deleted.<br>

</div>

Format: `dell INDEX d/DATE`

Examples:
* `list s-` followed by `dell 2 d/09/10/2020` deletes the leave record of which the given date coincides with from the 2nd person in shown list.
* `find s- Betsy` followed by `dell 1 d/09/10/2020` deletes the leave from the 1st person in the results of the `find s-` command.

The image below shows what you would see after executing the last example given above. Notice how the leave is deleted from Betsy's record.

![deleteLeave](images/ugimages/DeleteLeave.png)

#### 3.3.9. Clear staff database : `clear s-`

In any case that you might want to remove all staff records, you can always use this command. <br>
This command clears all staff entries from the Eva database. 

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br> 
Please take note that this action is irreversible.**

</div>

Format: `clear s-`

The image below shows what you would see after using this command. Notice that there are no more records of staff in the
Staff List.

![clearStaff](images/ugimages/ClearStaff.png)

### 3.4. Applicant commands

Applicant commands give you the ability to keep track of your applicants which help you make good recruitment decisions.

<div markdown="block" class="alert alert-info">

:information_source: **Note:**<br>

* All commands under applicant can only be done on either applicant list or applicant profile except `adda`, 
 which can be done on any panel. <br>
 
* While in profiles, only details of the profile that is being viewed can be changed.

</div>

#### 3.4.1. List all applicants : `list a-`

The applicant list panel serves as your primary tool to give you an overview of all the applicants in your company.

This command changes the panel to the [`Applicant List`](#31-eva-gui) and shows a list of all applicants in the Eva Database.

Format: `list a-`

The image below shows what you would see when you execute this command.

![listApplicant](images/ugimages/ListApplicant.png)

#### 3.4.2 Add an applicant: `adda`

Managing data of applicants is another main activity you might be involved in. Hence, this command is handy for you to 
add an applicant record into Eva. 

<div markdown="block" class="alert alert-info">

:information_source: **Note:**<br>

An applicant record can contain the details listed below:

 - Name*
 - Phone Number*
 - Email*
 - Address*
 - Tags
 - Comments
 - Interview Date
 - Application Status
 - Application
 
The fields marked with a asterisk (*) are compulsory and have to be provided in the parameters below.
Without any of these fields, Eva will not accept your input. <br> 
Tags, comments, interview date can be added with this `adda` command as shown in the examples. <br>
To manage application records for each applicant, please refer to the commands 
[`addapp`](#347-add-an-application-addapp) and [`delapp`](#348-delete-an-application-delapp). <br>
To manage the application status, please refer to the command [`setas`](#349-set-application-status-setas). <br>

Below are some notes about the parameters you can add:
 - An applicant can have any number of tags (including 0).
 - An applicant can have any number of comments (including 0).
 - Each applicant is uniquely identified by their name. In the event you wish to add 2 applicant records with the same name,
      please ensure that both their phone numbers and email addresses are different.
 - The interview date has to be in DD/MM/YYYY format.
 - Once you add an applicant the status will be automatically set as received, if you wish to change it, refer to the feature 
 [setting of application status](#349-set-application-status-setas) below.
 
</div>
 
 <div markdown="block" class="alert alert-primary">
 :bulb: **Tip:**
 -  In the case of multiple similar prefixes, the description of the last prefix will be used (excluding tags and comments).
 -  The details of each field can be provided in any order. 
 - The interview date is optional. If an interview date is not fixed yet, you can leave it and set it later.
  
</div>

Format: `adda n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [id/INTERVIEW_DATE] [t/TAG]…​[c/COMMENTS]…`

Examples:
* To add an applicant without an interview date <br>
`adda n/Vicky Santana p/98765432 e/vsc@xample.com a/John street, block 123, #01-01`
* To add an applicant with tags and comments <br>
 `adda n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 
 p/92345678 t/Developer c/ ti/Working Ethics d/10/10/2010 desc/Good`
* To add an applicant with an interview date <br>
`adda n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 id/22/11/2020`

The image below shows what you would see after executing the last example given above. 
Notice that John Doe is now recorded as an applicant in our database.

![addApplicant](images/ugimages/AddApplicant.png)

#### 3.4.3. Find an applicant : `find a-`

The command can help you quickly find all the applicant records with the given name in your company.

This command will show a list of applicants whose name contains one of the given names. It changes the panel to the [`Applicant List`](#31-eva-gui) to display the result.


Format `find FIND_TYPE- KEYWORD`

Examples:
* `find a- Doe`

* `find a- Yu`

The image below shows what you would see after executing the last example given above.

![findApplicant](images/ugimages/FindApplicant.png)

#### 3.4.4. View : `view`

To get to the [`Applicant Profile Panel`](#314-applicant-profile--applicant-profile-panel) mentioned earlier, 
use this command from the [`Applicant List Panel`](#313-applicant-list--applicant-list-panel). Over here, you can take a closer look at the application and comments of an applicant.

This command brings you to the profile panel of the applicant at the specified index. 

Format: `view INDEX`

Example:
* `view 1`

The image below shows what you would see after executing this example.

![viewApplicant](images/ugimages/ViewApplicant.png)

#### 3.4.5. Delete an applicant: `dela`

If you need to remove specific records of applicants, you may use this command. By providing the index of the 
applicant you see on Applicant List, you can remove that applicant's record. 

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br>
Please take note that this action is irreversible.**

</div>

Format: `dela INDEX`

Example:
* `dela 1`

The image below shows what you would see after executing this example. Notice how Bernice Yu, previously at index 2,
 is now at index 1.

![deleteApplicant](images/ugimages/DeleteApplicant.png)

#### 3.4.6. Edit an applicant: `edita`

Edits general details of an applicant from eva (excluding application status)

Format: `edita INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [id/INTERVIEW_DATE] [c/ ti/COMMENT_TITLE d/COMMENT_DATE desc/NEW_DESCRIPTION]`

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**<br>

* Only description of comments can be edited. Title and Date cannot be edited.

* In the case of multiple similar prefixes, the description of the last prefix will be used (excluding tags and comments).
 
</div>

Example:
* `edita 1 n/NEWNAME p/99999999 a/NEWADDRESS e/NEW@example.com`
* `edita 1 id/ 10/10/2010`
* `edita 2 p/98762345`

The image below shows what you would see after executing the last example given above.

![editApplicant](images/ugimages/EditApplicant.png)

#### 3.4.7. Add an application: `addapp`

Adds an application (resume details) to an applicant with the specified index under Eva.

Format: `addapp INDEX FILEPATH`

 - An applicant should be created prior to the addition of its application.

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:** <br>

* You may find a resume text file generated in the data folder `data/resume.txt`, 
which is in the same directory as your jar file. This is the strict template to follow for resume files.<br>

* You may use the sample resume by keying in `sample` as filepath.<br>

* More info [here](https://ay2021s1-cs2103t-w13-1.github.io/tp/DeveloperGuide.html#331-application-management-system).

</div>

Example:
* `addapp 1 data/resume.txt`
* `addapp 1 sample`

The image below shows what you would see after executing the last example given above.

![addApplication](images/ugimages/AddApplication.png)

#### 3.4.8. Delete an application: `delapp`

Deletes an application from an applicant with the specified index under Eva.

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br>
Please take note that this action is irreversible.**

</div>

<div markdown="span" class="alert alert-info">

**:information_source: Note:** `delapp` replaces the current application of the target applicant with a blank application. <br>

</div>

Format: `delapp INDEX`

Example:
* `delapp 1`

The image below shows what you would see after executing this example.

![deleteApplication](images/ugimages/DeleteApplication.png)

#### 3.4.9. Set application status: `setas`

We understand that the process of hiring new talent requires multiple stages and this can be tracked using the
application status. You can use this command to change the status of any applicant at any time.

<div markdown="span" class="alert alert-info">

**:information_source: Note:** <br>

Application status can only be any one from the following: <br>
  - received
  - processing
  - accepted
  - rejected
  
  </div>
  
Format: `setas INDEX as/NEW_APPLICATION_STATUS`

Example:
* `setas 1 as/received`
* `setas 3 as/accepted`

The image below shows what you would see after executing the last example given above.
Notice that the application status of Li David has changed to accepted.

![setApplicationStatus](images/ugimages/SetApplicationStatus.png)

#### 3.4.10 Clear applicant database: `clear a-`

In any case that you might want to remove all applicant records, you can always use this command.
This command clears all applicant entries from the Eva database.


<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br> 
Please take note that this action is irreversible.**

</div>

Format: `clear a-`

The image below shows what you would see after using this command. 
Notice that there are no more records of applicants in the
Applicant List.

![clearApplicantDatabase](images/ugimages/ClearApplicant.png)

### 3.5. Comment Commands

Commands to add, delete and edit comments on staff or applicants

<div markdown="block" class="alert alert-info">

**:information_source: Note:** <br>

* Comment Commands take index reference from which type of person user is viewing. <br>

* If user is viewing staff list or profile, comment commands takes index reference from staff list.<br>

* If user is viewing applicant list or profile, comment commands takes index reference from applicant list.<br>

* Comments are arranged according to date, then alphabetically if same date.<br>

* Comments do not support the input `|`.<br>

* Comment description can only be seen in profiles.<br>

</div>
 
#### 3.5.1 Add comment to a staff/applicant: `addc`

Adds a comment to a staff/applicant under eva depending on which panel you are in

Format: `addc INDEX c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/DESCRIPTION`

Example:
* `addc 1 c/ ti/Working Ethics d/10/10/2010 desc/Good`
* `addc 2 c/ ti/Punctuality d/10/10/2020 desc/This applicant as a problem with punctuality`

The image below shows what you would see after executing the last example given above.

![addComment](images/ugimages/AddComment.png)

#### 3.5.2 Delete comment from a staff/applicant: `delc`

Deletes a comment from a staff/applicant under eva depending on which panel you are in

<div markdown="block" class="alert alert-danger">

:exclamation: **Important! <br> 
Please take note that this action is irreversible.**

</div>

Format: `delc INDEX c/ ti/TITLE_OF_COMMENT_TO_DELETE`

Example:
* `delc 1 c/ ti/Working Ethics`
* `delc 2 c/ ti/Punctuality`

The image below shows what you would see after executing the last example given above.

![deleteComment](images/ugimages/DeleteComment.png)

#### 3.5.3. Edit comment on a staff/applicant: `editc`

Edits the description of a comment on a staff/applicant.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** <br>

Users can only view comment descriptions on staff or applicant profile panels.

</div>

Format: `editc INDEX c/ ti/TITLE_OF_COMMENT_TO_CHANGE d/DATE_OF_COMMENT_TO_CHANGE desc/NEW_DESCRIPTION`

Example:
* `editc 1 c/ ti/Working Ethics d/10/10/2010 desc/Quite Bad`
* `editc 3 c/ti/Review d/10/10/2010 desc/Suitable to be team leader due to experience in marketing`

The image below shows what you would see after executing the last example given above.

![editComment](images/ugimages/EditComment.png)

### 3.6. Script Engine (Experimental)

Eva provides a built-in script to execute JavaScript that can be used to extend the features of Eva.

To write a script to extend the features of Eva,
please refer to [Nashorn official website](https://www.oracle.com/technical-resources/articles/java/jf14-nashorn.html)
for supported JavaScript features, and learn how to import Java classes to extend Eva features.

<div markdown="block" class="alert alert-danger">

:exclamation: **Important!** <br>

* Please take note that this feature is not ready and is **purely experimental**.

* While loading scripts can extend the features of Eva and simplify the workflow, a script can also break the application. Use with caution! <br>

* Scripts downloaded over Internet could contain malicious content and pose security threats to your computer. Use at your own risks! <br>

</div>

#### 3.6.1. Load a JavaScript script: `load`

Format: `load SCRIPT_FILE_PATH`

Example Scenario:
* Load a script located at `C:\Documents\script.js`
* Command: `load C:\Documents\script.js`

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Eva home folder.

**Q**: How do I find the data file?<br>
**A**: The `data` folder is automatically generated in the same directory as your Eva.jar file. The `data` folder should contain the data you have saved inside some `.json` files.

**Q**: I can't run the app by double clicking! What do I do?<br>
**A**: Open terminal and [traverse](https://www.howtogeek.com/659411/how-to-change-directories-in-command-prompt-on-windows-10/) to the directory the `Eva.jar` file is in. Then type the command `java -jar Eva.jar`.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary                                                                                 

### 5.1. System

| Action                    | Format, Examples   |
|---------------------------|--------------------|
| **Help**                  | `help`             |
| **Exit**                  | `exit`             |
| **Load** (experimental)   | `load`             |

### 5.2. Staff

| Action          | Format, Examples                                                                                                                                        |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **List Staff**  | `list s-`   
| **Add Staff**    | `adds n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `adds n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **Find Staff**   | `find s- KEYWORD [MORE_KEYWORDS]`<br> e.g. `find s- James Jake`                                                                 |
| **View**        | `view INDEX`<br> e.g. `view 2`                                                                                            |
| **Delete Staff** | `dels INDEX`<br> e.g. `dels 1`         
| **Edit Staff**   | `edits INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.`edits 2 n/James Lee e/jameslee@example.com` |
| **Add Leave**    | `addl INDEX l/d/DATE [d/DATE]` <br> e.g. `addl 2 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`                                                   |
| **Delete Leave** | `dell INDEX d/DATE`<br> e.g. `dell 1 d/10/10/2020`                                                                                       |
| **Clear Staff Database** | `clear s-`         |

### 5.3. Applicant

| Action                | Format, Examples                                                                                                 |
|-----------------------|--------------------------------------------------------------------------------------------|
| **List** | `list a-`   
| **Add Applicant**      | `adda` <br> e.g. `adda n/John Doe p/98765432 e/jd@example.com a/John street, block 123, #01-01` |
| **Find** | `find a- KEYWORD [MORE_KEYWORDS]`<br> e.g. `find a- James Jake`                                                                 |
| **View** | `view INDEX`<br> e.g. `view 2`                                                                                            |
| **Delete Applicant**   | `dela` <br> e.g. `dela 1`                                                                       |
| **Add Application**    | `addapp INDEX [filepath]` <br> e.g. `addapp 1 C:\Users\Public\Downloads\resume.txt`             |
| **Delete Application** | `delapp INDEX` <br> e.g. `delapp 1`                                                       |
| **Set AppStatus**      | `setas INDEX as/NEW_APPLICATION_STATUS` <br> e.g. `setas 1 as/processing`                               |
| **Clear Applicant Database** | `clear a-`         |

## Appendix: Distribution of Contribution

* <span>1.</span> Introduction (Royce and Nikhila)
* <span>2.</span> Getting Started (Ben)
* <span>3.</span> Features (Ben and Nikhila)
    * 3.1. Eva GUI (Ben)
        * 3.1.1. Staff List : Staff List Panel (Ben)
        * 3.1.2. Staff Profile : Staff Profile Panel (Ben)
        * 3.1.3. Applicant List : Applicant List Panel (Ben)
        * 3.1.4. Applicant Profile : Applicant Profile Panel (Ben)
    * 3.2. System Commands
        * 3.2.1. Open help window : help (Royce)
        * 3.2.2. Exit the program : exit (Royce)
    * 3.3. Staff commands (Ben)
        * 3.3.1. List all staff : list s- (Hou Rui)
        * 3.3.2. Add a staff : adds (Nikhila)
        * 3.3.3. Find a staff : find s- (Hou Rui)
        * 3.3.4. View : view (Ben)
        * 3.3.5. Delete a staff: dels (Royce)
        * 3.3.6. Edit a staff: edits (Isaac)
        * 3.3.7. Record leave taken by staff: addl (Ben)
        * 3.3.8. Delete leave taken by staff: dell (Ben)
        * 3.3.9. Clear staff database : clear s- (Nikhila)
    * 3.4. Applicant commands
        * 3.4.1. List all applicants : list a- (Hou Rui)
        * 3.4.2 Add an applicant: adda (Nikhila)
        * 3.4.3. Find an applicant : find a- (Hou Rui)
        * 3.4.4. View : view (Ben)
        * 3.4.5. Delete an applicant: dela (Nikhila)
        * 3.4.6. Edit an applicant: edita (Isaac)
        * 3.4.7. Add an application: addapp (Royce)
        * 3.4.8. Delete an application: delapp (Royce)
        * 3.4.9. Set application status: setas (Nikhila)
        * 3.4.10 Clear applicant database: clear a- (Nikhila)
    * 3.5. Comment Commands
        * 3.5.1 Add comment to a staff/applicant: addc (Isaac)
        * 3.5.2 Delete comment from a staff/applicant: delc (Isaac)
        * 3.5.3. Edit comment on a staff: editc (Isaac)
    * 3.6. Script Engine (Experimental) (Hou Rui)
        * 3.6.1. Load a JavaScript script: load
* <span>4.</span> FAQ (Ben)
* <span>5.</span> Command summary (Royce)
    * 5.1. System
    * 5.2. Staff
    * 5.3. Applicant
