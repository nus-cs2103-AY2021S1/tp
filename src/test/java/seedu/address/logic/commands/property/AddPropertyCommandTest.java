package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.property.Property;
import seedu.address.testutil.property.PropertyBuilder;

public class AddPropertyCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyCommand(null));
    }

    @Test
    public void execute_propertyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyAdded modelStub = new ModelStubAcceptingPropertyAdded();
        Property validProperty = new PropertyBuilder().build();

        CommandResult commandResult = new AddPropertyCommand(validProperty).execute(modelStub);

        assertEquals(String.format(AddPropertyCommand.MESSAGE_SUCCESS, validProperty),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property validProperty = new PropertyBuilder().build();
        AddPropertyCommand addPropertyCommand = new AddPropertyCommand(validProperty);
        ModelStub modelStub = new ModelStubWithProperty(validProperty);

        assertThrows(CommandException.class, AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY, () ->
                addPropertyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddPropertyCommand addPropertyACommand = new AddPropertyCommand(PROPERTY_A);
        AddPropertyCommand addPropertyBCommand = new AddPropertyCommand(PROPERTY_B);

        // same object -> returns true
        assertTrue(addPropertyACommand.equals(addPropertyACommand));

        // same values -> returns true
        AddPropertyCommand addPropertyACommandCopy = new AddPropertyCommand(PROPERTY_A);
        assertTrue(addPropertyACommand.equals(addPropertyACommandCopy));

        // different types -> returns false
        assertFalse(addPropertyACommand.equals(1));

        // null -> returns false
        assertFalse(addPropertyACommand.equals(null));

        // different property -> returns false
        assertFalse(addPropertyACommand.equals(addPropertyBCommand));
    }

    /**
     * A Model stub that contains a single property.
     */
    private class ModelStubWithProperty extends ModelStub {
        private final Property property;

        ModelStubWithProperty(Property property) {
            requireNonNull(property);
            this.property = property;
        }

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return this.property.isSameProperty(property);
        }
    }

    /**
     * A Model stub that always accept the property being added.
     */
    private class ModelStubAcceptingPropertyAdded extends ModelStub {
        final ArrayList<Property> propertiesAdded = new ArrayList<>();

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return propertiesAdded.stream().anyMatch(property::isSameProperty);
        }

        @Override
        public Property addProperty(Property property) {
            requireNonNull(property);
            propertiesAdded.add(property);
            return property;
        }

    }
}
