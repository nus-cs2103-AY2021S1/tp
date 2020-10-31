package seedu.address.logic.parser.schedulerparsers;

import seedu.address.logic.commands.schedulercommands.FindEventCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactContainsTagsPredicate;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.event.EventContainsDatePredicate;
import seedu.address.model.event.EventContainsTagsPredicate;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.event.FindEventCriteria;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.*;

public class FindEventParser implements Parser<FindEventCommand> {
    @Override
    public FindEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!atLeastOnePresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG)) {
            throw new ParseException("At least one search parameter needed!");
        }
        FindEventCriteria criteria = new FindEventCriteria();
        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            List<String> keywordsAsList = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_NAME).get());
            EventNameContainsKeyWordsPredicate namePredicate = new EventNameContainsKeyWordsPredicate(keywordsAsList);
            criteria.addPredicate(namePredicate);
        }
        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            //TODO: most like won't work cos of format timing issues, need to test
            List<String> keywordsAsList = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_DATE).get());
            EventContainsDatePredicate namePredicate = new EventContainsDatePredicate(keywordsAsList);
            criteria.addPredicate(namePredicate);
        }
        if (argMultiMap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tags = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_TAG).get());
            Set<Tag> taskTags = ParserUtil.parseTags(tags);
            EventContainsTagsPredicate tagsPredicate = new EventContainsTagsPredicate(taskTags);
            criteria.addPredicate(tagsPredicate);
        }
        return new FindEventCommand(criteria);
    }

    private boolean atLeastOnePresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
