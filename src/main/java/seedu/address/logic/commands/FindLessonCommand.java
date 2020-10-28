package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.lesson.LessonContainsKeywordsPredicate;


public class FindLessonCommand extends Command {

    public static final String COMMAND_WORD = "find-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all lessons with the specified attribute"
            + "containing the specified valid search phrase (case-insensitive)"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...\n"
            + "For list of all available attribute, please refer to the user guide by typing 'help' command\n"
            + "Example: " + COMMAND_WORD + " title:meet zijian day:Mon";

    private final LessonContainsKeywordsPredicate predicate;

    public FindLessonCommand(LessonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLessonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLessonCommand // instanceof handles nulls
                && predicate.equals(((FindLessonCommand) other).predicate)); // state check
    }
}
