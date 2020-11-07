package seedu.address.logic.commands.meetingcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.DateContainsKeywordsPredicate;
import seedu.address.model.meeting.EndTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.StartTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;


/**
 * Finds and lists all properties in property book whose attributes contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "find-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose attributes contain"
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "can find by as many types of attributes in any order\n"
            + "Parameters:\n"
            + "[" + PREFIX_MEETING_BIDDER_ID + "BIDDER ID] \n"
            + "[" + PREFIX_MEETING_PROPERTY_ID + "PROPERTY ID] \n"
            + "[" + PREFIX_MEETING_VENUE + "VENUE]\n"
            + "[" + PREFIX_MEETING_DATE + "DATE] \n"
            + "[" + PREFIX_MEETING_STARTTIME + "START TIME] \n"
            + "[" + PREFIX_MEETING_ENDTIME + "END TIME]\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEETING_BIDDER_ID + "b12 b13"
            + PREFIX_MEETING_PROPERTY_ID + " p12";
    public static final String MESSAGE_NO_FILTERS = "At least one field to filter must be provided.";

    private final FindMeetingDescriptor findMeetingDescriptor;

    /**
     * Finds and lists all properties in property book whose attributes contains any of the argument keywords.
     * Keyword matching is case insensitive.
     */
    public FindMeetingCommand(FindMeetingDescriptor findMeetingDescriptor) {
        this.findMeetingDescriptor = findMeetingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(findMeetingDescriptor.getComposedPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && findMeetingDescriptor.equals(((FindMeetingCommand) other).findMeetingDescriptor));
    }

    /**
     * Stores the details to find the property with. Search results will find meetings that matches
     * all the non-empty fields.
     */
    public static class FindMeetingDescriptor {
        private DateContainsKeywordsPredicate datePredicate;
        private EndTimeContainsKeywordsPredicate endTimePredicate;
        private BidderIdContainsKeywordsPredicate bidderIdPredicate;
        private PropertyIdContainsKeywordsPredicate propertyIdPredicate;
        private StartTimeContainsKeywordsPredicate startTimePredicate;
        private VenueContainsKeywordsPredicate venuePredicate;

        public FindMeetingDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public FindMeetingDescriptor(FindMeetingDescriptor toCopy) {
            setDateContainsKeywordsPredicate(toCopy.datePredicate);
            setEndTimeContainsKeywordsPredicate(toCopy.endTimePredicate);
            setBidderIdContainsKeywordsPredicate(toCopy.bidderIdPredicate);
            setPropertyIdContainsKeywordsPredicate(toCopy.propertyIdPredicate);
            setStartTimeContainsKeywordsPredicate(toCopy.startTimePredicate);
            setVenueContainsKeywordsPredicate(toCopy.venuePredicate);
        }

        /**
         * Returns true if at least one field is found.
         */
        public boolean isAnyFieldFound() {
            return CollectionUtil.isAnyNonNull(datePredicate, endTimePredicate, bidderIdPredicate, propertyIdPredicate,
                    startTimePredicate, venuePredicate);
        }

        public void setEndTimeContainsKeywordsPredicate(EndTimeContainsKeywordsPredicate
                                                                     endTimeContainsKeywordsPredicate) {
            this.endTimePredicate = endTimeContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getEndTimePredicate() {
            return Optional.ofNullable(endTimePredicate);
        }

        public void setDateContainsKeywordsPredicate(DateContainsKeywordsPredicate
                                                             dateContainsKeywordsPredicate) {
            this.datePredicate = dateContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getDatePredicate() {
            return Optional.ofNullable(datePredicate);
        }

        public void setBidderIdContainsKeywordsPredicate(BidderIdContainsKeywordsPredicate
                                                             bidderIdContainsKeywordsPredicate) {
            this.bidderIdPredicate = bidderIdContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getBidderIdPredicate() {
            return Optional.ofNullable(bidderIdPredicate);
        }

        public void setPropertyIdContainsKeywordsPredicate(PropertyIdContainsKeywordsPredicate
                                                                 propertyIdContainsKeywordsPredicate) {
            this.propertyIdPredicate = propertyIdContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getPropertyIdPredicate() {
            return Optional.ofNullable(propertyIdPredicate);
        }

        public void setStartTimeContainsKeywordsPredicate(StartTimeContainsKeywordsPredicate
                                                                   startTimeContainsKeywordsPredicate) {
            this.startTimePredicate = startTimeContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getStartTimePredicate() {
            return Optional.ofNullable(startTimePredicate);
        }

        public void setVenueContainsKeywordsPredicate(VenueContainsKeywordsPredicate
                                                                  venueContainsKeywordsPredicate) {
            this.venuePredicate = venueContainsKeywordsPredicate;
        }

        public Optional<Predicate<Meeting>> getVenuePredicate() {
            return Optional.ofNullable(venuePredicate);
        }

        public Predicate<Meeting> getComposedPredicate() {
            Stream<Optional<Predicate<Meeting>>> predicateStream = Stream.of(
                    getStartTimePredicate(),
                    getBidderIdPredicate(),
                    getDatePredicate(),
                    getEndTimePredicate(),
                    getVenuePredicate(),
                    getPropertyIdPredicate()
            );
            return predicateStream.filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(p -> true, (pred1, pred2) -> pred1.and(pred2));
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindMeetingCommand.FindMeetingDescriptor)) {
                return false;
            }

            // state check
            FindMeetingCommand.FindMeetingDescriptor e = (FindMeetingCommand.FindMeetingDescriptor) other;

            return getBidderIdPredicate().equals(e.getBidderIdPredicate())
                    && getDatePredicate().equals(e.getDatePredicate())
                    && getEndTimePredicate().equals(e.getEndTimePredicate())
                    && getPropertyIdPredicate().equals(e.getPropertyIdPredicate())
                    && getStartTimePredicate().equals(e.getStartTimePredicate())
                    && getVenuePredicate().equals(e.getVenuePredicate());
        }
    }
}
