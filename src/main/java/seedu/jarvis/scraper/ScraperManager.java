package seedu.jarvis.scraper;

import static java.util.Objects.requireNonNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javafx.application.Platform;
import javafx.concurrent.Task;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.commons.exceptions.OsNotSupportedException;
import seedu.jarvis.commons.exceptions.WrongLoginDetailsException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Email;
import seedu.jarvis.model.student.Name;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.student.Telegram;
import seedu.jarvis.model.student.exceptions.DuplicateStudentException;
import seedu.jarvis.storage.Storage;

public class ScraperManager implements Scraper, PropertyChangeListener {

    private static final String STUDENT_FILTER_KEY = "STUDIO JOURNAL";
    private static final String MISSION_FILTER_KEY = "MISSION";
    private static final String QUEST_FILTER_KEY = "QUEST";
    private final Logger logger = LogsCenter.getLogger(ScraperManager.class);
    private WebDriver driver;
    private UserLogin loginInfo;
    private Model model;
    private Storage storage;
    private boolean isAuthenticated;

    /**
     * The scraper constructor to initialize a new scraper instance.
     */
    public ScraperManager(UserLogin loginInfo, Model model, Storage storage) throws OsNotSupportedException {
        requireNonNull(loginInfo);
        requireNonNull(model);
        this.loginInfo = loginInfo;
        this.model = model;
        this.storage = storage;
        this.isAuthenticated = false;

        // Grab current os name
        final String operatingSystem = System.getProperty("os.name").toUpperCase();
        // Set chrome driver path according to os
        if (operatingSystem.contains("WIN")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/chromedriver.exe");
        } else if (operatingSystem.contains("MAC")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/chromedriver_mac");
        } else if (operatingSystem.contains("NUX")) {
            //what about ubuntu??
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/chromedriver_linux");
        } else {
            throw new OsNotSupportedException(operatingSystem);
        }
    }

    /**
     * Initializes a new WebDriver.
     */
    public void initDriver() {
        // Setup headless browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    /**
     * Initializes the scraping sequence to parse for information.
     * @throws WrongLoginDetailsException
     * @throws IOException
     */
    public void startScraping() throws IOException {
        initDriver();

        authenticate();

        if (!isAuthenticated) {
            shutDown();
            return;
        }

        // Run JavaFX-modifying functions after JavaFX Thread is done.
        Platform.runLater(() -> {
            String greeting = "";

            // Only fetch name if name if addressbook is empty
            if (!model.hasGreeting()) {
                greeting = getGreeting();
            }

            List<Mission> missions = getMissions();
            List<Quest> quests = getQuests();
            List<Student> students = getStudents();

            getUngradedMissionsAndQuests();

            try {
                saveToStorage(missions, quests, students, greeting);
            } catch (IOException exception) {
                logger.info("Unable to save SA information to storage");
                return;
            }
            shutDown();
        });
    }

    /**
     * Authenticates the user with their supplied emails and password.
     */
    public void authenticate() {
        // Check if authenticated or login information is empty
        if (isAuthenticated || loginInfo.isEmpty()) {
            return;
        }

        logger.info("Authenticating");

        // Navigate to jarvis
        driver.get("https://sourceacademy.nus.edu.sg/login");

        try {
            // Authenticate using user-supplied details
            driver.findElement(By.xpath("//button[@class='bp3-button bp3-large']")).click();

            driver.findElement(By.xpath("//input[@id='userNameInput']")).sendKeys(
                    loginInfo.getUsername().getUsername());
            driver.findElement(By.xpath("//input[@id='passwordInput']")).sendKeys(
                    loginInfo.getUserPassword());
            driver.findElement(By.xpath("//span[@id='submitButton']")).click();

            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.urlToBe("https://sourceacademy.nus.edu.sg/academy/game"));
        } catch (Exception e) {
            logger.info("Authentication failed due to incorrect login details or unresponsive SA");
            return;
        }

        this.isAuthenticated = true;
    }

    public String getGreeting() {
        if (loginInfo.isEmpty()) {
            return "";
        }

        if (!isAuthenticated) {
            authenticate();
        }

        assert driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/game")
                : "Driver is on the wrong page (Get name)";

        logger.info("Getting user's name");

        driver.findElement(By.xpath("//button[@class='bp3-button bp3-minimal']")).click();

        WebElement nameElement = driver.findElement(By.xpath("//div[@class='bp3-text-overflow-ellipsis bp3-fill']"));
        String greeting = nameElement.getText();

        logger.info("User's name has been fetched");

        return greeting;
    }

