package seedu.homerce.logic.parser.expense;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.expense.FindExpenseCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.expense.TagPredicate;
import seedu.homerce.model.expense.predicate.ExpenseDatePredicate;
import seedu.homerce.model.expense.predicate.ExpenseDescriptionPredicate;
import seedu.homerce.model.expense.predicate.ExpenseIsFixedPredicate;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;

public class FindExpenseCommandParserTest {

    private FindExpenseCommandParser parser = new FindExpenseCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("  "));
    }

    @Test
    public void parse_multipleArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" d/Conditioner dt/10-10-2020"));
        assertThrows(ParseException.class, () -> parser.parse(" t/others dt/10-10-2020"));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" r/Conditioner"));
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" Conditioner"));
    }

    @Test
    public void parse_noValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" t/"));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" hello d/Conditioner"));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        //invalid date
        assertThrows(ParseException.class, () -> parser.parse(" dt/10-20-2020"));

        //invalid tag
        assertThrows(ParseException.class, () -> parser.parse(" t/hair supplies"));

        //invalid description
        assertThrows(ParseException.class, () -> parser.parse(" d/"));

        // invalid isfixed
        assertThrows(ParseException.class, () -> parser.parse(" f/a"));
    }

    @Test
    public void parse_validDesc_success() throws ParseException {
        FindExpenseCommand command = parser.parse(" d/Conditioner");
        Predicate<Expense> descPredicate =
                new ExpenseDescriptionPredicate(new Description("Conditioner"));
        assertTrue(command.predicate.equals(descPredicate));
    }

    @Test
    public void parse_validTag_success() throws ParseException {
        FindExpenseCommand command = parser.parse(" t/others");
        Predicate<Expense> tagPredicate =
                new TagPredicate(new Tag("others"));
        assertTrue(command.predicate.equals(tagPredicate));
    }

    @Test
    public void parse_validDate_success() throws ParseException {
        FindExpenseCommand command = parser.parse(" dt/10-10-2020");
        Predicate<Expense> datePredicate =
                new ExpenseDatePredicate(new Date("10-10-2020"));
        assertTrue(command.predicate.equals(datePredicate));
    }

    @Test
    public void parse_validIsFixed_success() throws ParseException {
        FindExpenseCommand command = parser.parse(" f/n");
        Predicate<Expense> isFixedPredicate =
                new ExpenseIsFixedPredicate(new IsFixed("n"));
        assertTrue(command.predicate.equals(isFixedPredicate));
    }
}
