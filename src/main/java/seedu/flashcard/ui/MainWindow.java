package seedu.flashcard.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.commons.core.LogsCenter;
import seedu.flashcard.logic.Logic;
import seedu.flashcard.logic.ReviewManager;
import seedu.flashcard.logic.commands.CommandResult;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private ReviewManager reviewManager;
    private EventHandler<KeyEvent> keyDownEventHandler;

    // Independent Ui parts residing in this Ui container
    private FlashcardListPanel flashcardListPanel;
    private FlashcardAnswerCard flashcardAnswerCard;
    private FlashcardQuestionCard flashcardQuestionCard;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane flashcardListPanelPlaceholder;

    @FXML
    private StackPane questionPlaceholder;

    @FXML
    private StackPane answerPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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
     *
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
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());

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
     * Cleans up window back to command mode.
     */
    @FXML
    private void exitReviewMode(String exitReason) {
        getRoot().removeEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
        flashcardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        questionPlaceholder.getChildren().clear();
        answerPlaceholder.getChildren().clear();
        questionPlaceholder.setPrefHeight(0);
        answerPlaceholder.setPrefHeight(0);
        resultDisplay.setFeedbackToUser(exitReason + ReviewManager.EXIT_MESSAGE);
    }

    /**
     * Sets up window for review mode.
     */
    @FXML
    private void enterReviewMode() {
        reviewManager = new ReviewManager(logic.getFilteredFlashcardList());
        flashcardListPanelPlaceholder.getChildren().clear();
        commandBoxPlaceholder.getChildren().clear();
        questionPlaceholder.setPrefHeight(100);
        answerPlaceholder.setPrefHeight(100);
        showReviewFlashcard(reviewManager.getCurrentFlashcard(), 1);
    }

    /**
     * Makes window show the current flashcard being reviewed.
     * @param flashcard the FlashCard being reviewed.
     * @param displayedIndex the displayed index of the Flashcard being reviewed.
     */
    @FXML
    private void showReviewFlashcard(Flashcard flashcard, int displayedIndex) {
        questionPlaceholder.getChildren().clear();
        flashcardQuestionCard = new FlashcardQuestionCard(flashcard, displayedIndex);
        questionPlaceholder.getChildren().add(flashcardQuestionCard.getRoot());
        answerPlaceholder.getChildren().clear();
    }

    /**
     * Executes review function.
     */
    @FXML
    private void handleReview() {
        enterReviewMode();
        keyDownEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(event.getTarget() instanceof TextInputControl)) {
                    return;
                }
                switch (event.getCode().getCode()) {
                case 39: // right arrow key down
                    if (!reviewManager.hasNextFlashcard()) {
                        exitReviewMode(ReviewManager.NO_NEXT_FLASHCARD_MESSAGE + "\n");
                    } else {
                        showReviewFlashcard(reviewManager.getNextFlashcard(),
                                reviewManager.getCurrentIndex() + 1);
                    }
                    break;
                case 37: // left arrow key down
                    if (!reviewManager.hasPreviousFlashcard()) {
                        exitReviewMode(ReviewManager.NO_PREVIOUS_FLASHCARD_MESSAGE + "\n");
                    } else {
                        showReviewFlashcard(reviewManager.getPrevFlashcard(),
                                reviewManager.getCurrentIndex() + 1);
                    }
                    break;
                case 40: // up arrow key down
                    flashcardAnswerCard = new FlashcardAnswerCard(reviewManager.getCurrentFlashcard());
                    answerPlaceholder.getChildren().add(flashcardAnswerCard.getRoot());
                    break;
                case 38: // down arrow key down
                    answerPlaceholder.getChildren().clear();
                    break;
                case 81: // 'q' key down
                    exitReviewMode("");
                    break;
                default:
                    break;
                }
                event.consume();
            }
        };
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    }

    public FlashcardListPanel getFlashcardListPanel() {
        return flashcardListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.flashcard.logic.Logic#execute(String)
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

            if (commandResult.isReviewMode()) {
                handleReview();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
