package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.DoneCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.Index;

public class DoneCommandParserTest {
    @Test
    public void parse_validDoneCommand_success() {
        DoneCommandParser doneCommandParser = new DoneCommandParser();
        String parametersStub = "0101";
        DoneCommand expected = new DoneCommand(new Index("0101"));
        try {
            assertEquals(expected.getTargetIndex(), doneCommandParser.parse(parametersStub).getTargetIndex());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidDoneCommandEmptyArguments_fail() {
        DoneCommandParser doneCommandParser = new DoneCommandParser();
        String parametersStub = "";
        try {
            doneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidDoneCommandInvalidIndex1_fail() {
        DoneCommandParser doneCommandParser = new DoneCommandParser();
        String parametersStub = "01401";
        try {
            doneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidDoneCommandInvalidIndex2_fail() {
        DoneCommandParser doneCommandParser = new DoneCommandParser();
        String parametersStub = "ABC";
        try {
            doneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidDoneCommandInvalidIndex3_fail() {
        DoneCommandParser doneCommandParser = new DoneCommandParser();
        String parametersStub = "0199";
        try {
            doneCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }
}
