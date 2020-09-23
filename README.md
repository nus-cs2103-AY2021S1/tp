[![CI Status](https://github.com/AY2021S1-CS2103T-F12-3/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2021S1-CS2103T-F12-3/tp/actions)
![Ui](docs/images/OriginalImages/Ui.png)

### Cap 5.0 Buddy

Cap 5.0 Buddy is a desktop app that helps NUS SoC students to keep track of their module details efficiently, optimized for use via a
**Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
It helps them centralize key module details and follows their study 
progress by allowing efficient management of module details.

## Contents:
[Quick Start](#Quick-Start "Goto Quick Start")
- [Features](#Features "Goto Features")
    - [Adding a module : `add module`](#Adding-a-module "Goto Adding-a-module")
    - [Viewing a module : `view`](#Viewing-a-module "Goto Viewing-a-module")
    - [Editing a module : `edit zoom`](#Editing-a-module "Goto Editing-a-module")
    - [Deleting a module : `delete`](#Deleting-a-module "Goto Deleting-a-module")
    - [Adding a zoom link for a module : `add zoom`](#Adding-a-zoom-link-for-a-module "Goto Adding-a-zoom-link-for-a-module")
- [Command Summary](#Command-Summary "Goto Command-Summary")
- [FAQ](#Frequently-Asked-Questions-FAQ "Goto Frequently-Asked-Questions-FAQ")
- [Acknowledgements](#Acknowledgments "Goto Acknowledgments")

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest cap5buddy.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Cap 5 Buddy application.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

## Features

1. Adding a module : `add module` 
Create and add a new module with the input name into the system.

**Format:** `add module [MODULE_NAME]`
Create and add a new module with `MODULE_NAME`

**Examples of Usage:**
* add module CS2103T adds a module called CS2103T into the system.
* add module LSM1301 adds a module called LSM1301 into the system.

2. Viewing a module : `view`
Views a module stored in the system

**Format:** `view [MODULE_NAME]`
Tip: Using the keyword all in place of specified module name will display all module details

**Example of usage:**
* view cs2103t views the specified module
* view all views all the modules stored

3. Editing a module : `edit zoom`
Edits an existing module in the module list.

**Format:** `edit zoom [MODULE_NAME] [ZOOM_LINK]`
* Edits the zoom link of a module named [MODULE_NAME] to [ZOOM_LINK]

**Example of usage:**
edit zoom CS1101S https://sample.zoom.us edits the zoom link for module named CS1101S to https://sample.zoom.us 

4. Deleting a module : `delete`
Deletes the specified module from the system.

**Format:** `delete [MODULE_NAME]`
* Deletes the module with the specified [MODULE_NAME]

**Example of usage:**
delete CS1231 deletes the module named CS1231

5. Adding a zoom link for a module : `add zoom`
Adds a zoom link to an existing module

**Format:** `add zoom [MODULE_NAME] [ZOOM_LINK]`
* Adds a zoom link with specified [ZOOM LINK] to the module with the specified [MODULE_NAME]

**Example of usage:**
add zoom cs2103T https://sample.zoom.us adds a zoom link 'https://sample.zoom.us' for the mod 'cs2103T'

## Command Summary

## Frequently Asked Questions
Q: How do you add a module into the program?
A: Run the program and wait for the terminal to start up. Next, type in : “add module [CS2103T]” to add a module called CS2103T. <br/>
Q: How do you view the zoom links of a particular module? 
A: When the program has started running, enter the following in the terminal : “view [CS2103T]” to view the zoom link for the module called CS2103T.

## Acknowledgments

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).