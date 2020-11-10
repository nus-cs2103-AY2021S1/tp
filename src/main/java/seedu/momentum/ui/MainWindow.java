package seedu.momentum.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.momentum.commons.core.GuiThemeSettings;
import seedu.momentum.commons.core.GuiWindowSettings;
import seedu.momentum.commons.core.LogsCenter;
import seedu.momentum.commons.core.StatisticTimeframe;
import seedu.momentum.commons.core.Theme;
import seedu.momentum.logic.Logic;
import seedu.momentum.logic.commands.CommandResult;
import seedu.momentum.logic.commands.exceptions.CommandException;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.logic.statistic.StatisticEntry;
import seedu.momentum.model.ViewMode;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private static final Logger LOGGER = LogsCenter.getLogger(MainWindow.class);

    private Theme theme;

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CommandBox commandBox;
    private ResultDisplay resultDisplay;
    private TrackedItemListPanel trackedItemListPanel;
    private ReminderDisplay reminderDisplay;
    private TagsDisplay tagsDisplay;
    private TimerListPanel timerListPanel;
    private StatListPanel statListPanel;
    private HelpWindow helpWindow;
    private BottomBar bottomBar;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem testStatMenuItem;

    @FXML
    private StackPane projectListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane reminderDisplayPlaceholder;

    @FXML
    private StackPane statListPanelPlaceholder;

    @FXML
    private StackPane timerListPanelPlaceholder;

    @FXML
    private StackPane infoDisplayPlaceholder;

    @FXML
    private StackPane bottomBarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     *
     * @param primaryStage Primary stage of the application.
     * @param logic Logic associated with the application instance.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiWindowSettings());
        setDefaultTheme(logic.getGuiThemeSettings());

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
     *
     * @param menuItem The item in the menu bar to associate an accelerator to.
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

    //@@author khoodehui
    /**
     * Fills up all the placeholders of the window.
     */
    void fillInnerParts() {
        initCommandBox();
        initResultDisplay();
        initProjectList();
        initReminderDisplay();
        initTagDisplay();
        initTimerList();
        initStatList();
        initBottomBar();
    }

    private void initCommandBox() {
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private void initResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    private void initProjectList() {
        trackedItemListPanel = new TrackedItemListPanel(logic.getObservableDisplayList());
        projectListPanelPlaceholder.getChildren().add(trackedItemListPanel.getRoot());
    }

    //@@author claracheong4
    private void initReminderDisplayAndPlaceholder() {
        reminderDisplay = new ReminderDisplay(logic.isReminderEmpty().get(), logic.getReminder().get());
        reminderDisplayPlaceholder.getChildren().add(reminderDisplay.getRoot());
    }

    private void hideReminder() {
        LOGGER.info("hide reminder");
        reminderDisplayPlaceholder.setVisible(false);
        reminderDisplayPlaceholder.setMaxHeight(0);
    }

    private void showReminder() {
        LOGGER.info("show reminder");
        reminderDisplayPlaceholder.setVisible(true);
        reminderDisplayPlaceholder.setMaxHeight(Region.USE_COMPUTED_SIZE);
    }

    private void initReminderDisplayListeners() {
        logic.isReminderEmpty().addListener((observable, oldValue, newValue) -> {
            reminderDisplayPlaceholder.getChildren().clear();
            if (!newValue) {
                showReminder();
                initReminderDisplayAndPlaceholder();
            } else {
                hideReminder();
            }
        });
        logic.getReminder().addListener((observable, oldValue, newValue) -> {
            reminderDisplayPlaceholder.getChildren().clear();
            if (newValue.length() > 0) {
                showReminder();
                initReminderDisplayAndPlaceholder();
            } else {
                hideReminder();
            }
        });
    }

    private void initReminderDisplay() {
        reminderDisplayPlaceholder.managedProperty().bind(reminderDisplayPlaceholder.visibleProperty());
        initReminderDisplayAndPlaceholder();

        if (logic.isReminderEmpty().get()) {
            hideReminder();
        } else {
            showReminder();
        }
        initReminderDisplayListeners();
    }

    private void hideTags() {
        LOGGER.info("hide tags");
        infoDisplayPlaceholder.setVisible(false);
        infoDisplayPlaceholder.setMaxHeight(0);
    }

    private void showTags() {
        LOGGER.info("show tags");
        infoDisplayPlaceholder.setVisible(true);
        infoDisplayPlaceholder.setMaxHeight(primaryStage.getHeight());
    }

    //@@author khoodehui
    private void initTagDisplay() {
        tagsDisplay = new TagsDisplay(logic.getVisibleTags());
        infoDisplayPlaceholder.getChildren().add(tagsDisplay.getRoot());

        // Add a listener to the project list that will update tags when there are changes made to the project list.
        logic.getObservableDisplayList().addListener(c -> {
            TagsDisplay newTagsDisplay = new TagsDisplay(logic.getVisibleTags());
            infoDisplayPlaceholder.getChildren().clear();
            infoDisplayPlaceholder.getChildren().add(newTagsDisplay.getRoot());
        });

        infoDisplayPlaceholder.managedProperty().bind(infoDisplayPlaceholder.visibleProperty());
        logic.getIsTagsVisible().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showTags();
            } else {
                hideTags();
            }
        });
    }

    private void initStatList() {
        ObservableList<StatisticEntry> stats = logic.getStatistic().getTimePerProjectStatistic();
        statListPanel = new StatListPanel(stats, logic.getStatisticTimeframeSettings().getStatTimeframe());
        statListPanelPlaceholder.getChildren().add(statListPanel.getRoot());
    }

    //@@author boundtotheearth
    private void initTimerList() {
        timerListPanel = new TimerListPanel(logic.getRunningTimers());
        timerListPanelPlaceholder.getChildren().add(timerListPanel.getRoot());
    }

    //@@author pr4aveen
    private void initBottomBar() {
        bottomBar = new BottomBar(logic.getTotalNumberOfItems(), logic.getObservableDisplayList().get().size());
        bottomBarPlaceholder.getChildren().add(bottomBar.getRoot());

        logic.getObservableDisplayList().addListener(c -> {
            BottomBar newBottomBar;

            if (logic.getViewMode() == ViewMode.PROJECTS) {
                newBottomBar = new BottomBar(
                        logic.getObservableDisplayList().get().size(),
                        logic.getTotalNumberOfItems());
            } else {
                newBottomBar = new BottomBar(
                        logic.getObservableDisplayList().get().size(),
                        logic.getTotalNumberOfItems(),
                        logic.getCurrentProject().getName().fullName);
            }

            bottomBarPlaceholder.getChildren().clear();
            bottomBarPlaceholder.getChildren().add(newBottomBar.getRoot());
        });
    }
    //@@author

    /**
     * Sets the default size based on {@code guiWindowSettings}.
     */
    private void setWindowDefaultSize(GuiWindowSettings guiWindowSettings) {
        primaryStage.setHeight(guiWindowSettings.getWindowHeight());
        primaryStage.setWidth(guiWindowSettings.getWindowWidth());
        if (guiWindowSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiWindowSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiWindowSettings.getWindowCoordinates().getY());
        }
    }

    //@@author khoodehui
    /**
     * Sets the default theme based on {@code guiThemeSetting}.
     *
     * @param guiThemeSettings Settings object containing the theme information.
     */
    private void setDefaultTheme(GuiThemeSettings guiThemeSettings) {
        this.theme = guiThemeSettings.getTheme();
        primaryStage.getScene().getStylesheets().add(guiThemeSettings.getTheme().getStylesheet());
    }

    /**
     * Updates the application theme with a {@code newTheme}.
     *
     * @param newTheme The theme to update the application UI to.
     */
    public void updateTheme(Theme newTheme) {
        primaryStage.getScene().getStylesheets().remove(this.theme.getStylesheet());
        primaryStage.getScene().getStylesheets().add(newTheme.getStylesheet());
        this.theme = newTheme;
    }

    /**
     * Updates the statistic list.
     *
     * @param newStatisticTimeframe The timeframe to update the displayed statistics to.
     */
    public void updateStatList(StatisticTimeframe newStatisticTimeframe) {
        StatListPanel newStatList = new StatListPanel(logic.getStatistic().getTimePerProjectStatistic(),
                newStatisticTimeframe);
        statListPanelPlaceholder.getChildren().clear();
        statListPanelPlaceholder.getChildren().add(newStatList.getRoot());
    }
    //@@author

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
        GuiWindowSettings guiWindowSettings = new GuiWindowSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiWindowSettings(guiWindowSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public TrackedItemListPanel getTrackedItemListPanel() {
        return trackedItemListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.momentum.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            LOGGER.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            LOGGER.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
