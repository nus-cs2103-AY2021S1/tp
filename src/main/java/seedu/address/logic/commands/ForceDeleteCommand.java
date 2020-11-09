package seedu.address.logic.commands;

import seedu.address.model.module.ModuleCode;

/**
 * Force-deletes a Module identified using a Module's ModuleCode.
 */
public class ForceDeleteCommand extends DeleteCommand {

    /**
     * Creates a ForceDeleteCommand to force delete the module with the specified {@code ModuleCode}
     *
     * @param code the module code of the module to be deleted.
     */
    public ForceDeleteCommand(ModuleCode code) {
        super(code);
    }

    @Override
    public boolean requiresStall() {
        return false;
    }
}
