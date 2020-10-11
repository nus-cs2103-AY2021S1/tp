package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module ALICE = new ModuleBuilder().withName("Alice Pauline")
            .withZoomLink("123, Jurong West Ave 6, #08-111").build();
    public static final Module BENSON = new ModuleBuilder().withName("Benson Meier")
            .withZoomLink("311, Clementi Ave 2, #02-25").build();
    public static final Module CARL = new ModuleBuilder().withName("Carl Kurz").withZoomLink("wall street").build();
    public static final Module DANIEL = new ModuleBuilder().withName("Daniel Meier").withZoomLink("10th street").build();
    public static final Module ELLE = new ModuleBuilder().withName("Elle Meyer").withZoomLink("michegan ave").build();
    public static final Module FIONA = new ModuleBuilder().withName("Fiona Kunz").withZoomLink("little tokyo").build();
    public static final Module GEORGE = new ModuleBuilder().withName("George Best").withZoomLink("4th street").build();

    // Manually added
    public static final Module HOON = new ModuleBuilder().withName("Hoon Meier").withZoomLink("little india").build();
    public static final Module IDA = new ModuleBuilder().withName("Ida Mueller").withZoomLink("chicago ave").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module AMY = new ModuleBuilder().withName(VALID_NAME_AMY).withZoomLink(VALID_ZOOM_LINK_AMY).build();
    public static final Module BOB = new ModuleBuilder().withName(VALID_NAME_BOB).withZoomLink(VALID_ZOOM_LINK_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Modules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
