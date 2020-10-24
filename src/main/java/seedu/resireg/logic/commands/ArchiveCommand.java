package seedu.resireg.logic.commands;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.util.logging.Logger;

import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;
import seedu.resireg.storage.Storage;

public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_SUCCESS = "The current semester has been archived!";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Archives the current semester data into a file, and starts the new semester.");

    private final Logger logger = LogsCenter.getLogger(ArchiveCommand.class);

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireAllNonNull(model, storage);
        // Create the new semester
        ReadOnlyResiReg resiReg = model.getResiReg();
        ResiReg newResiReg = ResiReg.getNextSemesterResiReg(resiReg);

        // Create the new semester. Note that this step has to be done before updating the model,
        // as it will affect the local copy of ReadOnlyResiReg we have created.
        try {
            storage.archiveResiReg(resiReg);
        } catch (IOException e) {
            logger.severe("------------- Unable to archive the semester  -------------");
            throw new CommandException("Unable to archive the semester");
        }

        // Update the model, after keeping an archive of the previous semester.
        model.setResiReg(newResiReg);
        model.saveStateResiReg();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
