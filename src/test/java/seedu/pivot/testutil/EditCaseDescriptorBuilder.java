package seedu.pivot.testutil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.pivot.logic.commands.EditCommand.EditCaseDescriptor;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Status;
import seedu.pivot.model.investigationcase.Title;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.model.tag.Tag;

/**
 * A utility class to help with building EditCaseDescriptor objects.
 */
public class EditCaseDescriptorBuilder {

    private EditCaseDescriptor descriptor;

    public EditCaseDescriptorBuilder() {
        descriptor = new EditCaseDescriptor();
    }

    public EditCaseDescriptorBuilder(EditCaseDescriptor descriptor) {
        this.descriptor = new EditCaseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCaseDescriptor} with fields containing {@code Case}'s details
     */
    public EditCaseDescriptorBuilder(Case investigationCase) {
        descriptor = new EditCaseDescriptor();
        descriptor.setTitle(investigationCase.getTitle());
        descriptor.setDescription(investigationCase.getDescription());
        descriptor.setStatus(investigationCase.getStatus());
        descriptor.setSuspects(investigationCase.getSuspects());
        descriptor.setWitnesses(investigationCase.getWitnesses());
        descriptor.setDocuments(investigationCase.getDocuments());
        descriptor.setVictims(investigationCase.getVictims());
        descriptor.setTags(investigationCase.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditCaseDescriptor} that we are building.
     */
    public EditCaseDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditCaseDescriptor} that we are building.
     */
    public EditCaseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditCaseDescriptor} that we are building.
     */
    public EditCaseDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.createStatus(status));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCaseDescriptor}
     * that we are building.
     */
    public EditCaseDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code suspects} into a {@code List<Suspect>} and set it to the {@code EditCaseDescriptor}
     * that we are building.
     */
    public EditCaseDescriptorBuilder withSuspects(Suspect... suspects) {
        List<Suspect> suspectList = Arrays.asList(suspects);
        descriptor.setSuspects(suspectList);
        return this;
    }

    /**
     * Parses the {@code victims} into a {@code List<Victim>} and set it to the {@code EditCaseDescriptor}
     * that we are building.
     */
    public EditCaseDescriptorBuilder withVictims(Victim... victims) {
        List<Victim> victimList = Arrays.asList(victims);
        descriptor.setVictims(victimList);
        return this;
    }

    /**
     * Parses the {@code witnesses} into a {@code List<Witness>} and set it to the {@code EditCaseDescriptor}
     * that we are building.
     */
    public EditCaseDescriptorBuilder withWitnesses(Witness... witnesses) {
        List<Witness> witnessList = Arrays.asList(witnesses);
        descriptor.setWitnesses(witnessList);
        return this;
    }

    /**
     * Parses the {@code documents} into a {@code List<Document>} and set it to the {@code EditCaseDescriptor}
     * that we are building.
     */
    public EditCaseDescriptorBuilder withDocuments(Document... documents) {
        List<Document> documentList = Arrays.asList(documents);
        descriptor.setDocuments(documentList);
        return this;
    }

    public EditCaseDescriptor build() {
        return descriptor;
    }
}
