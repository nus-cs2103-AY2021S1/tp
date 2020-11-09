package tutorspet.logic.parser.moduleclass;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutorspet.commons.core.Messages.MESSAGE_NOT_EDITED;
import static tutorspet.logic.commands.moduleclass.EditModuleClassCommand.EditModuleClassDescriptor;
import static tutorspet.logic.commands.moduleclass.EditModuleClassCommand.MESSAGE_USAGE;
import static tutorspet.logic.parser.CliSyntax.PREFIX_NAME;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.moduleclass.EditModuleClassCommand;
import tutorspet.logic.parser.ArgumentMultimap;
import tutorspet.logic.parser.ArgumentTokenizer;
import tutorspet.logic.parser.Parser;
import tutorspet.logic.parser.ParserUtil;
import tutorspet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create a new EditModuleClassCommand object.
 */
public class EditModuleClassCommandParser implements Parser<EditModuleClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditModuleClassCommand
     * and returns an EditModuleClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditModuleClassCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        EditModuleClassDescriptor editModuleClassDescriptor = new EditModuleClassDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModuleClassDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (!editModuleClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditModuleClassCommand(index, editModuleClassDescriptor);
    }
}
