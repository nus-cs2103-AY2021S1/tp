package seedu.address.model.history;

public interface History<T> {
    boolean hasHistory();
    T getHistory();
    boolean hasFuture();
    T getFuture();
    void saveToHistory(T history);
    void saveToFuture(T history);
}
