package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_ES2660;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleList;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2030 = new ModuleBuilder().withName("CS2030")
        .withZoomLink("www.cs2030zoom.us", "Tutorial").withModularCredits(4.0).build();
    public static final Module CS2101 = new ModuleBuilder().withName("CS2101")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();
    public static final Module CS1101S = new ModuleBuilder().withName("CS1101S")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();
    public static final Module IS1103 = new ModuleBuilder().withName("IS1103")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();

    // Manually added
    public static final Module CS1231S = new ModuleBuilder().withName("CS1231S")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();
    public static final Module MA1101R = new ModuleBuilder().withName("MA1101R")
        .withZoomLink("www.zoom.us", "Tutorial").withModularCredits(4.0).build();

    // Manually added - Modules's details found in {@code CommandTestUtil}
    public static final Module CS2103 = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
        .withZoomLink(VALID_ZOOMLINK_CS2103T, "Tutorial").withModularCredits(4.0).build();
    public static final Module ES2660 = new ModuleBuilder().withName(VALID_MODULENAME_ES2660)
        .withZoomLink(VALID_ZOOMLINK_ES2660, "Tutorial").withModularCredits(4.0).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code ModuleList} with all the typical modules.
     */
    public static ModuleList getTypicalModuleList() {
        ModuleList moduleList = new ModuleList();
        for (Module module : getTypicalModules()) {
            moduleList.addModule(module);
        }
        return moduleList;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2030, CS2101, CS2105, CS1101S, IS1103));
    }
}
