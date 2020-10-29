package seedu.pivot.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.pivot.logic.commands.witnesscommands.EditWitnessCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

public class EditWitnessCommandParser implements Parser<EditWitnessCommand> {
    @Override
    public EditWitnessCommand parse(String args) throws ParseException {
        requireNonNull(args);

        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditWitnessCommand.MESSAGE_USAGE));
        }
        final String index = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // convert index to Index class
        Index witnessIndex = ParserUtil.getParsedIndex(index, EditWitnessCommand.MESSAGE_USAGE);

        // get case from state
        Index caseIndex = StateManager.getState();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                arguments, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditPersonDescriptor editPersonDescriptor = ParserUtil.parseEditedPersonFields(argMultimap);

        return new EditWitnessCommand(caseIndex, witnessIndex, editPersonDescriptor);
    }
}
