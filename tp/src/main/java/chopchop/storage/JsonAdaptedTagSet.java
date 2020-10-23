package chopchop.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import chopchop.commons.exceptions.IllegalValueException;
import chopchop.model.attributes.Tag;

public class JsonAdaptedTagSet {

    private final List<String> tags;

    /**
     * Constructs a {@code JsonAdaptedTagSet} with the given tag strings.
     */
    @JsonCreator
    public JsonAdaptedTagSet(@JsonProperty("tags") List<String> tags) {
        this.tags = tags;
    }

    /**
     * Converts a given ingredient set into this class for Jackson use.
     */
    public JsonAdaptedTagSet(Set<Tag> source) {
        this.tags = source.stream()
                .map(Tag::getTagName)
                .collect(Collectors.toList());
    }

    /**
     * Converts this from the json thingy to the real thingy.
     */
    public Set<Tag> toModelType() throws IllegalValueException {
        //can just be empty set of tags
        var newTags = new HashSet<Tag>();

        for (var tag : this.tags) {
            newTags.add(new Tag(tag));
        }

        return newTags;
    }
}
