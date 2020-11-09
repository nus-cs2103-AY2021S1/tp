package seedu.flashcard.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.flashcard.commons.exceptions.IllegalValueException;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.Statistics;
import seedu.flashcard.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private String question;
    private String answer;
    private String category;
    private String note;
    private String rating;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private String diagramFilePath;
    private Statistics statistics;
    private String isFavourite;

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("question") String question, @JsonProperty("answer") String answer,
                                @JsonProperty("category") String category, @JsonProperty("note") String note,
                                @JsonProperty("rating") String rating,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                @JsonProperty("diagramFilePath") String diagramFilePath,
                                @JsonProperty("statistics") Statistics statistics,
                                @JsonProperty("favourite") String isFavourite) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.note = note;
        this.rating = rating;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.diagramFilePath = diagramFilePath;
        this.statistics = statistics;
        this.isFavourite = isFavourite;
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     * getTagName() method is used as toString()'s method format is [tag]
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        question = source.getQuestion().toString();
        answer = source.getAnswer().toString();
        category = source.getCategory().toString();
        note = source.getNote().toString();
        rating = source.getRating().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        diagramFilePath = source.getDiagram().toString();
        statistics = source.getStatistics();
        isFavourite = Boolean.toString(source.isFavourite());
    }

    /**
     * Converts this Jackson-friendly adapted flashcard object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> flashcardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            flashcardTags.add(tag.toModelType());
        }

        if (question == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Question.class.getSimpleName()));
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }
        final Question modelQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        final Answer modelAnswer = new Answer(answer);

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Category.class.getSimpleName()));
        }
        if (!Category.isValidCategory(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(note);

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        if (diagramFilePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Diagram.class.getSimpleName()));
        }
        final Diagram modelDiagram = new Diagram(diagramFilePath);

        if (statistics == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Statistics.class.getSimpleName()));
        }
        final Statistics modelStatistics = statistics;

        final Boolean modelIsFavourite = Boolean.parseBoolean(isFavourite);

        final Set<Tag> modelTags = new HashSet<>(flashcardTags);

        return new Flashcard(modelQuestion, modelAnswer, modelCategory, modelNote, modelRating, modelTags, modelDiagram,
                modelStatistics, modelIsFavourite);
    }

}
