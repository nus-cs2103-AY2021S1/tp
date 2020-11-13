package seedu.studybananas.ui.commons;

import javafx.event.EventHandler;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ResponsePopUp {
    private static final double BOTTOM_PADDING = 10.0;
    private final Popup popup;
    private Stage stage;
    private Response response;

    /**
     * Constructor for ResponsePopup window with the stage
     */
    public ResponsePopUp(Stage stage) {
        this.stage = stage;

        popup = new Popup();
        // Make sure popup stays in the window
        popup.setAutoFix(true);
        // Close popup on click outside
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        // Handle position.
        setPosition(stage);
    }

    /**
     * Open the response popup.
     */
    public void open() {
        assert response != null : "should set the content before you open the pop up window.";
        hide();
        this.popup.show(stage);
        response.play();
    }

    /**
     * Hide the response popup.
     */
    public void hide() {
        this.popup.hide();
    }

    /**
     * Set the content of the popup window.
     * @param response
     */
    public void setContent(Response response) {
        this.popup.getContent().clear();
        this.popup.getContent().add(response.getRoot());
        this.response = response;
    }

    private void setPosition(Stage stage) {
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
                popup.setY(stage.getY() + stage.getHeight() - popup.getHeight() - BOTTOM_PADDING);
            }
        });
    }

}
