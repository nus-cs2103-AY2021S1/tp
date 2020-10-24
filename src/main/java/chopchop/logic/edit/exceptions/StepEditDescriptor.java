// StepEditDescriptor.java

package chopchop.logic.edit;

import java.util.Optional;

import chopchop.logic.edit.exceptions.IllegalEditOperation;

public class StepEditDescriptor {

    private final EditOperationType editType;
    private final Optional<Integer> stepNumber;
    private final String stepText;

    /**
     * Creates a new descriptor representing step editing. If the {@code stepNumber} is empty,
     * then the step should be appended to the end of the recipe's existing steps.
     *
     * @param editType   the type of edit
     * @param stepNumber the 1-based index of the step to edit.
     * @param stepText   the new content of the step
     * @throws IllegalEditOperation if the stepText was not empty when required, or vice versa
     */
    public StepEditDescriptor(EditOperationType editType, Optional<Integer> stepNumber, String stepText)
        throws IllegalEditOperation {

        this.editType = editType;
        this.stepNumber = stepNumber;
        this.stepText = stepText;

        if ((editType == EditOperationType.ADD || editType == EditOperationType.EDIT)
            && stepText.isEmpty()) {
            throw new IllegalEditOperation("step content should not be empty");
        } else if (editType == EditOperationType.DELETE && !stepText.isEmpty()) {
            throw new IllegalEditOperation("step content should be empty when deleting");
        }
    }

    public EditOperationType getEditType() {
        return this.editType;
    }

    public String getStepText() {
        return this.stepText;
    }

    public Optional<Integer> getStepNumber() {
        return this.stepNumber;
    }
}

