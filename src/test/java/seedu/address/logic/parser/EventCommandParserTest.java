package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

public class EventCommandParserTest {

    private final EventCommandParser parser = new EventCommandParser();

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;

        //multiple titles
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple descriptions
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple dates
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple start times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple end times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

    }
}
