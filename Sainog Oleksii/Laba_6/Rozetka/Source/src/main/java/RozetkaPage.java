import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class RozetkaPage {
    private WebDriver driver;
    RozetkaPage(WebDriver new_driver){
        PageFactory.initElements(new_driver, this);
        this.driver = new_driver;
    }

    @FindBy(id = "price[min]")
    private WebElement filterMin;

    @FindBy(id = "submitprice")
    private WebElement filterSubmit;

    @FindBy(css = "[class=\"g-price\"][name = \"price\"] > div > span:first-child")
    private List<WebElement> prices;

    void set_filterMin(Integer price){
        filterMin.sendKeys(Keys.CONTROL + "a");
        filterMin.sendKeys(Keys.DELETE, price.toString());
    }

    void set_filterSubmit(){
        filterSubmit.click();
    }

    List<Integer> getPrices(){
        List<Integer> arrayPrices = new ArrayList<Integer>();
        for(WebElement iter: prices){
            arrayPrices.add(Integer.parseInt(iter.getText()));
        }
        return arrayPrices;
    }

    Integer get_filterMin(){
        String minPrice = filterMin.getAttribute("value");
        return ((minPrice != null)||(!minPrice.equals("")))?
                Integer.parseInt(minPrice): null;
    }
}
