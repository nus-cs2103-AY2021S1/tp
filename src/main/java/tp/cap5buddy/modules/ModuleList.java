package tp.cap5buddy.modules;

import java.util.List;

/**
 * Encapsulates information and methods that relate to a list of modules being tracked.
 */
public class ModuleList {

    /** List of modules belonging to the user. **/
    private final List<Module> modules;

    /**
     * Creates and initialises a new ModuleList object.
     *
     * @param modules List of modules.
     */
    public ModuleList(List<Module> modules) {
        this.modules = modules;
    }

    /**
     * Retrieves the module matching the input module name from the list of modules.
     *
     * @param inputModuleName
     * @return Module matching the input module name if it exists, null otherwise.
     */
    public Module getModule(String inputModuleName) {
        for (Module module : this.modules) {
            String moduleName = module.getName();
            if (moduleName.equals(inputModuleName)) {
                return module;
            }
        }
        return null;
    }

    /**
     * Adds a module.
     *
     * @param module
     */
    public void addModule(Module module) {
        this.modules.add(module);
    }

    /**
     * Returns the size of the list.
     * @return integer size.
     */
    public int size() {
        return this.modules.size();
    }
}

