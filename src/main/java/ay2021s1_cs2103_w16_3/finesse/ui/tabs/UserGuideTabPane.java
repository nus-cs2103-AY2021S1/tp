package ay2021s1_cs2103_w16_3.finesse.ui.tabs;

import java.util.logging.Logger;

import ay2021s1_cs2103_w16_3.finesse.commons.core.LogsCenter;
import ay2021s1_cs2103_w16_3.finesse.ui.UiPart;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

/**
 * Tab pane that displays the user guide.
 */
public class UserGuideTabPane extends UiPart<StackPane> {
    private static final String FXML = "UserGuideTabPane.fxml";

    // Links
    private static final String GITHUB_PAGES_DOMAIN = "https://ay2021s1-cs2103t-w16-3.github.io";
    private static final String USER_GUIDE_URL = "https://ay2021s1-cs2103t-w16-3.github.io/tp/UserGuide.html";
    private static final String NO_EXTERNAL_SITE_PAGE_URL =
            "https://ay2021s1-cs2103t-w16-3.github.io/tp/NoExternalSite.html";

    // Constants
    private static final double REFRESH_TIMEOUT_DELAY = 10000.0;

    // Logging messages
    private static final String WEB_ENGINE_EXTERNAL_SITE_REQUEST_BLOCKED =
            "Request to load page on external site blocked";
    private static final String WEB_ENGINE_WORKER_STATE_CANCELLED = "Page load cancelled: ";
    private static final String WEB_ENGINE_WORKER_STATE_FAILED = "Unable to load page (no internet connection): ";
    private static final String WEB_ENGINE_WORKER_STATE_RUNNING = "Loading page: ";
    private static final String WEB_ENGINE_WORKER_STATE_SCHEDULED = "Page load scheduled: ";
    private static final String WEB_ENGINE_WORKER_STATE_SUCCEEDED = "Page successfully loaded: ";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView webView;

    /**
     * Constructs a {@code UserGuideTabPane}.
     */
    public UserGuideTabPane() {
        super(FXML);
        initializeUserGuide();
    }

    /**
     * Initializes the {@code WebEngine} that is backing the {@code WebView}.
     */
    private void initializeUserGuide() {
        // Disable right click.
        webView.setContextMenuEnabled(false);

        WebEngine webEngine = webView.getEngine();

        // Override fonts to call the default macOS system font without using '-apple-system'.
        webEngine.setUserStyleSheetLocation(getClass().getResource("/view/WebView.css").toString());

        // Log user agent.
        logger.info("User Agent: " + webEngine.getUserAgent());

        webEngine.locationProperty().addListener((observableValue, oldUrl, newUrl) -> {
            if (!newUrl.startsWith(GITHUB_PAGES_DOMAIN)) {
                logger.info(WEB_ENGINE_EXTERNAL_SITE_REQUEST_BLOCKED);

                // Block requests to external sites.
                Platform.runLater(() -> {
                    // Load the 'No External Site' page.
                    webEngine.load(NO_EXTERNAL_SITE_PAGE_URL);
                });
            }
        });

        // Initialize refresh task.
        Timeline refreshTask = new Timeline(new KeyFrame(Duration.millis(REFRESH_TIMEOUT_DELAY),
            actionEvent -> refreshPage()));

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            String location = webEngine.getLocation();

            // Stop the refresh task to prevent multiple tasks running.
            refreshTask.stop();

            switch (newState) {
            case CANCELLED:
                logger.info(WEB_ENGINE_WORKER_STATE_CANCELLED + location);
                break;
            case FAILED:
                logger.warning(WEB_ENGINE_WORKER_STATE_FAILED + location);
                refreshTask.playFromStart();
                break;
            case RUNNING:
                logger.info(WEB_ENGINE_WORKER_STATE_RUNNING + location);
                break;
            case SCHEDULED:
                logger.info(WEB_ENGINE_WORKER_STATE_SCHEDULED + location);
                break;
            case SUCCEEDED:
                logger.info(WEB_ENGINE_WORKER_STATE_SUCCEEDED + location);
                break;
            default:
            }
        });

        webEngine.load(USER_GUIDE_URL);
    }

    /**
     * Refreshes the WebView.
     */
    public void refreshPage() {
        // Cannot simply reload the page because if the page has not loaded a single time,
        // reloading does not work.
        String currentPage = webView.getEngine().getLocation();
        webView.getEngine().load(currentPage);
    }
}
