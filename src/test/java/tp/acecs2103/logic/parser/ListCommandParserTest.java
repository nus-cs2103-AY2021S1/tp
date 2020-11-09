package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.ListCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.WeekNumber;

public class ListCommandParserTest {
    @Test
    public void parse_validListCommand1_success() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "1";
        ListCommand expected = new ListCommand(new WeekNumber("1"));
        try {
            assertEquals(expected.getWeekNumber(), listCommandParser.parse(parametersStub).getWeekNumber());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validListCommand2_success() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "13";
        ListCommand expected = new ListCommand(new WeekNumber("13"));
        try {
            assertEquals(expected.getWeekNumber(), listCommandParser.parse(parametersStub).getWeekNumber());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidListCommandInvalidWeekNumber1_fail() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "0";
        try {
            listCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidListCommandInvalidWeekNumber2_fail() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "14";
        try {
            listCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidListCommandInvalidWeekNumber3_fail() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "10000";
        try {
            listCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidListCommandNoArgument_fail() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String parametersStub = "";
        try {
            listCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }
}
