package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;

public class TeammateTestUtil {
    // Raw input data testcases
    public static final String VALID_TEAMMATE_NAME_A = "Jack Nicholson";
    public static final String VALID_TEAMMATE_NAME_B = "Tate Mcrae";
    public static final String VALID_TEAMMATE_NAME_C = "Halsey Mood";

    public static final String VALID_TEAMMATE_GIT_USERNAME_A = "Sparrow32";
    public static final String VALID_TEAMMATE_GIT_USERNAME_B = "Tatey";
    public static final String VALID_TEAMMATE_GIT_USERNAME_C = "Modi";
    public static final String INVALID_TEAMMATE_GIT_USERNAME = "$";

    public static final String VALID_TEAMMATE_PHONE_A = "92883923";
    public static final String VALID_TEAMMATE_PHONE_B = "92824833";
    public static final String VALID_TEAMMATE_PHONE_C = "83928444";

    public static final String VALID_TEAMMATE_EMAIL_A = "jack@gmail.com";
    public static final String VALID_TEAMMATE_EMAIL_B = "rae1992@hotmail.com";
    public static final String VALID_TEAMMATE_EMAIL_C = "modi5001000@live.com";

    public static final String VALID_TEAMMATE_ADDRESS_A = "32 Lake Road";
    public static final String VALID_TEAMMATE_ADDRESS_B = "4 Hacker Way";
    public static final String VALID_TEAMMATE_ADDRESS_C = "13 Three Quarters";

    // Valid User input with prefixes
    public static final String TEAMMATE_NAME_DESC_A = " " + PREFIX_PERSON_NAME + VALID_TEAMMATE_NAME_A;
    public static final String TEAMMATE_NAME_DESC_B = " " + PREFIX_PERSON_NAME + VALID_TEAMMATE_NAME_B;
    public static final String TEAMMATE_NAME_DESC_C = " " + PREFIX_PERSON_NAME + VALID_TEAMMATE_NAME_C;

    public static final String TEAMMATE_GIT_USERNAME_DESC_A = " " + PREFIX_PERSON_GIT_USERNAME
        + VALID_TEAMMATE_GIT_USERNAME_A;
    public static final String TEAMMATE_GIT_USERNAME_DESC_B = " " + PREFIX_PERSON_GIT_USERNAME
        + VALID_TEAMMATE_GIT_USERNAME_B;
    public static final String TEAMMATE_GIT_USERNAME_DESC_C = " " + PREFIX_PERSON_GIT_USERNAME
        + VALID_TEAMMATE_GIT_USERNAME_C;

    public static final String TEAMMATE_PHONE_DESC_A = " " + PREFIX_PERSON_PHONE + VALID_TEAMMATE_PHONE_A;
    public static final String TEAMMATE_PHONE_DESC_B = " " + PREFIX_PERSON_PHONE + VALID_TEAMMATE_PHONE_B;
    public static final String TEAMMATE_PHONE_DESC_C = " " + PREFIX_PERSON_PHONE + VALID_TEAMMATE_PHONE_C;

    public static final String TEAMMATE_EMAIL_DESC_A = " " + PREFIX_PERSON_EMAIL + VALID_TEAMMATE_EMAIL_A;
    public static final String TEAMMATE_EMAIL_DESC_B = " " + PREFIX_PERSON_EMAIL + VALID_TEAMMATE_EMAIL_B;
    public static final String TEAMMATE_EMAIL_DESC_C = " " + PREFIX_PERSON_EMAIL + VALID_TEAMMATE_EMAIL_C;

    public static final String TEAMMATE_ADDRESS_DESC_A = " " + PREFIX_PERSON_ADDRESS + VALID_TEAMMATE_ADDRESS_A;
    public static final String TEAMMATE_ADDRESS_DESC_B = " " + PREFIX_PERSON_ADDRESS + VALID_TEAMMATE_ADDRESS_B;
    public static final String TEAMMATE_ADDRESS_DESC_C = " " + PREFIX_PERSON_ADDRESS + VALID_TEAMMATE_ADDRESS_C;

    // Invalid user input with prefixes
    public static final String INVALID_TEAMMATE_NAME_DESC_A = " " + PREFIX_PERSON_NAME + "jack#$%";
    public static final String INVALID_TEAMMATE_GIT_USERNAME_DESC_A = " " + PREFIX_PERSON_GIT_USERNAME
        + "May Theresa";
    public static final String INVALID_TEAMMATE_PHONE_DESC_A = " " + PREFIX_PERSON_PHONE + "3818djfjjd";
    public static final String INVALID_TEAMMATE_EMAIL_DESC_A = " " + PREFIX_PERSON_EMAIL + "hey @";
    public static final String INVALID_TEAMMATE_ADDRESS_DESC_A = " " + PREFIX_PERSON_ADDRESS + " ";

}
