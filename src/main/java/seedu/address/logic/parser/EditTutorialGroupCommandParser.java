package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.commands.EditTutorialGroupCommand.EditTutorialGroupDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditTutorialGroupCommandParser implements Parser<EditTutorialGroupCommand> {

    /**
     * Main parse command in for EditTutorialGroup, parses user input to return a command to be executed
     * @param args
     * @throws ParseException
     */
    public EditTutorialGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GRP, PREFIX_TUTORIAL_GRP_DAY,
                PREFIX_TUTORIAL_GRP_START_TIME, PREFIX_TUTORIAL_GRP_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditTutorialGroupCommand.MESSAGE_USAGE), pe);
        }

        EditTutorialGroupDescriptor editTutorialGroupDescriptor = new EditTutorialGroupDescriptor();
        if (argMultimap.getValue(PREFIX_TUTORIAL_GRP).isPresent()) {
            editTutorialGroupDescriptor.setId(ParserUtil
                .parseTutorialGroupId(argMultimap.getValue(PREFIX_TUTORIAL_GRP).get()));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GRP_DAY).isPresent()) {
            editTutorialGroupDescriptor.setDayOfWeek(ParserUtil
                .parseDayOfWeek(argMultimap.getValue(PREFIX_TUTORIAL_GRP_DAY).get()));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GRP_START_TIME).isPresent()) {
            editTutorialGroupDescriptor.setStartTime(ParserUtil
                .parseTimeOfDay(argMultimap.getValue(PREFIX_TUTORIAL_GRP_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL_GRP_END_TIME).isPresent()) {
            editTutorialGroupDescriptor.setEndTime(ParserUtil
                .parseTimeOfDay(argMultimap.getValue(PREFIX_TUTORIAL_GRP_END_TIME).get()));
        }

        if (!editTutorialGroupDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTutorialGroupCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTutorialGroupCommand(index, editTutorialGroupDescriptor);
    }
}
