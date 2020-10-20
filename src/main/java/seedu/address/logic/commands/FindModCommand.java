package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INSTRUCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;

public class FindModCommand extends Command {

    public static final String COMMAND_WORD = "findmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the module whose code, name and instructors' "
            + "names contain the specified keywords (case-insensitive) and displays them as a list. "
            + "Command must contain at least one parameter and each parameter can have multiple keywords, "
            + "except for module code. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_MODULE_NAME + "MODULE NAME ... "
            + PREFIX_MODULE_INSTRUCTOR + "INSTRUCTOR's NAME ... \n"
            + "Examples: "
            + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2103 CS2100, "
            + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2 " + PREFIX_MODULE_INSTRUCTOR + "John, "
            + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS1 "
            + PREFIX_MODULE_NAME + "programming " + PREFIX_MODULE_INSTRUCTOR + "Martin";
    private final ModuleCodeContainsKeywordsPredicate codePredicate;
    private final ModuleNameContainsKeywordsPredicate namePredicate;
    private final ModuleInstructorsContainsKeywordsPredicate instructorPredicate;

    /**
     * Constructor that creates a FindMod Command.
     */
    public FindModCommand(ModuleCodeContainsKeywordsPredicate codePredicate,
                          ModuleNameContainsKeywordsPredicate namePredicate,
                          ModuleInstructorsContainsKeywordsPredicate instructorPredicate) {
        this.codePredicate = codePredicate;
        this.namePredicate = namePredicate;
        this.instructorPredicate = instructorPredicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Module> composedPredicate = getComposedPredicate(
                this.codePredicate, this.namePredicate, this.instructorPredicate);
        model.updateFilteredModuleList(composedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED, model.getFilteredModuleList().size()));
    }

    private static Predicate<Module> getComposedPredicate(Predicate<Module> ...predicates) {
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
                && codePredicate.equals(((FindModCommand) other).codePredicate)
                && namePredicate.equals(((FindModCommand) other).namePredicate)
                && instructorPredicate.equals(((FindModCommand) other).instructorPredicate)); // state check
    }
}
