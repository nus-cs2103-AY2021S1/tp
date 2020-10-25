package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Name;

/**
 * Jackson-friendly version of {@link Instruction}.
 */
public class JsonAdaptedInstruction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Instruction's %s field is missing!";

    private final String instruction;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given ingredient details.
     */
    @JsonCreator
    public JsonAdaptedInstruction(@JsonProperty("instructions") String instruction) {
        this.instruction = instruction;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedInstruction(Instruction source) {
        this.instruction = source.getInstruction();
    }

    /**
     * Converts this Jackson-friendly adapted instruction object into the model's {@code Instruction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted instruction.
     */
    public Instruction toModelType() throws IllegalValueException {
        if (instruction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(instruction)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        //final Name modelName = new Name(value);

        return new Instruction(instruction);
    }

}
