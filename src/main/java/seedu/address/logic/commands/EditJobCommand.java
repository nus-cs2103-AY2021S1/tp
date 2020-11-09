package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;

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
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Job;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing job in the address book.
 */
public class EditJobCommand extends Command {

    public static final String COMMAND_WORD = "edit job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the job identified "
            + "by the index number used in the displayed job list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_JOB_TITLE + "JOB TITLE] "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_VACANCY + "VACANCY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_JOB_TITLE + "Zookeeper"
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "goggle@example.com"
            + PREFIX_PRIORITY + "low ";

    public static final String MESSAGE_EDIT_JOB_SUCCESS = "Edited job: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the address book.";

    private final Index index;
    private final EditJobDescriptor editJobDescriptor;

    /**
     * @param index of the job in the filtered person list to edit
     * @param editJobDescriptor details to edit the person with
     */
    public EditJobCommand(Index index, EditJobDescriptor editJobDescriptor) {
        requireNonNull(index);
        requireNonNull(editJobDescriptor);

        this.index = index;
        this.editJobDescriptor = new EditJobDescriptor(editJobDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEdit = lastShownList.get(index.getZeroBased());
        Job editedJob = createEditedJob(jobToEdit, editJobDescriptor);

        if (!jobToEdit.isSameJob(editedJob) && model.hasJob(editedJob)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.setJob(jobToEdit, editedJob);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_EDIT_JOB_SUCCESS, editedJob), Job.TAB_NAME);
    }

    /**
     * Creates and returns a {@code Job} with the details of {@code jobToEdit}
     * edited with {@code editJobDescriptor}.
     */
    private static Job createEditedJob(Job jobToEdit, EditJobDescriptor editJobDescriptor) {
        assert jobToEdit != null;

        Name updatedJobTitle = editJobDescriptor.getJobTitle().orElse(jobToEdit.getJobTitle());
        CompanyName updatedCompanyName = editJobDescriptor.getCompanyName().orElse(jobToEdit.getCompanyName());
        Phone updatedPhone = editJobDescriptor.getPhone().orElse(jobToEdit.getPhone());
        Email updatedEmail = editJobDescriptor.getEmail().orElse(jobToEdit.getEmail());
        Address updatedAddress = editJobDescriptor.getAddress().orElse(jobToEdit.getAddress());
        Set<Tag> updatedTags = editJobDescriptor.getTags().orElse(jobToEdit.getTags());
        Priority updatedPriority = editJobDescriptor.getPriority().orElse(jobToEdit.getPriority());
        Vacancy updatedVacancy = editJobDescriptor.getVacancy().orElse(jobToEdit.getVacancy());

        return new Job(updatedJobTitle, updatedCompanyName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPriority, updatedVacancy);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditJobCommand)) {
            return false;
        }

        // state check
        EditJobCommand e = (EditJobCommand) other;
        return index.equals(e.index)
                && editJobDescriptor.equals(e.editJobDescriptor);
    }

    /**
     * Stores the details to edit the job with. Each non-empty field value will replace the
     * corresponding field value of the job.
     */
    public static class EditJobDescriptor {
        private Name jobTitle;
        private CompanyName companyName;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Priority priority;
        private Vacancy vacancy;

        public EditJobDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditJobDescriptor(EditJobDescriptor toCopy) {
            setJobTitle(toCopy.jobTitle);
            setCompanyName(toCopy.companyName);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPriority(toCopy.priority);
            setVacancy(toCopy.vacancy);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobTitle, companyName, phone, email, address, tags, priority, vacancy);
        }

        public void setJobTitle(Name jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<Name> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
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

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setVacancy(Vacancy vacancy) {
            this.vacancy = vacancy;
        }

        public Optional<Vacancy> getVacancy() {
            return Optional.ofNullable(vacancy);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditJobDescriptor)) {
                return false;
            }

            // state check
            EditJobDescriptor e = (EditJobDescriptor) other;

            return getJobTitle().equals(e.getJobTitle())
                    && getCompanyName().equals(e.getCompanyName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getPriority().equals(e.getPriority())
                    && getVacancy().equals(e.getVacancy());
        }
    }
}
