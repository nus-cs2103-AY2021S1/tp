package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * Edits the details of an existing attraction in the itinerary.
 */
public class EditItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "edit-itinerary-attraction";
    public static final String MESSAGE_EDIT_ATTRACTION_SUCCESS = "Edited Attraction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in Itinerary.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the itinerary attraction "
            + "identified by the name of the itinerary attraction displayed in the itinerary"
            + "Parameters: INDEX " + PREFIX_DAY_VISITING + "DAY VISITING " + "[" + PREFIX_START_TIME + "START_TIME] "
            + "[" + PREFIX_END_TIME + "END_TIME] \n"
            + "Example: " + COMMAND_WORD + " 2 " + PREFIX_DAY_VISITING + "2 " + PREFIX_START_TIME + "10000 "
            + PREFIX_DAY_VISITING + "4";

    private final Index index;
    private final Index dayVisiting;
    private final EditItineraryAttractionDescriptor editIaDescriptor;

    /**
     * @param index            of the itinerary attraction to edit
     * @param editIaDescriptor details to edit the itinerary attraction with
     */
    public EditItineraryAttractionCommand(Index index, Index dayVisiting,
                                          EditItineraryAttractionDescriptor editIaDescriptor) {
        requireNonNull(index);
        requireNonNull(editIaDescriptor);

        this.index = index;
        this.dayVisiting = dayVisiting;
        this.editIaDescriptor = new EditItineraryAttractionDescriptor(editIaDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Itinerary itinerary = model.getCurrentItinerary();
        Day day = itinerary.getDay(dayVisiting);
        List<ItineraryAttraction> itineraryAttractionsThatDay =
                model.getCurrentItinerary().getDay(dayVisiting).getItineraryAttractions();

        if (index.getZeroBased() >= itineraryAttractionsThatDay.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        ItineraryAttraction itineraryAttractionToEdit = itineraryAttractionsThatDay.get(index.getZeroBased());
        ItineraryAttraction editedItineraryAttraction = createEditedItineraryAttraction(itineraryAttractionToEdit,
                editIaDescriptor);

        if (!itineraryAttractionToEdit.isSameItineraryAttraction(editedItineraryAttraction)
                && day.contains(editedItineraryAttraction)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        itinerary.editItineraryAttraction(itineraryAttractionToEdit, editedItineraryAttraction, dayVisiting);


        return new CommandResult(String.format(MESSAGE_EDIT_ATTRACTION_SUCCESS, editedItineraryAttraction));
    }

    /**
     * Creates and returns a {@code Attraction} with the details of {@code attractionToEdit}
     * edited with {@code editItineraryAttractionDescriptor}.
     */
    private static ItineraryAttraction createEditedItineraryAttraction(ItineraryAttraction itineraryAttractionToEdit,
                                                                       EditItineraryAttractionDescriptor
                                                                               editItiAttrDesc) {
        assert itineraryAttractionToEdit != null;

        ItineraryTime startTime = editItiAttrDesc.getStartTime().orElse(itineraryAttractionToEdit.getStartTime());
        ItineraryTime endTime = editItiAttrDesc.getEndTime().orElse(itineraryAttractionToEdit.getEndTime());

        return new ItineraryAttraction(itineraryAttractionToEdit.getAttraction(), startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItineraryAttractionCommand)) {
            return false;
        }

        // state check
        EditItineraryAttractionCommand e = (EditItineraryAttractionCommand) other;
        return index.equals(e.index)
                && editIaDescriptor.equals(e.editIaDescriptor);
    }

    /**
     * Stores the details to edit the attraction with. Each non-empty field value will replace the
     * corresponding field value of the attraction.
     */
    public static class EditItineraryAttractionDescriptor {
        private ItineraryTime startTime;
        private ItineraryTime endTime;

        public EditItineraryAttractionDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItineraryAttractionDescriptor(EditItineraryAttractionDescriptor toCopy) {
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(startTime, endTime);
        }

        public void setStartTime(ItineraryTime startTime) {
            this.startTime = startTime;
        }

        public Optional<ItineraryTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(ItineraryTime endTime) {
            this.endTime = endTime;
        }

        public Optional<ItineraryTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItineraryAttractionDescriptor)) {
                return false;
            }

            // state check
            EditItineraryAttractionDescriptor e = (EditItineraryAttractionDescriptor) other;

            return getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime());
        }
    }
}
