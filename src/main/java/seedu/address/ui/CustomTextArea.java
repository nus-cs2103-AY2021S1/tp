package seedu.address.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class CustomTextArea extends TextArea {
    final TextArea myTextArea = this;

    public CustomTextArea() {
        addEventFilter(KeyEvent.KEY_PRESSED, new TabAndEnterHandler());
    }

    class TabAndEnterHandler implements EventHandler<KeyEvent> {
        private KeyEvent recodedEvent;

        @Override public void handle(KeyEvent event) {
            if (recodedEvent != null) {
                recodedEvent = null;
                return;
            }

            Parent parent = myTextArea.getParent();
            if (parent != null) {
                switch (event.getCode()) {
                case ENTER:
                    if (event.isControlDown()) {
                        recodedEvent = recodeWithoutControlDown(event);
                        myTextArea.fireEvent(recodedEvent);
                    } else {
                        Event parentEvent = event.copyFor(parent, parent);
                        myTextArea.getParent().fireEvent(parentEvent);
                    }
                    event.consume();
                    break;
                default:
                    break;
                }
            }
        }

        private KeyEvent recodeWithoutControlDown(KeyEvent event) {
            return new KeyEvent(
                    event.getEventType(),
                    event.getCharacter(),
                    event.getText(),
                    event.getCode(),
                    event.isShiftDown(),
                    false,
                    event.isAltDown(),
                    event.isMetaDown()
            );
        }
    }
}
