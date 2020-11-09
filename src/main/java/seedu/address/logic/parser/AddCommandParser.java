package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModularCredit;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;
import seedu.address.model.semester.SemesterManager;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD_NAME, PREFIX_GRADE, PREFIX_MODULAR_CREDIT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MOD_NAME) || !argMultimap.getPreamble().isEmpty()
            || argMultimap.argsContainWrongPrefix()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseName(argMultimap.getValue(PREFIX_MOD_NAME).get());
        Module module;
        SemesterManager semesterManager = SemesterManager.getInstance();
        Semester semester = semesterManager.getCurrentSemester();
        ModularCredit modularCredit;

        if (arePrefixesPresent(argMultimap, PREFIX_MODULAR_CREDIT)) {
            modularCredit = ParserUtil.parseModularCredit(argMultimap.getValue(PREFIX_MODULAR_CREDIT).get());
        } else {
            modularCredit = new ModularCredit(moduleName.fullModName);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_GRADE)) {
            Grade grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
            module = new Module(moduleName, grade, modularCredit, semester);
        } else {
            module = new Module(moduleName, modularCredit, semester);
        }

        return new AddCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
