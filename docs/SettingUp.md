#Calo - Setting Up

## Prerequiste
1. Java version `11` or higher
2. An IDE that supports `Gradle` and `Javafx` plugins. 
    * [For this guide, only instructions for IntelliJ will be provided.]
 
| Notes:| If you have disabled the plugins in IntelliJ, go to File > Settings > Plugins to enable them.|
-------------|-------------------
|

## Setting up the project in your computer

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.
</div>

1.  **Fork** this repo, and **clone** the fork into your computer.
1. Open IntelliJ and
    * If you are at Welcome Page, select `New Project`
    * Else, Click on `File` -> `Project...` -> Select JDK 11 or higher -> `Next`
1. Once you have started a new Project, Click `File` -> `Import Project`
1. Locate the `build.gradle` file from the root of the cloned folder and select it. Click `OK`
1. Click `Open as Project` and accept all defaults settings.

--------------------------------------------------------------------------------------------------------------------

## Before writing code

1. **Configure the coding style**

   If using IDEA, follow the guide [_[se-edu/guides] IDEA: Configuring the code style_](https://se-education.org/guides/tutorials/checkstyle.html) to set up IDEA's coding style to match ours.

   <div markdown="span" class="alert alert-primary">:bulb: **Tip:**

   Optionally, you can follow the guide [_[se-edu/guides] Using Checkstyle_](https://se-education.org/guides/tutorials/checkstyle.html) to find how to use the CheckStyle within IDEA e.g., to report problems _as_ you write code.
   </div>

1. **Set up CI**

   This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up required.

1. **Learn the design**

   When you are ready to start coding, we recommend that you get some sense of the overall design by reading about [AddressBook’s architecture](DeveloperGuide.md#architecture).

--------------------------------------------------------------------------------------------------------------------

## Verify Successful Setup

1. Run the `Main.class` and you will see the Ui as shown below.
1. Try out a few commands listed inside [UserGuide](UserGuide.md)
![Ui](images/Ui.png)
