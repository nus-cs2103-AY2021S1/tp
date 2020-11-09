package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.cc.logic.commands.accountlevel.AddAccountCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;


/**
 * Parses input arguments and creates a new AddAccountCommand object
 */
public class AddAccountCommandParser implements Parser<AddAccountCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAccountCommand
     * and returns an AddAccountCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAccountCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        boolean isPrefixPresent = arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();

        if (!isPrefixPresent || !isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAccountCommand.MESSAGE_USAGE));
        }

        boolean areNumberOfPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_NAME);

        if (!areNumberOfPrefixCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, AddAccountCommand.PREFIXES));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Account account = new Account(name);

        return new AddAccountCommand(account);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
