package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MOD_NAME + module.getModuleName().fullModName + " ");
        sb.append(PREFIX_GRADE + module.getGrade().toString() + " ");
        sb.append("" + PREFIX_MODULAR_CREDIT + module.getModularCredit().modularCredit);
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code UpdateModuleDescriptor}'s details.
     */
    public static String getUpdateModuleDescriptorDetails(UpdateCommand.UpdateModNameDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_MOD_NAME).append(name.fullModName).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.toString()).append(" "));
        return sb.toString();
    }
}
