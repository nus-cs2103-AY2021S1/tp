package chopchop.ui;

import java.util.logging.Logger;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;
import chopchop.logic.Logic;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.parser.exceptions.ParseException;
import chopchop.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private Model model;

    // Independent Ui parts residing in this Ui container
    private HelpWindow helpWindow;
    private CommandBox commandBox;
    private CommandOutput commandOutput;
    private StatsBox statsOutput;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem handleMenuExit;

    @FXML
    private StackPane displayPlaceholder;

    @FXML
    private StackPane pinBoxPlaceholder;

    @FXML
    private StackPane commandOutputPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, Model model) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.model = model;

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
        setAccelerator(handleMenuExit, KeyCombination.valueOf("F4"));
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
        CommandOutput commandOutput = new CommandOutput();
        this.commandOutput = commandOutput;
        commandOutputPlaceholder.getChildren().add(commandOutput.getRoot());

        StatsBox statsOutput = new StatsBox(model.getRecentlyUsedRecipe(10));
        this.statsOutput = statsOutput;
        pinBoxPlaceholder.getChildren().add(statsOutput.getRoot());

        DisplayController displayController = new DisplayController(logic);
        DisplayNavigator.setDisplayController(displayController);
        displayPlaceholder.getChildren().setAll(displayController.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, this.logic);
        this.commandBox = commandBox;
        commandBoxPlaceholder.getChildren().setAll(commandBox.getRoot());
        primaryStage.addEventFilter(KeyEvent.ANY, event -> {
            if (event.getEventType() == KeyEvent.KEY_TYPED) {
                commandBox.setFocus(event.getCharacter());
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

    void showCommandOutput(CommandResult output) {
        this.commandOutput.setFeedbackToUser(output);
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
     * Executes the command and returns the result.
     *
     * @see chopchop.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {

        try {
            var result = logic.execute(commandText);
            logger.info("Result: " + result.toString());

            if (result.isStatsOutput()) {
                this.commandOutput.clear(); // clear cmd box
                var res = result.getStatsMessage();
                this.statsOutput.setStatsMessage(result.toString(), res.size() == 0);
                this.statsOutput.renderList(result.getStatsMessage());
            } else {
                this.commandOutput.setFeedbackToUser(result);
                this.statsOutput.setStatsMessage("", false); // clear stats box
            }

            if (result.shouldShowHelp()) {
                handleHelp();
            }

            if (result.shouldExit()) {
                handleExit();
            }

            return result;

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);

            commandOutput.setFeedbackToUser(CommandResult.error(e.getMessage(), /* isError: */ true));
            throw e;
        }
    }
}
