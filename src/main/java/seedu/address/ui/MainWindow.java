package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.theme.Theme;
import seedu.address.ui.theme.ThemeSet;
import seedu.address.ui.util.UiUtil;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static MainWindow instance = null;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Theme currentTheme;
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private TagListPanel tagListPanel;
    private LastInputDisplay lastInputDisplay;
    private ThemeWindow themeWindow;
    private HelpWindow helpWindow;
    private FileExplorerPanel fileExplorerPanel;

    @FXML
    private StackPane resultDisplayPlaceHolder;

    @FXML
    private StackPane tagListPlaceholder;

    @FXML
    private StackPane lastInputPlaceHolder;

    @FXML
    private StackPane commandBoxPlaceHolder;

    @FXML
    private StackPane footerbarPlaceHolder;

    @FXML
    private StackPane fileExplorerPlaceHolder;

    @FXML
    private MenuItem helpMenuItem;

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

        // setAccelerators();

        // Set instance
        instance = this;

        // Theme selection window
        themeWindow = new ThemeWindow();

        // Help window
        helpWindow = new HelpWindow();

        // File explorer panel
        fileExplorerPanel = new FileExplorerPanel();
    }

    public static MainWindow getInstance() {
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        // result display
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceHolder.getChildren().add(resultDisplay.getRoot());

        // tag list view
        tagListPanel = new TagListPanel(logic.getFilteredTagList());
        tagListPlaceholder.getChildren().add(tagListPanel.getRoot());

        // last input display
        lastInputDisplay = new LastInputDisplay();
        lastInputPlaceHolder.getChildren().add(lastInputDisplay.getRoot());

        // command box
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceHolder.getChildren().add(commandBox.getRoot());

        // footer bar
        FooterBar footerBar = new FooterBar(MainApp.VERSION.toString());
        footerbarPlaceHolder.getChildren().add(footerBar.getRoot());

        // file explorer panel
        fileExplorerPanel.setData(logic.getCurrentPath(), logic.getFilteredFileList());
        fileExplorerPlaceHolder.getChildren().add(fileExplorerPanel.getRoot());
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
        currentTheme = ThemeSet.getTheme(guiSettings.getThemeName());
        UiUtil.setTheme(primaryStage, currentTheme);
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
                (int) primaryStage.getX(), (int) primaryStage.getY(), currentTheme.getThemeName());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Opens the theme choosing window.
     */
    @FXML
    public void handleTheme() {
        if (!themeWindow.isShowing()) {
            themeWindow.show();
        } else {
            themeWindow.focus();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    public CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            lastInputDisplay.setLastInput(commandText);
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            fileExplorerPanel.updateCurrentPath();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            lastInputDisplay.setLastInput(commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    public void setTheme(Theme theme) {
        currentTheme = theme;
        UiUtil.setTheme(primaryStage, theme);
        UiUtil.setTheme(helpWindow.getRoot(), theme);
        UiUtil.setTheme(themeWindow.getRoot(), theme);
    }
}
