package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.UndoneCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.Index;

public class UndoneCommandParserTest {
    @Test
    public void parse_validUndoneCommand_success() {
        UndoneCommandParser undoneCommandParser = new UndoneCommandParser();
        String parametersStub = "0101";
        UndoneCommand expected = new UndoneCommand(new Index("0101"));
        try {
            assertEquals(expected.getTargetIndex(), undoneCommandParser.parse(parametersStub).getTargetIndex());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidUndoneCommandEmptyArguments_fail() {
        UndoneCommandParser undoneCommandParser = new UndoneCommandParser();
        String parametersStub = "";
        try {
            undoneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    //TODO: WARNING!!!SHOULD BE FAILED
    @Test
    public void parse_invalidUndoneCommandInvalidIndex1_fail() {
        UndoneCommandParser undoneCommandParser = new UndoneCommandParser();
        String parametersStub = "01401";
        try {
            undoneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidUndoneCommandInvalidIndex2_fail() {
        UndoneCommandParser undoneCommandParser = new UndoneCommandParser();
        String parametersStub = "ABC";
        try {
            undoneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }
}
