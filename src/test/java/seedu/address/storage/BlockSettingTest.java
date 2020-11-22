package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BlockSettingTest {
    private static final String BLOCK_A_NAME = "A";
    private static final String BLOCK_A_FLOORS = "Floors : 1 to 4";
    private static final String BLOCK_A_ROOMS = "Rooms : 101 to 420";

    private static final BlockSetting BLOCK_A = new BlockSetting(BLOCK_A_NAME);

    @Test
    public void blockSetting_equalBlockSetting() {
        BlockSetting blockA = new BlockSetting(BLOCK_A_NAME);
        assertEquals(blockA, BLOCK_A);
    }

    @Test
    public void blockSetting_constructor_correctBlockName() {
        BlockSetting blockA = new BlockSetting(BLOCK_A_NAME);
        assertEquals(BLOCK_A_NAME, blockA.getBlockName());
    }

    @Test
    public void blockSetting_getLevels_correctRoomRangeString() {
        BlockSetting blockA = new BlockSetting(BLOCK_A_NAME);
        assertEquals(BLOCK_A_ROOMS, blockA.getRooms());
        assertEquals(BLOCK_A_FLOORS, blockA.getFloors());
    }
}
