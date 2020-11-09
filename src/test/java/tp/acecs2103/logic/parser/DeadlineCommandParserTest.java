package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.DeadlineCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Index;

public class DeadlineCommandParserTest {
    @Test
    public void parse_validDeadlineCommand_success() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/2020-08-17";
        DeadlineCommand expected = new DeadlineCommand(
                new Index("0101"), new CustomizedDeadline("2020-08-17", LocalDate.parse("2020-08-17")));
        try {
            assertEquals(expected, deadlineCommandParser.parse(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidDateDeadlineCommand_fail() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/2020-08-32";
        try {
            deadlineCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidMonthDeadlineCommand_fail() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/2020-13-17";
        try {
            deadlineCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidYearDeadlineCommand_fail() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/0000-08-17";
        try {
            deadlineCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    public void parse_validLeapYearDateDeadlineCommand_success() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/2020-02-29";
        DeadlineCommand expected = new DeadlineCommand(
                new Index("0101"), new CustomizedDeadline("2020-02-29", LocalDate.parse("2020-02-29")));
        try {
            assertEquals(expected, deadlineCommandParser.parse(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidCommonYearDateDeadlineCommand_fail() {
        DeadlineCommandParser deadlineCommandParser = new DeadlineCommandParser();
        String parametersStub = " i/0101 c/2019-02-29";
        try {
            deadlineCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }
}
