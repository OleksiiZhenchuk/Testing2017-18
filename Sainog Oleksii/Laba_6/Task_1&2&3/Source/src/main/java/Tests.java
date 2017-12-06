import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


@RunWith(Parameterized.class)
public class Tests {
    private static WebDriver driver;
    private final static String start_url = "https://www.google.com.ua";
    private String company_on_first, company_not_on_first, non_existent_company ;
    private String request;
    private static FileWriter output;
    private final static String badEnd = "The test failed!";

    public Tests(String file, String com_1, String com_2, String com_3, String req_) throws IOException {
        company_not_on_first = com_1;
        company_on_first = com_2;
        non_existent_company = com_3;
        request = req_;
        output = new FileWriter(file, true);
        output.write("Request: " + request + "\n");
    }

    @BeforeClass
    public static void begin() throws IOException {
        String exePath = "c:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // driver.manage().window().fullscreen();
    }

    @Before
    public  void start() throws InterruptedException {
        driver.get(start_url);
        GooglePage page = new GooglePage(driver);
        page.inputRequest(request);
        while (!page.isExisting(page.getSearchField())){Thread.sleep(100);}
    }

    @Test
    public void Test1() throws AWTException, IOException {
        if (!company_not_on_first.equals("")){
            boolean result = false;
            GooglePage page = new GooglePage(driver);
            while (page.hasNextPage()) {
                page = page.nextPage();
                if(page.isInPage(this.company_not_on_first)){
                    result = true;
                    String str = company_not_on_first +
                            " can be found on page № " +
                            page.getNumberOfPage() + "\n";
                    output.write(str);
                }
            }
            assertTrue(badEnd, result);
        }
    }

    @Test
    public void Test2() throws AWTException, IOException {
        if(!company_on_first.equals("")){
            boolean result = false;
            GooglePage page = new GooglePage(driver);
            if(page.isInPage(this.company_on_first)){
                result = true;
                String str = company_on_first +
                        " can be found on page № " +
                        page.getNumberOfPage() + "\n";
                output.write(str);
            }
            assertTrue(badEnd, result);
        }
    }

    @Test
    public void Test3() throws AWTException, IOException {
        if (!non_existent_company.equals("")){
            boolean result = false;
            GooglePage page = new GooglePage(driver);
            while (page.hasNextPage()) {
                page = page.nextPage();
                if(page.isInPage(this.non_existent_company)) {
                    result = true;
                }
            }
            if (!result) {
                String str = non_existent_company + " was not found on any page!\n";
                output.write(str);
            }
            assertFalse(badEnd, result);
        }
    }

    @AfterClass
    public static void end() {
        driver.quit();
        output.close();
    }

    private static String fileName(String req, String com1, String com2, String com3){
        return  req.toUpperCase() + "(" +
                com1 + ")(" +
                com2 + ")(" +
                com3 + ").txt";    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        String request1 = "осцилограф";
        String company1_1 = "Tektronix";
        String company1_2 = "Masteram";
        String company1_3 = "Ducati";
        String outputFile1 = fileName(request1, company1_1, company1_2,company1_3);

        Object[][] data = new Object[][]{
                {outputFile1, company1_1, company1_2, company1_3, request1}
        };
        return Arrays.asList(data);
    }
}
