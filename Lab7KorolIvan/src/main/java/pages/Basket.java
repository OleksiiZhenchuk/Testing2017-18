package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class Basket extends BasePage{
    public Basket(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".hub-i-link.hub-i-cart-link.sprite-side.whitelink")
    private WebElement myBusket;

    @FindBy(css = ".popup-close")
    private WebElement close;

    private List<WebElement> basketOrders;

    public void openBasket(){
        myBusket.click();
    }

    public void closeBasket(){
        close.click();
    }

    public int basketSize(){
        By.ByCssSelector by = new By.ByCssSelector(".cart-other");
        basketOrders = getDriver().findElements(by);
        return basketOrders.size();
    }
}
