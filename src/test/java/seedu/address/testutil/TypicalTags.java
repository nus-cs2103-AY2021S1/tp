package seedu.address.testutil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TypicalTags {

    // Valid tag strings
    public static final String VALID_BERT = "bertmodel";
    public static final String VALID_TUTURU = "tuturu";
    public static final String VALID_ASD = "asd";
    public static final String VALID_ABC = "abc";

    //Tokenized Strings
    public static final Collection<String> VALID_TAG_STRING = Set.of(VALID_ABC);
    public static final Collection<String> VALID_TAG_MULTI_STRING = Set.of("abc",
            "asd,tuturu , bertmodel");

    //Valid tags
    private static final Tag TAG_BERT = new Tag(VALID_BERT);
    private static final Tag TAG_TUTURU = new Tag(VALID_TUTURU);
    private static final Tag TAG_ASD = new Tag(VALID_ASD);
    private static final Tag TAG_ABC = new Tag(VALID_ABC);

    // Collection of tags
    private static final Collection<Tag> VALID_TAG_SINGLE_PARSED = Set.of(TAG_ABC);
    private static final Collection<Tag> VALID_TAG_MULTI_PARSED = Set.of(TAG_ABC,
            TAG_ASD, TAG_BERT, TAG_TUTURU);

    public static Set<Tag> getTypicalTagSet() {
        return new HashSet<>(VALID_TAG_MULTI_PARSED);
    }

    public static Set<Tag> getSingleTagSet() {
        return new HashSet<>(VALID_TAG_SINGLE_PARSED);
    }
}
