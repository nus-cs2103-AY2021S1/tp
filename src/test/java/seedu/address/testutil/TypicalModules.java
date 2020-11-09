package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.testutil.builders.ModuleBuilder;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS50 = new ModuleBuilder()
            .withCode("CS50").withName("Introduction to Computer Science").build();
    public static final Module CS1010S = new ModuleBuilder()
            .withCode("CS1010S").withName("Programming Methodology")
            .withInstructors(BENSON, FIONA).build();
    public static final Module CS1101S = new ModuleBuilder()
            .withCode("CS1101S").withName("Programming Methodology")
            .withInstructors(ALICE, BENSON).build();
    public static final Module CS2030 = new ModuleBuilder()
            .withCode("CS2030").withName("Programming Methodology 2")
            .withInstructors(CARL).build();
    public static final Module CS2100 = new ModuleBuilder()
            .withCode("CS2100").withName("Computer Organization")
            .withInstructors(FIONA, GEORGE).build();
    public static final Module CS2103 = new ModuleBuilder()
            .withCode("CS2103").withName("Software Engineering")
            .withInstructors(BENSON, CARL, DANIEL).build();

    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS50, CS1010S, CS1101S, CS2030, CS2100, CS2103));
    }

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }
}
