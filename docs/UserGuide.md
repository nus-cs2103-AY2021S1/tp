---
layout: page
title: User Guide
---

Jarvis is a desktop app for CS1101S Teaching Assistants (Avengers), optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Jarvis can get your TA administrative tasks done faster than traditional GUI apps.

## Table of Contents

1. [Overview](#1-overview)
    - [1.1 Introduction](#11-introduction)
    - [1.2 Purpose](#12-purpose)
    - [1.3 Design Considerations](#13-design-considerations)
2. [About the User Guide](#2-about-the-user-guide)
    - [2.1 Symbols](#21-symbols)
3. [Quick start](#3-quick-start)
4. [Navigating Jarvis](#4-navigating-jarvis)
    - [4.1 UI Layout](#41-ui-layout)
        * [4.1.1 Top Bar](#411-top-bar)
        * [4.1.2 Command Box](#412-command-box)
        * [4.1.3 Feedback Box](#413-feedback-box)
        * [4.1.4 Information Panel](#414-information-panel)
        * [4.1.5 Tab Switcher](#415-tab-switcher)
5. [Logging into Jarvis](#5-logging-into-jarvis)
    - [5.1 Prompt](#51-prompt)
    - [5.2 Updated Details Summary](#52-updated-details-summary)
    - [5.3 Updated Student Information](#53-updated-student-information)
6. [Features](#6-features)
    - [6.1 General Notes](#61-general-notes)
        * [6.1.1 Notes about Command Format](#611-notes-about-command-format)
        * [6.1.2 Notes about Jarvis' relationship with Source Academy](#612-notes-about-jarvis-relationship-with-source-academy)
        * [6.1.3 Notes about Tasks](#613-notes-about-tasks)
        * [6.1.4 Notes about truncation](#614-notes-about-truncation)
        * [6.1.5 Notes about Summary feature](#615-notes-about-summary-feature)
        
        <div style="page-break-after: always;"></div>
         
    - [6.2 Viewing help : `help`](#62-viewing-help--help)
    - [6.3 Adding Information : `add`](#63-adding-information--add)
        * [6.3.1 Adding Consultations : `add -c`](#631-adding-consultations---c)
        * [6.3.2 Adding Mastery Checks : `add -mc`](#632-adding-mastery-checks---mc)
        * [6.3.3 Adding Todos : `add -t`](#633-adding-todos---t)
        * [6.3.4 Adding Events : `add -e`](#634-adding-events---e)
        * [6.3.5 Adding Deadlines : `add -d`](#635-adding-deadlines---d)
    - [6.4 Editing Information : `edit`](#64-editing-information--edit)
        * [6.4.1 Editing Login information : `edit -l`](#641-editing-login-information---l)
        * [6.4.2 Editing Student information : `edit -s`](#642-editing-student-information---s)
        * [6.4.3 Editing Mastery Checks : `edit -mc`](#643-editing-mastery-checks---mc)
    - [6.5 Deleting Information : `delete`](#65-deleting-information--delete)
        * [6.5.1 Deleting Consultations : `delete -c`](#651-deleting-consultations---c)
        * [6.5.2 Deleting Mastery Checks : `delete -mc`](#652-deleting-mastery-checks---mc)
        * [6.5.3 Deleting Tasks : `delete -t`](#653-deleting-tasks---t)
    - [6.6 Viewing Information : `view`](#66-viewing-information--view)
        * [6.6.1 Viewing a list of all Students : `view -s`](#661-viewing-a-list-of-all-students---s)
        * [6.6.2 Viewing Student with keyword : `view -s NAME`](#662-viewing-student-with-keyword---s-name)
        * [6.6.3 Viewing deadline for Missions: `view -m`](#663-viewing-deadline-for-missions---m)
        * [6.6.4 Viewing ungraded Missions: `view -um`](#664-viewing-ungraded-missions---um)
        * [6.6.5 Viewing deadline for Quests: `view -q`](#665-viewing-deadline-for-quests---q)
        * [6.6.6 Viewing ungraded Quests: `view -uq`](#666-viewing-ungraded-quests---uq)
        * [6.6.7 Viewing all Consultations: `view -c`](#667-viewing-all-consultations---c)
        * [6.6.8 Viewing past Consultations: `view -cp`](#668-viewing-past-consultations---cp)
        * [6.6.9 Viewing upcoming Consultations: `view -cu`](#669-viewing-upcoming-consultations--cu)
        * [6.6.10 Viewing all Mastery Checks: `view -mc`](#6610-viewing-all-mastery-checks---mc)
        * [6.6.11 Viewing past Mastery Checks: `view -mcp`](#6611-viewing-past-mastery-checks---mcp)
        * [6.6.12 Viewing upcoming Mastery Checks: `view -mcu`](#6612-viewing-upcoming-mastery-checks---mcu)
        * [6.6.13 Viewing all Tasks: `view -t`](#6613-viewing-all-tasks---t)
        * [6.6.14 Viewing all Todos: `view -tt`](#6614-viewing-all-todos---tt)
        * [6.6.15 Viewing all Events: `view -te`](#6615-viewing-all-events---te)
        * [6.6.16 Viewing all Deadlines: `view -td`](#6616-viewing-all-deadlines---td)
    - [6.7 Exiting the program: `exit`](#67-exiting-the-program--exit)
    - [6.8 Saving the data](#68-saving-the-data)
7. [Command Summary](#7-command-summary)
    - [7.1 Add Command Summary](#71-add-command-summary)
    - [7.2 Edit Command Summary](#72-edit-command-summary)
    - [7.3 Delete Command Summary](#73-delete-command-summary)
    
    <div style="page-break-after: always;"></div>
    
    - [7.4 View Command Summary](#74-view-command-summary)
    - [7.5 Exit Command Summary](#75-exit-command-summary)
    - [7.6 Help Command Summary](#76-help-command-summary)
8. [Glossary](#8-glossary)
    - [8.1 Difference Between Consultations and Mastery Checks](#81-difference-between-consultations-and-mastery-checks)
9. [FAQ](#9-faq)


<div style="page-break-after: always;"></div>

## 1. Overview

### 1.1 Introduction
(Peirong)

Jarvis is a desktop app for CS1101S Teaching Assistants (Avengers), optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). Jarvis in general helps to organise and simplify
CS1101S tutors' administrative tasks.

### 1.2 Purpose
As an Avenger, not only do you have immense power but you are also burdened with inevitable great responsibility.
Not to worry, Jarvis is here to empower you to manage your personal tasks while teaching others efficiently and
effectively.

<div style="page-break-after: always;"></div>

### 1.3 Design Considerations
(Peirong)

Our design philosophy that has guided us through the development of Jarvis is to place the user experience at the core of all considerations.
As such, you will find throughout the user guide small features and language that we have deliberately chosen to enhance usability and improve the user-centered experience of our product.
We have highlighted several features below that we feel are reflective of our design philosophy.

1. You can toggle between your past and future commands entered by using the up and down arrows respectively.

1. Everytime a command has been entered, Jarvis will switch to the relevant tab automatically.

1. Important information such as `Student`, `Mission`, `Quest`, etc will not be deleted when the user keys in the wrong login information. We wish to give users the benefit of doubt and not delete their information as we do believe accidents can happen.

<div style="page-break-after: always;"></div>

## 2. About the User Guide
(Wiline)

This section aims to remind you of the important parts to take note of while reading the user guide.

### 2.1 Symbols
(Peirong)

When reading this user guide, various symbols may appear occasionally to draw your attention to important information.
The meaning for each symbol is as follows:

Symbol    | Meaning
----------| ------------
:warning: | This symbol indicates that there is something important for you to take note of.
:pencil2: | This symbol indicates that an example is provided.

<div style="page-break-after: always;"></div>

## 3. Quick start
(Peirong)

1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest _`jarvis.jar`_ and the _`chrome_driver.zip`_ from [here](https://github.com/AY2021S1-CS2103T-W11-2/tp/releases/tag/v1.4).
If the file downloaded does not show as _`chrome_driver.zip`_, rename the file to _`chrome_driver.zip`_ before proceeding. Unzip the zip file. Place all 3 unzipped files into a folder called _`chrome_driver`_. 
It is crucial to have the Chrome Driver in order for Jarvis to start up. If the GUI does not launch,
please check that you have installed the correct driver in the _`chrome_driver`_ folder:
    1. Windows: chromedriver.exe
    1. MacOS: chromedriver_mac
    1. Linux: chromedriver_linux<br><br>

1. Copy both file and folder to the directory you want to use as the _home directory_ for your Jarvis.

1. There are two options for launching Jarvis.
    1. Double-click the `jarvis.jar` file to start the app.
    1. Launch Jarvis from the Windows Command Prompt or MacOS command line by navigating to the directory containing `jarvis.jar`,
    then typing the command `java -jar jarvis.jar`

    <br>
    
    A GUI similar to Figure 3a below should appear in a few seconds.
    
    <div style="page-break-after: always;"></div>

    <div style="text-align: center; padding-bottom: 2em">
    <img src="images/userguide/Jarvis.png" width="115%" /> <br />
    Figure 3a: <i>Jarvis start up page</i>
    </div>
   
1. For Mac users, you may see a security popup telling you that _`chrome_driver`_ is not authorized. To resolve this issue, right click on _`chromedriver_mac`_ and select `Open With Terminal`. Then proceed to start Jarvis again.

1. Upon start up, you will be prompted to log in to unlock Jarvis's
 full set of features.
 Please refer to [Logging into Jarvis](#5-logging-into-jarvis) below to complete your login.
 Do note that after editing your login details, exiting and relaunching the Jarvis User interface will take some time
 as Jarvis is attempting to log you into Source Academy.

   <div style="page-break-after: always;"></div>

1. Type the command in the command box and press Enter to execute it. E.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:
   * `view -s:  Lists all Students.`
   * `view -s NAME: Lists information about a Student named John Doe.`
   * `add -t DESCRIPTION: Adds a Todo task with specified description.`
   * `edit -s STUDENT_ID: Edits Student's Name, Email, Telegram.`
   * `exit: Exits the app.`<br><br>

1. Refer to the [Features](#6-features) below for details of each command.<br>

1. Do note that the sample data displayed on initial startup of Jarvis will not be saved. This means that subsequent launches of Jarvis will only contain user-inputted data.

<div style="page-break-after: always;"></div>

## 4. Navigating Jarvis
(Zhen Teng)

Jarvis has a simple Graphical User Interface(GUI), designed for user-friendliness. It is centered around the command
box on the top, outlined by a light green box. In the following subsections you will be taken through a detailed
explanation on the  components of the Graphical User Interface.

### 4.1 UI Layout
(Zhen Teng)

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/uiLayout.png" width="115%" /> <br />
Figure 4.1a: <i>Ui Layout</i>
</div>

<div style="page-break-after: always;"></div>

As seen in Figure 4.1a above, the user interface is split into five separate segments marked 1 to 5.
1. **Top bar** - Displays passive information such as a summary for the user, greeting message, week number, academic topic for
 the week and today's date. A more detailed explanation on each item can be found in the upcoming section
  [Top Bar](#411-top-bar).
1. **Command Box** - An input box for your commands to be keyed in. To execute the inputted command, hit the
 Enter key on your keyboard after keying in your command.
1. **Feedback Box** - The feedback shown to the you after your command has been executed.
1. **Information Panel** - A list of cards showing details on the information stored within Jarvis. This
 includes the `Tasks` keyed in and your `Students` information.
1. **Tab Switcher** - A convenient tab switcher for browsing through the different lists of information
 stored in Jarvis.

In the upcoming sections [4.1.1](#411-top-bar) to [4.1.5](#415-tab-switcher), we will provide a deeper explanation on
the user interface segments 1 to 5 mentioned above.

<div style="page-break-after: always;"></div>

#### 4.1.1 Top Bar
(Zhen Teng)
    
<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/topBar.png" width="115%" /> <br />
Figure 4.1.1a: <i>Detailed breakdown of the Top Bar</i>
</div>

<div style="page-break-after: always;"></div> 

   * In Figure 4.1.1a, we can see that the top bar includes the following 5 components.
        1. **Summary information** - A quick summary indicating the number of ungraded `Missions` and `Quests`, upcoming
           `Consultations` and `Mastery Checks` as well as all `Tasks` in Jarvis. We refer to this summary as the `Summary` feature. 
           The summary is presented as a sentence. In Figure 4.1.1a, the `Summary` feature refers to the sentence
           "Remaining - Nothing!". These values are updated only when Jarvis starts up and after a user command is
           entered.

           Do note that graded `Missions` and `Quests`, as well as past `Consultations` and `Mastery Checks`, will not
           be indicated.
 
        1. **Greeting message** - A warm welcome message for you. If you are not logged in, this message will prompt you
         to do so.<br><br>
        1. **Weekly topic and today's date** - The week count in the semester, together with the academic topic for the
         week and today's date.<br><br>
        1. **Shortcut icons** - These icons are positioned here for your convenience in quickly navigating to Source
         Academy via the blue diamond on the left, and Jarvis' user guide via the white book icon on the right.<br><br>
        1. **Jarvis logo** - This is the application icon of Jarvis.

<div style="page-break-after: always;"></div> 

#### 4.1.2 Command Box
(Zhen Teng)

A text input box for all user commands to be keyed in. You may refer to the [Features](#6-features) section for
a detailed guide on the various commands available in Jarvis. Hit the Enter key to execute the inputted command.

<div style="page-break-after: always;"></div>

#### 4.1.3 Feedback Box
(Zhen Teng)

A box which shows the corresponding feedback to the user according to the commands keyed in. If the command is
successful, a success message will be shown. Otherwise, error messages guiding the user will be displayed.

#### 4.1.4 Information Panel
(Zhen Teng)

A detailed list of information that corresponds to the tab selected in the [Tab Switcher](#415-tab-switcher) on the
left of the Graphical User Interface (GUI).

#### 4.1.5 Tab Switcher
(Zhen Teng)

The tab switcher contains clearly marked tabs: `Student`, `Mission`, `Quest`, `Consultation`, `Mastery Check`, `Task`.
Clicking on each tab brings up the information list for the corresponding field. For instance, clicking on the
`Student` tab will bring up the list of `Students` from your class.  

Upon the execution of each command, the tab is switched automatically for you.

<br>
:warning:  The core features of Jarvis are based on `Students`, `Missions`, `Quests`, `Consultations`, `Mastery Checks` and `Tasks`.
 
:warning:  Each core feature has a tab for the organisation of its information.

<div style="page-break-after: always;"></div>
<div markdown="block" class="alert alert-info">

To navigate the features, there are 2 methods which could be used individually, or in a combined manner. We have
 designed
this flexibility with you, the user in mind.
 1. Only inputting commands into the [Command Box](#412-command-box) annotated "Enter command here...". Upon the
  successful execution of a command, the tab is automatically switched to the corresponding tab for your convenience.
 1. Clicking on the tabs.
</div>

<div style="page-break-after: always;"></div>

## 5. Logging into Jarvis
(Peirong)

Figure 5.0a shows the page upon first login, where you are prompted to key in your Source Academy username and password:

<div style="page-break-after: always;"></div>
<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/loginPrompt.png" width="115%" /> <br />
Figure 5.0a: <i>Login Page</i>
</div>

### 5.1 Prompt
(Peirong)

The prompt contains the format of the edit command to be keyed in. The format is shown in Figure 5.1a below for clarity.

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/editLoginDetails.png" width="115%" /> <br />
Figure 5.1a: <i>Login Page</i>
</div>

To log in, simply edit your login details with the following command:

<br>**Format: `edit -l u/LUMINUS_USERNAME p/LUMINUS_PASSWORD`**

<br>:pencil2: Examples:
* `edit -l u/nusstu\e1234567 p/testpassword`

<div style="page-break-after: always;"></div>

### 5.2 Updated Details Summary
(Peirong)

Within the command box marked as 3 in Figure 5.1a above, Jarvis will notify you if the login was successful and the login information entered.
The username, password and student names are covered with a red rectangle for Figure 5.1a to protect the Avenger's account security.
You will be able to see the login details you entered.

### 5.3 Updated Student Information
(Peirong)

Upon success, all information from Source Academy will be visible to you. The student information tab will display you student information.
The `Telegram` and `E-mail` fields are test values as these information are not available on Source Academy.
You will have to edit them using the [Editing Student Information](#642-editing-student-information---s) command below.

<div style="page-break-after: always;"></div>

## 6. Features

### 6.1 General Notes
(Wiline)

This section includes some general notes on Jarvis.

#### 6.1.1 Notes about Command Format
(Wiline & Eryn)

   * Words in UPPER_CASE are the parameters to be supplied by you.
   <br> e.g. in `add -t DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add -t Mark Missions`.

   * Items in square brackets are optional.
   <br> e.g `view -s [NAME]`, `NAME` is an optional parameter. It can be used as `view -s` or as `view -s [John]`.

   * Parameters can be in any order, unless specified otherwise.
   <br> e.g. if the command specifies `t/TELEGRAM e/EMAIL`, `e/EMAIL t/TELEGRAM` is also acceptable.

   * When there are repeated parameters, Jarvis only recognizes the data given by the last repeated parameter.
   <br> e.g. if the command specifies `t/TELEGRAM e/EMAIL` and the user enters `t/TELEGRAM t/TELEGRAM2 e/EMAIL e/EMAIL2 e/EMAIL3`, Jarvis recognizes the input as `t/TELEGRAM2 e/EMAIL3`.

<div style="page-break-after: always;"></div>

#### 6.1.2 Notes about Jarvis' relationship with Source Academy
(Peirong)

   * Students’ names that are under the Avenger will be fetched automatically from [https://sourceacademy.nus.edu.sg](https://sourceacademy.nus.edu.sg) upon startup.
   * LumiNUS username and passwords will be stored in plaintext.

#### 6.1.3 Notes about Tasks
(Jun Jie)

   * `Todo` is a `Task` without a completion time, `Event` is a `Task` that is required to be completed at a particular point of time and `Deadline` is a `Task` that is required to be completed by a prticular point of time. Hence, `Todo`, `Event` and `Deadline` are all variants of `Task`.
   * Each `Task` you create, be it `Todo`, `Event` or `Deadline`, there will be an unique Task ID assigned to each of them, so
   that there will be no 2 of the same ID in your Task List at any point of time. The Task ID is reflected as eg. D1, E5, T21
   * The first alphabet "T", "E" and "D" refers to `Todo`, `Event`, and `Deadline` respectively, and the number followed
   after the alphabet is the index of the `Task` you created with respect to all the `Tasks` you ever created.

#### 6.1.4 Notes about truncation
(Eryn)

   * If the user input is too long, it is automatically truncated when displayed.
   <br> e.g. if the user enters a `NAME` that is 150 characters long, Jarvis will automatically truncate it to fit the given screen size.

<div style="page-break-after: always;"></div>

#### 6.1.5 Notes about Summary feature
(Zhen Teng)

   * As mentioned above in [4.1.1](#411-top-bar), the `Summary` feature is updated when Jarvis is
   launched and after a user command is successfully executed. As such, the values in the `Summary` will remain the same, even if
   the current time has passed the date and time of a `Consultation` or `Mastery Check`. Also, if a `Mission` or
   `Quest` is graded while Jarvis is open, the number of ungraded `Mission` and `Quests` will remain the same.
   * To obtain the updated values, simply re-start Jarvis or execute a command by keying in a command and hitting the
    Enter key on your keyboard.
   * The `Summary` feature will display all `Tasks` stored in Jarvis, regardless of a `Deadline` or `Event`'s date and
    time. This is in contrast to `Consultations` and `Mastery Checks` where only the upcoming ones are included in
    the count.

<div style="page-break-after: always;"></div>

### 6.2 Viewing help : `help`
(Jun Jie)

Opens a pop up window with the link to this Jarvis user guide.

<br>**Format: `help`**

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/helpCommand.png" width="50%"/> <br />
Figure 6.2a: <i>Help Command</i>
</div>

Upon entering `help` in the command box, a pop up window, as shown in Figure 6.2a, will appear. Clicking on the "Open" button will bring you to this Jarvis user guide.

<div style="page-break-after: always;"></div>

### 6.3 Adding Information : `add`
(Eryn)

In the following subsections, we will explain how the commands for adding information work. Please take note of the
command inputted into the command box to understand how the respective commands are keyed in.

We have excluded screenshots from some sections as the command works similarly to other add commands in this section.

#### 6.3.1 Adding `Consultations` : `-c`
(Eryn)

Adds a `Consultation` session with a `Student` at a specific date and time.

<br>**Format: `add -c NAME d/YYYY-MM-DD t/HH:MM`**

<br>:pencil2: Examples:
* `add -c John Doe d/2020-09-20 t/13:30`
* `add -c Mary Jane d/2021-01-02 t/09:15`

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/addConsultation.png" width="115%" /> <br />
Figure 6.3.1a: <i>Adding a Consultation</i>
</div>

As shown in Figure 6.3.1a, once we key in the command `add -c NAME d/YYYY-MM-DD t/HH:MM`, a success message will be displayed and Jarvis
will auto switch to the `Consultation` tab, and the new `Consultation` will be added to the list.

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-info">
    
* When you add a `Consultation` for a future date while displaying only past `Consultations`, make sure to enter `view -c` to view the complete list to check if the `Consultation` has been added correctly.
* Similarly, when you add a `Consultation` for a past date while displaying only future `Consultations`, make sure to enter `view -c`.
* `NAME` does not have to correspond to an existing student; this is so that the tutor may freely use any nicknames or other identifiers as they see fit. However, it may not be left empty.
* Because date and time is considered in order, the d/ prefix must come earlier than t/.
</div>

#### 6.3.2 Adding `Mastery Checks` : `-mc`
(Eryn)

Adds a `Mastery Check` session with a `Student` at a specific `date` and `time`.

<br>**Format: `add -mc NAME d/YYYY-MM-DD t/HH:MM`**

<br>:pencil2: Examples:
* `add -mc John Doe d/2020-09-20 t/13:30`
* `add -mc Mary Jane d/2021-01-02 t/09:15`

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/addMasteryCheck.png" width="115%" /> <br />
Figure 6.3.2a: <i>Adding a Mastery Check</i>
</div>

<div style="page-break-after: always;"></div>

As shown in Figure 6.3.2a, once we key in the command `add -mc NAME d/YYYY-MM-DD t/HH:MM`, a success message will be displayed and Jarvis
will auto switch to the `Mastery Check` tab, and the new `Mastery Check` will be added to the list.

<div markdown="block" class="alert alert-info">
    
* When you add a `Mastery Check` for a future date while displaying only past `Mastery Checks`, make sure to enter `view -mc` to view the complete list to check if the `Mastery Check` has been added correctly.
* Similarly, when you add a `Mastery Check` for a past date while displaying only future `Mastery Check`, make sure to enter `view -mc`.
* `NAME` does not have to correspond to an existing student; this is so that the tutor may freely use any nicknames or other identifiers as they see fit. However, it may not be left empty.
* Because date and time is considered in order, the d/ prefix must come earlier than t/.
* When a `Mastery Check` is first added, a `FAIL` value will be assigned initially. The tutor may update this later once the `Mastery Check` session is completed.
</div>

#### 6.3.3 Adding `Todos` : `-t`
(Jun Jie)

Adds your personal [`Todo`](#613-notes-about-tasks) with a `description`.

<br>**Format: `add -t DESCRIPTION`**

<br>:pencil2: Examples:
* `add -t Clear the bin`
* `add -t Return calculator to Mary`

<div markdown="block" class="alert alert-info">

* The way to input is similar to adding `Mastery Check` above, only without the date and time. You can refer to [Figure 6.3.2a](#632-adding-mastery-checks---mc) for clearer depiction.
</div>

<div style="page-break-after: always;"></div>

#### 6.3.4 Adding `Events` : `-e`
(Jun Jie)

Adds your personal [`Event`](#613-notes-about-tasks) with a `description`, to attend at a specific date and time.

<br>**Format: `add -e DESCRIPTION d/YYYY-MM-DD t/HH:MM`**

<br>:pencil2: Examples:
* `add -e John’s birthday party d/2020-09-21 t/20:00`
* `add -e CS2103T team meeting d/2020-09-27 t/10:30`

<div markdown="block" class="alert alert-info">
    
* An `Event` requires extra date and time specifications in order to create. 
* The way to input is similar to adding `Mastery Check` above. You can refer to [Figure 6.3.2a](#632-adding-mastery-checks---mc) for clearer depiction.
</div>

#### 6.3.5 Adding `Deadlines` : `-d`
(Jun Jie)

Adds your personal [`Deadline`](#613-notes-about-tasks) with a `description`, to complete by a specific date and time.

<br>**Format: `add -d DESCRIPTION d/YYYY-MM-DD t/HH:MM`**

<br>:pencil2: Examples:
* `add -d CS2103T Week 5 ip tasks d/2020-09-08 t/23:59`
* `add -d Research project report d/2020-10-05 t/10:30`

<div style="page-break-after: always;"></div>
<div markdown="block" class="alert alert-info">
* Similar to `Event`, a `Deadline` requires extra date and time specifications in order to create.

* The way to input is similar to adding `Mastery Check` above. You can refer to [Figure 6.3.2a](#632-adding-mastery-checks---mc) for clearer depiction.
</div>

<div style="page-break-after: always;"></div>

### 6.4 Editing Information : `edit`
(Zhen Teng)
In the following subsections, we will explain how the commands for editing information work. Please take note of the
command inputted into the command box to understand how the respective commands are keyed in.

Certain commands are similar to one another and hence we have excluded screenshots from those sections.

#### 6.4.1 Editing Login Information : `-l`
(Peirong)

You can use tags to specify the field to be edited.

<br>**Format: `edit -l [u/LUMINUS_USERNAME] [p/LUMINUS_PASSWORD]`**

<br>:pencil2: Examples:
* `edit -l u/nusstu\e1234567 p/testpassword`
* `edit -l p/testpassword`

At least one of the optional parameters must be provided.

<div style="page-break-after: always;"></div>
<div markdown="block" class="alert alert-info">

* Do note that it takes a load time of around ~5 seconds after entering this command for the changes from Source Academy to be reflected in the GUI.
* After the `Students`, `Missions` and `Quests` have loaded, take note that the `Telegram` and `Email` fields of the `Student` are placeholder values.
* This is because Source Academy does not contain these 2 fields of any `Student`. Editing the `Student` details will save the updated values.
* After a TA account has been used to log in and fetch the information, logging in with a non-TA account will not change the information displayed
as we choose to give you the benefit of doubt of keying in the wrong login details.
* Whenever a TA account is used to log in, the information will always be updated to reflect the logged in TA's `Student`,
`Mission` and `Quest` information.
</div>

<div style="page-break-after: always;"></div>

#### 6.4.2 Editing `Student` Information : `-s`
(Peirong)

Edits the fields of a `Student`.

<br>**Format: `edit -s INDEX [n/NAME] [t/TELEGRAM] [e/EMAIL]`**

<br>:pencil2: Examples:
* `edit -s 1 e/koolguy@gmail.com t/handsome`
* `edit -s 3 n/Timots`

<div markdown="block" class="alert alert-info">
You can refer to Figure 6.4.3a below for how the input will look like.
</div>

<div style="page-break-after: always;"></div>

#### 6.4.3 Editing `Mastery Checks` : `-mc`
(Eryn)

At least one of the optional parameters must be provided.
Edits the score of a `Mastery Check` session with a `Student`.

<br>**Format: `edit -mc INDEX s/SCORE`**

<br>:pencil2: Examples:
* `edit -mc 1 s/0`
* `edit -mc 3 s/1`

<div style="page-break-after: always;"></div>
<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/editMasteryCheck.png" width="115%" /> <br />
Figure 6.4.3a: <i>Editing a Mastery Check</i>
</div>

As shown in Figure 6.4.3a, once we key in the command `edit -mc 1 s/1`, a success message will be displayed and Jarvis
will auto switch to the `Mastery Check` tab, and the `Mastery Check` with index 1 (first Mastery Check in the list) will be marked as PASS rather than FAIL.
<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-info">
    
* Do note that `SCORE` can only be 0 or 1, according to the actual restrictions of `Mastery Check` pass/fail in CS1101S.
* The `INDEX` should be a non-zero positive number that is a valid index currently displayed on the left side of the target `Mastery Check`.
* `Jarvis` allows the user to edit to the same value; thus, even if no values are actually updated by the edit command, it will display a success message.
</div>

### 6.5 Deleting Information : `delete`
(Jun Jie)

In the following subsections, we will explain how the commands for deleting information work. Please take note of the
command inputted into the command box to understand how the respective commands are keyed in.

#### 6.5.1 Deleting `Consultations` : `-c`
(Eryn)

Deletes a `Consultation` based on the `INDEX` you specify.

<br>**Format: `delete -c INDEX`**

<br>:pencil2: Examples:
* `delete -c 3`

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/deleteConsultation.png" width="115%" /> <br />
Figure 6.5.1a: <i>Deleting a Consultation</i>
</div>

As shown in Figure 6.5.1a, once we key in the command `delete -c 1`, a success message will be displayed and Jarvis
will auto switch to the `Consultation` tab, and the `Consultation` with index 1 (first Consultation in the list) will be removed from the list.

<div markdown="block" class="alert alert-info">
    
* The `INDEX` should be a non-zero positive number that is a valid index currently displayed along with the target
 `Consultation`. You may find the index of the `Consultation` on the left side of the name of the student who requested the Consultation in the Infomation Panel.
</div>

<div style="page-break-after: always;"></div>

#### 6.5.2 Deleting `Mastery Checks` : `-mc`

Deletes a `Mastery Check` based on the `INDEX` you specify.

<br>**Format: `delete -mc INDEX`**

<br>:pencil2: Examples:
* `delete -mc 3`

<div markdown="block" class="alert alert-info">
You can refer to Figure 6.5.1a above for how the input will look like.
</div>

<div markdown="block" class="alert alert-info">
    
* The `INDEX` should be a non-zero positive number that is a valid index currently displayed along with the target
 `Mastery Check`. You may find the index of the `Mastery Check` on the left side of the name of the student who requested the Mastery Check in the Infomation Panel.
</div>

#### 6.5.3 Deleting `Tasks` : `-t`
(Jun Jie)

Deletes a [`Task`](#613-notes-about-tasks) based on the `TASK_ID` you specify.

<br>**Format: `delete -t TASK_ID`**

<br>:pencil2: Examples:
* `delete -t T3`
* `delete -t D2`

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/deleteTask.png" width="115%" /> <br />
Figure 6.5.3a: <i>Deleting a Task</i>
</div>

As shown in Figure 6.5.3a, once we key in the command `delete -s T1`, a success message will be displayed if a `Task` with a TASK_ID of `T1` exists. Jarvis
will delete that `Task` away and then auto switch to the `Task` tab, showing all the remaining `Tasks`.

<div markdown="block" class="alert alert-info">
* Note that TASK_ID, once given, is permanent and no other `Tasks` can receive that TASK_ID even after the deletion of the original one. 
* The Index for `Consultation` and `Mastery Check` is not unique and they will change according to the GUI's displayed list, which is unlike the TASK_ID for `Tasks`.
</div>

<div style="page-break-after: always;"></div>

### 6.6 Viewing Information : `view`
(Zhen Teng)
In the following subsections, we will explain how the commands for viewing information work. Please take note of the
command inputted into the command box to understand how the respective commands are keyed in.

Certain commands are similar to one another and hence we have excluded screenshots from those sections.

<div style="page-break-after: always;"></div>

#### 6.6.1 Viewing a list of all `Students` : `-s`
(Zhen Teng)

Shows a list of all `Students` under you in Jarvis.
Please take note of the command inputted into the command box(marked 1) to understand how the respective commands are
 inputted.

<br>**Format: `view -s`**

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/viewAllStudents.png" width="115%" /> <br />
Figure 6.6.1a: <i>Viewing all Students</i>
</div>

As shown in Figure 6.6.1a, once we key in the command `view -s`, a success message will be displayed and Jarvis
will auto switch to the `Student` tab, showing all `Students` taught by you.

#### 6.6.2 Viewing `Student` with keyword : `-s NAME`
(Zhen Teng)

Shows all `Students` that match (partial and full) the name entered after the command. 

The following is an example where the name is case-sensitive
and partial name matches will work. If you would like to look for your student Tommy Hilfiger, you can simply type in `view -s Tom` 
and the student will be displayed. A full match on the other hand would be as such: key in the command `view -s John Doe` and John
Doe will be displayed.

<br>**Format: `view -s NAME`**

<br>:pencil2: Examples:
* `view -s John Doe`

<div style="page-break-after: always;"></div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/viewOneStudent.png" width="115%" /> <br />
Figure 6.6.2a: <i>Viewing Students with a keyword</i>
</div>

As shown in Figure 6.6.2a, once we key in the command `view -s Alex `, a success message will be displayed and Jarvis
will auto switch to the `Student` tab, showing all `Students` from your classes which match the keyword "Alex". On the
 contrary, using the keyword "alex" would not work as the keyword has to be case sensitive.

#### 6.6.3 Viewing deadline for `Missions` : `-m`
(Wiline)

Shows the deadline for the current `Missions`.

<br>**Format: `view -m`**

<div style="page-break-after: always;"></div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/viewMissionDeadline.png" width="115%" /> <br />
Figure 6.6.3a: <i>Viewing all current Missions</i>
</div>

As shown in Figure 6.6.3a, once we key in the command `view -m`, a success message will be displayed and Jarvis
will auto switch to the `Mission` tab, and show all the current `Missions` and their deadlines.

#### 6.6.4 Viewing ungraded `Missions` : `-um`
(Wiline)

Shows the `Missions` that you have not yet graded.

<br>**Format: `view -um`**

<div style="text-align: center; padding-bottom: 2em">
<img src="images/userguide/viewUngradedMission.png" width="115%" /> <br />
Figure 6.6.4a: <i>Viewing all ungraded Missions</i>
</div>

As shown in Figure 6.6.4a, once we key in the command `view -um`, a success message will be displayed and Jarvis
will auto switch to the `Mission` tab, and show all the ungraded `Missions` and their deadlines.

<div style="page-break-after: always;"></div>

#### 6.6.5 Viewing deadline for `Quests` : `-q`
(Wiline)

Shows the deadline for the current `Quests`.

<br>**Format: `view -q`**

#### 6.6.6 Viewing ungraded `Quests` : `-uq`
(Wiline)

Shows the `Quests` that you have not yet graded.

<br>**Format: `view -uq`**

#### 6.6.7 Viewing all `Consultations` : `-c`
(Eryn)

Shows all confirmed `Consultation` sessions, both past and upcoming.
<br>**Format: `view -c`**

<br>:pencil2: Examples:
* `view -c`

<div style="page-break-after: always;"></div>

#### 6.6.8 Viewing past `Consultations` : `-cp`
(Eryn)

Shows all past `Consultation` sessions.

<br>**Format: `view -cp`**
(Eryn)

<br>:pencil2: Examples:
* `view -cp`

<div markdown="block" class="alert alert-info">
    
* When the `Consultation` time is the same as the local time (year, month, date, hour, and minute all equal), it is considered to have occurred in the past.
</div>

<div style="page-break-after: always;"></div>

#### 6.6.9 Viewing upcoming `Consultations`: `-cu`
(Eryn)

Shows all upcoming `Consultation` sessions.

<br>**Format: `view -cu`**

<br>:pencil2: Examples:
* `view -cu`

<div markdown="block" class="alert alert-info">

* When the time of the `Consultation` displayed by `view -cu` is earlier than the local time (to year, month, date, hour, and minute) and then caught up eventually, it is still considered as an upcoming `Consultation` when you enter `view -cu`. This is to encourage the tutors to have this screen open while holding the `Consultation` session. When you switch to another command, such as `view -c` or `view -cp`, and then come back, you may see the updated `Consultation` list.
</div>

#### 6.6.10 Viewing all `Mastery Checks` : `-mc`
(Eryn)

Shows all confirmed `Mastery Check` sessions, both past and upcoming.

<br>**Format: `view -mc`**

<br>:pencil2: Examples:
* `view -mc`

<div style="page-break-after: always;"></div>

#### 6.6.11 Viewing past `Mastery Checks` : `-mcp`
(Eryn)

Shows all past `Mastery Check` sessions.

<br>**Format: `view -mcp`**

<br>:pencil2: Examples:
* `view -mcp`

<div markdown="block" class="alert alert-info">

* When the `Mastery Check` time is the same as the local time (year, month, date, hour, and minute all equal), it is considered to have occurred in the past.
</div>

#### 6.6.12 Viewing upcoming `Mastery Checks` : `-mcu`
(Eryn)

Shows all upcoming `Mastery Check` sessions.

<br>**Format: `view -mcu`**

<br>:pencil2: Examples:
* `view -mcu`

<div markdown="block" class="alert alert-info">

* When the time of the `Mastery Check` displayed by `view -mcu` is earlier than the local time (to year, month, date, hour, and minute) and then caught up eventually, it is still considered as an upcoming `Mastery Check` when you enter `view -mcu`. This is to encourage the tutors to have this screen open while holding the `Mastery Check` session. When you switch to another command, such as `view -mc` or `view -mcp`, and then come back, you may see the updated `Mastery Check` list.
</div>

<div style="page-break-after: always;"></div>

#### 6.6.13 Viewing all `Tasks` : `-t`
(Jun Jie)

Shows the list of all your current [`Tasks`](#613-notes-about-tasks).
This command includes viewing `Todos`, `Events` and `Deadlines`, which are all considered `Tasks`.

<br>**Format: `view -t`**

#### 6.6.14 Viewing all `Todos` : `-tt`
(Jun Jie)

Shows the list of all your current [`Todos`](#613-notes-about-tasks).

<br>**Format: `view -tt`**

<div style="page-break-after: always;"></div>

#### 6.6.15 Viewing all `Events` : `-te`
(Jun Jie)

Shows the list of all your current [`Events`](#613-notes-about-tasks).

<br>**Format: `view -te`**

#### 6.6.16 Viewing all `Deadlines` : `-td`
(Jun Jie)

Shows the list of all your current [`Deadlines`](#613-notes-about-tasks).

<br>**Format: `view -td`**

### 6.7 Exiting the program : `exit`

Exits the program.

<br>**Format: `exit`**

<div style="page-break-after: always;"></div>

### 6.8 Saving the data
(Wiline)

Jarvis data are saved in the file `jarvis.json` automatically after any command that changes the data. There is no need to save manually.

<div markdown="block" class="alert alert-info">
Note that only `Student`, `Consultation`, `Mastery Check` and `Task` data are saved to the hard drive since `Mission` and `Quest` data
are likely to change frequently and hence, will not need to be persisted.
</div>

<div style="page-break-after: always;"></div>

## 7. Command Summary

### 7.1 Add Command Summary
(Eryn)

| Function            | Tag | Format, Examples                        |
| ------------------- | --- | --------------------------------------- |
| Add `Consultation`  | -c  | add -c NAME d/YYYY-MM-DD t/HH:MM        |
| Add `Mastery Check` | -mc | add -mc NAME d/YYYY-MM-DD t/HH:MM       |
| Add `Todo`          | -t  | add -t DESCRIPTION                      |
| Add `Event`         | -e  | add -e DESCRIPTION d/YYYY-MM-DD t/HH:MM |
| Add `Deadline`      | -d  | add -d DESCRIPTION d/YYYY-MM-DD t/HH:MM |

<div style="page-break-after: always;"></div>

### 7.2 Edit Command Summary
(Zhen Teng)

| Function                   | Tag &nbsp; | Format, Examples                                  |
| -------------------------- | --- | ------------------------------------------------- |
| Edit Login information     | -l  | edit -l [u/LUMINUS_USERNAME] [p/LUMINUS_PASSWORD] |
| Edit `Student` information | -s  | edit -s INDEX [n/NAME] [t/TELEGRAM] [e/EMAIL]     |
| Edit `Mastery Check`       | -mc | edit -mc INDEX s/SCORE                            |

### 7.3 Delete Command Summary
(Jun Jie)

| Function               | Tag | Format, Examples                       |
| ---------------------- | --- | -------------------------------------- |
| Delete `Consultation`  | -c  | delete -c INDEX <br>E.g. delete -c 2   |
| Delete `Mastery Check` | -mc | delete -mc INDEX <br>E.g. delete -mc 2 |
| Delete `Task`          | -t  | delete -t TASK_ID                      |

<div style="page-break-after: always;"></div>

### 7.4 View Command Summary
(Wiline)

| Function                           | Tag  | Format, Examples |
| ---------------------------------- | ---- | --------------- |
| View all `Students`                | -s   | view -s         |
| View `Student` with keyword        | -s   | view -s NAME    |
| View deadlines for `Missions`      | -m   | view -m         |
| View ungraded `Missions`           | -um  | view -um        |
| View deadlines for `Quests`        | -q   | view -q         |
| View ungraded `Quests`             | -uq  | view -uq        |
| View all `Consultations`           | -c   | view -c         |
| View past `Consultations`          | -cp  | view -cp        |
| View upcoming `Consultations`      | -cu  | view -cu        |
| View all `Mastery Checks`          | -mc  | view -mc        |
| View past `Mastery Checks`         | -mcp | view -mcp       |
| View all upcoming `Mastery Checks` | -mcu | view -mcu       |
| View all `Tasks`                   | -t   | view -t         |
| View all `Todos`                   | -tt  | view -tt        |
| View all `Events`                  | -te  | view -te        |
| View all `Deadlines`               | -td  | view -td        |

<div style="page-break-after: always;"></div>

### 7.5 Exit Command Summary
(Peirong)

| Function      | Format, Examples |
| ------------- | ---------------- |
| Exits program | `exit`           |

### 7.6 Help Command Summary
(Peirong)

| Function                           | Format, Examples |
| ---------------------------------- | ---------------- |
| Provide help with program commands | `help`           |

<div style="page-break-after: always;"></div>

## 8. Glossary
### 8.1 Difference Between `Consultations` and `Mastery Checks`
(Eryn)

`Mastery Checks` are a specific type of `Consultations` that is mandatory and graded in CS1101S, unlike normal `Consultations`, and thus are displayed in separate tabs in Jarvis.


## 9. FAQ
(Wiline)

`Q: How do I transfer my data to another Computer?`
<br>`A: Install the app in the other computer and overwrite the empty data file (jarvis.json file) it creates with the file (jarvis.json file) that contains the data of your previous Jarvis home folder.`
