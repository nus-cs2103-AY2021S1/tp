package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all food";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
<<<<<<< Updated upstream:src/main/java/seedu/address/logic/commands/ListCommand.java
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
=======
        model.updateFilteredFoodList(Model.PREDICATE_SHOW_ALL_FOODS);
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/logic/commands/ListCommand.java
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
