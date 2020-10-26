package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;

public class JsonMenuManagerStorageTest {
    public List<Food> getExpectedFood() {
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Prata", 1, new HashSet<>()));
        list.add(new Food("Milo", 1.5, new HashSet<>()));
        list.add(new Food("Nasi Goreng", 4.5, new HashSet<>()));
        return list;
    }
    @Test
    public void readMenuManager_equalsExpectedMenu() throws DataConversionException {
        JsonMenuManagerStorage storage = new JsonMenuManagerStorage(Paths
                .get("src/test/data/JsonMenuManagerStorageTest/typicalMenuManager.json"));
        ReadOnlyMenuManager jsonMenuManager = storage.readMenuManager().get();
        MenuManager manager = new MenuManager();
        manager.setMenu(getExpectedFood());
        assertEquals(jsonMenuManager.getFoodList(), manager.getFoodList());
    }

    @Test
    public void readMenuManager_invalidMenuManager_throwsDataConversionException() {
        JsonMenuManagerStorage storage = new JsonMenuManagerStorage(Paths
                .get("src/test/data/JsonMenuManagerStorageTest/invalidMenuManager.json"));
        assertThrows(DataConversionException.class, storage::readMenuManager);
    }

    @Test
    public void getFilePath_equalsInputtedFilePath() {
        Path filePath = Paths.get("src/test/data/JsonMenuManagerStorageTest/typicalMenuManager.json");
        JsonMenuManagerStorage storage = new JsonMenuManagerStorage(filePath);
        assertEquals(filePath, storage.getMenuManagerFilePath());
    }
}
