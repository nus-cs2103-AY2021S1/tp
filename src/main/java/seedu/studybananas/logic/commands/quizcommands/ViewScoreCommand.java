package seedu.studybananas.logic.commands.quizcommands;

import static seedu.studybananas.logic.commands.commandresults.QuizCommandResultType.VIEW_SCORE;

import seedu.studybananas.commons.core.index.Index;
import seedu.studybananas.logic.commands.Command;
import seedu.studybananas.logic.commands.commandresults.CommandResult;
import seedu.studybananas.logic.commands.commandresults.QuizCommandResult;
import seedu.studybananas.logic.commands.exceptions.CommandException;
import seedu.studybananas.model.FlashcardQuizModel;
import seedu.studybananas.model.flashcard.FlashcardSet;
import seedu.studybananas.model.flashcard.FlashcardSetName;

public class ViewScoreCommand extends Command<FlashcardQuizModel> {

    public static final String COMMAND_WORD = "quiz score flset:";

    public static final String MESSAGE_UNABLE_TO_VIEW =
            "Unable to view score as quiz is in progress. "
                    + "Cancel or finish the quiz to view recent quiz score.";
    public static final String MESSAGE_QUIZ_NONEXISTENT =
            "Quiz records for this flashcard set does not exist.";
    public static final String MESSAGE_FLASHCARD_DELETED =
            "Unable to view score as flashcard set has deleted flashcard(s) "
                    + "since the last quiz. "
                    + "Do start a new quiz to update the score.";
    private final int index;
    private final FlashcardSetName flashcardSetName;

    /**
     * Constructs a ViewScoreCommand based on the flashcard set index.
     * @param index as provided
     */
    public ViewScoreCommand(int index) {
        this.index = index;
        this.flashcardSetName = null;
    }

    /**
     * Constructs a ViewScoreCommand based on flashcard set name.
     * @param flashcardSetName as specified
     */
    public ViewScoreCommand(FlashcardSetName flashcardSetName) {
        this.index = 0;
        this.flashcardSetName = flashcardSetName;
    }

    @Override
    public CommandResult execute(FlashcardQuizModel model) throws CommandException {

        if (model.hasStarted()) {
            throw new CommandException(MESSAGE_UNABLE_TO_VIEW);
        }

        try {
            if (index == 0) { // case for getting flashcard set name directly

                FlashcardSet flashcardSet = model.getFlashcardSet(flashcardSetName);

                if (flashcardSet == null) {
                    throw new CommandException(MESSAGE_QUIZ_NONEXISTENT);
                }

                model.setQuizRecordsToView(flashcardSetName);

                String score = model.getQuizRecords(flashcardSetName);

                if (score == null) {
                    model.setQuizRecordsToView(null);
                    throw new CommandException(MESSAGE_FLASHCARD_DELETED);
                }

                QuizCommandUtil.updateCommandResult(score);
                return new QuizCommandResult(score, VIEW_SCORE);
            }

            FlashcardSetName name = model.getFlashcardSet(Index.fromOneBased(index)).getFlashcardSetName();

            model.setQuizRecordsToView(name);

            String score = model.getQuizRecords(name);

            if (score == null) {
                model.setQuizRecordsToView(null);
                throw new CommandException(MESSAGE_FLASHCARD_DELETED);
            }

            QuizCommandUtil.updateCommandResult(score);

            return new QuizCommandResult(score, VIEW_SCORE);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_QUIZ_NONEXISTENT);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewScoreCommand // instanceof handles nulls
                && index == (((ViewScoreCommand) other).index)); // state check
    }
}
