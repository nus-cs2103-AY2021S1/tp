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
    public void parse_paramsWithEmptyArgs_throwsParseException() {
        // All no args
        assertParseFailure(parser, " m/ n/ i/ ", MESSAGE_EMPTY_KEYWORD);
        // "m/" no args
        assertParseFailure(parser, " m/ n/Software i/Damith", MESSAGE_EMPTY_KEYWORD);
        // "n/" no args
        assertParseFailure(parser, " m/CS2103 n/Software i/", MESSAGE_EMPTY_KEYWORD);
        // "i/" no args
        assertParseFailure(parser, " m/CS2103 n/Software i/", MESSAGE_EMPTY_KEYWORD);
        // "m/, i/" no args
        assertParseFailure(parser, " m/ n/Software i/", MESSAGE_EMPTY_KEYWORD);
        // "n/, i/" no args
        assertParseFailure(parser, " m/CS2103 n/ i/", MESSAGE_EMPTY_KEYWORD);
    }

    @Test
    public void parse_argsWithDuplicateParameters_throwsParseException() {
        // Duplicate "n/"
        assertParseFailure(parser, " m/CS2103 n/Software n/Engineering i/Damith ",
                String.format(MESSAGE_DUPLICATE_PREFIX, "n/"));
        // Duplicate "m/"
        assertParseFailure(parser, " m/CS2103 m/CS2100 n/Software i/Damith ",
                String.format(MESSAGE_DUPLICATE_PREFIX, "m/"));
        // Duplicate "i/"
        assertParseFailure(parser, " m/CS2103 n/Software Engineering i/Damith i/John ",
                String.format(MESSAGE_DUPLICATE_PREFIX, "i/"));
        // Duplicate "n/, i/". Error message should only show the first instance of the duplicate parameter.
        assertParseFailure(parser, " m/CS2103 n/Software n/Engineering i/Damith i/John ",
                String.format(MESSAGE_DUPLICATE_PREFIX, "n/"));
    }

    @Test
    public void parse_moduleParamWithArgAsSentence_throwsParseException() {
        assertParseFailure(parser, " m/CS21 03 n/Software Engineering i/Damith ",
                MESSAGE_INVALID_MODULE_CODE_KEYWORD);
    }

    @Test
    public void parse_validParamsWithSomeArgs_returnsFindCommand() {
        ModuleCodeContainsKeywordsPredicate codePredicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("Damith"));

        List<Predicate<Module>> predicateList1 = Collections.singletonList(codePredicate);
        List<Predicate<Module>> predicateList2 = Collections.singletonList(namePredicate);
        List<Predicate<Module>> predicateList3 = Collections.singletonList(instructorPredicate);
        List<Predicate<Module>> predicateList4 = Arrays.asList(codePredicate, namePredicate);
        List<Predicate<Module>> predicateList5 = Arrays.asList(namePredicate, instructorPredicate);

        FindModCommand expectedFindCommand = new FindModCommand(predicateList1);
        assertParseSuccess(parser, " m/CS2103", expectedFindCommand);

        expectedFindCommand = new FindModCommand(predicateList2);
        assertParseSuccess(parser, " n/Software Engineering", expectedFindCommand);

        expectedFindCommand = new FindModCommand(predicateList3);
        assertParseSuccess(parser, " i/Damith", expectedFindCommand);

        expectedFindCommand = new FindModCommand(predicateList4);
        assertParseSuccess(parser, " m/CS2103 n/Software Engineering", expectedFindCommand);
        // Same arguments, different arrangements
        assertParseSuccess(parser, " n/Software Engineering m/CS2103", expectedFindCommand);

        expectedFindCommand = new FindModCommand(predicateList5);
        assertParseSuccess(parser, " n/Software Engineering i/Damith", expectedFindCommand);
    }


    @Test
    public void parse_validParamsWithAllArgs_returnsFindModCommand() {
        ModuleCodeContainsKeywordsPredicate codePredicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("Damith"));

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand expectedFindCommand = new FindModCommand(predicateList);

        // No leading and trailing whitespaces
        assertParseSuccess(parser, " m/CS2103 n/Software Engineering i/Damith", expectedFindCommand);
        // leading and trailing whitespaces
        assertParseSuccess(parser, "\t m/CS2103  \n n/Software \n Engineering \t i/Damith   ", expectedFindCommand);
        // Same vlaid arguments, different arrangements
        assertParseSuccess(parser, " n/Software Engineering m/CS2103 i/Damith", expectedFindCommand);
    }

}
