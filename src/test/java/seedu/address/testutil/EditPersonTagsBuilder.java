package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonTags;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonTags objects.
 */
public class EditPersonTagsBuilder {
    private EditPersonTags editPersonTags;

    public EditPersonTagsBuilder() {
        editPersonTags = new EditPersonTags();
    }

    public EditPersonTagsBuilder(EditPersonTags editPersonTags) {
        this.editPersonTags = new EditPersonTags(editPersonTags);
    }

    /**
     * Returns a {@code EditPersonTags} with fields containing {@code person}'s details
     */
    public EditPersonTagsBuilder(Person person) {
        editPersonTags = new EditPersonTags();
        editPersonTags.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonTags}
     * that we are building.
     */
    public EditPersonTagsBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        editPersonTags.setTags(tagSet);
        return this;
    }

    public EditPersonTags build() {
        return editPersonTags;
    }

}
