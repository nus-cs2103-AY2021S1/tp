package seedu.address.logic.parser.mainpageparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.main.CreateBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.Date;
import seedu.address.model.budget.Name;
import seedu.address.model.budget.Threshold;
import seedu.address.model.expenditure.ExpenditureList;


public class CreateBudgetCommandParser implements Parser<CreateBudgetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the OpenBudgetCommand
     * and returns an OpenBudgetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateBudgetCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseBudgetName(argMultimap.getValue(PREFIX_NAME).get());
        Date date = new Date(LocalDateTime.now().toString());
        Optional<Threshold> threshold = ParserUtil.parseBudgetThreshold(argMultimap.getValue(PREFIX_PRICE)
                .orElse(""));
        Budget budget = new Budget(name, date, threshold, new ExpenditureList());
        return new CreateBudgetCommand(budget);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
