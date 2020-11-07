package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INSTRUCTOR_ALREADY_ASSIGNED;
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
import seedu.address.model.person.Person;

/**
 * Assigns an instructor to one or more modules.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns an instructor to one or more modules.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + "[" + PREFIX_MODULE_CODE + "MODULE_CODE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE_CODE + "CS2103";

    public static final String MESSAGE_ADD_ASSIGNMENT_SUCCESS = "Assigned %1$s to %2$s";

    private final Index index;
    private final Set<ModuleCode> moduleCodes;

    /**
     * @param index of the person in the filtered person list to be assigned
     * @param moduleCodes of modules the person would be assigned to
     */
    public AssignCommand(Index index, Set<ModuleCode> moduleCodes) {
        requireAllNonNull(index, moduleCodes);

        this.index = index;
        this.moduleCodes = moduleCodes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        assert lastShownList != null;

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person instructor = lastShownList.get(index.getZeroBased());
        StringBuilder moduleString = new StringBuilder();

        assert instructor != null;

        for (ModuleCode moduleCode: moduleCodes) {
            if (!model.hasModuleCode(moduleCode)) {
                throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST,
                        moduleCode));
            }
            if (model.moduleCodeHasInstructor(moduleCode, instructor)) {
                throw new CommandException(String.format(MESSAGE_INSTRUCTOR_ALREADY_ASSIGNED,
                        instructor.getName(), moduleCode));
            }
        }

        for (ModuleCode moduleCode: moduleCodes) {
            model.assignInstructor(instructor, moduleCode);
            moduleString.append(moduleCode).append(", ");
        }

        assert moduleString.length() > 2;

        moduleString.delete(moduleString.length() - 2, moduleString.length());

        return new CommandResult(String.format(MESSAGE_ADD_ASSIGNMENT_SUCCESS,
                instructor.getName(), moduleString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && index.equals(((AssignCommand) other).index)
                && moduleCodes.equals(((AssignCommand) other).moduleCodes));
    }
}
