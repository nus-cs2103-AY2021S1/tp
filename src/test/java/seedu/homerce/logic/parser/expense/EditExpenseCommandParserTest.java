package seedu.homerce.logic.parser.expense;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.expense.EditExpenseCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.testutil.expense.EditExpenseDescriptorBuilder;

public class EditExpenseCommandParserTest {

    private EditExpenseCommandParser parser = new EditExpenseCommandParser();

    @Test
    public void parse_missingParts_throwsParseException() {

        // no index specified
        assertThrows(ParseException.class, () -> parser.parse("d/Shampoo"));

        // no field specified
        assertThrows(ParseException.class, () -> parser.parse("1 Shampoo"));

        //no index and no field
        assertThrows(ParseException.class, () -> parser.parse("Shampoo"));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {

        // negative index
        assertThrows(ParseException.class, () -> parser.parse("-5 d/Shampoo"));

        // zero index
        assertThrows(ParseException.class, () -> parser.parse("0 d/Shampoo"));

        // invalid preamble
        assertThrows(ParseException.class, () -> parser.parse("2 hello d/Shampoo"));

        //invalid prefix
        assertThrows(ParseException.class, () -> parser.parse("1 r/Shampoo"));
    }

    @Test
    public void parse_invalidValues_throwsParseException() {

        // invalid description
        assertThrows(ParseException.class, () -> parser.parse("1 d/"));

        // invalid isfixed
        assertThrows(ParseException.class, () -> parser.parse("1 f/a"));

        // invalid value
        assertThrows(ParseException.class, () -> parser.parse("1 v/-1"));

        //invalid date
        assertThrows(ParseException.class, () -> parser.parse("1 dt/20-10-400"));

        //invalid tag
        assertThrows(ParseException.class, () -> parser.parse("1 t/Sham poo"));
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = "2 d/Chair f/n v/50.00 dt/10-1-2020 t/others";
        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription("Chair").withIsFixed("n").withAmount(50.00).withDate("10-1-2020")
                .withTag("others").build();
        EditExpenseCommand expectedCommand = new EditExpenseCommand(targetIndex, descriptor);
        EditExpenseCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.index.equals(command.index));
        assertTrue(expectedCommand.editExpenseDescriptor.equals(command.editExpenseDescriptor));
    }

    @Test
    public void parse_someFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = "2 d/Chair f/n v/40.00";
        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription("Chair").withIsFixed("n").withAmount(40.00).build();
        EditExpenseCommand expectedCommand = new EditExpenseCommand(targetIndex, descriptor);
        EditExpenseCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.index.equals(command.index));
        assertTrue(expectedCommand.editExpenseDescriptor.equals(command.editExpenseDescriptor));
    }

    @Test
    public void parse_oenFieldSpecified_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = "2 v/20.00";
        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withAmount(20).build();
        EditExpenseCommand expectedCommand = new EditExpenseCommand(targetIndex, descriptor);
        EditExpenseCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.index.equals(command.index));
        assertTrue(expectedCommand.editExpenseDescriptor.equals(command.editExpenseDescriptor));
    }

    @Test
    public void parse_multipleFieldsRepeated_takesLast() throws ParseException {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = "2 d/Chair f/n v/40 v/50.00 dt/10-1-2020 t/equipment t/others";
        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription("Chair").withIsFixed("n").withAmount(50.00).withDate("10-1-2020")
                .withTag("others").build();
        EditExpenseCommand expectedCommand = new EditExpenseCommand(targetIndex, descriptor);
        EditExpenseCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.index.equals(command.index));
        assertTrue(expectedCommand.editExpenseDescriptor.equals(command.editExpenseDescriptor));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = "2 d/Chair f/a f/n v/-10 v/50.00 dt/10-1-2020 t/others";
        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription("Chair").withIsFixed("n").withAmount(50.00).withDate("10-1-2020")
                .withTag("others").build();
        EditExpenseCommand expectedCommand = new EditExpenseCommand(targetIndex, descriptor);
        EditExpenseCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.index.equals(command.index));
        assertTrue(expectedCommand.editExpenseDescriptor.equals(command.editExpenseDescriptor));
    }
}
