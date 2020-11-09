package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static quickcache.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DifficultyTest {

    public static final String LOW_DIFFICULTY = "LOW";
    public static final String MEDIUM_DIFFICULTY = "MEDIUM";
    public static final String HIGH_DIFFICULTY = "HIGH";
    public static final String UNSPECIFIED_DIFFICULTY = "UNSPECIFIED";


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Difficulty(null));
    }

    @Test
    public void constructor_invalidDifficultyName1_throwsIllegalArgumentException() {
        String invalidDifficultyName = "";
        assertThrows(IllegalArgumentException.class, () -> new Difficulty(invalidDifficultyName));
    }

    @Test
    public void constructor_invalidDifficultyName2_throwsIllegalArgumentException() {
        String invalidDifficultyName = "invalidDifficultyName";
        assertThrows(IllegalArgumentException.class, () -> new Difficulty(invalidDifficultyName));
    }

    @Test
    public void isValidDifficultyName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Difficulty.isValidDifficultyName(null));
    }

    @Test
    public void testToString() {
        Difficulty difficulty = new Difficulty(LOW_DIFFICULTY);
        assertEquals(difficulty.toString(), "[" + LOW_DIFFICULTY + "]");
    }

    @Test
    public void testEquality() {
        Difficulty difficulty1 = new Difficulty(LOW_DIFFICULTY);
        Difficulty difficulty2 = new Difficulty(LOW_DIFFICULTY);
        assertEquals(difficulty1, difficulty2);

        Difficulty difficulty3 = new Difficulty(MEDIUM_DIFFICULTY);
        Difficulty difficulty4 = new Difficulty(MEDIUM_DIFFICULTY);
        assertEquals(difficulty3, difficulty4);

        Difficulty difficulty5 = new Difficulty(HIGH_DIFFICULTY);
        Difficulty difficulty6 = new Difficulty(HIGH_DIFFICULTY);
        assertEquals(difficulty5, difficulty6);

        Difficulty difficulty7 = new Difficulty(UNSPECIFIED_DIFFICULTY);
        Difficulty difficulty8 = new Difficulty(UNSPECIFIED_DIFFICULTY);
        assertEquals(difficulty7, difficulty8);

        assertNotEquals(difficulty1, difficulty3);
        assertNotEquals(difficulty3, difficulty5);
        assertNotEquals(difficulty5, difficulty7);
        assertNotEquals(difficulty7, difficulty1);
    }
}
