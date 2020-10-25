package seedu.pivot.model.investigationcase;

import static seedu.pivot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.model.tag.Tag;

/**
 * Represents a Case in PIVOT.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Case {

    // Identity fields
    private final Title title;
    private final Description description;
    private final Status status;

    // Data fields
    private final List<Suspect> suspects = new ArrayList<>();
    private final List<Victim> victims = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final List<Witness> witnesses = new ArrayList<>();
    private final List<Document> documents = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */

    public Case(Title title, Description description, Status status, List<Document> documents,
                List<Suspect> suspects, List<Victim> victims, List<Witness> witnesses, Set<Tag> tags) {
        requireAllNonNull(title, description, status, documents, suspects, victims, witnesses, tags);
        this.title = title;
        this.description = description;
        this.status = status;
        this.documents.addAll(documents);
        this.suspects.addAll(suspects);
        this.victims.addAll(victims);
        this.tags.addAll(tags);
        this.witnesses.addAll(witnesses);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public List<Document> getDocuments() {
        return documents.stream().collect(Collectors.toList());
    }

    public List<Suspect> getSuspects() {
        return suspects.stream().collect(Collectors.toList());
    }

    public List<Victim> getVictims() {
        return victims.stream().collect(Collectors.toList());
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Witness> getWitnesses() {
        return witnesses.stream().collect(Collectors.toList());
    }

    /**
     * Returns true if both cases of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two cases.
     */
    public boolean isSameCase(Case otherCase) {
        if (otherCase == this) {
            return true;
        }

        return otherCase != null
                && otherCase.getTitle().equals(getTitle())
                && otherCase.getStatus().equals(getStatus());
    }

    /**
     * Returns true if both cases have the same identity and data fields.
     * This defines a stronger notion of equality between two cases.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Case)) {
            return false;
        }

        Case otherCase = (Case) other;
        return otherCase.getTitle().equals(getTitle())
                && otherCase.getDescription().equals(getDescription())
                && otherCase.getStatus().equals(getStatus())
                && otherCase.getDocuments().equals(getDocuments())
                && otherCase.getSuspects().equals(getSuspects())
                && otherCase.getVictims().equals(getVictims())
                && otherCase.getWitnesses().equals(getWitnesses())
                && otherCase.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, status, documents,
                            suspects, victims, witnesses, tags);
    }

    /**
     * Combines all fields into one String to be used for FindCommand
     * @return String containing all words from all fields
     */
    public String toStringAllWords() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle()).append(" ");
        builder.append(getDescription()).append(" ");
        builder.append(getStatus()).append(" ");

        for (Document doc : getDocuments()) {
            builder.append(doc.getName()).append(" ");
            builder.append(doc.getReference().getFileName()).append(" ");
        }

        for (Suspect suspect : getSuspects()) {
            appendPersonDetails(builder, suspect.getName(), suspect.getGender(),
                    suspect.getPhone(), suspect.getEmail(), suspect.getAddress());
        }

        for (Victim victim : getVictims()) {
            appendPersonDetails(builder, victim.getName(), victim.getGender(),
                    victim.getPhone(), victim.getEmail(), victim.getAddress());
        }

        for (Witness witness : getWitnesses()) {
            appendPersonDetails(builder, witness.getName(), witness.getGender(),
                    witness.getPhone(), witness.getEmail(), witness.getAddress());
        }

        return builder.toString();
    }

    private void appendPersonDetails(StringBuilder builder, Name name, Gender gender,
                               Phone phone, Email email, Address address) {
        builder.append(name).append(" ");
        builder.append(gender).append(" ");
        builder.append(phone).append(" ");
        builder.append(email).append(" ");
        builder.append(address).append(" ");
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle()).append("\n")
                .append(" Description: ").append(getDescription()).append("\n")
                .append(" Status: ").append(getStatus()).append("\n");

        // Documents
        if (!getDocuments().isEmpty()) {
            builder.append("\n");
            builder.append(" Documents: ");
        }
        for (Document doc : getDocuments()) {
            builder.append(doc.getName()).append(",");
        }

        // Suspects
        if (!getSuspects().isEmpty()) {
            builder.append("\n");
            builder.append(" Suspects: ");
        }
        for (Suspect suspect : getSuspects()) {
            builder.append(suspect.getName()).append(",");
        }

        // Victims
        if (!getVictims().isEmpty()) {
            builder.append("\n");
            builder.append(" Victims: ");
        }
        for (Victim victim : getVictims()) {
            builder.append(victim.getName()).append(",");
        }

        // Witnesses
        if (!getWitnesses().isEmpty()) {
            builder.append("\n");
            builder.append(" Witnesses: ");
        }
        for (Witness witness : getWitnesses()) {
            builder.append(witness.getName()).append(",");
        }

        // Tags
        if (!getTags().isEmpty()) {
            builder.append("\n");
            builder.append(" Tags: ");
        }
        for (Tag tag : getTags()) {
            builder.append(tag.tagName);
        }

        return builder.toString();
    }

}
