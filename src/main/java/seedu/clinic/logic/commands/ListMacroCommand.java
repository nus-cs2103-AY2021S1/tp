package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.clinic.logic.commands.exceptions.CommandException;
import seedu.clinic.model.Model;
import seedu.clinic.model.macro.Macro;

/**
 * Lists all saved macros to the user.
 */
public class ListMacroCommand extends Command {
    public static final String COMMAND_WORD = "listmacro";

    public static final String MESSAGE_USAGE = "List macro Command Usage\n\nLists all presently saved macros"
            + " in CLI-nic.\n\n"
            + "Example:\n"
            + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Currently saved Macros:\n%1$s";
    public static final String MESSAGE_EMPTY_LIST = "There are no macros currently saved.";
    private String listFormatting = "%1$s. ";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Macro> macroList = model.getMacroList();

        if (macroList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        String resultString = formatListToString(macroList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, resultString));
    }

    private String formatListToString(List<Macro> macroList) {
        StringBuilder resultStringBuilder = new StringBuilder();
        int counter = 1;
        for (Macro macro : macroList) {
            resultStringBuilder.append(String.format(listFormatting, counter)).append(macro).append("\n\n");
            counter++;
        }
        return resultStringBuilder.toString();
    }
}
