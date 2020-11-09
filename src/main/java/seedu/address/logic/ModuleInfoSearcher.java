package seedu.address.logic;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.nusmods.ModuleInfo;
import seedu.address.nusmods.NusmodsDataManager;
import seedu.address.nusmods.exceptions.NusmodsException;

/**
 * Logic for searching a module from the Computer Science curriculum.
 */
public class ModuleInfoSearcher {
    private NusmodsDataManager dataManager;

    public ModuleInfoSearcher() {
        dataManager = new NusmodsDataManager();
    }

    ModuleInfoSearcher(NusmodsDataManager dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * Perform search function on NUSMods using dataManager.
     *
     * @param moduleCode from SearchCommandParser.
     * @return ModuleInfo of the module searched.
     * @throws CommandException if an error occurs during the search process.
     */
    public ModuleInfo searchModule(String moduleCode) throws CommandException {
        ModuleInfo moduleInfo;
        if (moduleCode.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_SEARCH);
        }
        try {
            Optional<ModuleInfo> moduleDetails = dataManager.getModuleInfo(moduleCode);
            if (moduleDetails.isEmpty()) {
                throw new CommandException(String.format(Messages.MESSAGE_FAILED_TO_FIND_MODULE, moduleCode));
            }
            moduleInfo = moduleDetails.get();
        } catch (NusmodsException e) {
            throw new CommandException(e.getMessage());
        }
        return moduleInfo;
    }
}
