package seedu.address.storage;

import seedu.address.model.person.Room;

/**
 * Represents a block with its settings.
 */
public class BlockSetting {
    private final String blockName;

    public BlockSetting(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockName() {
        return blockName;
    }

    public String getFloors() {
        return Room.getFloorRange();
    }

    public String getRooms() {
        return Room.getRoomRange();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BlockSetting)) {
            return false;
        }

        BlockSetting otherBlockSetting = (BlockSetting) other;
        return otherBlockSetting.getBlockName().equals(getBlockName());
    }
}
