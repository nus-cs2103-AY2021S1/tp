package seedu.flashcard.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.Statistics;
import seedu.flashcard.model.tag.Tag;
import seedu.flashcard.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "What does OOP stand for?";
    public static final String DEFAULT_ANSWER = "Object oriented programming";
    public static final String DEFAULT_CATEGORY = "General";
    public static final String DEFAULT_NOTE = "";
    public static final String DEFAULT_RATING = "";
    public static final String DEFAULT_TAG = "";
    public static final String DEFAULT_DIAGRAM = "";
    public static final Statistics DEFAULT_STATISTICS = new Statistics();
    public static final boolean DEFAULT_FAVOURITE_STATUS = false;

    private Question question;
    private Answer answer;
    private Category category;
    private Note note;
    private Rating rating;
    private Set<Tag> tags;
    private Diagram diagram;
    private Statistics statistics;
    private boolean isFavourite;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        category = new Category(DEFAULT_CATEGORY);
        note = new Note(DEFAULT_NOTE);
        rating = new Rating(DEFAULT_RATING);
        tags = new HashSet<>();
        diagram = new Diagram(DEFAULT_DIAGRAM);
        statistics = DEFAULT_STATISTICS;
        isFavourite = DEFAULT_FAVOURITE_STATUS;
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        question = flashcardToCopy.getQuestion();
        answer = flashcardToCopy.getAnswer();
        category = flashcardToCopy.getCategory();
        note = flashcardToCopy.getNote();
        rating = flashcardToCopy.getRating();
        tags = new HashSet<>(flashcardToCopy.getTags());
        diagram = flashcardToCopy.getDiagram();
        statistics = flashcardToCopy.getStatistics();
        isFavourite = flashcardToCopy.isFavourite();
    }

    /**
     * Sets the {@code Question} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Diagram} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withDiagram(String diagramFilePath) {
        this.diagram = new Diagram(diagramFilePath);
        return this;
    }

    /**
     * Sets the {@code Statistics} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withStatistics(Statistics statistics) {
        this.statistics = statistics;
        return this;
    }

    /**
     * Sets the {@code isFavourite} status of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withFavouriteStatus(boolean isFavourite) {
        this.isFavourite = isFavourite;
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, category, note, rating, tags, diagram, statistics, isFavourite);
    }

}
