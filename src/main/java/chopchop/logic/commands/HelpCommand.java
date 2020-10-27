package chopchop.logic.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    private static final String USER_GUIDE_BASE_URL = "https://ay2021s1-cs2103t-t10-3.github.io/tp/UserGuide.html";

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
            return CommandResult.help();
        }

        var cmd = helpCommand.get();

        var cls = getCommandClassFor(cmd, this.helpTarget.orElse(""));
        if (cls == null) {
            return CommandResult.error("Unknown command '%s'; see the User Guide for a list of commands: %s",
                cmd, USER_GUIDE_BASE_URL);
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
                .appendingLink("User Guide", USER_GUIDE_BASE_URL + "#" + cmdUgLink, /* newline: */ false);
        }

        return ret;
    }





    private Class<?> getCommandClassFor(String cmd, String target) {

        // oof.
        switch (cmd) {
        case "add":
            if (target.startsWith("recipe")) {
                return AddRecipeCommand.class;
            } else if (target.startsWith("ingredient")) {
                return AddIngredientCommand.class;
            } else {
                return AddCommandDummy.class;
            }

        case "list":
            if (target.startsWith("recipe") || target.startsWith("recipes")) {
                return ListRecipeCommand.class;
            } else if (target.startsWith("ingredient") || target.startsWith("ingredients")) {
                return ListIngredientCommand.class;
            } else {
                return ListCommandDummy.class;
            }

        case "edit":
            if (target.startsWith("recipe")) {
                return EditRecipeCommand.class;
            } else {
                return EditCommandDummy.class;
            }

        case "find":
            if (target.startsWith("recipe")) {
                return FindRecipeCommand.class;
            } else if (target.startsWith("ingredient")) {
                return FindIngredientCommand.class;
            } else {
                return FindCommandDummy.class;
            }

        case "filter":
            if (target.startsWith("recipe")) {
                return FilterRecipeCommand.class;
            } else if (target.startsWith("ingredient")) {
                return FilterIngredientCommand.class;
            } else {
                return FilterCommandDummy.class;
            }

        case "delete":
            if (target.startsWith("recipe")) {
                return DeleteRecipeCommand.class;
            } else if (target.startsWith("ingredient")) {
                return DeleteIngredientCommand.class;
            } else {
                return DeleteCommandDummy.class;
            }

        default:
            return null;
        }
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
        return "Shows the help dialog, containing a link to the User Guide for ChopChop";
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
            return "Edits items; see 'edit recipe' or 'edit ingredient'";
        }
    }
}
