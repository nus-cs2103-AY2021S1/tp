package seedu.jarvis.logic.commands.add;

import static java.util.Objects.requireNonNull;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.masterycheck.MasteryCheck;

public class AddMasteryCheckCommand extends AddCommand {
    //@@author wireseo
    public static final String MESSAGE_DUPLICATE_MASTERY_CHECK = "This mastery check already exists in jarvis";
    public static final String MESSAGE_SUCCESS_MASTERY_CHECK = "New mastery check added: %1$s";

    private final MasteryCheck toAddMasteryCheck;

    /**
     * Creates an AddMasteryCheckCommand to add the specified {@code MasteryCheck}
     */
    public AddMasteryCheckCommand(MasteryCheck masteryCheck) {
        requireNonNull(masteryCheck);
        toAddMasteryCheck = masteryCheck;
    }

    public MasteryCheck getToAddMasteryCheck() {
        return toAddMasteryCheck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasMasteryCheck(toAddMasteryCheck)) {
            throw new CommandException(MESSAGE_DUPLICATE_MASTERY_CHECK);
        }

        model.addMasteryCheck(toAddMasteryCheck);
        return new CommandResult(String.format(MESSAGE_SUCCESS_MASTERY_CHECK, toAddMasteryCheck),
                CommandTargetFeature.MasteryCheck);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMasteryCheckCommand)) {
            return false;
        }

        // state check
        AddMasteryCheckCommand e = (AddMasteryCheckCommand) other;

        return toAddMasteryCheck.equals(e.getToAddMasteryCheck());
    }
}
