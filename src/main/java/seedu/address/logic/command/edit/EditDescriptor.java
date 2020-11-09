package seedu.address.logic.command.edit;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Class to build parameters based on description from user input
 */
public class EditDescriptor {

    //identity
    private Name name;

    //all possible fields
    private Location location;
    private Cost cost;
    private WanderlustDate startDate;
    private WanderlustDate endDate;
    private Passport passport;
    private Mobile mobile;
    private Importance levelOfImportance;
    private WanderlustDateTime activityDateTime;

    public EditDescriptor() {

    }

    /**
     * Copy constructor
     *
     * @param toCopy contains information about all possible fields
     */
    public EditDescriptor(EditDescriptor toCopy) {
        toCopy.getName().ifPresent(this::setName);
        toCopy.getLocation().ifPresent(this::setLocation);
        toCopy.getCost().ifPresent(this::setCost);
        toCopy.getStartDate().ifPresent(this::setStartDate);
        toCopy.getEndDate().ifPresent(this::setEndDate);
        toCopy.getPassport().ifPresent(this::setPassport);
        toCopy.getMobile().ifPresent(this::setMobile);
        toCopy.getLevelOfImportance().ifPresent(this::setLevelOfImportance);
        toCopy.getActivityDateTime().ifPresent(this::setActivityDateTime);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, location, cost, levelOfImportance, activityDateTime, mobile, passport,
                startDate, endDate);
    }

    /**
     * Ensures that only the valid fields have been edited using the EditDescriptor
     * @param type a String that specify the type of object to check valid fields.
     * @return true if invalid field(s) has/have been edited.
     */
    public boolean wrongFieldEdited(String type) {

        switch (type) {
        case Activity.TPO_WORD:
            return CollectionUtil.isAnyNonNull(mobile, passport, startDate, endDate);

        case Accommodation.TPO_WORD:
            return CollectionUtil.isAnyNonNull(levelOfImportance, activityDateTime, mobile, passport);

        case Friend.TPO_WORD:
            return CollectionUtil.isAnyNonNull(location, cost, levelOfImportance, activityDateTime, startDate, endDate);

        case TravelPlan.TRAVEL_PLAN_WORD:
            return CollectionUtil.isAnyNonNull(location, cost, levelOfImportance, activityDateTime, mobile, passport);
        default:
            return false;
        }
    }

    /**
     * Provides editdescriptor to construct edited objects in edit commands
     *
     * @param source of tokenized parameters that are valid according to specified types
     * @return editdescriptor that contains all the parameters to be edited
     */
    public static EditDescriptor buildFromSource(ArgumentMultimap source) throws ParseException {

        EditDescriptor editItemDescriptor = new EditDescriptor();

        if (source.getValue(PREFIX_NAME).isPresent()) {
            editItemDescriptor.setName(ParserUtil.parseName(source.getValue(PREFIX_NAME).get()));
        }

        if (source.getValue(PREFIX_LOCATION).isPresent()) {
            editItemDescriptor.setLocation(ParserUtil.parseLocation(source.getValue(PREFIX_LOCATION).get()));
        }
        if (source.getValue(PREFIX_COST).isPresent()) {
            editItemDescriptor.setCost(ParserUtil.parseCost(source.getValue(PREFIX_COST).get()));
        }
        if (source.getValue(PREFIX_START).isPresent()) {
            editItemDescriptor.setStartDate(ParserUtil.parseDate(source.getValue(PREFIX_START).get()));
        }
        if (source.getValue(PREFIX_END).isPresent()) {
            editItemDescriptor.setEndDate(ParserUtil.parseDate(source.getValue(PREFIX_END).get()));
        }
        if (source.getValue(PREFIX_PASSPORT).isPresent()) {
            editItemDescriptor.setPassport(ParserUtil.parsePassport(source.getValue(PREFIX_PASSPORT).get()));
        }
        if (source.getValue(PREFIX_MOBILE).isPresent()) {
            editItemDescriptor.setMobile(ParserUtil.parseMobile(source.getValue(PREFIX_MOBILE).get()));
        }
        if (source.getValue(PREFIX_IMPORTANCE).isPresent()) {
            editItemDescriptor.setLevelOfImportance(ParserUtil.parseImportance(source
                    .getValue(PREFIX_IMPORTANCE).get()));
        }
        if (source.getValue(PREFIX_DATETIME).isPresent()) {
            editItemDescriptor.setActivityDateTime(ParserUtil.parseActivityDateTime(source.getValue(PREFIX_DATETIME)
                    .get()));
        }

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_NOT_EDITED));
        }

        return editItemDescriptor;

    }


    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Optional<Cost> getCost() {
        return Optional.ofNullable(cost);
    }

    public void setLevelOfImportance(Importance importance) {
        this.levelOfImportance = importance;
    }

    public Optional<Importance> getLevelOfImportance() {
        return Optional.ofNullable(levelOfImportance);
    }

    public void setActivityDateTime(WanderlustDateTime activityDateTime) {
        this.activityDateTime = activityDateTime;
    }

    public Optional<WanderlustDateTime> getActivityDateTime() {
        return Optional.ofNullable(activityDateTime);
    }

    public void setStartDate(WanderlustDate startDate) {
        this.startDate = startDate;
    }

    public Optional<WanderlustDate> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public void setEndDate(WanderlustDate endDate) {
        this.endDate = endDate;
    }

    public Optional<WanderlustDate> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Optional<Passport> getPassport() {
        return Optional.ofNullable(passport);
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Optional<Mobile> getMobile() {
        return Optional.ofNullable(mobile);
    }

    /**
     * Descriptors are considered as the same if both descriptor have the same name
     *
     * @param other name to check
     */
    public boolean isSameDescriptor(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditDescriptor)) {
            return false;
        }
        EditDescriptor toCompare = (EditDescriptor) other;
        return this.name.equals(toCompare.getName().get());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDescriptor)) {
            return false;
        }

        // state check
        EditDescriptor e = (EditDescriptor) other;

        return getName().equals(e.getName())
                && getActivityDateTime().equals(e.getActivityDateTime())
                && getLocation().equals(e.getLocation())
                && getCost().equals(e.getCost())
                && getStartDate().equals(e.getStartDate())
                && getEndDate().equals(e.getEndDate())
                && getPassport().equals(e.getPassport())
                && getMobile().equals(e.getMobile())
                && getLevelOfImportance().equals(e.getLevelOfImportance())
                && getActivityDateTime().equals(e.getActivityDateTime());

    }
}
