package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

public class MenuCommand extends Command {

    public static final String COMMAND_WORD = "menu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the whole menu again.";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }
        model.showDefaultMenu();
        return new CommandResult(
                Messages.MESSAGE_MENU_LIST);
    }
}
