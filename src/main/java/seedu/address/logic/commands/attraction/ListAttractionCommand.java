package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all attractions in the attractions list in TrackPad to the user.
 */
public class ListAttractionCommand extends Command {

    public static final String COMMAND_WORD = "list-attraction";

    public static final String MESSAGE_SUCCESS = "Listed all attractions.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
