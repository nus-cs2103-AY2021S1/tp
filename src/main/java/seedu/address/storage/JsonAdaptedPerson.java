package seedu.address.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Choice;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.MultipleChoiceQuestion;
import seedu.address.flashcard.OpenEndedQuestion;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;

//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";
    public static final String INVALID_TYPE = "Invalid flashcard type!";

    private final String type;
    private final String question;
    private final List<String> choices;
    private final String answer;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("type") String type,
                             @JsonProperty("question") String question,
                             @JsonProperty("choices") List<String> choices,
                             @JsonProperty("answer") String answer,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.type = type;
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Flashcard source) {
        if (source.getQuestion() instanceof MultipleChoiceQuestion) {
            this.type = MultipleChoiceQuestion.TYPE;
        } else if (source.getQuestion() instanceof OpenEndedQuestion) {
            this.type = OpenEndedQuestion.TYPE;;
        } else {
            this.type = "";
        }
        this.question = source.getQuestion().getQuestion();
        if (source.getQuestion().getChoices().isPresent()) {
            this.choices = Arrays.stream(source.getQuestion().getChoices().get()).map(Choice::toString)
                    .collect(Collectors.toList());
        } else {
            this.choices = null;
        }
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

        if (type == null || ((!(type.equals(MultipleChoiceQuestion.TYPE))) &&
                (!(type.equals(OpenEndedQuestion.TYPE))))) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        if (type.equals(MultipleChoiceQuestion.TYPE)) {

            if (question == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
            }
            if (!MultipleChoiceQuestion.isValidQuestion(question)) {
                throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
            }

            if (choices == null || choices.size() == 0) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Choice.class.getSimpleName()));
            }
            final Question modelQuestion= new MultipleChoiceQuestion(question, choices);

            if (answer == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
            }
            if (!Answer.isValidAnswer(answer)) {
                throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
            }
            final Answer modelAnswer = new Answer(answer);

            final Set<Tag> modelTags = new HashSet<>(flashcardTags);
            return new Flashcard(modelQuestion, modelAnswer, modelTags);

        } else if (type.equals(OpenEndedQuestion.TYPE)) {

            if (question == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
            }
            if (!OpenEndedQuestion.isValidQuestion(question)) {
                throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
            }

            final Question modelQuestion= new OpenEndedQuestion(question);

            if (answer == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
            }
            if (!Answer.isValidAnswer(answer)) {
                throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
            }
            final Answer modelAnswer = new Answer(answer);

            final Set<Tag> modelTags = new HashSet<>(flashcardTags);
            return new Flashcard(modelQuestion, modelAnswer, modelTags);

        } else {
            throw new IllegalValueException(String.format(INVALID_TYPE));
        }
    }

}
