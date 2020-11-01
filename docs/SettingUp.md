#TrackIt@NUS - Setting Up

## Prerequisites

1. JDK `11` or above
2. IntelliJ IDE

:information_source: IntelliJ by default has Gradle and JavaFX plugins installed. If you have disabled them, go to
 `File` > `Settings` > `Plugins` to re-enable them.

--------------------------------------------------------------------------------------------------------------------

## Setting up the project in your computer

1. Fork this repo, and clone the fork into your computer

2. Open IntelliJ (if your are not in the welcome screen, click `File` > `Close Project` to close the existing project
 dialog first)
 
 3. Configure the JDK
    1. Click `Configure` > `Project Defaults` > `Project Structure`
    2. Click `New…​` and find the directory of the JDK
    3. Follow the guide [_[se-edu/guides] IDEA: Configuring the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) 
    for more information
    
4. Click `Import Project`

5. Locate the `build.gradle` file and select it. Click `OK`

6. Click `Open as Project`

7. Click `OK` to accept the default settings

8. Run the `Main` and try a few commands

9. [Run the tests](Testing.md) to ensure they all pass

--------------------------------------------------------------------------------------------------------------------

## Before writing code

### Configuring the Coding Style

   If using IDEA, follow the guide [_[se-edu/guides] IDEA: Configuring the code style_](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to set up IDEA's coding style to match ours.

 :bulb: Optionally, you can follow the guide [_[se-edu/guides] Using Checkstyle_](https://se-education.org/guides/tutorials/checkstyle.html) 
 to find how to use the CheckStyle within IDEA e.g., to report problems as you write code.

1. **Set up CI**

   This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up required.

1. **Learn the design**

   When you are ready to start coding, we recommend that you get some sense of the overall design by reading about
    [TrackIt@NUS's architecture](DeveloperGuide.md#architecture).
