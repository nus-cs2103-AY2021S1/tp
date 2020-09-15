---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

## Introduction
__ResiReg__ is a Residential College management application that helps residential admins (from the Office of Housing Services) with their daily tasks. __ResiReg__ allows admin to deal with allocating rooms to students, billing, generating OHS reports, and even managing the COVID-19 situation in residences. 

__ResiReg__ has the following main features:
1. View a list of vacant rooms in the College, filtered by COVID-19 zoning if needed.
2. Allocate or deallocate students to vacant rooms in the College when the semester begins, depending on: 
    - year of study
    - faculty
    - major
    - room type.
3. View a list of current room allocations, where each allocation includes room number, student name, room type, and date of check-in. 
4. Add attributes of students staying in the College, including their room allocated, student name, matric number, faculty, year of study, etc.

__ResiReg__ is optimised for OHS who are fast typists who are used to MS Excel, and prefer typing over other means of input. It comes with:
- A Command Line Interface (CLI) which allows you to access all __ResiReg__ features by typing.
- A Graphical User Interface (GUI) that displays the information you need in a grid format.

> __ResiReg__ is currently a work in progress. Here is a low-fidelity mockup of its skeleton. Stay tuned for our progress!
![](https://i.imgur.com/uqEO8Lp.png)


<!---
your comment goes here
and here
3. Manage students serving Stay-at-Home-Notice (SHN) in the College:
   * mark rooms that are used for *quarantine*
   * add records of students serving SHN (duration of notice, symptoms, date of COVID test, emergency contacts)
   * assign students serving SHN to quarantine rooms
   * generate daily reports sent to the Ministry of Health (MOH) 
   
4. Handle College finances:
    * add bills for students e.g. for semesterly stay, installation of air-con, late fees
    * view the outstanding bill due for a student
    * log transactions when a student pays a bill
    * generate half-yearly report for internal audit of College accounts 
-->




## About this Guide
### Basic Information
This User Guide explains how you (as an OHS admin) can use __ResiReg__ to manage tasks at Residential Colleges.

You may refer to [Quick Start](#quick-start) for a short tutorial on how to run __ResiReg__ on your system and use __ResiReg__'s main features. For a full walkthrough of __ResiReg__, please refer to [Features](#features).

### Command Format
This section explains the format of commands in this User Guide.

- Words in `<angular_brackets>` are the parameters to be supplied by the user e.g. in `deallocate <student_name>`, `<student_name>` is a parameter which can be used as `deallocate Jet New`.
- Items in square brackets are optional e.g `<full_name> [-aka <alias>]` can be used as `Jet New -aka JJ` or as `Jet New`.
- Items with … after them can be used multiple times including zero times, unless otherwise stated e.g. `[/m <mod> /ig <interest_group>]…` can be used as `/m mod /ig ig`, `/m mod1 /ig ig1 /m mod2 /ig ig2` etc.

## Quick Start
1. Ensure that Java 11 or above is installed in your computer
2. Download the latest `ResiReg.jar` here.
3. Copy the file to the folder you want to use as the home folder for your **ResiReg**.
4. Double-click the file to start the app. The following window should appear within a few seconds - this is the Session Screen, where you can create, open, or delete interview sessions:
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing `help` and pressing Enter will open this user guide.
6. Some example commands you can try:
    -  `list rooms`: lists all rooms that are vacant.
    - `allocate A0123456X 08-108`: allocate a student with the student ID A0123456X to room number 08-108.
    - `exit`: exits the app.
8. Refer to “Features” for details of all the commands.


### I. Housing Management

>**ResiReg** allows you to manage rooms in the Residential College.

#### 1. Viewing a list of all vacant rooms

Before assigning a room to a student at the start of the semester, you can view a list of all vacant rooms using the `list rooms` command.

##### Command
```
rooms
```
##### Execution Example
```
> rooms
```

*Action*: Lists all rooms, along with the details such as vacancy in the Residential College.

*Output*:
The Output panel on the right should display all rooms, along with their details such as vacancy in the Residential College.

<!--
#### Viewing a list of vacant rooms that are within a zone

During the COVID-19 pandemic, you may need to consider the student's zones during assignment of a room to adhere to NUS policies, so you can view a list of vacant rooms within a zone using the `rooms <zone>` command.

##### Command
```
rooms <zone>
```
##### Execution Example
```
rooms A
```

*Action*: Lists all vacant rooms of Zone A in the Residential College.

*Output*:
The Output panel on the right should list the vacant rooms of Zone A in the Residential College.
-->

#### 2. Allocating a room to a student 

Each student can be allocated a room in the Residential College at the start of the semester by using the `allocate` command.

##### Command
```
allocate <matric_number> <room_number>
```
##### Execution Example
```
> allocate A0123456X 10-108
```

*Action*: Allocates the student A0123456X the room 10-108.

*Output*:
```
Successfully allocated the student A0123456X to room 10-108.
```

#### 3. Deallocating a room for a student

A student can be deallocated a room in the Residential College in cases such as early checkout or dropping out of the programme using the `deallocate` command.

##### Command
```
deallocate <matric_number>
```
##### Execution Example
```
> deallocate A0123456X
```

*Action*: Deletes the room allocation for the student A0123456X.

*Output*:
```
Successfully deallocated room for student A0123456X.
```

#### 4. Viewing a room allocation for a student

The room allocation for a student in the residential college can be viewed to inform said student of his room allocation during check-in

##### Command
```
room <matric_number>
```
##### Execution Example
```
> room A0123456X
```
*Action*: views a room allocation for the student A0123456X.

*Output*:
```
A0123456X is allocated room <room_number>
```


#### 5. Viewing a list of all allocated rooms

The room allocations for all students in the residential college can be viewed to check which room each student stays in.

##### Command
```
rooms
```
##### Execution Example
```
> rooms
```
*Action*: views the room allocations for all students

*Output*:
```
Here is the list of all room allocations:
- Jet New : 11-108
- John New : 12-107
- Jason New : 13-105
```
<br />

### II. Student Management

>**ResiReg** allows you to manage students in the Residential College.

#### 6. Adding a student
A new student can be added to **ResiReg**. The following student details are stored: name, matriculation number, email, faculty and year.

##### Command
```
add student /name <student_name> /matric <matric_number> /email <email> /faculty <faculty> /year <year>
```

The pairs of types and data (eg. `/name <student_name>`) may given be in any order. The student will not be added if some pieces of information is missing.

##### Successful Execution Example
```
add student /name Jet New /matric A0123456X /email jn@u.nus.edu /faculty computing /year 2
```

*Action*: Creates a new student named Jet New whose matriculation number is A0123456X, email is jn@u.nus.edu, faculty is Computing and who is in year 2.

*Output*:
```
Added a new student Jet New.
```

##### Unsuccessful Execution Example
```
add student /name Jet New /matric A0123456X /email jn@u.nus.edu
```

*Action*: The student is not added because the faculty and year are missing. **ResiReg** will show an error message describing which fields are missing.

*Output*:
```
Couldn't add student! The following fields are missing: faculty, year. 
```

#### 7. Listing all students
All the students currently in **ResiReg** can be listed.

##### Command
```
students
```

##### Execution Example
```
> students
```

*Action*: Shows the names of all the students in the panel on the right.
*Output*: 
![](https://i.imgur.com/uqEO8Lp.png)

<!---
#### Editing a room's type
The types of all rooms in **ResiReg** can be edited, to log upgrades like the installation of air conditioners.

**Command**
```
edit room <room_number> <new_room_type>
```

**Execution Example**
```
Edit room 10-108 type air-con
```

*Action*: edits a room's type 
*Output*:
```
Successfully edited type of room 10-108. It is now of type "air-con".
```

#### Editing a room's semesterly fees
The semesterly fees of all rooms in **ResiReg** can be edited, to update room charges when costs increase (e.g. from $1000 to $1500)

**Command**
```
edit fees <room_type> <new_fees>
```

**Example**
```
edit fees air-con $1500
```
*Action*: edits a room's semesterly fees

Output: 
```
The semesterly fees for air-con rooms are now $1500.
```
-->

## FAQ
### Where do I get help?
Just type in the `help` command!


### How do I transfer my data to another Computer?
1. Download the JAR file (`resireg.jar`) on your new computer. 
2. Navigate to where the JAR file is.  
3. Double click on `resireg.jar`
4. Delete the `resireg.json` file in the folder
5. Copy over the `resireg.json` file <em>residing in  your previous **ResiReg** home folder</em> that contains data of your previous **ResiReg** session.

## Command Summary
| Action   | Format Examples |
| -------- | --------------- |
| rooms    | `rooms` |
| students | `students` |
| allocate | `allocate <matric_number> <room_number>` e.g. `allocate A0123456X 10-108` |
| deallocate | `deallocate <matric_number> <room_number>` e.g. `allocate A0123456X 10-108` |
| room | `room <matric_number>` e.g. `room A0123456X` |
| add student | `add student /name <name> /faculty <faculty> /year <year> /email <email>` e.g.`add student /name Jet New /faculty SOC /year 2 /email jn@u.nus.edu` |
| help | `help [command]` e.g. `help` or `help list` |
