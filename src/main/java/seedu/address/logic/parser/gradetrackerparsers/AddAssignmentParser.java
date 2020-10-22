package seedu.address.logic.parser.gradetrackerparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_NEW_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERCENTAGE_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT_ASSIGNMENT;

import java.util.stream.Stream;

import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.grade.Assignment;

public class AddAssignmentParser implements Parser<AddAssignmentCommand> {
    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Assignment newAssignment;
        String moduleName;
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_ADD_NEW_ASSIGNMENT,
                PREFIX_PERCENTAGE_ASSIGNMENT, PREFIX_RESULT_ASSIGNMENT);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADD_NEW_ASSIGNMENT,
                PREFIX_PERCENTAGE_ASSIGNMENT, PREFIX_RESULT_ASSIGNMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
        }

        moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get()).fullName;
        String assignmentName = ParserUtil.parseAssignmentName(argMultimap.getValue(PREFIX_ADD_NEW_ASSIGNMENT).get());
        double assignmentPercentage = ParserUtil.parseAssignmentPercentage(
                argMultimap.getValue(PREFIX_PERCENTAGE_ASSIGNMENT).get());
        double assignmentResult = ParserUtil.parseAssignmentResult(
                argMultimap.getValue(PREFIX_RESULT_ASSIGNMENT).get());
        newAssignment = new Assignment(assignmentName, assignmentPercentage, assignmentResult);

        return new AddAssignmentCommand(moduleName, newAssignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
