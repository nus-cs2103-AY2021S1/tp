package seedu.jarvis.scraper;

import java.io.IOException;

import seedu.jarvis.commons.exceptions.WrongLoginDetailsException;

public interface Scraper {
    void startScraping() throws WrongLoginDetailsException, IOException;
}
