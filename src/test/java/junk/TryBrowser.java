package junk;

import com.trello.ui.core.BrowserFactory;
import org.testng.annotations.Test;

/**
 * Created by horobets on Jun 20, 2019
 */
public class TryBrowser extends BrowserFactory {


    @Test
    public void openClose() {
        driver().get("https://google.com");
    }


}