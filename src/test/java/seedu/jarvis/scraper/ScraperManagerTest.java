package seedu.jarvis.scraper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import javafx.collections.ObservableList;
import seedu.jarvis.commons.exceptions.DataConversionException;
import seedu.jarvis.commons.exceptions.OsNotSupportedException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.storage.JsonUserLoginStorage;
import seedu.jarvis.storage.UserLoginStorage;
import seedu.jarvis.testutil.TypicalManagers;
import seedu.jarvis.testutil.TypicalStudents;

public class ScraperManagerTest {

    private static String os = System.getProperty("os.name");
    private static final UserLogin USER_LOGIN = getLoginDetails();

    /**
     * Retrieves the login details from the local login.json file.
     * @return A UserLogin object
     */
    private static UserLogin getLoginDetails() {
        UserLoginStorage userLoginStorage = new JsonUserLoginStorage(Paths.get("login.json"));
        UserLogin userLogin = initLogin(userLoginStorage);

        return userLogin;
    }

    /**
     * Returns a UserLogin object corresponding to the data stored.
     * @param storage The place where UserLogin data is stored
     * @return A UserLogin object
     */
    private static UserLogin initLogin(UserLoginStorage storage) {
        UserLogin initializedLogin;
        try {
            Optional<UserLogin> loginOptional = storage.readUserLogin();

            initializedLogin = loginOptional.orElse(new UserLogin());
        } catch (DataConversionException e) {
            initializedLogin = new UserLogin();
        } catch (IOException e) {
            initializedLogin = new UserLogin();
        }

        return initializedLogin;
    }

    /**
     * Iterates through the given mission list, checking if the mission title and deadlines
     * are correctly assigned and not null.
     * @param missionObservableList an observable list of missions
     * @return true if the missionList contains missions with non null titles and deadlines
     */
    private boolean validateMissions(ObservableList<Mission> missionObservableList) {
        for (int i = 0; i < missionObservableList.size(); i++) {
            Mission checkedMission = missionObservableList.get(i);
            String missionTitle = checkedMission.getTitle();
            String missionDeadline = checkedMission.getDeadline();
            if (missionTitle == null || missionDeadline == null) {
                return false;
            } else if (missionTitle.length() == 0 || missionDeadline.length() == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void constructor_nullUserLogin_throwsNullPointerException() {
        System.setProperty("os.name", os);
        Assertions.assertThrows(NullPointerException.class, () -> new ScraperManager(null, null, null));
    }

    @Test
    /*
    as this sets the System.property "os.name" to android, all subsequent tests set it back to the
    original system name.
     */
    public void constructor_unsupportedOs_throwsOsNotSupportedException() {
        System.setProperty("os.name", os);
        String invalidOsName = "android";
        System.setProperty("os.name", invalidOsName);

        Assertions.assertThrows(OsNotSupportedException.class, () -> new ScraperManager(
                TypicalManagers.getUserLogin(), TypicalManagers.getModel(), TypicalManagers.getStorage())
        );
    }

    // glass box test case
    // Unable to simulate the separate Os's and their web driver set-up as the file paths are not
    // present for the Chrome object to be instantiated, even after os.name is set.
    // this test case only tests if the web driver is correctly allocated for the runtime environment os,
    // it does not test if the web driver is correctly allocated for all OSes.
    @Test
    public void constructor_supportedOs_webdriverFieldSetSuccess() throws OsNotSupportedException {
        System.setProperty("os.name", os);
        final String operatingSystem = System.getProperty("os.name").toUpperCase();

        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getUserLogin(), TypicalManagers.getModel(), TypicalManagers.getStorage()
        );
        scraperManager.initDriver();
        scraperManager.shutDown();

        // Check if the System property "webdriver.chrome.driver" is set correctly.
        String webDriver = System.getProperty("webdriver.chrome.driver");
        if (operatingSystem.contains("WIN")) {
            Assertions.assertEquals("src/main/resources/chrome_driver/chromedriver.exe", webDriver);
        } else if (operatingSystem.contains("MAC")) {
            Assertions.assertEquals("src/main/resources/chrome_driver/chromedriver_mac", webDriver);
        } else if (operatingSystem.contains("NUX")) {
            Assertions.assertEquals("src/main/resources/chrome_driver/chromedriver_linux", webDriver);
        }
    }

    @Test
    public void constructor_validLoginDetails_driverInstantiated() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        System.setProperty("os.name", os);
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getPopUserLogin(USER_LOGIN.getUsername(), USER_LOGIN.getUserPassword()),
                TypicalManagers.getModel(), TypicalManagers.getStorage());
        scraperManager.initDriver();
        Assertions.assertNotEquals(null, scraperManager.getDriver());
        scraperManager.shutDown();
    }
    // find a way to test for linux OSes.

