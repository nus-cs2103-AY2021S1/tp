---
layout: page
title: Testing Documentation
---

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------
# Testing Documentation
This page will document our testing for classes in Inventoryinator. Segmented into:
* Unittesting
* Integration Testing
* Hybrid Testing
* Acceptance Testing

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder
   ```
    java -jar inventoryinator.jar
   ```

   1. Double-click the jar file Expected: Shows the GUI with a set of sample items and recipes.
    The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an pre-set size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Saving data
1. Dealing with missing/corrupted data files

   1. Delete the data file and restart. The application will generate a valid default save file.
   
## Running tests

There are two ways to run tests.

* **Method 1: Using IntelliJ JUnit test runner**
  * To run all tests, right-click on the `src/test/java` folder and choose `Run 'All Tests'`
  * To run a subset of tests, you can right-click on a test package,
    test class, or a test and choose `Run 'ABC'`
* **Method 2: Using Gradle**
  * Open a console and run the command `gradlew clean test` (Mac/Linux: `./gradlew clean test`)

**Link**: Read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html) to learn more about using Gradle.

--------------------------------------------------------------------------------------------------------------------

## Types of tests

This project has three types of tests:

1. *Unit tests* targeting the lowest level methods/classes.<br>
   e.g. `seedu.address.commons.StringUtilTest`
1. *Integration tests* that are checking the integration of multiple code units (those code units are assumed to be working).<br>
   e.g. `seedu.address.storage.StorageManagerTest`
1. Hybrids of unit and integration tests. These test are checking multiple code units as well as how the are connected together.<br>
   These tests are separated into their respective files. e.g. `seedu.address.logic.commands.CraftItemCommandTest, seedu.address.logic.commands.CraftItemCommandIntegrationTest `
