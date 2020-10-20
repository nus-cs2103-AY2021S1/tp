package seedu.address.model.util;


import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.label.Label;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tag[] getSampleTags() {
        //TODO: NEED TO MODIFY DESCRIPTION
        return new Tag[] {
            new Tag(new TagName("Alex Yeoh"),
                new FileAddress("c:\\a\\b\\alex.txt"),
                   getLabelSet("AlexLabel")),
            new Tag(new TagName("Bernice Yu"),
                new FileAddress("c:\\a\\b\\bernice.txt"),
                getLabelSet("BerniceLabel")),
            new Tag(new TagName("Charlotte Oliveiro"),
                new FileAddress("c:\\a\\b\\charlotte.txt"),
                    getLabelSet("CharlotteLabel")),
            new Tag(new TagName("David Li"),
                new FileAddress("c:\\a\\b\\david.txt"),
                    getLabelSet("DavidLabel")),
            new Tag(new TagName("Irfan Ibrahim"),
                new FileAddress("c:\\a\\b\\irfan.txt"),
                    getLabelSet("IrfanLabel")),
            new Tag(new TagName("Roy Balakrishnan"),
                new FileAddress("c:\\a\\b\\roy.txt"),
                    getLabelSet("RoyLabel"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
        }
        return sampleAb;
    }

    public static Set<Label> getLabelSet(String... labels) {
        return Arrays.stream(labels)
                .map(Label::new)
                .collect(Collectors.toSet());
    }
}
