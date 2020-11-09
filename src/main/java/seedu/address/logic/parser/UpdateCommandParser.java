package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdateModNameDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Cap;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModuleName;

/**
 * Parses input arguments and creates a new UpdateCommand object
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns an UpdateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD_NAME, PREFIX_GRADE, PREFIX_SEMESTER);
        if (!argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_MOD_NAME).isEmpty()
                || argMultimap.argsContainWrongPrefix()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseName(argMultimap.getValue(PREFIX_MOD_NAME).get());

        UpdateModNameDescriptor updateModNameDescriptor = new UpdateModNameDescriptor();
        updateModNameDescriptor.setName(moduleName);
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            updateModNameDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        } else {
            updateModNameDescriptor.setGrade(new Grade(Cap.NA.toString()));
        }
        if (!updateModNameDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }
        if (argMultimap.getValue(PREFIX_SEMESTER).isPresent()) {
            updateModNameDescriptor.setSemester(ParserUtil.parseSemester(
                    argMultimap.getValue(PREFIX_SEMESTER).get().toUpperCase()));
        }

        return new UpdateCommand(moduleName, updateModNameDescriptor);
    }
}
