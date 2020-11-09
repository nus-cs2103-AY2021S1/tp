package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryDate;

/**
 * Edits the details of an existing itinerary in the itineraries list in TrackPad.
 */
public class EditItineraryCommand extends Command {

    public static final String COMMAND_WORD = "edit-itinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the itinerary identified "
            + "by the index number used in the displayed itinerary list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX must be a number between 0 and 2147483648 "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_START_DATE + "START_DATE] "
            + "[" + PREFIX_END_DATE + "END_DATE ] "
            + "[" + PREFIX_BUDGET + "BUDGET ].\n"
            + "Example: " + COMMAND_WORD + " 2 n/Singapore journey sd/05-06-2019.";

    public static final String MESSAGE_EDIT_ITINERARY_SUCCESS = "Edited Itinerary: %1$s.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITINERARY =
            "This itinerary already exists in TrackPad, please choose a different name/date";
    public static final String MESSAGE_START_BEFORE_END_DATE = "Start date should come before end date";
    public static final String MESSAGE_FIELD_NOT_CHANGED =
            "Please provide a different value for the field(s) to be edited.";

    private final Index index;
    private final EditItineraryDescriptor editItineraryDescriptor;

    /**
     * @param index of the itinerary in the filtered itinerary list to edit
     * @param editItineraryDescriptor details to edit the itinerary with
     */
    public EditItineraryCommand(Index index, EditItineraryDescriptor editItineraryDescriptor) {
        requireNonNull(index);
        requireNonNull(editItineraryDescriptor);

        this.index = index;
        this.editItineraryDescriptor = new EditItineraryDescriptor(editItineraryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Itinerary> lastShownList = model.getFilteredItineraryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
        }

        Itinerary itineraryToEdit = lastShownList.get(index.getZeroBased());

        Itinerary editedItinerary = createEditedItinerary(itineraryToEdit, editItineraryDescriptor);

        if (itineraryToEdit.equals(editedItinerary)) {
            throw new CommandException(MESSAGE_FIELD_NOT_CHANGED);
        }

        if (!itineraryToEdit.isSameItinerary(editedItinerary) && model.hasItinerary(editedItinerary)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITINERARY);
        }

        model.setItinerary(itineraryToEdit, editedItinerary);
        model.updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);
        model.setCurrentItinerary(null);
        return new CommandResult(String.format(MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary),
                CommandResult.ToSwitchItineraryPanels.NO);
    }

    /**
     * Creates and returns a {@code Itinerary} with the details of {@code itineraryToEdit}
     * edited with {@code editItineraryDescriptor}.
     */
    private static Itinerary createEditedItinerary(Itinerary itineraryToEdit,
                                                   EditItineraryDescriptor editItineraryDescriptor)
            throws CommandException {
        assert itineraryToEdit != null;

        Name updatedName = editItineraryDescriptor.getName().orElse(itineraryToEdit.getName());
        Description updatedDescription = editItineraryDescriptor.getDescription()
                .orElse(itineraryToEdit.getDescription());
        ItineraryDate updatedStartDate = editItineraryDescriptor.getStartDate().orElse(itineraryToEdit.getStartDate());
        ItineraryDate updatedEndDate = editItineraryDescriptor.getEndDate().orElse(itineraryToEdit.getEndDate());
        Budget updatedBudget = editItineraryDescriptor.getBudget().orElse(itineraryToEdit.getBudget());

        if (updatedStartDate.isAfter(updatedEndDate)) {
            throw new CommandException(MESSAGE_START_BEFORE_END_DATE);
        }

        return new Itinerary(updatedName, updatedDescription, updatedStartDate, updatedEndDate, updatedBudget,
                itineraryToEdit.getDays());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItineraryCommand)) {
            return false;
        }

        // state check
        EditItineraryCommand e = (EditItineraryCommand) other;
        return index.equals(e.index) && editItineraryDescriptor.equals(e.editItineraryDescriptor);
    }

    /**
     * Stores the details to edit the itinerary with. Each non-empty field value will replace the
     * corresponding field value of the itinerary.
     */
    public static class EditItineraryDescriptor {
        private Name name;
        private Description description;
        private ItineraryDate startDate;
        private ItineraryDate endDate;
        private Budget budget;

        public EditItineraryDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditItineraryDescriptor(EditItineraryDescriptor toCopy) {
            setName(toCopy.name);
            setDescription(toCopy.description);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
            setBudget(toCopy.budget);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, description, startDate, endDate, budget);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStartDate(ItineraryDate startDate) {
            this.startDate = startDate;
        }

        public Optional<ItineraryDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(ItineraryDate endDate) {
            this.endDate = endDate;
        }

        public Optional<ItineraryDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

        public Optional<Budget> getBudget() {
            return Optional.ofNullable(budget);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItineraryDescriptor)) {
                return false;
            }

            // state check
            EditItineraryDescriptor e = (EditItineraryDescriptor) other;

            return getName().equals(e.getName())
                    && getDescription().equals(e.getDescription())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate())
                    && getBudget().equals(e.getBudget());
        }
    }
}
