package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.category.Category;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ISBN = "+651234";
    private static final String INVALID_LANGUAGE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CATEGORY = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_ISBN = "123456";
    private static final String VALID_LANGUAGE = "English";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_CATEGORY_1 = "friend";
    private static final String VALID_CATEGORY_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_BOOK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_BOOK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseIsbn_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIsbn((String) null));
    }

    @Test
    public void parseIsbn_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIsbn(INVALID_ISBN));
    }

    @Test
    public void parseIsbn_validValueWithoutWhitespace_returnsIsbn() throws Exception {
        Isbn expectedIsbn = new Isbn(VALID_ISBN);
        assertEquals(expectedIsbn, ParserUtil.parseIsbn(VALID_ISBN));
    }

    @Test
    public void parseIsbn_validValueWithWhitespace_returnsTrimmedIsbn() throws Exception {
        String isbnWithWhitespace = WHITESPACE + VALID_ISBN + WHITESPACE;
        Isbn expectedIsbn = new Isbn(VALID_ISBN);
        assertEquals(expectedIsbn, ParserUtil.parseIsbn(isbnWithWhitespace));
    }

    @Test
    public void parseLanguage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLanguage((String) null));
    }

    @Test
    public void parseLanguage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLanguage(INVALID_LANGUAGE));
    }

    @Test
    public void parseLanguage_validValueWithoutWhitespace_returnsLanguage() throws Exception {
        Language expectedLanguage = new Language(VALID_LANGUAGE);
        assertEquals(expectedLanguage, ParserUtil.parseLanguage(VALID_LANGUAGE));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseCategory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCategory(null));
    }

    @Test
    public void parseCategory_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory(INVALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithoutWhitespace_returnsCategory() throws Exception {
        Category expectedCategory = new Category(VALID_CATEGORY_1);
        assertEquals(expectedCategory, ParserUtil.parseCategory(VALID_CATEGORY_1));
    }

    @Test
    public void parseCategory_validValueWithWhitespace_returnsTrimmedCategory() throws Exception {
        String categoryWithWhitespace = WHITESPACE + VALID_CATEGORY_1 + WHITESPACE;
        Category expectedCategory = new Category(VALID_CATEGORY_1);
        assertEquals(expectedCategory, ParserUtil.parseCategory(categoryWithWhitespace));
    }

    @Test
    public void parseCategories_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCategories(null));
    }

    @Test
    public void parseCategories_collectionWithInvalidCategories_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCategories(Arrays.asList(VALID_CATEGORY_1,
                INVALID_CATEGORY)));
    }

    @Test
    public void parseCategories_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCategories(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCategories_collectionWithValidCategories_returnsCategorySet() throws Exception {
        Set<Category> actualCategorySet = ParserUtil.parseCategories(Arrays.asList(VALID_CATEGORY_1, VALID_CATEGORY_2));
        Set<Category> expectedCategorySet = new HashSet<Category>(Arrays.asList(new Category(VALID_CATEGORY_1),
                new Category(VALID_CATEGORY_2)));

        assertEquals(expectedCategorySet, actualCategorySet);
    }

    /* @Test
    public void parseStocking_validValueWithoutWhitespace_returnsStocking() throws Exception {
        HashMap<String, Integer> storage = new HashMap<>();

        storage.put("central library", 30);
        storage.put("science library", 45);

        Stocking expectedStocking = new Stocking(storage);
        assertEquals(expectedStocking, ParserUtil.parseStocking(VALID_STOCKING));
    } */
}
