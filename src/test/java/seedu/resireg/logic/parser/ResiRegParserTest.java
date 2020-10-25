package seedu.resireg.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.commands.CommandResult;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

public class ResiRegParserTest {
    private static final String MOCK_COMMAND_WORD = "command";

    private final ResiRegParser mockParser;

    private static class MockCommand extends Command {
        private final String userInput;

        MockCommand(String userInput) {
            this.userInput = userInput;
        }

        @Override
        public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
            throw new CommandException("unimplemented stub method");
        }

        public String getUserInput() {
            return userInput;
        }
    }

    ResiRegParserTest() {
        Map<String, Parser<Command>> map = new HashMap<>();
        map.put(MOCK_COMMAND_WORD, MockCommand::new);
        mockParser = new ResiRegParser(map);
    }

    @Test
    public void parseCommand_validCommandWordNoArgs() throws ParseException {
        assertEquals("", ((MockCommand) mockParser.parseCommand(MOCK_COMMAND_WORD)).getUserInput());
    }

    @Test
    public void parseCommand_validCommandWordWithArgs() throws ParseException {
        assertEquals(" args", ((MockCommand) mockParser.parseCommand(MOCK_COMMAND_WORD + " args")).getUserInput());
        assertEquals(" a1 a2", ((MockCommand) mockParser.parseCommand(MOCK_COMMAND_WORD + " a1 a2")).getUserInput());
    }

    @Test
    public void parseCommand_validCommandWordWithArgsTrailingSpaces() throws ParseException {
        // check that trailing spaces are removed
        assertEquals(" args", ((MockCommand) mockParser.parseCommand(MOCK_COMMAND_WORD + " args  ")).getUserInput());
    }


    @Test
    public void parseCommand_invalidCommandWord() {
        assertThrows(ParseException.class, () -> mockParser.parseCommand("nonexistentCommand"));
    }

    @Test
    public void parseCommand_invalidCommandWordSubset() {
        assertThrows(ParseException.class, () -> mockParser.parseCommand(MOCK_COMMAND_WORD + "asdfj"));
        assertThrows(ParseException.class, () -> mockParser.parseCommand("asdfj" + MOCK_COMMAND_WORD));
    }
}
