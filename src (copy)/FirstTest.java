import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ViTaLES on 10.01.2016.
 */
public class FirstTest {

    private WebDriver driver;
    private String baseUrl;

    //@Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://www.ellos.se/";
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//magic

        //what we must add in this place when we will use WebElementsActions class?
    }

    //@Test
    public void testUntitled2() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("artcode");
        driver.findElement(By.name("btnG")).click();

        /**
         *  Example for checking something in your tests
         *  at present time
         */
        /*
        if ( driver.findElement(By.id("lst-ib")).isDisplayed()) {
            Assert.fail();
        }*/
    }

    //@After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
