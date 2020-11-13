package seedu.bagel.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.logic.Logic;
import seedu.bagel.logic.commands.CommandResult;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.FlashcardSet;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private HostServices hostServices;

    // Independent Ui parts residing in this Ui container
    private FlashcardListPanel flashcardListPanel;
    private ResultDisplay resultDisplay;
    private SideBar sideBar;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private ScrollPane sidebarPlaceholder;

    @FXML
    private StackPane flashcardListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList(), hostServices);
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getBagelFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        this.sideBar = new SideBar();
        sidebarPlaceholder.setContent(sideBar.getRoot());
        loadSetButtons();
    }

    /**
     * Adds set buttons for existing sets.
     */
    void loadSetButtons() {
        handleAddSet("All");
        if (logic.hasSet()) {
            for (FlashcardSet set : logic.getSets()) {
                handleAddSet(set.value);
            }
        }
    }

    /**
     * Adds a new set button.
     */
    @FXML
    private void handleAddSet(String setValue) {
        Button setButton = new Button(setValue);
        setUpSetButton(setButton);
    }

    /**
     * Deletes all existing set buttons.
     */
    @FXML
    private void handleClear() {
        this.sideBar.clearAllButtons();
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

    public FlashcardListPanel getFlashcardListPanel() {
        return flashcardListPanel;
    }

    public void setUpButton(Button button, String imgUrl, EventHandler<ActionEvent> event) {
        button.setMnemonicParsing(false);
        button.setMinWidth(80);
        button.setId(button.getText());
        button.getStyleClass().add("sidebar-button");

        Image image = new Image(imgUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        button.setGraphic(imageView);
        VBox.setMargin(sidebarPlaceholder, new Insets(10));
        button.setOnAction(event);
        sideBar.addButton(button);

    }

    /**
     * Sets up the newly created set button.
     */
    public void setUpSetButton(Button setButton) {
        String setImgUrl = "images/set.png";
        EventHandler<ActionEvent> setEvent = event -> {
            String commandText;
            if (setButton.getText().equals("All")) {
                commandText = "list";
            } else {
                commandText = "list s/" + setButton.getText();
            }
            try {
                executeCommand(commandText);
            } catch (CommandException | ParseException e) {
                e.printStackTrace();
            }
        };
        setUpButton(setButton, setImgUrl, setEvent);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.bagel.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            String setValue;
            commandText = commandText.replaceAll("\\s+", " ");
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isChangeSet()) {
                handleClear();
                loadSetButtons();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
