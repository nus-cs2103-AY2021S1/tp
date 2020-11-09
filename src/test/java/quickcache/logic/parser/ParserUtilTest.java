package quickcache.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static quickcache.testutil.Assert.assertThrows;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Difficulty;
import quickcache.model.flashcard.Tag;


public class ParserUtilTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_TAG = " ";
    private static final String INVALID_CHOICE = " ";

    private static final String VALID_QUESTION = "Is this a question?";
    private static final String VALID_ANSWER = "yes";
    private static final String VALID_ANSWER_MCQ = "5";
    private static final String VALID_ANSWER_MCQ_2 = "1";
    private static final String VALID_DIFFICULTY_LOW = "LOW";
    private static final String VALID_DIFFICULTY_UNSPECIFIED = "UNSPECIFIED";
    private static final String VALID_DIFFICULTY_EMPTY = "";
    private static final String VALID_TAG_1 = "CS2103T";
    private static final String VALID_TAG_2 = "HardModule";
    private static final String VALID_CHOICE_1 = "Choice 1";
    private static final String VALID_CHOICE_2 = "Choice 2";


    private static final String WHITESPACE = " \t\r\n";



    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseQuestion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuestion(null));
    }

    @Test
    public void parseQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuestion(INVALID_QUESTION));
    }

    @Test
    public void parseQuestion_validValueWithoutWhitespace_returnsQuestion() throws Exception {
        String expectedQuestion = VALID_QUESTION;
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(VALID_QUESTION));
    }

    @Test
    public void parseQuestion_validValueWithWhitespace_returnsTrimmedQuestion() throws Exception {
        String questionWithWhitespace = WHITESPACE + VALID_QUESTION + WHITESPACE;
        String expectedQuestion = VALID_QUESTION;
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(questionWithWhitespace));
    }

    @Test
    public void parseMultipleChoiceQuestion_answerSmallerThanChoice_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleChoiceQuestion(VALID_QUESTION,
                VALID_ANSWER_MCQ, ParserUtil.parseChoices(Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2))));
    }

    @Test
    public void parseMultipleChoiceQuestion_invalidAnswer_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleChoiceQuestion(VALID_QUESTION, VALID_ANSWER,
                ParserUtil.parseChoices(Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2))));
    }

    @Test
    public void parseMultipleChoiceQuestion_invalidQuestion_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleChoiceQuestion(INVALID_QUESTION,
                VALID_ANSWER_MCQ_2, ParserUtil.parseChoices(Arrays.asList(VALID_CHOICE_1, VALID_CHOICE_2))));
    }

    @Test
    public void parseOpenEndedQuestion_invalidQuestion_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOpenEndedQuestion(INVALID_QUESTION, VALID_ANSWER));
    }

    @Test
    public void parseAnswer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAnswer(null));
    }

    @Test
    public void parseAnswer_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAnswer(INVALID_ANSWER));
    }

    @Test
    public void parseAnswer_validValueWithoutWhitespace_returnsAnswer() throws Exception {
        Answer expectedAnswer = new Answer(VALID_ANSWER);
        assertEquals(expectedAnswer, ParserUtil.parseAnswer(VALID_ANSWER));
    }

    @Test
    public void parseAnswer_validValueWithWhitespace_returnsTrimmedAnswer() throws Exception {
        String answerWithWhitespace = WHITESPACE + VALID_ANSWER + WHITESPACE;
        Answer expectedAnswer = new Answer(VALID_ANSWER);
        assertEquals(expectedAnswer, ParserUtil.parseAnswer(answerWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseFileName_validValueWithoutWhiteSpace_returnsFileName() throws Exception {
        String expectedFileName = "test.json";
        String validFileNameInput = expectedFileName;
        assertEquals(expectedFileName, ParserUtil.parseFileName(validFileNameInput));
    }

    @Test
    public void parseFileName_validValueWithWhiteSpace_returnsTrimmedFileName() throws Exception {
        String expectedFileName = "test.json";
        String validFileNameInput = " " + expectedFileName;
        assertEquals(expectedFileName, ParserUtil.parseFileName(validFileNameInput));
    }

    @Test
    public void parseFileName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFileName(null));
    }

    @Test
    public void parseFileName_empty_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFileName(""));
    }

    @Test
    public void parseFileName_invalid_throwsParseException() {
        // throws error if null character is part of the file name string
        assertThrows(ParseException.class, () -> ParserUtil.parseFileName("\0"));
    }

    public void parseKeywords_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseKeywords(null));
    }

    @Test
    public void parseKeywords_collectionWithInvalidKeywords_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_QUESTION, INVALID_QUESTION)));
    }

    @Test
    public void parseKeywords_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseKeywords(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseKeywords_collectionWithOneSpacedInput_returnsBrokenDownKeywords() {
        List<String> inputWithWhiteSpacesInbetween = List.of(VALID_QUESTION);
        List<String> expectedKeywords = Arrays.asList(VALID_QUESTION.split("\\s+"));
    }

    @Test
    public void parseKeywords_collectionWithManyNotSpacedInputs_returnsCollectionWithSameContents() throws Exception {
        String[] notSpacedInputs = VALID_QUESTION.split("\\s+");
        List<String> inputsWithNoWhiteSpacesInBetween = List.of(notSpacedInputs[0], notSpacedInputs[1]);
        assertEquals(inputsWithNoWhiteSpacesInBetween, ParserUtil.parseKeywords(inputsWithNoWhiteSpacesInBetween));
    }

    @Test
    public void parseKeywords_collectionWithManySpacedInputs_returnsBrokenDownKeywords() throws Exception {
        String[] notSpacedInputs = VALID_QUESTION.split("\\s+");
        List<String> spacedInputs = List.of(notSpacedInputs[0] + " " + notSpacedInputs[1],
                notSpacedInputs[2] + " " + notSpacedInputs[3]);
        List<String> expectedKeywords = Arrays.asList(notSpacedInputs);
        assertEquals(expectedKeywords, ParserUtil.parseKeywords(spacedInputs));
    }

    @Test
    public void parseDifficulty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDifficulty(null));
    }

    @Test
    public void parseDifficulty_validValueWithoutWhitespace_returnsDifficultyLow() throws Exception {
        Difficulty expectedDifficulty = new Difficulty(VALID_DIFFICULTY_LOW);
        assertEquals(expectedDifficulty, ParserUtil.parseDifficulty(VALID_DIFFICULTY_LOW));
    }

    @Test
    public void parseDifficulty_emptyString_returnsDifficultyUnspecified() throws Exception {
        Difficulty expectedDifficulty = new Difficulty(VALID_DIFFICULTY_UNSPECIFIED);
        assertEquals(expectedDifficulty, ParserUtil.parseDifficulty(VALID_DIFFICULTY_EMPTY));
    }

    @Test
    public void parseDifficulty_invalid_throwsParseException() {
        // throws error if null character is part of the file name string
        assertThrows(ParseException.class, () -> ParserUtil.parseDifficulty("Invalid Value"));
    }

    @Test
    public void parseChoices_collectionWithDuplicateChoices_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseChoices(Arrays.asList("Choice", "Choice")));
    }

    @Test
    public void parseChoice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseChoice(INVALID_CHOICE));
    }
}
