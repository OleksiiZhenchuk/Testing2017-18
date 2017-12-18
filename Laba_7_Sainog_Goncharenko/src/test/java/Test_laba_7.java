import Contexts.*;
import Driver.DriverSingleton;
import PageObjects.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Test_laba_7 {
    private final static String startUrl = "https://rozetka.com.ua/ua/noski-golfy-getry-sledy/c1136465/price=100-1690/";
    private static WebDriver driver;
    private final static String browser = "chrome";

    @BeforeClass
    public static void begin(){
        driver = DriverSingleton.getInstance(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void Test() throws Exception{
        //Arrange
        driver.get(startUrl);
        FictionBook startPage = new FictionBook(driver);
        int min = 100, max = 2000;

        //Act
        Filter.filterByRange(startPage, min, max);

        //Assert
        Assert.assertTrue(Filter.verifyRange(startPage, min, max));

        //Act
        Filter.filterByCountry(startPage, "Україна");

        //Assert
        Assert.assertTrue(Filter.producerIsChecked(startPage));

        //Act
        GoodPage goodPage = SetOfGoods.clickOnGood(startPage, 0);
        BasketPage basketPage = Good.buyGood(goodPage);
        ContactsPage contactsPage = Basket.confirmOrder(basketPage);

        //Assert
        Assert.assertTrue(driver.getCurrentUrl().equals("https://my.rozetka.com.ua/ua/checkout/?"));

        //Act
        Contacts.inputContatData(contactsPage, "Voldemar", "+38-096-584-35-14", "dulguldur@gmail.com");

        //Assert
        Assert.assertTrue(Contacts.ButtonNextIsClickable(contactsPage));

        //Act
        Thread.sleep(2000);
        DeliveryPage deliveryPage = Contacts.chooseDeliveryType(contactsPage);
        Delivery.inputDeliveryData(deliveryPage, "Нової Пошти", "Готівкою");

        //Assert
        Assert.assertTrue(Delivery.makeOrderButtonIsEnable(deliveryPage));
    }

    @AfterClass
    public static void end(){
        driver.quit();
    }

}
