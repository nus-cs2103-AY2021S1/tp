package seedu.jarvis.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.jarvis.commons.util.CollectionUtil;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.login.Username;

public class EditLoginCommand extends EditCommand {
    public static final String MESSAGE_EDIT_LOGIN_SUCCESS = "Edited Login: %1$s";

    private final EditLoginDescriptor editLoginDescriptor;

    /**
     * @param editLoginDescriptor details to edit the student with
     */
    public EditLoginCommand(EditLoginDescriptor editLoginDescriptor) {
        requireNonNull(editLoginDescriptor);

        this.editLoginDescriptor = new EditLoginDescriptor(editLoginDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        UserLogin userLoginToEdit = model.getUserLogin();

        UserLogin editedUserLogin = createEditedPerson(userLoginToEdit, editLoginDescriptor);

        model.setUserLogin(editedUserLogin);

        return new CommandResult(String.format(MESSAGE_EDIT_LOGIN_SUCCESS, editedUserLogin));
    }

    /**
     * Creates and returns a {@code UserLogin} with the details of {@code loginToEdit}
     * edited with {@code editLoginDescriptor}.
     */
    private static UserLogin createEditedPerson(UserLogin loginToEdit, EditLoginDescriptor editLoginDescriptor) {
        assert loginToEdit != null;

        Username updatedUsername = editLoginDescriptor.getUsername().orElse(loginToEdit.getUsername());
        String updatedPassword = editLoginDescriptor.getPassword().orElse(loginToEdit.getUserPassword());

        return new UserLogin(updatedUsername, updatedPassword);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLoginCommand)) {
            return false;
        }

        // state check
        EditLoginCommand e = (EditLoginCommand) other;
        return editLoginDescriptor.equals(e.editLoginDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditLoginDescriptor {
        private Username username;
        private String password;

        public EditLoginDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLoginDescriptor(EditLoginDescriptor toCopy) {
            setUsername(toCopy.username);
            setPassword(toCopy.password);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(username, password);
        }

        public void setUsername(Username username) {
            this.username = username;
        }

        public Optional<Username> getUsername() {
            return Optional.ofNullable(username);
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Optional<String> getPassword() {
            return Optional.ofNullable(password);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLoginDescriptor)) {
                return false;
            }

            // state check
            EditLoginDescriptor e = (EditLoginDescriptor) other;

            return getUsername().equals(e.getUsername())
                    && getPassword().equals(e.getPassword());
        }
    }
}
