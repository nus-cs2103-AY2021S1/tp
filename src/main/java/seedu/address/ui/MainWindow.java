package seedu.address.ui;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.meeting.MeetingListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String STATUS_MESSAGE = "Welcome to PropertyFree, "
            + "the one place to manage all your properties.";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CalendarView calendarView = new CalendarView();

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane personAndBidTabPanePlaceholder;

    @FXML
    private StackPane calendarListPanePlaceholder;

    @FXML
    private StackPane calendar;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
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
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        TabBar personAndJobTabPane = new TabBar(this.logic);
        personAndBidTabPanePlaceholder.getChildren().add(personAndJobTabPane.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(STATUS_MESSAGE);
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        MeetingListPanel meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList());
        calendarListPanePlaceholder.getChildren().add(meetingListPanel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        setCommandBoxDefaultFocus(commandBox);

        calendar.getChildren().add(calendarView.getRoot());

        handleFocusRequestWhenKeyPressed(commandBox);
        setCommandBoxDefaultFocus(commandBox);
    }

    /**
     * Sets the default focus on launch to be commandBox
     */
    private void setCommandBoxDefaultFocus(CommandBox commandBox) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                commandBox.setCommandTextFieldFocusOnStart();
            }
        });
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
            helpWindow.show();
        } else {
            helpWindow.focus();
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
        primaryStage.hide();
    }

    /**
     * Receives the corresponding entityType and executes the method to switch tab bar automatically.
     */
    @FXML
    private void setAutoTab(EntityType entityType) {
        if (entityType != null) {
            TabBar personAndJobTabPane = new TabBar(this.logic);
            personAndJobTabPane.setTab(entityType);
            personAndBidTabPanePlaceholder.getChildren().add(personAndJobTabPane.getRoot());
        }
    }

    /**
     *
     */
    @FXML
    private void setCalendarNavigation(String direction) throws CommandException {
        if (direction.equals("next")) {
            calendarView.handleToNext();
        } else if (direction.equals("prev")) {
            calendarView.handleToPrev();
        } else {
            throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Creates the key pressed event triggers.
     */
    public void handleFocusRequestWhenKeyPressed(CommandBox commandBox) {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SHIFT) {
                    calendarView.handleToNext();
                }
                if (keyEvent.getCode() == KeyCode.CONTROL) {
                    calendarView.handleToPrev();
                }
                if (!commandBox.isCommandTextFieldFocused() && keyEvent.getCode() == KeyCode.ENTER) {
                    commandBox.giveCommandTextFieldFocus();
                }
            }
        });
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
            setAutoTab(commandResult.getEntityType());

            if (commandResult.isCalendarNavigation()) {
                setCalendarNavigation(commandText);
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
