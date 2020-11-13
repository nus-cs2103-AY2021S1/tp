package seedu.address.logic.parser.mainpageparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.stream.Stream;

import seedu.address.logic.commands.main.EditBudgetCommand;
import seedu.address.logic.commands.main.EditBudgetCommand.EditBudgetDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.budgetindex.BudgetIndex;

public class EditBudgetCommandParser implements Parser<EditBudgetCommand> {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    @Override
    public EditBudgetCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_PRICE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBudgetCommand.MESSAGE_USAGE));
        }

        BudgetIndex budgetIndex;

        budgetIndex = ParserUtil.parseBudgetIndex(argMultimap.getPreamble());


        EditBudgetDescriptor editBudgetDescriptor = new EditBudgetDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBudgetDescriptor.setName(ParserUtil.parseBudgetName((argMultimap.getValue(PREFIX_NAME).get())));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editBudgetDescriptor.setThreshold(ParserUtil
                    .parseBudgetThreshold(argMultimap.getValue(PREFIX_PRICE).get()));
        }

        return new EditBudgetCommand(budgetIndex, editBudgetDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
