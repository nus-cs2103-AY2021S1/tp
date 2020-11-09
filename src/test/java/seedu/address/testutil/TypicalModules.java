package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.ModuleBook;
import seedu.address.model.module.Module;

public class TypicalModules {
    public static final Module CS1000 = new ModuleBuilder()
            .withName("CS1000")
            .withMembers(new HashSet<>(Arrays.asList(AMY))).build();
    public static final Module CS1001 = new ModuleBuilder()
            .withName("CS1000")
            .withMembers(new HashSet<>(Arrays.asList(BOB))).build();
    public static final Module CS2100 = new ModuleBuilder()
            .withName("CS2100")
            .withMembers(new HashSet<>(Arrays.asList(BENSON, GEORGE))).build();
    public static final Module CS2101 = new ModuleBuilder()
            .withName("CS2101")
            .withMembers(new HashSet<>(Arrays.asList(CARL))).build();
    public static final Module CS2102 = new ModuleBuilder()
            .withName("CS2102")
            .withMembers(new HashSet<>(Arrays.asList(DANIEL))).build();
    public static final Module CS2104 = new ModuleBuilder()
            .withName("CS2104")
            .withMembers(new HashSet<>(Arrays.asList(ELLE))).build();
    public static final Module CS2105 = new ModuleBuilder()
            .withName("CS2105")
            .withMembers(new HashSet<>(Arrays.asList(FIONA))).build();

    public static final Module CM1111 = new ModuleBuilder()
            .withName("CM1111")
            .withMembers(new HashSet<>(Arrays.asList(AMY, BOB))).build();

    public static ModuleBook getTypicalModuleBook() {
        ModuleBook mb = new ModuleBook();
        for (Module module : getTypicalModules()) {
            mb.addModule(module);
        }
        return mb;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2100, CS2101, CS2102, CS2104));
    }


}
