package seedu.address.model.play.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.entry.TypicalEntries.JAPANESE_1;
import static seedu.address.testutil.entry.TypicalEntries.SPANISH_3;

import org.junit.jupiter.api.Test;

public class BinaryScoringTest {

    private BinaryScoring binaryScoring = new BinaryScoring();

    @Test
    public void computeScore_sameGuess_returnsOne() {
        String guess = JAPANESE_1.getWord().toString();
        assertEquals(binaryScoring.computeScore(guess, guess), 1.0);
    }

    @Test
    public void computeScore_wrongGuess_returnsZero() {
        String guess = JAPANESE_1.getWord().toString();
        String answer = SPANISH_3.getWord().toString();
        assertEquals(binaryScoring.computeScore(guess, answer), 0.0);
    }
}
