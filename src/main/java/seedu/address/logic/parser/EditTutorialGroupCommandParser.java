package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class EditTutorialGroupCommandParser implements Parser<EditTutorialGroupCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTutorialGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GRP, PREFIX_TUTORIAL_GRP_DAY,
            PREFIX_TUTORIAL_GRP_START_TIME, PREFIX_TUTORIAL_GRP_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GRP, PREFIX_TUTORIAL_GRP_DAY,
            PREFIX_TUTORIAL_GRP_START_TIME, PREFIX_TUTORIAL_GRP_END_TIME) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTutorialGroupCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTutorialGroupCommand.MESSAGE_USAGE), pe);
        }

        TutorialGroupId tutorialGroupId = ParserUtil
                .parseTutorialGroupId(argMultimap.getValue(PREFIX_TUTORIAL_GRP).get());
        DayOfWeek dayOfWeek = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_TUTORIAL_GRP_DAY).get());
        TimeOfDay[] timeOfDays = ParserUtil.parseTimesOfDay(argMultimap.getValue(PREFIX_TUTORIAL_GRP_START_TIME).get(),
                argMultimap.getValue(PREFIX_TUTORIAL_GRP_END_TIME).get());
        TimeOfDay startTime = timeOfDays[0];
        TimeOfDay endTime = timeOfDays[1];

        return new EditTutorialGroupCommand(index, tutorialGroupId, dayOfWeek, startTime, endTime);
    }
}
