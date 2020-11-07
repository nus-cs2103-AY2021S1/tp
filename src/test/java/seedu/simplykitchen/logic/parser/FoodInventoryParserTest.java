package seedu.simplykitchen.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;
import seedu.simplykitchen.logic.commands.ClearCommand;
import seedu.simplykitchen.logic.commands.DeleteCommand;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.ExitCommand;
import seedu.simplykitchen.logic.commands.ExpiredCommand;
import seedu.simplykitchen.logic.commands.FindCommand;
import seedu.simplykitchen.logic.commands.HelpCommand;
import seedu.simplykitchen.logic.commands.ListCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.TagSearchPredicate;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;
import seedu.simplykitchen.testutil.FoodBuilder;
import seedu.simplykitchen.testutil.FoodUtil;

public class FoodInventoryParserTest {

    private final FoodInventoryParser parser = new FoodInventoryParser();

    @Test
    public void parseCommand_add() throws Exception {
        Food food = new FoodBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FoodUtil.getAddCommand(food));
        assertEquals(new AddCommand(food), command);
    }

    @Test
    public void parseCommand_changeQuantity() throws Exception {
        double amount = +1.00;
        ChangeQuantityCommand command = (ChangeQuantityCommand) parser
                .parseCommand(FoodUtil.getChangeQuantityCommand(amount));
        assertEquals(new ChangeQuantityCommand(INDEX_FIRST_FOOD, amount), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FOOD.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_FOOD), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Food food = new FoodBuilder().build();
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(food).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FOOD.getOneBased() + " " + FoodUtil.getEditFoodDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FOOD, descriptor), command);
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
                FindCommand.COMMAND_WORD + " d/" + keywords.stream().collect(Collectors.joining(" ")));
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(new DescriptionContainsKeywordsPredicate(keywords));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();
        assertEquals(new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate),
                command);
    }

    @Test
    public void parseCommand_expired() throws Exception {
        assertTrue(parser.parseCommand(ExpiredCommand.COMMAND_WORD) instanceof ExpiredCommand);
        assertTrue(parser.parseCommand(ExpiredCommand.COMMAND_WORD + " 3") instanceof ExpiredCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
