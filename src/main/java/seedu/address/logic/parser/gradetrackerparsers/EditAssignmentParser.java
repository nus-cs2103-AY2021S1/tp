package seedu.address.logic.parser.gradetrackerparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;

public class EditAssignmentParser implements Parser<EditAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAssignmentCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditAssignmentCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME,
                PREFIX_ASSIGNMENT_NAME, PREFIX_ASSIGNMENT_PERCENTAGE, PREFIX_ASSIGNMENT_RESULT);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        Index index;
        ModuleName moduleName;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAssignmentCommand.MESSAGE_USAGE), pe);
        }

        EditAssignmentDescriptor editAssignmentDescriptor = new EditAssignmentDescriptor();
        if (argMultimap.getValue(PREFIX_ASSIGNMENT_NAME).isPresent()) {
            try {
                editAssignmentDescriptor.setAssignmentName(ParserUtil.parseAssignmentName(
                        argMultimap.getValue(PREFIX_ASSIGNMENT_NAME).get()));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignmentName.MESSAGE_CONSTRAINTS), pe);
            }
        }

        if (argMultimap.getValue(PREFIX_ASSIGNMENT_PERCENTAGE).isPresent()) {
            try {
                editAssignmentDescriptor.setAssignmentPercentage(ParserUtil.parseAssignmentPercentage(
                        argMultimap.getValue(PREFIX_ASSIGNMENT_PERCENTAGE).get()));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignmentPercentage.MESSAGE_CONSTRAINTS), pe);
            }
        }

        if (argMultimap.getValue(PREFIX_ASSIGNMENT_RESULT).isPresent()) {
            try {
                editAssignmentDescriptor.setAssignmentResult(ParserUtil.parseAssignmentResult(
                        argMultimap.getValue(PREFIX_ASSIGNMENT_RESULT).get()));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignmentResult.MESSAGE_CONSTRAINTS), pe);
            }
        }

        if (!editAssignmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAssignmentCommand.MESSAGE_NOT_EDITED));
        }

        return new EditAssignmentCommand(index, moduleName, editAssignmentDescriptor);
    }
}
