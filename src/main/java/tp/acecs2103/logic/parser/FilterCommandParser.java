package tp.acecs2103.logic.parser;

import static java.util.Objects.requireNonNull;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_DDLTYPE;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import java.util.stream.Stream;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.FilterCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.WeekNumber;

public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns an FilterCommand object for execution.
     * @throws ParseException if the user input is not in correct format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, CliSyntax.PREFIX_WEEK_NUMBER, CliSyntax.PREFIX_KEYWORD, CliSyntax.PREFIX_DDLTYPE);

        if (!anyPrefixPresent(argMultimap,
                PREFIX_WEEK_NUMBER, PREFIX_KEYWORD, PREFIX_DDLTYPE)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get().equals("")
                && argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get().equals("")
                && argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get().equals("")) {
            return new FilterCommand(argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get());
        }
        if (!argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get().equals("")
                && !argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get().equals("")
                && argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get().equals("")) {
            return new FilterCommand(
                    argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get(),
                    new WeekNumber(argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get()));
        }
        if (!argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get().equals("")
                && argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get().equals("")
                && !argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get().equals("")) {
            return new FilterCommand(
                    argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get(),
                    argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get());
        }
        if (!argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get().equals("")
                && !argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get().equals("")
                && !argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get().equals("")) {
            return new FilterCommand(
                    argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get(),
                    argMultimap.getValue(CliSyntax.PREFIX_DDLTYPE).get(),
                    new WeekNumber(argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get()));
        }
        return new FilterCommand(argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get());
    }

    /**
     * Returns true if any of the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> !argumentMultimap.getValue(prefix).get().equals(""));
    }
}
