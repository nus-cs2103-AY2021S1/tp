package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.Mcq;
import seedu.address.flashcard.OpenEndedQuestion;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;



public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "Question 0";
    public static final String DEFAULT_ANSWER = "Answer 0";

    private Question question;
    private Answer answer;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new OpenEndedQuestion(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>();
    }

    /**
     * A constructor for the FlashcarBuilder.
     * @param flashcard
     */
    public FlashcardBuilder(Flashcard flashcard) {
        question = new OpenEndedQuestion(flashcard.getQuestion().getQuestion());
        answer = new Answer(flashcard.getAnswer().getAnswer());
        tags = flashcard.getTags();
    }

    /**
     * Adds a new {@code question} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        this.question = new OpenEndedQuestion(question);
        return this;
    }

    /**
     * Adds a new {@code question} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withMultipleChoiceQuestion(String question, String[] choices) {
        this.question = new Mcq(question, choices);
        return this;
    }

    /**
     * Adds a new {@code answer} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tags);
    }

}
