package seedu.flashcard.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashcard.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.flashcard.testutil.Assert.assertThrows;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_CATEGORY = "%";

    private static final String VALID_QUESTION = "What does SDLC stand for?";
    private static final String VALID_ANSWER = "Software development life cycle";
    private static final String VALID_CATEGORY = "SDLC";
    private static final String VALID_NOTE = "Note";
    private static final String VALID_RATING = "2";
    private static final String VALID_TAG = "revise";

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "ImageTest");
    private static final Path INVALID_FILE_TYPE = TEST_DATA_FOLDER.resolve("invalidFileType.txt");
    private static final Path NON_EXISTENT_FILE = TEST_DATA_FOLDER.resolve("nonExistentFile.jpg");
    private static final Path VALID_FILE_TYPE = TEST_DATA_FOLDER.resolve("valid_image.jpg");

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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuestion((String) null));
    }

    @Test
    public void parseQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuestion(INVALID_QUESTION));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Question expectedQuestion = new Question(VALID_QUESTION);
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(VALID_QUESTION));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String questionWithWhitespace = WHITESPACE + VALID_QUESTION + WHITESPACE;
        Question expectedQuestion = new Question(VALID_QUESTION);
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(questionWithWhitespace));
    }

    @Test
    public void parseAnswer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAnswer((String) null));
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
    public void parseCategory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCategory((String) null));
    }

    @Test
    public void parseCategory_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory(INVALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithoutWhitespace_returnsCategory() throws Exception {
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, ParserUtil.parseCategory(VALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithWhitespace_returnsTrimmedCategory() throws Exception {
        String categoryWithWhitespace = WHITESPACE + VALID_CATEGORY + WHITESPACE;
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, ParserUtil.parseCategory(categoryWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }


    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating((String) null));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }


    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag((String) null));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsRating() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseDiagram_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDiagram((String) null));
    }

    @Test
    public void parseDiagram_validValue_returnsDiagram() throws Exception {
        Diagram expectedDiagram = new Diagram(VALID_FILE_TYPE.toString());
        assertEquals(expectedDiagram, ParserUtil.parseDiagram(VALID_FILE_TYPE.toString()));
    }

    @Test
    public void parseDiagram_nonExistentFile_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDiagram(NON_EXISTENT_FILE.toString()));
    }

    @Test
    public void parseDiagram_invalidFileType_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDiagram(INVALID_FILE_TYPE.toString()));
    }

    @Test
    public void parseDiagram_validValueWithWhitespace_returnsTrimmedDiagram() throws Exception {
        String diagramWithWhitespace = WHITESPACE + VALID_FILE_TYPE.toString() + WHITESPACE;
        Diagram expectedDiagram = new Diagram(VALID_FILE_TYPE.toString());
        assertEquals(expectedDiagram, ParserUtil.parseDiagram(diagramWithWhitespace));
    }
}
