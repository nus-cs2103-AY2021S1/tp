package seedu.flashcard.model.flashcard;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the criteria that flashcards can be sorted by.
 */
public enum SortCriteria implements Comparator<Flashcard> {
    REVIEWED_ASCENDING("reviewed a") {
        @Override
        public final int compare(final Flashcard o1, final Flashcard o2) {
            return o1.getStatistics().getReviewFrequency() - o2.getStatistics().getReviewFrequency();
        }
    },
    REVIEWED_DESCENDING("reviewed d") {
        @Override
        public final int compare(final Flashcard o1, final Flashcard o2) {
            return o2.getStatistics().getReviewFrequency() - o1.getStatistics().getReviewFrequency();
        }
    },
    SUCCESS_RATE_ASCENDING("success a") {
        @Override
        public final int compare(final Flashcard o1, final Flashcard o2) {
            return Double.compare(o1.getStatistics().getSuccessRate(), o2.getStatistics().getSuccessRate());
        }
    },

    SUCCESS_RATE_DESCENDING("success d") {
        @Override
        public final int compare(final Flashcard o1, final Flashcard o2) {
            return Double.compare(o2.getStatistics().getSuccessRate(), o1.getStatistics().getSuccessRate());
        }
    };

    public static final String MESSAGE_INVALID_SORT_CRITERIA = "Invalid sort criteria";
    private static final Map<String, SortCriteria> SORT_CRITERIA_MAP = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        for (SortCriteria sortCriteria : values()) {
            SORT_CRITERIA_MAP.put(sortCriteria.criteria, sortCriteria);
        }
    }

    public final String criteria;

    private SortCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * Returns true if a given string is a valid criteria.
     */
    public static boolean isValidCriteria(String criteria) {
        return SORT_CRITERIA_MAP.containsKey(criteria);
    }

    public static SortCriteria getCriteria(String criteria) {
        return SORT_CRITERIA_MAP.get(criteria);
    }
}
