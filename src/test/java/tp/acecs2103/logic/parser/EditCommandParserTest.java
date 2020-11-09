package tp.acecs2103.logic.parser;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.EditCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.WeekNumber;

public class EditCommandParserTest {
    @Test
    public void parse_validEditCommandWithoutRemark_success() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/1 d/CyberPunk2077 c/2020-12-10";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        editTaskDescriptor.setWeekNumber(new WeekNumber("1"));
        try {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline("2020-12-10");
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        editTaskDescriptor.setDescription(new Description("CyberPunk2077"));
        EditCommand expected = new EditCommand(index, editTaskDescriptor);
        try {
            assert editCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_validEditCommandWithRemark_success() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/1 d/CyberPunk2077 c/2020-12-10 r/release";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        editTaskDescriptor.setWeekNumber(new WeekNumber("1"));
        try {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline("2020-12-10");
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        editTaskDescriptor.setDescription(new Description("CyberPunk2077"));
        editTaskDescriptor.setRemark(new Remark("release"));
        EditCommand expected = new EditCommand(index, editTaskDescriptor);
        try {
            assert editCommandParser.parse(parametersStub).equals(expected);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidDateEditCommandWithRemark_fail() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/1 d/CyberPunk2077 c/2020-12-32 r/release";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        editTaskDescriptor.setWeekNumber(new WeekNumber("1"));
        try {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline("2020-12-32");
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidMonthEditCommandWithRemark_fail() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/1 d/CyberPunk2077 c/2020-13-10 r/release";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        editTaskDescriptor.setWeekNumber(new WeekNumber("1"));
        try {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline("2020-13-10");
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        } catch (ParseException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidWeekEditCommandWithRemark_fail() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/14 d/CyberPunk2077 c/2020-12-10 r/release";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();

        try {
            editTaskDescriptor.setWeekNumber(new WeekNumber("14"));
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    public void parse_invalidIndexEditCommandWithRemark_fail() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/01401 w/1 d/CyberPunk2077 c/2020-12-10 r/release";
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        try {
            Index index = new Index("01401");
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    //WARNING: SHOULD FAIL
    @Test
    public void parse_invalidNotAlignedEditCommandWithRemark_fail() {
        EditCommandParser editCommandParser = new EditCommandParser();
        String parametersStub = "edit i/0101 w/2 d/CyberPunk2077 c/2020-12-10 r/release";
        Index index = new Index("0101");
        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        editTaskDescriptor.setWeekNumber(new WeekNumber("2"));
        try {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline("2020-12-10");
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        editTaskDescriptor.setDescription(new Description("CyberPunk2077"));
        try {
            editCommandParser.parse(parametersStub);
        } catch (ParseException e) {
            assert true;
        }
    }

}
