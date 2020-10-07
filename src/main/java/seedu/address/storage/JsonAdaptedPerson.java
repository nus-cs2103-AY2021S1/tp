package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.flashcard.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final List<String> question = new ArrayList<>();
    private final String answer;


    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("question") List<String> question, @JsonProperty("answer") String answer,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.question.addAll(question);
        this.answer = answer;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Flashcard source) {
        question.addAll(source.getQuestion().getQuestion());
        answer = source.getAnswer().getAnswer();
        tagged.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> flashcardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            flashcardTags.add(tag.toModelType());
        }

        if (question == null || question.size() < 1) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName()));
        }
        Question modelQuestion = null;
        if (question.size() == 1) {
            if (!OpenEndedQuestion.isValidQuestion(question.get(0))) {
                throw new IllegalValueException(OpenEndedQuestion.MESSAGE_CONSTRAINTS);
            }
            modelQuestion = new OpenEndedQuestion(question.get(0));
        }
        if (question.size() > 1) {
            if (!MultipleChoiceQuestion.isValidQuestion(question.get(0))) {
                throw new IllegalValueException(MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);
            }
            modelQuestion = new MultipleChoiceQuestion(question);
        }
        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }
        final Answer modelAnswer = new Answer(answer);

        final Set<Tag> modelTags = new HashSet<>(flashcardTags);
        return new Flashcard(modelQuestion, modelAnswer, modelTags);
    }

}
