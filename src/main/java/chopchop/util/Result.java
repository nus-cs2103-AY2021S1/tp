// Result.java

package chopchop.util;

import java.util.Optional;
import java.util.function.Function;

/**
 * The Result class is used to encapsulate the result of some computation
 * producing a value of type {@code T} which may or may not fail. It also holds
 * a string value describing the reason for the failure or error, if indeed the
 * wanted value of {@code T} was not produced successfully.
 *
 * In other words, a {@code Result<T>} is an {@code Optional<T>} that also contains
 * a reason for the error.
 */
public class Result<T> extends Either<String, T> {

    /**
     * This constructor is private; use the of() and error() methods to create
     * Results.
     */
    private Result(T value, String message) {

        // note that, by convention, an Either<L, R> holds the failure case in the
        // left variant, and the success case in the right variant.
        super(message, value);
    }

    /**
     * Gets the contained value.
     *
     * @return the contained value
     * @throws NoSuchElementException if the Result was invalid (ie. does not have a value)
     */
    public T getValue() {
        return super.fromRight();
    }

    /**
     * Gets the contained value.
     *
     * @return the contained value, optionally
     */
    public Optional<T> getValueOpt() {
        return super.fromRightOpt();
    }

    /**
     * Checks whether this Result contains a value.
     *
     * @return true iff the Result contains a value.
     */
    public boolean hasValue() {
        return super.isRight();
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     * @throws NoSuchElementException if the Result was valid (ie. does not have an error message)
     */
    public String getError() {
        return super.fromLeft();
    }

    /**
     * Gets the error message.
     *
     * @return the error message, optionally
     */
    public Optional<String> getErrorOpt() {
        return super.fromLeftOpt();
    }

    /**
     * Checks whether this Result contains an error message.
     *
     * @return true iff the Result contains an error message.
     */
    public boolean isError() {
        return super.isLeft();
    }

    /**
     * Performs a functor map on the value of this Result, returning a new result
     * with the modified value. If the original result was an error variant, then
     * the error message is forwarded unchanged.
     *
     * @param fn the function to apply
     * @return   the new result
     */
    public <R> Result<R> map(Function<? super T, ? extends R> fn) {
        return new Result<R>(
            super.fromRightOpt().map(fn).orElse(null),
            super.fromLeftOpt().orElse(null)
        );
    }

    /**
     * If the Result contains a value, return it, otherwise return {@code other}.
     *
     * @param other the alternative value to use
     * @return      either the value contained in the result, or the alternative provided
     */
    public T orElse(T other) {
        return super.fromRightOpt().orElse(other);
    }

    /**
     * Creates a new {@code Result} containing the successfully-computed value.
     *
     * @param value the value
     * @return      a {@code Result} containing the given value
     */
    public static <T> Result<T> of(T value) {
        return new Result<T>(value, null);
    }

    /**
     * Creates a new {@code Result} with an error message describing why a value
     * could not be computed.
     *
     * @param message   the error message
     * @return          a {@code Result} without a value, with the given error message.
     */
    public static <T> Result<T> error(String message) {
        return new Result<T>(null, message);
    }
}
