import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

class GooglePage{

    private ExpandedChromeDriver driver;

    GooglePage(ExpandedChromeDriver new_driver){
        driver = new_driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "pnnext")
    private WebElement nextPageButton;

    @FindBy(className = "cur")
    private WebElement number;

    @FindBy(className = "g")
    private List<WebElement> paragraphs;

    @FindBy(css= "#fprs > a.spell_orig")
    private WebElement spell;

    @FindBy(id = "lst-ib")
    private WebElement searchField;


    boolean isExisting(WebElement elem) {
        try {
            elem.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {return false; }
    }

    void inputRequest(String request){
        searchField.sendKeys(request);
        searchField.sendKeys(Keys.ENTER);
    }

    String getNumberOfPage(){
        return (isExisting(number))? number.getText() : "1";
    }

    WebElement getSearchField() {return searchField;}
    WebElement getSpell() {return spell;}

     boolean hasNextPage(){
        return isExisting(nextPageButton);
    }

    GooglePage nextPage(){
        if (isExisting(nextPageButton)) nextPageButton.click();
        return new GooglePage(driver);
    }

    boolean isInPage(String company){
        for(WebElement iter: paragraphs)
            if (iter
                    .getText().toUpperCase()
                            .contains(
                                    company.toUpperCase()
                            )
                ) return true;
        return false;
    }

    File getScreenshot() throws Exception{
        return driver.getFullScreenshotAs(OutputType.FILE);
    }
}