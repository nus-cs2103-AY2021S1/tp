package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditLessonCommand.EditLessonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditLessonCommand object
 */
public class EditLessonCommandParser implements Parser<EditLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_DAY,
                        PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLessonCommand.MESSAGE_USAGE), e);
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editLessonDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editLessonDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                            .get()));
        }
        if (argMultimap.getValue(PREFIX_DAY).isPresent()) {
            editLessonDescriptor.setDayOfWeek(ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editLessonDescriptor.setStartTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editLessonDescriptor.setEndTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editLessonDescriptor.setStartDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            editLessonDescriptor.setEndDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditLessonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLessonCommand(index, editLessonDescriptor);
    }
}
