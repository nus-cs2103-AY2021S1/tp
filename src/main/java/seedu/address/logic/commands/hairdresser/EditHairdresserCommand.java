package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_HAIRDRESSERS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;


/**
 * Edits the details of an existing hairdresser in the address book.
 */
public class EditHairdresserCommand extends Command {

    public static final String COMMAND_WORD = "edit_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the hairdresser identified "
            + "by the hairdresser ID used in the displayed hairdresser list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_SPECIALISATION + "SPECIALISATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_EDIT_HAIRDRESSER_SUCCESS = "Edited Hairdresser: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_HAIRDRESSER = "This hairdresser already exists in HairStyleX.";
    private static final String MESSAGE_DUPLICATE_CLIENT = "This person already exists in HairStyleX, "
            + "and is a Client";

    private final HairdresserId hairdresserId;
    private final EditHairdresserDescriptor editHairdresserDescriptor;

    /**
     * @param hairdresserId of the hairdresser in the filtered hairdresser list to edit
     * @param editHairdresserDescriptor details to edit the hairdresser with
     */
    public EditHairdresserCommand(HairdresserId hairdresserId, EditHairdresserDescriptor editHairdresserDescriptor) {
        requireNonNull(hairdresserId);
        requireNonNull(editHairdresserDescriptor);

        this.hairdresserId = hairdresserId;
        this.editHairdresserDescriptor = new EditHairdresserDescriptor(editHairdresserDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Hairdresser hairdresserToEdit = model.getHairdresserById(hairdresserId);
        if (hairdresserToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_HAIRDRESSER_DISPLAYED_ID);
        }
        Hairdresser editedHairdresser = createEditedHairdresser(hairdresserToEdit, editHairdresserDescriptor);

        if (!hairdresserToEdit.isSame(editedHairdresser) && model.hasHairdresser(editedHairdresser)) {
            throw new CommandException(MESSAGE_DUPLICATE_HAIRDRESSER);
        }

        if (!hairdresserToEdit.isSame(editedHairdresser) && model.hasPerson(editedHairdresser)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setHairdresser(hairdresserToEdit, editedHairdresser);
        model.updateFilteredHairdresserList(PREDICATE_SHOW_ALL_HAIRDRESSERS);
        return new CommandResult(String.format(MESSAGE_EDIT_HAIRDRESSER_SUCCESS, editedHairdresser));
    }

    /**
     * Creates and returns a {@code Hairdresser} with the details of {@code hairdresserToEdit}
     * edited with {@code editHairdresserDescriptor}.
     */
    private static Hairdresser createEditedHairdresser(Hairdresser hairdresserToEdit,
                                                       EditHairdresserDescriptor editHairdresserDescriptor) {
        assert hairdresserToEdit != null;

        Name updatedName = editHairdresserDescriptor.getName().orElse(hairdresserToEdit.getName());
        Phone updatedPhone = editHairdresserDescriptor.getPhone().orElse(hairdresserToEdit.getPhone());
        Email updatedEmail = editHairdresserDescriptor.getEmail().orElse(hairdresserToEdit.getEmail());
        Gender updatedGender = editHairdresserDescriptor.getGender().orElse(hairdresserToEdit.getGender());
        Title updatedTitle = editHairdresserDescriptor.getTitle().orElse(hairdresserToEdit.getTitle());
        Set<Specialisation> updatedSpecs = editHairdresserDescriptor.getSpecs().orElse(hairdresserToEdit.getSpecs());
        return new Hairdresser(hairdresserToEdit.getId(), updatedName,
                updatedPhone, updatedEmail, updatedGender, updatedTitle, updatedSpecs);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditHairdresserCommand)) {
            return false;
        }

        // state check
        EditHairdresserCommand e = (EditHairdresserCommand) other;
        return hairdresserId.equals(e.hairdresserId)
                && editHairdresserDescriptor.equals(e.editHairdresserDescriptor);
    }

    /**
     * Stores the details to edit the hairdresser with. Each non-empty field value will replace the
     * corresponding field value of the hairdresser.
     */
    public static class EditHairdresserDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Gender gender;
        private Title title;
        private Set<Specialisation> specs;

        public EditHairdresserDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditHairdresserDescriptor(EditHairdresserDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGender(toCopy.gender);
            setTitle(toCopy.title);
            setSpecs(toCopy.specs);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, gender, title, specs);
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

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        /**
         * Sets {@code specs} to this object's {@code specs}.
         * A defensive copy of {@code specs} is used internally.
         */
        public void setSpecs(Set<Specialisation> specs) {
            this.specs = (specs != null) ? new HashSet<>(specs) : null;
        }

        /**
         * Returns an unmodifiable specialisation set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code specs} is null.
         */
        public Optional<Set<Specialisation>> getSpecs() {
            return (specs != null) ? Optional.of(Collections.unmodifiableSet(specs)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditHairdresserDescriptor)) {
                return false;
            }

            // state check
            EditHairdresserDescriptor e = (EditHairdresserDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getGender().equals(e.getGender())
                    && getTitle().equals(e.getTitle())
                    && getSpecs().equals(e.getSpecs());
        }
    }
}
