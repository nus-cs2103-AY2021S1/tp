package seedu.address.logic.parser.gradetrackerparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.model.task.Priority;

public class AddAssignmentParser implements Parser<AddAssignmentCommand> {
    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Assignment newAssignment;
        AssignmentName assignmentName;
        AssignmentPercentage assignmentPercentage;
        AssignmentResult assignmentResult;
        ModuleName moduleName;
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_ASSIGNMENT_NAME,
                PREFIX_ASSIGNMENT_PERCENTAGE, PREFIX_ASSIGNMENT_RESULT);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ASSIGNMENT_NAME,
                PREFIX_ASSIGNMENT_PERCENTAGE, PREFIX_ASSIGNMENT_RESULT)
                || !argMultimap.getPreamble().isEmpty()) && (argMultimap.getValue(PREFIX_ASSIGNMENT_NAME).isPresent()
                        && argMultimap.getValue(PREFIX_ASSIGNMENT_PERCENTAGE).isPresent()
                        && argMultimap.getValue(PREFIX_ASSIGNMENT_RESULT).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
        }

        moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
        try {
            assignmentName = ParserUtil.parseAssignmentName(
                    argMultimap.getValue(PREFIX_ASSIGNMENT_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE), pe);
        }
        try {
             assignmentPercentage = ParserUtil.parseAssignmentPercentage(
                    argMultimap.getValue(PREFIX_ASSIGNMENT_PERCENTAGE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE), pe);
        }
        try {
             assignmentResult = ParserUtil.parseAssignmentResult(
                    argMultimap.getValue(PREFIX_ASSIGNMENT_RESULT).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE), pe);
        }
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
