---
layout: page
title: User Guide
---

HelloFile is a desktop app for managing files, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). By being able to tag frequently used files/folders with a short nickname, you will be able to manage and access your files with ease.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 or above installed in your computer.
1. Download the latest hellofile.jar.
1. Copy the file to the folder you want to use as the home folder for HelloFile to start.
1. Double-click the file to start the app.
1. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Each tag name must be unique, but each file can have multiple tags

</div>

### Viewing help : `help`

Shows a command's usage and format, if no command is specified, show help for all commands.

Format: `help [c/COMMAND]`

### Adding a tag with filepath : `tag`

Shows a message explaning how to access the help page.

Format: `tag f/FILE_PATH t/TAG_NAME`

### Showing file path of a tag : `show`

Shows the file path of the specified tag.

Format: `show t/TAG_NAME`

Examples:
* `show t/my_research`
* `show t/notes`

### Accessing a tagged file : `open`

Opens the file specified in the filepath of the tag.

Format: `open t/TAG_NAME`

Examples:
* `open t/my_research`
* `open t/notes`

### Removing a tag : `untag`

Removes the file from the list of managed files.

Format: `untag t/TAG_NAME`

Examples:
* `untag t/notes`

### Renaming a tag : `retag`

Renames the tag of the file with a new tag.

Format: `retag o/OLD_TAG_NAME t/NEW_TAG_NAME`

* The command is case-sensitive. e.g `notes` will not match `Notes`

Examples:
* `retag o/notes t/secret`

### Listing all tags : `ls`

Lists all the tags with filepath

Format: `ls`

### Clearing screen : `clear`

Clears the UI/CLI screen.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HelloFile home folder.

**Q**: What if the name or the directory of the file I tagged is changed? Can I still access the file using HelloFile?<br>
**A**: No. HelloFile cannot trace the file if its name or directory is changed, but if you still want to manage the file, you can tag it again.

**Q**: Can tag name be duplicated?<br>
**A**: No. The tag names must be unique for all files being managed.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Tag** | `tag f/FILE_NAME t/TAG_NAME` <br> e.g., `tag f/c:/myfolder/file.jpg t/newTag`
**Show** | `show t/TAG_NAME`
**Untag** | `untag t/TAG_NAME`
**Retag** | `retag o/OLD_TAG_NAME t/NEW_TAG_NAME` <br> e.g., `retag o/mytag t/newtag`
**Open** | `open t/TAG_NAME`
**List** | `ls`
**Clear** | `clear`
**Help** | `help [c/COMMAND]`
**Exit** | `exit`
