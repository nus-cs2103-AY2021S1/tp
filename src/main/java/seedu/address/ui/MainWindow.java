package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.textfieldmodules.AutocompleteModule;
import seedu.address.logic.textfieldmodules.FzfModule;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String LIGHT_THEME = "light";
    private static final String DARK_THEME = "dark";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private HostServices hostServices;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private MeetingListPanel meetingListPanel;
    private ModuleListPanel moduleListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private TimelineWindow timelineWindow;

    private CommandHistory commandHistory = new CommandHistory();

    @FXML
    private VBox mainContainer;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem lightThemeMenuItem;

    @FXML
    private MenuItem darkThemeMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    @FXML
    private StackPane moduleListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane selectedMeetingPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, HostServices hostServices) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.hostServices = hostServices;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
        timelineWindow = new TimelineWindow(logic);

        // default theme is dark
        setStylesheet(DARK_THEME, false);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(lightThemeMenuItem, KeyCombination.valueOf("F2"));
        setAccelerator(darkThemeMenuItem, KeyCombination.valueOf("F3"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Sets the stylesheet to be used by the main container.
     * @param theme the theme to be used. Can be light or dark.
     * Guarantees that theme is a valid theme.
     */
    private void setStylesheet(String theme, boolean triggerResultDisplay) {
        logger.info("Switching to " + theme + " theme");
        assert theme.equals(LIGHT_THEME) || theme.equals(DARK_THEME);
        ObservableList<String> mainContainerStylesheets = mainContainer.getStylesheets();
        if (mainContainerStylesheets.size() != 0) {
            mainContainerStylesheets.remove(0);
        }
        String feedbackToUser;
        switch (theme) {
        case DARK_THEME:
            mainContainerStylesheets.add(getClass().getResource("/view/DarkTheme.css").toExternalForm());
            feedbackToUser = "Dark theme set successfully.";
            break;
        case LIGHT_THEME:
            mainContainerStylesheets.add(getClass().getResource("/view/LightTheme.css").toExternalForm());
            feedbackToUser = "Light theme set successfully.";
            break;
        default:
            assert false : theme;
            feedbackToUser = "Theme does not exist";
        }

        if (triggerResultDisplay) {
            resultDisplay.setFeedbackToUser(feedbackToUser);
        }
    }

    /**
     *
     * @return Theme of the application
     */
    private String getTheme() {
        String[] stylesheet = mainContainer.getStylesheets().get(0).split("/");
        return stylesheet[stylesheet.length - 1];
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        setupTextFieldModules(commandBox.getCommandTextField());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());


        commandBox.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.UP) {
                Optional<String> historyText = commandHistory.up();
                if (historyText.isPresent()) {
                    commandBox.setCommandTextField(historyText.get());
                    event.consume();
                }
            }
        });

        commandBox.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.DOWN) {
                Optional<String> historyText = commandHistory.down();
                if (historyText.isPresent()) {
                    commandBox.setCommandTextField(historyText.get());
                }
            }
        });

        if (logic.getSelectedMeeting() != null) {
            MeetingDetailsPanel selectedMeeting = new MeetingDetailsPanel(logic.getSelectedMeeting(), 1);
            selectedMeetingPlaceholder.getChildren().add(selectedMeeting.getRoot());
        }
    }

    private void setupTextFieldModules(TextField commandTextField) {
        AutocompleteModule autocompleteModule = AutocompleteModule.attachTo(commandTextField);
        autocompleteModule.addSuggestions("cn/", () -> logic.getFilteredPersonList().stream()
                .map(p -> p.getName().fullName).collect(Collectors.toList()));
        autocompleteModule.addSuggestions("mdn/", () -> logic.getFilteredModuleList().stream()
                .map(m -> m.getModuleName().getModuleName()).collect(Collectors.toList()));
        autocompleteModule.addSuggestions("mtn/", () -> logic.getFilteredMeetingList().stream()
                .map(m -> m.getMeetingName().meetingName).collect(Collectors.toList()));

        FzfModule.attachTo(commandTextField, () ->
                Stream.of(
                        logic.getFilteredPersonList().stream().map(p -> p.getName().fullName),
                        logic.getFilteredModuleList().stream().map(m -> m.getModuleName().getModuleName()),
                        logic.getFilteredMeetingList().stream().map(m -> m.getMeetingName().meetingName)
                ).flatMap(x -> x).collect(Collectors.toList())
        );
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show(hostServices, getTheme());
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Sets the theme to light theme.
     */
    @FXML
    public void toggleLight() {
        setStylesheet(LIGHT_THEME, true);
    }

    /**
     * Sets the theme to dark theme.
     */
    @FXML
    public void toggleDark() {
        setStylesheet(DARK_THEME, true);
    }

    /**
     * Updates the selected meeting when the meeting view command is executed or any details of the selected meeting
     * is changed.
     */
    public void updateSelectedMeeting() {
        logger.info("UI update selected meeting triggered");
        if (logic.getSelectedMeeting() == null && selectedMeetingPlaceholder.getChildren().size() > 0) {
            selectedMeetingPlaceholder.getChildren().remove(0);
        }
        if (logic.getFilteredMeetingList().size() != 0 && logic.getSelectedMeeting() != null) {
            MeetingDetailsPanel selectedMeeting = new MeetingDetailsPanel(logic.getSelectedMeeting(),
                    logic.getFilteredMeetingList().indexOf(logic.getSelectedMeeting()) + 1);
            if (selectedMeetingPlaceholder.getChildren().size() == 1) {
                selectedMeetingPlaceholder.getChildren().set(0, selectedMeeting.getRoot());
            } else {
                selectedMeetingPlaceholder.getChildren().add(selectedMeeting.getRoot());
            }
        }
    }

    /**
     * Updates the timeline window whenever a change is made in meetings
     */
    public void updateTimeline() {
        timelineWindow.hide();
        timelineWindow = timelineWindow.updateLogic(logic);
    }

    /**
     * Opens the timeline window or focuses on it if it's already opened.
     */
    @FXML
    public void handleShowTimeline() {
        logger.info("UI show timeline triggered");

        if (!timelineWindow.isShowing()) {
            timelineWindow.show(getTheme());
        } else {
            timelineWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        timelineWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isTriggerUpdate()) {
                updateSelectedMeeting();
            }

            if (commandResult.isShowTimeline()) {
                handleShowTimeline();
            }

            if (commandResult.isTriggerUpdateTimeline()) {
                updateTimeline();
            }

            if (commandResult.isTriggerDarkTheme()) {
                setStylesheet(DARK_THEME, false);
            }

            if (commandResult.isTriggerLightTheme()) {
                setStylesheet(LIGHT_THEME, false);
            }

            commandHistory.addCommand(commandText);
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
