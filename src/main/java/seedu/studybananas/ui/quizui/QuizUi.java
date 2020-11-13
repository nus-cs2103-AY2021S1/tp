package seedu.studybananas.ui.quizui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import seedu.studybananas.commons.core.LogsCenter;
import seedu.studybananas.logic.Logic;
import seedu.studybananas.logic.commands.commandresults.CommandResult;
import seedu.studybananas.logic.commands.commandresults.GeneralCommandResult;
import seedu.studybananas.logic.commands.commandresults.QuizCommandResult;
import seedu.studybananas.logic.commands.exceptions.CommandException;
import seedu.studybananas.logic.commands.quizcommands.RefreshCommand;
import seedu.studybananas.logic.parser.exceptions.ParseException;
import seedu.studybananas.model.quiz.Quiz;
import seedu.studybananas.ui.UiPart;
import seedu.studybananas.ui.commons.CommandBox;
import seedu.studybananas.ui.commons.PositiveResponse;
import seedu.studybananas.ui.commons.ResponsePopUp;
import seedu.studybananas.ui.commons.WarningResponse;
import seedu.studybananas.ui.flashcardui.FlashcardSetListPanel;
import seedu.studybananas.ui.listeners.CommandResultStateListener;
import seedu.studybananas.ui.listeners.UiStateListener;
import seedu.studybananas.ui.util.GlobalState;
import seedu.studybananas.ui.util.UiStateType;
import seedu.studybananas.ui.util.UiUtil;

public class QuizUi extends UiPart<Region> {

    private static final String FXML = "QuizUi.fxml";
    private final Callback<CommandResult, Void> actionOnCommandResultChange = new Callback<CommandResult, Void>() {
        @Override
        public Void call(CommandResult state) {
            if (shouldRender(state)) {
                QuizCommandResult quizCommandResult = (QuizCommandResult) state;
                updateUi(quizCommandResult);
            }
            return null;
        }
    };

    // empty callback
    private final Callback<UiStateType, Void> actionOnUiStateChange = (uiStateType) -> {
        return null;
    };

    private final Logic logic;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private FlashcardSetListPanel flashcardSetListPanel;
    private Statistics statistics;
    private QuizCard resultDisplay;
    private QuizScoreCard quizScoreDisplay;
    private UiStateListener uiStateListener;
    private CommandResultStateListener commandResultStateListener;
    private ResponsePopUp responsePopUp;

    @FXML
    private StackPane flashcardSetListPanelPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane statisticsPlaceholder;

    @FXML
    private StackPane quizCard;

    /**
     * Constructs a QuizUi object.
     */
    public QuizUi() {
        super(FXML);
        this.logic = GlobalState.getInstance().getLogic();
        this.responsePopUp = GlobalState.getInstance().getResponsePopUp();

        flashcardSetListPanel = new FlashcardSetListPanel();
        flashcardSetListPanelPlaceholder.getChildren().add(flashcardSetListPanel.getRoot());

        resultDisplay = new QuizCard();
        quizCard.getChildren().add(resultDisplay.getRoot());

        quizScoreDisplay = new QuizScoreCard(); // initiate quiz score display first

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        GlobalState globalState = GlobalState.getInstance();
        globalState.setQuizCommandBox(commandBox);

        // set up listeners
        uiStateListener = new UiStateListener();
        commandResultStateListener = new CommandResultStateListener();
        uiStateListener.onChange(actionOnUiStateChange);
        commandResultStateListener.onChange(actionOnCommandResultChange);
    }

    public FlashcardSetListPanel getFlashcardSetListPanel() {
        return flashcardSetListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.studybananas.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            if (UiUtil.isGeneralCommand(commandResult)) {
                UiUtil.handleGeneralCommand((GeneralCommandResult) commandResult);
                return commandResult;
            }

            uiStateListener.updateState(commandResult.getCommandResultType());
            commandResultStateListener.updateState(commandResult);
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            quizScoreDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private boolean shouldRender(CommandResult commandResult) {
        return commandResult instanceof QuizCommandResult;
    }

    private void updateUi(QuizCommandResult commandResult) {
        String feedback = commandResult.getFeedbackToUser();
        logger.info("Result: " + feedback);

        // handles the response of "refresh command" and "error
        switch (commandResult.getCommandType()) {
        case REFRESH:
            responsePopUp.setContent(new PositiveResponse(RefreshCommand.MESSAGE_SUCCESS));
            responsePopUp.open();
            break;
        case ERROR:
            responsePopUp.setContent(new WarningResponse(feedback));
            responsePopUp.open();
            break;
        default:
            break;
        }


        // special case for view quiz score
        Quiz quiz = logic.getQuizRecordsToView();
        if (quiz != null) {

            // replace quiz card with quiz score card
            quizScoreDisplay = new QuizScoreCard();
            quizCard.getChildren().removeAll(quizCard.getChildren());
            quizCard.getChildren().add(quizScoreDisplay.getRoot());

            // show score
            quizScoreDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            // show stacked bar chart
            statistics = new Statistics(quiz);
            statisticsPlaceholder.getChildren().removeAll(statisticsPlaceholder.getChildren());
            statisticsPlaceholder.getChildren().add(statistics.getStackedBarChart());
            return;
        }

        // re-initialise the quiz card
        quizCard.getChildren().removeAll(quizCard.getChildren());
        quizCard.getChildren().add(resultDisplay.getRoot());

        // show quiz
        resultDisplay.setQuiz(commandResult.getQuiz());
        resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

        // remove statistics
        statisticsPlaceholder.getChildren().removeAll(statisticsPlaceholder.getChildren());
    }

}
