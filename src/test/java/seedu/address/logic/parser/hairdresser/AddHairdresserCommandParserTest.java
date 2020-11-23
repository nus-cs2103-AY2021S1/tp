package seedu.address.logic.parser.hairdresser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIALISATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_PERM;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_PERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BENJAMIN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalHairdressers.ALISSA;
import static seedu.address.testutil.TypicalHairdressers.BENJAMIN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.hairdresser.AddHairdresserCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.testutil.HairdresserBuilder;

public class AddHairdresserCommandParserTest {
    private AddHairdresserCommandParser parser = new AddHairdresserCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Hairdresser expectedHairdresser = new HairdresserBuilder(BENJAMIN).withSpecs(VALID_SPECIALISATION_PERM).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ALISSA + NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BENJAMIN + PHONE_DESC_ALISSA + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_ALISSA + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple titles - last address accepted
        assertParseSuccess(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_ALISSA + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple genders - last address accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_ALISSA
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresser));

        // multiple specs - all accepted
        Hairdresser expectedHairdresserMultipleSpecs = new HairdresserBuilder(BENJAMIN)
                .withSpecs(VALID_SPECIALISATION_PERM, VALID_SPECIALISATION_COLOR).build();
        assertParseSuccess(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN
                + SPECIALISATION_DESC_COLOR
                + SPECIALISATION_DESC_PERM, new AddHairdresserCommand(expectedHairdresserMultipleSpecs));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero specs
        Hairdresser expectedHairdresser = new HairdresserBuilder(ALISSA).withSpecs().build();
        assertParseSuccess(parser, NAME_DESC_ALISSA + PHONE_DESC_ALISSA
                        + EMAIL_DESC_ALISSA + TITLE_DESC_ALISSA + GENDER_DESC_ALISSA,
                new AddHairdresserCommand(expectedHairdresser));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHairdresserCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BENJAMIN + PHONE_DESC_BENJAMIN
                        + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BENJAMIN + VALID_PHONE_BENJAMIN
                        + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                        + VALID_EMAIL_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN, expectedMessage);

        // missing title prefix
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                        + EMAIL_DESC_BENJAMIN + VALID_TITLE_BENJAMIN + GENDER_DESC_BENJAMIN, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                        + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + VALID_GENDER_BENJAMIN, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BENJAMIN + VALID_PHONE_BENJAMIN
                        + VALID_EMAIL_BENJAMIN + VALID_TITLE_BENJAMIN + VALID_EMAIL_BENJAMIN, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BENJAMIN + INVALID_PHONE_DESC
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + INVALID_EMAIL_DESC + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Email.MESSAGE_CONSTRAINTS);

        // invalid title
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + INVALID_TITLE_DESC + GENDER_DESC_BENJAMIN
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Title.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + INVALID_GENDER_DESC
                + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM, Gender.MESSAGE_CONSTRAINTS);

        // invalid spec
        assertParseFailure(parser, NAME_DESC_BENJAMIN + PHONE_DESC_BENJAMIN
                + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN + GENDER_DESC_BENJAMIN
                + INVALID_SPECIALISATION_DESC + VALID_SPECIALISATION_PERM, Specialisation.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BENJAMIN
                        + EMAIL_DESC_BENJAMIN + INVALID_TITLE_DESC + GENDER_DESC_BENJAMIN,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BENJAMIN
                + PHONE_DESC_BENJAMIN + EMAIL_DESC_BENJAMIN + TITLE_DESC_BENJAMIN
                + GENDER_DESC_BENJAMIN + SPECIALISATION_DESC_COLOR + SPECIALISATION_DESC_PERM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHairdresserCommand.MESSAGE_USAGE));
    }
}
