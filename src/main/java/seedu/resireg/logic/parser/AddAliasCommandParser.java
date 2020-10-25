package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_COMMAND;
import static seedu.resireg.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.resireg.logic.commands.AddAliasCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAliasCommandParser implements Parser<AddAliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_COMMAND, PREFIX_ALIAS);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMMAND, PREFIX_ALIAS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAliasCommand.HELP.getFullMessage()));
        }

        CommandWord commandWord = ParserUtil.parseCommandWord(argMultimap.getValue(PREFIX_COMMAND).get());
        Alias alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());

        CommandWordAlias commandWordAlias = new CommandWordAlias(commandWord, alias);

        return new AddAliasCommand(commandWordAlias);
    }
}
