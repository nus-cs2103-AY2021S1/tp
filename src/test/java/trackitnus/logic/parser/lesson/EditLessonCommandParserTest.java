package trackitnus.logic.parser.lesson;

import static trackitnus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.lesson.EditLessonCommand;

public class EditLessonCommandParserTest {
    private final EditLessonCommandParser parser = new EditLessonCommandParser();

    @Test
    public void parse_noFieldsProvided_failure() {
        assertParseFailure(parser, "", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            EditLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_justModuleProvided_failure() {
        assertParseFailure(parser, "m/CS1231S n/lecture", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            EditLessonCommand.MESSAGE_USAGE));
    }
}
