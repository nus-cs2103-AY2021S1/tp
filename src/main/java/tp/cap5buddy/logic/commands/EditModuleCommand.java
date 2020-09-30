package tp.cap5buddy.logic.commands;

import java.util.Optional;

import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;

/**
 * Command to edit information about a Module.
 */
public class EditModuleCommand extends Command {
    /**
     * Name of the original module.
     */
    private final String moduleName;
    /**
     * Descriptor for editing the module.
     */
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * Constructs an EditModuleCommand.
     *
     * @param moduleName name of the module that will be edited.
     * @param editModuleDescriptor descriptor for editing the module.
     */
    public EditModuleCommand(String moduleName, EditModuleDescriptor editModuleDescriptor) {
        this.moduleName = moduleName;
        this.editModuleDescriptor = editModuleDescriptor;
    }

    /**
     * Executes the command by editing the module based on the descriptor.
     *
     * @param moduleList the related moduleList that contains the module that needs to be edited.
     * @return a ResultCommand.
     */
    @Override
    public ResultCommand execute(ModuleList moduleList) {
        Module moduleToEdit = moduleList.getModule(this.moduleName);
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);
        moduleList.setModule(moduleName, editedModule);
        String message = createSuccessMessage(moduleToEdit, editedModule);
        return new ResultCommand(message, isExit());
    }

    /**
     * Creates edited version of the original Module.
     *
     * @param moduleToEdit original Module to be edited.
     * @return the edited Module.
     * */
    public Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        String updatedName = editModuleDescriptor.getName().orElse(moduleToEdit.getName());
        String updatedZoomLink = editModuleDescriptor.getZoomLink().orElse((moduleToEdit.getLink()));

        return new Module(updatedName, updatedZoomLink);
    }

    /**
     * Creates success message after successfully executing this command.
     *
     * @param moduleToEdit the original module.
     * @param editedModule the module after editing.
     * @return String that represent a success message followed by the content of the original
     *         module and the content after the module has been edited.
     */
    public String createSuccessMessage(Module moduleToEdit, Module editedModule) {
        String msg = ""
                + "Module " + moduleName + " has been successfully edited!\n\n"
                + "Original Module: \n"
                + "Name     : " + moduleToEdit.getName() + "\n"
                + "Zoom Link: " + moduleToEdit.getLink() + "\n\n"
                + "After Edited: \n"
                + "Name     : " + editedModule.getName() + "\n"
                + "Zoom Link: " + editedModule.getLink() + "\n";
        return msg;
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return True if the session has been terminated, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        /**
         * the new name.
         */
        private String name;
        /**
         * the new zoomLink.
         */
        private String zoomLink;

        /**
         * Constructs an empty EditModuleDescriptor.
         */
        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setName(toCopy.name);
            setZoomLink(toCopy.zoomLink);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setZoomLink(String zoomLink) {
            this.zoomLink = zoomLink;
        }

        public Optional<String> getZoomLink() {
            return Optional.ofNullable(zoomLink);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getName().equals(e.getName()) && getZoomLink().equals(e.getZoomLink());
        }
    }
}
