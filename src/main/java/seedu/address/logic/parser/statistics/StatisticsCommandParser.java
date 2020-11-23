package seedu.address.logic.parser.statistics;

import seedu.address.logic.commands.statistics.StatisticsCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class StatisticsCommandParser implements Parser<StatisticsCommand> {

    @Override
    public StatisticsCommand parse(String userInput) throws ParseException {
        userInput = userInput.trim();
        int deckIndex = -1;

        try {
            deckIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException numberFormatException) {
            // do nothing
        }

        return new StatisticsCommand(deckIndex);
    }
}
