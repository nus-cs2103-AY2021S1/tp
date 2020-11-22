package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} is in the current block.
 */
public class RoomInBlockPredicate implements Predicate<Person> {
    private final Block block;

    /**
     * Constructs a {@code RoomInBlockPredicate}.
     *
     * @param block A block to check with people's block.
     */
    public RoomInBlockPredicate(Block block) {
        requireNonNull(block);
        this.block = block;
    }

    @Override
    public boolean test(Person person) {
        return person.getBlock().equals(block);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomInBlockPredicate // instanceof handles nulls
                && block.equals(((RoomInBlockPredicate) other).block)); // state check
    }
}