    /**
     * Fetches missions from Sourceacademy and saves them to storage.
     * @return a list of missions
     */
    public List<Mission> getMissions() {
        List<Mission> missions = new ArrayList<>();

        if (loginInfo.isEmpty()) {
            return missions;
        }

        if (!isAuthenticated) {
            authenticate();
        }

        logger.info("Getting missions");

        if (!driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/missions")) {
            // Navigate to missions page
            driver.findElement(By.xpath("//a[@href='/academy/missions']")).click();
        }

        assert driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/missions")
                : "Driver is on wrong page (Missions)";

        // Grab mission titles
        List<WebElement> missionTitles = driver.findElements(By.xpath("//h4[@class='bp3-heading listing-title']"));
        List<WebElement> missionDeadlines = driver.findElements(By.xpath("//div[@class='listing-due-date']"));

        for (int i = 0; i < missionTitles.size(); i++) {
            // Add mission to ModelController here
            String mTitle = missionTitles.get(i).getText();
            String mDeadline = missionDeadlines.get(i).getText();

            // Do not add missions that are not yet opened
            if (mDeadline.contains("Opens at")) {
                continue;
            }
            logger.info((i + 1) + "th mission added: " + mTitle);
            missions.add(new Mission(mTitle, mDeadline));
        }
        logger.info("Missions addition complete");

        return missions;
    }

    /**
     * Fetches quests from Sourceacademy and saves them to storage.
     * @return returns a list of quests
     */
    public List<Quest> getQuests() {
        List<Quest> quests = new ArrayList<>();

        if (loginInfo.isEmpty()) {
            return quests;
        }

        if (!isAuthenticated) {
            authenticate();
        }

        if (!driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/quests")) {
            // Navigate to quests page
            driver.findElement(By.xpath("//a[@href='/academy/quests']")).click();
        }

        assert driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/quests")
                : "Driver is on wrong page (Quests)";

        // Grab quest titles
        List<WebElement> questTitles = driver.findElements(By.xpath("//h4[@class='bp3-heading listing-title']"));
        List<WebElement> questDeadlines = driver.findElements(By.xpath("//div[@class='listing-due-date']"));

        for (int i = 0; i < questTitles.size(); i++) {
            // Add quest to ModelController here
            String qTitle = questTitles.get(i).getText();
            String qDeadline = questDeadlines.get(i).getText();

            // Do not add quests that are not yet opened
            if (qDeadline.contains("Opens at")) {
                continue;
            }

            logger.info((i + 1) + "th quest added: " + qTitle);
            quests.add(new Quest(qTitle, qDeadline));
        }
        logger.info("Quests addition complete");

        return quests;
    }

    /**
     * Fetches students from Sourceacademy and saves them to the model.
     * @return A list of students
     */
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        if (loginInfo.isEmpty()) {
            return students;
        }

        if (!isAuthenticated) {
            authenticate();
        }

        logger.info("Getting students");

        if (!driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/grading")) {
            // Navigate to grading page
            driver.findElement(By.xpath("//a[@href='/academy/grading']")).click();
        }

