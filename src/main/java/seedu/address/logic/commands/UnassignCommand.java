package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;

public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns an instructor from one or more modules. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + "MODULE CODE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE_CODE + "CS2103";

    public static final String MESSAGE_ADD_ASSIGNMENT_SUCCESS = "Unassigned instructor from module(s)";

    private final Index index;
    private final Set<ModuleCode> moduleCodes;

    /**
     * @param index of the person in the filtered person list to be assigned
     * @param moduleCodes of modules the person would be unassigned from
     */
    public UnassignCommand(Index index, Set<ModuleCode> moduleCodes) {
        requireAllNonNull(index, moduleCodes);

        this.index = index;
        this.moduleCodes = moduleCodes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        UniqueModuleList moduleList = model.getModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person instructor = lastShownList.get(index.getZeroBased());

        for (ModuleCode moduleCode: moduleCodes) {
            if (!moduleList.containsModuleCode(moduleCode)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
        }

        for (ModuleCode moduleCode: moduleCodes) {
            model.unassignInstructor(instructor, moduleCode);
        }

        return new CommandResult(MESSAGE_ADD_ASSIGNMENT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && index.equals(((UnassignCommand) other).index)
                && moduleCodes.equals(((UnassignCommand) other).moduleCodes));
    }
}
