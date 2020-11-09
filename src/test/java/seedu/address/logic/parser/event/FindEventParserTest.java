package seedu.address.logic.parser.event;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedulercommands.FindEventCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedulerparsers.FindEventParser;
import seedu.address.model.event.EventContainsDatePredicate;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.event.FindEventCriteria;

public class FindEventParserTest {
    private static final String VALID_ALL_FIELDS_FIND = " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String VALID_NAME_ONLY_FIELD_FIND = " " + PREFIX_NAME + VALID_EVENT_NAME_1;

    private static final String VALID_DATE_ONLY_FIELD_FIND = " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private FindEventParser parser = new FindEventParser();

    @Test
    public void findAll_fields_success() throws ParseException {
        FindEventCriteria criteria = new FindEventCriteria();
        List<String> keywordsAsList = ParserUtil.parseSearchKeywords(VALID_EVENT_NAME_1);
        EventNameContainsKeyWordsPredicate namePredicate = new EventNameContainsKeyWordsPredicate(keywordsAsList);
        criteria.addPredicate(namePredicate);
        keywordsAsList = List.of(ParserUtil.parseDate(VALID_EVENT_DATE_1).toString());
        EventContainsDatePredicate datePredicate = new EventContainsDatePredicate(keywordsAsList);
        criteria.addPredicate(datePredicate);
        assertParseSuccess(parser, VALID_ALL_FIELDS_FIND, new FindEventCommand(criteria));
    }

    @Test
    public void findName_fieldOnly_success() throws ParseException {
        FindEventCriteria criteria = new FindEventCriteria();
        List<String> keywordsAsList = ParserUtil.parseSearchKeywords(VALID_EVENT_NAME_1);
        EventNameContainsKeyWordsPredicate namePredicate = new EventNameContainsKeyWordsPredicate(keywordsAsList);
        criteria.addPredicate(namePredicate);
        assertParseSuccess(parser, VALID_NAME_ONLY_FIELD_FIND, new FindEventCommand(criteria));
    }

    @Test
    public void findDate_fieldOnly_success() throws ParseException {
        FindEventCriteria criteria = new FindEventCriteria();
        List<String> keywordsAsList = List.of(ParserUtil.parseDate(VALID_EVENT_DATE_1).toString());
        EventContainsDatePredicate datePredicate = new EventContainsDatePredicate(keywordsAsList);
        criteria.addPredicate(datePredicate);
        assertParseSuccess(parser, VALID_DATE_ONLY_FIELD_FIND, new FindEventCommand(criteria));
    }
}
