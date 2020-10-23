package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddExamCommandParser implements Parser<AddExamCommand> {
    @Override
    public AddExamCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, EXAM_COMMAND_PREFIXES);

        if (!arePrefixesPresent(argMultimap, EXAM_COMMAND_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE), pe);
        }

        String examName = argMultimap.getValue(PREFIX_EXAM_NAME).get().trim();
        String examDate = argMultimap.getValue(PREFIX_EXAM_DATE).get().trim();
        Score score = ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get().trim());

        if (examName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddExamCommand.MESSAGE_EXAM_INVALID_NAME));
        }

        if (!Exam.isValidDate(examDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_EXAM_INVALID_DATE));
        }

        Exam exam = new Exam(examName, examDate, score);
        return new AddExamCommand(index, exam);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
