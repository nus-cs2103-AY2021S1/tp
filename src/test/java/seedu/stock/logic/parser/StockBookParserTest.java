package seedu.stock.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LIST_TYPE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.ClearCommand;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.commands.ExitCommand;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.commands.ListCommand;
import seedu.stock.logic.commands.SuggestionCommand;
import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.LocationContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SerialNumberContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SourceContainsKeywordsPredicate;
import seedu.stock.testutil.StockBuilder;
import seedu.stock.testutil.StockUtil;
import seedu.stock.testutil.UpdateStockDescriptorBuilder;

public class StockBookParserTest {
    private final StockBookParser parser = new StockBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Stock stock = new StockBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StockUtil.getAddCommand(stock));
        assertEquals(new AddCommand(stock), command);
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertFalse(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_SERIAL_NUMBER + SERIAL_NUMBER_FIRST_STOCK);
        Set<SerialNumber> serialNumberSet = new LinkedHashSet<>();
        serialNumberSet.add(SERIAL_NUMBER_FIRST_STOCK);

        assertEquals(new DeleteCommand(serialNumberSet), command);
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_updateIncrementedQuantity() throws Exception {
        Stock stock = new StockBuilder().build();
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder(stock).build();

        //change to required format
        descriptor.setQuantityAdder(new QuantityAdder("50"));
        descriptor.setQuantity(null);

        //build serial numbers to change
        String serialNumbers = descriptor.getSerialNumbers().stream().map(SerialNumber::toString)
                                .reduce("", (x, y) -> x + " sn/" + y);

        //builds command using user input
        String userInput = UpdateCommand.COMMAND_WORD + " " + serialNumbers
                + " " + StockUtil.getUpdateStockDescriptorDetailsIncrementedQuantity(descriptor);

        UpdateCommand incrementedQuantityCommand = (UpdateCommand) parser.parseCommand(userInput);

        assertEquals(new UpdateCommand(descriptor), incrementedQuantityCommand);
    }

    @Test
    public void parseCommand_updateNewQuantity() throws Exception {
        Stock stock = new StockBuilder().build();
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder(stock).build();

        //change to required format
        descriptor.setQuantity(new Quantity("50"));
        descriptor.setQuantityAdder(null);

        //build serial numbers to change
        String serialNumbers = descriptor.getSerialNumbers().stream().map(SerialNumber::toString)
                .reduce("", (x, y) -> x + " sn/" + y);

        //builds command using user input
        String userInput = UpdateCommand.COMMAND_WORD
                + serialNumbers + " " + StockUtil.getUpdateStockDescriptorDetailsNewQuantity(descriptor);

        UpdateCommand newQuantityCommand = (UpdateCommand) parser.parseCommand(userInput);

        assertEquals(new UpdateCommand(descriptor), newQuantityCommand);
    }

    @Test
    public void parseCommand_nonQuantityUpdates() throws Exception {
        Stock stock = new StockBuilder().build();
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder(stock).build();

        //change to required format
        descriptor.setQuantity(null);
        descriptor.setQuantityAdder(null);

        //build serial numbers to change
        String serialNumbers = descriptor.getSerialNumbers().stream().map(SerialNumber::toString)
                .reduce("", (x, y) -> x + " sn/" + y);

        //builds command using user input
        String userInput = UpdateCommand.COMMAND_WORD
                + serialNumbers + " " + StockUtil.getUpdateStockDescriptorDetailsNonQuantity(descriptor);

        UpdateCommand nonQuantityCommand = (UpdateCommand) parser.parseCommand(userInput);

        assertEquals(new UpdateCommand(descriptor), nonQuantityCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertFalse(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        //sample values for each attribute
        String sampleName = "name";
        String sampleSource = "source";
        String sampleSerialNumber = "serialnumber1";
        String sampleLocation = "location";

        String forName = " " + PREFIX_NAME + sampleName;
        String forSource = " " + PREFIX_SOURCE + sampleSource;
        String forSerialNumber = " " + PREFIX_SERIAL_NUMBER + sampleSerialNumber;
        String forLocation = " " + PREFIX_LOCATION + sampleLocation;

        //adding all the predicates
        List<FieldContainsKeywordsPredicate> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(Collections.singletonList(sampleName)));
        predicates.add(new LocationContainsKeywordsPredicate(Collections.singletonList(sampleLocation)));
        predicates.add(new SourceContainsKeywordsPredicate(Collections.singletonList(sampleSource)));
        predicates.add(new SerialNumberContainsKeywordsPredicate(Collections.singletonList(sampleSerialNumber)));

        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + forName
                        + forLocation + forSource + forSerialNumber);

        assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
        assertEquals(new FindCommand(predicates), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertFalse(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(
                ListCommand.COMMAND_WORD + " " + PREFIX_LIST_TYPE + "all") instanceof ListCommand);
        assertTrue(parser.parseCommand(
                ListCommand.COMMAND_WORD + " " + PREFIX_LIST_TYPE + "bookmark") instanceof ListCommand);
        assertTrue(parser.parseCommand(
                ListCommand.COMMAND_WORD + " " + PREFIX_LIST_TYPE + "low") instanceof ListCommand);
        assertFalse(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_returnsSuggestionCommand() throws ParseException {
        //suggestion command given otherwise
        assertTrue(parser.parseCommand("hello") instanceof SuggestionCommand);
        assertTrue(parser.parseCommand("31231") instanceof SuggestionCommand);
        assertTrue(parser.parseCommand("#*das") instanceof SuggestionCommand);
    }
}