        assert driver.getCurrentUrl().equals("https://sourceacademy.nus.edu.sg/academy/grading")
                : "Driver is on wrong page (Grading)";

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='filterBar']")));

        // Clear filter bar
        WebElement filterBar = driver.findElement(By.xpath("//input[@id='filterBar']"));
        filterBar.clear();

        // Filter by Studio Journal
        driver.findElement(By.xpath("//input[@id='filterBar']")).sendKeys(STUDENT_FILTER_KEY + Keys.ENTER);

        // Get all student names
        List<WebElement> studentNames = driver.findElements(By.xpath("//div[@col-id='studentName']"));
        studentNames.remove(0);

        // Add student names to ModelController here
        for (WebElement name : studentNames) {
            try {
                students.add(new Student(new Name(name.getText()), new Telegram("helloworld"),
                        new Email("student@gmail.com")));
            } catch (DuplicateStudentException dse) {
                // a DuplicateStudentException is thrown when addressbook.json contains a student and ScraperManager
                // tries to fetch the same students on startup to add them to the addressbook.
                logger.info("ScraperManager tried adding a student which existed in addressbook.json");
            }
        }

        return students;
    }

    /**
     * Fetches the ungraded missions and quests that the user has not marked.
     */
    public void getUngradedMissionsAndQuests() {
        if (loginInfo.isEmpty()) {
            return;
        }

        if (!isAuthenticated) {
            authenticate();
        }

        logger.info("Getting ungraded missions and quests");

        // Navigate to grading page
        driver.findElement(By.xpath("//a[@href='/academy/grading']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='filterBar']")));

        WebElement filterBar = driver.findElement(By.xpath("//input[@id='filterBar']"));

        getUngradedMissions(filterBar);
        getUngradedQuests(filterBar);
    }

    /**
     * Fetches the ungraded missions that the user has not marked.
     */
    public void getUngradedMissions(WebElement filterBar) {
        filterBar.clear();
        filterBar.sendKeys(MISSION_FILTER_KEY + Keys.ENTER);

        List<WebElement> missionTitles = driver.findElements(By.xpath("//div[@aria-colindex='2']"));
        List<WebElement> gradingIcons = driver.findElements(By.xpath("//div[@aria-colindex='7']"));

        List<String> ungradedMissions = new ArrayList<>();

        for (int i = 0; i < missionTitles.size(); i++) {
            WebElement icon = gradingIcons.get(i).findElement(By.xpath("..//span[@icon]"));

            if (icon.getAttribute("icon").equals("cross")
                    && !ungradedMissions.contains(missionTitles.get(i).getText())) {
                ungradedMissions.add(missionTitles.get(i).getText());
            }
        }

        for (String mTitle : ungradedMissions) {
            if (model.isMissionInList(mTitle)) {
                model.updateMission(mTitle);
            } else {
                Mission mission = new Mission(mTitle, "Deadline is over", false);
                model.addMission(mission);
            }
        }

        logger.info("Completed getting ungraded missions");
    }

    /**
     * Fetches the ungraded quests that the user has not marked.
     */
    public void getUngradedQuests(WebElement filterBar) {
        filterBar.clear();
        filterBar.sendKeys(QUEST_FILTER_KEY + Keys.ENTER);

        List<WebElement> questTitles = driver.findElements(By.xpath("//div[@aria-colindex='2']"));
        List<WebElement> gradingIcons = driver.findElements(By.xpath("//div[@aria-colindex='7']"));

        List<String> ungradedQuests = new ArrayList<>();

        for (int i = 0; i < questTitles.size(); i++) {
            WebElement icon = gradingIcons.get(i).findElement(By.xpath("..//span[@icon]"));

            if (icon.getAttribute("icon").equals("cross")
                    && !ungradedQuests.contains(questTitles.get(i).getText())) {
                ungradedQuests.add(questTitles.get(i).getText());
            }
        }

        for (String qTitle : ungradedQuests) {
            if (model.isQuestInList(qTitle)) {
                model.updateQuest(qTitle);
            } else {
                Quest quest = new Quest(qTitle, "Deadline is over", false);
                model.addQuest(quest);
            }
        }

        logger.info("Completed getting ungraded quests");
    }

    /**
     * Saves the model information to storage.
     * @param missions list of missions to be saved
     * @param quests list of quests to be saved
     * @param students list of students to be saved
     * @throws IOException
     */
    private void saveToStorage(List<Mission> missions, List<Quest> quests, List<Student> students, String greeting)
            throws IOException {
        try {
            if (!isAuthenticated) {
                return;
            }
            model.setGreeting(greeting);
            model.setMissions(missions);
            model.setQuests(quests);
            model.setStudents(students);

            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException e) {
            throw new IOException("Error saving to addressbook");
        }

    }

    /**
     * Returns the WebDriver object created by ScraperManager
     * The returned object is used for testing.
     * @return a webdriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Shutsdown the webdriver and resets the isAuthenticated variable.
     */
    public void shutDown() {
        isAuthenticated = false;
        driver.quit();
    }

    /**
     * Fires the startScraping method when UserLogin is modified.
     * @param evt the event that is fired
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        loginInfo = (UserLogin) evt.getNewValue();
        // Execute the task on another thread to fetch and update the GUI
        Thread fetchThread = new Thread(new Task<>() {
            @Override
            public Void call() {
                try {
                    startScraping();
                    return null;
                } catch (IOException e) {
                    return null;
                }
            }
        });
        fetchThread.start();
    }
}
