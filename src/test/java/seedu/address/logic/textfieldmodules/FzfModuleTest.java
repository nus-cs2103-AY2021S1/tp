package seedu.address.logic.textfieldmodules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
class FzfModuleTest extends HeadlessTestBase {
    private List<String> sampleNameList = Arrays.asList("Alice", "Bravo", "Charlie");

    private TextField textField;
    private FzfModule fzfModule;
    private StackPane stackPane;

    @Start
    private void start(Stage stage) {
        textField = new TextField();
        fzfModule = FzfModule.attachTo(textField, () -> sampleNameList);
        stackPane = new StackPane(textField);
        stage.setScene(new Scene(stackPane));
        stage.show();

    }
    @Test
    public void attachTo_nullTextField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> FzfModule.attachTo(null, () -> sampleNameList));
    }
    @Test
    public void attachTo_nullListSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> FzfModule.attachTo(textField, null));
    }
    @Test
    public void textFieldWithFzfModule_triggerFzf_showFzfMenu(FxRobot robot) {
        robot.press(KeyCode.CONTROL, KeyCode.SPACE);
        assertTrue(robot.listWindows().stream().anyMatch(win -> win instanceof ContextMenu));
    }
    @Test
    public void textFieldWithFzfModule_lockInFirstSuggestion_success(FxRobot robot) {

        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .press(KeyCode.ENTER);
        robot.release(new KeyCode[]{});

        String expected = sampleNameList.get(0);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithFzfModule_navigateAndLockInLastSuggestion_success(FxRobot robot) {
        robot.press(KeyCode.CONTROL, KeyCode.SPACE);

        // navigate down the list to last element
        for (int i = 0; i < sampleNameList.size() - 1; i++) {
            robot.press(KeyCode.DOWN);
            robot.release(KeyCode.DOWN);
        }

        robot.press(KeyCode.ENTER)
                .release(KeyCode.ENTER);

        String expected = sampleNameList.get(sampleNameList.size() - 1);
        String actual = textField.getText();

        assertEquals(expected, actual);
    }
    @Test
    public void textFieldWithFzfModule_nonEmptyQuery_validNumberOfSuggestions(FxRobot robot) {

        String query = "br";

        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .write(query);
        robot.release(new KeyCode[]{});

        long expected = sampleNameList.stream().filter(x -> x.toLowerCase().contains(query)).count();
        long actual = fzfModule.getMenu().getItems().stream().count();

        assertEquals(expected, actual);
    }

    @Test
    public void textFieldWithFzfModule_exitFzfMode_hideFzfMenu(FxRobot robot) {
        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .press(KeyCode.ESCAPE);
        assertTrue(robot.listWindows().stream().allMatch(win -> !(win instanceof ContextMenu)));
    }
    @Test
    public void textFieldWithFzfModule_queryHasNoValidSuggestions_hideFzfMenu(FxRobot robot) {
        String query = "Debra";

        robot.press(KeyCode.CONTROL, KeyCode.SPACE)
                .write(query);
        robot.release(new KeyCode[]{});

        long expected = sampleNameList.stream().filter(x -> x.toLowerCase().contains(query)).count();
        long actual = fzfModule.getMenu().getItems().stream().count();

        assertEquals(expected, actual);
        assertTrue(robot.listWindows().stream().allMatch(win -> !(win instanceof ContextMenu)));
    }

}
