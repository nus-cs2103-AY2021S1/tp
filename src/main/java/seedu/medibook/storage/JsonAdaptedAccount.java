package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

class JsonAdaptedAccount {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Account data is corrupted!";

    private final String username;
    private final String password;
    private final String doctorName;
    private final String doctorMcr;

    @JsonCreator
    public JsonAdaptedAccount(@JsonProperty("username") String username, @JsonProperty("password") String password,
                              @JsonProperty("doctorName") String doctorName,
                              @JsonProperty("doctorMcr") String doctorMcr) {
        this.username = username;
        this.password = password;
        this.doctorName = doctorName;
        this.doctorMcr = doctorMcr;
    }

    public JsonAdaptedAccount(Account source) {
        username = source.getUsername();
        password = source.getPassword();
        doctorName = source.getDoctor().getName().fullName;
        doctorMcr = source.getDoctor().getMcr().value;
    }

    public Account toModelType() throws IllegalValueException {
        if (doctorName == null || doctorMcr == null || !Name.isValidName(doctorName) || !Mcr.isValidMcr(doctorMcr)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final Name modelDoctorName = new Name(doctorName);
        final Mcr modelDoctorMcr = new Mcr(doctorMcr);
        final Doctor modelDoctor = new Doctor(modelDoctorName, modelDoctorMcr);

        final Account modelAccount = new Account(username, password, modelDoctor);
        return modelAccount;
    }

}
