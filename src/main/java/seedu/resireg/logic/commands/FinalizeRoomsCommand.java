package seedu.resireg.logic.commands;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

public class FinalizeRoomsCommand extends Command {

    public static final String COMMAND_WORD = "finalize";
    public static final String MESSAGE_SUCESS = "Finalized rooms. You can now no longer add or delete rooms. (You may undo if it was a mistake though)";

    public static final Help HELP = new Help(COMMAND_WORD, "Finalizes rooms.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        model.finalizeRooms();
        model.saveStateResiReg();
        return new CommandResult(MESSAGE_SUCESS);
    }
}
