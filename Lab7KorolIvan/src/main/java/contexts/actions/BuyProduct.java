package contexts.actions;

import org.openqa.selenium.By;
import pages.CheckOut;
import pages.ProductPage;
import utill.Waiters;

public class BuyProduct {
    public static CheckOut buyAndSubmitProduct(ProductPage page){
        page.getBuyButton().click();
        //Waiters.thredsleep(2000);

        By by = new By.ByCssSelector("#popup-checkout");
        //Waiters.waitExpected(getDriver(), by, 5000, 50 );
        Waiters.thredsleep(2000);
        page.setSubmitButton(page.getDriver().findElement(by));
        //Waiters.waitClickableAndDisplayed(getDriver(), submitButton, 5000, 50 );
        page.getSubmitButton().click();
        return new CheckOut(page.getDriver());
        //return page.buyAndSubmit();
    }
}
