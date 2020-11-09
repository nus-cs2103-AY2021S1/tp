package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.module.Module;

/**
 * A comparator class used to sort modules by semester.
 */
public class ModuleComparator implements Comparator<Module> {

    @Override
    public int compare(Module m1, Module m2) {
        return m1.getSemester().getSemStringToInt() - m2.getSemester().getSemStringToInt();
    }
}
