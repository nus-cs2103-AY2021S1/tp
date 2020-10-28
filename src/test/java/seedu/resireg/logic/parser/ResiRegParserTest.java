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

    private final Map<String, Parser<Command>> mockMap;

    ResiRegParserTest() {
        mockMap = new HashMap<>();
        mockMap.put(MOCK_COMMAND_WORD, MockCommand::new);
    }

    @Test
    public void parseCommand_validCommandWordNoArgs() throws ParseException {
        assertEquals("", ((MockCommand) ResiRegParser.parseCommand(MOCK_COMMAND_WORD, mockMap)).getUserInput());
    }

    @Test
    public void parseCommand_validCommandWordWithArgs() throws ParseException {
        assertEquals(" args", ((MockCommand) ResiRegParser.parseCommand(MOCK_COMMAND_WORD + " args", mockMap))
                .getUserInput());
        assertEquals(" a1 a2", ((MockCommand) ResiRegParser.parseCommand(MOCK_COMMAND_WORD + " a1 a2", mockMap))
                .getUserInput());
    }

    @Test
    public void parseCommand_validCommandWordWithArgsTrailingSpaces() throws ParseException {
        // check that trailing spaces are removed
        assertEquals(" args", ((MockCommand) ResiRegParser.parseCommand(MOCK_COMMAND_WORD + " args  ", mockMap))
                .getUserInput());
    }


    @Test
    public void parseCommand_invalidCommandWord() {
        assertThrows(ParseException.class, () -> ResiRegParser.parseCommand("nonexistentCommand", mockMap));
    }

    @Test
    public void parseCommand_invalidCommandWordSubset() {
        assertThrows(ParseException.class, () -> ResiRegParser.parseCommand(MOCK_COMMAND_WORD + "asdfj", mockMap));
        assertThrows(ParseException.class, () -> ResiRegParser.parseCommand("asdfj" + MOCK_COMMAND_WORD, mockMap));
    }

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

}
