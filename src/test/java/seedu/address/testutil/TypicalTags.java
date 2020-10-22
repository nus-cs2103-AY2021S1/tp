package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ABC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ASD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTURU;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TypicalTags {

    //Tokenized Strings
    public static final Collection<String> VALID_TAG_STRING = Set.of(VALID_TAG_ABC);
    public static final Collection<String> VALID_TAG_MULTI_STRING = Set.of("abc",
            "asd,tuturu , bertmodel");

    //Valid tags
    public static final Tag TAG_BERT = new Tag(VALID_TAG_BERT);
    public static final Tag TAG_TUTURU = new Tag(VALID_TAG_TUTURU);
    public static final Tag TAG_ASD = new Tag(VALID_TAG_ASD);
    public static final Tag TAG_ABC = new Tag(VALID_TAG_ABC);

    // Collection of tags
    public static final Collection<Tag> VALID_TAG_SINGLE_PARSED = Set.of(TAG_ABC);
    public static final Collection<Tag> VALID_TAG_MULTI_PARSED = Set.of(TAG_ABC,
            TAG_ASD, TAG_BERT, TAG_TUTURU);

    public static Set<Tag> getTypicalTagSet() {
        return new HashSet<>(VALID_TAG_MULTI_PARSED);
    }

    public static Set<Tag> getSingleTagSet() {
        return new HashSet<>(VALID_TAG_SINGLE_PARSED);
    }
}
