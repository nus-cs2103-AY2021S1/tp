package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.PerformanceCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Performance Window.
 */
public class PerformanceWindow extends UiPart<Stage> {

    private static final String FXML = "PerformanceWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage root;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private HelpWindow helpWindow;
    private AttemptListPanel attemptListPanel;
    private ResponseListPanel responseListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane attemptListPanelPlaceholder;

    @FXML
    private StackPane responseListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public PerformanceWindow(Stage root, Logic logic, HelpWindow helpWindow) {
        super(FXML, root);
        this.logic = logic;
        this.root = root;
        this.helpWindow = helpWindow;
        setWindowDefaultSize(logic.getGuiSettings());
        fillInnerParts();
    }

    /**
     * Creates a new PerformanceWindow.
     */
    public PerformanceWindow(Logic logic, HelpWindow helpWindow) {
        this(new Stage(), logic, helpWindow);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        attemptListPanel = new AttemptListPanel(logic.getAttemptList());
        attemptListPanelPlaceholder.getChildren().add(attemptListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        root.setHeight(guiSettings.getWindowHeight());
        root.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            root.setX(guiSettings.getWindowCoordinates().getX());
            root.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing performance page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Switches to responses panel.
     */
    @FXML
    public void handleViewResponses() {
        attemptListPanelPlaceholder.setVisible(false);
        attemptListPanelPlaceholder.setManaged(false);

        responseListPanel = new ResponseListPanel(logic.getResponseList());
        responseListPanelPlaceholder.getChildren().add(responseListPanel.getRoot());
        responseListPanelPlaceholder.setVisible(true);
        responseListPanelPlaceholder.setManaged(true);
        root.getScene().lookup("#responseList").setVisible(true);
        root.getScene().lookup("#responseList").setManaged(true);
    }

    /**
     * Switches to attempts panel.
     */
    @FXML
    public void handleViewAttempts() {
        responseListPanelPlaceholder.setVisible(false);
        responseListPanelPlaceholder.setManaged(false);

        attemptListPanel = new AttemptListPanel(logic.getAttemptList());
        attemptListPanelPlaceholder.getChildren().add(attemptListPanel.getRoot());
        attemptListPanelPlaceholder.setVisible(true);
        attemptListPanelPlaceholder.setManaged(true);
        root.getScene().lookup("#attemptList").setVisible(true);
        root.getScene().lookup("#attemptList").setManaged(true);
    }

    /**
     * Shows the help window (which is the same one
     * passed down from main window also)
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private PerformanceCommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            PerformanceCommandResult commandResult = logic.executePerformanceCommands(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            if (commandResult.isSwitchToResponses()) {
                handleViewResponses();
            }
            if (commandResult.isSwitchToAttempts()) {
                handleViewAttempts();
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
