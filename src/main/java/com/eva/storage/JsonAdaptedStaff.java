package com.eva.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.model.tag.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Jackson-friendly version of {@link Staff}.
 */
class JsonAdaptedStaff {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Staff's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String leaveTaken;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedLeave> leaves = new ArrayList<>();
    private final List<JsonAdaptedComment> comments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStaff} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStaff(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("leaveTaken") String leaveTaken,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("leaves") List<JsonAdaptedLeave> leaves,
            @JsonProperty("comments") List<JsonAdaptedComment> comments
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.leaveTaken = leaveTaken;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (leaves != null) {
            this.leaves.addAll(leaves);
        }
        if (comments != null) {
            this.comments.addAll(comments);
        }
    }

    /**
     * Converts a given {@code Staff} into this class for Jackson use.
     */
    public JsonAdaptedStaff(Staff source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        leaveTaken = Integer.toString(source.getLeaveTaken().getDays());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        leaves.addAll(source.getLeaves().stream()
                .map(JsonAdaptedLeave::new)
                .collect(Collectors.toList()));
        comments.addAll(source.getComments().stream()
                .map(JsonAdaptedComment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Staff} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted staff.
     */
    public Staff toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Leave> personLeaves = new ArrayList<>();
        for (JsonAdaptedLeave leave : leaves) {
            personLeaves.add(leave.toModelType());
        }

        final List<Comment> personComments = new ArrayList<>();
        for (JsonAdaptedComment comment : comments) {
            personComments.add(comment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (leaveTaken == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LeaveTaken.class.getSimpleName()));
        }
        final LeaveTaken modelLeaveTaken = new LeaveTaken(Integer.parseInt(leaveTaken));

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Leave> modelLeaves = new HashSet<>(personLeaves);
        final Set<Comment> modelComments = new HashSet<>(personComments);
        return new Staff(modelName, modelPhone, modelEmail, modelAddress, modelLeaveTaken,
                modelTags, modelLeaves, modelComments);
    }

}
