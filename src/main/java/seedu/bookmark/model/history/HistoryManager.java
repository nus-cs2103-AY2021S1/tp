package seedu.bookmark.model.history;

import java.util.ArrayDeque;
import java.util.ArrayList;

import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;

/**
 * Represents the history of a Library.
 * Guarantees: Immutable
 */
public class HistoryManager {

    public static final int MAX_UNDO_COUNT = 10;

    private final State currentState;
    private final ArrayDeque<State> undoDeque;
    private final ArrayDeque<State> redoDeque;

    /**
     * Initializes a new {@code HistoryManager} with the given {@code State} as the initial state.
     */
    public HistoryManager(State currentState) {
        this.currentState = currentState;
        this.undoDeque = new ArrayDeque<>();
        this.redoDeque = new ArrayDeque<>();
    }

    /**
     * Private constructor to facilitate the immutable nature of {@code HistoryManager}
     */
    private HistoryManager(State currentState, ArrayDeque<State> undoDeque,
            ArrayDeque<State> redoDeque) {
        this.currentState = currentState;
        this.undoDeque = undoDeque;
        this.redoDeque = redoDeque;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    /**
     * Sets a {@code ReadOnlyLibrary} as the currentState, adding the previous currentState to the undoDeque and
     * clearing the redoDeque.
     */
    public HistoryManager addNewState(State state) {
        addToUndo(currentState);
        redoDeque.clear();
        return new HistoryManager(state, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    /**
     * Undoes the currentState, adding it to the redoDeque and sets the top of the undoDeque as the currentState.
     */
    public HistoryManager undo() throws UndoException {
        if (!canUndo()) {
            throw new UndoException();
        }
        redoDeque.push(currentState);
        State newCurrentState = undoDeque.pop();
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    /**
     * Redoes the state at the top of the redoDeque, adding the currentState back into the top of the undoDeque.
     */
    public HistoryManager redo() throws RedoException {
        if (!canRedo()) {
            throw new RedoException();
        }
        State newCurrentState = redoDeque.pop();
        addToUndo(currentState);
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    private boolean canUndo() {
        return undoDeque.size() > 0;
    }

    private boolean canRedo() {
        return redoDeque.size() > 0;
    }

    /**
     * Add a state to the undo deque, removes the oldest states to make space if necessary.
     */
    private void addToUndo(State state) {
        undoDeque.push(state);
        while (undoDeque.size() > MAX_UNDO_COUNT) {
            undoDeque.removeLast();
        }
        assert(undoDeque.size() <= MAX_UNDO_COUNT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof HistoryManager) {
            HistoryManager otherHistory = (HistoryManager) other;
            return currentState.equals(otherHistory.currentState)
                    && compareHistory(otherHistory);
        }
        return false;
    }

    /**
     * Helper method to compare the undoDeque and redoDeque as {@code equals()} is not overridden in java's ArrayDeque.
     */
    private boolean compareHistory(HistoryManager otherHistory) {
        boolean undoEquals = new ArrayList<>(undoDeque).equals(new ArrayList<>(otherHistory.undoDeque));
        boolean redoEquals = new ArrayList<>(redoDeque).equals(new ArrayList<>(otherHistory.redoDeque));
        return undoEquals && redoEquals;
    }
}
