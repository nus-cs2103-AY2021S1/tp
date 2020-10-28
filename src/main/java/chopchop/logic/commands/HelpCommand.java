package chopchop.logic.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Function;

import chopchop.commons.util.Strings;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    private static final String METHOD_NAME_GET_CMD = "getCommandString";
    private static final String METHOD_NAME_GET_HELP = "getCommandHelp";
    private static final String METHOD_NAME_GET_UG_LINK = "getUserGuideSection";

    private final Optional<String> helpCommand;
    private final Optional<String> helpTarget;

    /**
     * Constructs a new HelpCommand for the given command and target.
     */
    public HelpCommand(Optional<String> command, Optional<String> target) {
        this.helpCommand = command;
        this.helpTarget = target;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {

        if (helpCommand.isEmpty() || helpCommand.get().isEmpty()) {
            return CommandResult.message("Refer to the ")
                .appendingLink("User Guide", Strings.USER_GUIDE_BASE_URL, /* newline: */ false)
                .appending(" for more detailed help", /* newline: */ false);
        }

        var cmd = helpCommand.get();

        var cls = getCommandClassFor(cmd, this.helpTarget.orElse(""));
        if (cls == null) {
            return CommandResult.error("Unknown command '%s'; see the User Guide for a list of commands: %s",
                cmd, Strings.USER_GUIDE_BASE_URL);
        }

        var cmdStr = invokeMethod(cls, METHOD_NAME_GET_CMD);
        var cmdHelp = invokeMethod(cls, METHOD_NAME_GET_HELP);
        var cmdUgLink = invokeMethod(cls, METHOD_NAME_GET_UG_LINK);

        if (cmdStr == null || cmdHelp == null) {
            return CommandResult.error("No help available for command '%s'", cmd);
        }

        var ret = CommandResult.message("%s: %s", cmdStr, cmdHelp);
        if (cmdUgLink != null) {
            ret = ret
                .appending("see the", /* newline: */ true)
                .appendingLink("User Guide", Strings.USER_GUIDE_BASE_URL + "#" + cmdUgLink,
                    /* newline: */ false);
        }

        return ret;
    }





    private Class<?> getCommandClassFor(String cmd, String target) {

        var camelCasing = (Function<String, String>) s -> {
            return s.isEmpty()
                ? ""
                : (s.substring(0, 1).toUpperCase() + s.substring(1));
        };

        var pkg = "chopchop.logic.commands.";

        if (target.equals("recipes")) {
            target = "recipe";
        } else if (target.equals("ingredients")) {
            target = "ingredient";
        }


        for (var cmdName : Strings.COMMAND_NAMES) {

            if (!cmdName.equals(cmd)) {
                continue;
            }

            var className = pkg
                + camelCasing.apply(cmdName)
                + camelCasing.apply(target)
                + "Command";

            System.out.printf("searching for class '%s'\n", className);
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                try {

                    // try to find the dummy class.
                    var dummyPkg = "chopchop.logic.commands.HelpCommand$";
                    className = dummyPkg
                        + camelCasing.apply(cmdName)
                        + "CommandDummy";

                    System.out.printf("searching for dummy class '%s'\n", className);
                    return Class.forName(className);

                } catch (ClassNotFoundException e1) {
                    break;
                }
            }
        }

        return null;
    }

    private String invokeMethod(Class<?> cls, String methodName) {

        try {
            var method = cls.getMethod(methodName);
            var ret = method.invoke(null);

            if (ret instanceof String) {
                return (String) ret;
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // do nothing, but exit the branch and return the error.
            System.err.printf("Command class '%s' had no method '%s': %s\n", cls, methodName, e);
        }

        return null;
    }



    @Override
    public String toString() {
        return String.format("HelpCommand");
    }

    public static String getCommandString() {
        return "help";
    }

    public static String getCommandHelp() {
        return "Shows a link to the user guide for ChopChop, and offers help for individual commands";
    }

    public static String getUserGuideSection() {
        return "viewing-help--help";
    }




    // internal fake classes for commands requiring targets.
    private static class AddCommandDummy {
        public static String getCommandString() {
            return "add";
        }
        public static String getCommandHelp() {
            return "Adds an item; see 'add recipe' or 'add ingredient'";
        }
    }

    private static class DeleteCommandDummy {
        public static String getCommandString() {
            return "delete";
        }
        public static String getCommandHelp() {
            return "Deletes an item; see 'delete recipe' or 'delete ingredient'";
        }
    }

    private static class ListCommandDummy {
        public static String getCommandString() {
            return "list";
        }
        public static String getCommandHelp() {
            return "Lists items; see 'list recipes' or 'list ingredients'";
        }
    }

    private static class FindCommandDummy {
        public static String getCommandString() {
            return "find";
        }
        public static String getCommandHelp() {
            return "Finds items; see 'find recipes' or 'find ingredients'";
        }
    }

    private static class FilterCommandDummy {
        public static String getCommandString() {
            return "filter";
        }
        public static String getCommandHelp() {
            return "Filters items; see 'filter recipes' or 'filter ingredients'";
        }
    }

    private static class EditCommandDummy {
        public static String getCommandString() {
            return "edit";
        }
        public static String getCommandHelp() {
            return "Edits an item; see 'edit recipe' or 'edit ingredient'";
        }
    }
}
