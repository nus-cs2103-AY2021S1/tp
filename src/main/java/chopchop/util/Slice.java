// Slice.java

package chopchop.util;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Represents a non-owning array-like reference. Allows for efficient
 * substring-like operations.
 */
public class Slice<T>
{
    public final T[] xs;
    public final int start;
    public final int length;

    /**
     * Constructs a new Slice from the backing array
     *
     * @param xs     the backing array
     * @param begin  the starting index of the slice
     * @param length the length of the slice
     */
    public Slice(T[] xs, int begin, int length)
    {
        this.xs     = xs;
        this.start  = begin;
        this.length = length;
    }

    /**
     * Constructs a new Slice from the backing array. The length of the slice is the
     * length of the backing array, minus the starting offset.
     *
     * @param xs     the backing array
     * @param begin  the starting index of the slice
     */
    public Slice(T[] xs, int begin)
    {
        this.xs     = xs;
        this.start  = begin;

        if (begin > xs.length) {
            this.length = 0;
        } else {
            this.length = xs.length - begin;
        }
    }

    /**
     * Constructs a new Slice from the backing array. The slice spans the whole array.
     *
     * @param xs     the backing array
     */
    public Slice(T[] xs)
    {
        this.xs     = xs;
        this.start  = 0;
        this.length = xs.length;
    }

    /**
     * Get the starting index of the slice.
     *
     * @return the starting index of the slice.
     */
    public int begin()
    {
        return this.start;
    }

    /**
     * Get one-past-the-last index of the slice.
     *
     * @return one-past-the-index of the slice.
     */
    public int end()
    {
        return this.start + this.length;
    }

    /**
     * Get the length of the slice.
     *
     * @return the length of the slice.
     */
    public int size()
    {
        return this.length;
    }

    /**
     * Get the ith element of the slice.
     *
     * @param i the index of the element to get
     * @return the element at the specified index
     */
    public T get(int i)
    {
        return this.xs[this.start + i];
    }

    /**
     * Get the first element of the slice.
     *
     * @return the first element of the slice
     */
    public T front()
    {
        assert this.size() > 0;
        return this.xs[this.start];
    }

    /**
     * Get the last element of the slice.
     *
     * @return the last element of the slice
     */
    public T back()
    {
        assert this.size() > 0;
        return this.xs[this.start + this.length - 1];
    }

    /**
     * Return a new slice without the first n elements. If n is larger than the length of
     * the slice, an empty slice will be returned.
     *
     * @param n the number of elements to drop
     * @return the new subslice
     */
    public Slice<T> drop(int n)
    {
        if (this.length < n) {
            n = this.length;
        }

        return new Slice<T>(this.xs, this.start + n, this.length - n);
    }

    /**
     * Return a new slice with only the first n elements. If n is larger than the length of
     * the slice, a copy of the original slice will be returned.
     *
     * @param n the number of elements to take
     * @return the new subslice
     */
    public Slice<T> take(int n)
    {
        if (this.length < n) {
            n = this.length;
        }

        return new Slice<T>(this.xs, this.start, n);
    }

    /**
     * Return a new slice with only the last n elements. If n is larger than the length of
     * the slice, a copy of the original slice will be returned.
     *
     * @param n the number of elements to take (from the back)
     * @return the new subslice
     */
    public Slice<T> takeLast(int n)
    {
        if (this.length < n) {
            n = this.length;
        }

        return new Slice<T>(this.xs, this.start + this.length - n, n);
    }

    /**
     * Return a new slice with the given bounds.
     *
     * @param begin the starting index of the new slice
     * @param len   the length of the new slice
     * @return the new subslice
     */
    public Slice<T> slice(int begin, int len)
    {
        return new Slice<T>(this.xs, this.start + begin, len);
    }

    /**
     * Return a Stream from the elements in the slice
     *
     * @return a Stream of elements
     */
    public Stream<T> stream()
    {
        return Stream
            .iterate(this.start, i -> i + 1)
            .map(i -> this.xs[i])
            .limit(this.size());
    }

    @Override
    public String toString()
    {
        return Arrays.toString(Arrays.copyOfRange(this.xs, this.begin(), this.end()));
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Slice<?>))
            return false;

        return ((Slice<?>) other).stream().collect(Collectors.toList())
            .equals(this.stream().collect(Collectors.toList()));
    }

    @Override
    public int hashCode()
    {
        // just cheat
        return this.stream()
            .collect(Collectors.toList())
            .hashCode();
    }
}
