package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contactcommands.AddCommand;
import seedu.address.logic.commands.contactcommands.DeleteCommand;
import seedu.address.logic.commands.contactcommands.EditCommand;
import seedu.address.logic.commands.contactcommands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.contactcommands.FindCommand;
import seedu.address.logic.commands.contactcommands.ListCommand;
import seedu.address.logic.commands.contactcommands.SortCommand;
import seedu.address.logic.commands.tagcommands.LinkDeleteCommand;
import seedu.address.logic.commands.tagcommands.TagAddCommand;
import seedu.address.logic.commands.tagcommands.TagDeleteCommand;
import seedu.address.logic.commands.tagcommands.TagEditCommand;
import seedu.address.logic.commands.tagcommands.TagFindCommand;
import seedu.address.logic.commands.tagcommands.TagListCommand;
import seedu.address.logic.commands.tagcommands.TagSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTagDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TagBuilder;
import seedu.address.testutil.TagUtil;

public class ProjactParserTest {

    private final ProjactParser parser = new ProjactParser();

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
        assertEquals(new FindCommand(new PersonNameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_tagAdd() throws Exception {
        Tag tag = new TagBuilder().build();
        TagAddCommand command = (TagAddCommand) parser.parseCommand(TagUtil.getTagAddCommand(tag));
        assertEquals(new TagAddCommand(tag), command);
    }

    @Test
    public void parseCommand_tagDelete() throws Exception {
        TagDeleteCommand command = (TagDeleteCommand) parser.parseCommand(
                TagDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new TagDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_tagEdit() throws Exception {
        Tag tag = new TagBuilder().build();
        TagEditCommand.EditTagDescriptor descriptor = new EditTagDescriptorBuilder(tag).build();
        TagEditCommand command = (TagEditCommand) parser.parseCommand(TagEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TAG.getOneBased() + " " + TagUtil.getEditTagDescriptorDetails(descriptor));
        assertEquals(new TagEditCommand(INDEX_FIRST_TAG, descriptor), command);
    }

    @Test
    public void parseCommand_tagList() throws Exception {
        assertTrue(parser.parseCommand(TagListCommand.COMMAND_WORD) instanceof TagListCommand);
        assertTrue(parser.parseCommand(TagListCommand.COMMAND_WORD + " 3") instanceof TagListCommand);
    }

    @Test
    public void parseCommand_tagFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        TagFindCommand command = (TagFindCommand) parser.parseCommand(
                TagFindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new TagFindCommand(new TagNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_tagSort() throws Exception {
        assertTrue(parser.parseCommand(TagSortCommand.COMMAND_WORD) instanceof TagSortCommand);
        assertTrue(parser.parseCommand(TagSortCommand.COMMAND_WORD + " 3") instanceof TagSortCommand);
    }

    @Test
    public void parseCommand_sortContact() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 3") instanceof SortCommand);

    }

    @Test
    public void parseCommand_linkDelete() throws Exception {
        LinkDeleteCommand command = (LinkDeleteCommand) parser.parseCommand(
                LinkDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new LinkDeleteCommand(INDEX_FIRST_PERSON), command);
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
