package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_DETAIL_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAdditionalDetailCommand;
import seedu.address.logic.commands.DeleteAdditionalDetailCommand;
import seedu.address.logic.commands.EditAdditionalDetailCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.admin.AdditionalDetail;


public class AdditionalDetailCommandParserTest {

    private final AdditionalDetailCommandParser parser = new AdditionalDetailCommandParser();

    @Test
    public void parseAdditionalDetailCommand_add() throws Exception {
        String userInput = String.format("add %s %s%s", INDEX_FIRST_PERSON.getOneBased(),
                PREFIX_TEXT, VALID_ADDITIONAL_DETAILS_AMY);
        AddAdditionalDetailCommand command = (AddAdditionalDetailCommand) parser
                .parseAdditionalDetailCommand(userInput);
        assertEquals(new AddAdditionalDetailCommand(INDEX_FIRST_PERSON,
                new AdditionalDetail(VALID_ADDITIONAL_DETAILS_AMY)), command);
    }

    @Test
    public void parseAdditionalDetailCommand_delete() throws Exception {
        String userInput = String.format("delete %s %s%s", INDEX_FIRST_PERSON.getOneBased(),
                PREFIX_INDEX, INDEX_FIRST_PERSON.getOneBased());
        DeleteAdditionalDetailCommand command = (DeleteAdditionalDetailCommand) parser
                .parseAdditionalDetailCommand(userInput);
        assertEquals(new DeleteAdditionalDetailCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseAdditionalDetailCommand_edit() throws Exception {
        String userInput = String.format("edit %s %s%s %s%s", INDEX_FIRST_PERSON.getOneBased(),
                PREFIX_INDEX, INDEX_FIRST_PERSON.getOneBased(),
                PREFIX_TEXT, VALID_ADDITIONAL_DETAILS_AMY);
        EditAdditionalDetailCommand command = (EditAdditionalDetailCommand) parser
                .parseAdditionalDetailCommand(userInput);
        assertEquals(new EditAdditionalDetailCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON,
                new AdditionalDetail(VALID_ADDITIONAL_DETAILS_AMY)), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseAdditionalDetailCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_DETAIL_COMMAND, () ->
                parser.parseAdditionalDetailCommand("unknownCommand"));
    }

}
