package seedu.address.model.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;
import seedu.address.model.play.scoring.QuestionAttempt;
import seedu.address.model.play.scoring.QuizAttempt;


public class LeitnerTest {

    private static Leitner leitner;
    private static Deck deck;

    @BeforeAll
    static void setUp() {
        deck = new Deck(new DeckName("deck_1"));
        for (int i = 0; i < 100; ++i) {
            deck.addEntry(new Entry(new Word(Integer.toString(i)), new Translation(
                Integer.toString(i))));
        }
        leitner = new Leitner(deck);
    }

    @Test
    public void test_leitnerShuffleNewDeckTwice_shouldGiveDifferentDecks() {
        Leitner leitner1 = new Leitner(deck);
        ArrayList<Entry> entryList1 = leitner1.getEntries();

        assertNotEquals(entryList1, deck.getEntryList());
    }

    @Test
    public void test_leitnerShufflePlayedOnceDeck_shouldGiveCorrectResults() {
        QuizAttempt quizAttempt = new QuizAttempt();
        HashMap<String, Double> qnToScore = new HashMap<>();

        for (int i = 0; i < 100; ++i) {
            double score = Math.random();
            quizAttempt.getQuestionAttempts()
                .add(new QuestionAttempt(Integer.toString(i), Integer.toString(i), score));
            qnToScore.put(Integer.toString(i), score);
        }
        deck.addQuizAttempt(quizAttempt);
        Leitner leitner2 = new Leitner(deck);
        quizAttempt.getQuestionAttempts().sort(Comparator
            .comparing(QuestionAttempt::getScore));
        for (int i = 0; i < 99; ++i) {
            String s1 = leitner2.getEntries().get(i).getWord().toString();
            String s2 = leitner2.getEntries().get(i + 1).getWord().toString();
            assertTrue(qnToScore.get(s1) <= qnToScore.get(s2));
        }
    }

    @Test
    public void test_leitnerShufflePlayedMultipleDeck_shouldGiveCorrectResults() {
        QuizAttempt quizAttempt1 = new QuizAttempt();
        QuizAttempt quizAttempt2 = new QuizAttempt();

        for (int i = 0; i < 100; ++i) {
            double score = Math.random();
            QuestionAttempt temp = new QuestionAttempt(Integer.toString(i), Integer.toString(i),
                score);
            quizAttempt1.getQuestionAttempts()
                .add(temp);
        }
        deck.addQuizAttempt(quizAttempt1);
        for (int i = 0; i < 100; ++i) {
            double score = Math.random();
            QuestionAttempt temp = new QuestionAttempt(Integer.toString(i), Integer.toString(i),
                score);
            quizAttempt2.getQuestionAttempts()
                .add(temp);
        }
        deck.addQuizAttempt(quizAttempt2);
        Leitner leitner = new Leitner(deck);

        quizAttempt2.getQuestionAttempts().sort(Comparator
            .comparing(QuestionAttempt::getScore));

        for (int i = 0; i < 100; ++i) {
            Entry entry = leitner.getEntries().get(i);
            assertEquals(entry.getTranslation().toString(),
                quizAttempt2.getQuestionAttempts().get(i).getAnswer());
        }
    }
}
