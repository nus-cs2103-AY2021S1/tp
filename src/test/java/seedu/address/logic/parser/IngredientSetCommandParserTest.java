package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ingredientcommands.IngredientSetCommand;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.IngredientName;

class IngredientSetCommandParserTest {

    private static final String INVALID_USER_INPUT_NO_INGREDIENT = " m/90";
    private static final String INVALID_USER_INPUT_NO_AMOUNT = " i/Milk";
    private static final String USER_INPUT_DUPLICATE_INGREDIENT = " i/Milk i/Boba m/90";
    private static final String USER_INPUT_DUPLICATE_AMOUNT = " i/Milk m/90 m/80";
    private static final String USER_INPUT_DUPLICATE_INGREDIENT_AND_AMOUNT = " i/Milk i/Boba m/90 m/80";
    private static final String INVALID_USER_INPUT_WITH_PREAMBLE = "Milk i/Milk M/90";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, IngredientSetCommand.MESSAGE_USAGE);

    private IngredientSetCommandParser parser = new IngredientSetCommandParser();

    @Test
    public void parse_missingFields_failure() {
        // no ingredient specified
        assertParseFailure(parser, INVALID_USER_INPUT_NO_INGREDIENT, MESSAGE_INVALID_FORMAT);

        // no amount specified
        assertParseFailure(parser, INVALID_USER_INPUT_NO_AMOUNT, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicateFields_success() {
        // duplicate ingredient field
        IngredientSetCommand.SetIngredientDescriptor descriptor = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor.setIngredientName(new IngredientName("Boba"));
        descriptor.setAmount(new Amount("90"));
        IngredientSetCommand expectedCommand = new IngredientSetCommand(new IngredientName("Boba"), descriptor);
        assertParseSuccess(parser, USER_INPUT_DUPLICATE_INGREDIENT, expectedCommand);

        // duplicate amount field
        IngredientSetCommand.SetIngredientDescriptor descriptor1 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor1.setIngredientName(new IngredientName("Milk"));
        descriptor1.setAmount(new Amount("80"));
        IngredientSetCommand expectedCommand1 = new IngredientSetCommand(new IngredientName("Milk"), descriptor1);
        assertParseSuccess(parser, USER_INPUT_DUPLICATE_AMOUNT, expectedCommand1);

        // duplicate ingredient and amount fields
        IngredientSetCommand.SetIngredientDescriptor descriptor2 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor2.setIngredientName(new IngredientName("Boba"));
        descriptor2.setAmount(new Amount("80"));
        IngredientSetCommand expectedCommand2 = new IngredientSetCommand(new IngredientName("Boba"), descriptor2);
        assertParseSuccess(parser, USER_INPUT_DUPLICATE_INGREDIENT_AND_AMOUNT, expectedCommand2);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // a preamble is wrongly entered after command word
        assertParseFailure(parser, INVALID_USER_INPUT_WITH_PREAMBLE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ingredient name , all lower case
        assertParseFailure(parser, " i/milk m/90", ParserUtil.MESSAGE_INVALID_INGREDIENT_NAME);

        // invalid ingredient name , wrong name
        assertParseFailure(parser, " i/Miilk m/90", ParserUtil.MESSAGE_INVALID_INGREDIENT_NAME);

        // invalid amount , negative value
        assertParseFailure(parser, " i/Milk m/-90", ParserUtil.MESSAGE_INVALID_AMOUNT);

        // invalid amount , non-negative value but contains negative sign
        assertParseFailure(parser, " i/Milk m/-0", ParserUtil.MESSAGE_INVALID_AMOUNT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // valid amount and ingredient name 0
        IngredientSetCommand.SetIngredientDescriptor descriptor = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor.setIngredientName(new IngredientName("Milk"));
        descriptor.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand0 = new IngredientSetCommand(new IngredientName("Milk"), descriptor);
        assertParseSuccess(parser, " i/Milk m/10", expectedCommand0);

        // valid amount and ingredient name 1
        IngredientSetCommand.SetIngredientDescriptor descriptor1 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor1.setIngredientName(new IngredientName("Pearl"));
        descriptor1.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand1 = new IngredientSetCommand(new IngredientName("Pearl"), descriptor1);
        assertParseSuccess(parser, " i/Pearl m/10", expectedCommand1);

        // valid amount and ingredient name 2
        IngredientSetCommand.SetIngredientDescriptor descriptor2 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor2.setIngredientName(new IngredientName("Boba"));
        descriptor2.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand2 = new IngredientSetCommand(new IngredientName("Boba"), descriptor2);
        assertParseSuccess(parser, " i/Boba m/10", expectedCommand2);

        // valid amount and ingredient name 3
        IngredientSetCommand.SetIngredientDescriptor descriptor3 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor3.setIngredientName(new IngredientName("Black Tea"));
        descriptor3.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand3 = new IngredientSetCommand(new IngredientName("Black Tea"), descriptor3);
        assertParseSuccess(parser, " i/Black Tea m/10", expectedCommand3);

        // valid amount and ingredient name 4
        IngredientSetCommand.SetIngredientDescriptor descriptor4 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor4.setIngredientName(new IngredientName("Green Tea"));
        descriptor4.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand4 = new IngredientSetCommand(new IngredientName("Green Tea"), descriptor4);
        assertParseSuccess(parser, " i/Green Tea m/10", expectedCommand4);

        // valid amount and ingredient name 5
        IngredientSetCommand.SetIngredientDescriptor descriptor5 = new IngredientSetCommand.SetIngredientDescriptor();
        descriptor5.setIngredientName(new IngredientName("Brown Sugar"));
        descriptor5.setAmount(new Amount("10"));
        IngredientSetCommand expectedCommand5 =
                new IngredientSetCommand(new IngredientName("Brown Sugar"), descriptor5);
        assertParseSuccess(parser, " i/Brown Sugar m/10", expectedCommand5);
    }

}
