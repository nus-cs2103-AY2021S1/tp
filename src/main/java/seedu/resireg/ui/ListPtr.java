package seedu.resireg.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Code for ListPtr is adapted from addressbook-level4, which can be found at
 * https://github.com/se-edu/addressbook-level4.
 */
/**
 * A class that contains a pointer to an element in the list, for
 * easier iteration through the list.
 */
public class ListPtr {
    private List<String> list;
    private int index;

    /**
     * Constructs a {@code ListPtr} object using a list.
     * The pointer points to the last element in the {@code list}.
     */
    public ListPtr(List<String> list) {
        this.list = new LinkedList<>(list);
        index = this.list.size() - 1;
    }

    /**
     * Appends {@code item} to the end of the list.
     * Guaranteed to be O(1)
     */
    public void add(String item) {
        list.add(item);
    }

    /**
     * Returns true if calling {@code #next()} does not throw a {@code NoSuchElementException}.
     */
    public boolean hasNext() {
        int next = index + 1;
        return isWithinBounds(next);
    }

    /**
     * Returns true if calling {@code #previous()} does not throw a {@code NoSuchElementException}.
     */
    public boolean hasPrevious() {
        int prev = index - 1;
        return isWithinBounds(prev);
    }

    /**
     * Returns true if calling {@code #current()} does not throw a {@code NoSuchElementException}.
     */
    public boolean hasCurrent() {
        return isWithinBounds(index);
    }

    private boolean isWithinBounds(int index) {
        return index >= 0 && index < list.size();
    }

    /**
     * Shifts the pointer position rightwards and returns the next element in the list.
     * @throws NoSuchElementException if there is no more next element.
     */
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
        return list.get(index);
    }

    /**
     * Shifts the pointer position leftwards and returns the previous element in the list.
     * @throws NoSuchElementException if there is no more previous element.
     */
    public String previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
        return list.get(index);
    }

    /**
     * Returns the current element in the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public String current() {
        if (!hasCurrent()) {
            throw new NoSuchElementException();
        }
        return list.get(index);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListPtr)) {
            return false;
        }

        // state check
        ListPtr o = (ListPtr) other;
        return list.equals(o.list) && index == o.index;
    }
}

