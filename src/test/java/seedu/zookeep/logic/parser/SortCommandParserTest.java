package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.zookeep.logic.commands.SortCommand;
import seedu.zookeep.model.animal.AnimalComparator;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_throwsParseException() {
        // no leading and trailing whitespaces
        SortCommand expectedSortNameCommand =
                new SortCommand(AnimalComparator.createAnimalNameComparator());
        assertParseSuccess(parser, "name", expectedSortNameCommand);

        // multiple whitespaces before and after category
        SortCommand expectedSortIdCommand =
                new SortCommand(AnimalComparator.createAnimalIdComparator());
        assertParseSuccess(parser, " \t id \t", expectedSortIdCommand);

        // uppercase category
        SortCommand expectedSortFeedTimeCommand =
                new SortCommand(AnimalComparator.createAnimalFeedTimeComparator());
        assertParseSuccess(parser, "FEEDTIME", expectedSortFeedTimeCommand);

        // mixed-case category
        SortCommand expectedSortMedicalCommand =
                new SortCommand(AnimalComparator.createAnimalMedicalComparator());
        assertParseSuccess(parser, "MedicAl", expectedSortMedicalCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // invalid category
        SortCommand expectedSortNameCommand =
                new SortCommand(AnimalComparator.createAnimalNameComparator());
        assertParseFailure(parser, "animal name", String.format(SortCommandParser.MESSAGE_INVALID_SORT_CATEGORY));
    }
}
