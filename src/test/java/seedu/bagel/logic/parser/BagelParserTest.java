package seedu.bagel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.commands.AddCommand;
import seedu.bagel.logic.commands.ClearCommand;
import seedu.bagel.logic.commands.DeleteCommand;
import seedu.bagel.logic.commands.EditCommand;
import seedu.bagel.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.bagel.logic.commands.ExitCommand;
import seedu.bagel.logic.commands.FlipCommand;
import seedu.bagel.logic.commands.HelpCommand;
import seedu.bagel.logic.commands.ListCommand;
import seedu.bagel.logic.commands.ViewCommand;
import seedu.bagel.logic.commands.sort.SortCommand;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.testutil.EditFlashcardDescriptorBuilder;
import seedu.bagel.testutil.FlashcardBuilder;
import seedu.bagel.testutil.FlashcardUtil;

public class BagelParserTest {

    private final BagelParser parser = new BagelParser();

    @Test
    public void parseCommand_add() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FlashcardUtil.getAddCommand(flashcard));
        assertEquals(new AddCommand(flashcard), command);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " s/3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_flip() throws Exception {
        assertTrue(parser.parseCommand(FlipCommand.COMMAND_WORD) instanceof FlipCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " r/atitle") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " r/dtitle") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " r/tag") instanceof SortCommand);

    }

    //@Test
    //public void parseCommand_unrecognisedInput_throwsParseException() {
    //    assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE),
    //    () -> parser.parseCommand(""));
    //}

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
