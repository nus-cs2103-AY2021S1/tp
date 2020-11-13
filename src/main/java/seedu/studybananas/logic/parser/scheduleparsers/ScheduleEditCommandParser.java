package seedu.studybananas.logic.parser.scheduleparsers;

import static seedu.studybananas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studybananas.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.studybananas.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.studybananas.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.studybananas.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.studybananas.logic.parser.parserutils.ParserUtil.arePrefixesPresent;

import java.util.Optional;

import seedu.studybananas.commons.core.Messages;
import seedu.studybananas.commons.core.index.Index;
import seedu.studybananas.logic.commands.schedulecommands.ScheduleEditCommand;
import seedu.studybananas.logic.parser.ArgumentMultimap;
import seedu.studybananas.logic.parser.ArgumentTokenizer;
import seedu.studybananas.logic.parser.Parser;
import seedu.studybananas.logic.parser.exceptions.ParseException;
import seedu.studybananas.logic.parser.parserutils.ParserUtil;
import seedu.studybananas.model.task.DateTime;
import seedu.studybananas.model.task.Description;
import seedu.studybananas.model.task.Duration;
import seedu.studybananas.model.task.Title;


public class ScheduleEditCommandParser implements Parser<ScheduleEditCommand> {
    /*
     * Arguments of userInput should contain 3 distinct part separated by empty space.
     * The first part is just an empty string.
     * The second part is the task index.
     * The third part is the updated task information.
     */
    private static final int EXPECTED_PART_OF_INPUT = 3;

    private String getIndexFromInput(String input) throws ParseException {
        String[] splittedPartInput = input.split(" ", EXPECTED_PART_OF_INPUT);
        if (splittedPartInput.length < EXPECTED_PART_OF_INPUT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleEditCommand.MESSAGE_USAGE));
        }

        String index = splittedPartInput[1];
        if (ParserUtil.isNumeric(index)) {
            return index;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleEditCommand.MESSAGE_USAGE));
        }
    }

    private String getEditInfo(String input) throws ParseException {
        String[] splittedPartInput = input.split(" ", EXPECTED_PART_OF_INPUT);
        String emptySpace = " ";
        if (splittedPartInput.length < EXPECTED_PART_OF_INPUT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleEditCommand.MESSAGE_USAGE));
        }
        // add back the empty space to conform to the format of ArgumentTokenizer#tokenize()
        return emptySpace + splittedPartInput[2];
    }

    // Special parser for Title since Title is optional in edit command, unlike add command where Title is compulsory
    private Title parseEditedTitle(ArgumentMultimap argumentMultimap) throws ParseException {
        Optional<String> parsedTitle = argumentMultimap.getValue(PREFIX_TITLE);
        if (!parsedTitle.isPresent()) {
            return null;
        } else {
            String titleString = parsedTitle.get();
            return ParserUtil.parseTitle(titleString);
        }
    }
    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleEditCommand
     * and returns a ScheduleEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleEditCommand parse(String args) throws ParseException {

        String indexString = getIndexFromInput(args);
        String editInfoString = getEditInfo(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        editInfoString, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_TIME, PREFIX_DURATION);

        if ((!arePrefixesPresent(argMultimap, PREFIX_TITLE)
                && !arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)
                && !arePrefixesPresent(argMultimap, PREFIX_TIME)
                && !arePrefixesPresent(argMultimap, PREFIX_DURATION))
        ) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleEditCommand.MESSAGE_USAGE));
        }


        Title title = parseEditedTitle(argMultimap);
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).orElse(null));
        DateTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).orElse(null));
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).orElse(null));

        return new ScheduleEditCommand(index, title, description, time, duration);

    }
}
