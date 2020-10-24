package seedu.address.logic.parser;

import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.PriceWithinRangePredicate;

public class PriceCommandParser {
    public PriceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] argsArr = trimmedArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, PriceCommand.COMMAND_WORD, PriceCommand.MESSAGE_USAGE, 2);
        Inequality inequality = ParserUtil.parseInequality(argsArr[0]);
        double price = ParserUtil.parsePrice(argsArr[1]);

        return new PriceCommand(new PriceWithinRangePredicate(inequality, price));
    }
}
