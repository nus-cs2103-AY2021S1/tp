package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModCommand;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;

class FindModCommandParserTest {
    private FindModCommandParser parser = new FindModCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_argsWithEmptyParameters_throwsParseException() {
        assertParseFailure(parser, " m/ n/ i/ ", "String can't be empty!");
    }
    @Test
    public void parse_argsWithDuplicateParameters_throwsParseException() {
        assertParseFailure(parser, " m/CS2103 n/Software n/Engineering i/ ",
                "Duplicate prefix! Might have to implement a DuplicatePrefixException class!");
    }
    @Test
    public void parse_argsWithModuleParameterAsSentence_throwsParseException() {
        assertParseFailure(parser, " m/CS21 03 n/Software Engineering i/ ",
                "Module code parameter should be a single word");
    }
    @Test
    public void parse_validArgs_returnsFindModCommand() {
        ModuleCodeContainsKeywordsPredicate codePredicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("Damith"));
        FindModCommand expectedFindCommand = new FindModCommand(codePredicate, namePredicate, instructorPredicate);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " m/CS2103 n/Software Engineering i/Damith", expectedFindCommand);

        assertParseSuccess(parser, "\t m/CS2103  \n n/Software \n Engineering \t i/Damith   ", expectedFindCommand);
    }

}
