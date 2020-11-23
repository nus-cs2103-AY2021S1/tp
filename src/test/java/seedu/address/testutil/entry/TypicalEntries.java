package seedu.address.testutil.entry;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_SPANISH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WordBank;
import seedu.address.model.deck.entry.Entry;

/**
 * A utility class containing a list of {@code Entry} objects to be used in tests.
 */
public class TypicalEntries {

    public static final Entry JAPANESE_1 = new EntryBuilder().withWord("Apple")
            .withTranslation("りんご").build();
    public static final Entry JAPANESE_2 = new EntryBuilder().withWord("Bridge")
            .withTranslation("橋").build();
    public static final Entry JAPANESE_3 = new EntryBuilder().withWord("Cat")
            .withTranslation("猫").build();
    public static final Entry JAPANESE_4 = new EntryBuilder().withWord("Deadline")
            .withTranslation("締め切り").build();
    public static final Entry SPANISH_1 = new EntryBuilder().withWord("Summer")
            .withTranslation("verano").build();
    public static final Entry SPANISH_2 = new EntryBuilder().withWord("Winter")
            .withTranslation("invierno").build();
    public static final Entry SPANISH_3 = new EntryBuilder().withWord("Spring")
            .withTranslation("primavera").build();

    // Manually added - Entry's details found in {@code CommandTestUtil}
    public static final Entry JAPANESE = new EntryBuilder().withWord(VALID_WORD_JAPANESE)
            .withTranslation(VALID_TRANSLATION_JAPANESE).build();
    public static final Entry SPANISH = new EntryBuilder().withWord(VALID_WORD_SPANISH)
            .withTranslation(VALID_TRANSLATION_SPANISH).build();

    private TypicalEntries() {} // prevents instantiation

    /**
     * Returns an {@code WordBank} with all the typical entries.
     * @return WordBank with all the typical entries in it
     */
    public static WordBank getTypicalAddressBook() {
        WordBank ab = new WordBank();
        for (Entry entry : getTypicalEntries()) {
            ab.addEntry(entry);
        }
        return ab;
    }

    /**
     * Returns the typical entries in a list
     * @return List of typical entries
     */
    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(JAPANESE_1, JAPANESE_2, JAPANESE_3, JAPANESE_4,
                SPANISH_1, SPANISH_2, SPANISH_3));
    }
}
