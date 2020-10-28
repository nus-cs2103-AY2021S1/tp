package seedu.address.model.lesson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.ModelContainsKeywordsPredicate;

/**
 * Tests that a {@code Lesson}'s given attribute matches any of the keywords given.
 */
public class LessonContainsKeywordsPredicate extends ModelContainsKeywordsPredicate<Lesson>
                                            implements Predicate<Lesson> {
    private final Map<Prefix, List<String>> keywords;

    public LessonContainsKeywordsPredicate() {
        this.keywords = super.keywords;
    }

    @Override
    public boolean test(Lesson lesson) {
        boolean matched = false;
        for (Map.Entry<Prefix, List<String>> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            List<String> words = entry.getValue();
            if (!isMatched(prefix.getPrefix(), words, lesson)) {
                return false;
            }
            matched = true;
        }
        return matched;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LessonContainsKeywordsPredicate)) {
            return false;
        }

        LessonContainsKeywordsPredicate casted = (LessonContainsKeywordsPredicate) other;
        for (Prefix key : keywords.keySet()) {
            if (!casted.keywords.containsKey(key) || !keywords.get(key).equals(casted.keywords.get(key))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatched(String prefix, List<String> words, Lesson lesson) {
        // assert prefix is of the desired format
        assert prefix.charAt(prefix.length() - 1) == ':' : "prefix string is in the wrong format";
        if (prefix.equals("title:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(lesson.getTitle().title, keyword));
        }

        if (prefix.equals("desc:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(lesson.getDescription().value, keyword));
        }

        if (prefix.equals("date:")) {
            return words.stream()
                    .anyMatch(keyword -> {
                        assert DateUtil.isValidDate(keyword) : "find keyword for date not in correct format";
                        return lesson.happensOnDate(
                                LocalDate.parse(keyword, DateUtil.DATE_FORMATTER));
                    });
        }

        if (prefix.equals("time:")) {
            return words.stream()
                    .anyMatch(keyword -> {
                        assert DateUtil.isValidTime(keyword) : "find keyword for time not in correct format";
                        return lesson.periodContainsGivenTime(
                                LocalTime.parse(keyword, DateUtil.TIME_FORMATTER));
                    });
        }

        if (prefix.equals("datetime:")) {
            return words.stream()
                    .anyMatch(keyword -> {
                        assert DateUtil.isValidDateTime(keyword) : "find keyword for date time not in correct format";
                        return lesson.happensOnDateTime(
                                LocalDateTime.parse(keyword, DateUtil.DATETIME_FORMATTER)
                        );
                    });
        }

        if (prefix.equals("tag:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(lesson.getTag().tagName, keyword));
        }

        return false;
    }


}
