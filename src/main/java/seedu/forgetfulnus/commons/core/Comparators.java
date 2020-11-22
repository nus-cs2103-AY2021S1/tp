package seedu.forgetfulnus.commons.core;

import java.util.Comparator;

import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Container for comparators used by SortCommand.
 */

public class Comparators {
    public static final Comparator<FlashCard> GERMAN_COMP = (obj1, obj2) -> obj1.getGermanPhrase().toString()
            .toLowerCase().compareTo(obj2.getGermanPhrase().toString().toLowerCase());
    public static final Comparator<FlashCard> REVERSE_GERMAN_COMP = (obj1, obj2) -> obj2.getGermanPhrase().toString()
            .toLowerCase().compareTo(obj1.getGermanPhrase().toString().toLowerCase());
    public static final Comparator<FlashCard> ENGLISH_COMP = (obj1, obj2) -> obj1.getEnglishPhrase().toString()
            .toLowerCase().compareTo(obj2.getEnglishPhrase().toString().toLowerCase());
    public static final Comparator<FlashCard> REVERSE_ENGLISH_COMP = (obj1, obj2) -> obj2.getEnglishPhrase().toString()
            .toLowerCase().compareTo(obj1.getEnglishPhrase().toString().toLowerCase());
    public static final Comparator<FlashCard> DIFFICULTY_EASY_COMP = (obj1, obj2) -> obj1.getDifficultyTag()
            .compareTo(obj2.getDifficultyTag());
    public static final Comparator<FlashCard> DIFFICULTY_HARD_COMP = (obj1, obj2) -> obj2.getDifficultyTag()
            .compareTo(obj1.getDifficultyTag());
    public static final Comparator<FlashCard> CHRONOLOGICAL_EARLIEST_COMP = (obj1, obj2) -> obj1.getOrder()
            .compareTo(obj2.getOrder());
    public static final Comparator<FlashCard> CHRONOLOGICAL_LATEST_COMP = (obj1, obj2) -> obj2.getOrder()
            .compareTo(obj1.getOrder());
}
