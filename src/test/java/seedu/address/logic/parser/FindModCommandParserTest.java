package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_KEYWORD;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CODE_KEYWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.predicates.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleNameContainsKeywordsPredicate;

class FindModCommandParserTest {
    private FindModCommandParser parser = new FindModCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_argsWithEmptyParameters_throwsParseException() {
        assertParseFailure(parser, " m/ n/ i/ ", MESSAGE_EMPTY_KEYWORD);
    }
    @Test
    public void parse_argsWithDuplicateParameters_throwsParseException() {
        assertParseFailure(parser, " m/CS2103 n/Software n/Engineering i/ ",
                String.format(MESSAGE_DUPLICATE_PREFIX, "n/"));
    }
    @Test
    public void parse_argsWithModuleParameterAsSentence_throwsParseException() {
        assertParseFailure(parser, " m/CS21 03 n/Software Engineering i/ ",
                MESSAGE_INVALID_MODULE_CODE_KEYWORD);
    }
    @Test
    public void parse_validArgs_returnsFindModCommand() {
        ModuleCodeContainsKeywordsPredicate codePredicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("Damith"));
        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand expectedFindCommand = new FindModCommand(predicateList);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " m/CS2103 n/Software Engineering i/Damith", expectedFindCommand);

        assertParseSuccess(parser, "\t m/CS2103  \n n/Software \n Engineering \t i/Damith   ", expectedFindCommand);
    }

}
