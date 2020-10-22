package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {
    private static final String VALID_BERT = "bertmodel";
    private static final String INVALID_VALID_BERT = "bert model";
    private static final String INVALID_TUTURU = "tu+turu";

    private static final Tag TAG_BERT = new Tag(VALID_BERT);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void invalidString_tagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(INVALID_VALID_BERT));
    }

    @Test
    public void invalidNonAlphanumeric_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(INVALID_TUTURU));
    }

    @Test
    public void validString_constructs_tagSuccessfully() {
        assertEquals(TAG_BERT, new Tag(VALID_BERT));
    }
}
