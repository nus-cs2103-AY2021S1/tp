package trackitnus.logic.parser.task;

import static trackitnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.task.AddTaskCommand;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    private final Name name = new Name("Sample");
    private final LocalDate date = LocalDate.parse("12/12/2020", Task.FORMATTER);
    private final Code code = new Code("CS1100");
    private final String remark = "Test";

    @Test
    public void parse_allFieldsPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, code, remark));

        assertParseSuccess(parser, " n/Sample d/12/12/2020 m/CS1100 r/Test", expectedCommand);
    }

    @Test
    public void parse_codeNotPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, null, remark));

        assertParseSuccess(parser, " n/Sample d/12/12/2020 r/Test", expectedCommand);
    }

    @Test
    public void parse_remarkNotPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, code, ""));

        assertParseSuccess(parser, " n/Sample d/12/12/2020 m/CS1100", expectedCommand);
    }

    @Test
    public void parse_codeAndRemarkNotPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, null, ""));

        assertParseSuccess(parser, " n/Sample d/12/12/2020", expectedCommand);
    }
}
