package PageObjects;
import Elements.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FictionBook extends BasePage{
    public FictionBook(WebDriver driver_){
        super(driver_);
    }

    @FindBy(id = "price[min]")
    private TextField minPrice;

    @FindBy(id = "price[max]")
    private TextField maxPrice;

    @FindBy(xpath = "//*[@param = \"49445\"]//label")
    private List<Html_label> producers;

    @FindBy(id = "submitprice")
    private Button submitPriceButton;

    @FindBy(css =".over-wraper .g-i-tile-i-box-desc >div:first-child > div>a")
    private List<WebElement> results;

    public void set_min_price(Integer price){
        minPrice.setValue(price.toString());
    }

    public void set_max_price(Integer price) {
        maxPrice.setValue(price.toString());
    }

    public void submit_filter_price(){
        submitPriceButton.click();
    }

    public void click_on_result(int index){
        results.get(index).click();
    }

    public String getMaxPrice(){
        return maxPrice.getValue();
    }

    public String getMinPrice(){
        return minPrice.getValue();
    }

    public List<Html_label> getProducers() {
        return producers;
    }
}
