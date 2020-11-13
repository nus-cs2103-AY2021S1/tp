package seedu.internhunter.logic.parser.find;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.find.FindCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * A general find command parser which will parser the data into a more specific parser.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        String itemType = getItemType(args, FindCommand.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new FindCompanyCommandParser().parse(commandDetails);
        case APPLICATION_ALIAS:
            return new FindApplicationCommandParser().parse(commandDetails);
        case PROFILE_ALIAS:
            return new FindProfileCommandParser().parse(commandDetails);
        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);
        }
    }

}
