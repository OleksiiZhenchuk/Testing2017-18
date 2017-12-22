import contexts.actions.BuyProduct;
import contexts.actions.Filtering;
import contexts.actions.MakeOrder;
import extentions.NotExistProducerExeption;
import extentions.NotHaveProductsExeption;
import extentions.NotValidDataExeption;
import helpers.PersonData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utill.Waiters;
import static junit.framework.TestCase.assertTrue;

public class Test1 {
    private static WebDriver driver;
    public static String RURL = "https://rozetka.com.ua/ua/vino/c4594285/filter/";
    @BeforeClass
    public static void Init()throws Exception{
        String exePath = "C:\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
    }
    @AfterClass
    public static void Close(){
        driver.quit();
    }


    @Test
    public void test() {
        SearchPage filteringPage = new SearchPage(driver);
        filteringPage.start(RURL);

        //Set Current price
        int minPrice = 100;
        int maxPrice = 2000;

        Filtering.setPrice(filteringPage, minPrice, maxPrice);

        //Set producer of product
        String producer = "Cinzano";

        try {
            filteringPage = Filtering.chooseProducer(filteringPage, producer);
        }
        catch (NotExistProducerExeption exeption){
            System.out.println(exeption.getMessage());
        }
        catch (NotHaveProductsExeption exeption1){
            System.out.println(exeption1.getMessage());
        }

        ProductPage choosenProduct = Filtering.setProduct(filteringPage, producer);

        CheckOut checkOutProduct = BuyProduct.buyAndSubmitProduct(choosenProduct);


        Finish deliverProduct = null;
        try {
            deliverProduct = MakeOrder.initilizateBuy(checkOutProduct, new PersonData());
        }
        catch (NotValidDataExeption e){
            e.getMessage();
        }

        assertTrue(deliverProduct.counter());
    }

}