package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

<<<<<<< Updated upstream:src/main/java/seedu/address/logic/commands/ClearCommand.java
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
=======
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.Model;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/logic/commands/ClearCommand.java

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMcGymmy(new McGymmy());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
