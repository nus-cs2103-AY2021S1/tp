package seedu.address.testutil;

import seedu.address.flashcard.*;
import seedu.address.model.person.*;

import java.util.HashSet;
import java.util.Set;

public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION="Question 0";
    public static final String DEFAULT_ANSWER="Answer 0";

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

    public FlashcardBuilder(Flashcard flashcard){
        question = new OpenEndedQuestion(flashcard.getQuestion());
        answer = new Answer(flashcard.getAnswer());
        tags = flashcard.getTags();
    }

    public FlashcardBuilder withQuestion(String question) {
        this.question = new OpenEndedQuestion(question);
        return this;
    }

    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tags);
    }

}
