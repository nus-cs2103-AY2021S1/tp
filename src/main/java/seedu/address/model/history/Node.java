package seedu.address.model.history;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a node in a doubly- inked list.
 * @param <T> the type of the doubly-linked list.
 */
public class Node<T> {
    private T value;
    private Node<T> next;
    private Node<T> previous;

    /**
     * Constructs an empty {@code Node}
     */
    public Node() {
        this.value = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * Constructs a {@code Node} with the given value
     */
    public Node(T value) {
        requireAllNonNull(value);
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public boolean hasNext() {
        return !isNull() && !next.isNull();
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    private void setNext(Node<T> next) {
        this.next = next;
    }

    private void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isEmpty() {
        return isNull() && !hasPrevious();
    }

    /**
     * Creates a bi-directional forward connection to another node.
     * @param toConnect the node to be connected to
     */
    public void connectTo(Node<T> toConnect) {
        requireAllNonNull(toConnect);
        disconnectNext();
        setNext(toConnect);
        toConnect.disconnectPrevious();
        toConnect.setPrevious(this);
    }

    private void disconnectPrevious() {
        if (previous != null) {
            Node<T> temp = previous;
            previous = null;
            temp.disconnectNext();
        }
    }

    private void disconnectNext() {
        if (next != null) {
            Node<T> temp = next;
            next = null;
            temp.disconnectPrevious();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }
}
