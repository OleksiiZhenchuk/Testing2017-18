package PageObjects;

import Elements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {

    public BasketPage (WebDriver driver){
        super(driver);
    }

    @FindBy(id = "popup-checkout")
    private Button confirmOrderButton;

    public void confirm_order(){
        confirmOrderButton.click();
    }
}
