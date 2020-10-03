package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;

public class ListFinanceRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list_finance";

    public static final String MESSAGE_SUCCESS = "Listed all finance records:\n %1$s";

    public static final String MESSAGE_EMPTY_LIST = "No finance records listed";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.viewFinanceRecords().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.viewFinanceRecords()));
    }
}
