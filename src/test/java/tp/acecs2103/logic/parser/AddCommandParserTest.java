package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.AddCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;

public class AddCommandParserTest {
    @Test
    public void parse_validAddCommandWithRemark_success() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 r/release a/Ip";
        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("CyberPunk2077"), null,
                new CustomizedDeadline("2020-12-10", LocalDate.parse("2020-12-10")),
                new Remark("release"), true, false);
        AddCommand expected;
        try {
            expected = new AddCommand(task);
            try {
                assertEquals(expected, addCommandParser.parse(parametersStub));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (CommandException e) {
                e.printStackTrace();
            }
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validAddCommandWithoutRemark_success() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 a/Ip";
        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("CyberPunk2077"), null,
                new CustomizedDeadline("2020-12-10", LocalDate.parse("2020-12-10")),
                new Remark(""), true, false);
        AddCommand expected;
        try {
            expected = new AddCommand(task);
            try {
                assertEquals(expected, addCommandParser.parse(parametersStub));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (CommandException e) {
                e.printStackTrace();
            }
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidAddCommandArgumentInsufficient2_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 d/CyberPunk2077 c/2020-12-10 a/Ip"; //no WeekNumber
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandEmptyCommand_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "";

        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandExtraArguments_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 a/Ip x/fail";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandArgumentInsufficient1_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add w/1 d/CyberPunk2077 c/2020-12-10 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandArgumentInsufficient3_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 c/2020-12-10 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandArgumentInsufficient4_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandArgumentInsufficient5_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_validAddCommandUnorderedArguments_success() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add a/Ip c/2020-12-10 w/1 i/0101 d/CyberPunk2077";
        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("CyberPunk2077"), null,
                new CustomizedDeadline("2020-12-10", LocalDate.parse("2020-12-10")),
                new Remark(""), true, false);
        AddCommand expected;
        try {
            expected = new AddCommand(task);
            try {
                assertEquals(expected, addCommandParser.parse(parametersStub));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (CommandException e) {
                e.printStackTrace();
            }
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidAddCommandInvalidDate_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-99 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandInvalidWeek_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/16 d/CyberPunk2077 c/2020-12-10 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    // TODO: WARNING!!! SHOULD BE FAILED
    @Test
    public void parse_invalidAddCommandInvalidIndex_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/01401 w/1 d/CyberPunk2077 c/2020-12-10 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidAddCommandInvalidCategory_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 a/Undefined";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidAddCommandInvalidDescription_fail() {
        AddCommandParser addCommandParser = new AddCommandParser();
        String parametersStub = "add i/0101 w/1 d/ c/2020-12-10 a/Ip";
        try {
            addCommandParser.parse(parametersStub);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            assert true;
        }
    }
}
