# TrackIt@NUS - Developer Guide

By: `Team W13-4` Since: `Aug 2020` License: `MIT`

## Table of Contents
1. [**Introduction**](#introduction)
2. [**Setting up**](#setup)
3. [**Design**](#design)
    1. [Architecture](#architecture)
    2. [UI Component](#ui)
    3. [Logic Component](#logic)
    4. [Model Component](#model)
    5. [Common Classes](#common)
4. [**Implementation**](#implementation)
    1. [Module Manager](#module-manager)
    2. [Lesson Manager](#lesson-manager)
    3. [Task Manager](#task-manager)
    4. [Contact Manager](#contact-manager)
    5. [Upcoming Tab](#upcoming-tab)
    6. [Logging](#logging)
    7. [Configuration](#config)
5. [**Documentation**](#documentation)
6. [**Testing**](#testing)
7. [**Dev Ops**](#devops)<br>
[**Appendix A: Product Scope**](#appen-a) <br>
[**Appendix B: User Stories**](#appen-b) <br>
[**Appendix C: Use Cases**](#appen-c) <br>
[**Appendix D: Non-Functional Requirements**](#appen-d) <br>
[**Appendix E: Glossary**](#appen-e) <br>
[**Appendix F: Instructions for Manual Testing**](#appen-f) <br>
[**Appendix G: Effort**](#appen-g) <br>
--------------------------------------------------------------------------------------------------------------------

## **Introduction** <a name="introduction"></a>

**TrackIt@NUS** is a desktop application for managing modules, lessons, tasks, and contacts, tailored to the needs of
 NUS students. It focuses on the _Command Line Interface (CLI)_ while providing users with a simple and clean
  _Graphical User Interface (GUI)_. The main iteraction with **TrackIt@NUS** will be done via commands.
  
**TrackIt@NUS** is an all-in-one solution for busy NUS students. The information that can be managed by **TrackIt@NUS
** includes:

* Modules
* Lessons (for each module)
* Tasks
* Contacts

The purpose of this Developer Guide is to help you understand the design and implementation of **TrackIt@NUS** so
 that you can get started on your contributions to the app.

## **Setting up, getting started** <a name="setup"></a>

Refer to the guide [here](./SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design** <a name="design"></a>

In this section, you will learn about the general design and structure TrackIt@NUS. Subsequently, this section will
 also describe and explain how each component in TrackIt@NUS works individually. TrackIt@NUS is coded using the
  [_Object Oriented Programming_](#oop) paradigm and it follows the [_Facade Pattern_](#facade-p) and [_Command
   Pattern_](#command-p) in software design.

### **Architecture** <a name="architecture"></a>

### **UI Component** <a name="ui"></a>

### **Logic Component** <a name="logic"></a>

### **Model Component** <a name="model"></a>

### **Common Classes** <a name="common"></a>

The `commons` package contains classes used by multiple other components in the `trackitnus.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation** <a name="implementation"></a>

This section describes some noteworthy details on how certain features are implemented.

### **Module Manager** <a name="module-manager"></a>

### **Lesson Manager** <a name="lesson-manager"></a>

### **Task Manager** <a name="task-manager"></a>

### **Contact Manager** <a name="contact-manager"></a>

### **Upcoming Tab** <a name="upcoming-tab"></a>

### **Logging** <a name="logging"></a>

### **Configuration** <a name="config"></a>

--------------------------------------------------------------------------------------------------------------------

## **Documentation** <a name="documentation"></a>

Refer to the guide [here](./Documentation.md)

## **Testing** <a name="testing"></a>

Refer to the guide [here](./Testing.md)

## **Dev Ops** <a name="devops"></a>

Refer to the guide [here](./DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix A: Product Scope** <a name="appen-a"></a>

**Target user profile**:

* take multiple modules
* want to easily track their weekly calendar
* want to be notified about important academic dates coming up
* want to have their personal todos & events integrated with their academic calendar
* want to access related contacts when they browse a module
* want to keep track of modules' details (grading scheme, module's knowledge coverage)
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**
* able to integrate personal todos/events into the weekly academic calendar.
* able to manage modules' information (module's knowledge coverage, grading scheme) & related contacts in one app

## **Appendix B: User stories** <a name="appen-b"></a>

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                   | So that I can…​                                                        |
| -------- | -------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | student              | have all modules' important dates in one place                         | never miss any event                                                   |
| `* * *`  | student              | have information (name, description, time) about any important dates   | always know what to prepare                                            |
| `* * *`  | student              | have all the tutorial/lab details (Zoom link, time) in one place       | easily find them when I need it                                        |
| `* * *`  | forgetful student    | find the grading structure of a module                                 | better revise for each assignment / mid-term / exam                    |
| `* *`    | student              | have all the module descriptions in one place                          | read them if I need to                                                 |
| `*`      | struggling student   | access the contact information of my TA/Prof                           | ask question or schedule a consultation                                |

## **Appendix C: Use cases** <a name="appen-c"></a>

(For all use cases below, the **System** is the `TrackIt@NUS` application and the **Actor** is the `user`, unless specified otherwise)

**Use case: Find the grading structure of a module**

**MSS**

1.  User requests to list the modules
2.  TrackIt@NUS shows a list of all modules
3.  User requests to find the grading structure of a module in the list
4.  TrackIt@NUS shows the requested grading structure

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index (or module name) is invalid.

    * 3a1. TrackIt@NUS shows an error message.

      Use case resumes at step 2.

## **Appendix D: Non-Functional Requirements** <a name="appen-d"></a>

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 tasks/contacts/classes without a noticeable sluggishness in performance for
 typical usage.
3.  Users of the app find the app intuitive and easy to use
4.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
 be able to accomplish most of the tasks faster using commands than using the mouse.
5. The UI of the app is attractive to the users
*{More to be added}*

## **Appendix E: Glossary** <a name="appen-e"></a>

* **Mainstream OS**: Windows, Linux, Unix, OS-X

* **Obect Oriented Programming** <a name="oop"></a>: A type of computer programming paradigm (software design) in which
 programmers define the data type of a data structure, and the types of operations (functions) that can be applied 
 to the data structure

* **Facade Pattern** <a name="facade-p"></a>: A structural design pattern that provides a simplified 
(but limited) interface to a complex system of classes, library or framework.
 While decreasing the overall complexity of the application, it also helps to move unwanted dependencies to one place
 
* **Command Pattern** <a name="command-p"></a>: A Design Pattern that lets you encapsulate actions within Java
 classes. Of which, each class has an `execute()` method which is declared in the Command interface the class implements

## **Appendix F: Instructions for Manual Testing** <a name="appen-f"></a>

## **Appendix G: Effort** <a name="appen-g"></a>
