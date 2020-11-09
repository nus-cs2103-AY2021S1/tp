package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_FIND_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.module.CompoundFindPredicate;
import seedu.address.model.module.Module;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_FIND_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<String> keywords = Arrays.asList("CS2103T", "CS3216");
        Predicate<Module> expectedPredicate = new CompoundFindPredicate(keywords);

        FindCommand expectedFindCommand = new FindCommand(expectedPredicate);
        assertParseSuccess(parser, "CS2103T CS3216", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T \n \t CS3216  \t", expectedFindCommand);
    }

}
