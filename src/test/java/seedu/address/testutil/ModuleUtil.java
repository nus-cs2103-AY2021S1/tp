package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INSTRUCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.AddModCommand;
import seedu.address.logic.commands.DelModCommand;
import seedu.address.logic.commands.FindModCommand;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

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
     * Returns an delete module command string for the deleting of the {@code module}.
     */
    public static String getDelModCommand(Module module) {
        return DelModCommand.COMMAND_WORD + " " + getModuleCode(module);
    }

    /**
     * Returns a findmod command string for the search of the {@code module}.
     */
    public static String getFindModCommand(Module module) {
        return FindModCommand.COMMAND_WORD + " " + getModuleDetails(module) + getModuleInstructor(module);
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

    /**
     * Returns the part of the command string for the given {@code module}'s code.
     */
    public static String getModuleCode(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_CODE + module.getModuleCode().moduleCode + " ");
        return sb.toString();
    }

    /**
     * Returns the part of the command string for the given {@code module}'s instructor.
     */
    public static String getModuleInstructor(Module module) {
        StringBuilder sb = new StringBuilder();
        List<Person> instructors = new ArrayList<>(module.getInstructors());
        assert(instructors.size() > 0);
        sb.append(PREFIX_MODULE_INSTRUCTOR + instructors.get(0).getName().toString());
        return sb.toString();
    }
}
