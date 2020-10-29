//@@author fall9x

package chopchop.ui;

import java.util.logging.Logger;

import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;
import chopchop.logic.Logic;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.parser.exceptions.ParseException;
import chopchop.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
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

    private CommandBox commandBox;
    private CommandOutput commandOutput;
    private StatsBox statsOutput;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem fileMenuItem;

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
        this.setWindowDefaultSize(logic.getGuiSettings());

        this.setAccelerators();
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    private void setAccelerators() {
        this.helpMenuItem.setAccelerator(KeyCombination.valueOf("F1"));
        this.fileMenuItem.setAccelerator(KeyCombination.valueOf("Alt+F4"));
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        var commandOutput = new CommandOutput();
        this.commandOutput = commandOutput;
        this.commandOutputPlaceholder.getChildren().add(commandOutput.getRoot());

        var statsOutput = new StatsBox(this.model.getRecentlyUsedRecipe(10));

        this.statsOutput = statsOutput;
        this.pinBoxPlaceholder.getChildren().add(statsOutput.getRoot());

        var displayController = new DisplayController(logic);
        DisplayNavigator.setDisplayController(displayController);
        this.displayPlaceholder.getChildren().setAll(displayController.getRoot());

        var commandBox = new CommandBox(this::executeCommand, this.logic);
        this.commandBox = commandBox;
        this.commandBoxPlaceholder.getChildren().setAll(commandBox.getRoot());
        this.primaryStage.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            commandBox.setFocus(event.getCharacter());
        });
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        this.primaryStage.setHeight(guiSettings.getWindowHeight());
        this.primaryStage.setWidth(guiSettings.getWindowWidth());

        if (guiSettings.getWindowCoordinates() != null) {
            this.primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            this.primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    public void show() {
        this.primaryStage.show();
    }

    public void showCommandOutput(CommandResult output) {
        this.commandOutput.setFeedbackToUser(output);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            var result = this.logic.execute(commandText);
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

            if (result.shouldExit()) {
                handleExit();
            }

            return result;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);

            this.commandOutput.setFeedbackToUser(CommandResult.error(e.getMessage()));
            throw e;
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    private void handleHelp() {
        try {
            this.executeCommand("help");
        } catch (CommandException | ParseException ignored) {
            // Command cannot fail
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(this.primaryStage.getWidth(), this.primaryStage.getHeight(),
                (int) this.primaryStage.getX(), (int) this.primaryStage.getY());
        this.logic.setGuiSettings(guiSettings);
        this.primaryStage.hide();
    }
}
