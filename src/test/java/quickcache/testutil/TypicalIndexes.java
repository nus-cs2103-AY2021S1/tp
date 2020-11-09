package quickcache.testutil;

import quickcache.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_FLASHCARD = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_FLASHCARD = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_FLASHCARD = Index.fromOneBased(3);
    public static final Index INDEX_SEVENTH_FLASHCARD = Index.fromOneBased(7);
    public static final Index VERY_BIG_INDEX_FLASHCARD = Index.fromOneBased(Integer.MAX_VALUE);
    public static final Index INDEX_FIRST_MCQ_FLASHCARD = Index.fromOneBased(5);
    public static final Index INDEX_FIRST_OPEN_ENDED_FLASHCARD = Index.fromOneBased(1);
}
