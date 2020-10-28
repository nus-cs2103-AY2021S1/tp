package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SalesListCommand;
import seedu.address.logic.commands.SalesUpdateCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientListCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientResetAllCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientViewSingleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Drink;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_resetAllIngredients() throws Exception {
        assertTrue(parser.parseCommand(IngredientResetAllCommand.COMMAND_WORD) instanceof IngredientResetAllCommand);
        assertTrue(parser.parseCommand(IngredientResetAllCommand.COMMAND_WORD + " 3")
                instanceof IngredientResetAllCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_ingredientViewSingle() throws Exception {
        final String userInput = IngredientViewSingleCommand.COMMAND_WORD
                + " " + PREFIX_INGREDIENT + "Milk ";
        IngredientViewSingleCommand command = (IngredientViewSingleCommand) parser.parseCommand(userInput);
        assertTrue(command instanceof IngredientViewSingleCommand);
    }

    @Test
    public void parseCommand_ingredientList() throws Exception {
        assertTrue(parser.parseCommand(IngredientListCommand.COMMAND_WORD) instanceof IngredientListCommand);
        assertTrue(parser.parseCommand(IngredientListCommand.COMMAND_WORD + " 3") instanceof IngredientListCommand);
    }

    @Test
    public void parseCommand_salesUpdate() throws Exception {
        final int numBsbmSold = 80;
        final String userInput = SalesUpdateCommand.COMMAND_WORD + " " + PREFIX_BSBM + numBsbmSold;
        SalesUpdateCommand command = (SalesUpdateCommand) parser.parseCommand(userInput);
        HashMap<Drink, Integer> sales = new HashMap<>();
        sales.put(Drink.BSBM, numBsbmSold);
        assertEquals(new SalesUpdateCommand(sales), command);
    }

    @Test
    public void parseCommand_salesList() throws Exception {
        assertTrue(parser.parseCommand(SalesListCommand.COMMAND_WORD) instanceof SalesListCommand);
        assertTrue(parser.parseCommand(SalesListCommand.COMMAND_WORD + " 3") instanceof SalesListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
