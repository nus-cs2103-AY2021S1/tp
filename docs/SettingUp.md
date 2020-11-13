---
layout: page
title: Setting up and getting started
---
(Contributed by Yu Ming)
### 1. Prerequisites

1. **JDK 11** or above
2. **IntelliJ** IDE  (Highly recommended)

> IntelliJ by default has Gradle and JavaFx plugins installed.
> Do not disable them. If you have disabled them, go to `File` > `Settings` > `Plugins` to re-enable them.

--------------------------------------------------------------------------------------------------------------------

### 2. Setting up the project in your computer

Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.

1. First, **fork** this repo, and **clone** the fork into your computer.
1. **Configure the JDK**: Follow the guide [_[se-edu/guides] IDEA: Configuring the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) to to ensure Intellij is configured to use **JDK 11**.
1. **Import the project as a Gradle project**: Follow the guide [_[se-edu/guides] IDEA: Importing a Gradle project_](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) to import the project into IDEA.<br>
>Note: Importing a Gradle project is slightly different from importing a normal Java project.
  
--------------------------------------------------------------------------------------------------------------------

### 3. Verify the setup
   1. Run the `seedu.address.Main` and try a few commands.
   1. [Run the tests](Testing.md) to ensure they all pass.
   
--------------------------------------------------------------------------------------------------------------------

### 4. Before writing code

1. **Configure the coding style**

   If using IDEA, follow the guide [_[se-edu/guides] IDEA: Configuring the code style_](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to set up IDEA's coding style to match ours.

   Optionally, you can follow the guide [_[se-edu/guides] Using Checkstyle_](https://se-education.org/guides/tutorials/checkstyle.html) to find how to use the CheckStyle within IDEA e.g., to report problems _as_ you write code.

1. **Set up CI**

   This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up required.

1. **Learn the design**

   When you are ready to start coding, we recommend that you get some sense of the overall design by reading about [NUSave’s architecture](DeveloperGuide.md).

1. **Do the tutorials**
   These tutorials will help you get acquainted with the codebase.

   * [Tracing code](tutorials/TracingCode.md)
   * [Removing fields](tutorials/RemovingFields.md)
   * [Adding a new command](tutorials/AddRemark.md)
