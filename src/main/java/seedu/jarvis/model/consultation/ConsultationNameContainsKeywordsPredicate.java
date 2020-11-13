package seedu.jarvis.model.consultation;

import java.util.List;
import java.util.function.Predicate;

import seedu.jarvis.commons.util.StringUtil;

/**
 * Tests that a {@code Consultation}'s student name matches any of the keywords given.
 */
public class ConsultationNameContainsKeywordsPredicate implements Predicate<Consultation> {
    private final List<String> keywords;

    public ConsultationNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Consultation consultation) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getStudentName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ConsultationNameContainsKeywordsPredicate
                && keywords.equals(((ConsultationNameContainsKeywordsPredicate) other).keywords));
    }
}
