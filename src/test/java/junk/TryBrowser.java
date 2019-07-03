package junk;

import com.trello.ui.core.BrowserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * Created by horobets on Jun 20, 2019
 */
@Epic("")
@Feature("")
@Story("JACK")
public class TryBrowser extends BrowserFactory {


    @Test
    public void openClose() {
        driver().get("https://google.com");
    }


}