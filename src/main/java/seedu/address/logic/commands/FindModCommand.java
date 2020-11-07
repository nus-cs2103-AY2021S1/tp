package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INSTRUCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class FindModCommand extends Command {

    public static final String COMMAND_WORD = "findmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the module whose code, name and instructors' "
            + "names contain the specified keywords (case-insensitive) and displays them as a list.\n"
            + "At least one of the fields must be provided and each field can have multiple keywords, "
            + "except for module code.\n"
            + "Parameters: "
            + "[" + PREFIX_MODULE_CODE + "MODULE_CODE] "
            + "[" + PREFIX_MODULE_NAME + "MODULE_NAME] "
            + "[" + PREFIX_MODULE_INSTRUCTOR + "INSTRUCTOR_NAME]\n"
            + "Example: "
            + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS1 "
            + PREFIX_MODULE_NAME + "programming methodology " + PREFIX_MODULE_INSTRUCTOR + "Martin";

    private final List<Predicate<Module>> predicates;

    /**
     * Constructor that creates a FindMod Command.
     */
    public FindModCommand(List<Predicate<Module>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Module> composedPredicate = getComposedPredicate(this.predicates);
        model.updateFilteredModuleList(composedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED, model.getFilteredModuleList().size()));
    }

    private static Predicate<Module> getComposedPredicate(List<Predicate<Module>> predicates) {
        assert(predicates != null);
        Predicate<Module> composedPredicate = module -> true;
        for (Predicate<Module> predicate : predicates) {
            if (predicate == null) {
                continue;
            }
            composedPredicate = composedPredicate.and(predicate);
        }
        return composedPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModCommand // instanceof handles nulls
                && predicates.equals(((FindModCommand) other).predicates)); // state check
    }
}
