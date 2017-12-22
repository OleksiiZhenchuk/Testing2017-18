package contexts.actions;
import extentions.NotExistProducerExeption;
import extentions.NotHaveProductsExeption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;
import utill.Waiters;

import java.util.List;
import java.util.Random;

public class Filtering {

    private static SearchPage yet(BasePage page){
        By by = new By.ByCssSelector("#filter_parameters_form > div:nth-child(2) > div.pos-fix > a:nth-child(2)");
        //Waiters.waitClickable(getDriver(), by, 5000, 5);
        Waiters.thredsleep(2000);
        WebElement clickYet = page.getDriver().findElement(by);
        //Waiters.waitClickableAndDisplayed(getDriver(), clickYet, 5000, 500);
        clickYet.click();
        return (SearchPage) page;
    }
    public static SearchPage setPrice(SearchPage page, Integer minPrice, Integer maxPrice){
        page.setMinimumPrice(minPrice).setMaximumPrice(maxPrice)
                .priceSumit();
        return page;
    }

    public static SearchPage chooseProducer(SearchPage page, String producer)throws NotExistProducerExeption, NotHaveProductsExeption{
        yet(page);
        int current = -1;
        for (int i = 0; i < page.getMyFirstLables().size(); i++) {
            if (page.getMyFirstLables().get(i).getLableName().equalsIgnoreCase(producer.trim())) {
                current = i;
                break;
            }
        }

        if (current == -1) {
            throw new NotExistProducerExeption();
        } else {
            page.setMyFirstLable(page.getMyFirstLables().get(current));
            if (page.getMyFirstLable().getCounterLable() == 0) {
                throw new NotHaveProductsExeption();
            } else {
                Waiters.thredsleep(1000);
                page.getMyFirstLable().click();
            }
        }
        return page;
    }

    public static ProductPage setProduct(SearchPage page, String producer){
        By linksXpath = new By.ByXPath("//*[@id='catalog_goods_block']/div/div/div/div/div/div/*[@class='g-i-tile-i-title clearfix']/a");
        Waiters.waitUrlContain(page.getDriver(), producer, 10000, 250);
        List<WebElement> links = page.getDriver().findElements(linksXpath);
        Random rnd = new Random(System.currentTimeMillis());
        int pageNum = rnd.nextInt(links.size());
        Waiters.waitClickableAndDisplayed(page.getDriver(), links.get(pageNum), 10000, 250);
        links.get(pageNum).click();
        return new ProductPage(page.getDriver());
    }

}
