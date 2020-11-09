package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.CHECKMC_COMMAND_WORD;
import static seedu.address.commons.core.Messages.CLEAR_COMMAND_WORD;
import static seedu.address.commons.core.Messages.DELETE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.EDIT_COMMAND_WORD;
import static seedu.address.commons.core.Messages.EXIT_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FIND_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FORCE_CLEAR_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FORCE_DELETE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.GEM_COMMAND_WORD;
import static seedu.address.commons.core.Messages.HELP_COMMAND_WORD;
import static seedu.address.commons.core.Messages.LIST_COMMAND_WORD;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_FIELD;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.REQUIRED_COMMAND_WORD;
import static seedu.address.commons.core.Messages.SCIENCE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.SEARCH_COMMAND_WORD;
import static seedu.address.commons.core.Messages.TAGS_COMMAND_WORD;
import static seedu.address.commons.core.Messages.YES_COMMAND_WORD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CheckMcCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ForceClearCommand;
import seedu.address.logic.commands.ForceDeleteCommand;
import seedu.address.logic.commands.GemCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RequiredCommand;
import seedu.address.logic.commands.ScienceCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.TagsCommand;
import seedu.address.logic.commands.YesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CompoundFindPredicate;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;

public class GradPadParserTest {

    private final GradPadParser parser = new GradPadParser();

    @Test
    public void parseCommand_add() throws Exception {
        Module module = new ModuleBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ModuleUtil.getAddCommand(module));
        assertEquals(new AddCommand(module), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(CLEAR_COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(CLEAR_COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_forceClear() throws Exception {
        assertTrue(parser.parseCommand(FORCE_CLEAR_COMMAND_WORD) instanceof ForceClearCommand);
        assertTrue(parser.parseCommand(FORCE_CLEAR_COMMAND_WORD + " 3") instanceof ForceClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DELETE_COMMAND_WORD + " " + CODE_FIRST_MODULE.toString());
        assertEquals(new DeleteCommand(CODE_FIRST_MODULE), command);
    }

    @Test
    public void parseCommand_forceDelete() throws Exception {
        ForceDeleteCommand command = (ForceDeleteCommand) parser.parseCommand(
                FORCE_DELETE_COMMAND_WORD + " " + CODE_FIRST_MODULE.toString());
        assertEquals(new ForceDeleteCommand(CODE_FIRST_MODULE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Module module = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditCommand command = (EditCommand) parser.parseCommand(EDIT_COMMAND_WORD + " "
                + CODE_FIRST_MODULE + " " + ModuleUtil.getEditModuleDescriptorDetails(descriptor));
        assertEquals(new EditCommand(CODE_FIRST_MODULE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(EXIT_COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(EXIT_COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FIND_COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new CompoundFindPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HELP_COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HELP_COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(LIST_COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(LIST_COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_checkMc() throws Exception {
        assertTrue(parser.parseCommand(CHECKMC_COMMAND_WORD) instanceof CheckMcCommand);
        assertTrue(parser.parseCommand(CHECKMC_COMMAND_WORD + " 3") instanceof CheckMcCommand);
    }

    @Test
    public void parseCommand_tags() throws Exception {
        assertTrue(parser.parseCommand(TAGS_COMMAND_WORD) instanceof TagsCommand);
        assertTrue(parser.parseCommand(TAGS_COMMAND_WORD + " 3") instanceof TagsCommand);
    }

    @Test
    public void parseCommand_search() throws Exception {
        SearchCommand command = (SearchCommand) parser.parseCommand(
                SEARCH_COMMAND_WORD + " " + CODE_FIRST_MODULE.toString());
        assertEquals(new SearchCommand(CODE_FIRST_MODULE), command);
    }

    @Test
    public void parseCommand_required() throws Exception {
        assertTrue(parser.parseCommand(REQUIRED_COMMAND_WORD) instanceof RequiredCommand);
        assertTrue(parser.parseCommand(REQUIRED_COMMAND_WORD + " 3") instanceof RequiredCommand);
    }

    @Test
    public void parseCommand_science() throws Exception {
        assertTrue(parser.parseCommand(SCIENCE_COMMAND_WORD) instanceof ScienceCommand);
        assertTrue(parser.parseCommand(SCIENCE_COMMAND_WORD + " 3") instanceof ScienceCommand);
    }

    @Test
    public void parseCommand_gem() throws Exception {
        assertTrue(parser.parseCommand(GEM_COMMAND_WORD) instanceof GemCommand);
        assertTrue(parser.parseCommand(GEM_COMMAND_WORD + " 3") instanceof GemCommand);
    }

    @Test
    public void parseCommand_yes() throws Exception {
        assertTrue(parser.parseCommand(YES_COMMAND_WORD) instanceof YesCommand);
        assertTrue(parser.parseCommand(YES_COMMAND_WORD + " 3") instanceof YesCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_EMPTY_FIELD, ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
