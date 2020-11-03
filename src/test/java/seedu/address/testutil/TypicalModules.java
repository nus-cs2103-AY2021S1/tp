package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.ModuleBook;
import seedu.address.model.module.Module;

public class TypicalModules {
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105").build();

    public static final Module CS2100 = new ModuleBuilder()
            .withName("CS2100")
            .withMembers(new HashSet<>(Arrays.asList(BENSON))).build();
    public static final Module CS2101 = new ModuleBuilder()
            .withName("CS2101")
            .withMembers(new HashSet<>(Arrays.asList(CARL))).build();

    public static ModuleBook getTypicalModuleBook() {
        ModuleBook mb = new ModuleBook();
        for (Module module : getTypicalModules()) {
            mb.addModule(module);
        }
        return mb;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2105, CS2100, CS2101));
    }
}
