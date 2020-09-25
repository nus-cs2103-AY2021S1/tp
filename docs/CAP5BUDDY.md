## Setting up the program in your computer

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**

Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.
</div>

1. Ensure that you have Java JDK 11 installed on your OS.
2. Download the JAR file of the program [_here_](https://github.com/AY2021S1-CS2103T-F12-3)
3. Save the file into a folder name **CAP5.0Buddy** in any directory.
4. Run the JAR file
    a. Double click on the JAR file to execute.
    b. Open up Command Prompt in OS, navigate to directory of the JAR file and return
    ```
    java -jar *JARfile_name*.jar
    ```
5. Refer to our [_User Guide_](UserGuide.md) to start using the program.

## Error fixing
If you face any issues starting to launch the JAR file, pls refer to the FAQ below.

1. Unable to run the file due to missing java in OS.

```
##example> java -jar cap5.jar
'java' is not recognized as an internal or external command,
operable program or batch file.
```
Solution:
Install java JDK on your OS, which can be found [_here_](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

2. Unable to run JAR file due to the JAR being compiled in a higher version.

```
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.UnsupportedClassVersionError: mattbot/Launcher has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
        at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
        at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
        at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:355)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:601)
```
Solution:
Make sure that you have Java JDK 11 installed on your computer. If not, you can uninstall your current java and reinstall from the above link, or [_here_](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
