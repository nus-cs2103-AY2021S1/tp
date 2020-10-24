package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.stream.Stream;

import seedu.resireg.logic.commands.DeleteAliasCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_COMMAND, PREFIX_ALIAS);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMMAND, PREFIX_ALIAS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAliasCommand.HELP.getFullMessage()));
        }

        CommandWord commandWord = ParserUtil.parseCommandWord(argMultimap.getValue(PREFIX_COMMAND).get());
        Alias alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());

        CommandWordAlias commandWordAlias = new CommandWordAlias(commandWord, alias);

        return new DeleteAliasCommand(commandWordAlias);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
