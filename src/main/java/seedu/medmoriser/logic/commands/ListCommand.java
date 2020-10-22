package seedu.medmoriser.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medmoriser.model.Model.PREDICATE_SHOW_ALL_QUESTIONSETS;

import seedu.medmoriser.model.Model;

/**
 * Lists all QAndAs in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all Questions (and Answers) "
            + "depending on the optional parameter passed.\n"
            + "Parameters: [questions]\n"
            + "Example: " + COMMAND_WORD + " questions";

    public static final String MESSAGE_LIST_ALL_SUCCESS = "Listed all QAndAs";

    public static final String MESSAGE_LIST_QUESTIONS_SUCCESS = "Listed all Questions";

    private final boolean isAnswerDisplayed;

    /**
     * Lists all QAndAs.
     * @param isAnswerDisplayed whether answers should be displayed
     */
    public ListCommand(boolean isAnswerDisplayed) {
        super();
        this.isAnswerDisplayed = isAnswerDisplayed;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredQAndAList(PREDICATE_SHOW_ALL_QUESTIONSETS);
        if (isAnswerDisplayed) {
            return new CommandResult(MESSAGE_LIST_ALL_SUCCESS, true);
        } else {
            return new CommandResult(MESSAGE_LIST_QUESTIONS_SUCCESS, false);
        }
    }
}
