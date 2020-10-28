package seedu.pivot.logic.commands.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.model.Model;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.ReadOnlyUserPrefs;
import seedu.pivot.model.investigationcase.Case;

/**
 * A default model stub that have all of the methods failing.
 * Classes that require a ModelStub can extend from this class and implement their own ModelStubs.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getPivotFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPivotFilePath(Path pivotFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCase(Case investigationCase) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPivot(ReadOnlyPivot newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyPivot getPivot() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCase(Case investigationCase) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCase(Case target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCase(Case target, Case editedCase) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Case> getFilteredCaseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCaseList(Predicate<Case> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitPivot(String command) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canRedoPivot() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String redoPivot() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canUndoPivot() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String undoPivot() {
        throw new AssertionError("This method should not be called.");
    }
}
