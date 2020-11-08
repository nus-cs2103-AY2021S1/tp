package seedu.pivot.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.pivot.logic.commands.suspectcommands.EditSuspectCommand;
import seedu.pivot.logic.commands.victimcommands.EditVictimCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

public class EditVictimCommandParser implements Parser<EditVictimCommand> {
    @Override
    public EditVictimCommand parse(String args) throws ParseException {
        requireNonNull(args);

        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditVictimCommand.MESSAGE_USAGE));
        }
        final String index = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // convert index to Index class
        Index victimIndex = ParserUtil.getParsedIndex(index, EditVictimCommand.MESSAGE_USAGE);

        // get case from state
        Index caseIndex = StateManager.getState();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                arguments, PREFIX_NAME, PREFIX_SEX, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditPersonDescriptor editPersonDescriptor =
                ParserUtil.parseEditedPersonFields(argMultimap, EditSuspectCommand.MESSAGE_USAGE);

        return new EditVictimCommand(caseIndex, victimIndex, editPersonDescriptor);
    }
}
