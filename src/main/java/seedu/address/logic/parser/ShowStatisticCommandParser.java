package seedu.address.logic.parser;

import seedu.address.logic.commands.ShowStatisticCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowStatisticCommand object
 */
public class ShowStatisticCommandParser implements Parser<ShowStatisticCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowStatisticCommand
     * and returns a ShowStatisticCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ShowStatisticCommand parse(String args) throws ParseException {
        return new ShowStatisticCommand();
    }
}