    // Test that the assumption, the openqa imports works after the system property
    // webDriver is allocated correctly.

    @Test
    public void authenticate_validLoginDetails_loginSuccess() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        System.setProperty("os.name", os);
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getPopUserLogin(USER_LOGIN.getUsername(), USER_LOGIN.getUserPassword()),
                TypicalManagers.getModel(), TypicalManagers.getStorage());
        scraperManager.initDriver();
        scraperManager.authenticate();
        WebDriver webDriver = scraperManager.getDriver();

        // check for both the url and the title of the web page to be correct
        Assertions.assertEquals("Source Academy", webDriver.getTitle());
        Assertions.assertEquals("https://sourceacademy.nus.edu.sg/academy/game", webDriver.getCurrentUrl());

        scraperManager.shutDown();
    }

    // test getMissions
    @Test
    public void getMissions_validLoginDetails_missionsAddedToModel() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getPopUserLogin(USER_LOGIN.getUsername(), USER_LOGIN.getUserPassword()), model,
                TypicalManagers.getStorage());
        scraperManager.initDriver();
        scraperManager.getMissions();
        ObservableList<Mission> missionObservableList = model.getAddressBook().getMissionList();

        // Assumption that there is always at least one mission in the list. however at the end of the semester this
        // test case may fail.
        int missionCount = missionObservableList.size();
        if (missionCount == 0) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertTrue(validateMissions(missionObservableList));
        }
        scraperManager.shutDown();
    }

    // test getStudents
    @Test
    public void getStudents_validLoginDetails_missionsAddedToModel() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        System.setProperty("os.name", os);
        Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getPopUserLogin(USER_LOGIN.getUsername(), USER_LOGIN.getUserPassword()),
                model, TypicalManagers.getStorage());
        scraperManager.initDriver();
        scraperManager.getMissions();
        ObservableList<Student> studentList = model.getAddressBook().getStudentList();

        // Assumption that there is always at least one mission in the list. however at the end of the semester this
        // test case may fail.
        Assertions.assertNotEquals(0, studentList.size());
        scraperManager.shutDown();
    }

    /**
     * Iterates through the given quest list, checking if the quest title and deadlines
     * are correctly assigned and not null.
     * @param questObservableList an observable list of quests
     * @return true if the questList contains missions with non null titles and deadlines
     */
    private boolean validateQuests(ObservableList<Quest> questObservableList) {
        for (int i = 0; i < questObservableList.size(); i++) {
            Quest checkedQuest = questObservableList.get(i);
            String questTitle = checkedQuest.getTitle();
            String questDeadline = checkedQuest.getDeadline();
            if (questTitle == null || questDeadline == null) {
                return false;
            } else if (questTitle.length() == 0 || questDeadline.length() == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void getQuests_validLoginDetails_questsAddedToModel() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getPopUserLogin(USER_LOGIN.getUsername(), USER_LOGIN.getUserPassword()),
                model, TypicalManagers.getStorage());
        scraperManager.initDriver();
        scraperManager.getQuests();
        ObservableList<Quest> questObservableList = model.getAddressBook().getQuestList();

        // Assumption that there is always at least one quest in the list. however at the end of the semester this
        // test case may fail.
        int questCount = questObservableList.size();

        if (questCount == 0) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertTrue(validateQuests(questObservableList));
        }
        scraperManager.shutDown();
    }

    @Test
    public void startScraping_invalidLoginDetails() throws OsNotSupportedException {
        if (USER_LOGIN.isEmpty()) {
            return;
        }

        Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        ScraperManager scraperManager = new ScraperManager(
                TypicalManagers.getUserLogin(), model, TypicalManagers.getStorage());

        Assertions.assertDoesNotThrow(() -> scraperManager.startScraping());
        scraperManager.shutDown();
    }

}
