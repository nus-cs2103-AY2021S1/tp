package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INSTRUCTOR_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;

public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns an instructor from one or more modules.\n"
            + "If module codes are provided, it will unassign the instructor "
            + "from all module codes provided.\n"
            + "Otherwise, it will unassign the instructor from all modules he/she teaches.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + "[MODULE CODE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE_CODE + "CS2103"
            + " or " + COMMAND_WORD + " 1 m/";

    public static final String MESSAGE_UNASSIGNMENT_SUCCESS = "Unassigned %1$s from %2$s";

    private final Index index;
    private final Set<ModuleCode> moduleCodes;

    /**
     * @param index of the person in the filtered person list to be unassigned
     * @param moduleCodes of modules the person would be unassigned from
     */
    public UnassignCommand(Index index, Set<ModuleCode> moduleCodes) {
        requireAllNonNull(index, moduleCodes);

        this.index = index;
        this.moduleCodes = moduleCodes;
    }

    /**
     * @param index of the person in the filtered person list to be unassigned
     */
    public UnassignCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
        this.moduleCodes = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person instructor = lastShownList.get(index.getZeroBased());
        StringBuilder moduleString = new StringBuilder();

        if (moduleCodes.isEmpty()) {
            model.unassignInstructorFromAll(instructor);
            moduleString.append("all modules");
        } else {
            for (ModuleCode moduleCode : moduleCodes) {
                if (!model.hasModuleCode(moduleCode)) {
                    throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
                }
            }

            for (ModuleCode moduleCode : moduleCodes) {
                if (!model.moduleCodeHasInstructor(moduleCode, instructor)) {
                    throw new CommandException(String.format(MESSAGE_INSTRUCTOR_DOES_NOT_EXIST,
                            instructor.getName(), moduleCode));
                }
            }

            for (ModuleCode moduleCode : moduleCodes) {
                model.unassignInstructor(instructor, moduleCode);
                moduleString.append(moduleCode).append(", ");
            }

            assert moduleString.length() > 2;

            moduleString.delete(moduleString.length() - 2, moduleString.length());
        }

        return new CommandResult(String.format(MESSAGE_UNASSIGNMENT_SUCCESS,
                instructor.getName(), moduleString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && index.equals(((UnassignCommand) other).index)
                && moduleCodes.equals(((UnassignCommand) other).moduleCodes));
    }

}
