package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.PriceWithinRangePredicate;

public class PriceCommandParserTest {

    private PriceCommandParser parser = new PriceCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        assertParseSuccess(parser, "< 2",
                new PriceCommand(new PriceWithinRangePredicate(ParserUtil.parseInequality("<"),
                        ParserUtil.parsePrice("2"))));
        assertParseSuccess(parser, "<= 3",
                new PriceCommand(new PriceWithinRangePredicate(ParserUtil.parseInequality("<="),
                        ParserUtil.parsePrice("3"))));
        assertParseSuccess(parser, ">= 4",
                new PriceCommand(new PriceWithinRangePredicate(ParserUtil.parseInequality(">="),
                        ParserUtil.parsePrice("4"))));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String format = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(Messages.MESSAGE_INSUFFICIENT_ARGUMENTS, PriceCommand.COMMAND_WORD, 2,
                        PriceCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", format);
        assertParseFailure(parser, "1", format);
        assertParseFailure(parser, "<", format);
    }

    @Test
    public void parse_invalidValues_failure() {
        String illegalPrice = Messages.MESSAGE_INVALID_PRICE;
        assertParseFailure(parser, "< -1", String.format(illegalPrice, "-1"));
        assertParseFailure(parser, "> -1.5", String.format(illegalPrice, "-1.5"));

        String illegalInequality = Messages.MESSAGE_INVALID_INEQUALITY;
        assertParseFailure(parser, "<< 1", String.format(illegalInequality, "<<"));
        assertParseFailure(parser, ">* 2", String.format(illegalInequality, ">*"));
    }
}
