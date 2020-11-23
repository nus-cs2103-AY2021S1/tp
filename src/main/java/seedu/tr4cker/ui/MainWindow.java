package seedu.tr4cker.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.ui.countdown.CountdownTabWindow;
import seedu.tr4cker.ui.daily.DailyTabWindow;
import seedu.tr4cker.ui.module.ModuleListPanel;
import seedu.tr4cker.ui.planner.PlannerTabWindow;


/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    // Tabs
    private static final String TAB_HIGHLIGHT_COLOR = "-fx-background-color: #7ed5ea";
    private static final int HOME = 0;
    private static final int DAILY = 1;
    private static final int MODULE = 2;
    private static final int COUNTDOWN = 3;
    private static final int PLANNER = 4;

    private static final String FXML = "MainWindow.fxml";

    private static Logic logic;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;
    private ExpiredTaskListPanel expiredTaskListPanel;
    private CompletedTaskListPanel completedTaskListPanel;
    private ModuleListPanel moduleListPanel;
    private PlannerTabWindow plannerTabWindow;
    private CountdownTabWindow countdownTabWindow;
    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;
    private DailyTabWindow dailyTabWindow;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane expiredTaskListPanelPlaceholder;

    @FXML
    private StackPane completedTaskListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /*
     * Module tab content.
     */
    @FXML
    private StackPane moduleListPanelPlaceholder;

    /*
     * Planner tab content.
     */
    @FXML
    private StackPane plannerTabWindowPlaceholder;

    /*
     * Countdown tab content.
     */
    @FXML
    private StackPane countdownTabWindowPlaceholder;

    /*
     * Daily tab content.
     */
    @FXML
    private StackPane dailyTabWindowPlaceholder;

    /*
     * Tab related objects
     */
    @FXML
    private MenuBar menuBar;

    @FXML
    private TabPane tabPane;

    @FXML
    private Label tabHome;

    @FXML
    private Label tabDaily;

    @FXML
    private Label tabModule;

    @FXML
    private Label tabCountdown;

    @FXML
    private Label tabPlanner;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        MainWindow.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        setTabColors(0);

        helpWindow = new HelpWindow();
    }

    public static Logic getLogic() {
        return logic;
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
        taskListPanel = new TaskListPanel(logic.getFilteredPendingTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        expiredTaskListPanel = new ExpiredTaskListPanel(logic.getFilteredExpiredTaskList());
        expiredTaskListPanelPlaceholder.getChildren().add(expiredTaskListPanel.getRoot());

        completedTaskListPanel = new CompletedTaskListPanel(logic.getFilteredCompletedTaskList());
        completedTaskListPanelPlaceholder.getChildren().add(completedTaskListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTr4ckerFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        /*Modules */
        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList(), logic.getFilteredTaskList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());

        /*Planner */
        plannerTabWindow = new PlannerTabWindow(logic);
        plannerTabWindowPlaceholder.getChildren().add(plannerTabWindow.getRoot());

        /*Countdown*/
        countdownTabWindow = new CountdownTabWindow(logic);
        countdownTabWindowPlaceholder.getChildren().add(countdownTabWindow.getRoot());

        /*Daily*/
        dailyTabWindow = new DailyTabWindow(logic);
        dailyTabWindowPlaceholder.getChildren().add(dailyTabWindow.getRoot());
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
     * Sets color of tab with specified {@code index} to be the {@code TAB_HIGHLIGHT_COLOR}.
     *
     * @param index index of tab starting from 0.
     */
    private void setTabColors(int index) {
        for (int i = 0; i < menuBar.getMenus().size(); i++) {
            if (i != index) {
                menuBar.getMenus().get(i).setStyle("");
            } else {
                menuBar.getMenus().get(i).setStyle(TAB_HIGHLIGHT_COLOR);
            }
        }
    }

    /**
     * Tab switch to main page.
     */
    @FXML
    public void handleShowTabHome() {
        tabPane.getSelectionModel().select(HOME);
        setTabColors(HOME);
        logger.info("Homepage tab is selected");
    }

    /**
     * Tab switch to daily page.
     */
    @FXML
    public void handleShowTabDaily() {
        tabPane.getSelectionModel().select(DAILY);
        setTabColors(DAILY);
    }

    /**
     * Tab switch to modules page.
     */
    @FXML
    public void handleShowTabModule() {
        // for refreshing module page beforehand
        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList(), logic.getFilteredTaskList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());

        // switch
        tabPane.getSelectionModel().select(MODULE);
        setTabColors(MODULE);
    }

    /**
     * Tab switch to countdown page.
     */
    @FXML
    public void handleShowTabCountdown() {
        tabPane.getSelectionModel().select(COUNTDOWN);
        setTabColors(COUNTDOWN);
        logger.info("Countdown tab is selected");
    }

    /**
     * Tab switch to planner page.
     */
    @FXML
    public void handleShowTabPlanner() {
        tabPane.getSelectionModel().select(PLANNER);
        setTabColors(PLANNER);
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

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    public ExpiredTaskListPanel getExpiredTaskListPanel() {
        return expiredTaskListPanel;
    }

    public CompletedTaskListPanel getCompletedTaskListPanel() {
        return completedTaskListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.tr4cker.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            plannerTabWindow.updateIndicator();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowHome()) {
                handleShowTabHome();
            }

            if (commandResult.isShowModules()) {
                handleShowTabModule();
            }

            if (commandResult.isShowPlanner()) {
                plannerTabWindow.updateCalendar(commandResult);
                handleShowTabPlanner();
            }

            if (commandResult.isShowCountdown()) {
                handleShowTabCountdown();
            }

            if (commandResult.isShowDaily()) {
                handleShowTabDaily();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
