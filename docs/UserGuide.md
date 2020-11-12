---
layout: page
title: User Guide
---
By: `Team T09-2` Since: `September 2020`

![Logo](images/fitnus_hd.png)

**Table of Contents:**

* Table of Contents
{:toc}

------------------------------------------------------------------------------------------------------------------------
## 1. Introduction

fitNUS is tailored for **NUS students** who are interested in staying healthy and keeping fit. It is suitable for **all
fitness levels** and is equipped with a customisable **Timetable** for you to manage your time wisely and slot in your
workout routines with ease. fitNUS also displays a **Calorie Graph** to track your daily caloric intake and expenditure.
The application is extremely easy to use, all you have to do is type in your commands in the command box, and the
information will be reflected in the user interface for ease of viewing.

This User Guide aims to showcase the features that fitNUS provides, as well as **step-by-step instructions**
regarding how to make use of these features. The following shows the home page of fitNUS:

![Ui](./images/Ui.png)

<br>
The following shows an example of a personalised Timetable when you click on the Timetable tab:
<br>

![Timetable View](./images/ui-timetable.png)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 2. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fitNUS.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your fitNUS.

1. Double-click the file to start the app. The GUI similar to the one [above](#1-introduction) should appear in a few seconds.
Note how the app contains some sample data.<br>

1. Type your desired command in the command box and press *Enter* to execute it. To verify that fitNUS is working for you,
here are some sample commands you can try:

   * **`exercise_add`**`e/Curls t/Upper` : Creates an Exercise named "Curls" with an "Upper" tag.

   * **`routine_create`**`r/Upper Body Session` : Creates a Routine named "Upper Body Session".

   * **`routine_add_exercise `**`r/Upper Body Session e/Curls` : Adds an Exercise named "Curls"
   to a Routine named "Upper Body Session".

   * **`timetable_add_routine`**`r/Upper Body Session D/Monday T/1600-1800` : Adds the Routine "Upper Body Session"
   to your timetable on Monday at 1600HRS - 1800HRS.

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>

 Now that fitNUS is running as expected, you can kick-start your fitness journey and start working towards your goals!
Experience first-hand the convenience that fitNUS brings to your life by exploring the various commands yourself.

   * Learn more about the specific details of each command [here](#4-commands).

   * View the complete summary of all commands available [here](#5-command-summary) in table format.
   
   * View the glossary [here](#7-glossary) at the end of the guide to explain some key terms.

</div>

--------------------------------------------------------------------------------------------------------------------
## 3. Additional Information

Please note the following formatting and notations that you will encounter as you read on about fitNUS features:

Formatting | Meaning | Examples
--------|-------|-----------
e/EXAMPLE | The character before the forward slash ("/") is a prefix, and words in **UPPER_CASE** after the slash are the parameters to be supplied by you | In `exercise_add e/EXERCISE`, you can use `EXERCISE` as a parameter as such: `exercise_add e/Deadlift`
[ x/X ] | Square brackets signify optional fields that you can consider using | `exercise_add e/EXERCISE [t/TAG]` can be used as `exercise_add e/Deadlift t/glutes` or `exercise_add e/Deadlift`
`...` | Fields which are followed by `...` indicate that you can use them more than once | `[t/TAG]...` can be used as `t/first t/important t/form` etc.
Order of parameters | You can provide valid parameters in any order unless one of the parameters is `INDEX`, in which case `INDEX` must be the first parameter | In `exercise_edit INDEX [e/EXERCISE] [t/TAG]...`, `exercise_edit INDEX [t/TAG]... [e/EXERCISE]` is also accepted. However, `exercise_edit [e/EXERCISE] INDEX [t/TAG]...` is not allowed
`mark-up` | Grey highlight of a word signifies the actual input that you can use | You can type `exercise_delete 1` in the command box and press *Enter* to execute the command
Call Outs | Red boxes are drawn up around areas of interest to show the comparison before you input a command and the result of a successful command outcome. Blue boxes are drawn up around commands you are expected to input to achieve those outcomes | -

--------------------------------------------------------------------------------------------------------------------

## 4. Commands

In this section, each command will be explained in greater depth. Illustrations are used to allow visual aid.
The purpose of each command, how it is formatted and usage examples are found in the subsequent subsections.

### 4.1 Exercise

An Exercise is the foundation of fitNUS. It represents a certain exercise that you can perform and you can glean more
information about it from the tags under it.

#### 4.1.1 Create new exercise : `exercise_add`

You can create a new exercise in fitNUS with the given exercise name.

Format: `exercise_add e/EXERCISE [t/TAG]...`

Example:
* `exercise_add e/Pull Ups t/Body` fitNUS creates a new exercise with the name "Pull Ups" and tag "Body".

**Before adding an exercise:**

![Exercise Add Before](./images/exercise_add_before.png)

<br>
Notice that you have no exercises currently listed.
<br>
<br>

**Successful outcome of adding an exercise "Pull Ups":**

![Exercise Add After](./images/exercise_add_after.png)

<br>
Note that you have exactly 1 exercise listed after the update.
<br>
<br>

#### 4.1.2 Delete exercise : `exercise_delete`

You can delete an existing exercise in fitNUS corresponding to the given index.

Format: `exercise_delete INDEX`

Example:
* `exercise_delete 1` fitNUS deletes the exercise at index 1 in the list.

**Before deleting the first exercise:**

![Exercise Delete Before](./images/exercise_delete_before.png)

<br>
Notice that you have 1 exercise currently listed.
<br>
<br>

**Successful outcome of deleting the first exercise "Pull Ups":**

![Exercise Delete After](./images/exercise_delete_after.png)

<br>
Note that you have no exercises listed after the update.
<br>
<br>

<div markdown="block" class="alert alert-warning">

:warning: **Caution:** When you delete an Exercise, fitNUS will delete this Exercise from all Routines that used to contain it.

</div>

#### 4.1.3 Edit exercise : `exercise_edit`

You can edit the details of the exercise identified by the index number used in the displayed exercise list.
Existing values will be overwritten by the input values.

Format: `exercise_edit INDEX [e/EXERCISE] [t/TAG]...`

Example:
* `exercise_edit 3 e/Squats t/Lower` fitNUS edits the exercise at index 3 in the list to the name "Squats" and tag "Lower".

**Before editing the third exercise:**

![Exercise Edit Before](./images/exercise_edit_before.png)

<br>
Notice that the third exercise is currently "Lunges".
<br>
<br>

**Successful outcome of editing the third exercise to "Squats":**

![Exercise Edit After](./images/exercise_edit_after.png)

<br>
Note that the third exercise is now "Squats" with tag "Lower".
<br>
<br>

#### 4.1.4 Find exercise : `exercise_find`

You can find all exercises in fitNUS whose names contain all of the specified keywords.

Format: `exercise_find KEYWORD [MORE_KEYWORDS]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

This feature is case-insensitive, as the following example will show you. Keywords will return partial matches as well.

</div>

Example:
* `exercise_find bench` fitNUS lists all exercises with names containing "bench" keyword.

**Before finding exercises with "bench" keyword:**

![Exercise Find Before](./images/exercise_find_before.png)

<br>
Notice that you have 3 exercises currently listed.
<br>
<br>

**Successful outcome of finding exercises with "bench" keyword:**

![Exercise Find After](./images/exercise_find_after.png)

<br>
Note that you have only 1 exercise listed which contains the keyword "bench".
<br>
<br>

#### 4.1.5 List exercise : `exercise_list`

After finding a certain exercise, you may want to view all the exercises registered in fitNUS again.
This command lists all existing exercises in fitNUS for you to view.

Format: `exercise_list`

Example:
* `exercise_list` fitNUS displays a list of all exercises in fitNUS.

**Before listing all exercises:**

![Exercise List Before](./images/exercise_list_before.png)

<br>
Notice that you are viewing only 1 exercise as of now if you followed from the previous `exercise_find bench` example.
<br>
<br>

**Successful outcome of listing all exercises:**

![Exercise List After](./images/exercise_list_after.png)

<br>
Note that you view all the exercises again.
<br>
<br>

### 4.2 Routine

Routine is the next building block in fitNUS. It is a collection of Exercise items, and you can customise a
Routine however you want to, by adding or deleting Exercises from it. You will be able to add Routines to your
Timetable, but we will cover more of this later.

#### 4.2.1 Create new routine : `routine_create`

You can create a new routine in fitNUS with the given routine name.

Format: `routine_create r/ROUTINE`

Example:
* `routine_create r/Leg Workout` fitNUS creates a new routine with the name "Leg Workout".

**Before adding a routine:**

![Routine Create Before](./images/routine_create_before.png)

<br>
Notice that you have no routines currently listed.
<br>
<br>

**Successful outcome of creating a routine "Leg Workout":**

![Routine Create After](./images/routine_create_after.png)

<br>
Note that you have exactly 1 routine listed after the update.
<br>
<br>

#### 4.2.2 Delete routine : `routine_delete`

You can delete an existing routine in fitNUS corresponding to the given index.

Format: `routine_delete INDEX`

Example:
* `routine_delete 1` fitNUS deletes the routine with index 1 in the list.

**Before deleting the first routine:**

![Routine Delete Before](./images/routine_delete_before.png)

<br>
Notice that you have 1 routine currently listed.
<br>
<br>

**Successful outcome of deleting the first routine "Leg Workout":**

![Routine Delete After](./images/routine_delete_after.png)

<br>
Note that you have no routines listed after the update.
<br>
<br>

#### 4.2.3 Find routine : `routine_find`

You can find all routines in fitNUS whose names contain all of the specified keywords.

Format: `routine_find KEYWORD [MORE_KEYWORDS]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

This feature is case-insensitive, as the following example will show you. Keywords will return partial matches as well.

</div>

Example:
* `routine_find upper body` fitNUS lists all routines with names containing "upper" and "body" keywords.

**Before finding routines with "upper" and "body" keywords:**

![Routine Find Before](./images/routine_find_before.png)

<br>
Notice that you have 2 routines currently listed.
<br>
<br>

**Successful outcome of finding routines with "upper" and "body" keywords:**

![Routine Find After](./images/routine_find_after.png)

<br>
Note that you have only 1 routine listed which contains the keywords "upper" and "body".
<br>
<br>

#### 4.2.4 List routine : `routine_list`

After finding a certain routine, you may want to view all the routines registered in fitNUS again.
This command lists all existing routines in fitNUS for you to view.

Format: `routine_list`

Example:
* `routine_list` fitNUS displays a list of all routines in fitNUS.

**Before listing all routines:**

![Routine List Before](./images/routine_list_before.png)

<br>
Notice that you are viewing only 1 routine as of now if you followed from the previous `routine_find upper body` example.
<br>
<br>

**Successful outcome of listing all routines:**

![Routine List After](./images/routine_list_after.png)

<br>
Note that you can view all the routines again.
<br>
<br>

#### 4.2.5 Add exercise to routine : `routine_add_exercise`

You can add an existing exercise to a specific routine in fitNUS.

Format: `routine_add_exercise r/ROUTINE e/EXERCISE`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

This feature is case-insensitive, this example just so happens to use capital letters. We have ensured there will be no
duplicates when creating Routine and Exercise.

</div>

Example:
* `routine_add_exercise r/Leg Workout e/Squats` fitNUS adds an exercise named "Squats" to the routine "Leg Workout".

**Before adding "Squats" to "Leg Workout":**

![Routine Add Exercise Before](./images/routine_add_exercise_before.png)

<br>
Notice that you are viewing 2 routines as of now if you followed from the previous `routine_list` example.
Additionally, we have pre-added an exercise "Squats" using `exercise_add e/Squats` to be able to add an exercise to a routine.
<br>
<br>

**Successful outcome of adding "Squats" to "Leg Workout":**

![Routine Add Exercise After](./images/routine_add_exercise_after.png)

<br>
Note that "Squats" now belongs to "Leg Workout" as a tagging.
<br>
<br>

#### 4.2.6 Delete exercise from routine : `routine_delete_exercise`

You can remove an exercise from a specific routine in fitNUS.

Format: `routine_delete_exercise r/ROUTINE e/EXERCISE`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

This feature is case-insensitive, this example just so happens to use capital letters. We have ensured there will be no
duplicates when creating Routine and Exercise.

</div>

Example:
* `routine_delete_exercise r/Leg Workout e/Squats` fitNUS deletes the exercise "Squats" from the routine "Leg Workout".

**Before removing "Squats" from "Leg Workout":**

![Routine Delete Exercise Before](./images/routine_delete_exercise_before.png)

<br>
Notice that "Squats" currently belongs to "Leg Workout" as a tagging.
<br>
<br>

**Successful outcome of removing "Squats" from "Leg Workout":**

![Routine Delete Exercise After](./images/routine_delete_exercise_after.png)

<br>
Note how "Squats" is no longer found under "Leg Workout" as a tagging.
<br>
<br>

#### 4.2.7 View routine details : `routine_view`

You can view a certain routine in fitNUS indicated by the index.

Format: `routine_view INDEX`

Example:
* `routine_view 2` fitNUS displays the routine at index 2 in the list.

**Before viewing the second routine:**

![Routine View Before](./images/routine_view_before.png)

<br>
Notice that you have 2 routines currently listed.
<br>
<br>

**Successful outcome of viewing the second routine:**

![Routine View After](./images/routine_view_after.png)

<br>
Note that you can only view the routine "Upper Body Session" which was at the second index.
<br>
<br>

### 4.3 Lesson

Similar to Routines, you can add Lessons to your Timetable. Both Lessons and Routines represent the essential items you
need to be able to start building your customisable Timetable and never forget another training session again!

#### 4.3.1 Create new lesson : `lesson_add`

You can create a new lesson in fitNUS with the given lesson name.

Format: `lesson_add n/LESSON [t/TAG]...`

Example:
* `lesson_add n/CS2100 t/priority t/homework` fitNUS creates a new lesson with the name "CS2100"
and tags "priority" and "homework".

**Before adding a lesson:**

![Lesson Add Before](./images/lesson_add_before.png)

<br>
Notice that you have no lessons currently listed.
<br>
<br>

**Successful outcome of adding a lesson "CS2100":**

![Lesson Add After](./images/lesson_add_after.png)

<br>
Note that you have exactly 1 lesson listed after the update.
<br>
<br>

#### 4.3.2 Delete lesson : `lesson_delete`

You can delete an existing lesson in fitNUS corresponding to the given index.

Format: `lesson_delete INDEX`

Example:
* `lesson_delete 1` fitNUS deletes the lesson at index 1 in the list.

**Before deleting the first lesson:**

![Lesson Delete Before](./images/lesson_delete_before.png)

<br>
Notice that you have 1 lesson currently listed.
<br>
<br>

**Successful outcome of deleting the first lesson "CS2100":**

![Lesson Delete After](./images/lesson_delete_after.png)

<br>
Note that you have no lessons listed after the update.
<br>
<br>

#### 4.3.3 Edit lesson : `lesson_edit`

You can edit the details of the lesson identified by the index number used in the displayed lesson list.
Existing values will be overwritten by the input values.

Format: `lesson_edit INDEX [n/LESSON] [t/TAG]...`

Example:
* `lesson_edit 3 n/MA1521 t/core` fitNUS edits the lesson at index 3 in the list to the name "MA1521" and tag "core".

**Before editing the third lesson:**

![Lesson Edit Before](./images/lesson_edit_before.png)

<br>
Notice that the third lesson is currently "GER1000".
<br>
<br>

**Successful outcome of editing the third lesson to "MA1521":**

![Lesson Edit After](./images/lesson_edit_after.png)

<br>
Note that the third lesson is now "MA1521" with tag "core".
<br>
<br>

#### 4.3.4 Find lesson : `lesson_find`

You can find all lessons in fitNUS whose names contain any of the specified keywords.

Format: `lesson_find KEYWORD [MORE_KEYWORDS]...`

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

This feature is case-insensitive, this example just so happens to use capital letters. Keywords will return partial
matches as well.

</div>

Example:
* `lesson_find CS GER` fitNUS lists all lessons with names containing "CS" or "GER" keywords.

**Before finding lessons with "CS" or "GER" keywords:**

![Lesson Find Before](./images/lesson_find_before.png)

<br>
Notice that you have 3 lessons currently listed.
<br>
<br>

**Successful outcome of finding lessons with "CS" or "GER" keywords:**

![Lesson Find After](./images/lesson_find_after.png)

<br>
Note that you have 2 lessons listed which contains the keyword "CS" or "GER".
<br>
<br>

#### 4.3.5 List lesson : `lesson_list`

After finding a certain lesson, you may want to view all the lessons registered in fitNUS again.
This command lists all existing lessons in fitNUS for you to view.

Format: `lesson_list`

Example:
* `lesson_list` fitNUS displays a list of all lessons in fitNUS.

**Before listing all lessons:**

![Lesson List Before](./images/lesson_list_before.png)

<br>
Notice that you are viewing 2 lessons as of now if you followed from the previous `lesson_find CS GER` example.
<br>
<br>

**Successful outcome of listing all lessons:**

![Lesson List After](./images/lesson_list_after.png)

<br>
Note that you can view all the lessons again.
<br>
<br>

### 4.4 Timetable

You can add both Routines and Lessons to your timetable to get an overview of your week. You can navigate between the
Timetable and Homepage display by clicking on the corresponding tabs found at the top left of the interface. The following
image shows an annotated example of a personalised Timetable when you click on the Timetable tab, for your reference:

![Timetable View](./images/ui-timetable.png)

<br>
Any changes made to existing Routines or Lessons that have already been added as a slot in the Timetable will be
reflected automatically on the Timetable. In this subsection, we will explore the Timetable feature and its related commands.

#### 4.4.1 Add routine to timetable : `timetable_add_routine`

You can add a complete routine into your timetable in fitNUS.

Format: `timetable_add_routine r/ROUTINE D/DAY T/TIME`

Example:
* `timetable_add_routine r/Leg Workout D/Monday T/1600-1800` fitNUS adds the routine "Leg Workout" to timetable on Monday, 1600-1800.

**Before adding routine "Leg Workout" to Timetable:**

![Timetable Add Routine Before](./images/timetable_add_routine_before.png)

<br>
Notice that there are no Monday slots initially. Additionally, we have pre-added a routine "Leg Workout" to facilitate this example.
<br>
<br>

**Successful outcome of adding routine "Leg Workout" to Timetable:**

![Timetable Add Routine After](./images/timetable_add_routine_after.png)

<br>
Note that a routine slot can be seen now on Monday representing the routine.
<br>
<br>

#### 4.4.2 Add lesson to timetable : `timetable_add_lesson`

You can add a lesson into your timetable in fitNUS.

Format: `timetable_add_lesson n/LESSON D/DAY T/TIME`

Example:
* `timetable_add_lesson n/CS2103T D/Wednesday T/1200-1400` fitNUS adds lesson "CS2103T" to timetable on Wednesday, 1200-1400.

**Before adding lesson "CS2103T" to Timetable:**

![Timetable Add Lesson Before](./images/timetable_add_lesson_before.png)

<br>
Notice that there are no Wednesday slots initially. Additionally, we have pre-added a lesson "CS2103T" to facilitate this example.
<br>
<br>

**Successful outcome of adding lesson "CS2103T" to Timetable:**

![Timetable Add Lesson After](./images/timetable_add_lesson_after.png)

<br>
Note that a lesson slot can be seen now on Wednesday representing the lesson.
<br>
<br>

#### 4.4.3 Delete routine or lesson from schedule : `timetable_delete_slot`

You can choose to delete a routine or lesson from your timetable in fitNUS.

Format: `timetable_delete_slot D/DAY T/TIME`

Example:
* `timetable_delete_slot D/Monday T/1600-1800`fitNUS deletes the routine or lesson scheduled on Monday, 1600-1800.

**Before removing slot from Timetable:**

![Timetable Delete Slot Before](./images/timetable_delete_slot_before.png)

<br>
Notice that there are 2 separate slots on the Timetable. 
<br>
<br>

**Successful outcome of removing slot from Timetable:**

![Timetable Delete Slot After](./images/timetable_delete_slot_after.png)

<br>
Note that the routine slot on Monday is removed from the Timetable.
<br>

### 4.5 BMI

fitNUS allows you to record your height and weight in order to better keep track of the progression of your health
journey. You BMI will be displayed under the BMI Metrics section once you have updated the 2 parameters in the command box.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

You are able to enter your height and weight accurate to 2 decimal places. fitNUS will automatically recalculate your BMI
upon your input.

</div>

#### 4.5.1 Set height : `height`

You can now set your specified height, in centimetres, to fitNUS.

Format: `height h/HEIGHT`

Example:
* `height h/170` fitNUS sets the height of the user, which is 170 cm.


**Before inputting "170" cm:**

![Add Height Before](./images/height_before.png)

<br>
Notice how the default height is set to 160 cm before the change. 
<br>
<br>

**Successful outcome of inputting "170" cm:**

![Add Height After](./images/height_after.png)

<br>
Note the updated height of 170 cm after the change.
<br>

#### 4.5.2 Set weight : `weight`

You can set your specified weight, in kilograms, to fitNUS.

Format: `weight w/WEIGHT`

Example:

* `weight w/72.8` fitNUS sets the weight of the user, which is 72.8 kg.


**Before inputting "72.8" kg:**

![Add Weight Before](./images/weight_before.png)

<br>
Notice how the default weight is set to 45 kg before the change.
<br>
<br>

**Successful outcome of inputting "72.8" kg:**

![Add Weight After](./images/weight_after.png)

<br>
Note the updated weight of 72.8 kg after the change. Also, since both height and weight parameters have been updated,
the current BMI is now up-to-date at 25.19
<br>

### 4.6 Calorie

You can keep track of your daily caloric intake and expenditure by inputting the values in the command box.
You can add or deduct a specified amount from your current calorie count. All successful modifications will be
automatically reflected in the Calorie Graph on the Home tab.

<div markdown="block" class="alert alert-info">

**:information_source: Note:**<br>

* You are able to increase or decrease your calorie intake for the day by typing in a whole number only.

* As with all great calorie trackers, fitNUS only allows you to edit the current day's calorie log so as to prevent any
edits to previous entries.

</div>

#### 4.6.1 Add calories : `calorie_add`

You can add a specified calorie amount to fitNUS.

Format: `calorie_add c/CALORIE`

Example:
* `calorie_add c/1500` fitNUS adds 1500 calories to today's sum.

**Before adding "1500" calories:**

![Calorie Add Before](./images/calorie_add_before.png)

<br>
Notice that the Calorie Graph has no data points prior to change.
<br>
<br>

**Successful outcome of adding "1500" calories:**

![Calorie Add After](./images/calorie_add_after.png)

<br>
Note that the current calorie count now stands at 1500.
<br>

#### 4.6.2 Deduct calories : `calorie_minus`

You can deduct a specified calorie amount from today's sum.

Format: `calorie_minus c/CALORIE`

Example:
* `calorie_minus c/600` Deducts 600 calories from today's sum.

**Before deducting "600" calories:**

![Calorie Minus Before](./images/calorie_minus_before.png)

<br>
Notice that the current calorie count stands at 1500 prior to change.
<br>
<br>

**Successful outcome of deducting "600" calories:**

![Calorie Minus After](./images/calorie_minus_after.png)

<br>
Note that the current calorie count now stands at 900.
<br>

### 4.7 Miscellaneous

There are some additional commands that you can use in fitNUS that are not linked to any of our features. These commands
include `help`, `clear` and `exit`.

#### 4.7.1 Help: `help`

By entering this command, fitNUS shows a brief description of all possible commands and links you to the User Guide where there is a comprehensive guide of
the program usage instructions in fitNUS. The link will appear in a pop-up.

Format: `help`

Example:
* `help` fitNUS now shows a scrollable full command list as a pop-up.

**Before inputting "help":**

![Help](./images/help_before.png)

**Successful outcome of inputting "help":**

![Help](./images/help_after.png)

<br>
Note how a pop-up window appears containing a list of all 27 commands that fitNUS supports.

Alternatively, you may use a keyword to search for a group of commands.
The following depicts what would happen if you chose to streamline your search to a certain keyword.

Format: `help [COMMAND_KEYWORD]`

Example:
* `help timetable` fitNUS shows all the commands containing the word "timetable".

<div style="page-break-after: always;"></div>

**Before inputting "help timetable":**

![Help](./images/help_keyword_before.png)

**Successful outcome of inputting "help timetable":**

![Help](./images/help_keyword_after.png)

<br>
Note how a pop-up window appears containing 3 commands that contain the word "timetable".

#### 4.7.2 Clear: `clear`

By entering this command, fitNUS clears all your data entries from memory.

Format: `clear`

<div markdown="block" class="alert alert-warning">

:warning: **Caution:** fitNUS will not be able to undo this action. Please be very sure this is what you want to do.

</div>

Example:
* `clear` fitNUS clears all data entries from fitNUS.

**Before clearing data:**

![Clear Before](./images/clear_before.png)

<br>
Notice that fitNUS currently displays some exercises, routines and lessons.
<br>
<br>

**Successful outcome of clearing data:**

![Clear After](./images/clear_after.png)
Note that all data entries have been cleared from fitNUS, the Calorie Graph shows no data points and the BMI Metrics have returned to default settings.

#### 4.7.3 Exit: `exit`

fitNUS will save your existing data and will close the application upon your input of this command.

Format: `exit`

### 4.8 Saving the data

Data in fitNUS is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## 5. Command Summary

### 5.1 Exercise

Action | Format | Examples
--------|-------|-----------
**Create Exercise** | `exercise_add e/EXERCISE [t/TAG]...` | `exercise_add e/Bench Press t/Upper`
**Delete Exercise** | `exercise_delete INDEX` | `exercise_delete 1`
**Edit Exercise** | `exercise_edit INDEX [e/EXERCISE] [t/TAG]...` | `exercise_edit 3 e/Squats t/Lower`
**Find Exercise** | `exercise_find KEYWORD [MORE_KEYWORDS]...` | `exercise_find Bench`
**List Exercise** | `exercise_list` | `exercise_list`

### 5.2 Routine

Action | Format | Examples
--------|-------|-----------
**Create Routine** | `routine_create r/ROUTINE` | `routine_create r/Leg Day Session`
**Delete Routine** | `routine_delete INDEX` | `routine delete 5`
**Find Routine** | `routine_find KEYWORD [MORE_KEYWORDS]...` | `routine_find upper body`
**List Routine** | `routine list` | `routine_list`
**Add Exercise to Routine** | `routine_add_exercise r/ROUTINE e/EXERCISE` | `routine_add_exercise r/Leg Day Session e/Squats`
**Delete Exercise from Routine** | `routine_delete_exercise r/ROUTINE e/EXERCISE` | `routine_delete_exercise r/Leg Day Session e/Squats`
**View Routine details** | `routine_view INDEX` | `routine_view 2`

### 5.3 Lesson

Action | Format | Examples
--------|-------|-----------
**Create Lesson** | `lesson_add n/LESSON [t/TAG]...` | `lesson_add n/CS2100 t/priority t/homework`
**Delete Lesson** | `lesson_delete INDEX` | `lesson_delete 1`
**Edit Lesson** | `lesson_edit INDEX [n/LESSON] [t/TAG]...` | `lesson_edit 3 n/MA1521 t/core`
**Find Lesson** | `lesson_find KEYWORD [MORE_KEYWORDS]...` | `lesson_find CS GER`
**List Lesson** | `lesson_list` | `lesson_list`

### 5.4 Timetable

Action | Format | Examples
--------|-------|-----------
**Add Routine to Timetable** | `timetable_add_routine r/ROUTINE D/DAY T/TIME` | `timetable_add_routine r/Leg Day Session D/Monday T/1600-1800`
**Add Lesson to Timetable** | `timetable_add_lesson n/LESSON D/DAY T/TIME` | `timetable_add_lesson n/CS2103T D/Tuesday T/1200-1400`
**Delete Routine or Lesson from Timetable** | `timetable_delete_slot D/DAY T/TIME` | `timetable_delete_slot D/Monday T/1600-1800`

### 5.5 BMI

Action | Format | Examples
--------|-------|-----------
**Add or edit Height** | `height h/HEIGHT` | `height h/170.5`
**Add or edit Weight** | `weight w/WEIGHT` | `weight w/72.8`

### 5.6 Calorie

Action | Format | Examples
--------|-------|-----------
**Add Calories** | `calorie_add c/CALORIE` | `calorie_add c/1000`
**Deduct Calories** | `calorie_minus c/CALORIE` | `calorie_minus c/200`

<div style="page-break-after: always;"></div>

### 5.7 Miscellaneous

Action | Format | Examples
--------|-------|-----------
**Link to User Guide** | `help` | `help`
**Clear all data entries from fitNUS** | `clear` | `clear`
**Save and exit fitNUS** | `exit` | `exit`

--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous fitNUS home folder.

**Q**: How can I save the data I keyed into fitNUS?<br>
**A**: Data is automatically saved into the computer after you input any command that changes the data. Manual saving is not needed.

## 7. Glossary

**Application**: Refers to the fitNUS program you are using

**BMI**: Represents your Body Mass Index, a convenient measurement which serves as an indicator of your health

**Calorie**: Measures the amount of energy you have consumed from food or burned from your workout

**Command**: Refers to the instruction to be performed by fitNUS to achieve the desired result

**Data Entry**: Refers to any inputted data or information such as exercise, routine or lesson etc

**Exercise**: Represents the physical activity you intend to include as part of your workout routine

**Index**: Represents the number beside the name of the corresponding data entry in fitNUS

**Keyword**: Refers to the user input to find matches during a search command

**Lesson**: Represents the module taught by NUS

**Routine**: Represents a collection of exercises

**Tag**: Refers to a word that describes a data entry

**Timetable**: Shows a customisable workout planner to organize your weekly lessons and workout routines
