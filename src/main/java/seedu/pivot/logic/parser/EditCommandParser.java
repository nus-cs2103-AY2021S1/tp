package seedu.pivot.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_DOC;
import static seedu.pivot.logic.commands.Command.TYPE_STATUS;
import static seedu.pivot.logic.commands.Command.TYPE_SUSPECT;
import static seedu.pivot.logic.commands.Command.TYPE_TITLE;
import static seedu.pivot.logic.commands.Command.TYPE_VICTIM;
import static seedu.pivot.logic.commands.Command.TYPE_WITNESS;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.logic.commands.EditCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        //check if at case page
        if (StateManager.atMainPage()) {
            throw new ParseException(MESSAGE_INCORRECT_CASE_PAGE);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        final String editType = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (editType) {
        case TYPE_TITLE:
            return new EditTitleCommandParser().parse(arguments);
        case TYPE_STATUS:
            return new EditStatusCommandParser().parse(arguments);
        case TYPE_DOC:
            return new EditDocumentCommandParser().parse(arguments);
        case TYPE_SUSPECT:
            return new EditSuspectCommandParser().parse(arguments);
        case TYPE_WITNESS:
            return new EditWitnessCommandParser().parse(arguments);
        case TYPE_VICTIM:
            return new EditVictimCommandParser().parse(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));

        }
    }


}
