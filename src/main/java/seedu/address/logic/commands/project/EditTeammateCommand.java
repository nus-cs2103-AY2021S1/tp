package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;

import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Edits the details of an existing project in the main catalogue.
 */
public class EditTeammateCommand extends Command {

    public static final String COMMAND_WORD = "editteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the teammate identified "
        + "by the name used in the displayed project list. Git User Name cannot be changed. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: GITUSERNAME (must be of an existing teammate) "
        + "[" + PREFIX_PERSON_NAME + "NAME] "
        + "[" + PREFIX_PERSON_PHONE + "PHONE] "
        + "[" + PREFIX_PERSON_EMAIL + "EMAIL]\n"
        + "[" + PREFIX_PERSON_ADDRESS + "ADDRESS] "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_PERSON_NAME + "Lucas "
        + PREFIX_PERSON_PHONE + "92912645 "
        + PREFIX_PERSON_EMAIL + "lucaskia@gmail.com "
        + PREFIX_PERSON_ADDRESS + "13 lay road";

    public static final String MESSAGE_EDIT_TEAMMATE_SUCCESS = "Teammate has been edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private static final Logger logger = Logger.getLogger("NewTeammateCommandLogger");
    private final GitUserIndex gitUserIndex;
    private final EditTeammateDescriptor editTeammateDescriptor;

    /**
     * @param gitUserIndex of the teammate in the teammate list in the project to edit
     * @param editTeammateDescriptor details to edit the project with
     */
    public EditTeammateCommand(GitUserIndex gitUserIndex, EditTeammateDescriptor editTeammateDescriptor) {
        requireNonNull(gitUserIndex);
        requireNonNull(editTeammateDescriptor);

        this.gitUserIndex = gitUserIndex;
        this.editTeammateDescriptor = new EditTeammateDescriptor(editTeammateDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();

        if (!project.hasParticipation(gitUserIndex.getGitUserNameString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
        }
        Participation participation = project.getParticipation(gitUserIndex.getGitUserNameString());

        Person oldTeammate = Person.getPersonFromList(gitUserIndex);
        Person editedTeammate = createEditedTeammate(participation, editTeammateDescriptor);
        Person.setPerson(editedTeammate);
        model.deletePerson(oldTeammate);
        model.addPerson(editedTeammate);

        if (model.getTeammateToBeDisplayedOnDashboard().isPresent()
                && model.getTeammateToBeDisplayedOnDashboard().get().equals(participation)) {
            participation.setPerson(editedTeammate);
            model.enter(participation);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_TEAMMATE_SUCCESS, editedTeammate));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedTeammate(Participation teammateToEdit,
          EditTeammateDescriptor editTeammateDescriptor) throws CommandException {

        assert teammateToEdit != null;

        if (!editTeammateDescriptor.getTeammateName().isPresent()
            && !editTeammateDescriptor.getPhone().isPresent()
            && !editTeammateDescriptor.getEmail().isPresent()
            && !editTeammateDescriptor.getAddress().isPresent()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Person personToEdit = teammateToEdit.getPerson();
        PersonName updatedTeammateName =
            editTeammateDescriptor.getTeammateName().orElse(personToEdit.getPersonName());
        GitUserName gitUserName = personToEdit.getGitUserName();
        Phone updatedPhone = editTeammateDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editTeammateDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editTeammateDescriptor.getAddress().orElse(personToEdit.getAddress());
        HashMap<ProjectName, Participation> updatedParticipation =
            editTeammateDescriptor.getParticipation().orElse(personToEdit.getParticipations());
        Person editedPerson = new Person(updatedTeammateName, gitUserName, updatedPhone, updatedEmail, updatedAddress,
                updatedParticipation);

        return editedPerson;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeammateCommand)) {
            return false;
        }

        // state check
        EditTeammateCommand e = (EditTeammateCommand) other;
        return gitUserIndex.equals(e.gitUserIndex)
            && editTeammateDescriptor.equals(e.editTeammateDescriptor);
    }

    /**
     * Stores the details to edit the teammate (Person) with. Each non-empty field value will replace the
     * corresponding field value of the teammate (Person).
     */
    public static class EditTeammateDescriptor {
        private PersonName name;
        private GitUserName gitUserName;
        private Phone phone;
        private Email email;
        private Address address;
        private HashMap<ProjectName, Participation> listOfParticipations = new HashMap<>();

        public EditTeammateDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTeammateDescriptor(EditTeammateDescriptor toCopy) {
            setTeammateName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setParticipation(toCopy.listOfParticipations);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, listOfParticipations);
        }

        public void setTeammateName(PersonName name) {
            this.name = name;
        }

        public Optional<PersonName> getTeammateName() {
            return Optional.ofNullable(name);
        }

        public void setGitUserName(GitUserName gitUserName) {
            this.gitUserName = gitUserName;
        }

        public Optional<GitUserName> getGitUserName() {
            return Optional.ofNullable(gitUserName);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code listOfParticipations} to this object's {@code listOfParticipations}.
         * A defensive copy of {@code listOfParticipations} is used internally.
         */
        public void setParticipation(HashMap<ProjectName, Participation> listOfParticipations) {
            this.listOfParticipations = (listOfParticipations != null)
                ? new HashMap<>(listOfParticipations)
                : null;
        }

        /**
         * Returns an unmodifiable assignees set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code assignees} is null.
         * TODO: THIS MAY FAIL, + 5 lines down
         */

        public Optional<HashMap<ProjectName, Participation>> getParticipation() {
            return (listOfParticipations != null)
                ? Optional.of(listOfParticipations)
                : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTeammateDescriptor)) {
                return false;
            }

            // state check
            EditTeammateDescriptor e = (EditTeammateDescriptor) other;

            return getTeammateName().equals(e.getTeammateName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getParticipation().equals(e.getParticipation());
        }
    }
}
