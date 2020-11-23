package seedu.address.testutil.entry;

import seedu.address.logic.commands.entry.EditCommand.EditEntryDescriptor;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

/**
 * A utility class to help with building EditEntryDescriptor objects.
 */
public class EditEntryDescriptorBuilder {

    private EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditEntryDescriptor();
    }

    public EditEntryDescriptorBuilder(EditEntryDescriptor descriptor) {
        this.descriptor = new EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntryDescriptor} with fields containing {@code entry}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry) {
        descriptor = new EditEntryDescriptor();
        descriptor.setWord(entry.getWord());
        descriptor.setTranslation(entry.getTranslation());
    }

    /**
     * Sets the {@code Word} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withWord(String word) {
        descriptor.setWord(new Word(word));
        return this;
    }

    /**
     * Sets the {@code Translation} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withTranslation(String translation) {
        descriptor.setTranslation(new Translation(translation));
        return this;
    }

    public EditEntryDescriptor build() {
        return descriptor;
    }
}
