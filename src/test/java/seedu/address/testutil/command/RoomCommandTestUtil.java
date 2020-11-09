package seedu.address.testutil.command;

import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;

import seedu.address.commons.core.index.Index;

public class RoomCommandTestUtil {

    public static final String VALID_ROOM_NUMBER_ONE = "1";
    public static final String VALID_ROOM_NUMBER_TWO = "2";
    public static final int VALID_ROOM_NUMBER_SEVEN = 7;
    public static final int VALID_ROOM_NUMBER_EIGHT = 8;
    public static final int VALID_ROOM_NUMBER_TEN = 10;
    public static final Index VALID_ROOM_INDEX_ONE = Index.fromOneBased(1);
    public static final Index VALID_ROOM_INDEX_TWO = Index.fromOneBased(2);


    public static final String INVALID_ROOM_NUMBER = "-1";
    public static final String INVALID_NON_NUMBER_ROOM_NUMBER = "a";
    public static final String INVALID_NON_INTEGER_ROOM_NUMBER = "1.1";

    public static final String ROOM_NUMBER_DESC_ONE = " " + PREFIX_ROOM_NUMBER + "1";
    public static final String ROOM_NUMBER_DESC_TWO = " " + PREFIX_ROOM_NUMBER + "2";
    public static final String ROOM_NUMBER_SEVEN_DESC = " " + PREFIX_ROOM_NUMBER + "7";
    public static final String ROOM_NUMBER_EIGHT_DESC = " " + PREFIX_ROOM_NUMBER + "8";

    public static final String INVALID_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER
        + INVALID_ROOM_NUMBER;
    public static final String INVALID_NON_NUMBER_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER
        + INVALID_NON_NUMBER_ROOM_NUMBER;
    public static final String INVALID_INTEGER_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER
        + INVALID_NON_INTEGER_ROOM_NUMBER;
    public static final String REMOVE_PATIENT_DESC = " " + PREFIX_NAME + "-";
}
