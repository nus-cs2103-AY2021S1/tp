package quickcache.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quickcache.commons.core.GuiSettings;
import quickcache.commons.core.LogsCenter;
import quickcache.logic.Logic;
import quickcache.logic.commands.CommandResult;
import quickcache.logic.commands.Feedback;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.Question;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final HelpWindow helpWindow;
    // Independent Ui parts residing in this Ui container
    private FlashcardListPanel flashcardListPanel;
    private ResultDisplay resultDisplay;
    private OptionListPanel optionListPanel;
    private QuestionDisplay questionDisplay;

    private PieChartDisplay pieChartDisplay;

    private boolean isOnChangedWindow;

    @FXML
    private VBox mainPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane displayPlaceholder;

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
        this.isOnChangedWindow = false;

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
        listPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        displayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getQuickCacheFilePath());
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
     * Changes the content of the placeHolders of this window.
     *
     * @param feedbackToUser the feedback describing what to display to the user.
     */
    public void handleChangeWindow(Feedback feedbackToUser) {
        // Statistics is null if there is no statistics to display to the user
        // CommandResult constructor constraints guarantees this
        feedbackToUser.getStatistics().ifPresentOrElse((statistics ->
                changeInnerPartsToStatisticsWindow(feedbackToUser)), () ->
                changeInnerPartsToFlashcardWindow(feedbackToUser));
        this.isOnChangedWindow = true;
    }

    /**
     * Changes the content of the placeHolders of this window to display the statistics of a flashcard.
     *
     * @param feedbackToUser the feedback describing what to display to the user.
     */
    private void changeInnerPartsToStatisticsWindow(Feedback feedbackToUser) {

        displayPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().clear();

        displayPlaceholder.getChildren().add(resultDisplay.getRoot());

        pieChartDisplay = new PieChartDisplay();
        listPanelPlaceholder.getChildren().add(pieChartDisplay.getRoot());

        pieChartDisplay.displayStatistics(feedbackToUser);
    }

    /**
     * Changes the content of the placeHolders of this window to display an opened flashcard.
     *
     * @param feedbackToUser the feedback describing what to display to the user.
     */
    private void changeInnerPartsToFlashcardWindow(Feedback feedbackToUser) {

        listPanelPlaceholder.getChildren().clear();
        displayPlaceholder.getChildren().clear();

        Optional<Question> questionToDisplay = feedbackToUser.getQuestion();

        questionDisplay = new QuestionDisplay();
        displayPlaceholder.getChildren().add(questionDisplay.getRoot());
        // display the question
        questionDisplay.setQuestion(questionToDisplay.map(Question::getValue)
                .orElse("There is no question to display"));

        // initialize question's options into VBox
        optionListPanel = new OptionListPanel(questionToDisplay.map(question -> {
            if (question instanceof MultipleChoiceQuestion) {
                return FXCollections.observableList(Arrays.stream(question
                        .getChoices().orElseGet(() -> new Choice[0])).map(
                        Choice::toString).collect(Collectors.toCollection(ArrayList::new)));
            } else {
                return FXCollections.observableArrayList("Open Ended Question has no options");
            }
        }).get()); // get() will never throw an exception as null will never be returned
        listPanelPlaceholder.getChildren().add(optionListPanel.getRoot());

        // isCorrect() is not null only when test command is called
        feedbackToUser.isCorrect().ifPresent(isCorrect -> {
            questionDisplay.showOutcome(feedbackToUser.toString(), isCorrect);
        });
    }

    /**
     * Reverse changes to the content of the window's placeholders to the default content.
     */
    private void reverseWindowChange() {
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        displayPlaceholder.getChildren().clear();
        displayPlaceholder.getChildren().add(resultDisplay.getRoot());

        this.isOnChangedWindow = false;
    }

    public FlashcardListPanel getFlashcardListPanel() {
        return flashcardListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isChangeWindow()) {
                handleChangeWindow(commandResult.getFeedback());
            } else if (commandResult.isShowHelp()) {
                handleHelp();
            } else if (commandResult.isExit()) {
                handleExit();
            } else if (isOnChangedWindow) {
                reverseWindowChange();
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            if (isOnChangedWindow) {
                reverseWindowChange();
            }
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
