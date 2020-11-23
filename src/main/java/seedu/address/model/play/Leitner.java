package seedu.address.model.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.UniqueEntryList;
import seedu.address.model.deck.entry.Word;
import seedu.address.model.play.scoring.QuestionAttempt;
import seedu.address.model.play.scoring.QuizAttempt;

public class Leitner {

    private ArrayList<Entry> entryList;
    private ArrayList<Translation> questionList;
    private ArrayList<Word> answerList;
    private ArrayList<String> guesses;
    private final Deck deck;

    /**
     * Returns a Leitner object that behaves as a flashcard object. Shuffles and stores the ordered
     * entry into entryList. Extracts the words and translations of each entry in entries and stores
     * them into questionsList and answerList.
     *
     * @param deck A deck that leitner will operate on
     */
    public Leitner(Deck deck) {
        this.deck = deck;
        doTheLeitner();
    }

    /**
     * Does the leitner shuffle thingy.
     */
    public void doTheLeitner() {
        entryList = new ArrayList<>();
        questionList = new ArrayList<>();
        answerList = new ArrayList<>();
        guesses = new ArrayList<>();
        UniqueEntryList entries = deck.getEntries();

        if (deck.getQuizAttempts().size() == 0) {
            // first time playing this deck so we randomly shuffle
            for (var entry : entries) {
                entryList.add(entry);
            }
            Collections.shuffle(entryList);
            for (var entry : entries) {
                answerList.add(entry.getWord());
                questionList.add(entry.getTranslation());
            }
        } else {
            Map<String, Pair<Word, Translation>> map = new HashMap<>();
            for (var entry : entries) {
                map.put(entry.getWord().toString(),
                    new Pair<>(entry.getWord(), entry.getTranslation()));
            }
            int n = deck.getQuizAttempts().size();
            QuizAttempt latestQuizAttempt = deck.getQuizAttempts().get(n - 1);
            List<QuestionAttempt> questionAttempts = latestQuizAttempt.getQuestionAttempts();
            questionAttempts.sort(Comparator
                .comparing(QuestionAttempt::getScore)); // scores sorted in ascending order now
            for (var questionAttempt : questionAttempts) {
                Pair<Word, Translation> pair;
                if ((pair = map.get(questionAttempt.getAnswer())) != null) {
                    answerList.add(pair.getKey());
                    questionList.add(pair.getValue());
                    entryList.add(new Entry(pair.getKey(), pair.getValue()));
                    map.remove(questionAttempt.getAnswer());
                }
            }
            for (var entry : map.entrySet()) {
                answerList.add(entry.getValue().getKey());
                questionList.add(entry.getValue().getValue());
                entryList.add(new Entry(entry.getValue().getKey(), entry.getValue().getValue()));
            }
        }
    }

    public ArrayList<Word> getAnswers() {
        return answerList;
    }

    public ArrayList<Translation> getQuestions() {
        return questionList;
    }

    public ArrayList<Entry> getEntries() {
        return entryList;
    }

    public ArrayList<String> getGuesses() {
        return this.guesses;
    }

    public UniqueEntryList getUniqueEntryList() {
        UniqueEntryList list = new UniqueEntryList();
        for (Entry entry : entryList) {
            Entry quiz = new Entry(new Word("???"), entry.getTranslation());
            list.addLeitner(quiz);
        }
        assert (!list.isEmpty());
        return list;
    }

    public void addGuess(String guess) {
        this.guesses.add(guess);
    }
}
