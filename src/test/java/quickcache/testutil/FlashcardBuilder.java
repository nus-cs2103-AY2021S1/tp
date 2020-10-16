package quickcache.testutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;
import quickcache.model.flashcard.Tag;


public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "Question 0";
    public static final String DEFAULT_ANSWER = "Answer 0";
    public static final String DEFAULT_TAG = "Tag0";
    public static final Statistics DEFAULT_STATISTICS = new Statistics();

    private Question question;
    private Set<Tag> tags;
    private Statistics statistics;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new OpenEndedQuestion(DEFAULT_QUESTION, new Answer(DEFAULT_ANSWER));
        tags = new HashSet<>(Collections.singletonList(new Tag(DEFAULT_TAG)));
        statistics = DEFAULT_STATISTICS;
    }

    /**
     * A constructor for the FlashcardBuilder.
     *
     * @param flashcard
     */
    public FlashcardBuilder(Flashcard flashcard) {
        question = flashcard.getQuestion();
        tags = flashcard.getTags();
        statistics = flashcard.getStatistics();
    }

    /**
     * Adds a new {@code question} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        Answer answer = new Answer(this.question.getAnswer().getValue());
        Question finalQuestion = this.question.copyQuestion(question, answer);
        this.question = finalQuestion;
        return this;
    }

    /**
     * Adds a new {@code question} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withMultipleChoiceQuestion(String question, String[] choices) {
        Answer answer = new Answer(this.question.getAnswer().getValue());
        Choice[] choiceArr = new Choice[choices.length];
        for (int i = 0; i < choices.length; i++) {
            choiceArr[i] = new Choice(choices[i]);
        }
        this.question = new MultipleChoiceQuestion(question, answer, choiceArr);
        return this;
    }

    /**
     * Adds a new {@code answer} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        String question = this.question.getValue();
        Question finalQuestion = this.question.copyQuestion(question, new Answer(answer));
        this.question = finalQuestion;
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code Flashcard} that we are building.
     *
     * @param tag the tag to be added.
     * @return the FlashcardBuilder
     */
    public FlashcardBuilder withTag(String tag) {
        tags = new HashSet<>(Collections.singletonList(new Tag(tag)));
        return this;
    }

    /**
     * Adds a few {@code Tag}s to the {@code Flashcard} that we are building.
     *
     * @param tagArr the array containing the tags.
     * @return the FlashcardBuilder.
     */
    public FlashcardBuilder withTags(String... tagArr) {
        List<Tag> taglist = new ArrayList<>();
        for (String tag : tagArr) {
            taglist.add(new Tag(tag));
        }
        tags = new HashSet<>(taglist);
        return this;
    }

    /**
     * Adds a new {@code Statistics} to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withStatistics(Statistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, tags, statistics);
    }

}
