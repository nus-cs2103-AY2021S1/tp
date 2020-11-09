// Either.java

package chopchop.commons.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.NoSuchElementException;
import static chopchop.commons.util.Enforce.enforceAny;

/**
 * A container class that wraps a value of either type L (the 'left' value), or
 * of type R (the 'right' value), but not both at once -- an Either contains only
 * either a left or a right value.
 */
public class Either<L, R> {

    private final L leftValue;
    private final R rightValue;

    protected Either(L leftVal, R rightVal) {
        this.leftValue = leftVal;
        this.rightValue = rightVal;

        enforceAny(this.leftValue == null, this.rightValue == null);
    }

    /**
     * Returns true if the Either contains a left value, false otherwise.
     *
     * @return true if this is a left value, false otherwise.
    */
    public boolean isLeft() {
        return this.leftValue != null;
    }

    /**
     * Returns true if the Either contains a right value, false otherwise.
     *
     * @return True if this is a right value, false otherwise.
     */
    public boolean isRight() {
        return this.rightValue != null;
    }

    /**
     * Obtains the stored left value in the Either. If the Either contains a
     * right value, throws a NoSuchElementException.
     *
     * @return The left value contained in the Either.
     * @throws NoSuchElementException if the Either contains a right value
     */
    public L fromLeft() {
        return Optional.ofNullable(this.leftValue)
            .orElseThrow(() -> new NoSuchElementException("Either was not left"));
    }

    /**
     * Obtains the stored right value in the Either. If the Either contains a
     * left value, throws a NoSuchElementException.
     *
     * @return The right value contained in the Either.
     * @throws NoSuchElementException if the Either contains a left value
     */
    public R fromRight() {
        return Optional.ofNullable(this.rightValue)
            .orElseThrow(() -> new NoSuchElementException("Either was not right"));
    }

    /**
     * Obtains the stored left value in the Either. If the Either contains a
     * right value, returns an empty {@code Optional}.
     *
     * @return The left value contained in the Either, optionally.
     * @throws NoSuchElementException if the Either contains a right value
     */
    public Optional<L> fromLeftOpt() {
        return Optional.ofNullable(this.leftValue);
    }

    /**
     * Obtains the stored right value in the Either. If the Either contains a
     * left value, returns an empty {@code Optional}.
     *
     * @return The right value contained in the Either, optionally.
     * @throws NoSuchElementException if the Either contains a left value
     */
    public Optional<R> fromRightOpt() {
        return Optional.ofNullable(this.rightValue);
    }

    /**
     * Maps the given function onto the left value of the Either, returning a new Either with
     * the updated left value. If it contains a right value, it returns the Either unmodified.
     *
     * @param fn the function to map with.
     * @return the new Either with the corresponding value.
     */
    public <L1> Either<L1, R> mapLeft(Function<? super L, ? extends L1> fn) {
        return this.isLeft()
            ? Either.left(fn.apply(this.leftValue))
            : Either.right(this.rightValue);
    }

    /**
     * Maps the given function onto the right value of the Either, returning a new Either with
     * the updated right value. If it contains a left value, it returns the Either unmodified.
     *
     * @param fn the function to map with.
     * @return the new Either with the corresponding value.
     */
    public <R1> Either<L, R1> mapRight(Function<? super R, ? extends R1> fn) {
        return this.isRight()
            ? Either.right(fn.apply(this.rightValue))
            : Either.left(this.leftValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Either<?, ?>)) {
            return false;
        }

        var other = (Either<?, ?>) obj;
        return Objects.equals(this.leftValue, other.leftValue)
            && Objects.equals(this.rightValue, other.rightValue);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)",
            this.leftValue == null ? "Right" : "Left",
            this.leftValue == null ? this.rightValue : this.leftValue);
    }


    /**
     * Creates an Either containing a left value.
     *
     * @return An Either containing a left value.
     */
    public static <L, R> Either<L, R> left(L l) {
        return new Either<L, R>(l, null);
    }

    /**
     * Creates an Either containing a right value.
     *
     * @return An Either containing a right value.
     */
    public static <L, R> Either<L, R> right(R r) {
        return new Either<L, R>(null, r);
    }
}
