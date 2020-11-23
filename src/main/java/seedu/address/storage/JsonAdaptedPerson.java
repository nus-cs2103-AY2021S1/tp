package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
abstract class JsonAdaptedPerson {

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String gender;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("id") String id,
                             @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("gender") String gender) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }


    protected String getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected String getPhone() {
        return phone;
    }

    protected String getEmail() {
        return email;
    }

    protected String getGender() {
        return gender;
    }

}
