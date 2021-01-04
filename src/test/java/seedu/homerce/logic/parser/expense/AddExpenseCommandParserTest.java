package seedu.homerce.logic.parser.expense;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.expense.AddExpenseCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.testutil.expense.ExpenseBuilder;

public class AddExpenseCommandParserTest {
    private AddExpenseCommandParser parser = new AddExpenseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Expense expectedExpense = new ExpenseBuilder(CONDITIONER).build();
        AddExpenseCommand expectedCommand = new AddExpenseCommand(expectedExpense);

        // whitespace only preamble
        AddExpenseCommand commandOne = parser.parse(" d/Conditioner f/n v/15.00 dt/10-12-2020 t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandOne.toAdd));

        //multiple descriptions - last description accepted
        AddExpenseCommand commandTwo = parser.parse(
                " d/Chair d/Conditioner f/n v/15.00 dt/10-12-2020 t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandTwo.toAdd));

        //multiple isfixed - last isfixed accepted
        AddExpenseCommand commandThree = parser.parse(
                " d/Conditioner f/y f/n v/15.00 dt/10-12-2020 t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandThree.toAdd));

        //multiple values - last value accepted
        AddExpenseCommand commandFour = parser.parse(
                " d/Conditioner f/n v/10 v/15.00 dt/10-12-2020 t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandFour.toAdd));

        //multiple dates - last date accepted
        AddExpenseCommand commandFive = parser.parse(
                " d/Conditioner f/n v/15.00 dt/10-10-2020 dt/10-12-2020 t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandFive.toAdd));

        //multiple tags - last tag accepted
        AddExpenseCommand commandSix = parser.parse(
                " d/Conditioner f/n v/15.00 dt/10-12-2020 t/equipment t/Hairsupplies");
        assertTrue(expectedCommand.toAdd.equals(commandSix.toAdd));
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws ParseException {
        // zero tags
        Expense expectedExpense = new ExpenseBuilder(CONDITIONER).withTag().build();
        AddExpenseCommand expenseCommand = new AddExpenseCommand(expectedExpense);
        AddExpenseCommand command = parser.parse(" d/Conditioner f/n v/15.00 dt/10-12-2020");
        assertTrue(expenseCommand.toAdd.equals(command.toAdd));
    }

    @Test
    public void parse_compulsoryFieldMissing_throwsParseException() {

        // description missing
        assertThrows(ParseException.class, () -> parser.parse("f/n v/15.00 dt/10-12-2020 t/Hairsupplies"));

        // isfixed missing
        assertThrows(ParseException.class, () -> parser.parse(
                "d/Conditioner v/15.00 dt/10-12-2020 t/Hairsupplies"));

        // date missing
        assertThrows(ParseException.class, () -> parser.parse("d/Conditioner f/n v/15.00 t/Hairsupplies"));

        // value missing
        assertThrows(ParseException.class, () -> parser.parse("d/Conditioner f/n dt/10-12-2020 t/Hairsupplies"));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // empty description
        assertThrows(ParseException.class, () -> parser.parse(" d/ f/n v/15.00 dt/10-12-2020 t/Hairsupplies"));

        // invalid isfixed
        assertThrows(ParseException.class, () -> parser.parse(
                " d/Conditioner f/a v/15.00 dt/10-12-2020 t/Hairsupplies"));

        // invalid value
        assertThrows(ParseException.class, () -> parser.parse(
                " d/Conditioner f/n v/-15.00 dt/10-12-2020 t/Hairsupplies"));

        //invalid date
        assertThrows(ParseException.class, () -> parser.parse(
                " d/Conditioner f/n v/15.00 dt/10-30-2020 t/Hairsupplies"));

        //invalid tag
        assertThrows(ParseException.class, () -> parser.parse(
                " d/Conditioner f/n v/15.00 dt/10-12-2020 t/Hair supplies"));
    }
}

