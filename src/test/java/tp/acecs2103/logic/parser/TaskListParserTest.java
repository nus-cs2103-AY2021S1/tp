package tp.acecs2103.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import tp.acecs2103.logic.commands.AddCommand;
import tp.acecs2103.logic.commands.DeadlineCommand;
import tp.acecs2103.logic.commands.DeleteCommand;
import tp.acecs2103.logic.commands.DoneCommand;
import tp.acecs2103.logic.commands.EditCommand;
import tp.acecs2103.logic.commands.ExitCommand;
import tp.acecs2103.logic.commands.FilterCommand;
import tp.acecs2103.logic.commands.FindCommand;
import tp.acecs2103.logic.commands.HelpCommand;
import tp.acecs2103.logic.commands.HomeCommand;
import tp.acecs2103.logic.commands.ListCommand;
import tp.acecs2103.logic.commands.UndoneCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;

public class TaskListParserTest {
    @Test
    public void taskListParser_parseCommandAddValid1_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 r/release a/Ip";
        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("CyberPunk2077"), null,
                new CustomizedDeadline("2020-12-10", LocalDate.parse("2020-12-10")),
                new Remark("release"), true, false);
        AddCommand expected;
        try {
            expected = new AddCommand(task);
            try {
                assertEquals(expected, parser.parseCommand(parametersStub));
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
    public void taskListParser_parseCommandAddValid2_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "add i/0101 w/1 d/CyberPunk2077 c/2020-12-10 a/Ip";
        Task task = new IP(new Index("0101"), new WeekNumber("1"), new Description("CyberPunk2077"), null,
                new CustomizedDeadline("2020-12-10", LocalDate.parse("2020-12-10")),
                new Remark(""), true, false);
        AddCommand expected;
        try {
            expected = new AddCommand(task);
            try {
                assertEquals(expected, parser.parseCommand(parametersStub));
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
    public void taskListParser_parseCommandEditValid_success() {
        TaskListParser parser = new TaskListParser();
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
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandDeleteValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "delete 0101";
        DeleteCommand expected = new DeleteCommand(new Index("0101"));
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandFindValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "find testtest";
        FindCommand expected = new FindCommand("testtest");
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandExitValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "exit";
        ExitCommand expected = new ExitCommand();
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandDeadlineValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "deadline i/0101 c/2020-08-17";
        DeadlineCommand expected = new DeadlineCommand(new Index("0101"),
                new CustomizedDeadline("2020-08-17", LocalDate.parse("2020-08-17")));
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void taskListParser_parseCommandHelpValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "help";
        HelpCommand expected = new HelpCommand();
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandListValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "list 5";
        ListCommand expected = new ListCommand(new WeekNumber("5"));
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandDoneValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "done 0101";
        DoneCommand expected = new DoneCommand(new Index("0101"));
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandUndoneValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "undone 0101";
        UndoneCommand expected = new UndoneCommand(new Index("0101"));
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandFilterValid1_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "filter w/4 k/pending l/official";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("pending", "official", new WeekNumber("4"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandFilterValid2_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "filter k/pending l/official";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("pending", "official");
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandFilterValid3_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "filter w/4 k/done";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("done", new WeekNumber("4"));
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandFilterValid4_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "filter k/done";
        FilterCommand expected = null;
        try {
            expected = new FilterCommand("done");
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void taskListParser_parseCommandHomeValid_success() {
        TaskListParser parser = new TaskListParser();
        String parametersStub = "home";
        HomeCommand expected = new HomeCommand();
        try {
            assertEquals(expected, parser.parseCommand(parametersStub));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}
