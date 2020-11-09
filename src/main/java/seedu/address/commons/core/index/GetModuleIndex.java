package seedu.address.commons.core.index;

import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;

/**
 * Gets the index of a module from a module list.
 */
public class GetModuleIndex {

    private static final int MODULE_NOT_FOUND_INDEX = -1;

    public static Index getIndex(List<Module> moduleList, ModuleName moduleName) throws IndexOutOfBoundsException {
        for (Module module : moduleList) {
            if (module.getModuleName().equals(moduleName)) {
                int index = moduleList.indexOf(module);
                return Index.fromZeroBased(index);
            }
        }
        return Index.fromZeroBased(MODULE_NOT_FOUND_INDEX);
    }
}
