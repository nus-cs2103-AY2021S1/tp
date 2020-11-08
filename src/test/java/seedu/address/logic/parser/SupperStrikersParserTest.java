package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_AMBIGUOUS_COMMAND;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeletePresetCommand;
import seedu.address.logic.commands.MenuCommand;
import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.commands.ProfileCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SubmitCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.TotalCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.commands.VendorCommand;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.PriceWithinRangePredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;

class SupperStrikersParserTest {
    private SupperStrikersParser supperStrikersParser = new SupperStrikersParser();

    @Test
    void parseCommand_addCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("add 2 3"), new AddCommand(Index.fromOneBased(2), 3));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_tagCommand_success() {
        try {
            Tag tag = new Tag("no ice");
            assertEquals(supperStrikersParser.parseCommand("tag 2 no ice"), new TagCommand(Index.fromOneBased(2), tag));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_untagCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("untag 3"), new UntagCommand(Index.fromOneBased(3)));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_sortCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("sort n"), new SortCommand("n", "t"));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_priceCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("price < 5"),
                    new PriceCommand(new PriceWithinRangePredicate(Inequality.LESSER_THAN, 5)));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_menuCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("menu"), new MenuCommand());
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_totalCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("total"), new TotalCommand());
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_submitCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("submit"), new SubmitCommand());
        } catch (ParseException e) {
            assertTrue(false);
        }
    }


    @Test
    void parseCommand_undoCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("undo"), new UndoCommand());
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_vendorCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("vendor"), new VendorCommand());
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_presetCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("preset delete abc"),
                    new DeletePresetCommand(Optional.of(new Name("abc"))));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_profileCommand_success() {
        try {
            assertEquals(supperStrikersParser.parseCommand("profile 88888888 RVRC Zone C"),
                    new ProfileCommand(new Phone("88888888"), new Address("RVRC Zone C")));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    void parseCommand_ambiguousCommand_parseException() {
        try {
            supperStrikersParser.parseCommand("u");
            assertTrue(false);
        } catch (ParseException e) {
            ArrayList<String> matchingCommands = new ArrayList<>();
            matchingCommands.add("untag");
            matchingCommands.add("undo");
            assertEquals(e, new ParseException(String.format(MESSAGE_AMBIGUOUS_COMMAND, matchingCommands)));
        }
    }
}
