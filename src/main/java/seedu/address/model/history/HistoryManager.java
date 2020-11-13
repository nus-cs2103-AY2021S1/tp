package seedu.address.model.history;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class HistoryManager<T> implements History<T> {
    private Node<T> history;

    public HistoryManager() {
        history = new Node<>();
    }

    public void setHistory(Node<T> history) {
        this.history = history;
    }

    @Override
    public boolean hasHistory() {
        assert history != null;
        return history.hasPrevious();
    }

    @Override
    public T getHistory() {
        assert hasHistory();
        rollBack();
        T history = this.history.getValue();
        return history;
    }

    @Override
    public boolean hasFuture() {
        assert history != null;
        return history.hasNext();
    }

    @Override
    public T getFuture() {
        assert hasFuture();
        rollForward();
        return history.getValue();
    }

    @Override
    public void saveToHistory(T toSave) {
        requireAllNonNull(toSave);
        assert history != null;
        Node<T> nodeToSave = new Node<>(toSave);
        Node<T> nullNode = new Node<>();
        nodeToSave.connectTo(nullNode);
        if (history.hasPrevious()) {
            history.getPrevious().connectTo(nodeToSave);
        }
        history = nullNode;
    }

    @Override
    public void saveToFuture(T toSave) {
        requireAllNonNull(toSave);
        assert hasHistory();
        if (!isNull()) {
            return;
        }

        Node<T> nodeToSave = new Node<>(toSave);
        Node<T> nullNode = new Node<>();
        nodeToSave.connectTo(nullNode);
        history.getPrevious().connectTo(nodeToSave);
        history = nodeToSave;
    }

    private void rollBack() {
        assert history.hasPrevious();
        history = history.getPrevious();
    }

    private void rollForward() {
        assert history.hasNext();
        history = history.getNext();
    }

    private boolean isNull() {
        assert history != null;
        return history.isNull();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoryManager<?> that = (HistoryManager<?>) o;
        return Objects.equals(history, that.history);
    }
}
