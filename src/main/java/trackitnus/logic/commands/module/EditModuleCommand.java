package trackitnus.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static trackitnus.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_NAME;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.Optional;

import trackitnus.commons.core.Messages;
import trackitnus.commons.util.CollectionUtil;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;

public class EditModuleCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = Module.TYPE + " " + COMMAND_WORD
        + ": Edits the details of the module "
        + "identified "
        + "by the module code. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: "
        + PREFIX_CODE + "MODULE_CODE (must be an existing code) "
        + "[" + PREFIX_NAME + "NAME]\n"
        + String.format("Example: %s %s %sCS1231S %sDiscrete Structures",
        Module.TYPE, COMMAND_WORD, PREFIX_CODE, PREFIX_NAME);

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";

    private final Code code;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param code                 of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditModuleCommand(Code code, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(code);
        requireNonNull(editModuleDescriptor);

        this.code = code;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    /**
     * Creates and returns a {@code Model} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit,
                                             EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        Code originalCode = moduleToEdit.getCode();
        Name updatedName = editModuleDescriptor.getName().orElse(moduleToEdit.getName());

        return new Module(originalCode, updatedName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Module> moduleToEdit = model.getModule(code);
        if (moduleToEdit.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Module editedModule = createEditedModule(moduleToEdit.get(), editModuleDescriptor);

        if (!moduleToEdit.get().isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit.get(), editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        EditModuleCommand e = (EditModuleCommand) other;
        return code.equals(e.code)
            && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    @Override
    public String toString() {
        return "EditModuleCommand{" +
            "code=" + code +
            ", editModuleDescriptor=" + editModuleDescriptor +
            '}';
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {

        private Name name;

        public EditModuleDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
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

            return getName().equals(e.getName());
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "EditModuleDescriptor{" +
                "name=" + name +
                '}';
        }
    }
}
