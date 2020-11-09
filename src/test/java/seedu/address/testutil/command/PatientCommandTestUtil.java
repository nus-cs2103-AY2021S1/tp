package seedu.address.testutil.command;

import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class PatientCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_GEORGE = "George Best";
    public static final String VALID_NAME_BESON = "Benson Meier";
    public static final String VALID_NAME_JAMES = "james";
    public static final String VALID_TEMP_AMY = "36.7";
    public static final String VALID_TEMP_BOB = "37.0";
    public static final String VALID_PERIOD_AMY = "20200912-20200918";
    public static final String VALID_PERIOD_BOB = "20201001-20201014";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_AGE_AMY = "22";
    public static final String VALID_AGE_BOB = "45";
    public static final String COMMENT_AMY = "Down with flu";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_JAMES = " " + PREFIX_NAME + VALID_NAME_JAMES;
    public static final String TEMP_DESC_AMY = " " + PREFIX_TEMP + VALID_TEMP_AMY;
    public static final String TEMP_DESC_BOB = " " + PREFIX_TEMP + VALID_TEMP_BOB;
    public static final String PERIOD_DESC_AMY = " " + PREFIX_PERIOD_OF_STAY + VALID_PERIOD_AMY;
    public static final String PERIOD_DESC_BOB = " " + PREFIX_PERIOD_OF_STAY + VALID_PERIOD_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String COMMENT_DESC_AMY = " " + PREFIX_COMMENTS + COMMENT_AMY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TEMP_DESC = " " + PREFIX_TEMP + "36.P"; //non-numeric not allowed in temperature
    public static final String INVALID_PERIOD_DESC = " " + PREFIX_PERIOD_OF_STAY + "20200913-20201838"; //invalid date
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "999"; //cannot be so old

    public static final String NO_COMMENT = "-";
    public static final String COMMENT = "History of heart attack";

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withTemperature(VALID_TEMP_AMY).withPeriodOfStay(VALID_PERIOD_AMY)
            .withAge(VALID_AGE_AMY).withComment(COMMENT).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withTemperature(VALID_TEMP_BOB).withPeriodOfStay(VALID_PERIOD_BOB)
            .withAge(VALID_AGE_BOB).withComment(NO_COMMENT).build();
    }
}
