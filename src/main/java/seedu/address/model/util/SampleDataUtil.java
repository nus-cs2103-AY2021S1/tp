package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Description;
import seedu.address.model.investigationcase.Document;
import seedu.address.model.investigationcase.Name;
import seedu.address.model.investigationcase.Reference;
import seedu.address.model.investigationcase.Status;
import seedu.address.model.investigationcase.Suspect;
import seedu.address.model.investigationcase.Title;
import seedu.address.model.investigationcase.Victim;
import seedu.address.model.investigationcase.Witness;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static List<Witness> sampleWitnesses = new ArrayList<>(
            List.of(new Witness(new Name("Mary")), new Witness(new Name("Janice"))));
    private static List<Document> sampleDocuments =
            List.of(new Document(new Name("name"), new Reference("test1.txt")));
    private static List<Suspect> sampleSuspects = getSuspectList("Alex", "Bernice");
    private static List<Victim> sampleVictims = getVictimList("Tom", "John");
    private static Description sampleDescription = new Description("7 people arrested for rioting");

    public static Case[] getSampleCases() {
        return new Case[] {
            new Case(new Title("Ang Mo Kio Car Theft"), sampleDescription,
                    Status.createStatus("active"), sampleDocuments,
                    sampleSuspects, sampleVictims,
                    sampleWitnesses, getTagSet()),
            new Case(new Title("Bishan Shopping Theft"), sampleDescription,
                    Status.createStatus("closed"), sampleDocuments,
                    sampleSuspects, sampleVictims, sampleWitnesses, getTagSet()),
            new Case(new Title("Cha Bee Hoon Stall Fire"), sampleDescription,
                    Status.createStatus("cold"), sampleDocuments,
                    sampleSuspects, sampleVictims, sampleWitnesses, getTagSet()),
            new Case(new Title("Dhoby Ghaut Murder Case"), sampleDescription,
                    Status.createStatus("active"), sampleDocuments,
                    sampleSuspects, sampleVictims, sampleWitnesses, getTagSet()),
            new Case(new Title("IceCream Man Harassment"), sampleDescription,
                    Status.createStatus("active"), sampleDocuments,
                    sampleSuspects, sampleVictims, sampleWitnesses, getTagSet()),
            new Case(new Title("Roti Prata Shop Robbery"), sampleDescription,
                    Status.createStatus("active"), sampleDocuments,
                    sampleSuspects, sampleVictims, sampleWitnesses, getTagSet())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Case sampleCase : getSampleCases()) {
            sampleAb.addCase(sampleCase);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a witness list containing the list of strings given.
     */
    public static List<Witness> getWitnessList(String... strings) {
        return Arrays.stream(strings)
                .map(string -> new Witness(new Name(string)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a suspect list containing the list of strings given.
     */
    public static List<Suspect> getSuspectList(String... suspects) {
        return Arrays.stream(suspects)
                .map(string -> new Suspect(new Name(string)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a victim list containing the list of strings given.
     */
    public static List<Victim> getVictimList(String... victims) {
        return Arrays.stream(victims)
                .map(string -> new Victim(new Name(string)))
                .collect(Collectors.toList());
    }

}
