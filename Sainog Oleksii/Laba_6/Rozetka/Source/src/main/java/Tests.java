import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {
    private final static String startUrl = "https://rozetka.com.ua/ua/search/?class=0&text=%D0%BB%D1%96%D1%82%D0%B5%D1%80%D0%B0%D1%82%D1%83%D1%80%D0%B0&p=1&price=2-408020/";
    private final static int price = 180;
    private static WebDriver driver;

    @BeforeClass
    public static void begin(){
        String exePath = "c:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test1(){
        driver.get(startUrl);
        RozetkaPage page = new RozetkaPage(driver);
        page.set_filterMin(price);
        page.set_filterSubmit();
        Assert.assertTrue(page.get_filterMin() == price);
        boolean result = true;
        List<Integer> prices = page.getPrices();
        for(Integer iter: prices){
            if(iter < price) result = false;
        }
        System.out.println(prices.toString());
        Assert.assertTrue(result);
    }

    @AfterClass
    public static void end(){
        driver.quit();
    }
}
