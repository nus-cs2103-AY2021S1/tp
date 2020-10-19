package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import seedu.address.logic.commands.AddModCommand;
import seedu.address.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add module command string for adding the {@code module}.
     */
    public static String getAddModCommand(Module module) {
        return AddModCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_CODE + module.getModuleCode().moduleCode + " ");
        sb.append(PREFIX_MODULE_NAME + module.getModuleName().moduleName + " ");
        return sb.toString();
    }

}
