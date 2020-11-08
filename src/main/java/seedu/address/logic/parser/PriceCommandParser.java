package seedu.address.logic.parser;

import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.PriceWithinRangePredicate;

public class PriceCommandParser implements Parser<PriceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PriceCommand
     * and returns an PriceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PriceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String finalArgs = trimmedArgs.replaceAll("( )+", " ");
        String[] argsArr = finalArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, PriceCommand.COMMAND_WORD, PriceCommand.MESSAGE_USAGE, 2);
        Inequality inequality = ParserUtil.parseInequality(argsArr[0]);
        double price = ParserUtil.parsePrice(argsArr[1]);
        price = ((int) (price * 100)) / 100.0;
        return new PriceCommand(new PriceWithinRangePredicate(inequality, price));
    }
}
