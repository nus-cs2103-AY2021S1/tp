package seedu.address.testutil;

import seedu.address.model.MeetingBook;
import seedu.address.model.ModuleBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalModules {
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105").build();

    public static ModuleBook getTypicalModuleBook() {
        ModuleBook mb = new ModuleBook();
        for (Module module : getTypicalModules()) {
            mb.addModule(module);
        }
        return mb;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2105));
    }
}
