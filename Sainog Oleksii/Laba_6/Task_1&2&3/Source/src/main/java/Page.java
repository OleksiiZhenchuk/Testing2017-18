import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

class GooglePage{

    private WebDriver driver;

    GooglePage(WebDriver new_driver){
        driver = new_driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "pnnext")
    private WebElement nextPageButton;

    @FindBy(className = "cur")
    private WebElement number;

    @FindBy(className = "g")
    private List<WebElement> paragraphs;

    @FindBy(id = "lst-ib")
    private WebElement searchField;


    public boolean isExisting(WebElement elem) {
        try {
            elem.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {return false; }
    }

    public void inputRequest(String request){
        searchField.sendKeys(request);
        searchField.sendKeys(Keys.ENTER);
    }

    String getNumberOfPage(){
        return (isExisting(number))? number.getText() : "1";
    }

    WebElement getSearchField() {return searchField;}

    public boolean hasNextPage(){
        return isExisting(nextPageButton);
    }

    public GooglePage nextPage(){
        if (isExisting(nextPageButton)) nextPageButton.click();
        return new GooglePage(driver);
    }

    public boolean isInPage(String company){
        for(WebElement iter: paragraphs)
            if (iter.getText().contains(company)) return true;
        return false;
    }
}