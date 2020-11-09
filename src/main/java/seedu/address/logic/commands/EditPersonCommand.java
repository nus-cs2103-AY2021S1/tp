package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Person;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing candidate in the address book.
 */
public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "edit can";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the candidate identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_EXPERIENCE + "EXPERIENCE] "
            + "[" + PREFIX_DATE_OF_APPLICATION + "DATE OF APPLICATION] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_URL_LINK + "PROFILE LINK] "
            + "[" + PREFIX_SALARY + "EXPECTED SALARY] "
            + "[" + PREFIX_BLACKLIST + "IS BLACKLISTED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_EXPERIENCE + "2.5 "
            + PREFIX_SALARY + "6500 "
            + PREFIX_URL_LINK + "linkedin.com/in/johndoe "
            + PREFIX_BLACKLIST + "true ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited candidate: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This candidate already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditPersonCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), Person.TAB_NAME);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Experience updatedExperience = editPersonDescriptor.getExperience()
                .orElse(personToEdit.getExperience());
        Date updatedDateOfApplication = editPersonDescriptor.getDateOfApplication()
                .orElse(personToEdit.getDateOfApplication());
        BlacklistStatus updatedBlacklistStatus = editPersonDescriptor.getBlackListStatus()
                .orElse(personToEdit.getBlacklistStatus());
        Optional<Address> updatedAddressOptional = editPersonDescriptor.getAddressOptional()
                .orElse(personToEdit.getAddressOptional());
        Optional<UrlLink> updatedUrlLinkOptional = editPersonDescriptor.getUrlLinkOptional()
                .orElse(personToEdit.getUrlLinkOptional());
        Optional<Salary> updatedSalaryOptional = editPersonDescriptor.getSalaryOptional()
                .orElse(personToEdit.getSalaryOptional());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedExperience,
                updatedDateOfApplication, updatedBlacklistStatus, updatedAddressOptional,
                updatedUrlLinkOptional, updatedSalaryOptional, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        // state check
        EditPersonCommand e = (EditPersonCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Experience experience;
        private Date dateOfApplication;
        private BlacklistStatus blacklistStatus;
        private Optional<Address> addressOptional;
        private Optional<UrlLink> urlLinkOptional;
        private Optional<Salary> salaryOptional;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setExperience(toCopy.experience);
            setDateOfApplication(toCopy.dateOfApplication);
            setBlackListStatus(toCopy.blacklistStatus);
            setAddressOptional(toCopy.addressOptional);
            setUrlLinkOptional(toCopy.urlLinkOptional);
            setSalaryOptional(toCopy.salaryOptional);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, experience, dateOfApplication,
                    blacklistStatus, addressOptional, urlLinkOptional, salaryOptional, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        public void setExperience(Experience experience) {
            this.experience = experience;
        }

        public Optional<Experience> getExperience() {
            return Optional.ofNullable(experience);
        }

        public void setDateOfApplication(Date dateOfApplication) {
            this.dateOfApplication = dateOfApplication;
        }

        public Optional<Date> getDateOfApplication() {
            return Optional.ofNullable(dateOfApplication);
        }

        public void setBlackListStatus(BlacklistStatus blackListStatus) {
            this.blacklistStatus = blackListStatus;
        }

        public Optional<BlacklistStatus> getBlackListStatus() {
            return Optional.ofNullable(blacklistStatus);
        }

        public void setAddressOptional(Optional<Address> addressOptional) {
            this.addressOptional = addressOptional;
        }

        public Optional<Optional<Address>> getAddressOptional() {
            return Optional.ofNullable(addressOptional);
        }

        public void setUrlLinkOptional(Optional<UrlLink> urlLinkOptional) {
            this.urlLinkOptional = urlLinkOptional;
        }

        public Optional<Optional<UrlLink>> getUrlLinkOptional() {
            return Optional.ofNullable(urlLinkOptional);
        }

        public void setSalaryOptional(Optional<Salary> salaryOptional) {
            this.salaryOptional = salaryOptional;
        }

        public Optional<Optional<Salary>> getSalaryOptional() {
            return Optional.ofNullable(salaryOptional);
        }



        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getExperience().equals(e.getExperience())
                    && getDateOfApplication().equals(e.getDateOfApplication())
                    && getBlackListStatus().equals(e.getBlackListStatus())
                    && getAddressOptional().equals(e.getAddressOptional())
                    && getUrlLinkOptional().equals(e.getUrlLinkOptional())
                    && getSalaryOptional().equals(e.getSalaryOptional())
                    && getTags().equals(e.getTags());
        }
    }
}
