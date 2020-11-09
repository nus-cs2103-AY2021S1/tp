package tp.acecs2103.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tp.acecs2103.commons.core.GuiSettings;
import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.util.AppUtil;
import tp.acecs2103.logic.Logic;
import tp.acecs2103.logic.commands.CommandResult;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CategoryPanel adminPanel;
    private CategoryPanel topicPanel;
    private CategoryPanel ipPanel;
    private CategoryPanel tpPanel;
    private WeekDisplay weekDisplay;
    private CommandBox commandBox;
    private FeedbackBox feedbackBox;

    @FXML
    private StackPane weekDisplayPlaceholder;

    @FXML
    private HBox progressBarContainer;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox categoryPanelPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane feedbackBoxPlaceholder;

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
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // TODO: change the method to get each category panel
        adminPanel = new CategoryPanel(logic.getUiTaskList().getAdminList(), "Admin");
        categoryPanelPlaceholder.getChildren().add(adminPanel.getRoot());
        topicPanel = new CategoryPanel(logic.getUiTaskList().getTopicList(), "Topic");
        categoryPanelPlaceholder.getChildren().add(topicPanel.getRoot());
        ipPanel = new CategoryPanel(logic.getUiTaskList().getIpList(), "Ip");
        categoryPanelPlaceholder.getChildren().add(ipPanel.getRoot());
        tpPanel = new CategoryPanel(logic.getUiTaskList().getTpList(), "Tp");
        categoryPanelPlaceholder.getChildren().add(tpPanel.getRoot());
        refreshTitle();

        int currentWeekNumber = AppUtil.getCurrentWeekNumber().getWeekValueInt();
        Double num = (double) currentWeekNumber / (double) 13;
        progressBar.setProgress(num);

        weekDisplay = new WeekDisplay(currentWeekNumber);

        weekDisplayPlaceholder.getChildren().add(weekDisplay.getRoot());

        feedbackBox = new FeedbackBox();
        feedbackBoxPlaceholder.getChildren().add(feedbackBox.getRoot());
        CommandBox commandBox = new CommandBox(this::executeCommand); // bottom of Ace CS2103/T
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        // System.out.println(guiSettings.toString());
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
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
        primaryStage.hide();
    }

    /**
     * Refreshes the title of category panel.
     */
    public void refreshTitle() {
        adminPanel.setText(logic.getUiTaskList().getAdminWeekRange());
        topicPanel.setText(logic.getUiTaskList().getTopicWeekRange());
        ipPanel.setText(logic.getUiTaskList().getIpWeekRange());
        tpPanel.setText(logic.getUiTaskList().getTpWeekRange());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException,
            ParseException, InvalidTaskListOperationException, IllegalArgumentException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            feedbackBox.setFeedbackToUser(commandResult.getFeedbackToUser());
            refreshTitle();

            // categoryPanel.setFeedbackToUser(commandResult.getFeedbackToUser());
            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException | InvalidTaskListOperationException | IllegalArgumentException e) {
            logger.info("Invalid command: " + commandText);
            feedbackBox.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
