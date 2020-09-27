package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.parameter.OptionalParameter;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.model.Model;

public class McGymmyParserTest { //TODO
    private final McGymmyParser parser = new McGymmyParser();
    private final McGymmyParser parserWithDummy = new McGymmyParser();
    {
        parserWithDummy.addCommand("dummy", DummyCommand::new);
    }

    @Test
    public void defaultCommands_added() {
        // if this breaks, you need to add the command in McGymmyParser.addDefaultCommands
        String[] commands = {"add", "edit", "delete", "clear", "find", "delete", "help", "list"};
        Set<String> registeredCommands = parser.getRegisteredCommands();
        for (String command : commands) {
            assertTrue(registeredCommands.contains(command));
        }
    }

    @Test
    public void parse_dummyCommand_correctType() throws Exception {
        Command command = parserWithDummy.parse("dummy -t1 abc -t2 cde -o1 fgh");
        assertTrue(command instanceof DummyCommand);
    }

    @Test
    public void parse_dummyCommandAllParameters_success() throws Exception {
        DummyCommand dummyCommand = (DummyCommand) parserWithDummy.parse("dummy -t1 abc -t2 cde -o1 fgh");
        assertEquals(dummyCommand.getParameter1(), "abc");
        assertEquals(dummyCommand.getParameter2(), "cde");
        assertEquals(dummyCommand.getOptionalParameter(), Optional.of("fgh"));
    }

    @Test
    public void parse_dummyCommandOmitOptional_success() throws Exception {
        DummyCommand dummyCommand = (DummyCommand) parserWithDummy.parse("dummy -t1 abc -t2 cde");
        assertEquals(dummyCommand.getParameter1(), "abc");
        assertEquals(dummyCommand.getParameter2(), "cde");
        assertEquals(dummyCommand.getOptionalParameter(), Optional.empty());
    }

    @Test
    public void parse_dummyCommandOmitRequired_failure() {
        assertThrows(ParseException.class, () -> parserWithDummy.parse("dummy -t1 abc"));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }
}

class DummyCommand extends Command {
    private Parameter<String> testParameter1 = CommandParserTestUtil.makeDummyParameter("test1", "t1");
    private Parameter<String> testParameter2 = CommandParserTestUtil.makeDummyParameter("test2", "t2");
    private OptionalParameter<String> testOptionalParameter =
        CommandParserTestUtil.makeDummyOptionalParameter("opt", "o1");
    {
        this.registerParameter(testParameter1);
        this.registerParameter(testParameter2);
        this.registerParameter(testOptionalParameter);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("nothing");
    }

    public String getParameter1() {
        return testParameter1.consume();
    }

    public String getParameter2() {
        return testParameter2.consume();
    }

    public Optional<String> getOptionalParameter() {
        return testOptionalParameter.getValue();
    }
}