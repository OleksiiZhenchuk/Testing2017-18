package pages;

import elements.*;
import extentions.NotExistProducerExeption;
import extentions.NotHaveProductsExeption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import utill.Waiters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class SearchPage extends BasePage {
    private static final String ROZQTKA_URL = "https://rozetka.com.ua/ua/vino/c4594285/filter/";

    public SearchPage (WebDriver driver){
        super(driver);
    }

    public SearchPage start(String url){
        getDriver().get(url);
        return this;
    }

    @FindBy(id = "price[min]")
    private TextField minPrice;

    @FindBy(id = "price[max]")
    private TextField  maxPrice;

    @FindBy(id = "submitprice")
    private WebElement submitPriceButton;

    @FindBys(
            @FindBy(xpath = "//*[@id='sort_producer']/li/label")
    )
    private List<HtmlLabel> myFirstLables;

    private HtmlLabel myFirstLable;

    @FindBys(
            @FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/div/div/div[@class='g-i-tile-i-box g-i-large-tile-i-box']" +
                    "/div/div[@name='prices_active_element_original']/div[@name='buy_catalog_small']")
    )
    private List<WebElement> miniBaskets;

    private Basket basket;


    public SearchPage  setMinimumPrice(Integer price){
        if(price == null) return this;
        else {
            minPrice.setValue(price.toString());
            return this;
        }
    }

    public SearchPage  setMaximumPrice(Integer price){
        if(price == null) return this;
        else {
            maxPrice.setValue(price.toString());
            return this;
        }
    }

    public SearchPage  priceSumit(){
        submitPriceButton.sendKeys(Keys.ENTER);
        return this;
    }


    public Basket getBasket() {
        return new Basket(getDriver());
    }

    public TextField getMinPrice() {
        return minPrice;
    }

    public SearchPage setMinPrice(TextField minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public TextField getMaxPrice() {
        return maxPrice;
    }

    public SearchPage setMaxPrice(TextField maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public WebElement getSubmitPriceButton() {
        return submitPriceButton;
    }

    public SearchPage setSubmitPriceButton(WebElement submitPriceButton) {
        this.submitPriceButton = submitPriceButton;
        return this;
    }

    public List<HtmlLabel> getMyFirstLables() {
        return myFirstLables;
    }

    public SearchPage setMyFirstLables(List<HtmlLabel> myFirstLables) {
        this.myFirstLables = myFirstLables;
        return this;
    }

    public HtmlLabel getMyFirstLable() {
        return myFirstLable;
    }

    public SearchPage setMyFirstLable(HtmlLabel myFirstLable) {
        this.myFirstLable = myFirstLable;
        return this;
    }
}
