package seedu.internhunter.logic.parser.view;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.view.ViewCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     *
     * @param args Arguments provided by the user.
     * @return ViewCommand.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ViewCommand parse(String args) throws ParseException {
        String itemType = getItemType(args, ViewCommand.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new ViewCompanyCommandParser().parse(commandDetails);

        case APPLICATION_ALIAS:
            return new ViewApplicationCommandParser().parse(commandDetails);

        case PROFILE_ALIAS:
            return new ViewProfileCommandParser().parse(commandDetails);
        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);
        }
    }

}
