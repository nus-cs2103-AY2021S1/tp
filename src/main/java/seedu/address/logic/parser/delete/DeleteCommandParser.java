package seedu.address.logic.parser.delete;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.address.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.address.logic.parser.util.GeneralParserUtil.isValidItemType;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteApplicationCommand;
import seedu.address.logic.commands.delete.DeleteCommandAbstract;
import seedu.address.logic.commands.delete.DeleteCompanyCommand;
import seedu.address.logic.commands.delete.DeleteProfileCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.GeneralParserUtil;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommandAbstract> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommandAbstract parse(String args) throws ParseException {
        String itemType = getItemType(args, DeleteCommandAbstract.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        isValidItemType(itemType);

        // Internship has a different parse requirement
        if (itemType.equals(INTERNSHIP_ALIAS)) {
            return new DeleteInternshipCommandParser().parse(commandDetails);
        }

        Index index = GeneralParserUtil.parseIndex(commandDetails);
        switch (itemType) {
        case COMPANY_ALIAS:
            //checkCommandDetailsIsNotBlank(commandDetails, itemType, DeleteCompanyCommand.MESSAGE_USAGE);
            return new DeleteCompanyCommand(index);

        case APPLICATION_ALIAS:
            //checkCommandDetailsIsNotBlank(commandDetails, itemType, DeleteApplicationCommand.MESSAGE_USAGE);
            return new DeleteApplicationCommand(index);

        case PROFILE_ALIAS:
            //checkCommandDetailsIsNotBlank(commandDetails, itemType, DeleteProfileCommand.MESSAGE_USAGE);
            return new DeleteProfileCommand(index);

        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
