package seedu.address.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * A clock in the form of a dynamic text.
 */
public class TextClock {
    private final Timeline timeline;

    /**
     * Constructor for the text clock object.
     *
     * @param text A given Text object to set the time value to.
     */
    public TextClock(Text text, TimeZone timeZone) {
        timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            dateFormat.setTimeZone(timeZone);
            text.setText(dateFormat.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Starts the clock.
     */
    public void play() {
        timeline.play();
    }

    /**
     * Pauses the clock.
     */
    public void pause() {
        timeline.pause();
    }

}
