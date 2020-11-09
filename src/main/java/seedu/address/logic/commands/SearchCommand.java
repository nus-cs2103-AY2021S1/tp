package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_SEARCH_SUCCESS;

import java.util.Optional;

import seedu.address.logic.ModuleInfoSearcher;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.nusmods.ModuleInfo;

/**
 * Search for a module to display its module details.
 * Module Code matching is case insensitive.
 */
public class SearchCommand extends Command {
    private final ModuleCode moduleCode;

    /**
     * Creates a SearchCommand to search for a module from the Computer Science
     * curriculum {@code Module}.
     *
     * @param moduleCode the module code of the module to be searched.
     */
    public SearchCommand(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ModuleInfoSearcher moduleInfoSearcher = new ModuleInfoSearcher();
        ModuleInfo searchResult = moduleInfoSearcher.searchModule(moduleCode.moduleCode);

        // preclusions and prerequisites could be null
        String preclusion = Optional.ofNullable(searchResult.getPreclusion()).orElse("None");
        String prerequisite = Optional.ofNullable(searchResult.getPrerequisite()).orElse("None");

        String searchDisplay = String.format(MESSAGE_SEARCH_SUCCESS, searchResult.getModuleCode(),
                searchResult.getModuleCredit(), searchResult.getTitle(),
                searchResult.getDescription(), preclusion, prerequisite, searchResult.getSemesters());
        return new CommandResult(searchDisplay);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && moduleCode.equals(((SearchCommand) other).moduleCode)); // state check
    }
}
