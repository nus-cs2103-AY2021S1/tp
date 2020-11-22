package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.forgetfulnus.model.Model;

/**
 * Lists all flashcards in the glossary to the user, shown in the default unsorted state.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards.";

    public static final String MESSAGE_EMPTY_GLOSSARY = "The glossary is empty!";

    public static final String QUIZ_MODE_REMINDER = "'list' command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getGlossary().getFlashCardList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY_GLOSSARY);
        }
        model.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
