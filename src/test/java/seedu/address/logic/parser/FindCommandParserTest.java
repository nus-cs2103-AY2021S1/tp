package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonHasTagsAndNamePredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Alice");
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("tag"));
        FindCommand expectedFindCommand =
                new FindCommand(new PersonHasTagsAndNamePredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice t/tag", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n t/tag\t", expectedFindCommand);
    }

    //TODO: Fix this test case
    //    @Test
    //    public void parse_multipleNames_returnsFindCommand() {
    //        // no leading and trailing whitespaces
    //        ArrayList<String> nameList = new ArrayList<>();
    //        nameList.add("Alice");
    //        nameList.add("Bob");
    //        nameList.add("Candy");
    //        ArrayList<Tag> tagList = new ArrayList<>();
    //        tagList.add(new Tag("tag"));
    //        FindCommand expectedFindCommand =
    //                new FindCommand(new PersonHasTagsAndNamePredicate(nameList, tagList));
    //        assertParseSuccess(parser, " n/Alice n/Bob n/Candy t/tag", expectedFindCommand);
    //    }

    @Test
    public void parse_singleNameAndNoTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Alice");
        ArrayList<Tag> tagList = new ArrayList<>();
        FindCommand expectedFindCommand =
                new FindCommand(new PersonHasTagsAndNamePredicate(nameList, tagList));
        assertParseSuccess(parser, " n/Alice ", expectedFindCommand);
    }
}
