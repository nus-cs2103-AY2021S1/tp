package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Choice;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.MultipleChoiceQuestion;
import seedu.address.flashcard.OpenEndedQuestion;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;



public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "Question 0";
    public static final String DEFAULT_ANSWER = "Answer 0";
    public static final String DEFAULT_TAG = "Tag 0";

    private Question question;
    private Answer answer;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new OpenEndedQuestion(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>(Collections.singletonList(new Tag(DEFAULT_TAG)));
    }

    /**
     * A constructor for the FlashcardBuilder.
     * @param flashcard
     */
    public FlashcardBuilder(Flashcard flashcard) {
        question = flashcard.getQuestion();
        answer = flashcard.getAnswer();
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
        Choice[] choiceArr = new Choice[choices.length];
        for (int i = 0; i < choices.length; i++) {
            choiceArr[i] = new Choice(choices[i]);
        }
        this.question = new MultipleChoiceQuestion(question, choiceArr);
        return this;
    }

    /**
     * Adds a new {@code answer} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code Flashcard} that we are building.
     * @param tag the tag to be added.
     * @return the FlashcardBuilder
     */
    public FlashcardBuilder withTag(String tag) {
        tags = new HashSet<>(Collections.singletonList(new Tag(tag)));
        return this;
    }

    /**
     * Adds a few {@code Tag}s to the {@code Flashcard} that we are building.
     * @param tagArr the array containing the tags.
     * @return the FlashcardBuilder.
     */
    public FlashcardBuilder withTags(String[] tagArr) {
        List<Tag> taglist = new ArrayList<>();
        for (String tag : tagArr) {
            taglist.add(new Tag(tag));
        }
        tags = new HashSet<>(taglist);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tags);
    }

}
