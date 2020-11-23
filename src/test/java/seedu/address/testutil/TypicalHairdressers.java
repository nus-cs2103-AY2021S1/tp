package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_PERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BENJAMIN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.HairStyleX;
import seedu.address.model.person.hairdresser.Hairdresser;

public class TypicalHairdressers {
    public static final Hairdresser AMELIA = new HairdresserBuilder().withName("Amelia Tan")
        .withEmail("alice@example.com")
        .withPhone("94351253")
        .withGender("F")
        .withTitle("Junior Stylist")
        .withSpecs("Color")
        .build();
    public static final Hairdresser BENEDICT = new HairdresserBuilder().withName("Benedict Meier")
        .withEmail("johnd@example.com")
        .withPhone("98765432")
        .withGender("M")
        .withTitle("Senior Stylist")
        .withSpecs("Perm", "HairExtension")
        .build();
    public static final Hairdresser CALEB = new HairdresserBuilder().withName("Caleb Kurz")
        .withPhone("95352563")
        .withEmail("heinz@example.com")
        .withGender("M")
        .withTitle("Shampooist")
        .build();
    public static final Hairdresser DAVID = new HairdresserBuilder().withName("David Meier")
        .withPhone("87652533")
        .withEmail("cornelia@example.com")
        .withGender("M")
        .withTitle("Colourist")
        .withSpecs("Styling")
        .build();
    public static final Hairdresser EMMA = new HairdresserBuilder().withName("Emma Meyer")
        .withPhone("9482224")
        .withGender("F")
        .withTitle("Apprentice")
        .withEmail("werner@example.com")
        .build();
    public static final Hairdresser FELICIA = new HairdresserBuilder().withName("Felicia Kunz")
        .withPhone("9482427")
        .withGender("F")
        .withTitle("Apprentice")
        .withEmail("lydia@example.com")
        .build();
    public static final Hairdresser GORDEN = new HairdresserBuilder().withName("Gorden Best")
        .withPhone("9482442")
        .withGender("M")
        .withTitle("Colourist")
        .withEmail("anna@example.com")
        .build();

    // Manually added
    public static final Hairdresser HANNAH = new HairdresserBuilder().withName("Hoon Meier")
        .withPhone("8482424")
        .withEmail("stefan@example.com")
        .withGender("F")
        .withTitle("Junior Stylist")
        .build();
    public static final Hairdresser IVY = new HairdresserBuilder().withName("Ida Mueller")
        .withPhone("8482131")
        .withGender("F")
        .withTitle("Junior Stylist")
        .withEmail("hans@example.com")
        .build();

    // Manually added - Hairdresser's details found in {@code CommandTestUtil}
    public static final Hairdresser ALISSA = new HairdresserBuilder().withName(VALID_NAME_ALISSA)
        .withPhone(VALID_PHONE_ALISSA)
        .withGender(VALID_GENDER_ALISSA)
        .withTitle(VALID_TITLE_ALISSA)
        .withEmail(VALID_EMAIL_ALISSA)
        .withSpecs(VALID_SPECIALISATION_PERM)
        .build();
    public static final Hairdresser BENJAMIN = new HairdresserBuilder().withName(VALID_NAME_BENJAMIN)
        .withPhone(VALID_PHONE_BENJAMIN)
        .withGender(VALID_GENDER_BENJAMIN)
        .withTitle(VALID_TITLE_BENJAMIN)
        .withEmail(VALID_EMAIL_BENJAMIN)
        .withSpecs(VALID_SPECIALISATION_PERM, VALID_SPECIALISATION_COLOR)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalHairdressers() {
    } // prevents instantiation

    /**
     * Returns an {@code HairStyleX} with all the typical hairdressers.
     */
    public static HairStyleX getTypicalHairStyleX() {
        HairStyleX ab = new HairStyleX();
        for (Hairdresser hairdresser : getTypicalHairdressers()) {
            ab.addHairdresser(hairdresser);
        }
        return ab;
    }

    public static List<Hairdresser> getTypicalHairdressers() {
        return new ArrayList<>(Arrays.asList(AMELIA, BENEDICT, CALEB, DAVID, EMMA, FELICIA, GORDEN));
    }
}
