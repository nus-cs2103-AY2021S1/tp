package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALGROUPS;

import static java.util.Objects.requireNonNull;

public class ListTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "listTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all the modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS = "Viewing All Tutorial Groups";

    public ListTutorialGroupCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTutorialGroupList(PREDICATE_SHOW_ALL_TUTORIALGROUPS);
        return new CommandResult(String.format(MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS), false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTutorialGroupCommand); // instanceof handles nulls
    }
}
