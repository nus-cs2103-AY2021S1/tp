package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_OBESE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAnimals.AHMENG;
import static seedu.address.testutil.TypicalAnimals.BUTTERCUP;
import static seedu.address.testutil.TypicalAnimals.COCO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAnimalDescriptor;
import seedu.address.logic.commands.ReplaceCommand;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.medicalcondition.MedicalCondition;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

public class ReplaceCommandParserTest {

    private static final String MEDICAL_CONDITION_EMPTY = " " + PREFIX_MEDICAL_CONDITION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReplaceCommand.MESSAGE_USAGE);

    private ReplaceCommandParser parser = new ReplaceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ARCHIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "123", ReplaceCommand.MESSAGE_NOT_REPLACED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "123" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "123" + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid id
        assertParseFailure(parser, "123" + INVALID_SPECIES_DESC, Species.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "123" + INVALID_MEDICAL_CONDITION_DESC,
                MedicalCondition.MESSAGE_CONSTRAINTS); // invalid medicalCondition

        // invalid id followed by valid species
        assertParseFailure(parser, "123" + INVALID_ID_DESC + SPECIES_DESC_ARCHIE, Id.MESSAGE_CONSTRAINTS);

        // valid id followed by invalid id. The test case for invalid id followed by valid id
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "123" + ID_DESC_BAILEY + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MEDICAL_CONDITION} alone will reset the
        // medicalConditions of the {@code Animal} being edited,
        // parsing it together with a valid medicalCondition results in error
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + MEDICAL_CONDITION_EMPTY, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_DESC_OBESE
                + MEDICAL_CONDITION_EMPTY + MEDICAL_CONDITION_DESC_ARTHRITIS, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_EMPTY + MEDICAL_CONDITION_DESC_OBESE
                + MEDICAL_CONDITION_DESC_ARTHRITIS, MedicalCondition.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "123" + INVALID_NAME_DESC + INVALID_SPECIES_DESC + VALID_ID_ARCHIE,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Id targetId = BUTTERCUP.getId();
        String userInput = targetId.value + ID_DESC_BAILEY + MEDICAL_CONDITION_DESC_ARTHRITIS
                + SPECIES_DESC_ARCHIE + NAME_DESC_ARCHIE + MEDICAL_CONDITION_DESC_OBESE;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_ARCHIE)
                .withId(VALID_ID_BAILEY).withSpecies(VALID_SPECIES_ARCHIE)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE).build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Id targetId = AHMENG.getId();
        String userInput = targetId.value + ID_DESC_BAILEY + SPECIES_DESC_ARCHIE;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_ARCHIE).build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Id targetId = COCO.getId();
        String userInput = targetId.value + NAME_DESC_ARCHIE;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_ARCHIE).build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // id
        userInput = targetId.value + ID_DESC_ARCHIE;
        descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_ARCHIE).build();
        expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // species
        userInput = targetId.value + SPECIES_DESC_ARCHIE;
        descriptor = new EditAnimalDescriptorBuilder().withSpecies(VALID_SPECIES_ARCHIE).build();
        expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // medicalConditions
        userInput = targetId.value + MEDICAL_CONDITION_DESC_OBESE;
        descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE).build();
        expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Id targetId = AHMENG.getId();
        String userInput = targetId.value + ID_DESC_ARCHIE + SPECIES_DESC_ARCHIE
                + MEDICAL_CONDITION_DESC_OBESE + ID_DESC_ARCHIE + SPECIES_DESC_ARCHIE + MEDICAL_CONDITION_DESC_OBESE
                + ID_DESC_BAILEY + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_ARTHRITIS;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_BAILEY).withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE,
                        VALID_MEDICAL_CONDITION_ARTHRITIS)
                .build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Id targetId = AHMENG.getId();
        String userInput = targetId.value + INVALID_ID_DESC + ID_DESC_BAILEY;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY).build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetId.value + INVALID_ID_DESC + SPECIES_DESC_BAILEY
                + ID_DESC_BAILEY;
        descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_BAILEY).build();
        expectedCommand = new ReplaceCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMedicalConditions_success() {
        Id targetId = COCO.getId();
        String userInput = targetId.value + MEDICAL_CONDITION_EMPTY;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions().build();
        ReplaceCommand expectedCommand = new ReplaceCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
