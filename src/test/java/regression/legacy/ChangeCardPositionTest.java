package regression.legacy;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ChangeCardPositionTest extends TrelloBaseTest {


    @Test
    public void moveCardToAnotherList() throws InterruptedException {

        driver().get("https://trello.com/b/UWcqx87C/jacksparrowtitle");

        Actions actions = new Actions(driver());

        actions.dragAndDrop(driver().findElement(By.cssSelector("[href*='2-card-2']")), driver().findElement(By.cssSelector(".js-list:nth-child(3)"))).perform();


    }


    //@Test
    public void dragAndDrop(By first, By second) {

        Actions actions = new Actions(driver());

        actions.dragAndDrop(driver().findElement(first), driver().findElement(second)).perform();

    }
}
