package seedu.address.logic.parser.contactlistparsers;

/*import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;*/

import org.junit.jupiter.api.Test;

/*import seedu.address.logic.commands.contactlistcommands.SortContactCommand;
import seedu.address.model.contact.ContactComparatorByName;*/

public class SortContactParserTest {
    private SortContactParser parser = new SortContactParser();

    @Test
    public void parse_emptyArg_parseSuccess() {
        /*SortContactCommand expectedSortContactCommand =
                new SortContactCommand(new ContactComparatorByName());
        assertParseSuccess(parser, "", expectedSortContactCommand);*/
    }

    @Test
    public void parse_validArgsSpecified_returnsSortCommand() {
        /*SortContactCommand expectedSortContactCommand =
                new SortContactCommand(new ContactComparatorByName().reversed());
        assertParseSuccess(parser, "r", expectedSortContactCommand);*/
    }

    @Test
    public void parse_invalidArgsSpecified_throwsParseException() {
        /*assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortContactCommand.MESSAGE_USAGE));*/
    }
}
